package com.rainsunset.codegenerate.common.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Constants
 * @Description: 常量配置
 * @Author: 李刚伟
 * @Company rainsunset
 * @CreateDate: 2019/4/10 15:55
 */
public class Constants {

	// region 构建能解析的数据库常量
	public static final String DB_TYPT_MYSQL = "MySql";
	public static final String DB_TYPT_SQLSERVER = "SqlServer";
	public static final String DB_TYPT_ORACLE = "Oracle";

	static List<String> initProvideDbType(){
		List<String> provideDbType = new ArrayList<>();
		provideDbType.add(DB_TYPT_MYSQL);
		provideDbType.add(DB_TYPT_SQLSERVER);
		provideDbType.add(DB_TYPT_ORACLE);
		return provideDbType;
	}

	public static final List<String> PROVIDE_DB_TYPE = initProvideDbType();
	// endregion

	// region 业务层命名配置
	/** . */
	static public final String DOT = ".";

	// region 不同层文件后缀名
	/**
	 * model层文件后缀
	 */
	static public final String MODEL_FILE_SUFFIX = ".java";

	/**
	 * mapper层文件后缀
	 */
	static public final String MAPPER_FILE_SUFFIX = "Mapper.xml";

	/**
	 * dao层文件后缀
	 */
	static public final String DAO_FILE_SUFFIX = "Mapper.java";

	/**
	 * service层文件后缀
	 */
	static public final String SERVICE_FILE_SUFFIX = "Service.java";

	/**
	 * ServiceImpl层文件后缀
	 */
	static public final String SERVICEIMPL_FILE_SUFFIX = "ServiceImpl.java";

	/**
	 * manager层文件后缀
	 */
	static public final String MANAGER_FILE_SUFFIX = "Manager.java";

	/**
	 * controller层文件后缀
	 */
	static public final String CONTROLLER_FILE_SUFFIX = "Controller.java";

	/**
	 * testDao层文件后缀
	 */
	static public final String TEST_DAO_FILE_SUFFIX = "DaoTest.java";

	/**
	 * testService层文件后缀
	 */
	static public final String TEST_SERVICE_FILE_SUFFIX = "ServiceTest.java";

	/**
	 * pageController层文件后缀
	 */
	static public final String PAGE_CONTROLLER_FILE_SUFFIX = "-ctrl.js";

	/**
	 * pageService层文件后缀
	 */
	static public final String PAGE_SERVICE_FILE_SUFFIX = "-service.js";

	/**
	 * pageTemplate层文件后缀
	 */
	static public final String PAGE_TEMPLATE_FILE_SUFFIX = "-tmp.js";

	/**
	 * PageView层文件后缀
	 */
	static public final String PAGE_VIEW_FILE_SUFFIX = ".html";


	/** / */
	static public final String SLASH = "/";

	/** bean */
	static public final String PACKAGE_BEAN = "bean";

	/** dao */
	static public final String PACKAGE_DAO = "dao";

	/** test */
	static public final String PACKAGE_TEST = "test";

	/**
	 * page
	 */
	static public final String PACKAGE_PAGE = "page";

	// endregion

	/**
	 * 类上注释信息
	 */
	static public final String CODE_AUTHOR = "ligangwei";
	static public final String CODE_COMPANY = "rainsunset";
	static public final String CODE_VERSION = "1.0";



}
