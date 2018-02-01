package com.quduo.welfareshop.ui.friend.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.friend.presenter.FollowPresenter;
import com.quduo.welfareshop.ui.friend.view.IFollowView;

/**
 * Author:scene
 * Time:2018/2/1 17:11
 * Description:我的关注
 */

public class FollowFragment extends BaseMvpFragment<IFollowView, FollowPresenter> implements IFollowView {
    public static FollowFragment newInstance() {
        Bundle args = new Bundle();
        FollowFragment fragment = new FollowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_follow, container, false);
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
    public FollowPresenter initPresenter() {
        return new FollowPresenter(this);
    }
}
