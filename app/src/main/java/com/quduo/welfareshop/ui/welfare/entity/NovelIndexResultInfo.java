package com.quduo.welfareshop.ui.welfare.entity;

import java.io.Serializable;
import java.util.List;

public class NovelIndexResultInfo implements Serializable {
    private List<BannerInfo> banner;
    private NovelIndexInfo data;

    public List<BannerInfo> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerInfo> banner) {
        this.banner = banner;
    }

    public NovelIndexInfo getData() {
        return data;
    }

    public void setData(NovelIndexInfo data) {
        this.data = data;
    }
}
