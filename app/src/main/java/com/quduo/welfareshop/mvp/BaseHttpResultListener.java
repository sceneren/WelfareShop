package com.quduo.welfareshop.mvp;

/**
 * Case By:
 * package:wiki.scene.shop.mvp
 * Author：scene on 2017/6/28 09:20
 */

public interface BaseHttpResultListener<T> {
    void onSuccess(T data);

    void onError(String msg);

    void onFinish();
}
