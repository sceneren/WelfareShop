package com.quduo.welfareshop.ui.friend.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/15 10:04
 * Description:附近的人
 */

public class OtherSimpleUserInfo extends BaseBean {

    /**
     * id : 1049
     * nickname : 米露
     * avatar : /avatar/3/f/1049/A.png
     * sex : 2
     * age : 23
     * subscribe : 0
     * location : 中国/重庆市/重庆市/渝中区
     * distance : 615
     * photo : /avatar/3/f/1049/A.png
     */

    private int id;
    private String nickname;
    private String avatar;
    private int sex;
    private int age;
    private int subscribe;
    private String location;
    private int distance;
    private List<String> photo;
    private int subscribe_id;
    private int avatar_width;
    private int avatar_height;
    private String signature;
    private int is_busy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<String> getPhoto() {
        return photo;
    }

    public void setPhoto(List<String> photo) {
        this.photo = photo;
    }

    public int getSubscribe_id() {
        return subscribe_id;
    }

    public void setSubscribe_id(int subscribe_id) {
        this.subscribe_id = subscribe_id;
    }

    public int getAvatar_width() {
        return avatar_width;
    }

    public void setAvatar_width(int avatar_width) {
        this.avatar_width = avatar_width;
    }

    public int getAvatar_height() {
        return avatar_height;
    }

    public void setAvatar_height(int avatar_height) {
        this.avatar_height = avatar_height;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getIs_busy() {
        return is_busy;
    }

    public void setIs_busy(int is_busy) {
        this.is_busy = is_busy;
    }
}
