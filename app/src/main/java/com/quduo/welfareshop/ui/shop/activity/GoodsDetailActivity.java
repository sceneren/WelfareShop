package com.quduo.welfareshop.ui.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.shop.adapter.GoodsDetailCommentAdapter;
import com.quduo.welfareshop.ui.shop.presenter.GoodsDetailPresenter;
import com.quduo.welfareshop.ui.shop.view.IGoodsDetailView;
import com.quduo.welfareshop.widgets.CustomListView;
import com.quduo.welfareshop.widgets.RatioImageView;
import com.quduo.welfareshop.widgets.X5WebView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/2/27 15:14
 * Description:商品详情
 */
public class GoodsDetailActivity extends BaseMvpActivity<IGoodsDetailView, GoodsDetailPresenter> implements IGoodsDetailView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    @BindView(R.id.baseimage)
    RatioImageView baseimage;
    @BindView(R.id.commentListView)
    CustomListView commentListView;
    @BindView(R.id.webView)
    X5WebView webView;

    private List<String> list;
    private GoodsDetailCommentAdapter commentAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        unbinder = ButterKnife.bind(this);
        initToolBar();
        initView();
        initListView();
        initWebView();
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
        showContentPage();

        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrLayout.refreshComplete();
                    }
                }, 1000);
            }
        });

    }

    private void initListView() {
        list = new ArrayList<>();
        list.add("");
        list.add("");
        commentAdapter = new GoodsDetailCommentAdapter(GoodsDetailActivity.this, list);
        commentListView.setAdapter(commentAdapter);
    }

    private void initWebView() {
        webView.loadUrl("https://www.baidu.com");
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
    public GoodsDetailPresenter initPresenter() {
        return new GoodsDetailPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.see_all_comment)
    public void onClickSeeAllComment() {
        startActivity(new Intent(GoodsDetailActivity.this, GoodsCommentActivity.class));
    }
}
