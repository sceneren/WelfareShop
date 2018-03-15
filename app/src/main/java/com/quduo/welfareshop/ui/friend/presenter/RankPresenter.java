package com.quduo.welfareshop.ui.friend.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.friend.model.RankModel;
import com.quduo.welfareshop.ui.friend.view.IRankView;

/**
 * Author:scene
 * Time:2018/2/1 17:10
 * Description:人气榜
 */

public class RankPresenter extends BasePresenter<IRankView> {
    private RankModel model;

    public RankPresenter(IRankView view) {
        super(view);
        this.model = new RankModel();
    }


}
