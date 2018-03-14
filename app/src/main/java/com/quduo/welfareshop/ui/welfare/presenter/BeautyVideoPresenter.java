package com.quduo.welfareshop.ui.welfare.presenter;

import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.entity.BeautyVideoResultInfo;
import com.quduo.welfareshop.ui.welfare.model.BeautyVideoModel;
import com.quduo.welfareshop.ui.welfare.view.IBeautyVideoView;

/**
 * Author:scene
 * Time:2018/2/23 15:03
 * Description:美女视频
 */
public class BeautyVideoPresenter extends BasePresenter<IBeautyVideoView> {
    private BeautyVideoModel model;

    public BeautyVideoPresenter(IBeautyVideoView view) {
        super(view);
        this.model = new BeautyVideoModel();
    }

    public void getBeautyVideoData(final boolean isFirst){
        try {
            if(isFirst){
                mView.showLoadingPage();
            }
            model.getBeautyVideoData(new HttpResultListener<BeautyVideoResultInfo>() {
                @Override
                public void onSuccess(BeautyVideoResultInfo data) {
                    try {
                        if (isFirst) {
                            mView.showContentPage();
                        } else {
                            mView.refreshFinish();
                        }
                        mView.bindData(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        if (isFirst) {
                            mView.showErrorPage();
                        } else {
                            mView.refreshFinish();
                        }
                        mView.showMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
