package com.quduo.welfareshop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.entity.MyUserDetailInfo;
import com.quduo.welfareshop.ui.mine.entity.UploadAvatarResultInfo;
import com.quduo.welfareshop.ui.mine.model.EditMyInfoModel;
import com.quduo.welfareshop.ui.mine.view.IEditMyInfoView;

import java.io.File;
import java.util.List;

/**
 * Author:scene
 * Time:2018/2/2 16:38
 * Description:编辑资料
 */

public class EditMyInfoPresenter extends BasePresenter<IEditMyInfoView> {
    private EditMyInfoModel model;

    public EditMyInfoPresenter(IEditMyInfoView view) {
        super(view);
        model = new EditMyInfoModel();
    }

    public void uploadAvatar(File avatarFile) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("files", avatarFile);
            model.uploadAvatar(params, new HttpResultListener<UploadAvatarResultInfo>() {
                @Override
                public void onSuccess(UploadAvatarResultInfo data) {
                    try {
                        mView.uploadAvaterSuccess(data);
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

    public void updateMyInfo() {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("nickname", mView.getNickName());
            params.put("sex", mView.getSex());
            params.put("weight", mView.getMyWeight());
            params.put("height", mView.getMyHeight());
            params.put("weixin", mView.getWechatNum());
            params.put("mobile", mView.getPhoneNum());
            params.put("job", mView.getJob());
            params.put("blood_type", mView.getBloodType());
            params.put("marital", mView.getEmotion());
            params.put("birthday", mView.getBirthday());
            params.put("signature_text", mView.getDes());
            model.updateMyInfo(params, new HttpResultListener<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    try {
                        mView.updateMyInfoSuccess();
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
