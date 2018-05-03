package com.quduo.welfareshop.ui.friend.entity;

import com.quduo.welfareshop.base.BaseBean;

//动态评论
public class DynamicCommentInfo extends BaseBean {

    /**
     * id : 1
     * video_id : 270
     * avatar : /avatar/4/m/1892/A.webp
     * name : 欲女
     * content : 哈哈哈哈哈哈哈哈
     * create_time : 0
     */

    private int id;
    private int video_id;
    private String avatar;
    private String name;
    private String content;
    private long create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }
}
