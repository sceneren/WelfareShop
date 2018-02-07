package com.quduo.welfareshop.ui.friend.entity;

/**
 * 聊天底部加号出来的东西
 */
public class AppBean {

    private int id;
    private int icon;
    private String funcName;

    public int getIcon() {
        return icon;
    }

    public String getFuncName() {
        return funcName;
    }

    public int getId() {
        return id;
    }

    public AppBean(int icon, String funcName){
        this.icon = icon;
        this.funcName = funcName;
    }
}
