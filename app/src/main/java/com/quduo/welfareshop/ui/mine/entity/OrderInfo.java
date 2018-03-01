package com.quduo.welfareshop.ui.mine.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
 * Author:scene
 * Time:2018/3/1 9:06
 * Description:订单
 */

public class OrderInfo extends BaseBean {
    private int type;
    private String goods_name;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }
}
