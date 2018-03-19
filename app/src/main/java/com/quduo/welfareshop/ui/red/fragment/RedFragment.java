package com.quduo.welfareshop.ui.red.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.dialog.RedOpenDialog;
import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.mvp.BaseMainMvpFragment;
import com.quduo.welfareshop.ui.mine.fragment.MineFragment;
import com.quduo.welfareshop.ui.red.dialog.GetRedDialog;
import com.quduo.welfareshop.ui.red.presenter.RedPresenter;
import com.quduo.welfareshop.ui.red.view.IRedView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/2/1 15:26
 * Description:
 */

public class RedFragment extends BaseMainMvpFragment<IRedView, RedPresenter> implements IRedView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_image_menu)
    ImageView toolbarImageMenu;
    Unbinder unbinder;

    private RedOpenDialog redOpenDialog;
    private GetRedDialog getRedDialog;

    public static RedFragment newInstance() {
        Bundle args = new Bundle();
        RedFragment fragment = new RedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_red, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initToolBar() {
        super.initToolBar();
        toolbarTitle.setText(getString(R.string.tab_red));
    }

    @Override
    public void initView() {
        super.initView();
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
    public RedPresenter initPresenter() {
        return new RedPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.toolbar_image_menu)
    public void onClickToolBarImageMenu() {
        EventBus.getDefault().post(new StartBrotherEvent(MineFragment.newInstance()));
    }

    @OnClick(R.id.open)
    public void onClickOpen() {
//        if (redOpenDialog == null) {
//            redOpenDialog = new RedOpenDialog(getContext());
//        }
//        if (redOpenDialog.isShowing()) {
//            redOpenDialog.cancel();
//        } else {
//            redOpenDialog.show();
//        }
        if (getRedDialog == null) {
            getRedDialog = new GetRedDialog(_mActivity);
        }
        getRedDialog.show();
    }

    @OnClick(R.id.enter_my_red)
    public void onClickEnterMyRed() {
        EventBus.getDefault().post(new StartBrotherEvent(MyRedFragment.newInstance()));
    }

    @OnClick(R.id.cash)
    public void onClickCash() {
        EventBus.getDefault().post(new StartBrotherEvent(CashFragment.newInstance()));
    }

}
