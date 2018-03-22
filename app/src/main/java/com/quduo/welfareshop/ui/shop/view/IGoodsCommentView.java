package com.quduo.welfareshop.ui.shop.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.shop.entity.GoodsCommentResultInfo;

/**
*Author:scene
*Time:2018/2/28 15:41
*Description:买家秀
*/

public interface IGoodsCommentView extends BaseView{
    void bindData(GoodsCommentResultInfo data);

    void showMessage(String message);

    void refreshFinish();

    void loadmoreFinish();

    void hasLoadmore(boolean hasMore);

    int getGoodsId();
}
