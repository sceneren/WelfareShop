package com.quduo.welfareshop.ui.shop.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.shop.entity.GoodsDetailResultInfo;

/**
 * Author:scene
 * Time:2018/2/27 15:14
 * Description:商品详情
 */

public interface IGoodsDetailView extends BaseView {
    void showLoadingDialog();

    void hideLoadingDialog();

    void showMessage(String message);

    void bindData(GoodsDetailResultInfo data);

    void refreshFinish();

    int getGoodsId();

    void followGoodsSuccess(int followId);

    void cancelFollowSuccess();
}
