package com.quduo.welfareshop.bean;

import com.quduo.welfareshop.base.BaseBean;

/**
 * Author:scene
 * Time:2018/2/28 15:03
 * Description:登录返回的信息
 */

public class LoginInfo extends BaseBean {
    private ConfigInfo config;
    private UserInfo user_info;

    public ConfigInfo getConfig() {
        return config;
    }

    public void setConfig(ConfigInfo config) {
        this.config = config;
    }

    public UserInfo getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfo user_info) {
        this.user_info = user_info;
    }
}
