package com.quduo.welfareshop.ui.mine.entity;

import com.quduo.welfareshop.base.BaseBean;
import com.quduo.welfareshop.ui.shop.entity.GoodsInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/23 15:25
 * Description:订单详情返回
 */

public class OrderDetailResultInfo extends BaseBean {
    private OrderDetailInfo data;
    private List<GoodsInfo> products;
    private boolean wx_pay_enable;
    private boolean ali_pay_enable;

    public OrderDetailInfo getData() {
        return data;
    }

    public void setData(OrderDetailInfo data) {
        this.data = data;
    }

    public List<GoodsInfo> getProducts() {
        return products;
    }

    public void setProducts(List<GoodsInfo> products) {
        this.products = products;
    }

    public boolean isWx_pay_enable() {
        return wx_pay_enable;
    }

    public void setWx_pay_enable(boolean wx_pay_enable) {
        this.wx_pay_enable = wx_pay_enable;
    }

    public boolean isAli_pay_enable() {
        return ali_pay_enable;
    }

    public void setAli_pay_enable(boolean ali_pay_enable) {
        this.ali_pay_enable = ali_pay_enable;
    }
}
