package com.quduo.welfareshop.event;

public class FriendOtherUserInfoChangeStatusEvent {
    private int position;

    public FriendOtherUserInfoChangeStatusEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
