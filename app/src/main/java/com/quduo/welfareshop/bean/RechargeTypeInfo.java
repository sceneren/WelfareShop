package com.quduo.welfareshop.bean;

import com.quduo.welfareshop.base.BaseBean;

/**
 * Author:scene
 * Time:2018/3/23 17:46
 * Description:充值类型
 */

public class RechargeTypeInfo extends BaseBean {

    /**
     * type : 1
     * money : 38
     * score : 38
     * gift : 38
     */

    private int type;
    private String money;
    private int score;
    private int gift;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGift() {
        return gift;
    }

    public void setGift(int gift) {
        this.gift = gift;
    }
}
