package com.quduo.welfareshop.ui.limit.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseMainMvpFragment;
import com.quduo.welfareshop.ui.limit.presenter.LimitPresenter;
import com.quduo.welfareshop.ui.limit.view.ILimitView;
import com.quduo.welfareshop.ui.welfare.presenter.WelfarePresenter;
import com.quduo.welfareshop.ui.welfare.view.IWelfareView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 限制级主界面
 * Created by scene on 2018/1/25.
 */

public class LimitFragment extends BaseMainMvpFragment<ILimitView, LimitPresenter> implements ILimitView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;

    public static LimitFragment newInstance() {
        Bundle args = new Bundle();
        LimitFragment fragment = new LimitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_limit, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public void initToolBar() {
        super.initToolBar();
        toolbarTitle.setText("限制级");
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
    public LimitPresenter initPresenter() {
        return new LimitPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
