package com.quduo.welfareshop.event;

/**
 * Author:scene
 * Time:2018/2/5 9:53
 * Description:修改个人资料
 */

public class EditMyInfoEvent {
    private String title;
    private String content;

    public EditMyInfoEvent(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
