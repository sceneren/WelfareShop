package com.quduo.welfareshop.ui.friend.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

public class NearInfo extends BaseBean {

    /**
     * id : 1884
     * nickname : 会飞的吕腊梅
     * avatar : /avatar/3/f/1884/15223189185572345abcbe46880c1.webp
     * sex : 2
     * age : 23
     * subscribe : 1338 -关注的数量
     * signature : 我也是一枚老司机
     * avatar_width : 688
     * avatar_height : 676
     * view_times : 0
     * tags : []
     * level : 0
     * medal : [] --勋章
     * video_times : 56
     * busy : 0-在线 1-忙碌
     * distance : 195
     */

    private int id;
    private String nickname;
    private String avatar;
    private int sex;
    private int age;
    private int subscribe;
    private String signature;
    private int avatar_width;
    private int avatar_height;
    private int view_times;
    private int level;
    private int video_times;
    private int busy;
    private int distance;
    private List<String> tags;
    private List<String> medal;
    private List<VideoChatUserInfo> chat_user;

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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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

    public int getView_times() {
        return view_times;
    }

    public void setView_times(int view_times) {
        this.view_times = view_times;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getVideo_times() {
        return video_times;
    }

    public void setVideo_times(int video_times) {
        this.video_times = video_times;
    }

    public int getBusy() {
        return busy;
    }

    public void setBusy(int busy) {
        this.busy = busy;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getMedal() {
        return medal;
    }

    public void setMedal(List<String> medal) {
        this.medal = medal;
    }

    public List<VideoChatUserInfo> getChat_user() {
        return chat_user;
    }

    public void setChat_user(List<VideoChatUserInfo> chat_user) {
        this.chat_user = chat_user;
    }
}
