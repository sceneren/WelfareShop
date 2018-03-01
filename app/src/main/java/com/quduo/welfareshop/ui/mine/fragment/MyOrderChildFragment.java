package com.quduo.welfareshop.ui.mine.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SizeUtils;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.itemDecoration.SpacesItemDecoration;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.mine.adapter.MyOrderChildAdapter;
import com.quduo.welfareshop.ui.mine.entity.OrderInfo;
import com.quduo.welfareshop.ui.mine.presenter.MyOrderChildPresenter;
import com.quduo.welfareshop.ui.mine.view.IMyOrderChildView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/2/28 18:08
 * Description:订单子界面
 */

public class MyOrderChildFragment extends BaseMvpFragment<IMyOrderChildView, MyOrderChildPresenter> implements IMyOrderChildView {
    private static final String ARG_TYPE = "arg_type";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;

    private int type = 0;
    private List<OrderInfo> list;
    private MyOrderChildAdapter adapter;

    public static MyOrderChildFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, type);
        MyOrderChildFragment fragment = new MyOrderChildFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_order_child, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(ARG_TYPE, 0);
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

        }
    };

    @Override
    public void initView() {
        showContentPage();
        initRecyclerView();
    }

    public void initRecyclerView() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
            }
        });
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setType(type);
            orderInfo.setGoods_name("商品名称" + i);
            list.add(orderInfo);
        }
        adapter = new MyOrderChildAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(10), true, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public MyOrderChildPresenter initPresenter() {
        return new MyOrderChildPresenter(this);
    }

    @Override
    public void onDestroyView() {
        try {
            list.clear();
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroyView();
        unbinder.unbind();
    }
}
