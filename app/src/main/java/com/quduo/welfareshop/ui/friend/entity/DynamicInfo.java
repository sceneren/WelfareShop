package com.quduo.welfareshop.ui.friend.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

//动态
public class DynamicInfo extends BaseBean {

    /**
     * id : 270
     * user_id : 1864
     * url : http://video.quduo1688.com/2382df1e040c4458b1f835668d94e0d9/8ca68668e70d42b582ef642e2c8b25b0-5287d2089db37e62345123a1be272f8b.mp4
     * images : []
     * thumb : /movie/61/h.webp
     * content : 哈哈哈哈
     * good : 101
     * price : 2
     * favor_times : 52
     * play_times : 999
     */

    private int id;
    private int user_id;
    private String avatar;
    private String nickname;
    private String url;
    private String thumb;
    private String content;
    private int good;
    private int price;
    private int favor_times;
    private int play_times;
    private List<String> images;
    private List<DynamicCommentInfo> comments;
    private int sex;
    private int age;
    private int comment_user_number;
    private boolean isShowAll;
    private List<String> avatars;
    private boolean is_good;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<DynamicCommentInfo> getComments() {
        return comments;
    }

    public void setComments(List<DynamicCommentInfo> comments) {
        this.comments = comments;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getComment_user_number() {
        return comment_user_number;
    }

    public void setComment_user_number(int comment_user_number) {
        this.comment_user_number = comment_user_number;
    }

    public boolean isShowAll() {
        return isShowAll;
    }

    public void setShowAll(boolean showAll) {
        isShowAll = showAll;
    }

    public List<String> getAvatars() {
        return avatars;
    }

    public void setAvatars(List<String> avatars) {
        this.avatars = avatars;
    }

    public boolean isIs_good() {
        return is_good;
    }

    public void setIs_good(boolean is_good) {
        this.is_good = is_good;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }
}
