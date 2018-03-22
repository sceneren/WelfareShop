package com.quduo.welfareshop.ui.shop.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.shop.entity.ShopResultInfo;

/**
 * 商城主界面
 * Created by scene on 2018/1/25.
 */

public interface IShopView extends BaseView {
    void bindData(ShopResultInfo data);

    void showMessage(String message);

    void refreshFinish();

    void loadmoreFinish();

    void hasLoadmore(boolean hasMore);
}
