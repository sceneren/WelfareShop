package com.quduo.welfareshop.ui.welfare.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
*Author:scene
*Time:2018/3/20 18:21
*Description:视频评论
*/

public class VideoCommentInfo extends BaseBean {

    /**
     * content : 相当巴适
     * nickname : 约会
     * avatar : /avatar/3/f/1076/A.png
     */

    private String content;
    private String nickname;
    private String avatar;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
