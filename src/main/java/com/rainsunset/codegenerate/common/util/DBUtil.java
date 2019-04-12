package com.rainsunset.codegenerate.common.util;

import com.rainsunset.codegenerate.bean.DataBaseSettingBO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description: 数据库工具类
 * @Author: ligangwei
 * @Company rainsunset
 * @CreateDate: 2019.04.10
 * @Version : 1.0
 */
public class DBUtil {


	/**
	 * 连接数据库执行语句
	 *
	 * @param dbs the dbs
	 * @param sql the sql
	 * @return the list
	 * @author : ligangwei / 2019-04-10
	 */
	static public List<Map<String,Object>> queryTable(DataBaseSettingBO dbs, String sql) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Class.forName(dbs.getDriver());
			conn = DriverManager.getConnection(dbs.getUrl(), dbs.getUsername(),dbs.getPassword());
			stmt = conn.createStatement();
			res = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = res.getMetaData();
			while (res.next()) {
				Map<String, Object> rowMap = new HashMap<String, Object>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					// 列名
					String colName = rsmd.getColumnLabel(i);
					// 列值
					Object colValue = res.getObject(colName);
					rowMap.put(colName, colValue);
				}
				list.add(rowMap);
			}
			res.close();
			stmt.close();
			conn.close();
			return list;
		} catch (Exception e) {
            e.printStackTrace();
			throw new RuntimeException(" [ # 数据库连接失败 # ] " + e.toString());
		} finally {
			res = null;
			stmt = null;
			conn = null;
		}
	}
}
