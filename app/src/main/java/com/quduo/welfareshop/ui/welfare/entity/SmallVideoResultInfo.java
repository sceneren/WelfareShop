package com.quduo.welfareshop.ui.welfare.entity;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/7 16:31
 * Description:小视频
 */

public class SmallVideoResultInfo {
    private int current_page;
    private int last_page;
    private List<VideoInfo> data;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public List<VideoInfo> getData() {
        return data;
    }

    public void setData(List<VideoInfo> data) {
        this.data = data;
    }
}
