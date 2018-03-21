package com.quduo.welfareshop.ui.red.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.red.entity.RedHistoryResultInfo;

/**
 * Author:scene
 * Time:2018/3/8 10:00
 * Description:历史记录
 */

public interface IHistoryRedView extends BaseView {
    void showMessage(String message);

    void refreshFinish();

    void loadmoreFinish();

    void bindData(RedHistoryResultInfo data);

    void setHasMore(boolean hasMore);
}
