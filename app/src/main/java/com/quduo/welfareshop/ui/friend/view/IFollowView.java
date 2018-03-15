package com.quduo.welfareshop.ui.friend.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.friend.entity.FollowUserInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/1 17:06
 * Description:我的关注
 */
public interface IFollowView extends BaseView {
    void bindData(List<FollowUserInfo> data);

    void showMessage(String message);

    void refreshFinish();
}
