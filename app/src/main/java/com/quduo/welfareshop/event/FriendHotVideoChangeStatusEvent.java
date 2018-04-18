package com.quduo.welfareshop.event;

public class FriendHotVideoChangeStatusEvent {
    private int position;

    public FriendHotVideoChangeStatusEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
