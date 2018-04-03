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
import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.shop.activity.GoodsDetailActivity;
import com.quduo.welfareshop.ui.welfare.activity.VideoDetailActivity;
import com.quduo.welfareshop.ui.welfare.adapter.NovelAdapter;
import com.quduo.welfareshop.ui.welfare.entity.BannerInfo;
import com.quduo.welfareshop.ui.welfare.entity.NovelModelInfo;
import com.quduo.welfareshop.ui.welfare.entity.NovelResultInfo;
import com.quduo.welfareshop.ui.welfare.presenter.NovelPresenter;
import com.quduo.welfareshop.ui.welfare.view.INovelView;
import com.quduo.welfareshop.util.BannerImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/2/24 11:15
 * Description:小爽文
 */

public class NovelFragment extends BaseMvpFragment<INovelView, NovelPresenter> implements INovelView {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;

    private Banner banner;
    private List<BannerInfo> bannerList;

    private NovelAdapter adapter;
    private List<NovelModelInfo> list = new ArrayList<>();

    public static NovelFragment newInstance() {
        Bundle args = new Bundle();
        NovelFragment fragment = new NovelFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welfare_novel, container, false);
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
            presenter.getNovelListData(true);
        }
    };

    @Override
    public void initView() {
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_NOVEL_INDEX, 0);
        initRecyclerView();
        initHeaderView();
        presenter.getNovelListData(true);
    }

    private void initRecyclerView() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new com.scwang.smartrefresh.layout.listener.OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getNovelListData(false);
            }
        });

        adapter = new NovelAdapter(getContext(), list);
        adapter.setOnNovelItemClickListener(new NovelAdapter.OnNovelItemClickListener() {
            @Override
            public void onClickNovel(int position, int childPosition) {
                if (position > 0) {
                    position = position - 1;
                    EventBus.getDefault().post(new StartBrotherEvent(NovelDetailFragment.newInstance(list.get(position).getNovel().get(childPosition).getId())));
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void initHeaderView() {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_welfare_beauty_video_header, (ViewGroup) recyclerView.getParent(), false);
        banner = headerView.findViewById(R.id.banner);
        banner.setImageLoader(new BannerImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                try {
                    switch (bannerList.get(position).getType()) {
                        case "gallery":
                            EventBus.getDefault().post(new StartBrotherEvent(GalleryDetailFragment.newInstance(bannerList.get(position).getData_id(), bannerList.get(position).getName())));
                            break;
                        case "video":
                            toVideoDetailActivity(bannerList.get(position).getData_id(), 2);
                            break;
                        case "movie":
                            toVideoDetailActivity(bannerList.get(position).getData_id(), 3);
                            break;
                        case "novel":
                            EventBus.getDefault().post(new StartBrotherEvent(NovelDetailFragment.newInstance(bannerList.get(position).getData_id())));
                            break;
                        case "goods":
                            toGoodsDetailActivity(bannerList.get(position).getData_id());
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        adapter.addHeaderView(headerView);
    }

    private void bindHeaderView(List<BannerInfo> banners) {
        if (bannerList == null) {
            bannerList = new ArrayList<>();
        } else {
            bannerList.clear();
        }
        bannerList.addAll(banners);

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

    @Override
    public NovelPresenter initPresenter() {
        return new NovelPresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.NOVEL_LIST_TAG);
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
    public void bindData(NovelResultInfo data) {
        try {
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
            bindHeaderView(data.getBanner());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toVideoDetailActivity(int videoId, int type) {
        try {
            Intent intent = new Intent(getContext(), VideoDetailActivity.class);
            intent.putExtra(VideoDetailActivity.ARG_VIDEO_ID, videoId);
            intent.putExtra(VideoDetailActivity.ARG_CATE_ID, type);
            startActivity(intent);
            _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toGoodsDetailActivity(int goodsId) {
        Intent intent = new Intent(_mActivity, GoodsDetailActivity.class);
        intent.putExtra(GoodsDetailActivity.ARG_ID, goodsId);
        startActivity(intent);
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }
}
