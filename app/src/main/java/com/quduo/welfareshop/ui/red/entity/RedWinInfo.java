package com.quduo.welfareshop.ui.red.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
*Author:scene
*Time:2018/3/19 19:53
*Description:红包中奖信息
*/

public class RedWinInfo extends BaseBean{

    /**
     * bonus : 4.96
     * nickname : EM
     */

    private String bonus;
    private String nickname;

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
