package com.quduo.welfareshop.ui.mine.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/15 15:20
 * Description:自己的资料
 */

public class MyUserDetailInfo extends BaseBean {

    /**
     * id : 2
     * nickname : 漂亮妹子
     * sex : 2
     * avatar : /avatar/2.png
     * age : 18
     * location : 重庆/重庆/南岸区
     * star : 处女座
     * weight : 99.00
     * height : 165.00
     * weixin : 13333333333
     * mobile : 13333333333
     * job : 陪酒妹
     * blood_type : AB
     * birthday : 2009-02-14
     * photos : [{"id":3524,"url":"/photos/1521093919.89985aaa0d1fdbab1.png"}]
     * marital : 2
     * subscribe : 2
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
    private long mobile;
    private String job;
    private String blood_type;
    private String birthday;
    private String marital;
    private int subscribe;
    private List<PhotosBean> photos;
    private String signature;

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

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
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

    public List<PhotosBean> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotosBean> photos) {
        this.photos = photos;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public static class PhotosBean extends BaseBean {
        /**
         * id : 3524
         * url : /photos/1521093919.89985aaa0d1fdbab1.png
         */

        private int id;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
