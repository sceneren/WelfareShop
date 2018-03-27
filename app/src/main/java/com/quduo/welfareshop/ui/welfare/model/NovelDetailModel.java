package com.quduo.welfareshop.ui.welfare.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.welfare.entity.FollowSuccessInfo;
import com.quduo.welfareshop.ui.welfare.entity.NovelDetailResultInfo;

import java.io.File;

/**
 * Author:scene
 * Time:2018/2/26 16:28
 * Description:小说详情
 */

public class NovelDetailModel extends FollowModel {
    public void getNovelDetailData(HttpParams params, final HttpResultListener<NovelDetailResultInfo> listener) {
        OkGo.<LzyResponse<NovelDetailResultInfo>>get(ApiUtil.API_PRE + ApiUtil.NOVEL_DETAIL)
                .tag(ApiUtil.NOVEL_DETAIL_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<NovelDetailResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<NovelDetailResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<NovelDetailResultInfo>> response) {
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

    public void downloadNovel(String fileUrl, String fileName, final HttpResultListener<String> listener) {
        try {
            OkGo.<File>get(fileUrl)
                    .tag("DOWNLOAD_TAG")
                    .execute(new FileCallback(AppConfig.NOVEL_DIR, fileName) {
                        @Override
                        public void onSuccess(Response<File> response) {
                            try {
                                listener.onSuccess(response.body().getAbsolutePath());
                            } catch (Exception e) {
                                e.printStackTrace();
                                listener.onFail("小说打开失败请重试");
                            }
                        }

                        @Override
                        public void onError(Response<File> response) {
                            super.onError(response);
                            try {
                                listener.onFail("小说打开失败请重试");
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void novelSee(HttpParams params) {
        OkGo.<LzyResponse<Boolean>>get(ApiUtil.API_PRE + ApiUtil.NOVEL_SEE)
                .tag(ApiUtil.NOVEL_SEE_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<Boolean>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<Boolean>> response) {

                    }
                });
    }

}
