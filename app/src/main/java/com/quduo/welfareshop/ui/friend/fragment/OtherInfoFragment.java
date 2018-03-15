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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.RechargeActivity;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.event.FollowEvent;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.friend.activity.ChatActivity;
import com.quduo.welfareshop.ui.friend.activity.VideoChatActivity;
import com.quduo.welfareshop.ui.friend.adapter.OtherInfoImageAdapter;
import com.quduo.welfareshop.ui.friend.dialog.EditUserInfoStep1Dialog;
import com.quduo.welfareshop.ui.friend.dialog.OpenChatDialog;
import com.quduo.welfareshop.ui.friend.dialog.ToRechargeDialog;
import com.quduo.welfareshop.ui.friend.dialog.VideoChatToRechargeDialog;
import com.quduo.welfareshop.ui.friend.presenter.OtherInfoPresenter;
import com.quduo.welfareshop.ui.friend.view.IOtherInfoView;
import com.quduo.welfareshop.widgets.CustomGridView;
import com.quduo.welfareshop.widgets.RatioImageView;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
    private String otherUserId;
    private boolean isFromNear = false;

    private ToRechargeDialog rechargeDialog;
    private OpenChatDialog openChatDialog;
    private VideoChatToRechargeDialog videoChatToRechargeDialog;

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

    }

    @Override
    public void showContentPage() {

    }

    @Override
    public void showErrorPage() {

    }

    @Override
    public void initToolbar() {
        initToolbarNav(toolbar);
    }

    @Override
    public void initView() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        OtherInfoImageAdapter adapter = new OtherInfoImageAdapter(getContext(), list);
        photoGridView.setAdapter(adapter);
        imageLayout.setVisibility(View.VISIBLE);
        noPhoto.setVisibility(View.GONE);

        GlideApp.with(this)
                .asBitmap()
                .centerCrop()
                .load("http://e.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg")
                .into(image);
    }

    @Override
    public OtherInfoPresenter initPresenter() {
        return new OtherInfoPresenter(this);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
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
        if (follow.getText().toString().equals("关注")) {
            follow.setText("已关注");
            follow.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(getContext(), R.drawable.ic_other_info_follow_s), null, null);
        } else {
            follow.setText("关注");
            follow.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(getContext(), R.drawable.ic_other_info_follow_d), null, null);
        }
    }

    @Subscribe
    public void currentUserHasFollow(FollowEvent event) {
        follow.setText("已关注");
        follow.setTextColor(ContextCompat.getColor(getContext(), R.color.theme_color));
        follow.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(getContext(), R.drawable.ic_guanzhu_s), null, null);
    }

    @OnClick(R.id.back)
    public void onClickBack() {
        _mActivity.onBackPressed();
    }

    @OnClick(R.id.video_chat)
    public void onClickVideoChat() {
        startActivity(new Intent(_mActivity, VideoChatActivity.class));
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    private void showRechargeDialog() {
        if (rechargeDialog == null) {
            rechargeDialog = new ToRechargeDialog(getContext());
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
            videoChatToRechargeDialog = new VideoChatToRechargeDialog(getContext());
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
            openChatDialog = new OpenChatDialog(getContext());
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
        intent.putExtra("ID", otherUserId);
        intent.putExtra("NICKNAME", "小周" + otherUserId);
        intent.putExtra("IS_FOLLOW", !follow.getText().toString().equals("关注"));
        intent.putExtra("OTHERAVATAR", "http://e.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg");
        startActivity(intent);
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

}
