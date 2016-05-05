package com.cheny.frame.http.service;

import com.cheny.frame.data.Test;
import com.cheny.frame.data.WeatherInfo;
import com.cheny.frame.http.manager.RetrofitManager;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by y on 2016/3/16.
 */
public class TestApi extends BaseApi {

    //定义接口
    private interface TestService {
        //GET注解不可用@FormUrlEncoded，要用@Query注解引入请求参数
        @GET("/api/Test/Test2")
        Observable<Test> queryProfile();


        /**
         * 天气情况 例子：http://wthrcdn.etouch.cn/weather_mini?city=%E5%8C%97%E4%BA%AC
         *
         * @param city 城市名称
         * @return 被观察者
         */
        @GET("weather_mini")
        Observable<WeatherInfo> getWeatherInfo(@Header("Cache-Control") String cacheControl, @Query("city") String city);
    }

    protected static final TestService service = getRetrofit().create(TestService.class);

    //查询用户信息接口
    public static Observable<Test> queryProfile() {
        return service.queryProfile();
    }

    public static Observable<WeatherInfo> getWeatherInfo(String city) {
        return service.getWeatherInfo(RetrofitManager.getCacheControl(), city);
    }

}
