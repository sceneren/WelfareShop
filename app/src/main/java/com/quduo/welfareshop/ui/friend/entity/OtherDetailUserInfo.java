package com.quduo.welfareshop.ui.friend.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/15 12:50
 * Description:用户详细信息
 */

public class OtherDetailUserInfo extends BaseBean {

    /**
     * id : 2
     * nickname : 漂亮妹子
     * sex : 2
     * avatar : /avatar/2.jpeg
     * age : 18
     * location : 中国/重庆市/重庆市/渝中区
     * star : 处女座
     * weight : 99.00
     * height : 165.00
     * weixin : 13333333333
     * mobile : 13333333333
     * job : 陪酒妹
     * blood_type : AB
     * birthday : 2009-02-14
     * marital : 2
     * subscribe : 0
     * photos : []
     */

    private int id;
    private String nickname;
    private int sex;
    private String avatar;
    private int age;
    private String location;
    private String star;
    private String weight;
    private String height;
    private String weixin;
    private String mobile;
    private String job;
    private String blood_type;
    private String birthday;
    private String marital;
    private int subscribe;
    private List<String> photos;
    private int subscribe_id;
    private String signature;
    private String cover;
    private String video_times;
    private String video_total_time;

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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public int getSubscribe_id() {
        return subscribe_id;
    }

    public void setSubscribe_id(int subscribe_id) {
        this.subscribe_id = subscribe_id;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getVideo_times() {
        return video_times;
    }

    public void setVideo_times(String video_times) {
        this.video_times = video_times;
    }

    public String getVideo_total_time() {
        return video_total_time;
    }

    public void setVideo_total_time(String video_total_time) {
        this.video_total_time = video_total_time;
    }
}
