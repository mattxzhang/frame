package com.cheny.frame.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cheny.frame.annotation.ActivityFragmentInject;
import com.cheny.frame.common.loading.VaryTestViewHelperController;
import com.cheny.frame.common.loading.VaryViewHelperController;

import butterknife.ButterKnife;

/**
 * Created by y on 2016/3/22.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements
        BaseView {

    /**
     * 布局的id
     */
    protected int mContentViewId;


    /**
     * 将代理类通用行为抽出来
     */
    protected T mPresenter;

    /**
     * 控制加载框、网络重试、错误Controller
     */
    protected VaryTestViewHelperController varyViewHelperController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //annotation attrs
        if (getClass().isAnnotationPresent(ActivityFragmentInject.class)) {
            ActivityFragmentInject annotation = getClass()
                    .getAnnotation(ActivityFragmentInject.class);
            mContentViewId = annotation.contentViewId();
        } else {
            throw new RuntimeException(
                    "Class must add annotations of ActivityFragmentInitParams.class");
        }
        setContentView(mContentViewId);
        if (getLoadingView() != null) {
            varyViewHelperController = new VaryTestViewHelperController(getLoadingView());
        }
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    // abstract class behavior
    protected abstract void initView();

    protected abstract View getLoadingView();


    @Override
    public void hideLoading() {
        varyViewHelperController.restore();
    }

    @Override
    public void showLoading() {
        varyViewHelperController.showLoading("test");
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        varyViewHelperController.showError("", null);
    }
}
