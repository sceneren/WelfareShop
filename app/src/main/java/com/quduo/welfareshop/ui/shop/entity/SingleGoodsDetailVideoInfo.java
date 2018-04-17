package com.quduo.welfareshop.ui.shop.entity;

import java.io.Serializable;
import java.util.List;

public class SingleGoodsDetailVideoInfo implements Serializable {
    private String title;
    private String thumb;
    private String video;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
