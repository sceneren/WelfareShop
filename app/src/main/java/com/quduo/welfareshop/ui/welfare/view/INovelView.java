package com.quduo.welfareshop.ui.welfare.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.welfare.entity.NovelResultInfo;

/**
 * Author:scene
 * Time:2018/2/24 11:13
 * Description:小爽文
 */

public interface INovelView extends BaseView {
    void showMessage(String msg);

    void refreshFinish();

    void bindData(NovelResultInfo data);
}
