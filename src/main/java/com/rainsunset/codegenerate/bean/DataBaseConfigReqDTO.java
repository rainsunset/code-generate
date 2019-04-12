package com.rainsunset.codegenerate.bean;

import java.io.Serializable;

/**
 * @ClassName DataBaseConfigReqDTO
 * @Description: 数据库配置信息
 * @Author: 李刚伟
 * @Company rainsunset
 * @CreateDate: 2019/4/10 15:48
 */
public class DataBaseConfigReqDTO{

	private String dbType;

	private String dbName;

	private String address;

	private String port;

	private String username;

	private String password;

	private String url;

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "DataBaseConfigReqDTO{" +
				"dbType='" + dbType + '\'' +
				", dbName='" + dbName + '\'' +
				", address='" + address + '\'' +
				", port='" + port + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", url='" + url + '\'' +
				'}';
	}
}
