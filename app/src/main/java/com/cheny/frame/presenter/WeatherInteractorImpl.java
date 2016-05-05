package com.cheny.frame.presenter;

import com.cheny.frame.callback.RequestCallback;
import com.cheny.frame.data.WeatherInfo;
import com.cheny.frame.http.callback.AbsAPICallback;
import com.cheny.frame.http.error.ApiException;
import com.cheny.frame.http.service.TestApi;
import com.socks.library.KLog;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * 作者： by y on 2016/3/22.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
public class WeatherInteractorImpl implements WeatherInteractor<WeatherInfo> {


    @Override
    public Subscription getWeather(RequestCallback<WeatherInfo> callback) {
        Subscription subscription = TestApi.getWeatherInfo("北京")
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        callback.beforeRequest();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<WeatherInfo>() {
                    @Override
                    protected void onError(ApiException ex) {
                        callback.requestError(ex.getMessage());
                    }

                    @Override
                    protected void onPermissionError(ApiException ex) {
                        KLog.i("Permission", "error");
                    }

                    @Override
                    protected void onResultError(ApiException ex) {
                        callback.requestError(ex.getMessage());
                    }

                    @Override
                    public void onNext(WeatherInfo weatherInfo) {
                        KLog.i("weatherInfo", weatherInfo);
                        callback.requestSuccess(weatherInfo);
                    }

                    @Override
                    protected void onResultComplete() {
                        callback.requestComplete();
                    }
                });
        return subscription;
    }
}
