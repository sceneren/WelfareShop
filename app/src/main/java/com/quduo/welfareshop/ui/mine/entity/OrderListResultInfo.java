package com.quduo.welfareshop.ui.mine.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
*Author:scene
*Time:2018/3/23 14:01
*Description:订单列表
*/

public class OrderListResultInfo extends BaseBean{
    private List<OrderInfo> data;
    private OrderPageInfo info;

    public List<OrderInfo> getData() {
        return data;
    }

    public void setData(List<OrderInfo> data) {
        this.data = data;
    }

    public OrderPageInfo getInfo() {
        return info;
    }

    public void setInfo(OrderPageInfo info) {
        this.info = info;
    }
}
