package com.quduo.welfareshop.ui.mine.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.mine.entity.MyFollowVideoInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 11:10
 * Description:收藏的视频
 */

public interface IMyFollowVideoView extends BaseView {

    void bindData(List<MyFollowVideoInfo> data);

    void showMessage(String message);

    void refreshFinish();
}
