package com.quduo.welfareshop.ui.welfare.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.welfare.entity.GalleryResultInfo;
import com.quduo.welfareshop.ui.welfare.entity.MidNightVideoResultInfo;

/**
 * Author:scene
 * Time:2018/1/25  12:04
 * Description:美女图库
 */
public interface IGalleryView extends BaseView {
    void showMessage(String msg);

    void refreshFinish();

    void loadmoreFinish();

    void bindData(GalleryResultInfo data);
}
