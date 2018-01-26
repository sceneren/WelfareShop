package com.quduo.welfareshop.http.listener;

/**
 * Case By:服务器返回的resultListener
 * package:wiki.scene.shop.http.listener
 * Author：scene on 2017/7/6 11:53
 */

public interface HttpResultListener<T> {
    void onSuccess(T data);

    void onFail(String message);

    void onFinish();
}
