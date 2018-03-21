package com.quduo.welfareshop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.mine.entity.MyFollowVideoInfo;
import com.quduo.welfareshop.ui.welfare.model.FollowModel;
import com.quduo.welfareshop.ui.welfare.model.UnlockModel;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 11:13
 * Description:收藏的视频
 */

public class MyFollowVideoModel extends UnlockModel{

    public void getData(final HttpResultListener<List<MyFollowVideoInfo>> listener) {
        OkGo.<LzyResponse<List<MyFollowVideoInfo>>>get(ApiUtil.API_PRE + ApiUtil.MY_FOLLOW_VIDEO)
                .tag(ApiUtil.MY_FOLLOW_VIDEO_TAG)
                .execute(new JsonCallback<LzyResponse<List<MyFollowVideoInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<MyFollowVideoInfo>>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<MyFollowVideoInfo>>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
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
