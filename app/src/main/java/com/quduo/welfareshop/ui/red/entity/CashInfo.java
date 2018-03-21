package com.quduo.welfareshop.ui.red.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
 * Author:scene
 * Time:2018/3/21 16:58
 * Description:提现信息
 */

public class CashInfo extends BaseBean {
    private double cost;
    private long time;
    private String cardString;
    private String type;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getCardString() {
        return cardString;
    }

    public void setCardString(String cardString) {
        this.cardString = cardString;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
