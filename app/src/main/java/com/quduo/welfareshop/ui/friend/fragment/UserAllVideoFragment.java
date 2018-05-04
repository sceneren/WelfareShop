package com.quduo.welfareshop.ui.friend.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hss01248.dialog.StyledDialog;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.itemDecoration.GridSpacingItemDecoration;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.friend.adapter.UserAllVideoAdapter;
import com.quduo.welfareshop.ui.friend.entity.UserVideoInfo;
import com.quduo.welfareshop.ui.friend.presenter.UserAllViewPresenter;
import com.quduo.welfareshop.ui.friend.view.IUserAllVideoView;
import com.quduo.welfareshop.util.WeakDataHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserAllVideoFragment extends BaseBackMvpFragment<IUserAllVideoView, UserAllViewPresenter> implements IUserAllVideoView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private List<UserVideoInfo> list;
    private UserAllVideoAdapter adapter;

    public static final String ARG_ALL_VIDEO = "arg_all_video_";
    public static final String ARG_TARGET_USER_ID = "target_user_id";

    private int targetUserId = 0;

    public static UserAllVideoFragment newInstance(int targetUserId) {
        Bundle args = new Bundle();
        args.putInt(ARG_TARGET_USER_ID, targetUserId);
        UserAllVideoFragment fragment = new UserAllVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            targetUserId = getArguments().getInt(ARG_TARGET_USER_ID, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_all_video, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText("视频");
        initToolbarNav(toolbar, true);
    }

    @Override
    public void initView() {
        list = (List<UserVideoInfo>) WeakDataHolder.getInstance().getData(ARG_ALL_VIDEO + targetUserId);
        if (list != null) {
            adapter = new UserAllVideoAdapter(list);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, SizeUtils.dp2px(5), true));
            recyclerView.setAdapter(adapter);
        }
    }


    @Override
    public UserAllViewPresenter initPresenter() {
        return new UserAllViewPresenter(this);
    }

    @Override
    public void showMessage(String message) {
        try {
            ToastUtils.showShort(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alert(String message) {
        try {
            StyledDialog.buildIosAlert("提示", message, null).setBtnText("确定").setActivity(_mActivity).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showLoadingDialog() {
        try {
            StyledDialog.buildLoading().setActivity(_mActivity).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideLoadingDialog() {
        try {
            StyledDialog.dismissLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
