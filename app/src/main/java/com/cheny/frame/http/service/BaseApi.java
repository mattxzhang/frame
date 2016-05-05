package com.cheny.frame.http.service;

import com.cheny.frame.http.manager.RetrofitManager;

import retrofit2.Retrofit;

/**
 * Created by y on 2016/3/16.
 */
public abstract class BaseApi {
    public static final String API_SERVER = "http://wthrcdn.etouch.cn/";
    private static Retrofit mRetrofit = null;


    protected static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            return RetrofitManager.getInstance(API_SERVER).getmRetrofit();
        }
        return mRetrofit;
    }


}
