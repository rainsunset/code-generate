package com.rainsunset.codegenerate.bean;


/**
 * @Description: 数据库表 每列信息
 * @Author: ligangwei
 * @Company rainsunset
 * @CreateDate: 2019.04.13
 * @Version : 1.0
 */
public class TableFullColumnsBO {

	/**
	 * 字段名
	 */
	private String column;

	/**
	 * 字段名小驼峰
	 */
	private String columnSmallCamel;

	/**
	 * 字段名大驼峰
	 */
	private String columnBigCamel;

	/**
	 * 字段描述
	 */
	private String columnComment;

	/**
	 * 字段类型
	 */
	private String columnType;

	public TableFullColumnsBO(String column, String columnSmallCamel, String columnBigCamel, String columnComment, String columnType) {
		this.column = column;
		this.columnSmallCamel = columnSmallCamel;
		this.columnBigCamel = columnBigCamel;
		this.columnComment = columnComment;
		this.columnType = columnType;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getColumnSmallCamel() {
		return columnSmallCamel;
	}

	public void setColumnSmallCamel(String columnSmallCamel) {
		this.columnSmallCamel = columnSmallCamel;
	}

	public String getColumnBigCamel() {
		return columnBigCamel;
	}

	public void setColumnBigCamel(String columnBigCamel) {
		this.columnBigCamel = columnBigCamel;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
}
