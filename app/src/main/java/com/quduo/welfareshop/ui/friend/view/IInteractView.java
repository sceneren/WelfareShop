package com.quduo.welfareshop.ui.friend.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.friend.entity.InteractResultInfo;

public interface IInteractView extends BaseView {
    void refreshFinish();

    void loadmoreFinish();

    void setHasmore(boolean hasmore);

    void bindData(InteractResultInfo data);

    void showMessage(String message);

    void showLoadingDialog();

    void hideLoadingDialog();

    void zanSuccess(int position);
}
