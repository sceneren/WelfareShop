package com.quduo.welfareshop.ui.friend.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.welfare.entity.UnlockResultInfo;

/**
 * Author:scene
 * Time:2018/3/22 11:19
 * Description:解锁私聊
 */

public class UnlockChatModel {
    public void unlockChat(final HttpResultListener<UnlockResultInfo> listener) {
        OkGo.<LzyResponse<UnlockResultInfo>>get(ApiUtil.API_PRE + ApiUtil.UNLOCK_CHAT)
                .tag(ApiUtil.UNLOCK_CHAT_TAG)
                .execute(new JsonCallback<LzyResponse<UnlockResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<UnlockResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("解锁失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<UnlockResultInfo>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("解锁失败请重试");
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
