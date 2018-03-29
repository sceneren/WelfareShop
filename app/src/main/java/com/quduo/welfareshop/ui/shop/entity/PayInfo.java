package com.quduo.welfareshop.ui.shop.entity;

/**
 * Author:scene
 * Time:2018/3/23 13:14
 * Description:支付信息
 */

public class PayInfo {

    /**
     * code_url : https://qr.alipay.com/bax03037x4vdox8yozye6033
     * url : https://qr.alipay.com/bax03037x4vdox8yozye6033
     * pay_type : 2
     * api : 1
     * real_pay_type : 2
     */

    private String url;
    private int pay_type;
    private int api;
    private int real_pay_type;
    private int order_id;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public int getApi() {
        return api;
    }

    public void setApi(int api) {
        this.api = api;
    }

    public int getReal_pay_type() {
        return real_pay_type;
    }

    public void setReal_pay_type(int real_pay_type) {
        this.real_pay_type = real_pay_type;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
