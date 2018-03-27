package com.quduo.welfareshop.bean;

import com.quduo.welfareshop.base.BaseBean;

/**
 * Author:scene
 * Time:2018/3/27 14:27
 * Description:版本信息
 */
public class VersionInfo extends BaseBean {

    /**
     * url : http://192.168.0.88:9092/android/fuli_1001_1.apk
     * version : 1
     */

    private String url;
    private int version;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
