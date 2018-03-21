package com.quduo.welfareshop.ui.red.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.red.entity.RedHistoryResultInfo;
import com.quduo.welfareshop.ui.red.model.HistoryRedModel;
import com.quduo.welfareshop.ui.red.view.IHistoryRedView;

/**
 * Author:scene
 * Time:2018/3/8 10:00
 * Description:历史记录
 */

public class HistoryRedPresenter extends BasePresenter<IHistoryRedView> {
    private HistoryRedModel model;

    public HistoryRedPresenter(IHistoryRedView view) {
        super(view);
        this.model = new HistoryRedModel();
    }

    public void getData(final int page, final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("page", page);
            model.getData(params, new HttpResultListener<RedHistoryResultInfo>() {
                @Override
                public void onSuccess(RedHistoryResultInfo data) {
                    try {
                        mView.bindData(data);
                        if (isFirst) {
                            mView.showContentPage();
                        } else {
                            if (page == 1) {
                                mView.refreshFinish();
                            } else {
                                mView.loadmoreFinish();
                            }
                        }
                        mView.setHasMore(page < data.getHistory().getInfo().getPage_total());
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
                            }
                        }
                        mView.setHasMore(true);
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
