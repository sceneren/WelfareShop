package com.quduo.welfareshop.ui.welfare.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:scene
 * Time:2018/2/23 16:19
 * Description:美女视频模块 (岛国写真)
 */

public class VideoModelInfo implements Serializable {
    private List<VideoTypeInfo> list;
    private String  title;

    public List<VideoTypeInfo> getList() {
        return list;
    }

    public void setList(List<VideoTypeInfo> list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
