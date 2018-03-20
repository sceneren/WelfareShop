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
     * id : 18
     * thumb : /gallery/2/18/fm.jpg
     * name : G奶天后冰露丰乳巨臀火热肉体诱惑
     * thumb_small : ["/gallery/2/18/s1.jpg","/gallery/2/18/s2.jpg","/gallery/2/18/s3.jpg"]
     * avatar : /gallery/2/18/tx.jpg
     * view_times : 0
     * favor_times : 0
     * tags : null
     * good : 0
     * price : 0
     * image_count : 61
     * favor_id : 0
     * is_good : false
     */

    private int id;
    private String thumb;
    private String name;
    private String avatar;
    private int view_times;
    private int favor_times;
    private Object tags;
    private int good;
    private int price;
    private int image_count;
    private int favor_id;
    private boolean is_good;
    private List<String> thumb_small;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImage_count() {
        return image_count;
    }

    public void setImage_count(int image_count) {
        this.image_count = image_count;
    }

    public int getFavor_id() {
        return favor_id;
    }

    public void setFavor_id(int favor_id) {
        this.favor_id = favor_id;
    }

    public boolean isIs_good() {
        return is_good;
    }

    public void setIs_good(boolean is_good) {
        this.is_good = is_good;
    }

    public List<String> getThumb_small() {
        return thumb_small;
    }

    public void setThumb_small(List<String> thumb_small) {
        this.thumb_small = thumb_small;
    }
}
