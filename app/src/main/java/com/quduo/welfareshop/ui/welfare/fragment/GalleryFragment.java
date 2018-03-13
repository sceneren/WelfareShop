package com.quduo.welfareshop.ui.welfare.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.welfare.adapter.GalleryAdapter;
import com.quduo.welfareshop.ui.welfare.adapter.GalleryTypeGridAdapter;
import com.quduo.welfareshop.ui.welfare.entity.BannerInfo;
import com.quduo.welfareshop.ui.welfare.entity.GalleryCateInfo;
import com.quduo.welfareshop.ui.welfare.entity.GalleryResultInfo;
import com.quduo.welfareshop.ui.welfare.entity.WelfareGalleryInfo;
import com.quduo.welfareshop.ui.welfare.presenter.GalleryPresenter;
import com.quduo.welfareshop.ui.welfare.view.IGalleryView;
import com.quduo.welfareshop.util.BannerImageLoader;
import com.quduo.welfareshop.widgets.CustomGridView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
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

/**
 * Author:scene
 * Time:2018/1/25  12:06
 * Description:美女图库
 */
public class GalleryFragment extends BaseMvpFragment<IGalleryView, GalleryPresenter> implements IGalleryView {
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;

    private Banner banner;
    private List<BannerInfo> bannerList;

    private GalleryTypeGridAdapter typeGridAdapter;
    private List<GalleryCateInfo> cateList;

    private List<WelfareGalleryInfo> galleryList;
    private GalleryAdapter adapter;
    private int currentPage = 1;

    public static GalleryFragment newInstance() {
        Bundle args = new Bundle();
        GalleryFragment fragment = new GalleryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welfare_gallery, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initView() {
        showContentPage();
        initRecyclerView();
        initHeaderView();
        presenter.getGalleryData(1, true);
    }

    private void initRecyclerView() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getGalleryData(1, false);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                presenter.getGalleryData(currentPage + 1, false);
            }
        });
        galleryList = new ArrayList<>();

        adapter = new GalleryAdapter(getContext(), galleryList);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //防止item位置互换
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EventBus.getDefault().post(new StartBrotherEvent(GalleryDetailFragment.newInstance(galleryList.get(position).getId())));
            }
        });
    }

    private void initHeaderView() {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_welfare_gallery_header, null);
        banner = headerView.findViewById(R.id.banner);
        banner.setImageLoader(new BannerImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                EventBus.getDefault().post(new StartBrotherEvent(GalleryDetailFragment.newInstance(bannerList.get(position).getData_id())));
            }
        });
        CustomGridView typeGridView = headerView.findViewById(R.id.typeGridView);
        cateList = new ArrayList<>();
        typeGridAdapter = new GalleryTypeGridAdapter(getContext(), cateList);
        typeGridView.setAdapter(typeGridAdapter);
        typeGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GalleryCateInfo info = cateList.get(position);
                EventBus.getDefault().post(new StartBrotherEvent(GalleryCateFragment.newInstance(info.getId(), info.getName())));
            }
        });
        adapter.addHeaderView(headerView);
    }

    private void bindBanner(List<BannerInfo> banners) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void bindCate(List<GalleryCateInfo> cateList) {
        try {
            this.cateList.clear();
            this.cateList.addAll(cateList);
            typeGridAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
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
            presenter.getGalleryData(1, true);
        }
    };

    @Override
    public GalleryPresenter initPresenter() {
        return new GalleryPresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.GALLERY_TAG);
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
    public void loadmoreFinish() {
        try {
            refreshLayout.finishLoadMore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bindData(GalleryResultInfo data) {
        try {
            bindBanner(data.getBanner());
            bindCate(data.getCate());
            currentPage = data.getGallery().getCurrent_page();
            refreshLayout.setEnableLoadMore(data.getGallery().getLast_page() > currentPage);
            if (currentPage == 1) {
                galleryList.clear();
            }
            galleryList.addAll(data.getGallery().getData());
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
