package com.quduo.welfareshop.ui.welfare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.PreviewImageActivity;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.itemDecoration.SpacesItemDecoration;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.welfare.adapter.GalleryDetailAdapter;
import com.quduo.welfareshop.ui.welfare.entity.GalleryDetailResultInfo;
import com.quduo.welfareshop.ui.welfare.entity.ImageDetailInfo;
import com.quduo.welfareshop.ui.welfare.presenter.GalleryDetailPresenter;
import com.quduo.welfareshop.ui.welfare.view.IGalleryDetailView;
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
 * Time:2018/3/5 16:57
 * Description:图库详情
 */

public class GalleryDetailFragment extends BaseBackMvpFragment<IGalleryDetailView, GalleryDetailPresenter> implements IGalleryDetailView {
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
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    Unbinder unbinder;

    private List<ImageDetailInfo> galleryList = new ArrayList<>();
    private GalleryDetailAdapter adapter;

    private int id;
    private final static String ARG_ID = "ID";
    private String title;
    private final static String ARG_TITLE = "title";

    public static GalleryDetailFragment newInstance(int id, String title) {
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putString(ARG_TITLE, title);
        GalleryDetailFragment fragment = new GalleryDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_ID, 0);
            title = getArguments().getString(ARG_TITLE, "图库详情");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery_detail, container, false);
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
            presenter.getGalleryDetailData(id, true);
        }
    };

    @Override
    public void initToolbar() {
        toolbarTitle.setText(title);
        toolbarText.setText("收藏");
        initToolbarNav(toolbar, true);
    }

    @Override
    public void initView() {
        initRecyclerView();
        presenter.getGalleryDetailData(id, true);
    }

    private void initRecyclerView() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getGalleryDetailData(id, false);
            }
        });

        adapter = new GalleryDetailAdapter(getContext(), galleryList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //防止item位置互换
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(1)));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ArrayList<String> imageUrlList = new ArrayList<>();
                for (ImageDetailInfo info : galleryList) {
                    imageUrlList.add(MyApplication.getInstance().getConfigInfo().getFile_domain() + info.getUrl());
                }
                Intent intent = new Intent(getContext(), PreviewImageActivity.class);
                intent.putExtra(PreviewImageActivity.ARG_URLS, imageUrlList);
                intent.putExtra(PreviewImageActivity.ARG_POSITION, position);
                startActivity(intent);
                _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
            }
        });
    }

    @Override
    public GalleryDetailPresenter initPresenter() {
        return new GalleryDetailPresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.GALLERY_DETAIL_TAG);
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
    public void bindData(GalleryDetailResultInfo data) {
        try {
            galleryList.clear();
            galleryList.addAll(data.getImages());
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
