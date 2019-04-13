package com.rainsunset.codegenerate.bean;

import com.rainsunset.codegenerate.common.util.DBUtil;
import com.rainsunset.codegenerate.common.util.StringUtil;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 数据库表信息
 * @Author: ligw
 * @Company rainsunset
 * @CreateDate: 19 -4-13 上午12:33
 * @Version : 1.0
 * @ClassName TableInfoBO
 */
public class TableInfoBO {

	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * 表名小驼峰
	 */
	private String tableSmallCamel;

	/**
	 * 表名大驼峰
	 */
	private String tableBigCamel;

	/**
	 * 表字段描述
	 */
	private String tableComment;

	/**
	 * 主键名称
	 */
	private String tabPrikey = "";

	/**
	 * 主键小驼峰命名
	 */
	private String tabPrikeySmallCamel = "";

	/**
	 * 主键大驼峰命名
	 */
	private String tabPrikeyBigCamel= "";

	/**
	 * 主键类型
	 */
	private String tabPrikeyType = "";

	/**
	 * 主键类型全类名
	 */
	private String tabPrikeyTypeFullName = "";

	/**
	 * 主键是否自增
	 */
	private Boolean tabPrikeyIsautoIncrement = false;
	/**
	 * 主键描述
	 */
	private String tabPrikeyComment = "";

	/**
	 * 除主键外其他列名及列名别名 注释及表字段别名字段类型
	 */
	private List<TableFullColumnsBO> columList = new ArrayList<TableFullColumnsBO>();

	/**
	 * 对表信息进行初始化
	 *
	 * @param tableName  the tableName name
	 * @param tabComment the tab comment
	 * @param dbs        the dbs
	 */
	public TableInfoBO (String tableName, String tabComment,DataBaseSettingBO dbs) {
		this.tableName = tableName;
		this.tableBigCamel = StringUtil.formaterTabName2bigCamel(tableName);
		this.tableSmallCamel = StringUtil.formaterTabName2smallCamel(tableName);
		this.tableComment = tabComment;
		List<TableFullColumnsBO> columList = new ArrayList<TableFullColumnsBO>();
		// 查询数据库列名及属性
		List<Map<String, Object>> tablColumnList = DBUtil.queryTable(dbs, DataBaseSettingBO.getTablColumnSql(dbs.getDbType(), tableName));
		for (Map<String, Object> tabColumnMap : tablColumnList) {
			//表列名
			String column = (String) tabColumnMap.get("Field");
			//表列别名
			String columnSmallCamel = StringUtil.formaterTabName2smallCamel(column);
			String columnBigCamel = StringUtil.formaterTabName2bigCamel(column);
			//表列注释
			String columnComment =  (String) tabColumnMap.get("Comment");
			//表列属性
			String columnType = columnTypeTurn((String) tabColumnMap.get("Type"));
			// 主键属性
			if ((null != tabColumnMap.get("Key")) && ("PRI".equals(tabColumnMap.get("Key")))) {
				this.tabPrikey = column;
				this.tabPrikeyBigCamel = columnBigCamel;
				this.tabPrikeySmallCamel = columnSmallCamel;
				this.tabPrikeyType = columnType;
				Map<String,String> keyTypeMap = tabPrikeyTypeTurn((String) tabColumnMap.get("Type"));
				this.tabPrikeyTypeFullName = keyTypeMap.get("keyTypeFullName");
				this.tabPrikeyType = keyTypeMap.get("keyType");
				if (StringUtils.isEmpty(columnComment)) {
					columnComment = StringUtil.conlitionStr(tableName , "表主键");
				}
				this.tabPrikeyComment = columnComment;
				if ("auto_increment".equals((String) tabColumnMap.get("Extra"))) {
					this.tabPrikeyIsautoIncrement = true;
				} else {
					this.tabPrikeyIsautoIncrement = false;
				}
			} else {
				//处理注释字段 以及字段属性
				if (StringUtils.isEmpty(columnComment)) {
					columnComment = columnSmallCamel;
				}
				TableFullColumnsBO tableFullColumnsBO = new TableFullColumnsBO(column, columnSmallCamel, columnBigCamel, columnComment, columnType);
				columList.add(tableFullColumnsBO);
			}
		}
		this.columList = columList;
	}

	public String getTabPrikey() {
		return tabPrikey;
	}

	public void setTabPrikey(String tabPrikey) {
		this.tabPrikey = tabPrikey;
	}

	public String getTabPrikeySmallCamel() {
		return tabPrikeySmallCamel;
	}

	public void setTabPrikeySmallCamel(String tabPrikeySmallCamel) {
		this.tabPrikeySmallCamel = tabPrikeySmallCamel;
	}

	public String getTabPrikeyBigCamel() {
		return tabPrikeyBigCamel;
	}

	public void setTabPrikeyBigCamel(String tabPrikeyBigCamel) {
		this.tabPrikeyBigCamel = tabPrikeyBigCamel;
	}

	public String getTabPrikeyType() {
		return tabPrikeyType;
	}

	public void setTabPrikeyType(String tabPrikeyType) {
		this.tabPrikeyType = tabPrikeyType;
	}

	public Boolean getTabPrikeyIsautoIncrement() {
		return tabPrikeyIsautoIncrement;
	}

	public void setTabPrikeyIsautoIncrement(Boolean tabPrikeyIsautoIncrement) {
		this.tabPrikeyIsautoIncrement = tabPrikeyIsautoIncrement;
	}

	public String getTabPrikeyComment() {
		return tabPrikeyComment;
	}

	public void setTabPrikeyComment(String tabPrikeyComment) {
		this.tabPrikeyComment = tabPrikeyComment;
	}

	public List<TableFullColumnsBO> getColumList() {
		return columList;
	}

	public void setColumList(List<TableFullColumnsBO> columList) {
		this.columList = columList;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableSmallCamel() {
		return tableSmallCamel;
	}

	public void setTableSmallCamel(String tableSmallCamel) {
		this.tableSmallCamel = tableSmallCamel;
	}

	public String getTableBigCamel() {
		return tableBigCamel;
	}

	public void setTableBigCamel(String tableBigCamel) {
		this.tableBigCamel = tableBigCamel;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public String getTabPrikeyTypeFullName() {
		return tabPrikeyTypeFullName;
	}

	public void setTabPrikeyTypeFullName(String tabPrikeyTypeFullName) {
		this.tabPrikeyTypeFullName = tabPrikeyTypeFullName;
	}

	/**
	 * 主键类型转化 MySql类型转Java类型
	 * @param keyType
	 * @return
	 */
	private static Map<String,String> tabPrikeyTypeTurn(String keyType){
		keyType = keyType.toLowerCase();
		String keyTypeFullName = "java.lang.String";
		if (keyType.indexOf("bigint") != -1){
			keyType = "Long";
			keyTypeFullName = "java.lang.Long";
		} else if (keyType.indexOf("int") != -1){
			keyType = "Integer";
			keyTypeFullName = "java.lang.Integer";
		} else if (keyType.indexOf("double") != -1) {
			keyType = "Double";
			keyTypeFullName = "java.lang.Double";
		} else if (keyType.indexOf("float") != -1) {
			keyType = "Float";
			keyTypeFullName = "java.lang.Float";
		} else if (keyType.indexOf("decimal") != -1) {
			keyType = "BigDecimal";
			keyTypeFullName = "java.math.BigDecimal";
		} else {
			keyType = "String";
		}
		Map<String,String> keyTypeMap = new HashMap<String,String>();
		keyTypeMap.put("keyType",keyType);
		keyTypeMap.put("keyTypeFullName",keyTypeFullName);
		return keyTypeMap;
	}

	/**
	 * 非主键类型转化 MySql类型转Java类型
	 * @param columnType
	 * @return
	 */
	private static String columnTypeTurn(String columnType){
		columnType = columnType.toLowerCase();
		String columJavaType = "String";
		if (columnType.indexOf("bigint") != -1){
			columJavaType = "Long";
		} else if (columnType.indexOf("int") != -1){
			columJavaType = "Integer";
		} else if (columnType.indexOf("double") != -1) {
			columJavaType = "Double";
		} else if (columnType.indexOf("float") != -1) {
			columJavaType = "Float";
		} else if (columnType.indexOf("decimal") != -1) {
			columJavaType = "BigDecimal";
		} else if (columnType.indexOf("date") != -1){
			columJavaType = "Date";
		}
		return columJavaType;
	}
}
