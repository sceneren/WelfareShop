package com.quduo.welfareshop.ui.welfare.fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.RechargeActivity;
import com.quduo.welfareshop.activity.ShowWebActivity;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.UpdateScoreAndDiamondEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.welfare.entity.LsjInfo;
import com.quduo.welfareshop.ui.welfare.presenter.LsjPresenter;
import com.quduo.welfareshop.ui.welfare.view.ILsjView;
import com.quduo.welfareshop.widgets.MyVideoPlayer;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunsky.marqueeview.MarqueeView;

import org.greenrobot.eventbus.EventBus;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jzvd.JZVideoPlayer;
import wiki.scene.loadmore.StatusViewLayout;

public class LsjFragment extends BaseMvpFragment<ILsjView, LsjPresenter> implements ILsjView {
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.number_1)
    TextView number1;
    @BindView(R.id.number_2)
    TextView number2;
    @BindView(R.id.number_3)
    TextView number3;
    @BindView(R.id.number_4)
    TextView number4;
    @BindView(R.id.number_5)
    TextView number5;
    @BindView(R.id.refresh_layout1)
    SmartRefreshLayout refreshLayout1;
    @BindView(R.id.status_view1)
    StatusViewLayout statusView1;
    @BindView(R.id.pc_number)
    TextView pcNumber;
    @BindView(R.id.pc_web)
    TextView pcWeb;
    @BindView(R.id.pc_video)
    TextView pcVideo;
    @BindView(R.id.android_number)
    TextView androidNumber;
    @BindView(R.id.android_web)
    TextView androidWeb;
    @BindView(R.id.android_video)
    TextView androidVideo;
    @BindView(R.id.ios_number)
    TextView iosNumber;
    @BindView(R.id.ios_web)
    TextView iosWeb;
    @BindView(R.id.ios_video)
    TextView iosVideo;
    @BindView(R.id.refresh_layout2)
    SmartRefreshLayout refreshLayout2;
    Unbinder unbinder;

    private LsjInfo info;
    private List<View> views;

    public static LsjFragment newInstance() {
        Bundle args = new Bundle();
        LsjFragment fragment = new LsjFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welfare_lsj, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initView() {
        initRefreshLayout();
        presenter.getData(true);
    }

    private void initRefreshLayout() {
        refreshLayout1.setEnableLoadMore(false);
        refreshLayout1.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getData(false);
            }
        });
        refreshLayout2.setEnableLoadMore(false);
        refreshLayout2.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getData(false);
            }
        });
    }

    private void bindMarqueeView(List<String> list) {
        try {
            if (list != null && list.size() > 0) {
                if (views == null) {
                    views = new ArrayList<>();
                } else {
                    views.clear();
                }
                marqueeView.stopFlipping();
                views.clear();
                marqueeView.removeAllViews();
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    View itemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_welfare_lsj_item, marqueeView, false);
                    TextView nickName = itemView.findViewById(R.id.nickname);
                    nickName.setText(list.get(i));
                    views.add(itemView);
                }
                marqueeView.setViews(views);
                marqueeView.startFlipping();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public LsjPresenter initPresenter() {
        return new LsjPresenter(this);
    }

    @Override
    public void showLoadingPage() {
        try {
            statusView1.showLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showContentPage() {
        try {
            statusView1.showContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showErrorPage() {
        try {
            statusView1.showFailed(retry1Listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener retry1Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.getData(true);
        }
    };

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.UNLOCK_TECH_TAG);
        OkGo.getInstance().cancelTag(ApiUtil.TECH_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }

    private String[] num2StringArray(int number) {
        String numberStr = String.valueOf(number);
        char[] chars = numberStr.toCharArray();
        String[] nums = new String[5];
        if (chars.length == 0) {
            nums[0] = String.valueOf(0);
            nums[1] = String.valueOf(0);
            nums[2] = String.valueOf(0);
            nums[3] = String.valueOf(0);
            nums[4] = String.valueOf(0);
        } else if (chars.length == 1) {
            nums[0] = String.valueOf(0);
            nums[1] = String.valueOf(0);
            nums[2] = String.valueOf(0);
            nums[3] = String.valueOf(0);
            nums[4] = String.valueOf(chars[0]);
        } else if (chars.length == 2) {
            nums[0] = String.valueOf(0);
            nums[1] = String.valueOf(0);
            nums[2] = String.valueOf(0);
            nums[3] = String.valueOf(chars[1]);
            nums[4] = String.valueOf(chars[0]);
        } else if (chars.length == 3) {
            nums[0] = String.valueOf(0);
            nums[1] = String.valueOf(0);
            nums[2] = String.valueOf(chars[2]);
            nums[3] = String.valueOf(chars[1]);
            nums[4] = String.valueOf(chars[0]);
        } else if (chars.length == 4) {
            nums[0] = String.valueOf(0);
            nums[1] = String.valueOf(chars[3]);
            nums[2] = String.valueOf(chars[2]);
            nums[3] = String.valueOf(chars[1]);
            nums[4] = String.valueOf(chars[0]);
        } else if (chars.length == 5) {
            nums[0] = String.valueOf(chars[4]);
            nums[1] = String.valueOf(chars[3]);
            nums[2] = String.valueOf(chars[2]);
            nums[3] = String.valueOf(chars[1]);
            nums[4] = String.valueOf(chars[0]);
        }
        return nums;
    }

    @OnClick(R.id.learn_now)
    public void onClickLearnNow() {
        try {
            if (info != null) {
                if (info.getPrice() > MyApplication.getInstance().getUserInfo().getScore()) {
                    StyledDialog.buildIosAlert("观看全套教程需" + info.getPrice() + "积分", "您的积分：" + MyApplication.getInstance().getUserInfo().getScore() + "\n\n积分不足请先充值", new MyDialogListener() {
                        @Override
                        public void onFirst() {
                            toRechargeActivity();
                        }

                        @Override
                        public void onSecond() {

                        }
                    })
                            .setBtnText("充值")
                            .setActivity(_mActivity)
                            .show();
                } else {
                    StyledDialog.buildIosAlert("观看全套教程需" + info.getPrice() + "积分", "您的积分：" + MyApplication.getInstance().getUserInfo().getScore(), new MyDialogListener() {
                        @Override
                        public void onFirst() {
                            presenter.unlock();
                        }

                        @Override
                        public void onSecond() {

                        }
                    })
                            .setBtnText("确定")
                            .setActivity(_mActivity)
                            .show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refreshFinish1() {
        try {
            refreshLayout1.finishRefresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refreshFinish2() {
        try {
            refreshLayout2.finishRefresh();
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
    public void alert(String message) {
        try {
            StyledDialog.buildIosAlert("提示", message, null)
                    .setActivity(_mActivity)
                    .setBtnText("确定")
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void bindData(LsjInfo data) {
        try {
            info = data;
            if (data.isPayed()) {
                MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_TECHED, 0);
                pcNumber.setText(MessageFormat.format("{0}人已学习", data.getPc()));
                androidNumber.setText(MessageFormat.format("{0}人已学习", data.getAndroid()));
                iosNumber.setText(MessageFormat.format("{0}人已学习", data.getIos()));
                refreshLayout1.setVisibility(View.GONE);
                refreshLayout2.setVisibility(View.VISIBLE);
            } else {
                MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_UNTECH, 0);
                String[] totalNums = num2StringArray(data.getTotal());
                number1.setText(totalNums[0]);
                number2.setText(totalNums[1]);
                number3.setText(totalNums[2]);
                number4.setText(totalNums[3]);
                number5.setText(totalNums[4]);
                bindMarqueeView(data.getUsers());
                refreshLayout1.setVisibility(View.VISIBLE);
                refreshLayout2.setVisibility(View.GONE);
            }

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
    public void unlockSuccess(int score) {
        try {
            StyledDialog.buildIosAlert("提示", "解锁成功", null)
                    .setActivity(_mActivity)
                    .setBtnText("确定")
                    .show();
            info.setPayed(true);
            MyApplication.getInstance().getUserInfo().setScore(score);
            EventBus.getDefault().post(new UpdateScoreAndDiamondEvent());
            refreshLayout2.setVisibility(View.VISIBLE);
            refreshLayout1.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toRechargeActivity() {
        Intent intent = new Intent(_mActivity, RechargeActivity.class);
        intent.putExtra(RechargeActivity.ARG_FROM_POSITION, AppConfig.POSITION_UNTECH);
        _mActivity.startActivity(intent);
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @OnClick(R.id.pc_web)
    public void onClickPCWeb() {
        if (info != null && info.isPayed()) {
            toShowWebActivity(info.getPc_url(), AppConfig.POSITION_PC_WEB);
        }
    }

    @OnClick(R.id.android_web)
    public void onClickAndroidWeb() {
        if (info != null && info.isPayed()) {
            toShowWebActivity(info.getAndroid_url(), AppConfig.POSITION_ANDROID_WEB);
        }
    }

    @OnClick(R.id.ios_web)
    public void onClickIosWeb() {
        if (info != null && info.isPayed()) {
            toShowWebActivity(info.getIos_url(), AppConfig.POSITION_IOS_WEB);
        }
    }

    @OnClick(R.id.pc_video)
    public void onClickPCVideo() {
        if (info != null && info.isPayed()) {
            showVideo(info.getPc_video());
        }
    }

    @OnClick(R.id.android_video)
    public void onClickAndroidVideo() {
        if (info != null && info.isPayed()) {
            showVideo(info.getAndroid_video());
        }
    }

    @OnClick(R.id.ios_video)
    public void onClickIosVideo() {
        if (info != null && info.isPayed()) {
            showVideo(info.getIos_video());
        }
    }

    private void toShowWebActivity(String url, int position) {
        Intent intent = new Intent(_mActivity, ShowWebActivity.class);
        intent.putExtra(ShowWebActivity.ARG_URL, url);
        intent.putExtra(ShowWebActivity.ARG_POSITION, position);
        startActivity(intent);
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    private void showVideo(String videoUrl) {
        try {
            JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
            MyVideoPlayer.startFullscreen(_mActivity, MyVideoPlayer.class, videoUrl, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
