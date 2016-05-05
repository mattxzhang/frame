package com.cheny.frame.view;

import com.cheny.frame.base.BaseView;
import com.cheny.frame.data.WeatherInfo;

/**
 * 作者： by y on 2016/3/22.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
public interface TestView extends BaseView {


    void setText(String helloWorld);

    void setWeatherInfo(WeatherInfo weatherInfo);
}
