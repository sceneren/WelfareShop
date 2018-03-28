package com.quduo.welfareshop.ui.shop.entity;

import com.quduo.welfareshop.base.BaseBean;
import com.quduo.welfareshop.ui.mine.entity.CouponInfo;
import com.quduo.welfareshop.ui.mine.entity.ReceiverInfo;

/**
 * Author:scene
 * Time:2018/3/23 11:42
 * Description:确认订单返回信息
 */

public class ConfirmOrderResultInfo extends BaseBean {
    private CouponInfo coupon;
    private ReceiverInfo address;
    private boolean wx_pay_enable;
    private boolean ali_pay_enable;

    public CouponInfo getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponInfo coupon) {
        this.coupon = coupon;
    }

    public ReceiverInfo getAddress() {
        return address;
    }

    public void setAddress(ReceiverInfo address) {
        this.address = address;
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
