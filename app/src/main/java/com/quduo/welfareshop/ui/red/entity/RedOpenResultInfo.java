package com.quduo.welfareshop.ui.red.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/21 12:48
 * Description:开奖结果
 */

public class RedOpenResultInfo extends BaseBean {
    private List<RedOtherResultInfo> bonus;
    private RedMyResultInfo data;

    public List<RedOtherResultInfo> getBonus() {
        return bonus;
    }

    public void setBonus(List<RedOtherResultInfo> bonus) {
        this.bonus = bonus;
    }

    public RedMyResultInfo getData() {
        return data;
    }

    public void setData(RedMyResultInfo data) {
        this.data = data;
    }
}
