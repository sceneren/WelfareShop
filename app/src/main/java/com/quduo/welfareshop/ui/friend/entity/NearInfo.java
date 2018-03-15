package com.quduo.welfareshop.ui.friend.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
*Author:scene
*Time:2018/3/15 10:04
*Description:附近的人
*/

public class NearInfo extends BaseBean{

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
    private String photo;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
