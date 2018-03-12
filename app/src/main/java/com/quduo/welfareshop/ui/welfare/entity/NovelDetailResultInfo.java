package com.quduo.welfareshop.ui.welfare.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/12 10:05
 * Description:视频详情
 */

public class NovelDetailResultInfo extends BaseBean {
    private NovelDetailInfo data;
    private List<NovelChapterInfo> chapters;

    public NovelDetailInfo getData() {
        return data;
    }

    public void setData(NovelDetailInfo data) {
        this.data = data;
    }

    public List<NovelChapterInfo> getChapters() {
        return chapters;
    }

    public void setChapters(List<NovelChapterInfo> chapters) {
        this.chapters = chapters;
    }
}
