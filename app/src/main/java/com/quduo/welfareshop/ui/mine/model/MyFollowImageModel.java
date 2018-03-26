package com.quduo.welfareshop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.mine.entity.MyFollowGalleryInfo;
import com.quduo.welfareshop.ui.welfare.model.FollowModel;
import com.quduo.welfareshop.ui.welfare.model.ZanModel;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 11:13
 * Description:收藏的图片
 */

public class MyFollowImageModel extends ZanModel {

    public void getData(final HttpResultListener<List<MyFollowGalleryInfo>> listener) {
        OkGo.<LzyResponse<List<MyFollowGalleryInfo>>>get(ApiUtil.API_PRE + ApiUtil.MY_FOLLOW_IMAGE)
                .tag(ApiUtil.MY_FOLLOW_IMAGE_TAG)
                .execute(new JsonCallback<LzyResponse<List<MyFollowGalleryInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<MyFollowGalleryInfo>>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<MyFollowGalleryInfo>>> response) {
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
