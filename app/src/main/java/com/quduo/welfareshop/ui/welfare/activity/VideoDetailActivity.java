package com.quduo.welfareshop.ui.welfare.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.welfare.adapter.BeautyVideoItemAdapter;
import com.quduo.welfareshop.ui.welfare.adapter.VideoDetailGoodsAdapter;
import com.quduo.welfareshop.ui.welfare.entity.VideoTypeInfo;
import com.quduo.welfareshop.ui.welfare.presenter.VideoDetailPresenter;
import com.quduo.welfareshop.ui.welfare.view.IVideoDetailView;
import com.quduo.welfareshop.util.FileUtils;
import com.quduo.welfareshop.widgets.CustomListView;
import com.quduo.welfareshop.widgets.CustomeGridView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;
    @BindView(R.id.video_player)
    JZVideoPlayerStandard videoPlayer;
    @BindView(R.id.zan_number)
    TextView zanNumber;
    @BindView(R.id.play_number)
    TextView playNumber;
    @BindView(R.id.follow_number)
    TextView followNumber;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    @BindView(R.id.videoListView)
    CustomListView videoListView;
    @BindView(R.id.goodsGridView)
    CustomeGridView goodsGridView;

    private List<String> goodsList;
    private VideoDetailGoodsAdapter goodsAdapter;

    private List<VideoTypeInfo> videoList;
    private BeautyVideoItemAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare_video_detail);
        unbinder = ButterKnife.bind(this);
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
        showContentPage();
        String imageUrl = "http://jzvd-pic.nathen.cn/jzvd-pic/00b026e7-b830-4994-bc87-38f4033806a6.jpg";
        String videoUrl = "http://jzvd.nathen.cn/c494b340ff704015bb6682ffde3cd302/64929c369124497593205a4190d7d128-5287d2089db37e62345123a1be272f8b.mp4";
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        videoPlayer.setUp(videoUrl, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子不信");
        GlideApp.with(this)
                .asBitmap()
                .load(imageUrl)
                .centerCrop()
                .into(videoPlayer.thumbImageView);

        initVideoListView();
        initGoodsGridView();
    }

    private void initGoodsGridView() {
        goodsList = new ArrayList<>();
        goodsList.add("xx");
        goodsList.add("xx");
        goodsList.add("xx");
        goodsList.add("xx");
        goodsAdapter = new VideoDetailGoodsAdapter(VideoDetailActivity.this, goodsList);
        goodsGridView.setAdapter(goodsAdapter);
    }

    private void initVideoListView() {
        String jsonStr = FileUtils.getAssetsJson(VideoDetailActivity.this, "recommend_video.json");
        Type listType = new TypeToken<LinkedList<VideoTypeInfo>>() {
        }.getType();
        Gson gson = new Gson();
        videoList = gson.fromJson(jsonStr, listType);
        videoAdapter = new BeautyVideoItemAdapter(VideoDetailActivity.this, videoList);
        videoListView.setAdapter(videoAdapter);
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
        //Change these two variables back
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
