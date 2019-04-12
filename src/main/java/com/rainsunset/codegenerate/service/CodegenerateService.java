package com.rainsunset.codegenerate.service;

import com.rainsunset.codegenerate.bean.*;
import com.rainsunset.codegenerate.common.constants.Constants;
import com.rainsunset.codegenerate.common.enums.TemplatesTypeEnum;
import com.rainsunset.codegenerate.common.util.DBUtil;
import com.rainsunset.codegenerate.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author whaosoft
 */
@Service
public class CodegenerateService {

    /** logger */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 测试连接
     *
     * @param dataBaseConfigReqDTO the data base config req dto
     * @return boolean
     * @author : ligangwei / 2019-04-10
     */
    public boolean doTestConnection(DataBaseConfigReqDTO dataBaseConfigReqDTO) {
        DataBaseSettingBO dbs = new DataBaseSettingBO(dataBaseConfigReqDTO);
        DBUtil.queryTable(dbs, dbs.getTestSql());
        return true;
    }

    /**
     * 查询表名list
     *
     * @param dataBaseConfigReqDTO the data base config req dto
     * @return list
     * @author : ligangwei / 2019-04-10
     */
    public List<String> doQueryTableNames(DataBaseConfigReqDTO dataBaseConfigReqDTO) {
        DataBaseSettingBO dbs = new DataBaseSettingBO(dataBaseConfigReqDTO);
        List<Map<String,Object>> objList = DBUtil.queryTable(dbs, dbs.getShowTable());
        if (CollectionUtils.isEmpty(objList)) {
            return null;
        }
        String showtableSqlColumnLabel = genShowtableSqlColumnLabel(dataBaseConfigReqDTO.getDbName());
        List<String> tabNameList = new ArrayList<>();
        for (int i = 0; i < objList.size(); i++) {
            Map<String, Object> obj = objList.get(i);
            if (null == obj || StringUtils.isEmpty(obj.get(showtableSqlColumnLabel))) {
                continue;
            }
            tabNameList.add((String) obj.get(showtableSqlColumnLabel));
        }
        return tabNameList;
    }


    /**
     * 生成查询数据库表list结果的列名
     * 规则:"Tables_in_" + dbName
     *
     * @param dbName the db name
     * @return the string
     * @author : ligangwei / 2019-04-10
     */
    private String genShowtableSqlColumnLabel(String dbName) {
        StringBuilder sb = new StringBuilder("Tables_in_");
        return sb.append(dbName).toString();
    }

    /**
     * 生成代码
     *
     * @param generatePathConfigReqDTO the generate path config req dto
     * @return the boolean
     * @throws Exception
     * @author : ligangwei / 2019-04-11
     */
    @SuppressWarnings("unchecked")
    public boolean generateCode(GenerateConfigReqDTO generatePathConfigReqDTO){
        FilePathConfigReqDTO filePathConfigReqDTO = generatePathConfigReqDTO.getFilePathConfigReqDTO();
        String templatesType = generatePathConfigReqDTO.getTemplatesType();
        DataBaseSettingBO dbs = new DataBaseSettingBO(generatePathConfigReqDTO.getDataBaseConfigReqDTO());
        List<String> tabNameList = generatePathConfigReqDTO.getTabNameList();
        // 依据模板构建包相对路径
        PackagePathBO packagePathBO = PackagePathFactory.getPackagePath(templatesType);
        // 构建包文件夹绝对路径，并依据配置创建文件夹
        FilePathBO filePathBO = doCreateSource(filePathConfigReqDTO,packagePathBO);
        // 获取模版绝对系统文件路径
        String templatesPath = getTemplatesAbsPath(templatesType);
        if (StringUtils.isEmpty(templatesPath)) {
            return false;
        }
        // 获取数据库表注释
        Map<String,String> tabCommentMap = getTablesComment(dbs);
        for (String tabName : tabNameList) {
            if (StringUtils.isEmpty(tabName)) {
                continue;
            }
            logger.debug(" >>> 开始依据表{}创建代码：",tabName);
            String tabComment = tabCommentMap.get(tabName);
            // 表对象
            TableInfoBO tableInfoBO = new TableInfoBO(tabName,tabComment,dbs);
            // 数据已准备好 开始按照模板生成代码
            CodeCreator.creatorCode(templatesPath,filePathBO,filePathConfigReqDTO,tableInfoBO);
            logger.debug(" >>> 依据表{}创建代码完成：",tabName);
        }
        return true;
    }

    /**
     * 依据模板类型获取模版绝文件夹绝对系统文件路径
     *
     * @param templatesType the templates type
     * @return the string
     * @author : ligangwei / 2019-04-11
     */
    private String getTemplatesAbsPath(String templatesType) {
        String classPathAbsPath = "";
        try {
            classPathAbsPath = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX ).getPath();
        } catch (FileNotFoundException e) {
            logger.error("系统获取Resource绝对路径异常。\n MSG：{}",e.toString());
            return "" ;
        }
        String templatesPath = TemplatesTypeEnum.getTemplatePathByComp(templatesType);
        String templatesAbsPath = StringUtil.conlitionStr(classPathAbsPath,templatesPath , File.separator);
        return templatesAbsPath;
    }


    /**
     * 获取表说明
     * 默认说明为表名+表
     *
     * @param dbs the dbs
     * @return the map
     * @author : ligangwei / 2019-04-11
     */
    private Map<String,String> getTablesComment(DataBaseSettingBO dbs) {
        List<Map<String, Object>> tabCommentList = DBUtil.queryTable(dbs,dbs.getShowTablColumn());
        //将表明和表注释配对
        Map<String,String> tabCommentMap = new HashMap<String,String>();
        for (Map<String, Object> tabColumnMap : tabCommentList) {
            String tbName = (String) tabColumnMap.get("Name");
            String tbNameComment = (String) tabColumnMap.get("Comment");
            if ((null != tbNameComment) && (!"".equals(tbNameComment))) {
                tabCommentMap.put(tbName,tbNameComment);
            } else {
                tabCommentMap.put(tbName,StringUtil.conlitionStr(tbName,"表"));
            }
        }
        return tabCommentMap;
    }

    /**
     * 初始化每层代码的路径，并依据配置创建文件夹
     * 生成文件结构如下
     * |--moduleName
     * |----model
     * |----dal
     * |----mapper
     * |----service
     * |----serviceimpl
     * |----manager
     * |----controller
     * |----test
     * |------testservice
     * |------testdao
     * |----page
     * @param filePathConfigReqDTO the file path config req dto
     * @return the package file path bo
     * @author : ligangwei / 2019-04-11
     */
    private FilePathBO doCreateSource(FilePathConfigReqDTO filePathConfigReqDTO, PackagePathBO packagePathBO ) {
        // 保存路径 项目类路径 生成文件夹名 子模块儿名 是否需要生成文件夹 需要操作的数据库表明
        String outFilePath = filePathConfigReqDTO.getOutFilePath();
        String moduleName = filePathConfigReqDTO.getModuleName();
		// module 最外层文件夹路径
        FilePathBO filePathBO = new FilePathBO();
        String filePathModule = StringUtil.conlitionStr(outFilePath,File.separator,moduleName);
        // region src-main-java|resources 下package文件路径
        // main-java-package-dal
        if (filePathConfigReqDTO.isGenerateDao()) {
            String filePathDal = StringUtils.isEmpty(packagePathBO.getPackDaoRelFilePath()) ? null :
                    StringUtil.conlitionStr(filePathModule, File.separator, packagePathBO.getPackDaoRelFilePath());
            creatDirectory(filePathDal);
            filePathBO.setFilePathDao(filePathDal);
            // main-resources-mapper
            String filePathMapper = StringUtils.isEmpty(packagePathBO.getPackMapperRelFilePath()) ? null :
                    StringUtil.conlitionStr(filePathModule, File.separator, packagePathBO.getPackMapperRelFilePath());
            creatDirectory(filePathMapper);
            filePathBO.setFilePathMapper(filePathMapper);
        }
        if (filePathConfigReqDTO.isGenerateModel()) {
            // main-java-package-model
            String filePathModel = StringUtils.isEmpty(packagePathBO.getPackModelRelFilePath()) ? null :
                    StringUtil.conlitionStr(filePathModule, File.separator, packagePathBO.getPackModelRelFilePath());
            creatDirectory(filePathModel);
            filePathBO.setFilePathModel(filePathModel);
        }
        if (filePathConfigReqDTO.isGenerateService()) {
            // main-java-package-service
            String filePathService = StringUtils.isEmpty(packagePathBO.getPackServiceRelFilePath()) ? null :
                    StringUtil.conlitionStr(filePathModule, File.separator, packagePathBO.getPackServiceRelFilePath());
            creatDirectory(filePathService);
            filePathBO.setFilePathService(filePathService);
            // main-java-package-serviceimpl
            String filePathServiceImpl = StringUtils.isEmpty(packagePathBO.getPackServiceImplRelFilePath()) ? null :
                    StringUtil.conlitionStr(filePathModule, File.separator, packagePathBO.getPackServiceImplRelFilePath());
            creatDirectory(filePathServiceImpl);
            filePathBO.setFilePathServiceImpl(filePathServiceImpl);
            // main-java-package-manager
            String filePathManager = StringUtils.isEmpty(packagePathBO.getPackManagerRelFilePath()) ? null :
                    StringUtil.conlitionStr(filePathModule, File.separator, packagePathBO.getPackManagerRelFilePath());
            creatDirectory(filePathManager);
            filePathBO.setFilePathManager(filePathManager);
        }
        if (filePathConfigReqDTO.isGenerateController()) {
            // main-java-package-controller
            String filePathController = StringUtils.isEmpty(packagePathBO.getPackControllerRelFilePath()) ? null :
                    StringUtil.conlitionStr(filePathModule, File.separator, packagePathBO.getPackControllerRelFilePath());
            creatDirectory(filePathController);
            filePathBO.setFilePathController(filePathController);
        }
        // endregion

        // region src-main-test-java 下package路径
        if (filePathConfigReqDTO.isGenerateTest()) {
            // test-java-package-dal
            String filePathTestDal = StringUtils.isEmpty(packagePathBO.getPackTestDaoRelFilePath()) ? null :
                    StringUtil.conlitionStr(filePathModule, File.separator, Constants.PACKAGE_TEST,
                            File.separator,packagePathBO.getPackTestDaoRelFilePath());
            creatDirectory(filePathTestDal);
            filePathBO.setFilePathTestDao(filePathTestDal);
            // test-java-package-service
            String filePathTestService = StringUtils.isEmpty(packagePathBO.getPackTestServiceRelFilePath()) ? null :
                    StringUtil.conlitionStr(filePathModule, File.separator, Constants.PACKAGE_TEST,
                            File.separator,packagePathBO.getPackTestServiceRelFilePath());
            creatDirectory(filePathTestService);
            filePathBO.setFilePathTestService(filePathTestService);
        }
        // endregion

        // region page 下路径
        if (filePathConfigReqDTO.isGeneratePage()) {
            // page-controller
            String filePathPageController = StringUtils.isEmpty(packagePathBO.getPackMapperRelFilePath()) ? null :
                    StringUtil.conlitionStr(filePathModule, File.separator,Constants.PACKAGE_PAGE,
                            File.separator,packagePathBO.getPackPageControlerRelFilePath());
            creatDirectory(filePathPageController);
            filePathBO.setFilePathPageControler(filePathPageController);
            // TODO 后续添加页面生成模板
        }
        // endregion

		return filePathBO;
	}

    /**
     * 本地创建文件夹 存在则不创建
     *
     * @param directoryPath the directory path
     * @author : ligangwei / 2019-04-12
     */
    private void creatDirectory(String directoryPath) {
        if (StringUtils.isEmpty(directoryPath)) {
            return;
        }
        File file_main_controller = new File(directoryPath);
        if (!file_main_controller.isDirectory()) {
            file_main_controller.mkdirs();
            logger.debug(" >>> 创建目录：{}",directoryPath);
        }
    }

}
