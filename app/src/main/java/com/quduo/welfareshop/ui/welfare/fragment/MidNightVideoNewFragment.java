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
import com.quduo.welfareshop.ui.welfare.adapter.MidNightNewAdapter;
import com.quduo.welfareshop.ui.welfare.entity.BannerInfo;
import com.quduo.welfareshop.ui.welfare.entity.MidNightVideoResultInfo;
import com.quduo.welfareshop.ui.welfare.entity.VideoTypeInfo;
import com.quduo.welfareshop.ui.welfare.presenter.MidNightVideoPresenter;
import com.quduo.welfareshop.ui.welfare.view.IMidNightVideoView;
import com.quduo.welfareshop.util.BannerImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/2/24 10:11
 * Description:午夜影院
 */
public class MidNightVideoNewFragment extends BaseMvpFragment<IMidNightVideoView, MidNightVideoPresenter> implements IMidNightVideoView {

    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;

    private List<VideoTypeInfo> list = new ArrayList<>();

    private Banner banner;

    private List<BannerInfo> bannerList;
    private MidNightNewAdapter adapter;

    public static MidNightVideoNewFragment newInstance() {
        Bundle args = new Bundle();
        MidNightVideoNewFragment fragment = new MidNightVideoNewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welfare_midnight_video, container, false);
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
            presenter.getMidNightVideoData(true);
        }
    };

    @Override
    public void initView() {
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_MIDNIGHT_VIDEO, 0);
        initRecyclerView();
        initHeaderView();
        presenter.getMidNightVideoData(true);
    }

    private void initRecyclerView() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getMidNightVideoData(false);
            }
        });
        adapter = new MidNightNewAdapter(getContext(), list);
        adapter.setOnItemClickItemVideoListener(new MidNightNewAdapter.OnItemClickItemVideoListener() {
            @Override
            public void onItemClickItemVideo(int position, int childPosition) {
                try {
                    toVideoDetailActivity(list.get(position).getVideos().get(childPosition).getId(), 3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void initHeaderView() {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_welfare_beauty_video_header, (ViewGroup) recyclerView.getParent(), false);
        banner = headerView.findViewById(R.id.banner);
        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        layoutParams.height = PtrLocalDisplay.SCREEN_WIDTH_PIXELS / 2;
        layoutParams.width = PtrLocalDisplay.SCREEN_WIDTH_PIXELS;
        banner.setImageLoader(new BannerImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        adapter.addHeaderView(headerView);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (bannerList != null) {
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

    @Override
    public MidNightVideoPresenter initPresenter() {
        return new MidNightVideoPresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.MIDNIGHT_VIDEO_TAG);
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
    public void bindData(MidNightVideoResultInfo data) {
        try {
            bindHeaderView(data.getBanner());
            list.clear();
            list.addAll(data.getVideos().get(0).getPositions());
            adapter.notifyDataSetChanged();
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
