package com.cheny.frame.http.manager;


import android.support.annotation.NonNull;

import com.cheny.frame.base.Base;
import com.cheny.frame.http.convert.JsonResponseConverterFactory;
import com.cheny.frame.utils.NetworkUtil;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.socks.library.KLog;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * ClassName: RetrofitManager<p>
 * Author: oubowu<p>
 * Fuction: Retrofit请求管理类<p>
 * CreateDate:2016/2/13 20:34<p>
 * UpdateUser:<p>
 * UpdateDate:<p>
 */
public class RetrofitManager {

    //设缓存有效期为两天
    protected static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    protected static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
    protected static final String CACHE_CONTROL_NETWORK = "max-age=0";

    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private volatile static RetrofitManager mInstance;


    public static RetrofitManager getInstance(String strHost) {
        if (mInstance == null) {
            synchronized (RetrofitManager.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitManager(strHost);
                }
            }
        }
        return mInstance;
    }

    /**
     * @param strHost
     * @return
     */
    public static RetrofitManager builder(String strHost) {
        return new RetrofitManager(strHost);
    }

    private RetrofitManager(String strHost) {

        initOkHttpClient();

        mRetrofit = new Retrofit.Builder().baseUrl(strHost).client(mOkHttpClient)
                .addConverterFactory(JsonResponseConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

    }

    // 配置OkHttpClient
    private void initOkHttpClient() {
        if (mOkHttpClient == null) {
            KLog.e("初始化mOkHttpClient");
            // 因为BaseUrl不同所以这里Retrofit不为静态，但是OkHttpClient配置是一样的,静态创建一次即可
            File cacheFile = new File(Base.getContext().getCacheDir(),
                    "HttpCache"); // 指定缓存路径
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); // 指定缓存大小100Mb
            // 云端响应头拦截器，用来配置缓存策略
            Interceptor rewriteCacheControlInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    if (!NetworkUtil.isConnected()) {
                        request = request.newBuilder()
                                .cacheControl(CacheControl.FORCE_CACHE).build();
                        KLog.e("no network");
                    }
                    Response originalResponse = chain.proceed(request);
                    if (NetworkUtil.isConnected()) {
                        //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                        String cacheControl = request.cacheControl().toString();
                        return originalResponse.newBuilder()
                                .header("Cache-Control", cacheControl)
                                .removeHeader("Pragma").build();
                    } else {
                        return originalResponse.newBuilder().header("Cache-Control",
                                "public, only-if-cached," + CACHE_STALE_SEC)
                                .removeHeader("Pragma").build();
                    }
                }
            };
            mOkHttpClient = new OkHttpClient.Builder().cache(cache)
                    .addNetworkInterceptor(rewriteCacheControlInterceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .addInterceptor(rewriteCacheControlInterceptor)
                    .connectTimeout(30, TimeUnit.SECONDS).build();

        }
    }


    /**
     * 根据网络状况获取缓存的策略
     *
     * @return
     */
    @NonNull
    public static String getCacheControl() {
        return NetworkUtil.isConnected() ? CACHE_CONTROL_NETWORK : CACHE_CONTROL_CACHE;
    }

    public Retrofit getmRetrofit() {
        return mRetrofit;
    }

    public void setmRetrofit(Retrofit mRetrofit) {
        this.mRetrofit = mRetrofit;
    }
}
