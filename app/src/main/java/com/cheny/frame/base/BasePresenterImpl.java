package com.cheny.frame.base;

import rx.Subscription;

/**
 * 作者： by y on 2016/3/22.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
public class BasePresenterImpl<T extends BaseView> implements BasePresenter {

    protected Subscription mSubscription;
    protected T mView;

    public BasePresenterImpl(T view) {
        mView = view;
    }

    @Override
    public void onDestroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mView = null;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }
}
