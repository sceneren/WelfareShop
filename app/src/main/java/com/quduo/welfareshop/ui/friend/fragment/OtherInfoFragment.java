package com.quduo.welfareshop.ui.friend.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.base.UnlockLisenter;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.FollowEvent;
import com.quduo.welfareshop.event.FriendOtherUserInfoChangeStatusEvent;
import com.quduo.welfareshop.event.UpdateScoreAndDiamondEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.friend.activity.ChatActivity;
import com.quduo.welfareshop.ui.friend.adapter.OtherInfoImageAdapter;
import com.quduo.welfareshop.ui.friend.adapter.OtherInfoVideoAdapter;
import com.quduo.welfareshop.ui.friend.entity.FriendOtherInfoDetailVideoInfo;
import com.quduo.welfareshop.ui.friend.entity.FriendVideoDetailInfo;
import com.quduo.welfareshop.ui.friend.entity.OtherDetailUserInfo;
import com.quduo.welfareshop.ui.friend.presenter.OtherInfoPresenter;
import com.quduo.welfareshop.ui.friend.view.IOtherInfoView;
import com.quduo.welfareshop.ui.mine.activity.AlbumActivity;
import com.quduo.welfareshop.ui.mine.entity.MyUserDetailInfo;
import com.quduo.welfareshop.util.DialogUtils;
import com.quduo.welfareshop.widgets.CustomGridView;
import com.quduo.welfareshop.widgets.RatioImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/2/5 14:57
 * Description:别人的主页
 */
public class OtherInfoFragment extends BaseBackMvpFragment<IOtherInfoView, OtherInfoPresenter> implements IOtherInfoView {
    private static final String ARG_ID = "user_id";
    private static final String ARG_FROM = "from";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.baseimage)
    RatioImageView baseimage;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.follow)
    TextView follow;
    @BindView(R.id.no_photo)
    TextView noPhoto;
    @BindView(R.id.photoGridView)
    CustomGridView photoGridView;
    @BindView(R.id.send_message)
    LinearLayout sendMessage;
    @BindView(R.id.image_layout)
    RelativeLayout imageLayout;
    Unbinder unbinder;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.follow_number)
    TextView followNumber;
    @BindView(R.id.others_show_id)
    TextView othersShowId;
    @BindView(R.id.des)
    TextView des;
    @BindView(R.id.height)
    TextView height;
    @BindView(R.id.weight)
    TextView weight;
    @BindView(R.id.marital)
    TextView marital;
    @BindView(R.id.blood_type)
    TextView bloodType;
    @BindView(R.id.job)
    TextView job;
    @BindView(R.id.star)
    TextView star;
    @BindView(R.id.video_chat_price)
    TextView videoChatPrice;
    @BindView(R.id.video_count)
    TextView videoCount;
    @BindView(R.id.video_time)
    TextView videoTime;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.user_sign)
    TextView userSign;
    @BindView(R.id.video_status_view)
    ImageView videoStatusView;
    @BindView(R.id.video_status_text)
    TextView videoStatusText;
    @BindView(R.id.arrow)
    ImageView arrow;
    @BindView(R.id.video_chat)
    RelativeLayout videoChat;
    @BindView(R.id.btn_follow)
    TextView btnFollow;
    @BindView(R.id.video_avatar_1)
    ImageView videoAvatar1;
    @BindView(R.id.video_avatar_2)
    ImageView videoAvatar2;
    @BindView(R.id.video_avatar_3)
    ImageView videoAvatar3;
    @BindView(R.id.video_chat_text)
    TextView videoChatText;
    @BindView(R.id.no_video)
    TextView noVideo;
    @BindView(R.id.videoGridView)
    CustomGridView videoGridView;

    private String otherUserId;
    private boolean isFromNear = false;


    private List<String> list = new ArrayList<>();
    private OtherInfoImageAdapter adapter;

    private List<FriendOtherInfoDetailVideoInfo> videoList;
    private OtherInfoVideoAdapter videoAdapter;

    private OtherDetailUserInfo detailUserInfo;

    private ArrayList<MyUserDetailInfo.PhotosBean> images;

    public static OtherInfoFragment newInstance(String otherUserId, boolean isFromNear) {
        Bundle args = new Bundle();
        args.putString(ARG_ID, otherUserId);
        args.putBoolean(ARG_FROM, isFromNear);
        OtherInfoFragment fragment = new OtherInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (getArguments() != null) {
            otherUserId = getArguments().getString(ARG_ID, "0");
            isFromNear = getArguments().getBoolean(ARG_FROM, false);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other_info, container, false);
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
            presenter.getData(true);
        }
    };

    @Override
    public void initToolbar() {
        initToolbarNav(toolbar);
    }

    @Override
    public void initView() {
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_FRIEND_OTHERS_INFO, 0);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getData(false);
            }
        });
        adapter = new OtherInfoImageAdapter(getContext(), list);
        photoGridView.setAdapter(adapter);
        photoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toAlbumActivity();
            }
        });

        videoList = new ArrayList<>();
        videoAdapter = new OtherInfoVideoAdapter(getContext(), videoList);
        videoGridView.setAdapter(videoAdapter);
        videoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FriendOtherInfoDetailVideoInfo hotVideoInfo = videoList.get(position);
                FriendVideoDetailInfo detailInfo = new FriendVideoDetailInfo();
                detailInfo.setId(hotVideoInfo.getId());
                detailInfo.setAvatar(hotVideoInfo.getAvatar());
                detailInfo.setContent(hotVideoInfo.getContent());
                detailInfo.setNickName(hotVideoInfo.getNickname());
                detailInfo.setPlay_times(hotVideoInfo.getPlay_times());
                detailInfo.setThumb(hotVideoInfo.getThumb());
                detailInfo.setUserId(hotVideoInfo.getUser_id());
                detailInfo.setVideo_url(hotVideoInfo.getUrl());
                detailInfo.setPrice(hotVideoInfo.getPrice());
                detailInfo.setPayed(hotVideoInfo.isPayed());
                detailInfo.setPosition(position);
                start(FriendVideoDetailFragment.newInstance(detailInfo, FriendVideoDetailFragment.FROM_OTHER_USER_INFO));
            }
        });

        presenter.getData(true);
    }

    @Override
    public OtherInfoPresenter initPresenter() {
        return new OtherInfoPresenter(this);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        OkGo.getInstance().cancelTag(ApiUtil.OTHER_USER_DETAIL_INFO_TAG);
        OkGo.getInstance().cancelTag(ApiUtil.CENCEL_FOLLOW_USER_TAG);
        OkGo.getInstance().cancelTag(ApiUtil.FOLLOW_USER_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.send_message)
    public void onClickSendMessage() {
        if (MyApplication.getInstance().getUserInfo().getUnlock_chat() != 0) {
            toChatMessage();
        } else {
            DialogUtils.getInstance().showUnlockChatDialog(_mActivity, AppConfig.POSITION_FRIEND_OTHERS_INFO, new UnlockLisenter() {
                @Override
                public void unlock() {
                    presenter.unlockChat();
                }
            });
        }
    }

    @OnClick({R.id.follow, R.id.btn_follow})
    public void onClickFollow() {
        if (detailUserInfo.getSubscribe_id() == 0) {
            presenter.followUser();
        } else {
            presenter.cancelFollowUser();
        }
    }

    @Subscribe
    public void currentUserHasFollow(FollowEvent event) {
        try {
            if (detailUserInfo != null) {
                detailUserInfo.setSubscribe_id(event.getFollowId());
                showFollowStates();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.back)
    public void onClickBack() {
        _mActivity.onBackPressed();
    }

    @OnClick(R.id.video_chat)
    public void onClickVideoChat() {
        try {
            if (MyApplication.getInstance().getUserInfo().getScore() > MyApplication.getInstance().getUserInfo().getChat_price()) {
                DialogUtils.getInstance().showVideoChatNoticeDialog(_mActivity, detailUserInfo.getAvatar(), detailUserInfo.getNickname());
            } else {
                DialogUtils.getInstance().showVideoChatScoreNoEnough(_mActivity, AppConfig.POSITION_FRIEND_OTHERS_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void toChatMessage() {
        Intent intent = new Intent(_mActivity, ChatActivity.class);
        intent.putExtra("ID", String.valueOf(detailUserInfo.getId()));
        intent.putExtra("NICKNAME", detailUserInfo.getNickname());
        intent.putExtra("IS_FOLLOW", detailUserInfo.getSubscribe_id() != 0);
        intent.putExtra("NEARBY", isFromNear);
        intent.putExtra("OTHERAVATAR", detailUserInfo.getAvatar());
        startActivity(intent);
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @Override
    public void bindData(OtherDetailUserInfo data) {
        try {
            detailUserInfo = data;
            videoChatPrice.setText(String.valueOf(MyApplication.getInstance().getUserInfo().getChat_price()));
            GlideApp.with(this)
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.getCover())
                    .placeholder(R.drawable.ic_default_cover)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(image);
            GlideApp.with(this)
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.getAvatar())
                    .placeholder(R.drawable.ic_default_avatar)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(avatar);
            nickname.setText(StringUtils.isTrimEmpty(data.getNickname()) ? "游客" : data.getNickname());
            followNumber.setText(MessageFormat.format("粉丝：{0}人", data.getSubscribe()));
            othersShowId.setText(MessageFormat.format("ID:{0}", data.getId()));
            showFollowStates();

            if (data.getPhotos().size() > 0) {
                imageLayout.setVisibility(View.VISIBLE);
                noPhoto.setVisibility(View.GONE);
            } else {
                imageLayout.setVisibility(View.GONE);
                noPhoto.setVisibility(View.VISIBLE);
            }
            list.clear();
            list.addAll(data.getPhotos());
            adapter.notifyDataSetChanged();
            des.setText(data.getSignature());
            userSign.setText(data.getSignature());
            videoCount.setText(MessageFormat.format("视频人数:{0}次", data.getVideo_times()));
            videoTime.setText(MessageFormat.format("视频总时长:{0}分", data.getVideo_total_time()));
            if (!StringUtils.isEmpty(data.getHeight())) {
                if (data.getHeight().toUpperCase().endsWith("CM")) {
                    height.setText(data.getHeight());
                } else {
                    height.setText(MessageFormat.format("{0}CM", data.getHeight()));
                }
            }
            if (!StringUtils.isEmpty(data.getWeight())) {
                if (data.getWeight().toUpperCase().endsWith("KG")) {
                    weight.setText(data.getWeight());
                } else {
                    weight.setText(MessageFormat.format("{0}KG", data.getWeight()));
                }
            }

            marital.setText(data.getMarital());
            bloodType.setText(data.getBlood_type());
            job.setText(data.getJob());
            star.setText(data.getStar());


            if (data.getGood_users() != null && data.getGood_users().size() > 0) {
                String zanStr = data.getGood_users().get(0).getNickname();
                if (zanStr.length() > 4) {
                    zanStr = zanStr.substring(0, 4);
                }
                zanStr += "...等" + data.getVideo_times() + "人和TA视频过";
                videoChatText.setText(zanStr);

                if (data.getGood_users().size() == 1) {
                    videoAvatar1.setVisibility(View.VISIBLE);
                    videoAvatar2.setVisibility(View.GONE);
                    videoAvatar3.setVisibility(View.GONE);
                    GlideApp.with(OtherInfoFragment.this)
                            .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.getGood_users().get(0).getAvatar())
                            .circleCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.ic_default_avatar)
                            .into(videoAvatar1);

                } else if (data.getGood_users().size() == 2) {
                    videoAvatar1.setVisibility(View.VISIBLE);
                    videoAvatar2.setVisibility(View.VISIBLE);
                    videoAvatar3.setVisibility(View.GONE);
                    GlideApp.with(OtherInfoFragment.this)
                            .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.getGood_users().get(0).getAvatar())
                            .circleCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.ic_default_avatar)
                            .into(videoAvatar1);
                    GlideApp.with(OtherInfoFragment.this)
                            .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.getGood_users().get(1).getAvatar())
                            .circleCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.ic_default_avatar)
                            .into(videoAvatar2);
                } else {
                    videoAvatar1.setVisibility(View.VISIBLE);
                    videoAvatar2.setVisibility(View.VISIBLE);
                    videoAvatar3.setVisibility(View.VISIBLE);
                    GlideApp.with(OtherInfoFragment.this)
                            .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.getGood_users().get(0).getAvatar())
                            .circleCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.ic_default_avatar)
                            .into(videoAvatar1);
                    GlideApp.with(OtherInfoFragment.this)
                            .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.getGood_users().get(1).getAvatar())
                            .circleCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.ic_default_avatar)
                            .into(videoAvatar2);
                    GlideApp.with(OtherInfoFragment.this)
                            .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.getGood_users().get(2).getAvatar())
                            .circleCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.ic_default_avatar)
                            .into(videoAvatar3);
                }

            } else {
                videoChatText.setText("");
                videoAvatar1.setVisibility(View.GONE);
                videoAvatar2.setVisibility(View.GONE);
                videoAvatar3.setVisibility(View.GONE);
            }

            if (data.getVideos() == null || data.getVideos().size() == 0) {
                noVideo.setVisibility(View.VISIBLE);
                videoGridView.setVisibility(View.GONE);
            } else {
                noVideo.setVisibility(View.GONE);
                videoGridView.setVisibility(View.VISIBLE);
                videoList.clear();
                videoList.addAll(data.getVideos());
                videoAdapter.notifyDataSetChanged();
            }

            if (data.isBusy()) {
                videoStatusText.setText("视频通话中");
                videoStatusView.setImageResource(R.drawable.ic_friend_status_1);
            } else {
                videoStatusText.setText("空闲");
                videoStatusView.setImageResource(R.drawable.ic_friend_status_0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTargetUserId() {
        return otherUserId;
    }

    @Override
    public double getLongitude() {
        return MyApplication.getInstance().getLongitude();
    }

    @Override
    public double getLatitude() {
        return MyApplication.getInstance().getLatitude();
    }

    @Override
    public int getFromNearby() {
        return isFromNear ? 1 : 0;
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
    public void showMessage(String message) {
        try {
            ToastUtils.showShort(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void hideLoadingDialog() {
        try {
            StyledDialog.dismissLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void followSuccess(int followId) {
        try {
            detailUserInfo.setSubscribe_id(followId);
            showFollowStates();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelFollowSuccess() {
        detailUserInfo.setSubscribe_id(0);
        showFollowStates();
    }

    @Override
    public int getFollowId() {
        return detailUserInfo.getSubscribe_id();
    }

    @Override
    public void showAlert(String message) {
        try {
            if (message.equals("积分不足")) {
                DialogUtils.getInstance().showChatNeedRechargeDialog(_mActivity, AppConfig.POSITION_FRIEND_OTHERS_INFO);
                return;
            }
            StyledDialog.buildIosAlert("提示", message, new MyDialogListener() {
                @Override
                public void onFirst() {

                }

                @Override
                public void onSecond() {

                }
            }).setActivity(_mActivity).setBtnText("确定").show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unlockChatSuccess(int currentScore) {
        try {
            MyApplication.getInstance().getUserInfo().setUnlock_chat(1);
            MyApplication.getInstance().getUserInfo().setScore(currentScore);
            EventBus.getDefault().post(new UpdateScoreAndDiamondEvent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.arrow)
    public void onClickArrow() {
        toAlbumActivity();
    }

    private void showFollowStates() {
        try {
            if (detailUserInfo.getSubscribe_id() != 0) {
                follow.setText("已关注");
                follow.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(_mActivity, R.drawable.ic_other_info_follow_s), null, null);
                btnFollow.setText("已关注");
            } else {
                follow.setText("关注");
                btnFollow.setText("+关注");
                follow.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(_mActivity, R.drawable.ic_other_info_follow_d), null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toAlbumActivity() {
        Intent intent = new Intent(_mActivity, AlbumActivity.class);
        intent.putExtra(AlbumActivity.ARG_IS_MINE, false);
        if (images == null) {
            images = new ArrayList<>();
        }
        images.clear();
        for (String str : list) {
            MyUserDetailInfo.PhotosBean bean = new MyUserDetailInfo.PhotosBean();
            bean.setUrl(str);
            images.add(bean);
        }
        intent.putExtra(AlbumActivity.ARG_IMAGES, images);
        startActivity(intent);
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @Subscribe
    public void changePayStatus(FriendOtherUserInfoChangeStatusEvent event) {
        try {
            videoList.get(event.getPosition()).setPayed(true);
            videoAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
