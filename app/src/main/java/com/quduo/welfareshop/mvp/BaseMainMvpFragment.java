package com.quduo.welfareshop.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.quduo.welfareshop.base.BaseMainFragment;


/**
 * Case By:
 * package:wiki.scene.shop.mvp
 * Author：scene on 2017/6/27 16:46
 */

public abstract class BaseMainMvpFragment<V, T extends BasePresenter<V>> extends BaseMainFragment {
    public T presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initToolBar();
        initView();
    }

    public void initToolBar() {

    }

    public void initView() {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        presenter.dettach();
        super.onDestroyView();
    }

    // 实例化presenter
    public abstract T initPresenter();
}
