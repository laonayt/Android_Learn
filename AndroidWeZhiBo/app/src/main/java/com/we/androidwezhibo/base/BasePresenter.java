package com.we.androidwezhibo.base;

import com.we.androidwezhibo.network.RetrofitService;
import com.we.androidwezhibo.network.RetrofitTool;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

//abstract 抽象类
public abstract class BasePresenter<V> {
    protected V mView;
    //net
//    protected RetrofitService retrofitService;
    protected RetrofitService retrofitService() {
        return RetrofitTool.getInstance().retrofitService;
    }
    //订阅
    private CompositeSubscription mCompositeSubscription;

    //初始化方法
    public BasePresenter(V view) {
        attachView(view);
    }

    public void attachView(V view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
        onUnsubscribe();
    }

    //注册
    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
