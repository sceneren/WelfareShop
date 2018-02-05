package com.quduo.welfareshop.ui.friend.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.friend.presenter.OtherInfoPresenter;
import com.quduo.welfareshop.ui.friend.view.IOtherInfoView;
import com.quduo.welfareshop.widgets.CustomeGridView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/2/5 14:57
 * Description:别人的主页
 */
public class OtherInfoFragment extends BaseBackMvpFragment<IOtherInfoView, OtherInfoPresenter> implements IOtherInfoView {
    private static final String ARG_ID = "user_id";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.user_id)
    TextView userId;
    @BindView(R.id.fans_num)
    TextView fansNum;
    @BindView(R.id.no_photo)
    TextView noPhoto;
    @BindView(R.id.photoGridView)
    CustomeGridView photoGridView;
    Unbinder unbinder;
    private int otherUserId = 0;

    public static OtherInfoFragment newInstance(int userId) {
        Bundle args = new Bundle();
        args.putInt(ARG_ID, userId);
        OtherInfoFragment fragment = new OtherInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            otherUserId = getArguments().getInt(ARG_ID, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
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
    public void initToolbar() {
        initToolbarNav(toolbar);
    }

    @Override
    public void initView() {

    }

    @Override
    public OtherInfoPresenter initPresenter() {
        return new OtherInfoPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
