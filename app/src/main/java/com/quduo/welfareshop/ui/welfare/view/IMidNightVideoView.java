package com.quduo.welfareshop.ui.welfare.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.welfare.entity.BeautyVideoResultInfo;
import com.quduo.welfareshop.ui.welfare.entity.MidNightVideoResultInfo;
import com.quduo.welfareshop.ui.welfare.entity.SmallVideoResultInfo;

/**
 * Author:scene
 * Time:2018/2/24 10:09
 * Description:午夜影院
 */

public interface IMidNightVideoView extends BaseView {
    void showMessage(String msg);

    void refreshFinish();

    void bindData(MidNightVideoResultInfo data);
}
