package com.quduo.welfareshop.event;

public class UnreadEvent {
    private int count;

    public UnreadEvent(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
