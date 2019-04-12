package com.rainsunset.codegenerate.bean;

import com.rainsunset.codegenerate.common.util.StringUtil;

import java.io.File;

/**
 * @ClassName FilePathConfigReqDTO
 * @Description: 文件路径配置
 * @Author: 李刚伟
 * @Company rainsunset
 * @CreateDate: 2019/4/11 10:36
 */
public class FilePathConfigReqDTO {

	/**
	 * class 基础路径 传入时以/ 分割 ,开头没有路径分隔符
	 */
	private String baseClassPath;

	/**
	 * 模块儿名称
	 */
	private String moduleName;

	/**
	 * 文件路径
	 */
	private String outFilePath;

	/**
	 * 是否生成Model
	 */
	private boolean generateModel;

	/**
	 * 是否生成Dao
	 */
	private boolean generateDao;

	/**
	 * 是否生成service
	 */
	private boolean generateService;

	/**
	 * 是否生成controller
	 */
	private boolean generateController;

	/**
	 * 是否生成测试类
	 */
	private boolean generateTest;

	/**
	 * 是否生成页面
	 */
	private boolean generatePage;

	public String getBaseClassPath() {
		// 将packagePath 处理成标准package形式com.rainsunset.***
		String baseClassFilePath = baseClassPath.replace("\\\\", ".")
				.replace("\\", ".")
				.replace("/", ".");
		// 包路径前面的第一个字符为路径分割符时要截掉
		if (baseClassFilePath.startsWith(".")) {
			baseClassFilePath.substring(1);
		}
		return baseClassFilePath;
	}

	public String getBaseClassFilePath() {
		// 将packagePath也处理成文件路径行形式com/rainsunset/***
		String baseClassFilePath = baseClassPath.replace("\\\\", File.separator)
				.replace("\\", File.separator)
				.replace("/", File.separator)
				.replace(".", File.separator);
		// 包路径前面的第一个字符为路径分割符时要截掉
		if (baseClassFilePath.startsWith(File.separator)) {
			baseClassFilePath.substring(1);
		}
		return StringUtil.formater2filePathNoStartWithFileSeparator(baseClassPath);
	}

	public void setBaseClassPath(String baseClassPath) {
		this.baseClassPath = baseClassPath;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getOutFilePath() {
		// 对文件路径规范
		return outFilePath.replace("\\\\", File.separator)
				.replace("\\", File.separator)
				.replace("/", File.separator);
	}

	public void setOutFilePath(String outFilePath) {
		this.outFilePath = outFilePath;
	}

	public boolean isGenerateModel() {
		return generateModel;
	}

	public void setGenerateModel(boolean generateModel) {
		this.generateModel = generateModel;
	}

	public boolean isGenerateDao() {
		return generateDao;
	}

	public void setGenerateDao(boolean generateDao) {
		this.generateDao = generateDao;
	}

	public boolean isGenerateService() {
		return generateService;
	}

	public void setGenerateService(boolean generateService) {
		this.generateService = generateService;
	}

	public boolean isGenerateController() {
		return generateController;
	}

	public void setGenerateController(boolean generateController) {
		this.generateController = generateController;
	}

	public boolean isGenerateTest() {
		return generateTest;
	}

	public void setGenerateTest(boolean generateTest) {
		this.generateTest = generateTest;
	}

	public boolean isGeneratePage() {
		return generatePage;
	}

	public void setGeneratePage(boolean generatePage) {
		this.generatePage = generatePage;
	}

	@Override
	public String toString() {
		return "FilePathConfigReqDTO{" +
				"baseClassPath='" + baseClassPath + '\'' +
				", moduleName='" + moduleName + '\'' +
				", outFilePath='" + outFilePath + '\'' +
				", generateModel=" + generateModel +
				", generateDao=" + generateDao +
				", generateService=" + generateService +
				", generateController=" + generateController +
				", generateTest=" + generateTest +
				", generatePage=" + generatePage +
				'}';
	}

}
