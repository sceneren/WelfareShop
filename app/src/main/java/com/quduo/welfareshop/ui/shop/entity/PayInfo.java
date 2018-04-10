package com.quduo.welfareshop.ui.shop.entity;

/**
 * Author:scene
 * Time:2018/3/23 13:14
 * Description:支付信息
 */

public class PayInfo {


    /**
     * url : http://api.quduo1688.com/wx_wap_pay/232
     * code_url :
     * pay_type : 3
     * order_id : 232
     * data_id : 106
     */

    private String url;
    private String code_url;
    private int pay_type;
    private int order_id;
    private int data_id;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }
}
