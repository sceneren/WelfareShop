package com.quduo.welfareshop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.mine.entity.UploadAvatarResultInfo;

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
}
