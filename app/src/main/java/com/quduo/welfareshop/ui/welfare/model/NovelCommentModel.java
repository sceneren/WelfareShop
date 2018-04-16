package com.quduo.welfareshop.ui.welfare.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.welfare.entity.NovelCommentInfo;

import java.util.List;

public class NovelCommentModel {
    public void getData(HttpParams params, final HttpResultListener<List<NovelCommentInfo>> listener){
        OkGo.<LzyResponse<List<NovelCommentInfo>>>get(ApiUtil.API_PRE+ApiUtil.NOVEL_COMMENT)
                .tag(ApiUtil.NOVEL_COMMENT_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<List<NovelCommentInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<NovelCommentInfo>>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据加载失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<NovelCommentInfo>>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据加载失败请重试");
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
