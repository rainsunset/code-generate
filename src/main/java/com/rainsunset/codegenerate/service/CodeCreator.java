package com.rainsunset.codegenerate.service;

import com.rainsunset.codegenerate.bean.DataBaseSettingBO;
import com.rainsunset.codegenerate.bean.FilePathBO;
import com.rainsunset.codegenerate.bean.FilePathConfigReqDTO;
import com.rainsunset.codegenerate.common.constants.Constants;
import com.rainsunset.codegenerate.common.util.DBUtil;
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
     * Creator code.
     *
     * @param tableName            DB中的表名
     * @param tabNameComment       表描述
     * @param templatesAbsPath     模板文件绝对路径
     * @param filePathBO           文件包
     * @param filePathConfigReqDTO the file path config req dto
     * @param dbs                  the dbs
     * @author : ligangwei / 2019-04-12
     */
    public static void creatorCode(String tableName, String tabNameComment, String templatesAbsPath, FilePathBO filePathBO, FilePathConfigReqDTO filePathConfigReqDTO, DataBaseSettingBO dbs) {
        String outFolder2 = outFolder + Constants.SLASH;
        String classPath = (baseClassPath + Constants.DOT + moduleName).replaceAll(Constants.SLASH, Constants.DOT);
        // main
        String filePath_bean = null == sop ? outFolder2 : sop.getFilePathModel();
        String filePath_dao = null == sop ? outFolder2 : sop.getFilePathDao();
        String filePath_service = null == sop ? outFolder2 : sop.getFilePathService();
        String filePath_service_impl = null == sop ? outFolder2 : sop.getFilePathServiceImpl();
        String filePath_controller = null == sop ? outFolder2 : sop.getFilePathController();
        String filePath_test_dao = null == sop ? outFolder2 : sop.getFilePathTestDao();
        String filePath_test_service = null == sop ? outFolder2 : sop.getFilePathTestService();
        String filePath_mapping = null == sop ? outFolder2 : sop.getFilePathMapper();
        String filePath_page = null == sop ? outFolder2 : sop.getFilePathPageView();
        String filePath_page_controler = null == sop ? outFolder2 : sop.getFilePathPageControler();
        String filePath_page_service = null == sop ? outFolder2 : sop.getFilePathPageService();
        String filePath_page_template = null == sop ? outFolder2 : sop.getFilePathPageTemplate();

        // 查询数据库列名及属性
        List<Map<String, Object>> tablColumnList = DBUtil.queryTable(dbs, DataBaseSettingBO.getTablColumnSql(dbs.getDbType(), tableName));
        // 主键名称
        String tabPrikeyName = "";
        String tabPrikeyAlias = "";
        String tabPrikeyAliasFirstUpper = "";
        // 主键类型
        String tabPrikeyType = "";
        // 主键是否自增
        Boolean isautoIncrement = false;
        //主键注释
        String tabPrikeyComment = "";
        // 除主键外其他列名及列名别名 注释及表字段别名字段类型
        List<Map<String,String>> columList = new ArrayList<Map<String,String>>();
        String firatUpperName = firstUpperformate(tableName);
        String firstlowerName = firstLowerformate(tableName);
        tabNameComment = "".equals(tabNameComment) ? firstlowerName : tabNameComment;
        for (Map<String, Object> tabColumnMap : tablColumnList) {
            //表列名
            String columnField = (String) tabColumnMap.get("Field");
            //表列别名
            String columnAlias = firstLowerformate(columnField);
            String columnAliasFirstUpper = firstUpperformate(columnField);
            //表列注释
            String columnComment =  (String) tabColumnMap.get("Comment");
            //表列属性
            String columnType = columTypeTurn((String) tabColumnMap.get("Type"));
            if ((null != tabColumnMap.get("Key")) && ("PRI".equals(tabColumnMap.get("Key")))) {
                tabPrikeyName = columnField;
                tabPrikeyAlias = columnAlias;
                tabPrikeyAliasFirstUpper = columnAliasFirstUpper;
                tabPrikeyType = (String) tabColumnMap.get("Type");
                if ((null == columnComment) || ("".equals(columnComment))) {
                    tabPrikeyComment = tabNameComment + "主键";
                }
                if ("auto_increment".equals((String) tabColumnMap.get("Extra"))) {
                    isautoIncrement = true;
                }
            } else {
                //处理注释字段 以及字段属性
                if ((null == columnComment) || ("".equals(columnComment))) {
                    columnComment = columnAlias;
                }
                Map<String,String> columMap = new HashMap<String,String>();
                columMap.put("column",columnField);
                columMap.put("columnAlias",columnAlias);
                columMap.put("columnAliasFirstUpper",columnAliasFirstUpper);
                columMap.put("columnComment",columnComment);
                columMap.put("columType",columnType);
                columList.add(columMap);
            }
        }
        Map<String, Object> demoMap = new HashMap<String, Object>();
        demoMap.put("tableName", tableName);
        demoMap.put("tabNameComment", tabNameComment);
        demoMap.put("firatUpperName", firatUpperName);
        demoMap.put("firstlowerName", firstlowerName);
        demoMap.put("moduleName", moduleName);
        demoMap.put("classPath", classPath);
        //添加主键名称
        demoMap.put("tabPrikeyName",tabPrikeyName);
        demoMap.put("tabPrikeyAlias",tabPrikeyAlias);
        demoMap.put("tabPrikeyAliasFirstUpper",tabPrikeyAliasFirstUpper);
        //添加主键类型 keyType keypackage
        demoMap.putAll(tabPrikeyTypeTurn(tabPrikeyType));
        //主键注释
        demoMap.put("tabPrikeyComment",tabPrikeyComment);
        //添加表列名 列别名 表注释 字段属性
        demoMap.put("columList",columList);
        //添加#
        demoMap.put("u0023","#");
        //添加时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        demoMap.put("now",currentDateTime);
        demoMap.put("bean", Constants.PACKAGE_BEAN);
        demoMap.put("dao", Constants.PACKAGE_DAO);


        // Bean
        createBean(demoMap, templatesAbsPath, filePath_bean);
        // Dao
        createIDao(demoMap, templatesAbsPath, filePath_dao);
        // Service
        createIService(demoMap, templatesAbsPath, filePath_service);
        // ServiceImpl
        createServiceImpl(demoMap, templatesAbsPath, filePath_service_impl);
        // Controller
        createController(demoMap, templatesAbsPath, filePath_controller);
        // TestDao
        createTestDao(demoMap, templatesAbsPath, filePath_test_dao);
        // TestService
        createTestService(demoMap, templatesAbsPath, filePath_test_service);
        // Mapping
        createMapping(demoMap, templatesAbsPath, filePath_mapping,isautoIncrement);
        //page_modules
        createPageModule(demoMap, templatesAbsPath, filePath_page);
        //page_controller
        createPageController(demoMap, templatesAbsPath, filePath_page_controler);
        //page_service
        createPageService(demoMap, templatesAbsPath, filePath_page_service);
        //page_template
        createPageTemplate(demoMap, templatesAbsPath, filePath_page_template);
    }

    /**
     *
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createBean(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath + map.get("firatUpperName") + ".java";
        handleCreatorCode(fileName, templatesPath + "bean/", "bean.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createIDao(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath + map.get("firatUpperName") + "Mapper.java";
        handleCreatorCode(fileName, templatesPath + "dao/", "dao.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createIService(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath + map.get("firatUpperName") + "Service.java";
        handleCreatorCode(fileName, templatesPath + "service/", "service.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createServiceImpl(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath + map.get("firatUpperName") + "ServiceImpl.java";
        handleCreatorCode(fileName, templatesPath + "service/", "serviceImpl.flv", map);
    }

    /**
     *
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createController(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath + map.get("firatUpperName") + "Controller.java";
        handleCreatorCode(fileName, templatesPath + "controller/", "controller.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createTestDao(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath  + "Test" + map.get("firatUpperName") + "Dao.java";
        handleCreatorCode(fileName, templatesPath + "test/", "testDao.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createTestService(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath  + "Test" + map.get("firatUpperName") + "Service.java";
        handleCreatorCode(fileName, templatesPath + "test/", "testService.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createMapping(Map<String, Object> map, String templatesPath, String filePath,boolean isautoIncrement) {
        String fileName = filePath + map.get("firstlowerName") + "Mapper.xml";
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
        String fileName = filePath + map.get("firstlowerName") + "-module.js";
            handleCreatorCode(fileName, templatesPath + "page/", "basePageModule.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createPageController(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath + map.get("firstlowerName") + "-ctrl.js";
            handleCreatorCode(fileName, templatesPath + "page/", "basePageController.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createPageService(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath + map.get("firstlowerName") + "-service.js";
            handleCreatorCode(fileName, templatesPath + "page/", "basePageModule.flv", map);
    }

    /**
     * @param map
     * @param templatesPath
     * @param filePath
     */
    private static void createPageTemplate(Map<String, Object> map, String templatesPath, String filePath) {
        String fileName = filePath + map.get("firstlowerName") + ".html";
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

    /**
     * 格式化表名/数据名
     *
     * @param tname
     * @return
     */
    private static String firstUpperformate(String tname) {
        Map<String, String> map = new HashMap<String, String>();
        String UNDERLINE = "_";
        String formateName = null;
        // 若第二个字符为下划线则支取第第二个字符之后的部分
        if (tname.substring(1, 2).equals(UNDERLINE)) {
            formateName = tname.substring(2);
        }
        else {
            formateName = tname;
        }
        logger.debug(" ######截取后的表名(若第二个字符为下划线则支取第第二个字符之后的部分)： " + formateName);
        // 按照下划线分割表名，每部分首字母大写后拼接
        String[] _array = formateName.split(UNDERLINE);
        StringBuilder sb = new StringBuilder();
        for (String str : _array) {
            String firstCodeUpperStr = firstCodeUpper(str);
            sb.append(firstCodeUpperStr);
        }
        String firatUpperStr = sb.toString();
        logger.debug(" @@@@@@经过处理的名称(去掉所有_且首字母大写)： " + firatUpperStr);
        return firatUpperStr;
    }

    private static String firstLowerformate(String tname) {
        String firatUpperStr = firstUpperformate(tname);
        // 首字母小写的Name
        String beginStr = firatUpperStr.substring(0, 1);
        String endStr = firatUpperStr.substring(1);
        String firstlowerStr = beginStr.toLowerCase() + endStr;
        logger.debug(" !!!!!!处理后首字母小写的名称： " + firstlowerStr);
        return firstlowerStr;
    }
    /**
     * 首字母大写
     *
     * @param sbStr
     * @return
     */
    public static String firstCodeUpper(String sbStr) {
        String beginStr = sbStr.substring(0, 1);
        String endStr = sbStr.substring(1);
        return (beginStr.toUpperCase() + endStr);
    }

    //主键类型转化 MySql类型转Java类型
    public static Map<String,String> tabPrikeyTypeTurn(String tabPrikeyType){
        String keyType = "String";
        String keypackage = "java.lang.String";
        if (tabPrikeyType.toLowerCase().indexOf("bigint") != -1){
            keyType = "Long";
            keypackage = "java.lang.Long";
        } else if (tabPrikeyType.toLowerCase().indexOf("int") != -1){
            keyType = "Integer";
            keypackage = "java.lang.Integer";
        } else if (tabPrikeyType.toLowerCase().indexOf("double") != -1) {
            keyType = "Double";
            keypackage = "java.lang.Double";
        } else if (tabPrikeyType.toLowerCase().indexOf("float") != -1) {
            keyType = "Float";
            keypackage = "java.lang.Float";
        } else if (tabPrikeyType.toLowerCase().indexOf("decimal") != -1) {
            keyType = "BigDecimal";
            keypackage = "java.math.BigDecimal";
        }
        Map<String,String> keyTypeMap = new HashMap<String,String>();
        keyTypeMap.put("keyType",keyType);
        keyTypeMap.put("keypackage",keypackage);
        return keyTypeMap;
    }

    public static String columTypeTurn(String columType){
        columType = columType.toLowerCase();
        String columJavaType = "String";
        if (columType.indexOf("bigint") != -1){
            columJavaType = "Long";
        } else if (columType.indexOf("int") != -1){
            columJavaType = "Integer";
        } else if (columType.indexOf("double") != -1) {
            columJavaType = "Double";
        } else if (columType.indexOf("float") != -1) {
            columJavaType = "Float";
        } else if (columType.indexOf("decimal") != -1) {
            columJavaType = "BigDecimal";
        } else if (columType.indexOf("date") != -1){
            columJavaType = "Date";
        }
        return columJavaType;
    }
}
