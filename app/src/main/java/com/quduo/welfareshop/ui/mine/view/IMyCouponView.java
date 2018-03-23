package com.quduo.welfareshop.ui.mine.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.mine.entity.CouponInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 13:47
 * Description:我的代金券
 */

public interface IMyCouponView extends BaseView {
    void showMessage(String message);

    void bindData(List<CouponInfo> data);

    void refreshFinish();
}
