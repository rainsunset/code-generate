package com.rainsunset.codegenerate.common.util;

import org.springframework.util.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
			for (String str : str2) {
				sb.append(str);
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
		if (StringUtils.isEmpty(path)) {
			return "";
		}
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
		if (StringUtils.isEmpty(path)) {
			return "";
		}
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
		if (StringUtils.isEmpty(path)) {
			return "";
		}
		String filePath = formater2filePath(path);
		// 包路径前面的第一个字符为路径分割符时要截掉
		if (filePath.startsWith(File.separator)) {
			filePath.substring(1);
		}
		return filePath;
	}

	/**
	 * 将用特殊字符(包括空格)分割开的单词合并成首字母大写的单词（大驼峰）
	 *
	 * @param path the path
	 * @return the string
	 * @author : ligangwei / 2019-04-12
	 */
	public static String formater2bigCamel(String path) {
		if (StringUtils.isEmpty(path)) {
			return "";
		}
		path = path.replace("\\\\", ",")
				.replace("\\",",")
				.replace("/", ",")
				.replace(".",",")
				.replace("_",",")
				.replace("-",",")
				.replace(" ",",");
		String[] pathArray = path.split(",");
		StringBuilder sb = new StringBuilder();
		for(String str : pathArray) {
			if (StringUtils.isEmpty(str)) {
				continue;
			}
			sb.append(wordFirst2upper(str));
		}
		return sb.toString();
	}

	/**
	 * 将用特殊字符(包括空格)分割开的单词合并成首字母大写的单词（大驼峰）
	 *
	 * @param path the path
	 * @return the string
	 * @author : ligangwei / 2019-04-12
	 */
	public static String formater2smallCamel(String path) {
		if (StringUtils.isEmpty(path)) {
			return "";
		}
		String bigCamel = formater2bigCamel(path);
		return wordFirst2lower(bigCamel);
	}

	/**
	 * 格式化表名/数据名 到大驼峰形式
	 *
	 * @param tname
	 * @return
	 */
	public static String formaterTabName2bigCamel(String tname) {
		if (StringUtils.isEmpty(tname)) {
			return "";
		}
		String UNDERLINE = "_";
		String formateName = null;
		// 横杠也算分隔符
		tname = tname.replace("-", UNDERLINE);
		// 若第二个字符为下划线则支取第第二个字符之后的部分
		if (tname.substring(1, 2).equals(UNDERLINE)) {
			formateName = tname.substring(2);
		}
		else {
			formateName = tname;
		}
		// 检查表命名方式是否都是大写 以前三个字母为准
		boolean isAllUpper = true;
		int checkLength = formateName.length() > 3 ? 3 : formateName.length();
		String checkStr = formateName.substring(0, checkLength);
		for (int i = 0; i < checkLength; i++) {
			String currentCheckStr = checkStr.substring(i, i + 1);
			boolean currentCheckRes = currentCheckStr.equals(currentCheckStr.toUpperCase());
			isAllUpper = (isAllUpper && currentCheckRes);
		}
		// 如果全是大写字母则转换成全小写字母
		if (isAllUpper) {
			formateName = formateName.toLowerCase();
		}
		// 按照下划线和横杠分割表名，每部分首字母大写后拼接
		String[] formateNameArray = formateName.split(UNDERLINE);
		StringBuilder sb = new StringBuilder();
		for (String str : formateNameArray) {
			if (StringUtils.isEmpty(str)) {
				continue;
			}
			sb.append(wordFirst2upper(str));
		}
		return sb.toString();
	}

	/**
	 * 格式化表名/数据名 到小驼峰形式
	 *
	 * @param tname the tname
	 * @return the string
	 * @author : ligangwei / 2019-04-13
	 */
	public static String formaterTabName2smallCamel(String tname) {
		if (StringUtils.isEmpty(tname)) {
			return "";
		}
		// 首字母小写的Name
		String tnameSmallCamel = formaterTabName2bigCamel(tname);
		return wordFirst2lower(tnameSmallCamel);
	}

	/**
	 * 单词首字母大写其余不变
	 *
	 * @param word the word
	 * @return the string
	 * @author : ligangwei / 2019-04-12
	 */
	private static String wordFirst2upper(String word) {
		if (StringUtils.hasText(word)) {
//			word = word.toLowerCase();
			StringBuilder sb = new StringBuilder(word.substring(0, 1).toUpperCase());
			sb.append(word.substring(1));
			return sb.toString();
		} else {
			return "";
		}
	}

	/**
	 *  单词第一个字母变小写，其余不变
	 *
	 * @param word the word
	 * @return the string
	 * @author : ligangwei / 2019-04-12
	 */
	private static String wordFirst2lower(String word) {
		if (StringUtils.hasText(word)) {
			StringBuilder sb = new StringBuilder(word.substring(0, 1).toLowerCase());
			sb.append(word.substring(1));
			return sb.toString();
		} else {
			return "";
		}
	}


}
