package com.quduo.welfareshop.ui.welfare.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/8 14:25
 * Description:美女图库
 */

public class WelfareGalleryInfo extends BaseBean {

    /**
     * id : 2512
     * cate_id : 5
     * name : 黑色蕾丝美眉梓萱Crystal私房照
     * weight : 0
     * thumb : /gallery/5/2512/fm.jpg
     * images : ["/gallery/5/2512/5.jpg","/gallery/5/2512/11.jpg","/gallery/5/2512/19.jpg","/gallery/5/2512/1.jpg"]
     * view_times : 0
     * favor_times : 12
     * tags : null
     * good : 1
     * image_count : 24
     * is_good : false
     */

    private int id;
    private int cate_id;
    private String name;
    private int weight;
    private String thumb;
    private int view_times;
    private int favor_times;
    private int good;
    private int image_count;
    private boolean is_good;
    private List<String> images;
    private int favor_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getView_times() {
        return view_times;
    }

    public void setView_times(int view_times) {
        this.view_times = view_times;
    }

    public int getFavor_times() {
        return favor_times;
    }

    public void setFavor_times(int favor_times) {
        this.favor_times = favor_times;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getImage_count() {
        return image_count;
    }

    public void setImage_count(int image_count) {
        this.image_count = image_count;
    }

    public boolean isIs_good() {
        return is_good;
    }

    public void setIs_good(boolean is_good) {
        this.is_good = is_good;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getFavor_id() {
        return favor_id;
    }

    public void setFavor_id(int favor_id) {
        this.favor_id = favor_id;
    }
}
