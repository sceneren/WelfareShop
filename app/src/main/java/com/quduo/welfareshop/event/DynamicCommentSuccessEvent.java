package com.quduo.welfareshop.event;

import com.quduo.welfareshop.ui.friend.entity.DynamicCommentInfo;

public class DynamicCommentSuccessEvent {
    private int position;
    private DynamicCommentInfo commentInfo;

    public DynamicCommentSuccessEvent(int position, DynamicCommentInfo commentInfo) {
        this.position = position;
        this.commentInfo = commentInfo;
    }

    public int getPosition() {
        return position;
    }

    public DynamicCommentInfo getCommentInfo() {
        return commentInfo;
    }
}
