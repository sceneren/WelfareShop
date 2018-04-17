package com.quduo.welfareshop.ui.shop.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.shop.adapter.SingleGoodsDetailVideoAdapter;
import com.quduo.welfareshop.ui.shop.dialog.ChooseGoodsTypeDialog;
import com.quduo.welfareshop.ui.shop.entity.GoodsCommentInfo;
import com.quduo.welfareshop.ui.shop.entity.GoodsDetailInfo;
import com.quduo.welfareshop.ui.shop.entity.SingleGoodsDetailImageInfo;
import com.quduo.welfareshop.ui.shop.entity.SingleGoodsDetailResultInfo;
import com.quduo.welfareshop.ui.shop.entity.SingleGoodsDetailVideoInfo;
import com.quduo.welfareshop.ui.shop.fragment.ServiceCenterActivity;
import com.quduo.welfareshop.ui.shop.presenter.SingleGoodsDetailPresenter;
import com.quduo.welfareshop.ui.shop.view.ISingleGoodsDetailView;
import com.quduo.welfareshop.util.WeakDataHolder;
import com.quduo.welfareshop.widgets.CustomGridView;
import com.quduo.welfareshop.widgets.MyVideoPlayer;
import com.quduo.welfareshop.widgets.RatioImageView;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
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
import wiki.scene.loadmore.utils.PtrLocalDisplay;

public class SingleGoodsDetailActivity extends BaseMvpActivity<ISingleGoodsDetailView, SingleGoodsDetailPresenter> implements ISingleGoodsDetailView {
    public static final String ARG_ID = "id";
    @BindView(R.id.top_image_layout)
    LinearLayout topImageLayout;
    @BindView(R.id.gridView)
    CustomGridView gridView;
    @BindView(R.id.bottom_image_layout)
    LinearLayout bottomImageLayout;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tab_goods_price)
    TextView tabGoodsPrice;
    @BindView(R.id.btn_service_center)
    TextView btnServiceCenter;
    @BindView(R.id.btn_follow)
    TextView btnFollow;
    @BindView(R.id.buy_now)
    TextView buyNow;
    @BindView(R.id.bottomBar)
    LinearLayout bottomBar;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    @BindView(R.id.view_left)
    ImageView viewLeft;
    @BindView(R.id.video1_thumb)
    RatioImageView video1Thumb;
    @BindView(R.id.view_right)
    ImageView viewRight;
    @BindView(R.id.danMuView)
    DanmakuView mDanmakuView;
    @BindDrawable(R.drawable.ic_goods_follow_d)
    public Drawable iconNoFollow;
    @BindDrawable(R.drawable.ic_goods_follow_s)
    public Drawable iconHasFollow;
    @BindView(R.id.send_score)
    TextView sendScore;

    private GoodsDetailInfo detailInfo;

    private List<SingleGoodsDetailVideoInfo> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_goods_detail);
        ButterKnife.bind(this);
        initDanmu();
        presenter.getData();
    }


    @Override
    public SingleGoodsDetailPresenter initPresenter() {
        return new SingleGoodsDetailPresenter(this);
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
            StyledDialog.buildLoading().setActivity(SingleGoodsDetailActivity.this).show();
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
    public void followGoodsSuccess(int followId) {
        try {
            detailInfo.setFavor_id(followId);
            btnFollow.setCompoundDrawablesWithIntrinsicBounds(null, detailInfo.getFavor_id() == 0 ? iconNoFollow : iconHasFollow, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void cancelFollowSuccess() {
        try {
            detailInfo.setFavor_id(0);
            btnFollow.setCompoundDrawablesWithIntrinsicBounds(null, detailInfo.getFavor_id() == 0 ? iconNoFollow : iconHasFollow, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getGoodsId() {
        try {
            return detailInfo.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return 101;
        }

    }

    @Override
    public void bindData(final SingleGoodsDetailResultInfo data) {
        try {
            detailInfo = data.getData();
            bindDanmuData(data.getComments());
            tabGoodsPrice.setText(MessageFormat.format("￥{0}", detailInfo.getPrice()));
            Number num = Float.parseFloat(detailInfo.getPrice()) * 100;
            int giveNum = num.intValue() / 100;
            sendScore.setText(MessageFormat.format("赠{0}积分", giveNum));
            btnFollow.setCompoundDrawablesWithIntrinsicBounds(null, detailInfo.getFavor_id() == 0 ? iconNoFollow : iconHasFollow, null, null);

            for (SingleGoodsDetailImageInfo topImage : data.getImg_top()) {
                ImageView imageView = new ImageView(SingleGoodsDetailActivity.this);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                float scale = (float) PtrLocalDisplay.SCREEN_WIDTH_PIXELS / (float) topImage.getWidth();
                params.height = (int) (scale * (float) topImage.getHeight());
                params.width = PtrLocalDisplay.SCREEN_WIDTH_PIXELS;
                imageView.setLayoutParams(params);
                topImageLayout.addView(imageView);
                GlideApp.with(SingleGoodsDetailActivity.this)
                        .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + topImage.getImg())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);
            }
            for (SingleGoodsDetailImageInfo bottomImage : data.getImg_bottom()) {
                ImageView imageView = new ImageView(SingleGoodsDetailActivity.this);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                float scale = (float) PtrLocalDisplay.SCREEN_WIDTH_PIXELS / (float) bottomImage.getWidth();
                params.height = (int) (scale * (float) bottomImage.getHeight());
                params.width = PtrLocalDisplay.SCREEN_WIDTH_PIXELS;
                imageView.setLayoutParams(params);
                bottomImageLayout.addView(imageView);
                GlideApp.with(SingleGoodsDetailActivity.this)
                        .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + bottomImage.getImg())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);
            }

            GlideApp.with(SingleGoodsDetailActivity.this)
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.getVideos().get(0).getThumb())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(video1Thumb);
            video1Thumb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    MyVideoPlayer.startFullscreen(SingleGoodsDetailActivity.this, MyVideoPlayer.class, data.getVideos().get(0).getVideo(), data.getVideos().get(0).getTitle());
                }
            });

            list = new ArrayList<>();
            for (int i = 0; i < data.getVideos().size(); i++) {
                if (i > 0) {
                    list.add(data.getVideos().get(i));
                }
            }
            SingleGoodsDetailVideoAdapter adapter = new SingleGoodsDetailVideoAdapter(SingleGoodsDetailActivity.this, list);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    MyVideoPlayer.startFullscreen(SingleGoodsDetailActivity.this, MyVideoPlayer.class, list.get(position).getVideo(), list.get(position).getTitle());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            presenter.getData();
        }
    };

    @Override
    public void onBackPressedSupport() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressedSupport();
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @OnClick(R.id.btn_service_center)
    public void onClickServiceCenter() {
        startActivity(new Intent(SingleGoodsDetailActivity.this, ServiceCenterActivity.class));
        overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @OnClick(R.id.btn_follow)
    void onClickBtnFollow() {
        if (detailInfo.getFavor_id() == 0) {
            presenter.followGoods();
        } else {
            presenter.cancelFollowGoods(detailInfo.getFavor_id());
        }
    }

    @OnClick(R.id.buy_now)
    public void onClickBuyNow() {
        try {
            WeakDataHolder.getInstance().saveData(String.valueOf(detailInfo.getId()), detailInfo);
            Intent intent = new Intent(SingleGoodsDetailActivity.this, ChooseGoodsTypeDialog.class);
            intent.putExtra(ChooseGoodsTypeDialog.ARG_GOODS_ID, detailInfo.getId());
            startActivity(intent);
            overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                }
            });
            //缓存，提升绘制效率
            mDanmakuView.enableDanmakuDrawingCache(true);
            initDanmakuContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DanmakuContext danmakuContext;

    private void initDanmakuContext() {
        try {
            //DanmakuContext主要用于弹幕样式的设置
            @SuppressLint("UseSparseArrays") HashMap<Integer, Integer> maxLinesPair = new HashMap<>();
            maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 3);
            // 设置是否禁止重叠
            @SuppressLint("UseSparseArrays") HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<>();
            overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_LR, true);
            overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_BOTTOM, true);
            overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);
            danmakuContext = DanmakuContext.create();
            danmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3);//描边
            danmakuContext.setDuplicateMergingEnabled(false);//重复合并
            danmakuContext.setScrollSpeedFactor(1f);//弹幕滚动速度
            danmakuContext.setMaximumLines(maxLinesPair);
            danmakuContext.setCacheStuffer(new BackgroundCacheStuffer(), null);
            danmakuContext.preventOverlapping(overlappingEnablePair);
            //让弹幕进入准备状态，传入弹幕解析器和样式设置
            mDanmakuView.prepare(parser, danmakuContext);
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

    private boolean isWork = true;

    private void bindDanmuData(final List<GoodsCommentInfo> data) {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int size = data.size();
                    for (int i = 0; i < size; i++) {
                        if (!isWork) {
                            break;
                        }
                        try {
                            String content = data.get(i).getContent();
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

    /**
     * 向弹幕View中添加一条弹幕
     *
     * @param content 弹幕的具体内容
     */
    private void addDanmaku(String content) {
        try {
            if (danmakuContext == null) {
                initDanmakuContext();
            }
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

    @Override
    protected void onDestroy() {
        OkGo.getInstance().cancelTag(ApiUtil.VR_DETAIL_TAG);
        isWork = false;
        super.onDestroy();
    }

}
