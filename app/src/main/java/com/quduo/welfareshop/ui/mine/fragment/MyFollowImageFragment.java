package com.quduo.welfareshop.ui.mine.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arjinmc.recyclerviewdecoration.RecyclerViewItemDecoration;
import com.blankj.utilcode.util.SizeUtils;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.mine.adapter.MyFollowImageAdapter;
import com.quduo.welfareshop.ui.mine.presenter.MyFollowImagePresenter;
import com.quduo.welfareshop.ui.mine.view.IMyFollowImageView;
import com.quduo.welfareshop.ui.welfare.entity.ImageDetailInfo;
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
 * Description:收藏的图片
 */

public class MyFollowImageFragment extends BaseMvpFragment<IMyFollowImageView, MyFollowImagePresenter> implements IMyFollowImageView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;

    private List<ImageDetailInfo> galleryList;
    private MyFollowImageAdapter adapter;

    public static MyFollowImageFragment newInstance() {
        Bundle args = new Bundle();
        MyFollowImageFragment fragment = new MyFollowImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_follow_image, container, false);
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

        }
    };

    @Override
    public void initView() {
        showContentPage();
        initRecyclerView();
    }


    private void initRecyclerView() {

        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
            }
        });

        galleryList = new ArrayList<>();


        adapter = new MyFollowImageAdapter(getContext(), galleryList);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //防止item位置互换
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewItemDecoration.Builder builder = new RecyclerViewItemDecoration.Builder(getContext());
        builder.color("#00000000");
        builder.dashWidth(SizeUtils.dp2px(5));
        builder.dashGap(SizeUtils.dp2px(5));
        builder.thickness(SizeUtils.dp2px(5));
        builder.gridBottomVisible(true); //控制下面边框
        builder.gridTopVisible(true); //控制上面边框
        builder.gridLeftVisible(true); //控制左边边框
        builder.gridRightVisible(true); //控制右边边框
        recyclerView.addItemDecoration(builder.create());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public MyFollowImagePresenter initPresenter() {
        return new MyFollowImagePresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
