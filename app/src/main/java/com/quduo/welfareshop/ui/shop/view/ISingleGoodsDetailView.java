package com.quduo.welfareshop.ui.shop.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.shop.entity.SingleGoodsDetailResultInfo;

public interface ISingleGoodsDetailView extends BaseView {
    void showMessage(String message);

    void showLoadingDialog();

    void hideLoadingDialog();

    void followGoodsSuccess(int followId);

    void cancelFollowSuccess();

    int getGoodsId();

    void bindData(SingleGoodsDetailResultInfo data);
}
