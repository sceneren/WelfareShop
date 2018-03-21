package com.quduo.welfareshop.ui.red.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anbetter.danmuku.DanMuView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.event.UpdateMyInfoSuccessEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMainMvpFragment;
import com.quduo.welfareshop.ui.mine.fragment.MineFragment;
import com.quduo.welfareshop.ui.red.dialog.GetRedDialog;
import com.quduo.welfareshop.ui.red.dialog.NeedGetDiamondDialog;
import com.quduo.welfareshop.ui.red.entity.RedBuyInfo;
import com.quduo.welfareshop.ui.red.entity.RedResultInfo;
import com.quduo.welfareshop.ui.red.entity.RedWinInfo;
import com.quduo.welfareshop.ui.red.presenter.RedPresenter;
import com.quduo.welfareshop.ui.red.view.IRedView;
import com.quduo.welfareshop.util.DanMuHelper;
import com.quduo.welfareshop.util.NetTimeUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunsky.marqueeview.MarqueeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.joda.time.DateTime;

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
 * Time:2018/2/1 15:26
 * Description:
 */

public class RedFragment extends BaseMainMvpFragment<IRedView, RedPresenter> implements IRedView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_image_menu)
    ImageView toolbarImageMenu;
    Unbinder unbinder;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.diamonds)
    TextView diamonds;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.cash)
    TextView cash;
    @BindView(R.id.jackpot1)
    TextView jackpot1;
    @BindView(R.id.jackpot2)
    TextView jackpot2;
    @BindView(R.id.jackpot3)
    TextView jackpot3;
    @BindView(R.id.jackpot4)
    TextView jackpot4;
    @BindView(R.id.jackpot5)
    TextView jackpot5;
    @BindView(R.id.jackpot6)
    TextView jackpot6;
    @BindView(R.id.time1)
    TextView time1;
    @BindView(R.id.time2)
    TextView time2;
    @BindView(R.id.time3)
    TextView time3;
    @BindView(R.id.time4)
    TextView time4;
    @BindView(R.id.time5)
    TextView time5;
    @BindView(R.id.time6)
    TextView time6;
    @BindView(R.id.open)
    ImageView open;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.text)
    ImageView text;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.danMuView)
    DanMuView danMuView;

    private GetRedDialog getRedDialog;
    private NeedGetDiamondDialog needGetDiamondDialog;

    private List<View> views = new ArrayList<>();
    private RedResultInfo redResultInfo;

    public static RedFragment newInstance() {
        Bundle args = new Bundle();
        RedFragment fragment = new RedFragment();
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
        View view = inflater.inflate(R.layout.fragment_red, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initToolBar() {
        super.initToolBar();
        toolbarTitle.setText(getString(R.string.tab_red));
    }

    @Override
    public void initView() {
        super.initView();
        initDanmu();
        initRefreshLayout();
        presenter.getData(true);
        getDataDelayed();
    }

    private void initDanmu() {
        try {
            danMuView.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDataDelayed() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(20000);
                    _mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            DateTime dateTime=new DateTime(System.currentTimeMillis());
                            LogUtils.e(dateTime.toString("mm-ss"));
                            presenter.getData(false);
                        }
                    });
                }
            }
        }).start();
    }

    private void initRefreshLayout() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getData(false);
            }
        });
    }

    /**
     * @param endTime 结束时间
     * @param maxPool 最大奖池
     */
    private void showCountDownTimer(long endTime, final int maxPool) {
        long countTime = endTime * 1000 - NetTimeUtils.getWebsiteDatetime();
        new CountDownTimer(countTime, 50) {
            @Override
            public void onTick(long millisUntilFinished) {
                try {
                    long minite = millisUntilFinished / 60000;
                    long second = (millisUntilFinished - 60000 * minite) / 1000;
                    long mills = millisUntilFinished - 60000 * minite - second * 1000;

                    if (minite > 9) {
                        char[] chars = String.valueOf(minite).toCharArray();
                        time1.setText(String.valueOf(chars[0]));
                        time2.setText(String.valueOf(chars[1]));
                    } else {
                        time1.setText(String.valueOf(0));
                        time2.setText(String.valueOf(minite));
                    }

                    if (second > 9) {
                        char[] chars = String.valueOf(second).toCharArray();
                        time3.setText(String.valueOf(chars[0]));
                        time4.setText(String.valueOf(chars[1]));
                    } else {
                        time3.setText(String.valueOf(0));
                        time4.setText(String.valueOf(second));
                    }

                    if (mills > 9) {
                        char[] chars = String.valueOf(mills).toCharArray();
                        time5.setText(String.valueOf(chars[0]));
                        time6.setText(String.valueOf(chars[1]));
                    } else {
                        time5.setText(String.valueOf(0));
                        time6.setText(String.valueOf(mills));
                    }
                    int currentPool = (int) ((maxPool * (600 - millisUntilFinished / 1000)) / 600d);
                    char[] pools = String.valueOf(formatNumber(currentPool)).toCharArray();
                    jackpot6.setText(String.valueOf(pools[5]));
                    jackpot5.setText(String.valueOf(pools[4]));
                    jackpot4.setText(String.valueOf(pools[3]));
                    jackpot3.setText(String.valueOf(pools[2]));
                    jackpot2.setText(String.valueOf(pools[1]));
                    jackpot1.setText(String.valueOf(pools[0]));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFinish() {
                try {
                    cancel();
                    time1.setText(String.valueOf(0));
                    time2.setText(String.valueOf(0));
                    time3.setText(String.valueOf(0));
                    time4.setText(String.valueOf(0));
                    time5.setText(String.valueOf(0));
                    time6.setText(String.valueOf(0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
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
    public RedPresenter initPresenter() {
        return new RedPresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.RED_INDEX_TAG);
        OkGo.getInstance().cancelTag(ApiUtil.BUY_RED_TAG);
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.toolbar_image_menu)
    public void onClickToolBarImageMenu() {
        EventBus.getDefault().post(new StartBrotherEvent(MineFragment.newInstance()));
    }

    @OnClick(R.id.open)
    public void onClickOpen() {
        showGetRedDialog();
    }

    @OnClick(R.id.enter_my_red)
    public void onClickEnterMyRed() {
        EventBus.getDefault().post(new StartBrotherEvent(MyRedFragment.newInstance()));
    }

    @OnClick(R.id.cash)
    public void onClickCash() {
        EventBus.getDefault().post(new StartBrotherEvent(CashFragment.newInstance()));
    }

    @Override
    public void bindData(RedResultInfo data) {
        try {
            redResultInfo = null;
            redResultInfo = data;
            nickname.setText(MyApplication.getInstance().getUserInfo().getNickname());
            diamonds.setText(String.valueOf(MyApplication.getInstance().getUserInfo().getDiamond()));
            money.setText(String.valueOf(MyApplication.getInstance().getUserInfo().getMoney()));
            showCountDownTimer(data.getPeriod().getOpen_time(), data.getPeriod().getPool());
            bindMarqueeView(data.getWin());

            bindDanmuData(data.getBuy());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void bindMarqueeView(List<RedWinInfo> list) {
        try {
            marqueeView.stopFlipping();
            views.clear();
            marqueeView.removeAllViews();
            for (int i = 0; i < list.size(); i = i + 3) {
                View moreView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_red_scroll, null);
                TextView name1 = moreView.findViewById(R.id.name1);
                TextView money1 = moreView.findViewById(R.id.money1);
                TextView name2 = moreView.findViewById(R.id.name2);
                TextView money2 = moreView.findViewById(R.id.money2);
                TextView name3 = moreView.findViewById(R.id.name3);
                TextView money3 = moreView.findViewById(R.id.money3);
                LinearLayout layout2 = moreView.findViewById(R.id.layout2);
                LinearLayout layout3 = moreView.findViewById(R.id.layout3);
                if (list.size() % 3 == 0) {
                    name1.setText(list.get(i).getNickname());
                    money1.setText(MessageFormat.format("{0}元现金", list.get(i).getBonus()));
                    name2.setText(list.get(i + 1).getNickname());
                    money2.setText(MessageFormat.format("{0}元现金", list.get(i + 1).getBonus()));
                    name3.setText(list.get(i + 2).getNickname());
                    money3.setText(MessageFormat.format("{0}元现金", list.get(i + 2).getBonus()));
                } else if (list.size() % 3 == 1) {
                    name1.setText(list.get(i).getNickname());
                    money1.setText(MessageFormat.format("{0}元现金", list.get(i).getBonus()));
                    //不是最后一组
                    if (list.size() >= 3 * i) {
                        layout2.setVisibility(View.VISIBLE);
                        layout3.setVisibility(View.VISIBLE);
                        name2.setText(list.get(i + 1).getNickname());
                        money2.setText(MessageFormat.format("{0}元现金", list.get(i + 1).getBonus()));
                        name3.setText(list.get(i + 2).getNickname());
                        money3.setText(MessageFormat.format("{0}元现金", list.get(i + 2).getBonus()));
                    } else {
                        layout2.setVisibility(View.GONE);
                        layout3.setVisibility(View.GONE);
                    }
                } else {
                    name1.setText(list.get(i).getNickname());
                    money1.setText(MessageFormat.format("{0}元现金", list.get(i).getBonus()));
                    name2.setText(list.get(i + 1).getNickname());
                    money2.setText(MessageFormat.format("{0}元现金", list.get(i + 1).getBonus()));
                    //不是最后一组
                    if (list.size() >= 3 * i) {
                        layout3.setVisibility(View.VISIBLE);
                        name3.setText(list.get(i + 2).getNickname());
                        money3.setText(MessageFormat.format("{0}元现金", list.get(i + 2).getBonus()));
                    } else {
                        layout3.setVisibility(View.GONE);
                    }
                }
                views.add(moreView);
            }
            marqueeView.setViews(views);
            marqueeView.startFlipping();
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
    public void showMessage(String message) {
        try {
            ToastUtils.showShort(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buyRedSuccess(int diamondNumber) {
        try {
            hideGetRedDialog();
            diamonds.setText(String.valueOf(diamondNumber));
            MyApplication.getInstance().getUserInfo().setDiamond(diamondNumber);
            StyledDialog.buildIosAlert("提示", "红包领取成功", new MyDialogListener() {
                @Override
                public void onFirst() {
                    onDismiss();
                }

                @Override
                public void onSecond() {

                }
            }).setBtnText("确定").show();
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
    public void hideGetRedDialog() {
        try {
            if (getRedDialog != null && getRedDialog.isShowing()) {
                getRedDialog.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showNeedGetDiamondDialog() {
        try {
            if (needGetDiamondDialog == null) {
                needGetDiamondDialog = new NeedGetDiamondDialog(_mActivity);
            }
            needGetDiamondDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideNeedGetDiamondDialog() {
        try {
            if (needGetDiamondDialog != null && needGetDiamondDialog.isShowing()) {
                needGetDiamondDialog.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("DefaultLocale")
    private String formatNumber(int number) {
        return String.format("%06d", number);
    }

    @Subscribe
    public void updateMyInfoSuccess(UpdateMyInfoSuccessEvent event) {
        try {
            nickname.setText(MyApplication.getInstance().getUserInfo().getNickname());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showGetRedDialog() {
        if (getRedDialog == null) {
            getRedDialog = new GetRedDialog(_mActivity);
            getRedDialog.setOnClickGetRedListener(new GetRedDialog.OnClickGetRedListener() {
                @Override
                public void onClickGetRed(int number) {
                    presenter.buyRed(number, redResultInfo.getPeriod().getId());
                }
            });
        }
        getRedDialog.show();
    }

    private DanMuHelper danMuHelper;

    private void bindDanmuData(List<RedBuyInfo> data) {
        try {
            if (danMuHelper == null) {
                danMuHelper = new DanMuHelper(getContext());
                danMuHelper.add(danMuView);
            }
            for (int i = 0; i < data.size(); i++) {
                danMuHelper.addDanMu("恭喜" + data.get(i).getNickname() + "抢到了红包");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
