package com.quduo.welfareshop.ui.welfare.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
 * Author:scene
 * Time:2018/3/12 10:04
 * Description:目录
 */

public class NovelChapterInfo extends BaseBean {

    /**
     * no : 1
     * id : 1
     * title : 第1章 你想得倒美

     */

    private int no;
    private int id;
    private String title;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
