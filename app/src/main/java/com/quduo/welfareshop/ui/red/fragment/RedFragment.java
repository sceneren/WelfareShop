package com.quduo.welfareshop.ui.red.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.dialog.RedOpenDialog;
import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.event.UpdateMyInfoSuccessEvent;
import com.quduo.welfareshop.mvp.BaseMainMvpFragment;
import com.quduo.welfareshop.ui.mine.fragment.MineFragment;
import com.quduo.welfareshop.ui.red.dialog.GetRedDialog;
import com.quduo.welfareshop.ui.red.dialog.NeedGetScoreDialog;
import com.quduo.welfareshop.ui.red.entity.RedResultInfo;
import com.quduo.welfareshop.ui.red.entity.RedWinInfo;
import com.quduo.welfareshop.ui.red.presenter.RedPresenter;
import com.quduo.welfareshop.ui.red.view.IRedView;
import com.quduo.welfareshop.util.NetTimeUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunsky.marqueeview.MarqueeView;

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
    @BindView(R.id.enter_my_red)
    ImageView enterMyRed;
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

    private RedOpenDialog redOpenDialog;
    private GetRedDialog getRedDialog;
    private NeedGetScoreDialog needGetScoreDialog;

    private List<View> views = new ArrayList<>();

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
        initRefreshLayout();
        presenter.getData(true);
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
        new CountDownTimer(countTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                try {
                    long resetTime = millisUntilFinished / 1000;
                    long hour = resetTime / 3600;
                    long minite = (resetTime - 3600 * hour) / 60;
                    long second = (resetTime - 3600 * hour - 60 * minite);

                    if (hour > 9) {
                        char[] chars = String.valueOf(hour).toCharArray();
                        time1.setText(String.valueOf(chars[0]));
                        time2.setText(String.valueOf(chars[1]));
                    } else {
                        time1.setText(String.valueOf(0));
                        time2.setText(String.valueOf(hour));
                    }

                    if (minite > 9) {
                        char[] chars = String.valueOf(minite).toCharArray();
                        time3.setText(String.valueOf(chars[0]));
                        time4.setText(String.valueOf(chars[1]));
                    } else {
                        time3.setText(String.valueOf(0));
                        time4.setText(String.valueOf(minite));
                    }

                    if (second > 9) {
                        char[] chars = String.valueOf(second).toCharArray();
                        time5.setText(String.valueOf(chars[0]));
                        time6.setText(String.valueOf(chars[1]));
                    } else {
                        time5.setText(String.valueOf(0));
                        time6.setText(String.valueOf(second));
                    }
                    int currentPool = (int) ((maxPool * (600 - resetTime)) / 600d);
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
                    presenter.getData(false);
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
//        if (redOpenDialog == null) {
//            redOpenDialog = new RedOpenDialog(getContext());
//        }
//        if (redOpenDialog.isShowing()) {
//            redOpenDialog.cancel();
//        } else {
//            redOpenDialog.show();
//        }
        if (getRedDialog == null) {
            getRedDialog = new GetRedDialog(_mActivity);
        }
        getRedDialog.show();

        if (needGetScoreDialog == null) {
            needGetScoreDialog = new NeedGetScoreDialog(_mActivity);
        }
        needGetScoreDialog.show();
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
            nickname.setText(MyApplication.getInstance().getUserInfo().getNickname());
            diamonds.setText(String.valueOf(MyApplication.getInstance().getUserInfo().getDiamond()));
            money.setText(String.valueOf(MyApplication.getInstance().getUserInfo().getMoney()));

            showCountDownTimer(data.getPeriod().getStop_time(), data.getPeriod().getPool());
            bindMarqueeView(data.getWin());
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
}
