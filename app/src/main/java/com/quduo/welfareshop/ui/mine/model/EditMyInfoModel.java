package com.quduo.welfareshop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.mine.entity.MyUserDetailInfo;
import com.quduo.welfareshop.ui.mine.entity.UploadAvatarResultInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/2 16:38
 * Description:编辑资料
 */

public class EditMyInfoModel {
    public void uploadAvatar(HttpParams params, final HttpResultListener<UploadAvatarResultInfo> listener) {
        OkGo.<LzyResponse<UploadAvatarResultInfo>>post(ApiUtil.API_PRE + ApiUtil.UPLOAD_AVATAR)
                .tag(ApiUtil.UPLOAD_AVATAR_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<UploadAvatarResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<UploadAvatarResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("头像上传失败，请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<UploadAvatarResultInfo>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("头像上传失败，请重试");
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }

    public void uploadPhoto(HttpParams params, final HttpResultListener<List<MyUserDetailInfo.PhotosBean>> listener) {
        OkGo.<LzyResponse<List<MyUserDetailInfo.PhotosBean>>>post(ApiUtil.API_PRE + ApiUtil.UPLOAD_PHOTO)
                .tag(ApiUtil.UPLOAD_PHOTO_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<List<MyUserDetailInfo.PhotosBean>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<MyUserDetailInfo.PhotosBean>>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("图片上传失败，请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<MyUserDetailInfo.PhotosBean>>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("图片上传失败，请重试");
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }

    public void deleteImage(HttpParams params, final HttpResultListener<Boolean> listener) {
        OkGo.<LzyResponse<Boolean>>get(ApiUtil.API_PRE + ApiUtil.DELETE_PHOTO)
                .tag(ApiUtil.DELETE_PHOTO_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<Boolean>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<Boolean>> response) {
                        try {
                            if (response.body().status) {
                                listener.onSuccess(true);
                            } else {
                                listener.onFail("删除失败请重试");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<Boolean>> response) {
                        super.onError(response);
                        try {
                            listener.onFail("删除失败请重试");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }

}
