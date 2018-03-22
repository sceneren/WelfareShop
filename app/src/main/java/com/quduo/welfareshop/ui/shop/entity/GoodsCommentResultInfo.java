package com.quduo.welfareshop.ui.shop.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/22 15:53
 * Description:商品评论返回
 */

public class GoodsCommentResultInfo extends BaseBean {
    private int current_page;
    private int last_page;
    private List<GoodsCommentInfo> data;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public List<GoodsCommentInfo> getData() {
        return data;
    }

    public void setData(List<GoodsCommentInfo> data) {
        this.data = data;
    }
}
