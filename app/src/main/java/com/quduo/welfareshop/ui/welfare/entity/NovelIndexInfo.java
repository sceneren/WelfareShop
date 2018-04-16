package com.quduo.welfareshop.ui.welfare.entity;

import java.io.Serializable;
import java.util.List;

public class NovelIndexInfo implements Serializable {
    private List<NovelInfo> data;
    private int current_page;
    private int last_page;

    public List<NovelInfo> getData() {
        return data;
    }

    public void setData(List<NovelInfo> data) {
        this.data = data;
    }

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
}
