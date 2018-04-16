package com.quduo.welfareshop.ui.welfare.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.entity.NovelCommentInfo;
import com.quduo.welfareshop.ui.welfare.model.NovelCommentModel;
import com.quduo.welfareshop.ui.welfare.view.INovelCommentView;

import java.util.List;

public class NovelCommentPresenter extends BasePresenter<INovelCommentView> {
    private NovelCommentModel model;

    public NovelCommentPresenter(INovelCommentView view) {
        super(view);
        this.model = new NovelCommentModel();
    }

    public void getData(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("id", mView.getNovelId());
            model.getData(params, new HttpResultListener<List<NovelCommentInfo>>() {
                @Override
                public void onSuccess(List<NovelCommentInfo> data) {
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
