package com.quduo.welfareshop.ui.welfare.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.welfare.entity.SmallVideoResultInfo;

/**
 * Author:scene
 * Time:2018/2/9 9:48
 * Description:小视频
 */

public interface ISmallVideoView extends BaseView {
    void showMessage(String msg);

    void refreshFinish();

    void loadmoreFinish();

    void bindData(SmallVideoResultInfo data);

    void showLoadingDialog();

    void hideLoadingDialog();

    void followSuccess(int position, int followId);

    void cancelFollowSuccess(int position);

    void zanSuccess(int position);
}
