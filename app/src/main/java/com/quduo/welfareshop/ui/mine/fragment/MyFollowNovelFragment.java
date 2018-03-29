package com.quduo.welfareshop.ui.mine.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.mine.adapter.MyFollowNovelAdapter;
import com.quduo.welfareshop.ui.mine.entity.MyFollowNovelInfo;
import com.quduo.welfareshop.ui.mine.presenter.MyFollowNovelPresenter;
import com.quduo.welfareshop.ui.mine.view.IMyFollowNovelView;
import com.quduo.welfareshop.ui.welfare.fragment.NovelDetailFragment;
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
 * Time:2018/3/1 11:18
 * Description:收藏的小说
 */

public class MyFollowNovelFragment extends BaseMvpFragment<IMyFollowNovelView, MyFollowNovelPresenter> implements IMyFollowNovelView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;

    private List<MyFollowNovelInfo> list;
    private MyFollowNovelAdapter adapter;

    public static MyFollowNovelFragment newInstance() {
        Bundle args = new Bundle();
        MyFollowNovelFragment fragment = new MyFollowNovelFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_follow_novel, container, false);
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
            presenter.getMyFollowNovelData(true);
        }
    };

    @Override
    public void initView() {
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_MINE_FOLLOW_NOVEL,0);
        initRecyclerView();
        presenter.getMyFollowNovelData(true);
    }

    private void initRecyclerView() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getMyFollowNovelData(false);
            }
        });
        list = new ArrayList<>();
        adapter = new MyFollowNovelAdapter(getContext(), list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (getParentFragment() != null) {
                    ((MyFollowFragment) getParentFragment()).start(NovelDetailFragment.newInstance(list.get(position).getNovel_id()));
                }
            }
        });
        View notDataView = getLayoutInflater().inflate(R.layout.status_none_layout, (ViewGroup) recyclerView.getParent(), false);
        adapter.setEmptyView(notDataView);
    }

    @Override
    public MyFollowNovelPresenter initPresenter() {
        return new MyFollowNovelPresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.MY_FOLLOW_NOVEL_TAG);
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
    public void bindData(List<MyFollowNovelInfo> list) {
        try {
            this.list.clear();
            this.list.addAll(list);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
