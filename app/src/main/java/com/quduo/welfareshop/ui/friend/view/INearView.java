package com.quduo.welfareshop.ui.friend.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.friend.entity.NearInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/1 17:07
 * Description:附近的人
 */

public interface INearView extends BaseView {
    void bindData(List<NearInfo> data);

    double getLongitude();

    double getLatitude();

    int getSex();

    void refreshFinish();

    void showMessage(String message);
}
