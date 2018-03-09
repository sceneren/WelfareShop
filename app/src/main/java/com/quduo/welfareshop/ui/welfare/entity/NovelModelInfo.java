package com.quduo.welfareshop.ui.welfare.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:scene
 * Time:2018/2/24 11:58
 * Description:小说模块 大图  竖图  广告
 */

public class NovelModelInfo implements Serializable {
    private String name;
    private int type;
    private List<NovelInfo> novel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<NovelInfo> getNovel() {
        return novel;
    }

    public void setNovel(List<NovelInfo> novel) {
        this.novel = novel;
    }
}
