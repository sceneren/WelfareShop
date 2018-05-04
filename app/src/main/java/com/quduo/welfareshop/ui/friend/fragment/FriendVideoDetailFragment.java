package com.quduo.welfareshop.ui.friend.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hss01248.dialog.StyledDialog;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.base.UnlockLisenter;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.FriendHotVideoChangeStatusEvent;
import com.quduo.welfareshop.event.FriendInteractChangeStatusEvent;
import com.quduo.welfareshop.event.FriendOtherUserInfoChangeStatusEvent;
import com.quduo.welfareshop.event.UpdateScoreAndDiamondEvent;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.friend.entity.FriendVideoDetailInfo;
import com.quduo.welfareshop.ui.friend.presenter.FriendVideoDetailPresenter;
import com.quduo.welfareshop.ui.friend.view.IFriendVideoDetailView;
import com.quduo.welfareshop.util.DialogUtils;
import com.quduo.welfareshop.widgets.MyVideoPlayer;
import com.quduo.welfareshop.widgets.RatioImageView;

import org.greenrobot.eventbus.EventBus;

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jzvd.JZVideoPlayer;

public class FriendVideoDetailFragment extends BaseBackMvpFragment<IFriendVideoDetailView, FriendVideoDetailPresenter> implements IFriendVideoDetailView {
    private static final String ARG_DATA = "data";
    private static final String ARG_FROM = "from";
    public static final int FROM_INTERACT = 1;
    public static final int FROM_HOT_VIDEO = 2;
    public static final int FROM_OTHER_USER_INFO = 3;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.baseimage)
    RatioImageView baseimage;
    @BindView(R.id.video_player)
    MyVideoPlayer videoPlayer;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.play_count)
    TextView playCount;
    Unbinder unbinder;

    private FriendVideoDetailInfo detailInfo;

    private int from = 0;

    public static FriendVideoDetailFragment newInstance(FriendVideoDetailInfo data, int from) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA, data);
        args.putInt(ARG_FROM, from);
        FriendVideoDetailFragment fragment = new FriendVideoDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            detailInfo = (FriendVideoDetailInfo) getArguments().getSerializable(ARG_DATA);
            from = getArguments().getInt(ARG_FROM, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_video_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void initToolbar() {
        initToolbarNav(toolbar);
    }

    @Override
    public void initView() {
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_FRIEND_VIDEO_DETAIL, detailInfo.getId());
        GlideApp.with(this)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + detailInfo.getAvatar())
                .circleCrop()
                .placeholder(R.drawable.ic_default_avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(avatar);

        nickname.setText(detailInfo.getNickName());
        playCount.setText(MessageFormat.format("{0}次播放", detailInfo.getPlay_times()));

        GlideApp.with(this)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + detailInfo.getThumb())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(videoPlayer.thumbImageView);

        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        videoPlayer.setUp(detailInfo.getVideo_url(), JZVideoPlayer.SCREEN_WINDOW_LIST, "");
        videoPlayer.setCurrentInfo(detailInfo.isPayed(), detailInfo.getId(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.getInstance().showNeedUnlockDialog(_mActivity, detailInfo.getPrice(), MyApplication.getInstance().getUserInfo().getScore(), AppConfig.POSITION_FRIEND_VIDEO_DETAIL, new UnlockLisenter() {
                    @Override
                    public void unlock() {
                        presenter.unlockVideo(detailInfo.getId());
                    }
                });
            }
        });
        content.setText(detailInfo.getContent());
    }

    @Override
    public FriendVideoDetailPresenter initPresenter() {
        return new FriendVideoDetailPresenter(this);
    }

    @Override
    public void showLoadingPage() {

    }

    @Override
    public void showContentPage() {

    }

    @Override
    public void showErrorPage() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
    public void showMessage(String message) {
        try {
            ToastUtils.showShort(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unlockSuccess(int currentScore) {
        try {
            MyApplication.getInstance().getUserInfo().setScore(currentScore);
            EventBus.getDefault().post(new UpdateScoreAndDiamondEvent());
            switch (from) {
                case FROM_HOT_VIDEO:
                    EventBus.getDefault().post(new FriendHotVideoChangeStatusEvent(detailInfo.getPosition()));
                    break;
                case FROM_INTERACT:
                    EventBus.getDefault().post(new FriendInteractChangeStatusEvent(detailInfo.getPosition()));
                    break;
                case FROM_OTHER_USER_INFO:
                    EventBus.getDefault().post(new FriendOtherUserInfoChangeStatusEvent(detailInfo.getPosition()));
                    break;
            }
            detailInfo.setPayed(true);
            videoPlayer.setPayed(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.close)
    public void onClickClose() {
        _mActivity.onBackPressed();
    }

    @OnClick(R.id.layout_to_user_detail)
    public void onClickLayoutToVideoDetail() {
        JZVideoPlayer.releaseAllVideos();
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        start(OthersHomePageFragment.newInstance(detailInfo.getUserId(), 0));
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }
}
