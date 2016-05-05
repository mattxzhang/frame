package com.cheny.frame.presenter;

import com.cheny.frame.callback.RequestCallback;

import rx.Subscription;

/**
 * 作者： by y on 2016/3/22.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
public interface WeatherInteractor<T> {
    Subscription getWeather(RequestCallback<T> callback);
}
