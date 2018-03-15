package com.quduo.welfareshop.event;

/**
 * Author:scene
 * Time:2018/2/6 13:07
 * Description:关注的时间传递
 */

public class FollowEvent {
    private int followId;

    public FollowEvent(int followId) {
        this.followId = followId;
    }

    public int getFollowId() {
        return followId;
    }
}
