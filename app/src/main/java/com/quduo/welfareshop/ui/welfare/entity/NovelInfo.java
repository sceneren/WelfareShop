package com.quduo.welfareshop.ui.welfare.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
 * Author:scene
 * Time:2018/3/9 9:50
 * Description:小说
 */

public class NovelInfo extends BaseBean {

    /**
     * title : 盛宠蜜爱：总裁的18岁甜妻
     * thumb : /novels/34/p.png
     * view_times : 0
     * favor_times : 0
     * score : 0
     * description :
     * 《盛宠蜜爱：总裁的18岁甜妻》
     * 作者：燕蔚儿
     * <p>
     * 简介：
     * 　　那晚酒后，她和他一夜缠绵。
     * 　　为了钱，她用腹中的孩子威胁他，却反被逼婚。
     * 　　婚前说好互不干涉，婚后他却插手她的大事小事。
     * 　　他拿她当真老婆疼，她眼里他却是一个强迫症晚期的神经病。
     * 　　“霍司琛，我要跟你离婚！”
     * 　　“下辈子吧。”
     * 　　“我受不了你了！”
     * 　　“等孩子生了，我再告诉你什么叫‘受不了’。”
     * 　　从结婚她就想着离婚，他最终如了她的愿。
     * 　　多年后的相遇，他依...
     */

    private String title;
    private String thumb;
    private String thumb_shu;
    private int view_times;
    private int favor_times;
    private int score;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumb_shu() {
        return thumb_shu;
    }

    public void setThumb_shu(String thumb_shu) {
        this.thumb_shu = thumb_shu;
    }
}
