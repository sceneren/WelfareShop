package com.quduo.welfareshop.ui.shop.fragment;

import android.content.Intent;
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
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.itemDecoration.GridSpacingItemDecoration;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.shop.activity.GoodsDetailActivity;
import com.quduo.welfareshop.ui.shop.adapter.ShopAdapter;
import com.quduo.welfareshop.ui.shop.entity.GoodsInfo;
import com.quduo.welfareshop.ui.shop.entity.ShopDataInfo;
import com.quduo.welfareshop.ui.shop.presenter.CatePresenter;
import com.quduo.welfareshop.ui.shop.view.ICateView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/3/22 14:28
 * Description:商品分类
 */

public class CateFragment extends BaseBackMvpFragment<ICateView, CatePresenter> implements ICateView {
    private static final String ARG_CATEID = "cate_id";
    private static final String ARG_NAME = "name";
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

    private int cateId;
    private String name;

    private List<GoodsInfo> list = new ArrayList<>();
    private ShopAdapter adapter;

    private int page = 1;

    public static CateFragment newInstance(int cateId, String name) {
        Bundle args = new Bundle();
        args.putInt(ARG_CATEID, cateId);
        args.putString(ARG_NAME, name);
        CateFragment fragment = new CateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cateId = getArguments().getInt(ARG_CATEID, 0);
            name = getArguments().getString(ARG_NAME, "");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_cate, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
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
    public void initToolbar() {
        toolbarTitle.setText(name);
        initToolbarNav(toolbar, true);
    }

    @Override
    public void initView() {
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_SHOP_CATE,cateId);
        initRecyclerView();
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

        adapter = new ShopAdapter(_mActivity, list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, SizeUtils.dp2px(1), false));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                toGoodsDetailActivity(list.get(position).getId());
            }
        });
    }

    @Override
    public CatePresenter initPresenter() {
        return new CatePresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.SHOP_CATE_LIST_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void bindData(ShopDataInfo data) {
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
    public void hasLoadmore(boolean hasMore) {
        try {
            refreshLayout.setEnableLoadMore(hasMore);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCateId() {
        return cateId;
    }

    private void toGoodsDetailActivity(int goodsId) {
        Intent intent = new Intent(_mActivity, GoodsDetailActivity.class);
        intent.putExtra(GoodsDetailActivity.ARG_ID, goodsId);
        startActivity(intent);
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }
}
