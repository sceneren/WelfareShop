package com.quduo.welfareshop.ui.welfare.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.welfare.presenter.GalleryPresenter;
import com.quduo.welfareshop.ui.welfare.view.IGalleryView;

/**
 * Author:scene
 * Time:2018/1/25  12:06
 * Description:美女图库
 */
public class GalleryFragment extends BaseMvpFragment<IGalleryView, GalleryPresenter> implements IGalleryView {
    public static GalleryFragment newInstance() {
        Bundle args = new Bundle();
        GalleryFragment fragment = new GalleryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        return view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void showLoadingPage() {

    }

    @Override
    public void showContentPage() {

    }

    @Override
    public void showErrorPage() {

    }

    @Override
    public GalleryPresenter initPresenter() {
        return new GalleryPresenter(this);
    }
}
