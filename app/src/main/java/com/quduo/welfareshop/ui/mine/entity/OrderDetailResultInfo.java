package com.quduo.welfareshop.ui.mine.entity;

import com.quduo.welfareshop.base.BaseBean;
import com.quduo.welfareshop.ui.shop.entity.GoodsInfo;

import java.util.List;

/**
*Author:scene
*Time:2018/3/23 15:25
*Description:订单详情返回
*/

public class OrderDetailResultInfo extends BaseBean{
    private OrderDetailInfo data;
    private List<GoodsInfo> products;

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
}
