/**
 * Company
 * Copyright (C) 2004-2018 All Rights Reserved.
 */
package com.rainsunset.codegenerate.common.bean;

/**
 * @ClassName GlobalErrorInfoEnum
 * @Description: 全局异常封装类
 * @Author: ligw
 * @Company rainsunset
 * @CreateDate: 2019.04.04
 */
public class GlobalErrorInfoException extends RuntimeException{

    private ErrorInfoInterface errorInfo;

    public GlobalErrorInfoException (ErrorInfoInterface errorInfo) {
        this.errorInfo = errorInfo;
    }

    public ErrorInfoInterface getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfoInterface errorInfo) {
        this.errorInfo = errorInfo;
    }

}
