package com.quduo.welfareshop.ui.friend.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.friend.entity.NearResultInfo;
import com.quduo.welfareshop.ui.friend.entity.OtherSimpleUserInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/1 17:07
 * Description:附近的人
 */

public interface INearView extends BaseView {
    void bindData(NearResultInfo data);

    double getLongitude();

    double getLatitude();

    int getSex();

    void refreshFinish();

    void showMessage(String message);

    void setHasMore(boolean hasMore);

    void loadmoreFinish();
}
