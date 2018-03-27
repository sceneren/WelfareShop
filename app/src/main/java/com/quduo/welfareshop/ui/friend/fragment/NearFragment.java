package com.quduo.welfareshop.ui.friend.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.itemDecoration.GridSpacingItemDecoration;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.friend.adapter.NearAdapter;
import com.quduo.welfareshop.ui.friend.dialog.FriendChooseDialog;
import com.quduo.welfareshop.ui.friend.entity.OtherSimpleUserInfo;
import com.quduo.welfareshop.ui.friend.presenter.NearPresenter;
import com.quduo.welfareshop.ui.friend.view.INearView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/2/1 17:11
 * Description:附近的人
 */

public class NearFragment extends BaseMvpFragment<INearView, NearPresenter> implements INearView {
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    @BindView(R.id.friend_screen)
    ImageView friendScreen;

    private FriendChooseDialog dialog;

    private List<OtherSimpleUserInfo> list = new ArrayList<>();
    private NearAdapter adapter;

    public static NearFragment newInstance() {
        Bundle args = new Bundle();
        NearFragment fragment = new NearFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_near, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private int retryTime = 0;

    @Override
    public void initView() {
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_FRIEND_NEAR,0);
        if (MyApplication.getInstance().getLatitude() == 0) {
            refreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    retryTime += 1;
                    if (retryTime > 3) {
                        showMessage("无法获取到定位信息，请到设置中开启定位权限");
                        showNoLocationPage();
                    } else {
                        initView();
                    }
                }
            }, 500);
        } else {
            initRecyclerView();
        }

    }

    private void initRecyclerView() {
        showContentPage();
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getData(false);
            }
        });

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //防止item位置互换
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NearAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EventBus.getDefault().post(new StartBrotherEvent(OtherInfoFragment.newInstance(String.valueOf(list.get(position).getId()), true)));
            }
        });
        presenter.getData(true);
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

    public void showNoLocationPage() {
        try {
            statusView.showNetError();
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

    @OnClick(R.id.friend_screen)
    public void onClickFriendScreen() {
        if (dialog == null && getContext() != null) {
            dialog = new FriendChooseDialog(getContext());
            dialog.setOnClickSexListener(new FriendChooseDialog.OnClickSexListener() {
                @Override
                public void onClickSex(int sex) {
                    if (sex != getSex()) {
                        SPUtils.getInstance().put("SEX", sex);
                        refreshLayout.autoRefresh();
                    }
                }
            });
        }
        dialog.show();
    }

    @Override
    public NearPresenter initPresenter() {
        return new NearPresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.NEAR_LIST_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.getData(true);
        }
    };

    @Override
    public void bindData(List<OtherSimpleUserInfo> data) {
        try {
            list.clear();
            list.addAll(data);
            adapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double getLongitude() {
        return MyApplication.getInstance().getLongitude();
    }

    @Override
    public double getLatitude() {
        return MyApplication.getInstance().getLatitude();
    }

    @Override
    public int getSex() {
        return SPUtils.getInstance().getInt("SEX", 0);
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
    public void showMessage(String message) {
        try {
            ToastUtils.showShort(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
