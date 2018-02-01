package com.quduo.welfareshop.ui.friend.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.friend.presenter.NearPresenter;
import com.quduo.welfareshop.ui.friend.view.INearView;

/**
 * Author:scene
 * Time:2018/2/1 17:11
 * Description:附近的人
 */

public class NearFragment extends BaseMvpFragment<INearView, NearPresenter> implements INearView {
    public static NearFragment newInstance() {
        Bundle args = new Bundle();
        NearFragment fragment = new NearFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_near, container, false);
        return view;
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
    public NearPresenter initPresenter() {
        return new NearPresenter(this);
    }
}
