package com.quduo.welfareshop.ui.friend.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

public class HomePageInfo extends BaseBean {

    /**
     * id : 1874
     * nickname : Cancer、乐乐
     * avatar : /jy/1874/photos/1.webp
     * sex : 2
     * age : 23
     * subscribe : 554
     * signature : 止不住的想.
     * avatar_width : 518
     * avatar_height : 675
     * view_times : 0
     * tags : ["标签1","标签2","标签3","标签4","标签5"]
     * level : 0
     * medal : ["/medal/1.png","/medal/2.png"]
     * video_times : 68
     * cover : /jy/1874/photos/1.webp
     * birthday : 0
     * photos : ["/jy/1874/photos/1.webp","/jy/1874/photos/2.webp","/jy/1874/photos/3.webp","/jy/1874/photos/4.webp","/jy/1874/photos/5.webp","/jy/1874/photos/6.webp"]
     * distance : 236
     * subscribe_id : 0
     * busy : true
     * videos : []
     * good_users : [{"nickname":"季天一灬","avatar":""},{"nickname":"鼻尖触碰","avatar":""},{"nickname":"狂恋HlPHOP","avatar":""},{"nickname":"苦逼鸭梨青少年","avatar":""},{"nickname":"嘿哟！一脚踹翻！","avatar":""},{"nickname":"会发光的权志龙","avatar":""},{"nickname":"伤很美","avatar":"/avatar/3/m/1673/A.webp"},{"nickname":"摘星老鬼","avatar":"/avatar/3/m/1682/A.webp"},{"nickname":"天舞","avatar":"/avatar/3/m/1727/A.webp"},{"nickname":"孤独自在","avatar":"/avatar/4/m/1918/A.webp"}]
     * dynamic : []
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
    private String cover;
    private int birthday;
    private int distance;
    private int subscribe_id;
    private boolean busy;
    private List<String> tags;
    private List<String> medal;
    private List<String> photos;
    private List<UserVideoInfo> videos;
    private List<VideoChatUserInfo> chat_users;
    private List<DynamicInfo> dynamic;

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getSubscribe_id() {
        return subscribe_id;
    }

    public void setSubscribe_id(int subscribe_id) {
        this.subscribe_id = subscribe_id;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
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

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public List<UserVideoInfo> getVideos() {
        return videos;
    }

    public void setVideos(List<UserVideoInfo> videos) {
        this.videos = videos;
    }

    public List<VideoChatUserInfo> getChat_users() {
        return chat_users;
    }

    public void setChat_users(List<VideoChatUserInfo> chat_users) {
        this.chat_users = chat_users;
    }

    public List<DynamicInfo> getDynamic() {
        return dynamic;
    }

    public void setDynamic(List<DynamicInfo> dynamic) {
        this.dynamic = dynamic;
    }

}
