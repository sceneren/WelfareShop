package com.quduo.welfareshop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.entity.MyUserDetailInfo;
import com.quduo.welfareshop.ui.mine.model.AlbumModel;
import com.quduo.welfareshop.ui.mine.view.IAlbumView;

import java.io.File;
import java.util.List;

/**
 * Author:scene
 * Time:2018/3/19 9:46
 * Description:相册
 */

public class AlbumPresenter extends BasePresenter<IAlbumView> {
    private AlbumModel model;

    public AlbumPresenter(IAlbumView view) {
        super(view);
        model = new AlbumModel();
    }

    public void uploadPhoto(List<File> photos) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.putFileParams("files[]", photos);
            model.uploadPhoto(params, new HttpResultListener<List<MyUserDetailInfo.PhotosBean>>() {
                @Override
                public void onSuccess(List<MyUserDetailInfo.PhotosBean> data) {
                    try {
                        mView.uploadPhotoSuccess(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        mView.hideLoadingDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePhoto(final int position, int photoId) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("photo_id", photoId);
            model.deleteImage(params, new HttpResultListener<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    try {
                        mView.deletePhotoSuccess(position);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        mView.hideLoadingDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
