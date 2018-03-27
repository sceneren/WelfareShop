package com.quduo.welfareshop.ui.red.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.OpenRedSuccessEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.red.adapter.RedHistoryAdapter;
import com.quduo.welfareshop.ui.red.entity.RedHistoryDetailInfo;
import com.quduo.welfareshop.ui.red.entity.RedHistoryResultInfo;
import com.quduo.welfareshop.ui.red.presenter.HistoryRedPresenter;
import com.quduo.welfareshop.ui.red.view.IHistoryRedView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/3/8 10:00
 * Description:历史记录
 */

public class HistoryRedFragment extends BaseMvpFragment<IHistoryRedView, HistoryRedPresenter> implements IHistoryRedView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;

    private List<RedHistoryDetailInfo> list = new ArrayList<>();
    private RedHistoryAdapter adapter;

    private TextView money;
    private TextView totalWin;
    private TextView luckTime;

    private int page = 1;

    public static HistoryRedFragment newInstance() {
        Bundle args = new Bundle();
        HistoryRedFragment fragment = new HistoryRedFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_red_history, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
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
    public void initView() {
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_RED_HISTORY,0);
        initRecyclerView();
        initHeaderView();
        presenter.getData(1, true);
    }

    private void initRecyclerView() {
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
        adapter = new RedHistoryAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void initHeaderView() {
        @SuppressLint("InflateParams")
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_my_red_history_header, null);
        ImageView avatar = headerView.findViewById(R.id.avatar);
        money = headerView.findViewById(R.id.money);
        totalWin = headerView.findViewById(R.id.total_win);
        luckTime = headerView.findViewById(R.id.luck_time);
        adapter.addHeaderView(headerView);
        headerView.findViewById(R.id.cash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCashFragment();
            }
        });
        MultiTransformation multiTransformation = new MultiTransformation(
                new CenterCrop(), new RoundedCornersTransformation(SizeUtils.dp2px(5), 0)
        );
        GlideApp.with(this)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .apply(RequestOptions.bitmapTransform(multiTransformation))
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + MyApplication.getInstance().getUserInfo().getAvatar())
                .into(avatar);
        money.setText(MessageFormat.format("{0}元", MyApplication.getInstance().getUserInfo().getMoney()));
    }

    @Override
    public HistoryRedPresenter initPresenter() {
        return new HistoryRedPresenter(this);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        OkGo.getInstance().cancelTag(ApiUtil.RED_HISTORY_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }

    private void toCashFragment() {
        if ((getParentFragment()) != null && getParentFragment() instanceof MyRedFragment) {
            ((MyRedFragment) getParentFragment()).start(CashFragment.newInstance());
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
    public void loadmoreFinish() {
        try {
            refreshLayout.finishLoadMore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bindData(RedHistoryResultInfo data) {
        try {
            totalWin.setText(MessageFormat.format("{0}元", data.getTotal_win()));
            luckTime.setText(MessageFormat.format("{0}次", data.getBest_times()));
            page = data.getHistory().getInfo().getPage();
            if (page == 1) {
                list.clear();
            }
            list.addAll(data.getHistory().getData());
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setHasMore(boolean hasMore) {
        try {
            refreshLayout.setEnableLoadMore(hasMore);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void changeMoney(OpenRedSuccessEvent event) {
        try {
            money.setText(MessageFormat.format("{0}元", MyApplication.getInstance().getUserInfo().getMoney()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
