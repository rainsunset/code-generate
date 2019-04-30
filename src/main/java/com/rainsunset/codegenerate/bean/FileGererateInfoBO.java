package com.rainsunset.codegenerate.bean;

import com.rainsunset.codegenerate.common.util.StringUtil;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * @ClassName FileGererateInfoBO
 * @Description: 文件生成基础信息
 * @Author: 李刚伟
 * @Company rainsunset
 * @CreateDate: 2019/4/30 14:16
 */
public class FileGererateInfoBO {

	private String packagePath;

	private String packageFilePath;

	private String fileSuffix;

	private String templatesName;

	public FileGererateInfoBO(String packagePath, String fileSuffix, String templatesName) {
		this.packagePath = packagePath;
		this.fileSuffix = fileSuffix;
		this.templatesName = templatesName;
		this.packageFilePath = StringUtils.isEmpty(packagePath) ? "" :
				StringUtil.conlitionStr(packagePath.replace(".", File.separator));
	}

	public String getPackagePath() {
		return packagePath;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getTemplatesName() {
		return templatesName;
	}

	public void setTemplatesName(String templatesName) {
		this.templatesName = templatesName;
	}

	public String getPackageFilePath() {
		return packageFilePath;
	}

	public String getAbsPackageFilePath(String filePathModule) {
		return StringUtil.conlitionStr(filePathModule,File.separator,packageFilePath);
	}
}
