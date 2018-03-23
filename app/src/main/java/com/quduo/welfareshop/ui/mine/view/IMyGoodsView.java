package com.quduo.welfareshop.ui.mine.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.mine.entity.MyFollowGoodsInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 10:38
 * Description:我收藏的商品
 */

public interface IMyGoodsView extends BaseView {
    void bindData(List<MyFollowGoodsInfo> data);

    void showMessage(String message);

    void refreshFinish();
}
