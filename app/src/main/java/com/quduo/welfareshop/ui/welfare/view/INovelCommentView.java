package com.quduo.welfareshop.ui.welfare.view;

import com.quduo.welfareshop.mvp.BaseView;

public interface INovelCommentView extends BaseView {
    void showMessage(String message);

    void refreshFinish();

    void bindData();
}
