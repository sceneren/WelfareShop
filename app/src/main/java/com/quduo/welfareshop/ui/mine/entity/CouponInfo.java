package com.quduo.welfareshop.ui.mine.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
 * Author:scene
 * Time:2018/3/23 11:30
 * Description:我的代金券
 */
public class CouponInfo extends BaseBean {

    /**
     * id : 1
     * user_id : 3
     * cost : 38.00
     * order_id : 1
     * status : 1
     */

    private int id;
    private int user_id;
    private int cost;
    private int order_id;
    private int status;//1-未使用，2-锁定中，3-已使用，4-已过期
    private long create_time;
    private long express_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public long getExpress_time() {
        return express_time;
    }

    public void setExpress_time(long express_time) {
        this.express_time = express_time;
    }
}
