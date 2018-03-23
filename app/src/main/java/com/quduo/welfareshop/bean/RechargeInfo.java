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
}
