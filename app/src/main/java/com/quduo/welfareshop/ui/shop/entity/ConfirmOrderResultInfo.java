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
}
