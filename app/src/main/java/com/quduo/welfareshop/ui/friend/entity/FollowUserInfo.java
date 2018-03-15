package com.quduo.welfareshop.ui.friend.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
 * Author:scene
 * Time:2018/3/15 11:58
 * Description:关注的用户
 */

public class FollowUserInfo extends BaseBean {

    /**
     * id : 3
     * target_user_id : 2
     * location : 中国/重庆市/重庆市/渝中区
     * nickname : 漂亮妹子
     * avatar : /avatar/2.jpeg
     * sex : 2
     * age : 18
     * signature : null
     */

    private int id;
    private int target_user_id;
    private String location;
    private String nickname;
    private String avatar;
    private int sex;
    private int age;
    private String signature;
    private int distance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTarget_user_id() {
        return target_user_id;
    }

    public void setTarget_user_id(int target_user_id) {
        this.target_user_id = target_user_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
