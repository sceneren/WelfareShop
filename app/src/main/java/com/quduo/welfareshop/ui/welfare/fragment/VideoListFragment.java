package com.quduo.welfareshop.ui.welfare.fragment;

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
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.itemDecoration.GridSpacingItemDecoration;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.welfare.adapter.VideoListAdapter;
import com.quduo.welfareshop.ui.welfare.entity.VideoInfo;
import com.quduo.welfareshop.ui.welfare.presenter.VideoListPresenter;
import com.quduo.welfareshop.ui.welfare.view.IVideoListView;
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
 * Time:2018/3/5 17:05
 * Description:视频列表
 */

public class VideoListFragment extends BaseBackMvpFragment<IVideoListView, VideoListPresenter> implements IVideoListView {
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

    private List<VideoInfo> list;
    private VideoListAdapter adapter;


    public static VideoListFragment newInstance() {
        Bundle args = new Bundle();
        VideoListFragment fragment = new VideoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welfare_video_list, container, false);
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

        }
    };

    @Override
    public void initToolbar() {
        toolbarTitle.setText("视频列表");
        initToolbarNav(toolbar,true);
    }

    @Override
    public void initView() {
        showContentPage();
        initRecyclerView();
        initHeaderView();
        initFooterView();
    }

    private void initRecyclerView() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
            }
        });

        list = new ArrayList<>();
        VideoInfo info = new VideoInfo();
        info.setTitle("视频1");
        info.setImageUrl("http://f.hiphotos.baidu.com/image/h%3D300/sign=4a0a3dd10155b31983f9847573ab8286/503d269759ee3d6db032f61b48166d224e4ade6e.jpg");
        list.add(info);
        VideoInfo info1 = new VideoInfo();
        info1.setTitle("视频2");
        info1.setImageUrl("http://f.hiphotos.baidu.com/image/h%3D300/sign=4a0a3dd10155b31983f9847573ab8286/503d269759ee3d6db032f61b48166d224e4ade6e.jpg");
        list.add(info1);
        VideoInfo info2 = new VideoInfo();
        info2.setTitle("视频3");
        info2.setImageUrl("http://f.hiphotos.baidu.com/image/h%3D300/sign=4a0a3dd10155b31983f9847573ab8286/503d269759ee3d6db032f61b48166d224e4ade6e.jpg");
        list.add(info2);
        VideoInfo info3 = new VideoInfo();
        info3.setTitle("视频4");
        info3.setImageUrl("http://f.hiphotos.baidu.com/image/h%3D300/sign=4a0a3dd10155b31983f9847573ab8286/503d269759ee3d6db032f61b48166d224e4ade6e.jpg");
        list.add(info3);
        adapter = new VideoListAdapter(getContext(), list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, SizeUtils.dp2px(5), true));
        recyclerView.setAdapter(adapter);
    }

    private void initHeaderView() {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_more_content, null);
        adapter.addHeaderView(headerView);
    }

    private void initFooterView() {
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_more_content, null);
        adapter.addFooterView(footerView);
    }

    @Override
    public VideoListPresenter initPresenter() {
        return new VideoListPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
