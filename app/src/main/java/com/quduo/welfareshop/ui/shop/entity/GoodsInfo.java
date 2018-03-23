package com.quduo.welfareshop.ui.shop.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
 * Author:scene
 * Time:2018/3/20 18:16
 * Description:商品列表信息
 */

public class GoodsInfo extends BaseBean {

    /**
     * thumb : /goods/1.jpg
     * id : 1
     * name : 女性自慰器
     * price : 200.00
     * sales : 100
     */

    private String thumb;
    private int id;
    private String name;
    private String price;
    private int sales;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }
}
