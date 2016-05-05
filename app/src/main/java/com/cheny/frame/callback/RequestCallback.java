package com.cheny.frame.callback;

/**
 * 作者： by y on 2016/3/22.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
public interface RequestCallback<T> {

    /**
     * 请求之前调用
     */
    void beforeRequest();

    /**
     * 请求错误调用
     *
     * @param msg 错误信息
     */
    void requestError(String msg);

    /**
     * 请求完成调用
     */
    void requestComplete();

    /**
     * 请求成功调用
     *
     * @param data 数据
     */
    void requestSuccess(T data);
}
