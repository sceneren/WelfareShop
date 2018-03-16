package com.quduo.welfareshop.ui.welfare.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.welfare.adapter.SmallVideoAdapter;
import com.quduo.welfareshop.ui.welfare.entity.SmallVideoResultInfo;
import com.quduo.welfareshop.ui.welfare.entity.VideoInfo;
import com.quduo.welfareshop.ui.welfare.presenter.SmallVideoPresenter;
import com.quduo.welfareshop.ui.welfare.view.ISmallVideoView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayer;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/2/9 9:51
 * Description:小视频
 */

public class SmallVideoFragment extends BaseMvpFragment<ISmallVideoView, SmallVideoPresenter> implements ISmallVideoView {
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;

    private List<VideoInfo> videoInfoList = new ArrayList<>();
    private SmallVideoAdapter adapter;

    private int currentPage = 1;

    public static SmallVideoFragment newInstance() {
        Bundle args = new Bundle();
        SmallVideoFragment fragment = new SmallVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welfare_small_video, container, false);
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
            presenter.getSmallVideoData(currentPage, true);
        }
    };

    @Override
    public void initView() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getSmallVideoData(1, false);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                presenter.getSmallVideoData(currentPage + 1, false);
            }
        });

        adapter = new SmallVideoAdapter(getContext(), videoInfoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                try {
                    JZVideoPlayer jzvd = view.findViewById(R.id.video_player);
                    JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    if (jzvd != null && JZUtils.dataSourceObjectsContainsUri(jzvd.dataSourceObjects, JZMediaManager.getCurrentDataSource())) {
                        JZVideoPlayer.releaseAllVideos();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        presenter.getSmallVideoData(currentPage, true);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.btn_follow) {
                    if (videoInfoList.get(position).getFavor_id() != 0) {
                        presenter.cancelFollow(position, videoInfoList.get(position).getFavor_id());
                    } else {
                        presenter.followVideo(position, videoInfoList.get(position).getId());
                    }
                } else {
                    if (!videoInfoList.get(position).isIs_good()) {
                        presenter.zan(position, videoInfoList.get(position).getId());
                    }
                }
            }
        });
    }

    @Override
    public SmallVideoPresenter initPresenter() {
        return new SmallVideoPresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.SMALL_VIDEO_LIST_TAG);
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
    public void bindData(SmallVideoResultInfo data) {
        try {
            currentPage = data.getCurrent_page();
            refreshLayout.setEnableLoadMore(currentPage < data.getLast_page());
            if (currentPage == 1) {
                videoInfoList.clear();
            }
            videoInfoList.addAll(data.getData());
            adapter.notifyDataSetChanged();
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
    public void followSuccess(int position, int followId) {
        try {
            videoInfoList.get(position).setFavor_id(followId);
            adapter.notifyItemChanged(position, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelFollowSuccess(int position) {
        try {
            videoInfoList.get(position).setFavor_id(0);
            adapter.notifyItemChanged(position, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void zanSuccess(int position) {
        try {
            videoInfoList.get(position).setIs_good(true);
            adapter.notifyItemChanged(position, videoInfoList.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
