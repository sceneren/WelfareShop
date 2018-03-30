package com.quduo.welfareshop.ui.mine.fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hss01248.dialog.StyledDialog;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.UnlockLisenter;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.mine.adapter.MyFollowVideoAdapter;
import com.quduo.welfareshop.ui.mine.entity.MyFollowVideoInfo;
import com.quduo.welfareshop.ui.mine.presenter.MyFollowVideoPresenter;
import com.quduo.welfareshop.ui.mine.view.IMyFollowVideoView;
import com.quduo.welfareshop.ui.welfare.activity.VideoDetailActivity;
import com.quduo.welfareshop.util.DialogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/3/1 11:18
 * Description:收藏的视频
 */

public class MyFollowVideoFragment extends BaseMvpFragment<IMyFollowVideoView, MyFollowVideoPresenter> implements IMyFollowVideoView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;

    private List<MyFollowVideoInfo> list = new ArrayList<>();
    private MyFollowVideoAdapter adapter;

    public static MyFollowVideoFragment newInstance() {
        Bundle args = new Bundle();
        MyFollowVideoFragment fragment = new MyFollowVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_follow_video, container, false);
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
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_MINE_FOLLOW_VIDEO, 0);
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

        adapter = new MyFollowVideoAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                if (list.get(position).getCate_id() == AppConfig.VIDEO_TYPE_SMALL_VIDEO) {
                    if (list.get(position).isPayed()) {
                        MyFollowVideoInfo item = list.get(position);
                        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                        JZVideoPlayerStandard.startFullscreen(getContext(), JZVideoPlayerStandard.class, item.getUrl(), item.getName());
                    } else {
                        DialogUtils.getInstance().showNeedUnlockDialog(_mActivity, list.get(position).getPrice(), MyApplication.getInstance().getUserInfo().getScore(), AppConfig.POSITION_MINE_FOLLOW_VIDEO, new UnlockLisenter() {
                            @Override
                            public void unlock() {
                                presenter.unlockVideo(position, list.get(position).getVideo_id());
                            }
                        });
                    }
                } else {
                    toVideoDetailActivity(list.get(position).getVideo_id(), list.get(position).getCate_id());
                }
            }
        });
        View notDataView = getLayoutInflater().inflate(R.layout.layout_empty, (ViewGroup) recyclerView.getParent(), false);
        adapter.setEmptyView(notDataView);
    }

    @Override
    public MyFollowVideoPresenter initPresenter() {
        return new MyFollowVideoPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void bindData(List<MyFollowVideoInfo> data) {
        try {
            list.clear();
            list.addAll(data);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMessage(String message, int position) {
        try {
            if (message.equals("积分不足")) {
                DialogUtils.getInstance().showNeedRechargeScoreDialog(_mActivity, list.get(position).getPrice(), AppConfig.POSITION_MINE_FOLLOW_VIDEO, MyApplication.getInstance().getUserInfo().getScore());
                return;
            }
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
    public void unlockSuccess(int position, int currentScore) {
        try {
            MyApplication.getInstance().getUserInfo().setScore(currentScore);
            list.get(position).setPayed(true);
            adapter.notifyItemChanged(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        return JZVideoPlayer.backPress() || super.onBackPressedSupport();
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            JZVideoPlayer.releaseAllVideos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toVideoDetailActivity(int videoId, int cateId) {
        try {
            Intent intent = new Intent(getContext(), VideoDetailActivity.class);
            intent.putExtra(VideoDetailActivity.ARG_VIDEO_ID, videoId);
            intent.putExtra(VideoDetailActivity.ARG_CATE_ID, cateId);
            startActivity(intent);
            _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
