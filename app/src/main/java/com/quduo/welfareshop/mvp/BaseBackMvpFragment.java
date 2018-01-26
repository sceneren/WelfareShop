package com.quduo.welfareshop.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.quduo.welfareshop.base.BaseBackFragment;


/**
 * Case By:mvp模式带返回的fragment
 * package:wiki.scene.shop.mvp
 * Author：scene on 2017/6/27 16:46
 */

public abstract class BaseBackMvpFragment<V, T extends BasePresenter<V>> extends BaseBackFragment {
    public T presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initToolbar();
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initView();
    }

    public void initToolbar() {

    }

    public void initView() {
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    @Override
    public void onDestroyView() {
        hideSoftInput();
        presenter.dettach();
        super.onDestroyView();
    }

    // 实例化presenter
    public abstract T initPresenter();
}
