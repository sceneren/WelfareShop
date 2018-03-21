package com.quduo.welfareshop.ui.welfare.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.welfare.entity.VideoDetailInfo;

/**
 * Author:scene
 * Time:2018/2/27 12:13
 * Description:视频详情
 */

public interface IVideoDetailView extends BaseView {
    void refreshFinish();

    void showLoadingDialog();

    void hideLoadingDialog();

    void showMessage(String message);

    void bindData(VideoDetailInfo data);

    int getVideoId();

    int getCateId();

    void zanSuccess();

    void followSuccess(int followId);

    void cancelFollowSuccess();

    void unlockSuccess(int currentScore);
}
