package com.quduo.welfareshop.ui.friend.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.friend.entity.HotVideoResultInfo;

public interface IHotVideoView extends BaseView {
    void showMessage(String message);

    void alert(String message);

    void refreshFinish();

    void loadmoreFinish();

    void setHasmore(boolean hasmore);

    void bindData(HotVideoResultInfo data);
}
