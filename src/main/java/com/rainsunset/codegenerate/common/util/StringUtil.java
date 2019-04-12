package com.rainsunset.codegenerate.common.util;

import java.io.File;

/**
 * @ClassName SpecStringUtil
 * @Description: 字符串工具类
 * @Author: 李刚伟
 * @Company rainsunset
 * @CreateDate: 2019/4/11 9:21
 */
public class StringUtil {


	/**
	 * 连接字符串
	 *
	 * @param str1 the str 1
	 * @param str2 the str 2
	 * @return the string
	 * @author : ligangwei / 2019-04-11
	 */
	public static String conlitionStr(String str1, String... str2) {
		StringBuilder sb = new StringBuilder(str1);
		if (str2.length > 0) {
			for (int i = 0; i < str2.length; i++) {
				sb.append(str2[i]);
			}
		}
		return sb.toString();
	}


	/**
	 * 将packagePath 处理成标准package形式com.rainsunset.***
	 *
	 * @param path the path
	 * @return the string
	 * @author : ligangwei / 2019-04-12
	 */
	public static String formater2JavaPackage(String path) {
		// 将packagePath 处理成标准package形式com.rainsunset.***
		String packagePath = path.replace("\\\\", ".")
				.replace("\\", ".")
				.replace("/", ".");
		// 包路径前面的第一个字符为路径分割符时要截掉
		if (packagePath.startsWith(".")) {
			packagePath.substring(1);
		}
		return packagePath;
	}

	/**
	 * 将packagePath也处理成文件路径行形式com/rainsunset/***
	 *
	 * @param path the path
	 * @return the string
	 * @author : ligangwei / 2019-04-12
	 */
	public static String formater2filePath(String path) {
		String filePath = path.replace("\\\\", File.separator)
				.replace("\\", File.separator)
				.replace("/", File.separator)
				.replace(".", File.separator);
		// 包路径前面的第一个字符为路径分割符时要截掉
		if (filePath.startsWith(File.separator)) {
			filePath.substring(1);
		}
		return filePath;
	}

	/**
	 * 将packagePath也处理成文件路径行形式com/rainsunset/***
	 * 并且开头不是文件路径分隔符
	 *
	 * @param path the path
	 * @return the string
	 * @author : ligangwei / 2019-04-12
	 */
	public static String formater2filePathNoStartWithFileSeparator(String path) {
		String filePath = formater2filePath(path);
		// 包路径前面的第一个字符为路径分割符时要截掉
		if (filePath.startsWith(File.separator)) {
			filePath.substring(1);
		}
		return filePath;
	}

}
