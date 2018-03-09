package com.quduo.welfareshop.ui.welfare.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/9 9:49
 * Description:小说
 */

public class NovelResultInfo extends BaseBean {
    private List<BannerInfo> banner;
    private List<NovelModelInfo> data;

    public List<BannerInfo> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerInfo> banner) {
        this.banner = banner;
    }

    public List<NovelModelInfo> getData() {
        return data;
    }

    public void setData(List<NovelModelInfo> data) {
        this.data = data;
    }
}
