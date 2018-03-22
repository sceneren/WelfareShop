package com.quduo.welfareshop.ui.shop.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/22 15:48
 * Description:商品详情
 */

public class GoodsDetailResultInfo extends BaseBean {
    private List<GoodsCommentInfo> comments;
    private GoodsDetailInfo data;

    public List<GoodsCommentInfo> getComments() {
        return comments;
    }

    public void setComments(List<GoodsCommentInfo> comments) {
        this.comments = comments;
    }

    public GoodsDetailInfo getData() {
        return data;
    }

    public void setData(GoodsDetailInfo data) {
        this.data = data;
    }
}
