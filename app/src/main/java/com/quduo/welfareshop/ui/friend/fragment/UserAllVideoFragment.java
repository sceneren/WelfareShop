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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hss01248.dialog.StyledDialog;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.UnlockLisenter;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.UnlockUserVideoEvent;
import com.quduo.welfareshop.event.UpdateScoreAndDiamondEvent;
import com.quduo.welfareshop.itemDecoration.GridSpacingItemDecoration;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.friend.adapter.UserAllVideoAdapter;
import com.quduo.welfareshop.ui.friend.entity.FriendVideoDetailInfo;
import com.quduo.welfareshop.ui.friend.entity.UserVideoInfo;
import com.quduo.welfareshop.ui.friend.presenter.UserAllViewPresenter;
import com.quduo.welfareshop.ui.friend.view.IUserAllVideoView;
import com.quduo.welfareshop.util.DialogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

public class UserAllVideoFragment extends BaseBackMvpFragment<IUserAllVideoView, UserAllViewPresenter> implements IUserAllVideoView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;

    private List<UserVideoInfo> list;
    private UserAllVideoAdapter adapter;

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
        initRefreshLayout();
        initRecyclerView();
        presenter.getData(targetUserId, true);
    }

    private void initRefreshLayout() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getData(targetUserId, false);
            }
        });
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new UserAllVideoAdapter(list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, SizeUtils.dp2px(5), true));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (list.get(position).isPayed()) {
                    UserVideoInfo videoInfo = list.get(position);
                    FriendVideoDetailInfo detailInfo = new FriendVideoDetailInfo();
                    detailInfo.setId(videoInfo.getId());
                    detailInfo.setAvatar(videoInfo.getAvatar());
                    detailInfo.setContent(videoInfo.getContent());
                    detailInfo.setNickName(videoInfo.getNickname());
                    detailInfo.setPlay_times(videoInfo.getPlay_times());
                    detailInfo.setThumb(videoInfo.getThumb());
                    detailInfo.setUserId(videoInfo.getUser_id());
                    detailInfo.setVideo_url(videoInfo.getUrl());
                    detailInfo.setPrice(videoInfo.getPrice());
                    detailInfo.setPayed(videoInfo.isPayed());
                    detailInfo.setPosition(position);
                    start(FriendVideoDetailFragment.newInstance(detailInfo, FriendVideoDetailFragment.FROM_INTERACT));
                } else {
                    showNeedUnlockDialog(position);
                }
            }
        });
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
    public void unlockSuccess(int position, int currentScore) {
        try {
            MyApplication.getInstance().getUserInfo().setScore(currentScore);
            EventBus.getDefault().post(new UpdateScoreAndDiamondEvent());
            EventBus.getDefault().post(new UnlockUserVideoEvent());
            list.get(position).setPayed(true);
            adapter.notifyItemChanged(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refreshFinish() {
        try {
            refreshLayout.finishRefresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bindData(List<UserVideoInfo> data) {
        try {
            list.clear();
            list.addAll(data);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showLoadingPage() {
        try {
            statusView.showLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showContentPage() {
        try {
            statusView.showContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showErrorPage() {
        try {
            statusView.showFailed(retryListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.getData(targetUserId, true);
        }
    };

    private void showNeedUnlockDialog(final int position) {
        try {
            DialogUtils.getInstance().showNeedUnlockDialog(_mActivity, list.get(position).getPrice(), MyApplication.getInstance().getUserInfo().getScore(), AppConfig.POSITION_FRIEND_VIDEO_DETAIL, new UnlockLisenter() {
                @Override
                public void unlock() {
                    presenter.unlockVideo(position, list.get(position).getId());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
