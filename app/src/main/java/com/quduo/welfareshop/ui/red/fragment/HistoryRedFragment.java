package com.quduo.welfareshop.ui.red.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.red.adapter.RedHistoryAdapter;
import com.quduo.welfareshop.ui.red.presenter.HistoryRedPresenter;
import com.quduo.welfareshop.ui.red.view.IHistoryRedView;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
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

    private List<String> list = new ArrayList<>();
    private RedHistoryAdapter adapter;

    private SelectableRoundedImageView avatar;
    private TextView money;
    private LinearLayout cash;

    public static HistoryRedFragment newInstance() {
        Bundle args = new Bundle();
        HistoryRedFragment fragment = new HistoryRedFragment();
        fragment.setArguments(args);
        return fragment;
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

        }
    };

    @Override
    public void initView() {
        showContentPage();
        initRecyclerView();
        initHeaderView();
    }

    private void initRecyclerView() {
        refreshLayout.setEnableLoadMore(false);
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        adapter = new RedHistoryAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void initHeaderView() {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_my_red_history_header, null);
        avatar = headerView.findViewById(R.id.avatar);
        money = headerView.findViewById(R.id.money);
        cash = headerView.findViewById(R.id.cash);
        adapter.addHeaderView(headerView);
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCashFragment();
            }
        });

        GlideApp.with(this)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
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
        super.onDestroyView();
        unbinder.unbind();
    }

    private void toCashFragment() {
        if ((getParentFragment()) != null && getParentFragment() instanceof MyRedFragment) {
            ((MyRedFragment) getParentFragment()).start(CashFragment.newInstance());
        }
    }
}
