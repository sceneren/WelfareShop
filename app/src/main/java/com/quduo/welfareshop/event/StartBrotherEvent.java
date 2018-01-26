package com.quduo.welfareshop.event;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Case By: 启动下一级的Event
 * package:
 * Author：scene on 2017/6/26 14:08
 */
public class StartBrotherEvent {
    public SupportFragment targetFragment;

    public StartBrotherEvent(SupportFragment targetFragment) {
        this.targetFragment = targetFragment;
    }
}
