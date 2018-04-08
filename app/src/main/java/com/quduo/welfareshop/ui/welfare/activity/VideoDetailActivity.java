package com.quduo.welfareshop.ui.welfare.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.base.UnlockLisenter;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.UpdateScoreAndDiamondEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.shop.activity.GoodsDetailActivity;
import com.quduo.welfareshop.ui.shop.entity.GoodsInfo;
import com.quduo.welfareshop.ui.welfare.adapter.BeautyVideoHengAdapter;
import com.quduo.welfareshop.ui.welfare.adapter.BeautyVideoShu2Adapter;
import com.quduo.welfareshop.ui.welfare.adapter.VideoDetailCommentAdapter;
import com.quduo.welfareshop.ui.welfare.adapter.VideoDetailGoodsAdapter;
import com.quduo.welfareshop.ui.welfare.entity.VideoCommentInfo;
import com.quduo.welfareshop.ui.welfare.entity.VideoDetailInfo;
import com.quduo.welfareshop.ui.welfare.entity.VideoInfo;
import com.quduo.welfareshop.ui.welfare.presenter.VideoDetailPresenter;
import com.quduo.welfareshop.ui.welfare.view.IVideoDetailView;
import com.quduo.welfareshop.util.DialogUtils;
import com.quduo.welfareshop.widgets.CustomGridView;
import com.quduo.welfareshop.widgets.CustomListView;
import com.quduo.welfareshop.widgets.MyVideoPlayer;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/2/27 12:14
 * Description:视频详情
 */
public class VideoDetailActivity extends BaseMvpActivity<IVideoDetailView, VideoDetailPresenter> implements IVideoDetailView {

    public static final String ARG_VIDEO_ID = "videoId";
    public static final String ARG_CATE_ID = "cateId";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;
    @BindView(R.id.video_player)
    MyVideoPlayer videoPlayer;
    @BindView(R.id.zan_number)
    TextView zanNumber;
    @BindView(R.id.play_number)
    TextView playNumber;
    @BindView(R.id.follow_number)
    TextView followNumber;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    @BindView(R.id.videoShuGridView)
    CustomGridView videoShuGridView;
    @BindView(R.id.videoHengGridView)
    CustomGridView videoHengGridView;
    @BindView(R.id.goodsGridView)
    CustomGridView goodsGridView;
    @BindView(R.id.btn_zan)
    ImageView btnZan;
    @BindView(R.id.btn_follow)
    ImageView btnFollow;
    @BindView(R.id.commentListView)
    CustomListView commentListView;
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.comment_content)
    EditText commentContent;
    @BindView(R.id.comment)
    TextView comment;
    @BindView(R.id.arrow)
    ImageView arrow;
    @BindView(R.id.des)
    TextView des;
    @BindView(R.id.video_name)
    TextView videoName;
    @BindView(R.id.layout_see_des)
    LinearLayout layoutSeeDes;

    private List<GoodsInfo> goodsList = new ArrayList<>();
    private VideoDetailGoodsAdapter goodsAdapter;

    private List<VideoInfo> videoShuList = new ArrayList<>();
    private BeautyVideoHengAdapter videoHengAdapter;

    private List<VideoInfo> videoHengList = new ArrayList<>();
    private BeautyVideoShu2Adapter videoShuAdapter;

    private List<VideoCommentInfo> commentList = new ArrayList<>();
    private VideoDetailCommentAdapter commentAdapter;

    private VideoDetailInfo info;

    private int videoId = 0;
    private int cateId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare_video_detail);
        unbinder = ButterKnife.bind(this);
        videoId = getIntent().getIntExtra(ARG_VIDEO_ID, 0);
        cateId = getIntent().getIntExtra(ARG_CATE_ID, 0);
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_VIDEO_DETAIL, videoId);
        initToolbar();
        initView();
    }

    @Override
    public VideoDetailPresenter initPresenter() {
        return new VideoDetailPresenter(this);
    }

    private void initToolbar() {
        toolbarTitle.setText("视频详情");
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });
    }

    private void initView() {
        initRefreshLayout();
        initVideoShuGridView();
        initVideoHengGridView();
        initGoodsGridView();
        initCommentListView();

        presenter.getData(true);
    }

    private void bindVideoPlayer() {
        JZVideoPlayer.releaseAllVideos();
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        videoPlayer.setUp(info.getUrl(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, info.getName());
        GlideApp.with(this)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + info.getThumb())
                .placeholder(R.drawable.ic_default_video)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(videoPlayer.thumbImageView);
        videoPlayer.setCurrentInfo(info.isPayed(), info.getId(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.getInstance().showNeedUnlockDialog(VideoDetailActivity.this, info.getPrice(), MyApplication.getInstance().getUserInfo().getScore(), AppConfig.POSITION_VIDEO_DETAIL, new UnlockLisenter() {
                    @Override
                    public void unlock() {
                        presenter.unlockVideo(info.getId());
                    }
                });
            }
        });
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

    private void initGoodsGridView() {
        goodsAdapter = new VideoDetailGoodsAdapter(VideoDetailActivity.this, goodsList);
        goodsGridView.setAdapter(goodsAdapter);
        goodsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toGoodsDetailActivity(goodsList.get(position).getId());
            }
        });
    }

    private void initVideoShuGridView() {
        videoShuAdapter = new BeautyVideoShu2Adapter(VideoDetailActivity.this, videoShuList);
        videoShuGridView.setAdapter(videoShuAdapter);
        videoShuGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toVideoDetailActivity(videoShuList.get(position).getId(), videoShuList.get(position).getCate_id());
            }
        });
    }

    private void initVideoHengGridView() {
        videoHengAdapter = new BeautyVideoHengAdapter(VideoDetailActivity.this, videoHengList);
        videoHengGridView.setAdapter(videoHengAdapter);
        videoHengGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toVideoDetailActivity(videoHengList.get(position).getId(), videoHengList.get(position).getCate_id());
            }
        });
    }

    private void initCommentListView() {
        commentAdapter = new VideoDetailCommentAdapter(VideoDetailActivity.this, commentList);
        commentListView.setAdapter(commentAdapter);
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
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    @Override
    protected void onDestroy() {
        OkGo.getInstance().cancelTag(ApiUtil.VIDEO_DETAIL_TAG);
        super.onDestroy();
        unbinder.unbind();
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
    public void showLoadingDialog() {
        try {
            StyledDialog.buildLoading().setActivity(VideoDetailActivity.this).show();
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
            if (message.equals("积分不足")) {
                DialogUtils.getInstance().showNeedRechargeScoreDialog(VideoDetailActivity.this, info.getPrice(), AppConfig.POSITION_VIDEO_DETAIL, MyApplication.getInstance().getUserInfo().getScore());
                return;
            }
            ToastUtils.showShort(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bindData(VideoDetailInfo data) {
        try {
            info = data;
            bindVideoPlayer();
            playNumber.setText(MessageFormat.format("播放：{0}", data.getPlay_times()));
            followNumber.setText(MessageFormat.format("收藏：{0}", data.getFavor_times()));
            zanNumber.setText(MessageFormat.format("点赞：{0}", data.getGood()));
            btnZan.setImageResource(data.isIs_good() ? R.drawable.ic_video_zan_s : R.drawable.ic_video_zan_d);
            btnFollow.setImageResource(data.getFavor_id() != 0 ? R.drawable.ic_video_follow_s : R.drawable.ic_video_follow_d);
            bindVideoHengListView(data.getRelated().getHeng());
            bindVideoShuListView(data.getRelated().getShu());
            bindGoodsGridView(data.getGoods());
            bindCommentListView(data.getComment());
            GlideApp.with(this)
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + MyApplication.getInstance().getUserInfo().getAvatar())
                    .placeholder(R.drawable.ic_default_video)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(avatar);
            des.setText(data.getDescription());
            videoName.setText(data.getName());
            layoutSeeDes.setVisibility(cateId == 2 ? View.GONE : View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getVideoId() {
        return videoId;
    }

    @Override
    public int getCateId() {
        return cateId;
    }

    @Override
    public void zanSuccess() {
        try {
            info.setIs_good(true);
            info.setGood(info.getGood() + 1);
            zanNumber.setText(MessageFormat.format("点赞:{0}", info.getGood()));
            btnZan.setImageResource(R.drawable.ic_video_zan_s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void followSuccess(int followId) {
        try {
            info.setFavor_id(followId);
            info.setFavor_times(info.getFavor_times() + 1);
            followNumber.setText(MessageFormat.format("收藏:{0}", info.getFavor_times()));
            btnFollow.setImageResource(R.drawable.ic_video_follow_s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelFollowSuccess() {
        try {
            info.setFavor_id(0);
            info.setFavor_times(info.getFavor_times() > 0 ? info.getFavor_times() - 1 : 0);
            followNumber.setText(MessageFormat.format("收藏:{0}", info.getFavor_times()));
            btnFollow.setImageResource(R.drawable.ic_video_follow_d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unlockSuccess(int currentScore) {
        try {
            MyApplication.getInstance().getUserInfo().setScore(currentScore);
            EventBus.getDefault().post(new UpdateScoreAndDiamondEvent());
            info.setPayed(true);
            bindVideoPlayer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_zan)
    public void onClickBtnZan() {
        if (!info.isIs_good()) {
            presenter.zan();
        }
    }

    @OnClick(R.id.btn_follow)
    public void onClickBtnFollow() {
        if (info.getFavor_id() != 0) {
            presenter.cancelFollow(info.getFavor_id());
        } else {
            presenter.followVideo();
        }
    }

    private void bindVideoHengListView(List<VideoInfo> data) {
        try {
            videoHengList.clear();
            videoHengList.addAll(data);
            videoHengAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindVideoShuListView(List<VideoInfo> data) {
        try {
            videoShuList.clear();
            videoShuList.addAll(data);
            videoShuAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindGoodsGridView(List<GoodsInfo> data) {
        try {
            goodsList.clear();
            goodsList.addAll(data);
            goodsAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindCommentListView(List<VideoCommentInfo> data) {
        try {
            commentList.clear();
            commentList.addAll(data);
            commentAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.comment)
    public void onClickComment() {
        try {
            if (StringUtils.isTrimEmpty(commentContent.getText().toString())) {
                showMessage("请输入评论内容");
                return;
            }

            showLoadingDialog();
            comment.postDelayed(new Runnable() {
                @Override
                public void run() {
                    commentContent.setText("");
                    showMessage("评论已提交，审核成功后显示");
                    hideLoadingDialog();
                }
            }, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toVideoDetailActivity(int videoId, int cateId) {
        try {
            Intent intent = new Intent(VideoDetailActivity.this, VideoDetailActivity.class);
            intent.putExtra(VideoDetailActivity.ARG_VIDEO_ID, videoId);
            intent.putExtra(VideoDetailActivity.ARG_CATE_ID, cateId);
            startActivity(intent);
            overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toGoodsDetailActivity(int goodsId) {
        try {
            Intent intent = new Intent(VideoDetailActivity.this, GoodsDetailActivity.class);
            intent.putExtra(GoodsDetailActivity.ARG_ID, goodsId);
            startActivity(intent);
            overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.layout_see_des)
    public void onClicklayoutSeeDes() {
        try {
            if (des.getVisibility() == View.VISIBLE) {
                des.setVisibility(View.GONE);
                arrow.setImageResource(R.drawable.ic_arrow_top);
            } else {
                des.setVisibility(View.VISIBLE);
                arrow.setImageResource(R.drawable.ic_arrow_bottom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
