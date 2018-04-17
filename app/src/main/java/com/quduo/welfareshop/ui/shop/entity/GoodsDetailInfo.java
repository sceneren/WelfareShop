package com.quduo.welfareshop.ui.shop.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
*Author:scene
*Time:2018/3/22 16:18
*Description:商品详情
*/

public class GoodsDetailInfo extends BaseBean {

    /**
     * id : 1
     * name : 女性自慰器
     * cate_id : 1
     * price : 200.00
     * old_price : 500
     * is_hot : 1
     * thumb : /goods/1/1.jpg
     * images : ["/goods/1/1.jpg","/goods/1/2.jpg","/goods/1/3.jpg","/goods/1/4.jpg"]
     * tags : 电动
     * description : 测试简介
     * sales : 100
     * model : ["小号","中号","大号"]
     * content : /img/2018/03/bef0cfeee2c180ba4f7c9995d685c59a.png,/img/2018/03/ed126bf1942b5f95333eb9c7b46e2603.jpeg
     * weight : 6666
     * good : 0
     * coupon_cost : 20.00
     * view_times : 331
     * status : 1
     * hot_thumb : /img/2018/03/4f0f474304a959e6c4d8a0e9116d61f1.jpeg
     */
    private String sub_title;
    private int id;
    private String name;
    private int cate_id;
    private String price;
    private int old_price;
    private int is_hot;
    private String thumb;
    private String tags;
    private String description;
    private int sales;
    private String content;
    private int weight;
    private int good;
    private String coupon_cost;
    private int view_times;
    private int status;
    private String hot_thumb;
    private List<String> images;
    private List<String> model;
    private int comment_count;
    private int favor_id;


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

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getOld_price() {
        return old_price;
    }

    public void setOld_price(int old_price) {
        this.old_price = old_price;
    }

    public int getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(int is_hot) {
        this.is_hot = is_hot;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public String getCoupon_cost() {
        return coupon_cost;
    }

    public void setCoupon_cost(String coupon_cost) {
        this.coupon_cost = coupon_cost;
    }

    public int getView_times() {
        return view_times;
    }

    public void setView_times(int view_times) {
        this.view_times = view_times;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHot_thumb() {
        return hot_thumb;
    }

    public void setHot_thumb(String hot_thumb) {
        this.hot_thumb = hot_thumb;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getModel() {
        return model;
    }

    public void setModel(List<String> model) {
        this.model = model;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getFavor_id() {
        return favor_id;
    }

    public void setFavor_id(int favor_id) {
        this.favor_id = favor_id;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }
}
