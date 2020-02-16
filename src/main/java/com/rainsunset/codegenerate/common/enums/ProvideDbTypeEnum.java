package com.rainsunset.codegenerate.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 支持的数据库类型枚举
 * @Author: Amo
 * @CreateDate: 2020/2/17
 */
public enum ProvideDbTypeEnum {
    DB_TYPT_MYSQL5("MySql5"),
    DB_TYPT_MYSQL8("MySql8"),
    DB_TYPT_SQLSERVER("SqlServer"),
    DB_TYPT_ORACLE("Oracle"),
    ;

    private String code;

    ProvideDbTypeEnum(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /** 初始化支持的数据库类型 */
    public static List<String> initProvideDbType(){
        List<String> provideDbType = new ArrayList<>();
        for (ProvideDbTypeEnum dbType : ProvideDbTypeEnum.values()) {
            provideDbType.add(dbType.getCode());
        }
        return provideDbType;
    }
}
