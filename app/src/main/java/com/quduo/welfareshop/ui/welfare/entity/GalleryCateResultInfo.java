package com.quduo.welfareshop.ui.welfare.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
 * Created by scene on 2018/3/9.
 */

public class GalleryCateResultInfo extends BaseBean {
    private int current_page;
    private List<WelfareGalleryInfo> data;
    private int last_page;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public List<WelfareGalleryInfo> getData() {
        return data;
    }

    public void setData(List<WelfareGalleryInfo> data) {
        this.data = data;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }
}
