package com.quduo.welfareshop.ui.welfare.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/9 9:30
 * Description:午夜视频列表
 */

public class MidNightVideoResultInfo extends BaseBean {
    private List<BannerInfo> banner;
    private List<VideoModelInfo> videos;

    public List<BannerInfo> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerInfo> banner) {
        this.banner = banner;
    }

    public List<VideoModelInfo> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoModelInfo> videos) {
        this.videos = videos;
    }
}
