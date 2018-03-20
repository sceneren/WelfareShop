package com.quduo.welfareshop.ui.red.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
*Author:scene
*Time:2018/3/20 15:50
*Description:拆红包返回
*/

public class OpenRedResultInfo extends BaseBean {
    private String bonus;
    private String money;

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
