package com.quduo.welfareshop.ui.welfare.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
*Author:scene
*Time:2018/3/12 10:16
*Description:小说详情
*/

public class NovelDetailInfo extends BaseBean {

    /**
     * id : 1
     * title : 余生不过我爱你
     * weight : 0
     * thumb :
     * thumb_shu : /novels/1/p.png
     * view_times : 0
     * favor_times : 0
     * score : 0
     * description : 书名：余生不过我爱你
     作者：杨柠萌

     简介：
     谁都知道帝国都城的帝铭爵向来毒舌无比狂拽上天，一小心娶了个顽皮千金以后，画风秒变。
     “宝宝，来我给你捶捶腿。”
     “宝宝，要我给你捏捏腰吗？”
     众人纷纷不解，“请问顾小姐你是怎么把帝少变成妻奴的？”
     顾七宝挑眉，“因为帝少闲到无所事事，数钱数到手抽筋，所以我只要会捣乱就行咯。”
     众人：还有这种操作！！！

     ==================

     * good : 0
     * position_id : 0
     * txt_url : /novels/1/all.txt
     * is_favor : false
     * is_good : false
     */

    private int id;
    private String title;
    private int weight;
    private String thumb;
    private String thumb_shu;
    private int view_times;
    private int favor_times;
    private int score;
    private String description;
    private int good;
    private int position_id;
    private String txt_url;
    private boolean is_favor;
    private boolean is_good;
    private String tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getThumb_shu() {
        return thumb_shu;
    }

    public void setThumb_shu(String thumb_shu) {
        this.thumb_shu = thumb_shu;
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

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public String getTxt_url() {
        return txt_url;
    }

    public void setTxt_url(String txt_url) {
        this.txt_url = txt_url;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
