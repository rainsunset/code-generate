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

}
