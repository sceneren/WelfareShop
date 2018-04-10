package com.quduo.welfareshop.bean;

import com.quduo.welfareshop.base.BaseBean;

/**
 * Author:scene
 * Time:2018/2/28 14:56
 * Description:服务器返回的配置信息
 */

public class ConfigInfo extends BaseBean {

    /**
     * user_agreement :
     * intro : 玩法说明
     * file_domain : http://192.168.0.88:9092
     * withdraw_fee : 2
     * server_time : 1519800912
     * ios_version : 1.0
     */

    private String user_agreement;
    private String intro;
    private String file_domain;
    private int withdraw_fee;
    private long server_time;
    private String ios_version;
    private int chat_price;
    private String service_url;

    public String getUser_agreement() {
        return user_agreement;
    }

    public void setUser_agreement(String user_agreement) {
        this.user_agreement = user_agreement;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getFile_domain() {
        return file_domain;
    }

    public void setFile_domain(String file_domain) {
        this.file_domain = file_domain;
    }

    public int getWithdraw_fee() {
        return withdraw_fee;
    }

    public void setWithdraw_fee(int withdraw_fee) {
        this.withdraw_fee = withdraw_fee;
    }

    public long getServer_time() {
        return server_time;
    }

    public void setServer_time(long server_time) {
        this.server_time = server_time;
    }

    public String getIos_version() {
        return ios_version;
    }

    public void setIos_version(String ios_version) {
        this.ios_version = ios_version;
    }

    public int getChat_price() {
        return chat_price;
    }

    public void setChat_price(int chat_price) {
        this.chat_price = chat_price;
    }

    public String getService_url() {
        return service_url;
    }

    public void setService_url(String service_url) {
        this.service_url = service_url;
    }
}
