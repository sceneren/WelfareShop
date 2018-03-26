package com.quduo.welfareshop.ui.mine.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/21 10:59
 * Description:收藏的图库
 */

public class MyFollowGalleryInfo extends BaseBean {

    /**
     * id : 28
     * thumb : /gallery/2/17/fm.jpg
     * thumb_small : ["/gallery/2/17/s1.jpg","/gallery/2/17/s2.jpg","/gallery/2/17/s3.jpg"]
     * avatar : /gallery/2/17/tx.jpg
     * gallery_id : 17
     * name : 大胸女神景思晴肤白貌美楚楚动人
     * favor_times : 6
     * price : 1
     */

    private int id;
    private String thumb;
    private String avatar;
    private int gallery_id;
    private String name;
    private int favor_times;
    private int price;
    private List<String> thumb_small;
    private boolean is_good;
    private int view_times;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGallery_id() {
        return gallery_id;
    }

    public void setGallery_id(int gallery_id) {
        this.gallery_id = gallery_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFavor_times() {
        return favor_times;
    }

    public void setFavor_times(int favor_times) {
        this.favor_times = favor_times;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<String> getThumb_small() {
        return thumb_small;
    }

    public void setThumb_small(List<String> thumb_small) {
        this.thumb_small = thumb_small;
    }


    public int getView_times() {
        return view_times;
    }

    public void setView_times(int view_times) {
        this.view_times = view_times;
    }

    public boolean isIs_good() {
        return is_good;
    }

    public void setIs_good(boolean is_good) {
        this.is_good = is_good;
    }
}
