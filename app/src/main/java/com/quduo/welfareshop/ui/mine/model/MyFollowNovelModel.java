package com.quduo.welfareshop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.mine.entity.MyFollowNovelInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 11:13
 * Description:收藏的小说
 */

public class MyFollowNovelModel {
    public void getMyFollowNovelData(final HttpResultListener<List<MyFollowNovelInfo>> listener) {
        OkGo.<LzyResponse<List<MyFollowNovelInfo>>>get(ApiUtil.API_PRE + ApiUtil.MY_FOLLOW_NOVEL)
                .tag(ApiUtil.MY_FOLLOW_NOVEL_TAG)
                .execute(new JsonCallback<LzyResponse<List<MyFollowNovelInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<MyFollowNovelInfo>>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<MyFollowNovelInfo>>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
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
