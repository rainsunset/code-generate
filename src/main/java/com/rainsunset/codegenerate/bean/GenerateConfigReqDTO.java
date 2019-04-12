package com.rainsunset.codegenerate.bean;

import java.util.List;

/**
 * @ClassName GenerateConfigReqDTO
 * @Description: 代码生成路径配置
 * @Author: 李刚伟
 * @Company rainsunset
 * @CreateDate: 2019/4/10 16:50
 */
public class GenerateConfigReqDTO {

	/**
	 * 数据库连接配置
	 */
	private DataBaseConfigReqDTO dataBaseConfigReqDTO;

	/**
	 * 文件路径配置
	 */
	private FilePathConfigReqDTO filePathConfigReqDTO;

	/**
	 * 模板代码
	 */
	private String templatesType;

	/**
	 * 要生成代码的表名
	 */
	private List<String> tabNameList;

	public DataBaseConfigReqDTO getDataBaseConfigReqDTO() {
		return dataBaseConfigReqDTO;
	}

	public void setDataBaseConfigReqDTO(DataBaseConfigReqDTO dataBaseConfigReqDTO) {
		this.dataBaseConfigReqDTO = dataBaseConfigReqDTO;
	}

	public FilePathConfigReqDTO getFilePathConfigReqDTO() {
		return filePathConfigReqDTO;
	}

	public void setFilePathConfigReqDTO(FilePathConfigReqDTO filePathConfigReqDTO) {
		this.filePathConfigReqDTO = filePathConfigReqDTO;
	}

	public String getTemplatesType() {
		return templatesType;
	}

	public void setTemplatesType(String templatesType) {
		this.templatesType = templatesType;
	}

	public List<String> getTabNameList() {
		return tabNameList;
	}

	public void setTabNameList(List<String> tabNameList) {
		this.tabNameList = tabNameList;
	}

	@Override
	public String toString() {
		return "GenerateConfigReqDTO{" +
				"dataBaseConfigReqDTO=" + dataBaseConfigReqDTO +
				", filePathConfigReqDTO=" + filePathConfigReqDTO +
				", templatesType='" + templatesType + '\'' +
				", tabNameList=" + tabNameList +
				'}';
	}
}
