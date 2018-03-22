package com.quduo.welfareshop.ui.shop.entity;

import com.quduo.welfareshop.base.BaseBean;
import com.quduo.welfareshop.ui.welfare.entity.BannerInfo;

import java.util.List;

/**
*Author:scene
*Time:2018/3/22 14:47
*Description:商城首页
*/

public class ShopResultInfo extends BaseBean {
    private List<BannerInfo> banner;
    private ShopDataInfo data;
    private List<ShopCateInfo> cate;
    private List<ShopHotInfo> hot;

    public List<BannerInfo> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerInfo> banner) {
        this.banner = banner;
    }

    public ShopDataInfo getData() {
        return data;
    }

    public void setData(ShopDataInfo data) {
        this.data = data;
    }

    public List<ShopCateInfo> getCate() {
        return cate;
    }

    public void setCate(List<ShopCateInfo> cate) {
        this.cate = cate;
    }

    public List<ShopHotInfo> getHot() {
        return hot;
    }

    public void setHot(List<ShopHotInfo> hot) {
        this.hot = hot;
    }
}
