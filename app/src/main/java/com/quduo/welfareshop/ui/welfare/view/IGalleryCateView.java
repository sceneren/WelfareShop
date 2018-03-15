package com.quduo.welfareshop.ui.welfare.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.welfare.entity.GalleryCateResultInfo;

/**
 * Author:scene
 * Time:2018/1/25  12:04
 * Description:图库分类
 */
public interface IGalleryCateView extends BaseView {
    void showMessage(String msg);

    void refreshFinish();

    void loadmoreFinish();

    void bindData(GalleryCateResultInfo data);

    int getCateId();

    void followSuccess(int position, int followId);

    void hideLoadingDialog();

    void cancelFollowSuccess(int position);

    void showLoadingDialog();
}
