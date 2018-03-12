package com.quduo.welfareshop.ui.mine.entity;

/**
 * Author:scene
 * Time:2018/3/12 15:37
 * Description:我收藏的小说
 */

public class MyFollowNovelInfo {


    /**
     * id : 5
     * thumb_shu : /novels/1/p.png
     * novel_id : 1
     * title : 余生不过我爱你
     */

    private int id;
    private String thumb_shu;
    private int novel_id;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumb_shu() {
        return thumb_shu;
    }

    public void setThumb_shu(String thumb_shu) {
        this.thumb_shu = thumb_shu;
    }

    public int getNovel_id() {
        return novel_id;
    }

    public void setNovel_id(int novel_id) {
        this.novel_id = novel_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
