package com.quduo.welfareshop.ui.welfare.entity;

/**
 * Author:scene
 * Time:2018/3/7 17:47
 * Description:banner
 */

public class BannerInfo {

    /**
     * thumb : /img/2018/03/71212304f9483f0f87fb44de6282cc54.png
     * name : 测试美女视频banner
     * id : 2
     * type : video
     */

    private String thumb;
    private String name;
    private int data_id;
    private String type;

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
