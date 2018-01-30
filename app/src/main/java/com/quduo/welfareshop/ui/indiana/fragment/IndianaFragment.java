package com.quduo.welfareshop.ui.indiana.fragment;

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
import com.quduo.welfareshop.ui.indiana.presenter.IndianaPresenter;
import com.quduo.welfareshop.ui.indiana.view.IIndianaView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 夺宝首页
 * Created by scene on 2018/1/29.
 */

public class IndianaFragment extends BaseMainMvpFragment<IIndianaView, IndianaPresenter> implements IIndianaView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;

    public static IndianaFragment newInstance() {
        Bundle args = new Bundle();
        IndianaFragment fragment = new IndianaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_indiana, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initToolBar() {
        super.initToolBar();
        toolbarTitle.setText("积分夺宝");
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
    public IndianaPresenter initPresenter() {
        return new IndianaPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
