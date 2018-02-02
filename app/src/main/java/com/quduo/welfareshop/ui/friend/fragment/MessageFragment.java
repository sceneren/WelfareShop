package com.quduo.welfareshop.ui.friend.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.friend.presenter.MessagePresenter;
import com.quduo.welfareshop.ui.friend.view.IMessageView;

/**
 * Author:scene
 * Time:2018/2/1 17:11
 * Description:消息
 */

public class MessageFragment extends BaseMvpFragment<IMessageView, MessagePresenter> implements IMessageView {
    public static MessageFragment newInstance() {
        Bundle args = new Bundle();
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_message, container, false);
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
    public MessagePresenter initPresenter() {
        return new MessagePresenter(this);
    }
}
