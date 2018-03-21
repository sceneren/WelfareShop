package com.quduo.welfareshop.ui.red.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/19 18:48
 * Description:红包返回值
 */

public class RedResultInfo extends BaseBean {
    private OpenRedIndexInfo period;
    private List<RedWinInfo> win;
    private List<RedBuyInfo> buy;

    public OpenRedIndexInfo getPeriod() {
        return period;
    }

    public void setPeriod(OpenRedIndexInfo period) {
        this.period = period;
    }

    public List<RedWinInfo> getWin() {
        return win;
    }

    public void setWin(List<RedWinInfo> win) {
        this.win = win;
    }

    public List<RedBuyInfo> getBuy() {
        return buy;
    }

    public void setBuy(List<RedBuyInfo> buy) {
        this.buy = buy;
    }
}
