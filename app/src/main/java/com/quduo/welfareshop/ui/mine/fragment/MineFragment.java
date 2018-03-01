package com.quduo.welfareshop.ui.mine.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.ReadActivity;
import com.quduo.welfareshop.db.BookList;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.mine.presenter.MinePresenter;
import com.quduo.welfareshop.ui.mine.view.IMineView;
import com.quduo.welfareshop.ui.read.listener.OnSaveData2DBListener;
import com.quduo.welfareshop.util.FileUtils;
import com.quduo.welfareshop.util.ReaderUtil;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的主界面
 * Created by scene on 2018/1/25.
 */

public class MineFragment extends BaseBackMvpFragment<IMineView, MinePresenter> implements IMineView {
    Unbinder unbinder;

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public void initToolbar() {

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
    public MinePresenter initPresenter() {
        return new MinePresenter(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.toolbar_back)
    public void onClickToolbarBack() {
        _mActivity.onBackPressed();
    }

    @OnClick(R.id.avatar)
    public void onClickAvatar() {
        start(MyInfoFragment.newInstance());
    }

    @OnClick(R.id.my_order)
    public void onClickMyOrder() {
        start(MyOrderFragment.newInstance());
    }

}
