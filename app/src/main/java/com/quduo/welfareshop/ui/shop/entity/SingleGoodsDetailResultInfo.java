package com.quduo.welfareshop.ui.shop.entity;

import java.io.Serializable;
import java.util.List;

public class SingleGoodsDetailResultInfo implements Serializable {
    private List<GoodsCommentInfo> comments;
    private GoodsDetailInfo data;
    private List<SingleGoodsDetailVideoInfo> videos;
    private List<SingleGoodsDetailImageInfo> img_top;
    private List<SingleGoodsDetailImageInfo> img_bottom;

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

    public List<SingleGoodsDetailVideoInfo> getVideos() {
        return videos;
    }

    public void setVideos(List<SingleGoodsDetailVideoInfo> videos) {
        this.videos = videos;
    }

    public List<SingleGoodsDetailImageInfo> getImg_top() {
        return img_top;
    }

    public void setImg_top(List<SingleGoodsDetailImageInfo> img_top) {
        this.img_top = img_top;
    }

    public List<SingleGoodsDetailImageInfo> getImg_bottom() {
        return img_bottom;
    }

    public void setImg_bottom(List<SingleGoodsDetailImageInfo> img_bottom) {
        this.img_bottom = img_bottom;
    }
}
