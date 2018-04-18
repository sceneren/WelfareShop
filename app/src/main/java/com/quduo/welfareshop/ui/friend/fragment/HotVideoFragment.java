package com.quduo.welfareshop.ui.friend.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.FriendHotVideoChangeStatusEvent;
import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.itemDecoration.GridSpacingItemDecoration;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.friend.adapter.HotVideoAdapter;
import com.quduo.welfareshop.ui.friend.entity.FriendVideoDetailInfo;
import com.quduo.welfareshop.ui.friend.entity.HotVideoInfo;
import com.quduo.welfareshop.ui.friend.entity.HotVideoResultInfo;
import com.quduo.welfareshop.ui.friend.presenter.HotVideoPresenter;
import com.quduo.welfareshop.ui.friend.view.IHotVideoView;
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

public class HotVideoFragment extends BaseMvpFragment<IHotVideoView, HotVideoPresenter> implements IHotVideoView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;

    private int page = 1;

    private List<HotVideoInfo> list;
    private HotVideoAdapter adapter;

    public static HotVideoFragment newInstance() {
        Bundle args = new Bundle();
        HotVideoFragment fragment = new HotVideoFragment();
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
        View view = inflater.inflate(R.layout.fragment_friend_hot_video, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initView() {
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_FRIEND_HOT_VIDEO, 0);
        initRefreshLayout();
        initRecyclerView();
        presenter.getData(1, true);
    }

    private void initRefreshLayout() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                presenter.getData(page + 1, false);
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getData(1, false);
            }
        });
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new HotVideoAdapter(list);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.layout_to_user_detail) {
                    EventBus.getDefault().post(new StartBrotherEvent(OtherInfoFragment.newInstance(String.valueOf(list.get(position).getUser_id()), false)));
                } else if (view.getId() == R.id.layout_to_video_detail) {
                    HotVideoInfo hotVideoInfo = list.get(position);
                    FriendVideoDetailInfo detailInfo = new FriendVideoDetailInfo();
                    detailInfo.setId(hotVideoInfo.getId());
                    detailInfo.setAvatar(hotVideoInfo.getAvatar());
                    detailInfo.setContent(hotVideoInfo.getContent());
                    detailInfo.setNickName(hotVideoInfo.getNickname());
                    detailInfo.setPlay_times(hotVideoInfo.getPlay_times());
                    detailInfo.setThumb(hotVideoInfo.getThumb());
                    detailInfo.setUserId(hotVideoInfo.getUser_id());
                    detailInfo.setVideo_url(hotVideoInfo.getUrl());
                    detailInfo.setPrice(hotVideoInfo.getPrice());
                    detailInfo.setPayed(hotVideoInfo.isPayed());
                    detailInfo.setPosition(position);

                    EventBus.getDefault().post(new StartBrotherEvent(FriendVideoDetailFragment.newInstance(detailInfo)));
                }
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, SizeUtils.dp2px(5), true));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public HotVideoPresenter initPresenter() {
        return new HotVideoPresenter(this);
    }

    @Override
    public void bindData(HotVideoResultInfo data) {
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
    public void alert(String message) {
        try {
            StyledDialog.buildIosAlert("提示", message, null).setBtnText("确定").setActivity(_mActivity).show();
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

    @Subscribe
    public void changePayStatus(FriendHotVideoChangeStatusEvent event) {
        try {
            list.get(event.getPosition()).setPayed(true);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.FRIEND_HOT_VIDEO_TAG);
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }
}
