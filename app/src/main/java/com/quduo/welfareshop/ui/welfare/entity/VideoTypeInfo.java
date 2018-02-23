package com.quduo.welfareshop.ui.welfare.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:scene
 * Time:2018/2/23 16:19
 * Description:美女视频模块 排版 横图大图竖图分类
 */

public class VideoTypeInfo implements Serializable {
    private List<VideoInfo> list;
    private int type;

    public List<VideoInfo> getList() {
        return list;
    }

    public void setList(List<VideoInfo> list) {
        this.list = list;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
