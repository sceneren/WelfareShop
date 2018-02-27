package com.quduo.welfareshop.ui.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.itemDecoration.SpacesItemDecoration;
import com.quduo.welfareshop.mvp.BaseMainMvpFragment;
import com.quduo.welfareshop.ui.mine.fragment.MineFragment;
import com.quduo.welfareshop.ui.shop.activity.GoodsDetailActivity;
import com.quduo.welfareshop.ui.shop.adapter.ShopAdapter;
import com.quduo.welfareshop.ui.shop.presenter.ShopPresenter;
import com.quduo.welfareshop.ui.shop.view.IShopView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 商城主界面
 * Created by scene on 2018/1/25.
 */

public class ShopFragment extends BaseMainMvpFragment<IShopView, ShopPresenter> implements IShopView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    LRecyclerView recyclerView;

    private List<String> list;
    private LRecyclerViewAdapter mAdapter;


    public static ShopFragment newInstance() {
        Bundle args = new Bundle();
        ShopFragment fragment = new ShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void initToolBar() {
        super.initToolBar();
        toolbarTitle.setText("性趣商城");
    }

    @Override
    public void initView() {
        super.initView();
        initRecyclerView();
        initHeaderView();
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        ShopAdapter adapter = new ShopAdapter(getContext(), list);
        mAdapter = new LRecyclerViewAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(2)));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLoadMoreEnabled(false);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getContext(), GoodsDetailActivity.class));
            }
        });
    }

    private void initHeaderView() {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_shop_header, null);
        mAdapter.addHeaderView(headerView);
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
    public ShopPresenter initPresenter() {
        return new ShopPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.toolbar_image_menu)
    public void onClickToolBarImageMenu() {
        EventBus.getDefault().post(new StartBrotherEvent(MineFragment.newInstance()));
    }
}
