package com.quduo.welfareshop.ui.welfare.entity;

import com.quduo.welfareshop.base.BaseBean;
import com.quduo.welfareshop.ui.shop.entity.GoodsInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/20 16:26
 * Description:视频详情
 */
public class VideoDetailInfo extends BaseBean {

    /**
     * id : 2
     * cate_id : 1
     * name : 让我也睡一下
     * weight : 0
     * thumb : /micro-videos/2/h.png
     * thumb_shu : null
     * position_id : 0
     * url : http://192.168.0.88:9092/micro-videos/2/v.mp4
     * view_times : 8
     * favor_times : 0
     * tags : null
     * score : 0
     * play_times : 0
     * good : 0
     * description : null
     * duration : 0
     * price : 0.00
     */

    private int id;
    private int cate_id;
    private String name;
    private int weight;
    private String thumb;
    private Object thumb_shu;
    private int position_id;
    private String url;
    private int view_times;
    private int favor_times;
    private Object tags;
    private int score;
    private int play_times;
    private int good;
    private Object description;
    private int duration;
    private String price;
    private int favor_id;
    private boolean is_good;
    private RelatedVideoBean related;
    private List<GoodsInfo> goods;
    private List<VideoCommentInfo> comment;

    public List<VideoCommentInfo> getComment() {
        return comment;
    }

    public void setComment(List<VideoCommentInfo> comment) {
        this.comment = comment;
    }

    public List<GoodsInfo> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsInfo> goods) {
        this.goods = goods;
    }

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

    public Object getThumb_shu() {
        return thumb_shu;
    }

    public void setThumb_shu(Object thumb_shu) {
        this.thumb_shu = thumb_shu;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
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

    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public RelatedVideoBean getRelated() {
        return related;
    }

    public void setRelated(RelatedVideoBean related) {
        this.related = related;
    }

    public class RelatedVideoBean extends BaseBean {
        private List<VideoInfo> heng;
        private List<VideoInfo> shu;

        public List<VideoInfo> getHeng() {
            return heng;
        }

        public void setHeng(List<VideoInfo> heng) {
            this.heng = heng;
        }

        public List<VideoInfo> getShu() {
            return shu;
        }

        public void setShu(List<VideoInfo> shu) {
            this.shu = shu;
        }
    }
}
