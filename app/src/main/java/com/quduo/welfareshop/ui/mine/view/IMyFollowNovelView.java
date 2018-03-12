package com.quduo.welfareshop.ui.mine.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.mine.entity.MyFollowNovelInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 11:10
 * Description:收藏的小说
 */

public interface IMyFollowNovelView extends BaseView {
    void refreshFinish();

    void bindData(List<MyFollowNovelInfo> list);
}
