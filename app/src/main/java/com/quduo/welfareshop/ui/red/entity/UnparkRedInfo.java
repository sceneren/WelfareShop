package com.quduo.welfareshop.ui.red.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
*Author:scene
*Time:2018/3/20 14:45
*Description:未拆开的红包
*/

public class UnparkRedInfo extends BaseBean {

    /**
     * id : 8932
     * period_id : 4
     * user_id : 2
     * user_type : 1
     * bonus : 0.00
     * status : 1-未开奖 2-已开奖 3-已拆开
     * create_time : 1521516176
     * create_date : 20180320
     */

    private int id;
    private int period_id;
    private int user_id;
    private int user_type;
    private String bonus;
    private int status;
    private int create_time;
    private int create_date;
    private String pool;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeriod_id() {
        return period_id;
    }

    public void setPeriod_id(int period_id) {
        this.period_id = period_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getCreate_date() {
        return create_date;
    }

    public void setCreate_date(int create_date) {
        this.create_date = create_date;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }
}
