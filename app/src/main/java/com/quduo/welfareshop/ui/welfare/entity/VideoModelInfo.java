package com.quduo.welfareshop.ui.welfare.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:scene
 * Time:2018/2/23 16:19
 * Description:美女视频模块 (岛国写真)
 */

public class VideoModelInfo implements Serializable {
    private List<VideoTypeInfo> positions;
    private String name;

    public List<VideoTypeInfo> getPositions() {
        return positions;
    }

    public void setPositions(List<VideoTypeInfo> positions) {
        this.positions = positions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
