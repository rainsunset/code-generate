package com.rainsunset.codegenerate.service;

import com.rainsunset.codegenerate.bean.FilePathConfigReqDTO;
import com.rainsunset.codegenerate.bean.PackagePathBO;
import com.rainsunset.codegenerate.bean.TableInfoBO;
import com.rainsunset.codegenerate.common.constants.Constants;
import com.rainsunset.codegenerate.common.util.StaticizePage;
import com.rainsunset.codegenerate.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @Description: 代码生成
 * @Author: ligangwei
 * @Company rainsunset
 * @CreateDate: 2019.04.13
 * @Version : 1.0
 */
public class CodeCreator {

    /** logger */
    private final static Logger logger = LoggerFactory.getLogger(CodeCreator.class);


    /**
     * 构造模版变量 -> 调用模版生成代码
     *
     * @param templatesPath        模板文件所在文件夹绝对路径
     * @param filePathConfigReqDTO 文件路径 包信息 是否生成 配置
     * @param packagePathBO        代码各层包文件相对路径
     * @param tableInfoBO          表及表字段信息
     * @author : ligangwei / 2019-04-12
     */
    public static void creatorCode(String templatesPath, FilePathConfigReqDTO filePathConfigReqDTO,
                                   PackagePathBO packagePathBO ,TableInfoBO tableInfoBO) {
        String moduleName = filePathConfigReqDTO.getModuleName();
        String rootPackage = StringUtil.conlitionStr(filePathConfigReqDTO.getBaseClassPath() ,
                Constants.DOT,moduleName);
        String tableBigCamel = tableInfoBO.getTableBigCamel();
        String tableSmallCamel = tableInfoBO.getTableSmallCamel();
        // 构建模板需要数据
        Map<String, Object> demoMap = new HashMap<String, Object>();
        demoMap.put("moduleName", moduleName);
        demoMap.put("rootPackage", rootPackage);
        // 表名
        demoMap.put("tableName", tableInfoBO.getTableName());
        demoMap.put("tableComment", tableInfoBO.getTableComment());
        demoMap.put("tableBigCamel", tableBigCamel);
        demoMap.put("tableSmallCamel", tableSmallCamel);
        //添加主键
        demoMap.put("tabPrikey",tableInfoBO.getTabPrikey());
        demoMap.put("tabPrikeySmallCamel",tableInfoBO.getTabPrikeySmallCamel());
        demoMap.put("tabPrikeyBigCamel",tableInfoBO.getTabPrikeyBigCamel());
        demoMap.put("tabPrikeyType", tableInfoBO.getTabPrikeyType());
        demoMap.put("tabPrikeyTypeFullName", tableInfoBO.getTabPrikeyTypeFullName());
        demoMap.put("tabPrikeyComment",tableInfoBO.getTabPrikeyComment());
        demoMap.put("tabPrikeyIsautoIncrement", tableInfoBO.getTabPrikeyIsautoIncrement());
        //添加表列名 列别名 表注释 字段属性
        demoMap.put("columList",tableInfoBO.getColumList());
        //添加#
        demoMap.put("u0023","#");
        //添加时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        demoMap.put("now",currentDateTime);

        // 包路径
        demoMap.put("packModel", packagePathBO.getPackModel());
        demoMap.put("packDao", packagePathBO.getPackDao());
        demoMap.put("packMapper", packagePathBO.getPackMapper());
        demoMap.put("packService", packagePathBO.getPackService());
        demoMap.put("packServiceImpl", packagePathBO.getPackServiceImpl());
        demoMap.put("packManager", packagePathBO.getPackManager());
        demoMap.put("packController", packagePathBO.getPackController());
        demoMap.put("packTestDao", packagePathBO.getPackTestDao());
        demoMap.put("packTestService", packagePathBO.getPackTestService());
        demoMap.put("packPageControler", packagePathBO.getPackPageControler());
        demoMap.put("packPageService", packagePathBO.getPackPageService());
        demoMap.put("packPageTemplate", packagePathBO.getPackPageTemplate());
        demoMap.put("packPageView", packagePathBO.getPackPageView());

        //作者及公司及版本信息
        demoMap.put("codeAuthor",Constants.CODE_AUTHOR);
        demoMap.put("codeCompany", Constants.CODE_COMPANY);
        demoMap.put("codeVersion", Constants.CODE_VERSION);

        // 文件保存路径
        String outFilePath = filePathConfigReqDTO.getOutFilePath();
        // module 最外层文件夹路径
        String filePathModule = StringUtil.conlitionStr(outFilePath,File.separator,moduleName);

        // 生成文件夹 生成代码
        // region src-main-java|resources 下package文件路径
        // main-java-package-dal
        if (filePathConfigReqDTO.isGenerateDao() && (!StringUtils.isEmpty(packagePathBO.getPackDao()))) {
            // dao层文件绝对路径
            String dirPathDao = packagePathBO.getPackDaoDirPath(filePathModule);
            // 创建dao层文件夹
            creatDirectory(dirPathDao);
            // dao文件路径及名称
            String fileName = StringUtil.conlitionStr(dirPathDao, File.separator, tableBigCamel, Constants.DAO_FILE_SUFFIX);
            // dao模板文件名称
            String templatesName = packagePathBO.getPackDaoTemplateFileName();
            // 生成dao文件
            handleCreatorCode(fileName, templatesPath ,templatesName, demoMap);
        }
        // main-resources-mapper
        if (filePathConfigReqDTO.isGenerateDao() && (!StringUtils.isEmpty(packagePathBO.getPackMapper()))) {
            // mapper层文件绝对路径
            String dirPathMapper = packagePathBO.getPackMapperDirPath(filePathModule);
            // 创建mapper层文件夹
            creatDirectory(dirPathMapper);
            // mapper文件路径及名称
            String fileName = StringUtil.conlitionStr(dirPathMapper, File.separator, tableSmallCamel, Constants.MAPPER_FILE_SUFFIX);
            // mapper模板文件名称
//            String templatesName = tableInfoBO.getTabPrikeyIsautoIncrement() ?
//                    packagePathBO.getPackMapperIncrementTemplateFileName() :
//                    packagePathBO.getPackMapperTemplateFileName();
            String templatesName = packagePathBO.getPackMapperTemplateFileName();
            // 生成mapper文件
            handleCreatorCode(fileName, templatesPath ,templatesName, demoMap);
        }
        // main-java-package-model
        if (filePathConfigReqDTO.isGenerateModel() && (!StringUtils.isEmpty(packagePathBO.getPackModel()))) {
            // model层文件绝对路径
            String dirPathModel = packagePathBO.getPackModelDirPath(filePathModule);
            // 创建model层文件夹
            creatDirectory(dirPathModel);
            // model文件路径及名称
            String fileName = StringUtil.conlitionStr(dirPathModel, File.separator, tableBigCamel, Constants.MODEL_FILE_SUFFIX);
            // model模板文件名称
            String templatesName = packagePathBO.getPackModelTemplateFileName();
            // 生成model文件
            handleCreatorCode(fileName, templatesPath ,templatesName, demoMap);
        }
        // main-java-package-service
        if (filePathConfigReqDTO.isGenerateService() && (!StringUtils.isEmpty(packagePathBO.getPackService()))) {
            // service层文件绝对路径
            String dirPathService = packagePathBO.getPackServiceDirPath(filePathModule);
            // 创建service层文件夹
            creatDirectory(dirPathService);
            // service文件路径及名称
            String fileName = StringUtil.conlitionStr(dirPathService, File.separator, tableBigCamel, Constants.SERVICE_FILE_SUFFIX);
            // service模板文件名称
            String templatesName = packagePathBO.getPackServiceTemplateFileName();
            // 生成service文件
            handleCreatorCode(fileName, templatesPath ,templatesName, demoMap);
        }
        // main-java-package-serviceimpl
        if (filePathConfigReqDTO.isGenerateService() && (!StringUtils.isEmpty(packagePathBO.getPackServiceImpl()))) {
            // serviceimpl层文件绝对路径
            String dirPathServiceImpl = packagePathBO.getPackServiceImplDirPath(filePathModule);
            // 创建serviceimpl层文件夹
            creatDirectory(dirPathServiceImpl);
            // serviceimpl文件路径及名称
            String fileName = StringUtil.conlitionStr(dirPathServiceImpl, File.separator, tableBigCamel, Constants.SERVICEIMPL_FILE_SUFFIX);
            // serviceimpl模板文件名称
            String templatesName = packagePathBO.getPackServiceImplTemplateFileName();
            // 生成serviceimpl文件
            handleCreatorCode(fileName, templatesPath ,templatesName, demoMap);
        }
        // main-java-package-manager
        if (filePathConfigReqDTO.isGenerateService() && (!StringUtils.isEmpty(packagePathBO.getPackManager()))) {
            // manager层文件绝对路径
            String dirPathManager = packagePathBO.getPackManagerDirPath(filePathModule);
            // 创建manager层文件夹
            creatDirectory(dirPathManager);
            // manager文件路径及名称
            String fileName = StringUtil.conlitionStr(dirPathManager, File.separator, tableBigCamel, Constants.MANAGER_FILE_SUFFIX);
            // manager模板文件名称
            String templatesName = packagePathBO.getPackManagerTemplateFileName();
            // 生成manager文件
            handleCreatorCode(fileName, templatesPath ,templatesName, demoMap);
        }
        // main-java-package-controller
        if (filePathConfigReqDTO.isGenerateController() && (!StringUtils.isEmpty(packagePathBO.getPackController()))) {
            // controller层文件绝对路径
            String dirPathController = packagePathBO.getPackControllerDirPath(filePathModule);
            // 创建controller层文件夹
            creatDirectory(dirPathController);
            // controller文件路径及名称
            String fileName = StringUtil.conlitionStr(dirPathController, File.separator, tableBigCamel, Constants.CONTROLLER_FILE_SUFFIX);
            // controller模板文件名称
            String templatesName = packagePathBO.getPackControllerTemplateFileName();
            // 生成controller文件
            handleCreatorCode(fileName, templatesPath ,templatesName, demoMap);
        }
        // endregion

        // region src-main-test-java 下package路径
        // test-java-package-dal
        if (filePathConfigReqDTO.isGenerateTest() && (!StringUtils.isEmpty(packagePathBO.getPackTestDao()))) {
            // test-dao层文件绝对路径
            String dirPathTestDao = packagePathBO.getPackTestDaoDirPath(filePathModule);
            // 创建test-dao层文件夹
            creatDirectory(dirPathTestDao);
            // test-dao文件路径及名称
            String fileName = StringUtil.conlitionStr(dirPathTestDao, File.separator, tableBigCamel, Constants.TEST_DAO_FILE_SUFFIX);
            // test-dao模板文件名称
            String templatesName = packagePathBO.getPackTestDaoTemplateFileName();
            // 生成test-dao文件
            handleCreatorCode(fileName, templatesPath ,templatesName, demoMap);
        }
        // test-java-package-service
        if (filePathConfigReqDTO.isGenerateTest() && (!StringUtils.isEmpty(packagePathBO.getPackTestService()))) {
            // test-service层文件绝对路径
            String dirPathTestService = packagePathBO.getPackTestServiceDirPath(filePathModule);
            // 创建test-service层文件夹
            creatDirectory(dirPathTestService);
            // test-service文件路径及名称
            String fileName = StringUtil.conlitionStr(dirPathTestService, File.separator, tableBigCamel, Constants.TEST_SERVICE_FILE_SUFFIX);
            // test-service模板文件名称
            String templatesName = packagePathBO.getPackTestServiceTemplateFileName();
            // 生成test-service文件
            handleCreatorCode(fileName, templatesPath ,templatesName, demoMap);
        }
        // endregion

        // region page 下路径
        // page-controller
        if (filePathConfigReqDTO.isGeneratePage() && (!StringUtils.isEmpty(packagePathBO.getPackPageControler()))) {
            // page-controller层文件绝对路径
            String dirPathPageControler = packagePathBO.getPackPageControlerDirPath(filePathModule);
            // 创建page-controller层文件夹
            creatDirectory(dirPathPageControler);
            // page-controller文件路径及名称
            String fileName = StringUtil.conlitionStr(dirPathPageControler, File.separator, tableSmallCamel, Constants.PAGE_CONTROLLER_FILE_SUFFIX);
            // page-controller模板文件名称
            String templatesName = packagePathBO.getPackPageControlerTemplateFileName();
            // 生成page-controller文件
            handleCreatorCode(fileName, templatesPath ,templatesName, demoMap);
        }
        // page-service
        if (filePathConfigReqDTO.isGeneratePage() && (!StringUtils.isEmpty(packagePathBO.getPackPageService()))) {
            // page-service层文件绝对路径
            String dirPathPageService = packagePathBO.getPackPageServiceDirPath(filePathModule);
            // 创建page-service层文件夹
            creatDirectory(dirPathPageService);
            // page-service文件路径及名称
            String fileName = StringUtil.conlitionStr(dirPathPageService, File.separator, tableSmallCamel, Constants.PAGE_SERVICE_FILE_SUFFIX);
            // page-service模板文件名称
            String templatesName = packagePathBO.getPackPageServiceTemplateFileName();
            // 生成page-service文件
            handleCreatorCode(fileName, templatesPath ,templatesName, demoMap);
        }
        // page-template
        if (filePathConfigReqDTO.isGeneratePage() && (!StringUtils.isEmpty(packagePathBO.getPackPageTemplate()))) {
            // page-template层文件绝对路径
            String dirPathPageTemplate = packagePathBO.getPackPageTemplateDirPath(filePathModule);
            // 创建page-template层文件夹
            creatDirectory(dirPathPageTemplate);
            // page-template文件路径及名称
            String fileName = StringUtil.conlitionStr(dirPathPageTemplate, File.separator, tableSmallCamel, Constants.PAGE_TEMPLATE_FILE_SUFFIX);
            // page-template模板文件名称
            String templatesName = packagePathBO.getPackPageTemplateTemplateFileName();
            // 生成page-template文件
            handleCreatorCode(fileName, templatesPath ,templatesName, demoMap);
        }
        // page-view
        if (filePathConfigReqDTO.isGeneratePage() && (!StringUtils.isEmpty(packagePathBO.getPackPageView()))) {
            // page-view层文件绝对路径
            String dirPathPageView = packagePathBO.getPackPageViewDirPath(filePathModule);
            // 创建page-view层文件夹
            creatDirectory(dirPathPageView);
            // page-view文件路径及名称
            String fileName = StringUtil.conlitionStr(dirPathPageView, File.separator, tableSmallCamel, Constants.PAGE_VIEW_FILE_SUFFIX);
            // page-view模板文件名称
            String templatesName = packagePathBO.getPackPageViewTemplateFileName();
            // 生成page-view文件
            handleCreatorCode(fileName, templatesPath ,templatesName, demoMap);
        }
        // endregion
    }

    /**
     * 本地创建文件夹 存在则不创建
     *
     * @param directoryPath the directory path
     * @author : ligangwei / 2019-04-12
     */
    private static void creatDirectory(String directoryPath) {
        if (StringUtils.isEmpty(directoryPath)) {
            return;
        }
        File file_main_controller = new File(directoryPath);
        if (!file_main_controller.isDirectory()) {
            file_main_controller.mkdirs();
            logger.debug(" >>> 创建目录：{}",directoryPath);
        }
    }

    /**
     * @param outPath
     * @param templatesPath
     * @param templatesName
     * @param data
     */
    private static void handleCreatorCode(String outPath, String templatesPath, String templatesName,
            Map<String, Object> data) {
        Writer out;
        try {
            out = new OutputStreamWriter(new FileOutputStream(outPath), "UTF-8");
            new StaticizePage().buildHtmlPage(new File(templatesPath), Locale.CHINA, "UTF-8", templatesName, out, data);
        }
        catch (Exception e) {
            throw new RuntimeException(" [ WHAOSOFT ] " + e);
        }
    }

}
