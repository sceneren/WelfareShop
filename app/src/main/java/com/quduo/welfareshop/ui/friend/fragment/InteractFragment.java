package com.quduo.welfareshop.ui.friend.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.FriendInteractChangeStatusEvent;
import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.friend.adapter.InteractAdapter;
import com.quduo.welfareshop.ui.friend.entity.FriendVideoDetailInfo;
import com.quduo.welfareshop.ui.friend.entity.InteractInfo;
import com.quduo.welfareshop.ui.friend.entity.InteractResultInfo;
import com.quduo.welfareshop.ui.friend.presenter.InteractPresenter;
import com.quduo.welfareshop.ui.friend.view.IInteractView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

public class InteractFragment extends BaseMvpFragment<IInteractView, InteractPresenter> implements IInteractView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;

    private int page = 1;
    private List<InteractInfo> list;
    private InteractAdapter adapter;

    public static InteractFragment newInstance() {
        Bundle args = new Bundle();
        InteractFragment fragment = new InteractFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_interact, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void initView() {
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_FRIEND_INTERACT, 0);
        initRefreshLayout();
        initRecyclerView();
        presenter.getData(1, true);
    }


    private void initRefreshLayout() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getData(1, false);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                presenter.getData(page + 1, false);
            }
        });
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new InteractAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EventBus.getDefault().post(new StartBrotherEvent(OtherInfoFragment.newInstance(String.valueOf(list.get(position).getUser_id()), false)));
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.layout_zan) {
                    if (!list.get(position).isIs_good()) {
                        presenter.zanVideo(position, list.get(position).getId());
                    }
                } else if (view.getId() == R.id.thumb) {
                    InteractInfo interactInfo = list.get(position);
                    FriendVideoDetailInfo detailInfo = new FriendVideoDetailInfo();
                    detailInfo.setId(interactInfo.getId());
                    detailInfo.setAvatar(interactInfo.getAvatar());
                    detailInfo.setContent(interactInfo.getContent());
                    detailInfo.setNickName(interactInfo.getNickname());
                    detailInfo.setPlay_times(interactInfo.getPlay_times());
                    detailInfo.setThumb(interactInfo.getThumb());
                    detailInfo.setUserId(interactInfo.getUser_id());
                    detailInfo.setVideo_url(interactInfo.getUrl());
                    detailInfo.setPrice(interactInfo.getPrice());
                    detailInfo.setPayed(interactInfo.isPayed());
                    detailInfo.setPosition(position);

                    EventBus.getDefault().post(new StartBrotherEvent(FriendVideoDetailFragment.newInstance(detailInfo, FriendVideoDetailFragment.FROM_INTERACT)));
                }
            }
        });
    }

    @Override
    public InteractPresenter initPresenter() {
        return new InteractPresenter(this);
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
            presenter.getData(1, true);
        }
    };

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.FRIEND_INTERACT_TAG);
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
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
    public void loadmoreFinish() {
        try {
            refreshLayout.finishLoadMore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setHasmore(boolean hasmore) {
        try {
            refreshLayout.setEnableLoadMore(hasmore);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bindData(InteractResultInfo data) {
        try {
            page = data.getCurrent_page();
            if (page == 1) {
                list.clear();
            }
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
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
    public void zanSuccess(int position) {
        try {
            list.get(position).setIs_good(true);
            list.get(position).setGood(list.get(position).getGood() + 1);
            adapter.notifyItemChanged(position, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void changePayStatus(FriendInteractChangeStatusEvent event) {
        try {
            list.get(event.getPosition()).setPayed(true);
            adapter.notifyItemChanged(event.getPosition(),0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
