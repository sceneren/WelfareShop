package com.quduo.welfareshop.ui.red.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
*Author:scene
*Time:2018/3/21 11:41
*Description:历史红包详情
*/

public class RedHistoryDetailInfo extends BaseBean{
    private long create_time;
    private String bonus;
    private long open_time;

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public long getOpen_time() {
        return open_time;
    }

    public void setOpen_time(long open_time) {
        this.open_time = open_time;
    }
}
