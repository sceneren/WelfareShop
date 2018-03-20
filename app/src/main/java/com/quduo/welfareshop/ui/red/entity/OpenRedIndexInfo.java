package com.quduo.welfareshop.ui.red.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
*Author:scene
*Time:2018/3/19 18:49
*Description:红包首页的开奖信息
*/

public class OpenRedIndexInfo extends BaseBean {

    /**
     * period : 20180319113
     * pool : 26709
     * real_pool : 0
     * start_time : 1521456000
     * stop_time : 1521456540
     * open_time : 1521456600
     * status : 1
     * id : 6
     */

    private long period;
    private int pool;
    private int real_pool;
    private long start_time;
    private long stop_time;
    private long open_time;
    private int status;
    private int id;

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public int getPool() {
        return pool;
    }

    public void setPool(int pool) {
        this.pool = pool;
    }

    public int getReal_pool() {
        return real_pool;
    }

    public void setReal_pool(int real_pool) {
        this.real_pool = real_pool;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getStop_time() {
        return stop_time;
    }

    public void setStop_time(long stop_time) {
        this.stop_time = stop_time;
    }

    public long getOpen_time() {
        return open_time;
    }

    public void setOpen_time(long open_time) {
        this.open_time = open_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
