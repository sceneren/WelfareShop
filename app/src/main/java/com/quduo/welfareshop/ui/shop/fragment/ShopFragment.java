package com.quduo.welfareshop.ui.shop.fragment;

import android.annotation.SuppressLint;
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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.itemDecoration.ShopIndexItemDecoration;
import com.quduo.welfareshop.mvp.BaseMainMvpFragment;
import com.quduo.welfareshop.ui.shop.activity.GoodsDetailActivity;
import com.quduo.welfareshop.ui.shop.adapter.ShopAdapter;
import com.quduo.welfareshop.ui.shop.adapter.ShopIndexCateAdapter;
import com.quduo.welfareshop.ui.shop.entity.GoodsInfo;
import com.quduo.welfareshop.ui.shop.entity.ShopCateInfo;
import com.quduo.welfareshop.ui.shop.entity.ShopHotInfo;
import com.quduo.welfareshop.ui.shop.entity.ShopResultInfo;
import com.quduo.welfareshop.ui.shop.presenter.ShopPresenter;
import com.quduo.welfareshop.ui.shop.view.IShopView;
import com.quduo.welfareshop.ui.welfare.activity.VideoDetailActivity;
import com.quduo.welfareshop.ui.welfare.entity.BannerInfo;
import com.quduo.welfareshop.ui.welfare.fragment.GalleryDetailFragment;
import com.quduo.welfareshop.ui.welfare.fragment.NovelDetailFragment;
import com.quduo.welfareshop.util.BannerImageLoader;
import com.quduo.welfareshop.widgets.CustomGridView;
import com.quduo.welfareshop.widgets.RatioImageView;
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
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;

    private List<GoodsInfo> list = new ArrayList<>();
    private ShopAdapter adapter;

    private List<ShopCateInfo> cateList = new ArrayList<>();
    private ShopIndexCateAdapter cateAdapter;

    private List<BannerInfo> bannerList;
    //header
    private Banner banner;
    private RatioImageView recommend1;
    private RatioImageView recommend2;
    private RatioImageView recommend3;
    private ImageView recommend5;
    private ImageView recommend6;

    private int page = 1;

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
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_SHOP_INDEX, 0);
        initRecyclerView();
        initHeaderView();
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
        adapter = new ShopAdapter(getContext(), list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new ShopIndexItemDecoration());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                toGoodsDetailActivity(list.get(position).getId());
            }
        });
    }

    private void initHeaderView() {
        @SuppressLint("InflateParams") View headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_shop_header, null);
        CustomGridView cateGridView = headerView.findViewById(R.id.cateGridView);
        banner = headerView.findViewById(R.id.banner);
        recommend1 = headerView.findViewById(R.id.recommend_1);
        recommend2 = headerView.findViewById(R.id.recommend_2);
        recommend3 = headerView.findViewById(R.id.recommend_3);
        recommend5 = headerView.findViewById(R.id.recommend_5);
        recommend6 = headerView.findViewById(R.id.recommend_6);
        ImageView hotImage = headerView.findViewById(R.id.hot_image);

        GlideApp.with(this)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .load(R.drawable.ic_shop_hot_icon)
                .into(hotImage);


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


        cateAdapter = new ShopIndexCateAdapter(getContext(), cateList);
        cateGridView.setAdapter(cateAdapter);
        cateGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toCateFragment(cateList.get(position).getId(), cateList.get(position).getName());
            }
        });
        adapter.addHeaderView(headerView);
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
    public ShopPresenter initPresenter() {
        return new ShopPresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.SHOP_INDEX_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void bindData(ShopResultInfo data) {
        try {
            page = data.getData().getCurrent_page();
            bindBanner(data.getBanner());
            bindCate(data.getCate());
            bindRecyclerView(data.getData().getData());
            bindHot(data.getHot());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindRecyclerView(List<GoodsInfo> data) {
        try {
            if (page == 1) {
                list.clear();
            }
            list.addAll(data);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindBanner(List<BannerInfo> data) {
        try {
            if (bannerList == null) {
                bannerList = new ArrayList<>();
            } else {
                bannerList.clear();
            }
            bannerList.addAll(data);

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

    private void bindCate(List<ShopCateInfo> data) {
        try {
            cateList.clear();
            cateList.addAll(data);
            cateAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindHot(final List<ShopHotInfo> data) {
        try {
            if (null != data.get(0)) {
                GlideApp.with(this)
                        .asBitmap()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_default_shop)
                        .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.get(0).getHot_thumb())
                        .into(recommend1);
                recommend1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toGoodsDetailActivity(data.get(0).getId());
                    }
                });
            }
            if (null != data.get(1)) {
                GlideApp.with(this)
                        .asBitmap()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_default_shop)
                        .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.get(1).getHot_thumb())
                        .into(recommend2);
                recommend2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toGoodsDetailActivity(data.get(1).getId());
                    }
                });
            }

            if (null != data.get(2)) {
                GlideApp.with(this)
                        .asBitmap()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_default_shop)
                        .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.get(2).getHot_thumb())
                        .into(recommend3);
                recommend3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toGoodsDetailActivity(data.get(2).getId());
                    }
                });
            }

            if (null != data.get(3)) {
                GlideApp.with(this)
                        .asBitmap()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_default_shop)
                        .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.get(4).getHot_thumb())
                        .into(recommend5);
                recommend5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toGoodsDetailActivity(data.get(3).getId());
                    }
                });
            }
            if (null != data.get(4)) {
                GlideApp.with(this)
                        .asBitmap()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_default_shop)
                        .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.get(5).getHot_thumb())
                        .into(recommend6);
                recommend6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toGoodsDetailActivity(data.get(4).getId());
                    }
                });
            }

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

    private void toCateFragment(int cateId, String cateName) {
        EventBus.getDefault().post(new StartBrotherEvent(CateFragment.newInstance(cateId, cateName)));
    }
}
