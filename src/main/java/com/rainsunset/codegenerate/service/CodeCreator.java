package com.rainsunset.codegenerate.service;

import com.rainsunset.codegenerate.bean.FileGererateInfoBO;
import com.rainsunset.codegenerate.bean.FilePathConfigReqDTO;
import com.rainsunset.codegenerate.bean.PackageGenerateInfoBO;
import com.rainsunset.codegenerate.bean.TableInfoBO;
import com.rainsunset.codegenerate.common.constants.Constants;
import com.rainsunset.codegenerate.common.util.StaticizePage;
import com.rainsunset.codegenerate.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;

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
     * @param packageGenerateInfoBO        代码各层包文件相对路径
     * @param tableInfoBO          表及表字段信息
     * @author : ligangwei / 2019-04-12
     */
    public static void creatorCode(String templatesPath, FilePathConfigReqDTO filePathConfigReqDTO,
                                   PackageGenerateInfoBO packageGenerateInfoBO, TableInfoBO tableInfoBO,
                                   String codeAuthor, String codeCompany, String codeVersion) {
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

        //作者及公司及版本信息
        demoMap.put("codeAuthor",codeAuthor);
        demoMap.put("codeCompany",codeCompany);
        demoMap.put("codeVersion",codeVersion);

        // 文件保存路径
        String outFilePath = filePathConfigReqDTO.getOutFilePath();
        // module 最外层文件夹路径
        String filePathModule = StringUtil.conlitionStr(outFilePath,File.separator,moduleName);

        // 生成文件夹 生成代码
        // region src-main-java|resources 下package文件路径
        // main-java-package-model
        if (filePathConfigReqDTO.isGenerateModel() && (!CollectionUtils.isEmpty(packageGenerateInfoBO.getPackModel()))) {
            List<FileGererateInfoBO> packModel = packageGenerateInfoBO.getPackModel();
            creatModelCode(packModel, filePathModule, tableBigCamel, templatesPath, demoMap);
        }
        // main-java-package-dal
        if (filePathConfigReqDTO.isGenerateDao() && (!CollectionUtils.isEmpty(packageGenerateInfoBO.getPackDao()))) {
            List<FileGererateInfoBO> packDao = packageGenerateInfoBO.getPackDao();
            creatModelCode(packDao, filePathModule, tableBigCamel, templatesPath, demoMap);
        }
        // main-resources-mapper
        if (filePathConfigReqDTO.isGenerateDao() && (!CollectionUtils.isEmpty(packageGenerateInfoBO.getPackMapper()))) {
            List<FileGererateInfoBO> packMapper = packageGenerateInfoBO.getPackMapper();
            creatModelCode(packMapper, filePathModule, tableBigCamel, templatesPath, demoMap);
        }

        // main-java-package-service
        if (filePathConfigReqDTO.isGenerateService() && (!CollectionUtils.isEmpty(packageGenerateInfoBO.getPackService()))) {
            // main-java-package-serviceimpl
            // main-java-package-manager
            List<FileGererateInfoBO> packService = packageGenerateInfoBO.getPackService();
            creatModelCode(packService, filePathModule, tableBigCamel, templatesPath, demoMap);
        }

        // main-java-package-controller
        if (filePathConfigReqDTO.isGenerateController() && (!CollectionUtils.isEmpty(packageGenerateInfoBO.getPackController()))) {
            List<FileGererateInfoBO> packController = packageGenerateInfoBO.getPackController();
            creatModelCode(packController, filePathModule, tableBigCamel, templatesPath, demoMap);
        }
        // endregion

        // region src-main-test-java 下package路径
        if (filePathConfigReqDTO.isGenerateTest() && (!CollectionUtils.isEmpty(packageGenerateInfoBO.getPackTest()))) {
            // test-java-package-dal
            // test-java-package-service
            List<FileGererateInfoBO> packTest = packageGenerateInfoBO.getPackTest();
            creatModelCode(packTest, filePathModule, tableBigCamel, templatesPath, demoMap);
        }
        // endregion

        // region page 下路径
        if (filePathConfigReqDTO.isGeneratePage() && (!CollectionUtils.isEmpty(packageGenerateInfoBO.getPackPage()))) {
            // page-controller
            // page-service
            // page-template
            // page-view
            List<FileGererateInfoBO> packPage = packageGenerateInfoBO.getPackPage();
            creatModelCode(packPage, filePathModule, tableBigCamel, templatesPath, demoMap);
        }
        // endregion
    }

    /**
     * 生成每层的代码
     *
     * @param packFileInfo   the pack file info
     * @param filePathModule the file path module
     * @param tableBigCamel  the table big camel
     * @param templatesPath  the templates path
     * @param demoMap        the demo map
     * @author : ligangwei / 2019-04-30
     */
    private static void creatModelCode(List<FileGererateInfoBO> packFileInfo, String filePathModule, String tableBigCamel,
                                       String templatesPath,Map<String, Object> demoMap) {
        for (int i = 0; i < packFileInfo.size(); i++) {
            FileGererateInfoBO fileGererateInfoBO = packFileInfo.get(i);
            // 层文件绝对路径
            String dirPathPackage = fileGererateInfoBO.getAbsPackageFilePath(filePathModule);
            // 创建model层文件夹
            creatDirectory(dirPathPackage);
            // 文件路径及名称
            String fileName = StringUtil.conlitionStr(dirPathPackage, File.separator, tableBigCamel, fileGererateInfoBO.getFileSuffix());
            // 模板文件名称
            String templatesName = fileGererateInfoBO.getTemplatesName();
            // 生成文件
            handleCreatorCode(fileName, templatesPath, templatesName, demoMap);
        }
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
