package com.quduo.welfareshop.ui.welfare.entity;

import java.io.Serializable;

/**
 * Author:scene
 * Time:2018/2/9 10:10
 * Description:视频信息
 */

public class WelfareVideoInfo implements Serializable{
    private String imagePath;
    private String videoPath;
    private String title;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
