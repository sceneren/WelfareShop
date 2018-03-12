package com.quduo.welfareshop.ui.welfare.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.welfare.entity.NovelDetailResultInfo;

/**
 * Author:scene
 * Time:2018/2/26 16:29
 * Description:小说详情
 */

public interface INovelDetailView extends BaseView {
    int getNovelId();

    void showMessage(String msg);

    void bindData(NovelDetailResultInfo detailInfo);

    void refreshFinish();
}
