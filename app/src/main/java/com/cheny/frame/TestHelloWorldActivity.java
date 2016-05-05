package com.cheny.frame;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cheny.frame.adapter.BaseRecyclerAdapter;
import com.cheny.frame.adapter.BaseRecyclerViewHolder;
import com.cheny.frame.annotation.ActivityFragmentInject;
import com.cheny.frame.base.BaseActivity;
import com.cheny.frame.base.BaseSpacesItemDecoration;
import com.cheny.frame.data.WeatherInfo;
import com.cheny.frame.presenter.TestPresenter;
import com.cheny.frame.presenter.TestPresenterImpl;
import com.cheny.frame.utils.MeasureUtil;
import com.cheny.frame.view.TestView;
import com.cheny.frame.widgets.AutoLoadMoreRecyclerView;
import com.jakewharton.rxbinding.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * 作者： by y on 2016/3/22.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_test_hellowolrd
)
public class TestHelloWorldActivity extends BaseActivity<TestPresenter> implements TestView {


    //UI
    @Bind(R.id.btn_test)
    Button btnTest;
    @Bind(R.id.tv_msg)
    TextView tvMsg;
    @Bind(R.id.recycler_view)
    AutoLoadMoreRecyclerView recyclerView;

    //Adapter
    private BaseRecyclerAdapter<WeatherInfo.DataEntity.ForecastEntity> mAdapter;


    @Override
    protected void initView() {
        mPresenter = new TestPresenterImpl(this);
        RxView.clicks(btnTest).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(new Observer<Void>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Void aVoid) {
                //Toast.makeText(TestHelloWorldActivity.this, "test", Toast.LENGTH_SHORT).show();

                mPresenter.setHelloWorld();
                mPresenter.setWeather();
            }
        });


    }

    @Override
    protected View getLoadingView() {
        return ButterKnife.findById(this, R.id.lv_container);
    }


    @Override
    public void setText(String helloWorld) {
        tvMsg.setText(helloWorld);
    }

    @Override
    public void setWeatherInfo(WeatherInfo weatherInfo) {
        tvMsg.setText(weatherInfo.toString());

        if (mAdapter == null) {
            initWeatherList(weatherInfo.data.forecast);
        } else {
            mAdapter.setData(weatherInfo.data.forecast);
        }
        if (recyclerView.isAllLoaded()) {
            // 之前全部加载完了的话，这里把状态改回来供底部加载用
            recyclerView.notifyMoreLoaded();
        }
    }

    private void initWeatherList(List<WeatherInfo.DataEntity.ForecastEntity> forecast) {
        mAdapter = new BaseRecyclerAdapter<WeatherInfo.DataEntity.ForecastEntity>(getApplicationContext(), forecast) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item_weather_summary;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, int position, WeatherInfo.DataEntity.ForecastEntity item) {
                holder.getTextView(R.id.tv_news_summary_title).setText(item.fengxiang + item.date + item.high);
                holder.getTextView(R.id.tv_news_summary_digest).setText(item.fengli + item.low + item.type);
            }
        };

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setAutoLayoutManager(linearLayoutManager).setAutoHasFixedSize(true)
                .addAutoItemDecoration(
                        new BaseSpacesItemDecoration(MeasureUtil.dip2px(getApplicationContext(), 4)))
                .setAutoItemAnimator(new DefaultItemAnimator()).setAutoAdapter(mAdapter);
    }


 /*   @Override
    public void showError(String message) {
        varyViewHelperController.showError("", v -> btnTest());
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
