package com.quduo.welfareshop.ui.shop.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
*Author:scene
*Time:2018/3/22 14:49
*Description:商城热门
*/

public class ShopHotInfo extends BaseBean{

    /**
     * id : 6
     * name : 产品6
     * hot_thumb : /img/2018/03/22d68c098c890efc4c23e8678ccc71a3.jpeg
     */

    private int id;
    private String name;
    private String hot_thumb;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHot_thumb() {
        return hot_thumb;
    }

    public void setHot_thumb(String hot_thumb) {
        this.hot_thumb = hot_thumb;
    }
}
