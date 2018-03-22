package com.quduo.welfareshop.ui.shop.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
*Author:scene
*Time:2018/3/22 14:42
*Description:商品分类
*/

public class ShopCateInfo extends BaseBean{

    /**
     * name : 延时锻炼
     * thumb : /img/2018/03/b46be68238499d4a1fc3ca4614781f47.png
     * id : 4
     */

    private String name;
    private String thumb;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
