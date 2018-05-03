package com.quduo.welfareshop.ui.friend.entity;

import com.quduo.welfareshop.base.BaseBean;

//用户发布的视频
public class UserVideoInfo extends BaseBean {

    /**
     * id : 270
     * user_id : 1864
     * url : http://video.quduo1688.com/2382df1e040c4458b1f835668d94e0d9/8ca68668e70d42b582ef642e2c8b25b0-5287d2089db37e62345123a1be272f8b.mp4
     * thumb : /movie/61/h.webp
     * content : 哈哈哈哈
     * good : 101
     * price : 2
     * favor_times : 52
     * play_times : 999
     * age : 20
     * nickname : 萧萌
     * avatar : /avatar/3/f/1864/1522318917785565abcbe45bfc9d.webp
     * video_times : 2
     * payed : false
     */

    private int id;
    private int user_id;
    private String url;
    private String thumb;
    private String content;
    private int good;
    private int price;
    private int favor_times;
    private int play_times;
    private int age;
    private String nickname;
    private String avatar;
    private int video_times;
    private boolean payed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getFavor_times() {
        return favor_times;
    }

    public void setFavor_times(int favor_times) {
        this.favor_times = favor_times;
    }

    public int getPlay_times() {
        return play_times;
    }

    public void setPlay_times(int play_times) {
        this.play_times = play_times;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getVideo_times() {
        return video_times;
    }

    public void setVideo_times(int video_times) {
        this.video_times = video_times;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }
}
