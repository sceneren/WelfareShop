package com.quduo.welfareshop.ui.welfare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.welfare.activity.VideoDetailActivity;
import com.quduo.welfareshop.ui.welfare.adapter.BeautyVideoAdapter;
import com.quduo.welfareshop.ui.welfare.entity.BannerInfo;
import com.quduo.welfareshop.ui.welfare.entity.BeautyVideoResultInfo;
import com.quduo.welfareshop.ui.welfare.entity.VideoModelInfo;
import com.quduo.welfareshop.ui.welfare.presenter.BeautyVideoPresenter;
import com.quduo.welfareshop.ui.welfare.view.IBeautyVideoView;
import com.quduo.welfareshop.util.BannerImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/2/23 15:03
 * Description:美女视频
 */

public class BeautyVideoFragment extends BaseMvpFragment<IBeautyVideoView, BeautyVideoPresenter> implements IBeautyVideoView {
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;

    private List<VideoModelInfo> list = new ArrayList<>();

    private Banner banner;

    private List<BannerInfo> bannerList;
    private BeautyVideoAdapter adapter;

    public static BeautyVideoFragment newInstance() {
        Bundle args = new Bundle();
        BeautyVideoFragment fragment = new BeautyVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welfare_beauty_video, container, false);
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
            presenter.getBeautyVideoData(true);
        }
    };

    @Override
    public void initView() {
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_BEAUTY_VIDEO,0);
        initRecyclerView();
        initHeaderView();
        //initFooterView();
        presenter.getBeautyVideoData(true);
    }

    private void initRecyclerView() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getBeautyVideoData(false);
            }
        });
        adapter = new BeautyVideoAdapter(getContext(), list);
        adapter.setOnItemClickVideoListener(new BeautyVideoAdapter.OnItemClickVideoListener() {
            @Override
            public void onItemClickVideo(int position, int position1, int position2) {
                try {
                    toVideoDetailActivity(list.get(position - 1).getPositions().get(position1).getVideos().get(position2).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void initHeaderView() {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_welfare_beauty_video_header, null);
        banner = headerView.findViewById(R.id.banner);
        banner.setImageLoader(new BannerImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        adapter.addHeaderView(headerView);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (bannerList != null) {
                    toVideoDetailActivity(bannerList.get(position).getData_id());
                }
            }
        });
    }

    private void bindHeaderView(List<BannerInfo> bannerInfoList) {
        if (bannerList == null) {
            bannerList = new ArrayList<>();
        } else {
            bannerList.clear();
        }
        bannerList.addAll(bannerInfoList);

        List<String> images = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < bannerList.size(); i++) {
            images.add(MyApplication.getInstance().getConfigInfo().getFile_domain() + bannerList.get(i).getThumb());
            titles.add(bannerList.get(i).getName());
        }
        banner.setImages(images);
        banner.setBannerTitles(titles);
        banner.start();
    }

    private void initFooterView() {
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_more_content, null);
        adapter.addFooterView(footerView);
    }

    @Override
    public BeautyVideoPresenter initPresenter() {
        return new BeautyVideoPresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.BEAUTY_VIDEO_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showMessage(String msg) {
        try {
            ToastUtils.showShort(msg);
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
    public void bindData(BeautyVideoResultInfo data) {
        try {
            bindHeaderView(data.getBanner());
            list.clear();
            list.addAll(data.getVideos());
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toVideoDetailActivity(int videoId) {
        try {
            Intent intent = new Intent(getContext(), VideoDetailActivity.class);
            intent.putExtra(VideoDetailActivity.ARG_VIDEO_ID, videoId);
            intent.putExtra(VideoDetailActivity.ARG_CATE_ID, 2);
            startActivity(intent);
            _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
