package com.quduo.welfareshop.ui.welfare.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.welfare.entity.SmallVideoResultInfo;
import com.quduo.welfareshop.ui.welfare.entity.VideoInfo;

import java.util.List;

public interface ISelfieView extends BaseView {
    void bindData(SmallVideoResultInfo data);

    void refreshFinish();

    void loadMoreFinish();

    void setHasmore(boolean hasmore);

    void showLoadingDialog();

    void hideLoadingDialog();

    void showMessage(String message);

    void alert(String message,int position);

    void unlockSuccess(int position,int score);
}
