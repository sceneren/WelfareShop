package com.quduo.welfareshop.ui.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.RechargeActivity;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.event.UpdateAvatarEvent;
import com.quduo.welfareshop.event.UpdateMyInfoSuccessEvent;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.mine.presenter.MinePresenter;
import com.quduo.welfareshop.ui.mine.view.IMineView;
import com.quduo.welfareshop.ui.shop.fragment.ServiceCenterActivity;
import com.quduo.welfareshop.widgets.CircleImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的主界面
 * Created by scene on 2018/1/25.
 */

public class MineFragment extends BaseBackMvpFragment<IMineView, MinePresenter> implements IMineView {
    Unbinder unbinder;
    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.score_number)
    TextView scoreNumber;
    @BindView(R.id.diamonds_number)
    TextView diamondsNumber;
    @BindView(R.id.show_id)
    TextView showId;
    @BindView(R.id.follow_number)
    TextView followNumber;

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void initView() {
        GlideApp.with(this)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + MyApplication.getInstance().getUserInfo().getAvatar())
                .into(avatar);
        nickname.setText(MyApplication.getInstance().getUserInfo().getNickname());
        scoreNumber.setText(String.valueOf(MyApplication.getInstance().getUserInfo().getScore()));
        diamondsNumber.setText(String.valueOf(MyApplication.getInstance().getUserInfo().getDiamond()));
        showId.setText(String.valueOf(MyApplication.getInstance().getUserInfo().getId()));
        followNumber.setText(String.valueOf(MyApplication.getInstance().getUserInfo().getSubscribe()));
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
    public MinePresenter initPresenter() {
        return new MinePresenter(this);
    }


    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.toolbar_back)
    public void onClickToolbarBack() {
        _mActivity.onBackPressed();
    }

    @OnClick(R.id.avatar)
    public void onClickAvatar() {
        start(MyInfoFragment.newInstance());
    }

    @OnClick(R.id.my_order)
    public void onClickMyOrder() {
        start(MyOrderFragment.newInstance());
    }

    @OnClick(R.id.my_follow_goods)
    public void onClickMyFollowGoods() {
        start(MyGoodsFragment.newInstance());
    }

    @OnClick(R.id.my_video)
    public void onClickMyVideo() {
        start(MyFollowFragment.newInstance(0));
    }

    @OnClick(R.id.my_novel)
    public void onClickMyNovel() {
        start(MyFollowFragment.newInstance(1));
    }

    @OnClick(R.id.my_photo)
    public void onClickMyPhoto() {
        start(MyFollowFragment.newInstance(2));
    }

    @OnClick(R.id.my_coupon)
    public void onClickMyCoupon() {
        start(MyCouponFragment.newInstance());
    }

    @OnClick(R.id.my_receiver)
    public void onClickMyReceiver() {
        start(MyReceiverFragment.newInstance());
    }

    @OnClick(R.id.user_agreement)
    public void onClickUserAgreement() {
        startActivity(new Intent(_mActivity, ServiceCenterActivity.class));
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @OnClick(R.id.to_recharge)
    public void onClickToRecharge() {
        startActivity(new Intent(getContext(), RechargeActivity.class));
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @Subscribe
    public void uploadAvatar(UpdateAvatarEvent event) {
        try {
            MyApplication.getInstance().getUserInfo().setAvatar(event.getAvatarPath());
            GlideApp.with(this)
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.drawable.ic_default_avatar)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + MyApplication.getInstance().getUserInfo().getAvatar())
                    .into(avatar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void updateMyInfoSuccess(UpdateMyInfoSuccessEvent event) {
        try {
            nickname.setText(MyApplication.getInstance().getUserInfo().getNickname());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
