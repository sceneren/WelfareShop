package com.quduo.welfareshop.bean;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/23 17:09
 * Description:充值首页返回的信息
 */

public class RechargeInfo extends BaseBean {
    private List<RechargeTypeInfo> score_recharge_type;
    private int score;
    private boolean wx_pay_enable;
    private boolean ali_pay_enable;

    public List<RechargeTypeInfo> getScore_recharge_type() {
        return score_recharge_type;
    }

    public void setScore_recharge_type(List<RechargeTypeInfo> score_recharge_type) {
        this.score_recharge_type = score_recharge_type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isWx_pay_enable() {
        return wx_pay_enable;
    }

    public void setWx_pay_enable(boolean wx_pay_enable) {
        this.wx_pay_enable = wx_pay_enable;
    }

    public boolean isAli_pay_enable() {
        return ali_pay_enable;
    }

    public void setAli_pay_enable(boolean ali_pay_enable) {
        this.ali_pay_enable = ali_pay_enable;
    }
}
