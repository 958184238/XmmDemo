package com.exam.admin.rongyundemo.http.utils;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/4/15.
 */

public abstract class BaseSubscriber<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(T value) {
        doOnNext(value);
    }

    @Override
    public void onError(Throwable e) {
        doOnError(e);
    }

    @Override
    public void onComplete() {
    }

    protected abstract void doOnNext(T t);

    protected abstract void doOnError(Throwable e);
}
