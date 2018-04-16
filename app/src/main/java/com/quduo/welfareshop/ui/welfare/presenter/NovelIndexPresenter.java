package com.quduo.welfareshop.ui.welfare.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.entity.NovelIndexResultInfo;
import com.quduo.welfareshop.ui.welfare.model.NovelIndexModel;
import com.quduo.welfareshop.ui.welfare.view.INovelIndexView;

/**
 * Author:scene
 * Time:2018/2/24 11:14
 * Description:小爽文
 */

public class NovelIndexPresenter extends BasePresenter<INovelIndexView> {
    private NovelIndexModel model;

    public NovelIndexPresenter(INovelIndexView view) {
        super(view);
        this.model = new NovelIndexModel();
    }

    public void getNovelListData(final int page, final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("page", page);
            model.getNovelListData(params, new HttpResultListener<NovelIndexResultInfo>() {

                @Override
                public void onSuccess(NovelIndexResultInfo data) {
                    try {
                        if (isFirst) {
                            mView.showContentPage();
                        } else {
                            if (data.getData().getCurrent_page() == 1) {
                                mView.refreshFinish();
                            } else {
                                mView.loadmoreFinish();
                            }
                        }
                        mView.setHasmore(data.getData().getLast_page() > data.getData().getCurrent_page());
                        mView.bindData(data.getData().getCurrent_page(), data);
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
                            if (page == 1) {
                                mView.refreshFinish();
                            } else {
                                mView.loadmoreFinish();
                                mView.setHasmore(true);
                            }
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
