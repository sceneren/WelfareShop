package com.quduo.welfareshop.ui.welfare.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

public class LsjInfo extends BaseBean {

    /**
     * android : 6585
     * ios : 6888
     * pc : 3366
     * android_url : http://api.quduo1688.comtech/android
     * ios_url : http://api.quduo1688.comtech/ios
     * pc_url : http://api.quduo1688.comtech/pc
     * android_video : http://video.quduo1688.com/1f118977519a433d8f349db2f76e260f/d7d57b2daa9a4d32b8f4afd22db6f290-5287d2089db37e62345123a1be272f8b.mp4
     * ios_video : http://video.quduo1688.com/1f118977519a433d8f349db2f76e260f/d7d57b2daa9a4d32b8f4afd22db6f290-5287d2089db37e62345123a1be272f8b.mp4
     * pc_video : http://video.quduo1688.com/1f118977519a433d8f349db2f76e260f/d7d57b2daa9a4d32b8f4afd22db6f290-5287d2089db37e62345123a1be272f8b.mp4
     * unlocked : 0
     */

    private int android;
    private int ios;
    private int pc;
    private String android_url;
    private String ios_url;
    private String pc_url;
    private String android_video;
    private String ios_video;
    private String pc_video;
    private boolean payed;
    private int total;
    private int price;
    private List<String> users;

    public int getAndroid() {
        return android;
    }

    public void setAndroid(int android) {
        this.android = android;
    }

    public int getIos() {
        return ios;
    }

    public void setIos(int ios) {
        this.ios = ios;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public String getAndroid_url() {
        return android_url;
    }

    public void setAndroid_url(String android_url) {
        this.android_url = android_url;
    }

    public String getIos_url() {
        return ios_url;
    }

    public void setIos_url(String ios_url) {
        this.ios_url = ios_url;
    }

    public String getPc_url() {
        return pc_url;
    }

    public void setPc_url(String pc_url) {
        this.pc_url = pc_url;
    }

    public String getAndroid_video() {
        return android_video;
    }

    public void setAndroid_video(String android_video) {
        this.android_video = android_video;
    }

    public String getIos_video() {
        return ios_video;
    }

    public void setIos_video(String ios_video) {
        this.ios_video = ios_video;
    }

    public String getPc_video() {
        return pc_video;
    }

    public void setPc_video(String pc_video) {
        this.pc_video = pc_video;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
