package com.quduo.welfareshop.ui.friend.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

public class InteractInfo extends BaseBean {

    /**
     * id : 11
     * user_id : 3
     * url : http://file.quduo1688.com/selfie/934/v.mp4
     * thumb : /selfie/918/h.webp
     * content : 哈哈哈哈哈哈哈哈哈哈哈哈哈
     * good : 0
     * price : 2
     * favor_times : 1
     * play_times : 0
     * age : 33
     * nickname : 测试
     * avatar : /avatar/1522199469.0859HS2by.jpeg
     * video_times : 0
     * sex : 0
     * favor_id : 17158
     * payed : true
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
    private int sex;
    private int favor_id;
    private boolean payed;
    private List<InteractGoodsUserInfo> good_users;
    private boolean is_good;
    private int subscribe;

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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public List<InteractGoodsUserInfo> getGood_users() {
        return good_users;
    }

    public void setGood_users(List<InteractGoodsUserInfo> good_users) {
        this.good_users = good_users;
    }

    public boolean isIs_good() {
        return is_good;
    }

    public void setIs_good(boolean is_good) {
        this.is_good = is_good;
    }

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }
}
