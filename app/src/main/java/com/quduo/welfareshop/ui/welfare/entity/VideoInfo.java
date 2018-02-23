package com.quduo.welfareshop.ui.welfare.entity;

import java.io.Serializable;

/**
*Author:scene
*Time:2018/2/23 16:16
*Description:视频简介
*/

public class VideoInfo implements Serializable{
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
