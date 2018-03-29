package com.quduo.welfareshop.ui.friend.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.UnlockLisenter;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.itemDecoration.SpacesItemDecoration;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.friend.activity.ChatActivity;
import com.quduo.welfareshop.ui.friend.adapter.FollowAdapter;
import com.quduo.welfareshop.ui.friend.entity.FollowUserInfo;
import com.quduo.welfareshop.ui.friend.presenter.FollowPresenter;
import com.quduo.welfareshop.ui.friend.view.IFollowView;
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

/**
 * Author:scene
 * Time:2018/2/1 17:11
 * Description:我的关注
 */

public class FollowFragment extends BaseMvpFragment<IFollowView, FollowPresenter> implements IFollowView {
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;

    private List<FollowUserInfo> list = new ArrayList<>();
    private FollowAdapter adapter;

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
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initView() {
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_FRIEND_FOLLOW, 0);
        initRecyclerView();
        presenter.getData(true);
    }

    private void initRecyclerView() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getData(false);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(1)));
        adapter = new FollowAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.chat) {
                    if (MyApplication.getInstance().getUserInfo().getUnlock_chat() != 0) {
                        toChatMessage(list.get(position));
                    } else {
                        DialogUtils.getInstance().showUnlockChatDialog(_mActivity, new UnlockLisenter() {
                            @Override
                            public void unlock() {
                                presenter.unlockChat();
                            }
                        });
                    }
                }
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EventBus.getDefault().post(new StartBrotherEvent(OtherInfoFragment.newInstance(String.valueOf(list.get(position).getTarget_user_id()), false)));
            }
        });
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
            presenter.getData(true);
        }
    };

    @Override
    public FollowPresenter initPresenter() {
        return new FollowPresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.FOLLOW_USER_LIST_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void bindData(List<FollowUserInfo> data) {
        try {
            list.clear();
            list.addAll(data);
            adapter.notifyDataSetChanged();
            if (list.size() == 0) {
                View notDataView = getLayoutInflater().inflate(R.layout.status_none_layout, (ViewGroup) recyclerView.getParent(), false);
                adapter.setEmptyView(notDataView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void refreshFinish() {
        try {
            refreshLayout.finishRefresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showAlert(String message) {
        try {
            if (message.equals("积分不足")) {
                DialogUtils.getInstance().showChatNeedRechargeDialog(_mActivity);
                return;
            }
            StyledDialog.buildIosAlert("提示", message, new MyDialogListener() {
                @Override
                public void onFirst() {

                }

                @Override
                public void onSecond() {

                }
            }).setActivity(_mActivity).setBtnText("确定").show();
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
    public void unlockChatSuccess(int currentScore) {
        try {
            MyApplication.getInstance().getUserInfo().setUnlock_chat(1);
            MyApplication.getInstance().getUserInfo().setScore(currentScore);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void toChatMessage(FollowUserInfo userInfo) {
        Intent intent = new Intent(_mActivity, ChatActivity.class);
        intent.putExtra("ID", String.valueOf(userInfo.getTarget_user_id()));
        intent.putExtra("NICKNAME", userInfo.getNickname());
        intent.putExtra("IS_FOLLOW", true);
        intent.putExtra("NEARBY", false);
        intent.putExtra("OTHERAVATAR", userInfo.getAvatar());
        startActivity(intent);
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }
}
