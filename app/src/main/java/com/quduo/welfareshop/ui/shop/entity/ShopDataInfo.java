package com.quduo.welfareshop.ui.shop.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
*Author:scene
*Time:2018/3/22 14:46
*Description:商城首页
*/

public class ShopDataInfo extends BaseBean{
    private int last_page;
    private int current_page;
    private List<GoodsInfo> data;

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public List<GoodsInfo> getData() {
        return data;
    }

    public void setData(List<GoodsInfo> data) {
        this.data = data;
    }
}
