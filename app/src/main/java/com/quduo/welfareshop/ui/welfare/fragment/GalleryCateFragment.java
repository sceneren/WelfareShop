package com.quduo.welfareshop.ui.welfare.fragment;

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
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.itemDecoration.SpacesItemDecoration;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.welfare.adapter.GalleryAdapter;
import com.quduo.welfareshop.ui.welfare.entity.GalleryCateResultInfo;
import com.quduo.welfareshop.ui.welfare.entity.WelfareGalleryInfo;
import com.quduo.welfareshop.ui.welfare.presenter.GalleryCatePresenter;
import com.quduo.welfareshop.ui.welfare.view.IGalleryCateView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/3/9 13:56
 * Description:视频分类
 */

public class GalleryCateFragment extends BaseBackMvpFragment<IGalleryCateView, GalleryCatePresenter> implements IGalleryCateView {
    private static final String ARG_CATEID = "cate_id";
    private static final String ARG_CATENAME = "cate_name";
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
    Unbinder unbinder;

    private int cateId;
    private String cateName;

    private int currentPage = 1;

    private List<WelfareGalleryInfo> list = new ArrayList<>();
    private GalleryAdapter adapter;

    public static GalleryCateFragment newInstance(int cateId, String cateName) {
        Bundle args = new Bundle();
        args.putInt(ARG_CATEID, cateId);
        args.putString(ARG_CATENAME, cateName);
        GalleryCateFragment fragment = new GalleryCateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cateId = getArguments().getInt(ARG_CATEID, 0);
            cateName = getArguments().getString(ARG_CATENAME, "分类");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welfare_gallery_cate, container, false);
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
            presenter.getGalleryData(1, true);
        }
    };

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
    public void bindData(GalleryCateResultInfo data) {
        try {
            currentPage = data.getCurrent_page();
            refreshLayout.setEnableLoadMore(data.getLast_page() > currentPage);
            if (currentPage == 1) {
                list.clear();
            }
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCateId() {
        return cateId;
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(cateName);
        initToolbarNav(toolbar, true);
    }

    @Override
    public void initView() {
        initRecyclerView();
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
        list = new ArrayList<>();

        adapter = new GalleryAdapter(getContext(), list);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(5)));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(GalleryDetailFragment.newInstance(list.get(position).getId(), list.get(position).getName()));
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (list.get(position).getFavor_id() == 0) {
                    presenter.followGallery(position, list.get(position).getId());
                } else {
                    presenter.cancelFollow(position, list.get(position).getFavor_id());
                }
            }
        });
    }

    @Override
    public GalleryCatePresenter initPresenter() {
        return new GalleryCatePresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.GALLERY_CATE_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showLoadingDialog() {
        try {
            StyledDialog.buildLoading().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void followSuccess(int position, int followId) {
        try {
            list.get(position).setFavor_id(followId);
            list.get(position).setFavor_times(list.get(position).getFavor_times() + 1);
            adapter.notifyDataSetChanged();
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
    public void cancelFollowSuccess(int position) {
        try {
            list.get(position).setFavor_id(0);
            list.get(position).setFavor_times(list.get(position).getFavor_times() > 0 ? list.get(position).getFavor_times() - 1 : 0);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
