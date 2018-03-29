package com.quduo.welfareshop.ui.mine.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.itemDecoration.SpacesItemDecoration;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.mine.adapter.MyFollowImageAdapter;
import com.quduo.welfareshop.ui.mine.entity.MyFollowGalleryInfo;
import com.quduo.welfareshop.ui.mine.presenter.MyFollowImagePresenter;
import com.quduo.welfareshop.ui.mine.view.IMyFollowImageView;
import com.quduo.welfareshop.ui.welfare.fragment.GalleryDetailFragment;
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

    private List<MyFollowGalleryInfo> list;
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
            presenter.getData(true);
        }
    };

    @Override
    public void initView() {
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_MINE_FOLLOW_GALLERY, 0);
        initRecyclerView();
        presenter.getData(true);
    }


    private void initRecyclerView() {

        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getData(false);
            }
        });

        list = new ArrayList<>();

        adapter = new MyFollowImageAdapter(getContext(), list);
        //防止item位置互换
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(5)));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.layout_follow) {
                    presenter.cancelFollowImage(position, list.get(position).getId());
                } else if (view.getId() == R.id.layout_zan) {
                    presenter.zanGallery(position, list.get(position).getGallery_id());
                }
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (getParentFragment() != null && getParentFragment() instanceof MyFollowFragment) {
                    ((MyFollowFragment) getParentFragment()).start(GalleryDetailFragment.newInstance(list.get(position).getGallery_id(), list.get(position).getName()));
                }
            }
        });
        View notDataView = getLayoutInflater().inflate(R.layout.layout_empty, (ViewGroup) recyclerView.getParent(), false);
        adapter.setEmptyView(notDataView);
    }

    @Override
    public MyFollowImagePresenter initPresenter() {
        return new MyFollowImagePresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.MY_FOLLOW_IMAGE_TAG);
        super.onDestroyView();
        unbinder.unbind();
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
    public void bindData(List<MyFollowGalleryInfo> data) {
        try {
            list.clear();
            list.addAll(data);
            adapter.notifyDataSetChanged();
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
    public void showLoadingDialog() {
        try {
            StyledDialog.buildLoading().setActivity(_mActivity).show();
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
            list.remove(position);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void zanSuccess(int position) {
        try {
            list.get(position).setGood(list.get(position).getGood() + 1);
            list.get(position).setIs_good(true);
            adapter.notifyItemChanged(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
