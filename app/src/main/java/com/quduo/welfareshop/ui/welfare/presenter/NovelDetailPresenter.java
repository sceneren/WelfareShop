package com.quduo.welfareshop.ui.welfare.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.entity.NovelDetailResultInfo;
import com.quduo.welfareshop.ui.welfare.model.NovelDetailModel;
import com.quduo.welfareshop.ui.welfare.view.INovelDetailView;

/**
 * Author:scene
 * Time:2018/2/26 16:29
 * Description:小说详情
 */

public class NovelDetailPresenter extends BasePresenter<INovelDetailView> {
    private NovelDetailModel model;

    public NovelDetailPresenter(INovelDetailView view) {
        this.mView = view;
        this.model = new NovelDetailModel();
    }

    public void getNovelDetailData(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("novel_id", mView.getNovelId());
            model.getNovelDetailData(params, new HttpResultListener<NovelDetailResultInfo>() {
                @Override
                public void onSuccess(NovelDetailResultInfo data) {
                    try {
                        mView.bindData(data);
                        if (isFirst) {
                            mView.showContentPage();
                        } else {
                            mView.refreshFinish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showMessage(message);
                        if (isFirst) {
                            mView.showErrorPage();
                        } else {
                            mView.refreshFinish();
                        }
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
