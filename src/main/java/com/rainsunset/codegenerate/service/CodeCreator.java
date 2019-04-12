package com.rainsunset.codegenerate.service;

import com.rainsunset.codegenerate.bean.DataBaseSettingBO;
import com.rainsunset.codegenerate.bean.FilePathBO;
import com.rainsunset.codegenerate.bean.FilePathConfigReqDTO;
import com.rainsunset.codegenerate.bean.TableInfoBO;
import com.rainsunset.codegenerate.common.constants.Constants;
import com.rainsunset.codegenerate.common.util.DBUtil;
import com.rainsunset.codegenerate.common.util.StringUtil;
import com.rainsunset.codegenerate.service.cmbi.StaticizePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author whaosoft
 */
public class CodeCreator {

    /** logger */
    private final static Logger logger = LoggerFactory.getLogger(CodeCreator.class);


    /**
     * 构造模版变量 -> 调用模版生成代码
     *
     * @param templatesPath     模板文件所在文件夹绝对路径
     * @param filePathBO           代码各层包文件(绝对)路径
     * @param filePathConfigReqDTO 文件路径 包信息 是否生成 配置
     * @param tableInfoBO          表及表字段信息
     * @author : ligangwei / 2019-04-12
     */
    public static void creatorCode(String templatesPath, FilePathBO filePathBO, FilePathConfigReqDTO filePathConfigReqDTO,
                                   TableInfoBO tableInfoBO) {
        String moduleName = filePathConfigReqDTO.getModuleName();
        String rootPackage = StringUtil.conlitionStr(filePathConfigReqDTO.getBaseClassPath() ,
                Constants.DOT,moduleName);
        // main
        String filePath_bean = filePathBO.getFilePathModel();
        String filePath_dao = filePathBO.getFilePathDao();
        String filePath_service = filePathBO.getFilePathService();
        String filePath_service_impl = filePathBO.getFilePathServiceImpl();
        String filePath_controller = filePathBO.getFilePathController();
        String filePath_test_dao = filePathBO.getFilePathTestDao();
        String filePath_test_service = filePathBO.getFilePathTestService();
        String filePath_mapping = filePathBO.getFilePathMapper();
        String filePath_page = filePathBO.getFilePathPageView();
        String filePath_page_controler = filePathBO.getFilePathPageControler();
        String filePath_page_service = filePathBO.getFilePathPageService();
        String filePath_page_template = filePathBO.getFilePathPageTemplate();

        // 构建模板需要数据
        Map<String, Object> demoMap = new HashMap<String, Object>();
        demoMap.put("moduleName", moduleName);
        demoMap.put("rootPackage", rootPackage);

        demoMap.put("tableName", tableInfoBO.getTableName());
        demoMap.put("tableComment", tableInfoBO.getTableComment());
        demoMap.put("tableBigCamel", tableInfoBO.getTableBigCamel());
        demoMap.put("tableSmallCamel", tableInfoBO.getTableSmallCamel());
        //添加主键名称
        demoMap.put("tabPrikey",tableInfoBO.getTabPrikey());
        demoMap.put("tabPrikeySmallCamel",tableInfoBO.getTabPrikeySmallCamel());
        demoMap.put("tabPrikeyBigCamel",tableInfoBO.getTabPrikeyBigCamel());
        //添加主键类型
        demoMap.put("tabPrikeyType", tableInfoBO.getTabPrikeyType());
        demoMap.put("tabPrikeyTypeFullName", tableInfoBO.getTabPrikeyTypeFullName());


        //主键注释
        demoMap.put("tabPrikeyComment",tableInfoBO.getTabPrikeyComment());
        //添加表列名 列别名 表注释 字段属性
        demoMap.put("columList",tableInfoBO.getColumList());
        //添加#
        demoMap.put("u0023","#");
        //添加时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        demoMap.put("now",currentDateTime);
        demoMap.put("bean", Constants.PACKAGE_BEAN);
        demoMap.put("dao", Constants.PACKAGE_DAO);


        // Bean
        createBean(demoMap, templatesPath, filePath_bean);
        // Dao
        createIDao(demoMap, templatesPath, filePath_dao);
        // Service
        createIService(demoMap, templatesPath, filePath_service);
        // ServiceImpl
        createServiceImpl(demoMap, templatesPath, filePath_service_impl);
        // Controller
        createController(demoMap, templatesPath, filePath_controller);
        // TestDao
        createTestDao(demoMap, templatesPath, filePath_test_dao);
        // TestService
        createTestService(demoMap, templatesPath, filePath_test_service);
        // Mapping
        createMapping(demoMap, templatesPath, filePath_mapping,tableInfoBO.getTabPrikeyIsautoIncrement());
        //page_modules
        createPageModule(demoMap, templatesPath, filePath_page);
        //page_controller
        createPageController(demoMap, templatesPath, filePath_page_controler);
        //page_service
        createPageService(demoMap, templatesPath, filePath_page_service);
        //page_template
        createPageTemplate(demoMap, templatesPath, filePath_page_template);
    }

    /**
     *
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createBean(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath + map.get("tableBigCamel") + ".java";
        handleCreatorCode(fileName, templatesPath + "bean/", "bean.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createIDao(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath + map.get("tableBigCamel") + "Mapper.java";
        handleCreatorCode(fileName, templatesPath + "dao/", "dao.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createIService(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath + map.get("tableBigCamel") + "Service.java";
        handleCreatorCode(fileName, templatesPath + "service/", "service.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createServiceImpl(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath + map.get("tableBigCamel") + "ServiceImpl.java";
        handleCreatorCode(fileName, templatesPath + "service/", "serviceImpl.flv", map);
    }

    /**
     *
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createController(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath + map.get("tableBigCamel") + "Controller.java";
        handleCreatorCode(fileName, templatesPath + "controller/", "controller.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createTestDao(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath  + "Test" + map.get("tableBigCamel") + "Dao.java";
        handleCreatorCode(fileName, templatesPath + "test/", "testDal.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createTestService(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath  + "Test" + map.get("tableBigCamel") + "Service.java";
        handleCreatorCode(fileName, templatesPath + "test/", "testService.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createMapping(Map<String, Object> map, String templatesPath, String filePath,boolean isautoIncrement) {
        String fileName = filePath + map.get("tableSmallCamel") + "Mapper.xml";
        if (isautoIncrement) {
            handleCreatorCode(fileName, templatesPath + "mapping/", "incrementMappingDao.flv", map);
        } else {
            handleCreatorCode(fileName, templatesPath + "mapping/", "mappingDao.flv", map);
        }
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createPageModule(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath + map.get("tableSmallCamel") + "-module.js";
            handleCreatorCode(fileName, templatesPath + "page/", "basePageModule.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createPageController(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath + map.get("tableSmallCamel") + "-ctrl.js";
            handleCreatorCode(fileName, templatesPath + "page/", "basePageController.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createPageService(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath + map.get("tableSmallCamel") + "-service.js";
            handleCreatorCode(fileName, templatesPath + "page/", "basePageModule.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createPageTemplate(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath + map.get("tableSmallCamel") + ".html";
        handleCreatorCode(fileName, templatesPath + "page/", "basePageTemplate.flv", map);
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
