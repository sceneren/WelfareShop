package com.quduo.welfareshop.ui.friend.entity;

import com.quduo.welfareshop.base.BaseBean;

public class HotVideoInfo extends BaseBean {

    /**
     * id : 11
     * user_id : 3
     * url : http://file.quduo1688.com/selfie/934/v.mp4
     * thumb : /selfie/918/h.webp
     * content : 哈哈哈哈哈哈哈哈哈哈哈哈哈
     * good : 0
     * price : 2
     * favor_times : 0
     * play_times : 0
     * age : 33
     * nickname : 测试
     * avatar : /avatar/1522199469.0859HS2by.jpeg
     * favor_id : 0
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
    private int favor_id;
    private boolean payed;
    private int sex;

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

    public int getFavor_id() {
        return favor_id;
    }

    public void setFavor_id(int favor_id) {
        this.favor_id = favor_id;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
