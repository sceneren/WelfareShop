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

import com.blankj.utilcode.util.ToastUtils;
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.PreviewImageActivity;
import com.quduo.welfareshop.activity.RechargeActivity;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.event.FollowEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.friend.activity.ChatActivity;
import com.quduo.welfareshop.ui.friend.activity.VideoChatActivity;
import com.quduo.welfareshop.ui.friend.adapter.OtherInfoImageAdapter;
import com.quduo.welfareshop.ui.friend.dialog.EditUserInfoStep1Dialog;
import com.quduo.welfareshop.ui.friend.dialog.OpenChatDialog;
import com.quduo.welfareshop.ui.friend.dialog.ToRechargeDialog;
import com.quduo.welfareshop.ui.friend.dialog.VideoChatToRechargeDialog;
import com.quduo.welfareshop.ui.friend.entity.OtherDetailUserInfo;
import com.quduo.welfareshop.ui.friend.presenter.OtherInfoPresenter;
import com.quduo.welfareshop.ui.friend.view.IOtherInfoView;
import com.quduo.welfareshop.widgets.CustomGridView;
import com.quduo.welfareshop.widgets.RatioImageView;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;
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
    SelectableRoundedImageView avatar;
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
    private String otherUserId;
    private boolean isFromNear = false;

    private ToRechargeDialog rechargeDialog;
    private OpenChatDialog openChatDialog;
    private VideoChatToRechargeDialog videoChatToRechargeDialog;

    private List<String> list = new ArrayList<>();
    private OtherInfoImageAdapter adapter;
    private OtherDetailUserInfo detailUserInfo;

    private ArrayList<String> imageList;

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
                toPriviewActivity(position);
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
        toChatMessage();

//        showRechargeDialog();

//        showOpenChatDialog();

//        showVideoChatRechargeDialog();

//        showEditUserInfoStep1Dialog();
    }

    @OnClick(R.id.follow)
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
        Intent intent = new Intent(_mActivity, VideoChatActivity.class);
        intent.putExtra(VideoChatActivity.ARG_AVATAR, detailUserInfo.getAvatar());
        intent.putExtra(VideoChatActivity.ARG_NICKNAME, detailUserInfo.getNickname());
        startActivity(intent);
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    private void showRechargeDialog() {
        if (rechargeDialog == null) {
            rechargeDialog = new ToRechargeDialog(_mActivity);
            rechargeDialog.setOnClickToRechargeListener(new ToRechargeDialog.OnClickToRechargeListener() {
                @Override
                public void onClickToRecharge() {
                    startActivity(new Intent(_mActivity, RechargeActivity.class));
                    _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
                }
            });
        }
        rechargeDialog.show();
    }

    private void showVideoChatRechargeDialog() {
        if (videoChatToRechargeDialog == null) {
            videoChatToRechargeDialog = new VideoChatToRechargeDialog(_mActivity);
            videoChatToRechargeDialog.setOnClickToRechargeListener(new VideoChatToRechargeDialog.OnClickToRechargeListener() {
                @Override
                public void onClickToRecharge() {
                    startActivity(new Intent(_mActivity, RechargeActivity.class));
                    _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
                }
            });
        }
        videoChatToRechargeDialog.show();
    }


    private void showOpenChatDialog() {
        if (openChatDialog == null) {
            openChatDialog = new OpenChatDialog(_mActivity);
            openChatDialog.setOnClickOpenChatListener(new OpenChatDialog.OnClickOpenChatListener() {
                @Override
                public void onClickOpenChat() {

                }
            });
        }
        openChatDialog.show();
    }

    private void showEditUserInfoStep1Dialog() {
        Intent intent = new Intent(_mActivity, EditUserInfoStep1Dialog.class);
        startActivity(intent);
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
            GlideApp.with(this)
                    .asBitmap()
                    .centerCrop()
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.getAvatar())
                    .into(image);
            GlideApp.with(this)
                    .asBitmap()
                    .centerCrop()
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.getAvatar())
                    .into(avatar);
            nickname.setText(data.getNickname());
            followNumber.setText(MessageFormat.format("粉丝：{0}", data.getSubscribe()));
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
            height.setText(MessageFormat.format("{0}cm", data.getHeight()));
            weight.setText(MessageFormat.format("{0}kg", data.getWeight()));
            marital.setText(data.getMarital());
            bloodType.setText(data.getBlood_type());
            job.setText(data.getJob());
            star.setText(data.getStar());
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

    @OnClick(R.id.arrow)
    public void onClickArrow() {
        toPriviewActivity(0);
    }

    private void showFollowStates() {
        try {
            if (detailUserInfo.getSubscribe_id() != 0) {
                follow.setText("已关注");
                follow.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(_mActivity, R.drawable.ic_other_info_follow_s), null, null);
            } else {
                follow.setText("关注");
                follow.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(_mActivity, R.drawable.ic_other_info_follow_d), null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toPriviewActivity(int position) {
        Intent intent = new Intent(_mActivity, PreviewImageActivity.class);
        if (imageList == null) {
            imageList = new ArrayList<>();
        }
        imageList.clear();
        for (String url : list) {
            imageList.add(MyApplication.getInstance().getConfigInfo().getFile_domain() + url);
        }
        intent.putExtra(PreviewImageActivity.ARG_URLS, imageList);
        intent.putExtra(PreviewImageActivity.ARG_POSITION, position);
        startActivity(intent);
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }
}
