package com.quduo.welfareshop.event;

public class ChangeCouponStatusEvent {
    private boolean needShow;

    public ChangeCouponStatusEvent(boolean needShow) {
        this.needShow = needShow;
    }

    public boolean isNeedShow() {
        return needShow;
    }
}
