package com.quduo.welfareshop.ui.welfare.entity;

import java.io.Serializable;

/**
 * Author:scene
 * Time:2018/2/23 16:16
 * Description:视频简介
 */

public class VideoInfo implements Serializable {


    /**
     * id : 159
     * name : 屁股扭的很带劲
     * thumb : /micro-videos/159/h.png
     * url : http://192.168.0.88:9092/micro-videos/159/v.mp4
     * view_times : 0
     * favor_times : 0
     * tags : null
     * play_times : 0
     * good : 0
     * score : 0
     */

    private int id;
    private String name;
    private String thumb;
    private String url;
    private int view_times;
    private int favor_times;
    private String tags;
    private int play_times;
    private int good;
    private int score;
    private boolean is_favor;
    private boolean is_good;
    private String description;
    private int favor_id;
    private int cate_id;

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
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

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getPlay_times() {
        return play_times;
    }

    public void setPlay_times(int play_times) {
        this.play_times = play_times;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isIs_favor() {
        return is_favor;
    }

    public void setIs_favor(boolean is_favor) {
        this.is_favor = is_favor;
    }

    public boolean isIs_good() {
        return is_good;
    }

    public void setIs_good(boolean is_good) {
        this.is_good = is_good;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFavor_id() {
        return favor_id;
    }

    public void setFavor_id(int favor_id) {
        this.favor_id = favor_id;
    }
}
