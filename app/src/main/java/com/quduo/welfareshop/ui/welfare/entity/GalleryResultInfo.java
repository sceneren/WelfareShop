package com.quduo.welfareshop.ui.welfare.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
 * Created by scene on 2018/3/9.
 */

public class GalleryResultInfo extends BaseBean {
    private List<BannerInfo> banner;
    private List<GalleryCateInfo> cate;
    private GalleryDataInfo gallery;

    public List<BannerInfo> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerInfo> banner) {
        this.banner = banner;
    }

    public List<GalleryCateInfo> getCate() {
        return cate;
    }

    public void setCate(List<GalleryCateInfo> cate) {
        this.cate = cate;
    }

    public GalleryDataInfo getGallery() {
        return gallery;
    }

    public void setGallery(GalleryDataInfo gallery) {
        this.gallery = gallery;
    }
}
