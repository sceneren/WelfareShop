package com.quduo.welfareshop.ui.welfare.entity;

import java.io.Serializable;

/**
*Author:scene
*Time:2018/2/24 11:58
*Description:小说首页的描述
*/

public class NovelIndexInfo implements Serializable {
    private String title;
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
