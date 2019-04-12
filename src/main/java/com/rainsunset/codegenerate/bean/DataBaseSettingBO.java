package com.rainsunset.codegenerate.bean;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import com.mysql.jdbc.Driver;
import oracle.jdbc.driver.OracleDriver;

/**
 * @Description: 数据库配置
 * @Author: ligangwei
 * @Company CMBI
 * @CreateDate: 2019.04.12
 * @Version : 1.0
 */
public class DataBaseSettingBO {

    private String dbType;

    private String dbName;

    private String address;

    private String port;

    private String username;

    private String password;

    private String url;

    private String driver;

    private String testSql;

    private String showTable;

    private String showTablColumn;

    public DataBaseSettingBO(){
    }

    public DataBaseSettingBO(DataBaseConfigReqDTO dataBaseConfigReqDTO){
        this.dbType = dataBaseConfigReqDTO.getDbType();
        this.dbName = dataBaseConfigReqDTO.getDbName();
        this.address = dataBaseConfigReqDTO.getAddress();
        this.port = dataBaseConfigReqDTO.getPort();
        this.username = dataBaseConfigReqDTO.getUsername();
        this.password = dataBaseConfigReqDTO.getPassword();
        this.url = dataBaseConfigReqDTO.getUrl();
        // 自动装配驱动及查询语句
        this.processDriver();
    }

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

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getTestSql() {
        return testSql;
    }

    public void setTestSql(String testSql) {
        this.testSql = testSql;
    }

    public String getShowTable() {
        return showTable;
    }

    public void setShowTable(String showTable) {
        this.showTable = showTable;
    }

    public String getShowTablColumn() {
        return showTablColumn;
    }

    public void setShowTablColumn(String showTablColumn) {
        this.showTablColumn = showTablColumn;
    }


    /**
     * 依据数据库类型初始化 驱动 连接测试 表查询 表注释查询 字段
     *
     * @author : ligangwei / 2019-04-10
     */
    private void processDriver() {
        if (dbType.equals("MySql")) {
            driver = Driver.class.getName();
            testSql = "select 1";
            showTable = " show tables ";
            showTablColumn = "SHOW TABLE STATUS FROM "+ dbName;
            return;
        }
        if (dbType.equals("Oracle")) {
            driver = OracleDriver.class.getName();
            testSql = "select 1 from dual";
            showTable = "SELECT TABLE_NAME as Tables_in_" + dbName + " FROM user_tab_comments "
                    + "where table_type='TABLE'";
            return;
        }
        if (dbType.equals("MSSqlServer")) {
            driver = SQLServerDriver.class.getName();
            testSql = "select 1";
            showTable = " SELECT Name as Tables_in_" + dbName + " FROM SysObjects Where XType='U' ORDER BY Name";
            showTablColumn = "select sysobjects.name Name , convert(varchar(100), sys.extended_properties.value) Comment " +
                    "from sysobjects " +
                    "left join sys.extended_properties on sysobjects.id=sys.extended_properties.major_id " +
                    "where type= 'U' and sys.extended_properties.minor_id='0' order by name";
            return;
        }
    }

    //查询表字段说明
    public static String getTablColumnSql(String dbType ,String tname) {
        String tablColumnSql = "";
        if (dbType.equals("MySql")) {
            tablColumnSql = "SHOW FULL COLUMNS FROM "+ tname;
        }
        if (dbType.equals("Oracle")) {
            tablColumnSql = "";
        }
        if (dbType.equals("MSSqlServer")) {
            tablColumnSql = "SELECT colu.name 'Field' ,d.name 'Type' ,col.length AS 'Length' ,d.collation_name 'Collation' " +
                    ",CASE WHEN colu.is_nullable =0 THEN 'No' ELSE 'YES' END 'Null'  " +
                    ",CASE WHEN EXISTS ( SELECT 1 FROM dbo.sysindexes si  " +
                    "INNER JOIN dbo.sysindexkeys sik ON si.id = sik.id AND si.indid = sik.indid " +
                    "INNER JOIN dbo.syscolumns sc ON sc.id = sik.id AND sc.colid = sik.colid " +
                    "INNER JOIN dbo.sysobjects so ON so.name = si.name AND so.xtype = 'PK' " +
                    "WHERE sc.id = col.id AND sc.colid = col.colid ) THEN 'PRI' ELSE '' END 'Key'  " +
                    ",ISNULL(comm.text, '') 'Default' " +
                    ",CASE WHEN colu.is_identity = 1 THEN 'auto_increment' ELSE '' END 'Extra' " +
                    ",convert(varchar(100), c.value) Comment " +
                    "FROM sys.tables tab " +
                    "LEFT JOIN sys.columns colu ON colu.object_id = tab.object_id " +
                    "LEFT JOIN dbo.syscolumns col ON col.id = tab.object_id and col.colid = colu.column_id  " +
                    "LEFT JOIN dbo.syscomments comm ON col.cdefault = comm.id " +
                    "LEFT JOIN sys.extended_properties c ON c.major_id = colu.object_id AND c.minor_id = colu.column_id " +
                    "LEFT JOIN sys.types d ON colu.system_type_id = d.system_type_id AND d.user_type_id = colu.system_type_id " +
                    "WHERE tab.name ='"+tname+"' ORDER BY colu.column_id";
        }
        return tablColumnSql;
    }

    @Override
    public String toString() {
        return "DataBaseSettingBO{" + "dbType='" + dbType + '\'' + ", dbName='" + dbName + '\'' + ", address='"
                + address + '\'' + ", port='" + port + '\'' + ", username='" + username + '\'' + ", password='"
                + password + '\'' + ", url='" + url + '\'' + ", driver='" + driver + '\'' + ", testSql='" + testSql
                + '\'' + ", showTable='" + showTable + '\'' + '}';
    }

}
