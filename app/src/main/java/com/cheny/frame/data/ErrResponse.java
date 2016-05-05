package com.cheny.frame.data;

/**
 * Created by y on 2016/3/17.
 */

/**
 * "errorCode":"错误代码",
 * "errorMsg":"错误信息"
 */

public class ErrResponse {

    private int errorCode;
    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
