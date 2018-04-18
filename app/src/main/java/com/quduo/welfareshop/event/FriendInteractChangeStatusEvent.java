package com.quduo.welfareshop.event;

public class FriendInteractChangeStatusEvent {
    private int position;

    public FriendInteractChangeStatusEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
