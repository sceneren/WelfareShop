package com.quduo.welfareshop.ui.shop.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SpanUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.PreviewImageActivity;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.shop.adapter.GoodsDetailCommentAdapter;
import com.quduo.welfareshop.ui.shop.dialog.ChooseGoodsTypeDialog;
import com.quduo.welfareshop.ui.shop.entity.GoodsCommentInfo;
import com.quduo.welfareshop.ui.shop.entity.GoodsDetailInfo;
import com.quduo.welfareshop.ui.shop.entity.GoodsDetailResultInfo;
import com.quduo.welfareshop.ui.shop.fragment.ServiceCenterActivity;
import com.quduo.welfareshop.ui.shop.presenter.GoodsDetailPresenter;
import com.quduo.welfareshop.ui.shop.view.IGoodsDetailView;
import com.quduo.welfareshop.util.BannerImageLoader;
import com.quduo.welfareshop.util.WeakDataHolder;
import com.quduo.welfareshop.widgets.CustomListView;
import com.quduo.welfareshop.widgets.RatioImageView;
import com.quduo.welfareshop.widgets.X5WebView;
import com.quduo.welfareshop.widgets.goodsdetail.PageBehavior;
import com.quduo.welfareshop.widgets.goodsdetail.PageContainer;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/2/27 15:14
 * Description:商品详情
 */
public class GoodsDetailActivity extends BaseMvpActivity<IGoodsDetailView, GoodsDetailPresenter> implements IGoodsDetailView {
    public static final String ARG_ID = "goods_id";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;
    @BindView(R.id.banner)
    Banner banner;
    //    @BindView(R.id.refresh_layout)
//    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    @BindView(R.id.baseimage)
    RatioImageView baseimage;
    @BindView(R.id.commentListView)
    CustomListView commentListView;
    @BindView(R.id.webView)
    X5WebView webView;
    @BindView(R.id.see_all_comment)
    TextView seeAllComment;
    @BindView(R.id.buy_now)
    TextView buyNow;
    @BindView(R.id.layout_comment)
    LinearLayout layoutComment;
    @BindView(R.id.goods_name)
    TextView goodsName;
    @BindView(R.id.goods_price)
    TextView goodsPrice;
    @BindView(R.id.buy_give_info)
    TextView buyGiveInfo;
    @BindView(R.id.goods_old_price)
    TextView goodsOldPrice;
    @BindView(R.id.goods_sales)
    TextView goodsSales;
    @BindView(R.id.comment_num)
    TextView commentNum;
    @BindView(R.id.tab_goods_price)
    TextView tabGoodsPrice;
    @BindView(R.id.btn_service_center)
    TextView btnServiceCenter;
    @BindView(R.id.btn_follow)
    TextView btnFollow;
    @BindView(R.id.notice)
    TextView notice;
    @BindView(R.id.container)
    PageContainer pageContainer;
    @BindView(R.id.sub_title)
    TextView subTitle;

    private List<GoodsCommentInfo> commentList = new ArrayList<>();
    private GoodsDetailCommentAdapter commentAdapter;

    private List<String> bannerList = new ArrayList<>();

    private int goodsId;

    private GoodsDetailInfo detailInfo;

    @BindDrawable(R.drawable.ic_goods_follow_d)
    public Drawable iconNoFollow;
    @BindDrawable(R.drawable.ic_goods_follow_s)
    public Drawable iconHasFollow;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        unbinder = ButterKnife.bind(this);
        goodsId = getIntent().getIntExtra(ARG_ID, 0);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        toolbarTitle.setText("商品详情");
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });
    }

    private void initView() {
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_SHOP_DETAIL, goodsId);
        initRefreshLayout();
        initBanner();
        initCommentListView();

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView webView, String s) {
                webView.loadUrl("javascript:App.resize(document.body.getBoundingClientRect().height)");
                super.onPageFinished(webView, s);
            }
        });
        webView.addJavascriptInterface(this, "App");

        pageContainer.setOnPageChanged(new PageBehavior.OnPageChanged() {
            @Override
            public void toTop() {
                try {
                    notice.setText("继续拖动，查看图文详情");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void toBottom() {
                try {
                    notice.setText("继续拖动，查看商品详情");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        presenter.getData(true);
    }

    private void initBanner() {
        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        layoutParams.height = PtrLocalDisplay.SCREEN_WIDTH_PIXELS;
        layoutParams.width = PtrLocalDisplay.SCREEN_WIDTH_PIXELS;
        banner.setImageLoader(new BannerImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                try {
                    ArrayList<String> imageList = new ArrayList<>();
                    for (String str : bannerList) {
                        imageList.add(MyApplication.getInstance().getConfigInfo().getFile_domain() + str);
                    }
                    toPriviewActivity(imageList, position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initCommentListView() {
        commentAdapter = new GoodsDetailCommentAdapter(GoodsDetailActivity.this, commentList);
        commentListView.setAdapter(commentAdapter);
    }

    private void initRefreshLayout() {
//        refreshLayout.setEnableLoadMore(false);
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshLayout) {
//                presenter.getData(false);
//            }
//        });
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
            presenter.getData(true);
        }
    };

    @Override
    public GoodsDetailPresenter initPresenter() {
        return new GoodsDetailPresenter(this);
    }

    @Override
    protected void onDestroy() {
        try {
            webView.stopLoading();
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkGo.getInstance().cancelTag(ApiUtil.GOODS_DETAIL_TAG);
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.see_all_comment)
    public void onClickSeeAllComment() {
        toGoodsCommentActivity();
    }

    @OnClick(R.id.buy_now)
    public void onClickBuyNow() {
        try {
            WeakDataHolder.getInstance().saveData(String.valueOf(detailInfo.getId()), detailInfo);
            Intent intent = new Intent(GoodsDetailActivity.this, ChooseGoodsTypeDialog.class);
            intent.putExtra(ChooseGoodsTypeDialog.ARG_GOODS_ID, goodsId);
            startActivity(intent);
            overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toGoodsCommentActivity() {
        WeakDataHolder.getInstance().saveData(String.valueOf("goods" + detailInfo.getId()), detailInfo);
        Intent intent = new Intent(GoodsDetailActivity.this, GoodsCommentActivity.class);
        intent.putExtra(GoodsCommentActivity.ARG_GOODS_ID, detailInfo.getId());
        startActivity(intent);
        overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @Override
    public void showLoadingDialog() {
        try {
            StyledDialog.buildLoading().setActivity(GoodsDetailActivity.this).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideLoadingDialog() {
        try {
            StyledDialog.dismissLoading();
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
    public void bindData(GoodsDetailResultInfo data) {
        try {
            detailInfo = data.getData();
            bindBanner(data.getData().getImages());
            bindCommentLisView(data.getComments());

            subTitle.setText(detailInfo.getSub_title());
            subTitle.setVisibility(StringUtils.isTrimEmpty(detailInfo.getSub_title()) ? View.GONE : View.VISIBLE);
            goodsName.setText(detailInfo.getName());
            goodsPrice.setText(MessageFormat.format("￥{0}", detailInfo.getPrice()));
            tabGoodsPrice.setText(MessageFormat.format("￥{0}", detailInfo.getPrice()));
            SpannableStringBuilder stringBuilder = new SpanUtils().append("原价:￥" + detailInfo.getOld_price()).setStrikethrough().create();
            goodsOldPrice.setText(stringBuilder);
            goodsSales.setText(MessageFormat.format("销量:{0}", detailInfo.getSales()));
            Number num = Float.parseFloat(detailInfo.getPrice()) * 100;
            int giveNum = num.intValue() / 100;
            buyGiveInfo.setText(MessageFormat.format("{0}积分{1}钻石", giveNum, giveNum));

            btnFollow.setCompoundDrawablesWithIntrinsicBounds(null, detailInfo.getFavor_id() == 0 ? iconNoFollow : iconHasFollow, null, null);

            webView.loadUrl(data.getData().getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindBanner(List<String> data) {
        if (bannerList == null) {
            bannerList = new ArrayList<>();
        } else {
            bannerList.clear();
        }
        bannerList.addAll(data);
        List<String> images = new ArrayList<>();
        for (int i = 0; i < bannerList.size(); i++) {
            images.add(MyApplication.getInstance().getConfigInfo().getFile_domain() + bannerList.get(i));
        }
        banner.setImages(images);
        banner.start();
    }

    private void bindCommentLisView(List<GoodsCommentInfo> data) {
        try {
            commentNum.setText(MessageFormat.format("买家秀（{0}）", detailInfo.getComment_count()));
            commentList.clear();
            commentList.addAll(data);
            commentAdapter.notifyDataSetChanged();
            if (detailInfo.getComment_count() > 0) {
                layoutComment.setVisibility(View.VISIBLE);
                if (detailInfo.getComment_count() > 2) {
                    seeAllComment.setVisibility(View.VISIBLE);
                } else {
                    seeAllComment.setVisibility(View.GONE);
                }
            } else {
                layoutComment.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refreshFinish() {
    }

    @Override
    public int getGoodsId() {
        return goodsId;
    }

    @Override
    public void followGoodsSuccess(int followId) {
        try {
            detailInfo.setFavor_id(followId);
            btnFollow.setCompoundDrawablesWithIntrinsicBounds(null, detailInfo.getFavor_id() == 0 ? iconNoFollow : iconHasFollow, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelFollowSuccess() {
        try {
            detailInfo.setFavor_id(0);
            btnFollow.setCompoundDrawablesWithIntrinsicBounds(null, detailInfo.getFavor_id() == 0 ? iconNoFollow : iconHasFollow, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toPriviewActivity(ArrayList<String> imageList, int position) {
        Intent intent = new Intent(GoodsDetailActivity.this, PreviewImageActivity.class);
        intent.putExtra(PreviewImageActivity.ARG_URLS, imageList);
        intent.putExtra(PreviewImageActivity.ARG_POSITION, position);
        startActivity(intent);
        overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @OnClick(R.id.btn_service_center)
    public void onClickServiceCenter() {
        startActivity(new Intent(GoodsDetailActivity.this, ServiceCenterActivity.class));
        overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @OnClick(R.id.btn_follow)
    void onClickBtnFollow() {
        if (detailInfo.getFavor_id() == 0) {
            presenter.followGoods();
        } else {
            presenter.cancelFollowGoods(detailInfo.getFavor_id());
        }
    }
}
