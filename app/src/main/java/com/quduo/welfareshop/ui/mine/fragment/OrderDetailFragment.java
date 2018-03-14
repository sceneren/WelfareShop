package com.quduo.welfareshop.ui.mine.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.mine.adapter.OrderDetailRecommendGoodsAdapter;
import com.quduo.welfareshop.ui.mine.adapter.TimeLineAdapter;
import com.quduo.welfareshop.ui.mine.presenter.OrderDetailPresenter;
import com.quduo.welfareshop.ui.mine.view.IOrderDetailView;
import com.quduo.welfareshop.widgets.CustomGridView;
import com.quduo.welfareshop.widgets.CustomListView;
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
 * Time:2018/3/14 10:13
 * Description:订单详情
 * 1未支付  2已支付  3已发货  4已评价  5已取消
 */

public class OrderDetailFragment extends BaseBackMvpFragment<IOrderDetailView, OrderDetailPresenter> implements IOrderDetailView {
    private static final String ARG_ID = "order_id";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.status_text)
    TextView statusText;
    @BindView(R.id.goods_image)
    ImageView goodsImage;
    @BindView(R.id.goods_name)
    TextView goodsName;
    @BindView(R.id.goods_number)
    TextView goodsNumber;
    @BindView(R.id.wechat_pay)
    TextView wechatPay;
    @BindView(R.id.layout_type_unpay)
    LinearLayout layoutTypeUnpay;
    @BindView(R.id.logisticsListView)
    CustomListView logisticsListView;
    @BindView(R.id.layout_type_has_send)
    LinearLayout layoutTypeHasSend;
    @BindView(R.id.goodsGridView)
    CustomGridView goodsGridView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;
    private int orderId;

    private List<String> goodsList = new ArrayList<>();
    private OrderDetailRecommendGoodsAdapter recommendGoodsAdapter;

    private List<String> logisticsList = new ArrayList<>();
    private TimeLineAdapter timeLineAdapter;

    public static OrderDetailFragment newInstance(int orderId) {
        Bundle args = new Bundle();
        args.putInt(ARG_ID, orderId);
        OrderDetailFragment fragment = new OrderDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            orderId = getArguments().getInt(ARG_ID, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_order_detail, container, false);
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
        toolbarTitle.setText("订单详情");
        initToolbarNav(toolbar, true);
    }

    @Override
    public void initView() {
        showContentPage();
        initRefreshLayout();
        showType();
        initRecommendGoodsGridView();
    }

    private void initRefreshLayout() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
            }
        });
    }

    private void initRecommendGoodsGridView() {
        goodsList.add("");
        goodsList.add("");
        goodsList.add("");
        goodsList.add("");
        recommendGoodsAdapter = new OrderDetailRecommendGoodsAdapter(getContext(), goodsList);
        goodsGridView.setAdapter(recommendGoodsAdapter);
    }

    private void initLogisticsListView() {
        logisticsList.add("");
        logisticsList.add("");
        logisticsList.add("");
        logisticsList.add("");
        logisticsList.add("");
        logisticsList.add("");
        logisticsList.add("");
        timeLineAdapter=new TimeLineAdapter(getContext(),logisticsList);
        logisticsListView.setAdapter(timeLineAdapter);
    }

    private void showType() {
        if (orderId == 0) {
            layoutTypeUnpay.setVisibility(View.VISIBLE);
            layoutTypeHasSend.setVisibility(View.GONE);
        } else if (orderId == 1) {
            layoutTypeUnpay.setVisibility(View.GONE);
            layoutTypeHasSend.setVisibility(View.GONE);
        } else if (orderId == 2) {
            layoutTypeUnpay.setVisibility(View.GONE);
            layoutTypeHasSend.setVisibility(View.VISIBLE);
            initLogisticsListView();
        }
    }

    @Override
    public OrderDetailPresenter initPresenter() {
        return new OrderDetailPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
