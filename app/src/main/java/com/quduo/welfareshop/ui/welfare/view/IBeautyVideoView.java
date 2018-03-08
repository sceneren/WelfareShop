package com.quduo.welfareshop.ui.welfare.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.welfare.entity.BeautyVideoResultInfo;

/**
 * Author:scene
 * Time:2018/2/23 15:03
 * Description:美女视频
 */
public interface IBeautyVideoView extends BaseView {
    void showMessage(String msg);

    void refreshFinish();

    void bindData(BeautyVideoResultInfo data);
}
