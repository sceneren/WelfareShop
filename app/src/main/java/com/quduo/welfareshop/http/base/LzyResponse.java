package com.quduo.welfareshop.http.base;

/**
 * Case By:baseResponse
 * package:wiki.scene.shop.http.base
 * Author：scene on 2017/6/27 11:54
 */

public class LzyResponse<T> {
    public int code;
    public String message;
    public boolean status;
    public T data;
}
