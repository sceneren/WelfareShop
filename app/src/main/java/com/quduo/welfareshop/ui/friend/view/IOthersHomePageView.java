package com.quduo.welfareshop.ui.friend.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.friend.entity.DynamicCommentInfo;
import com.quduo.welfareshop.ui.friend.entity.HomePageInfo;

public interface IOthersHomePageView extends BaseView {
    void bindData(HomePageInfo data);

    void showMessage(String message);

    void alert(String message);

    void refreshFinish();

    void showLoadingDialog();

    void hideLoadingDialog();

    void zanSuccess(int position);

}
