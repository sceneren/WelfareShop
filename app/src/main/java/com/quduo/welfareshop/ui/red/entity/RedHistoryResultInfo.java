package com.quduo.welfareshop.ui.red.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
*Author:scene
*Time:2018/3/21 11:33
*Description:红包历史
*/

public class RedHistoryResultInfo extends BaseBean {
    private String total_win;
    private String best_times;
    private RedHistoryInfo history;

    public String getTotal_win() {
        return total_win;
    }

    public void setTotal_win(String total_win) {
        this.total_win = total_win;
    }

    public String getBest_times() {
        return best_times;
    }

    public void setBest_times(String best_times) {
        this.best_times = best_times;
    }

    public RedHistoryInfo getHistory() {
        return history;
    }

    public void setHistory(RedHistoryInfo history) {
        this.history = history;
    }
}
