package com.quduo.welfareshop.ui.welfare.presenter;

import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.entity.NovelResultInfo;
import com.quduo.welfareshop.ui.welfare.model.NovelModel;
import com.quduo.welfareshop.ui.welfare.view.INovelView;

/**
 * Author:scene
 * Time:2018/2/24 11:14
 * Description:小爽文
 */

public class NovelPresenter extends BasePresenter<INovelView> {
    private NovelModel model;

    public NovelPresenter(INovelView view) {
        this.mView = view;
        this.model = new NovelModel();
    }

    public void getNovelListData(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            model.getNovelListData(new HttpResultListener<NovelResultInfo>() {

                @Override
                public void onSuccess(NovelResultInfo data) {
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
