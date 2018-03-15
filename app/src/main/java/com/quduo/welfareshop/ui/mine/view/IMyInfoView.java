package com.quduo.welfareshop.ui.mine.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.mine.entity.MyUserDetailInfo;

/**
 * Author:scene
 * Time:2018/2/2 15:46
 * Description:我的资料
 */

public interface IMyInfoView extends BaseView {
    void showMessage(String message);

    void refreshFinish();

    void bindData(MyUserDetailInfo data);
}
