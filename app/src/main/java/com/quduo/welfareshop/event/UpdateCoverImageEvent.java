package com.quduo.welfareshop.event;

/**
 * Author:scene
 * Time:2018/3/15 16:05
 * Description:修改封面
 */

public class UpdateCoverImageEvent {
    private String coverPath;

    public String getCoverPath() {
        return coverPath;
    }

    public UpdateCoverImageEvent(String coverPath) {
        this.coverPath = coverPath;
    }
}
