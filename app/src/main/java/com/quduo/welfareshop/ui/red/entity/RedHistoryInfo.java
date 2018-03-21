package com.quduo.welfareshop.ui.red.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/21 11:35
 * Description:红包历史
 */

public class RedHistoryInfo extends BaseBean {
    private PageInfo info;
    private List<RedHistoryDetailInfo> data;

    public PageInfo getInfo() {
        return info;
    }

    public void setInfo(PageInfo info) {
        this.info = info;
    }

    public List<RedHistoryDetailInfo> getData() {
        return data;
    }

    public void setData(List<RedHistoryDetailInfo> data) {
        this.data = data;
    }
}
