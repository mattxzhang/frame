package com.cheny.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.cheny.frame.data.WeatherInfo;
import com.cheny.frame.http.callback.AbsAPICallback;
import com.cheny.frame.http.error.ApiException;
import com.cheny.frame.http.service.TestApi;
import com.socks.library.KLog;

import rx.schedulers.Schedulers;

/**
 * Created by y on 2016/3/16.
 */
public class TestActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


       /* findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestApi.getWeatherInfo("北京")
                        .subscribeOn(Schedulers.io())
                        .subscribe(new AbsAPICallback<WeatherInfo>() {
                            @Override
                            protected void onError(ApiException ex) {

                            }

                            @Override
                            protected void onPermissionError(ApiException ex) {
                                KLog.i("Permission", "error");
                            }

                            @Override
                            protected void onResultError(ApiException ex) {

                            }

                            @Override
                            public void onNext(WeatherInfo weatherInfo) {
                                KLog.i("weatherInfo", weatherInfo);
                            }
                        });


            }
        });*/
    }
}
