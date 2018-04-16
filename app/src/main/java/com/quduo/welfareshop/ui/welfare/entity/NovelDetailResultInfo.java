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
    private List<NovelCommentInfo> comments;

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

    public List<NovelCommentInfo> getComments() {
        return comments;
    }

    public void setComments(List<NovelCommentInfo> comments) {
        this.comments = comments;
    }
}
