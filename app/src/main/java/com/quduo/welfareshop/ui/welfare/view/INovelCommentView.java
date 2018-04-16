package com.quduo.welfareshop.ui.welfare.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.welfare.entity.NovelCommentInfo;

import java.util.List;

public interface INovelCommentView extends BaseView {
    void showMessage(String message);

    void refreshFinish();

    void bindData(List<NovelCommentInfo> data);

    int getNovelId();
}
