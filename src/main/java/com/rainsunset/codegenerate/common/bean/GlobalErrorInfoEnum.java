/**
 * Company
 * Copyright (C) 2004-2018 All Rights Reserved.
 */
package com.rainsunset.codegenerate.common.bean;

/**
 * @ClassName GlobalErrorInfoEnum
 * @Description: 全局异常枚举类
 * @Author: ligw
 * @Company rainsunset
 * @CreateDate: 2019.04.04
 */
public enum GlobalErrorInfoEnum implements ErrorInfoInterface{

    /**
     200，成功
     201，已存在
     401，未授权
     403，禁止
     404，不存在
     405，状态异常
     412，前提条件失败
     415，格式错
     500，内部错误
     */

    //region
    // 全局系统异常 占用范围-999 -- 1000
    SUCCESS("0", "success"),
    SYSTEM_ERROR("-1","系统异常") ,
    PARAM_ERROR("-101","参数异常") ,
    //endregion

    ;

    private String code;

    private String message;

    GlobalErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override public String getCode(){
        return this.code;
    }

    @Override public String getMessage(){
        return this.message;
    }

    public static String getMsgByCode(String code){
        for(GlobalErrorInfoEnum responseInfo:GlobalErrorInfoEnum.values()){
            if(code.equals(responseInfo.getCode())){
                return responseInfo.getMessage();
            }
        }
        return code + "未定义";
    }

}
