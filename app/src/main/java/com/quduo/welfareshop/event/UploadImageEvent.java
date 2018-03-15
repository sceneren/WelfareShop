package com.quduo.welfareshop.event;

import com.quduo.welfareshop.ui.mine.entity.MyUserDetailInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/15 16:09
 * Description:上传图片
 */

public class UploadImageEvent {
    private List<MyUserDetailInfo.PhotosBean> photosBeanList;

    public UploadImageEvent(List<MyUserDetailInfo.PhotosBean> photosBeanList) {
        this.photosBeanList = photosBeanList;
    }

    public List<MyUserDetailInfo.PhotosBean> getPhotosBeanList() {
        return photosBeanList;
    }
}
