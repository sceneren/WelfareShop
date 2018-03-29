package com.quduo.welfareshop.ui.red.fragment;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.event.TabSelectedEvent;
import com.quduo.welfareshop.event.UpdateMyInfoSuccessEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMainMvpFragment;
import com.quduo.welfareshop.ui.mine.fragment.MineFragment;
import com.quduo.welfareshop.ui.red.dialog.GetRedDialog;
import com.quduo.welfareshop.ui.red.dialog.NeedGetDiamondDialog;
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
import org.joda.time.DateTime;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;
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
    DanmakuView mDanmakuView;


    private GetRedDialog getRedDialog;
    private NeedGetDiamondDialog needGetDiamondDialog;

    private List<View> views = new ArrayList<>();
    private RedResultInfo redResultInfo;

    private DanmakuContext danmakuContext;

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
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_RED_INDEX, 0);
        initDanmu();
        initRefreshLayout();
        presenter.getData(true);
    }

    /**
     * 弹幕解析器
     */
    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    private void initDanmu() {
        try {
             @SuppressLint("UseSparseArrays") HashMap<Integer, Integer> maxLinesPair = new HashMap<>();
            maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 3);
            // 设置是否禁止重叠
            @SuppressLint("UseSparseArrays") HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<>();
            overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_LR, true);
            overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_BOTTOM, true);
            overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);

            mDanmakuView.setCallback(new DrawHandler.Callback() {
                @Override
                public void prepared() {
                    mDanmakuView.start();
                    //generateSomeDanmaku();
                }

                @Override
                public void updateTimer(DanmakuTimer timer) {

                }

                @Override
                public void danmakuShown(BaseDanmaku danmaku) {

                }

                @Override
                public void drawingFinished() {
                    presenter.getData(false);
                }
            });
            //缓存，提升绘制效率
            mDanmakuView.enableDanmakuDrawingCache(true);
            //DanmakuContext主要用于弹幕样式的设置
            danmakuContext = DanmakuContext.create();
            danmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3);//描边
            danmakuContext.setDuplicateMergingEnabled(false);//重复合并
            danmakuContext.setScrollSpeedFactor(1.6f);//弹幕滚动速度
            danmakuContext.setMaximumLines(maxLinesPair);
            danmakuContext.setCacheStuffer(new BackgroundCacheStuffer(), null);
            danmakuContext.preventOverlapping(overlappingEnablePair);
            //让弹幕进入准备状态，传入弹幕解析器和样式设置
            mDanmakuView.prepare(parser, danmakuContext);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 向弹幕View中添加一条弹幕
     *
     * @param content 弹幕的具体内容
     */
    private void addDanmaku(String content) {
        try {
            //弹幕实例BaseDanmaku,传入参数是弹幕方向
            BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
            danmaku.text = content;
            danmaku.padding = 5;
            danmaku.textSize = SizeUtils.sp2px(12);
            danmaku.textColor = Color.WHITE;
            danmaku.setTime(mDanmakuView.getCurrentTime());
            mDanmakuView.addDanmaku(danmaku);
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
                            DateTime dateTime = new DateTime(System.currentTimeMillis());
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

    @Override
    public void onPause() {
        super.onPause();
        if (mDanmakuView != null && mDanmakuView.isPrepared()) {
            mDanmakuView.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.resume();
        }
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
        if (mDanmakuView != null) {
            mDanmakuView.release();
            mDanmakuView = null;
        }
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

            bindDanmuData(data.getWin());

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
                needGetDiamondDialog.setOnClickGetDiamondListener(new NeedGetDiamondDialog.OnClickGetDiamondListener() {
                    @Override
                    public void onClickGetDiamond() {
                        hideGetRedDialog();
                        EventBus.getDefault().post(new TabSelectedEvent(1));
                    }
                });
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
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_BUY_RED_DIALOG, 0);
    }


    private void bindDanmuData(final List<RedWinInfo> data) {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < data.size(); i++) {

                        try {
                            String content = "恭喜" + data.get(i).getNickname() + "从红包中拆出" + data.get(i).getBonus() + "现金";
                            addDanmaku(content);
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static class BackgroundCacheStuffer extends SpannedCacheStuffer {
        // 通过扩展SimpleTextCacheStuffer或SpannedCacheStuffer个性化你的弹幕样式
        Paint paint = new Paint();


        @Override
        public void measure(BaseDanmaku danmaku, TextPaint paint, boolean fromWorkerThread) {
            danmaku.padding = 10;  // 在背景绘制模式下增加padding
            super.measure(danmaku, paint, fromWorkerThread);
        }

        @Override
        public void drawBackground(BaseDanmaku danmaku, Canvas canvas, float left, float top) {
            paint.setColor(Color.parseColor("#99000000"));  //弹幕背景颜色
            canvas.drawRoundRect(new RectF(left + 2, top + 2, left + danmaku.paintWidth - 2, top + danmaku.paintHeight - 2),
                    SizeUtils.dp2px(12), SizeUtils.dp2px(12), paint);
        }


        @Override
        public void drawStroke(BaseDanmaku danmaku, String lineText, Canvas canvas, float left, float top, Paint paint) {
            // 禁用描边绘制
        }
    }
}
