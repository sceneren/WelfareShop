package com.quduo.welfareshop.mvp;

/**
 * Case By: presenter基类
 * package:
 * Author：scene on 2017/6/27 10:52
 */
public abstract class BasePresenter<T> {
    public T mView;

    public void attach(T mView) {
        this.mView = mView;
    }

    public void dettach() {
        mView = null;
    }

}
