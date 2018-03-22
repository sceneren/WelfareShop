package com.quduo.welfareshop.ui.shop.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.shop.entity.ShopDataInfo;

/**
 * Author:scene
 * Time:2018/3/22 14:28
 * Description:商品分类
 */

public interface ICateView extends BaseView {
    void bindData(ShopDataInfo data);

    void showMessage(String message);

    void refreshFinish();

    void loadmoreFinish();

    void hasLoadmore(boolean hasMore);

    int getCateId();
}
