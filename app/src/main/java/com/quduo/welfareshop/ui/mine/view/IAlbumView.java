package com.quduo.welfareshop.ui.mine.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.mine.entity.MyUserDetailInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/19 9:46
 * Description:相册
 */

public interface IAlbumView extends BaseView {
    void showLoadingDialog();

    void hideLoadingDialog();

    void showMessage(String message);

    void uploadPhotoSuccess(List<MyUserDetailInfo.PhotosBean> data);

    void deletePhotoSuccess(int position);

    void setCoverImageSuccess(int position);
}
