package com.cheny.frame.presenter;

import com.cheny.frame.base.BasePresenterImpl;
import com.cheny.frame.callback.RequestCallback;
import com.cheny.frame.data.WeatherInfo;
import com.cheny.frame.view.TestView;

/**
 * 作者： by y on 2016/3/22.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
public class TestPresenterImpl extends BasePresenterImpl<TestView> implements TestPresenter {

    WeatherInteractor<WeatherInfo> weatherInfoWeatherInteractor;

    public TestPresenterImpl(TestView view) {
        super(view);
        weatherInfoWeatherInteractor = new WeatherInteractorImpl();

    }


    @Override
    public void setHelloWorld() {
        mView.setText("Hello World!");
    }

    @Override
    public void setWeather() {
        weatherInfoWeatherInteractor.getWeather(new RequestCallback<WeatherInfo>() {
            @Override
            public void beforeRequest() {
                mView.showLoading();
            }

            @Override
            public void requestError(String msg) {
                mView.hideLoading();
                mView.showError(msg);

            }

            @Override
            public void requestComplete() {
                mView.hideLoading();
            }

            @Override
            public void requestSuccess(WeatherInfo data) {
                mView.setWeatherInfo(data);
                mView.hideLoading();
            }
        });
    }
}
