package com.cheny.frame.data;

/**
 * Created by y on 2016/3/17.
 */

/**
 * "resultCode":"结果代号，0表示成功",
 * "resultMsg":"成功返回时是消息数据列表，失败时是异常消息文本"
 */
public class ResultResponse {
    private int resultCode;
    private String resultMsg;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
