package com.quduo.welfareshop.ui.welfare.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.welfare.adapter.SmallVideoAdapter;
import com.quduo.welfareshop.ui.welfare.entity.WelfareVideoInfo;
import com.quduo.welfareshop.ui.welfare.presenter.SmallVideoPresenter;
import com.quduo.welfareshop.ui.welfare.view.ISmallVideoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayer;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/2/9 9:51
 * Description:小视频
 */

public class SmallVideoFragment extends BaseMvpFragment<ISmallVideoView, SmallVideoPresenter> implements ISmallVideoView {
    @BindView(R.id.recyclerView)
    LRecyclerView recyclerView;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;

    private List<WelfareVideoInfo> videoInfoList;
    private LRecyclerViewAdapter mAdapter;

    public static SmallVideoFragment newInstance() {
        Bundle args = new Bundle();
        SmallVideoFragment fragment = new SmallVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welfare_small_video, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
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
    public void initView() {
        showContentPage();
        videoInfoList = new ArrayList<>();
        WelfareVideoInfo videoInfo1 = new WelfareVideoInfo();
        videoInfo1.setImagePath("http://jzvd-pic.nathen.cn/jzvd-pic/bd7ffc84-8407-4037-a078-7d922ce0fb0f.jpg");
        videoInfo1.setVideoPath("http://jzvd.nathen.cn/6ea7357bc3fa4658b29b7933ba575008/fbbba953374248eb913cb1408dc61d85-5287d2089db37e62345123a1be272f8b.mp4");
        videoInfo1.setTitle("饺子出来");
        videoInfoList.add(videoInfo1);

        WelfareVideoInfo videoInfo2 = new WelfareVideoInfo();
        videoInfo2.setImagePath("http://jzvd-pic.nathen.cn/jzvd-pic/f2dbd12e-b1cb-4daf-aff1-8c6be2f64d1a.jpg");
        videoInfo2.setVideoPath("http://jzvd.nathen.cn/35b3dc97fbc240219961bd1fccc6400b/8d9b76ab5a584bce84a8afce012b72d3-5287d2089db37e62345123a1be272f8b.mp4");
        videoInfo2.setTitle("饺子溢出");
        videoInfoList.add(videoInfo2);

        WelfareVideoInfo videoInfo3 = new WelfareVideoInfo();
        videoInfo3.setImagePath("http://jzvd-pic.nathen.cn/jzvd-pic/ccd86ca1-66c7-4331-9450-a3b7f765424a.png");
        videoInfo3.setVideoPath("http://jzvd.nathen.cn/df6096e7878541cbbea3f7298683fbed/ef76450342914427beafe9368a4e0397-5287d2089db37e62345123a1be272f8b.mp4");
        videoInfo3.setTitle("饺子我姓王");
        videoInfoList.add(videoInfo3);

        WelfareVideoInfo videoInfo4 = new WelfareVideoInfo();
        videoInfo4.setImagePath("http://jzvd-pic.nathen.cn/jzvd-pic/2adde364-9be1-4864-b4b9-0b0bcc81ef2e.jpg");
        videoInfo4.setVideoPath("http://jzvd.nathen.cn/384d341e000145fb82295bdc54ecef88/103eab5afca34baebc970378dd484942-5287d2089db37e62345123a1be272f8b.mp4");
        videoInfo4.setTitle("饺子趴好了");
        videoInfoList.add(videoInfo4);

        WelfareVideoInfo videoInfo5 = new WelfareVideoInfo();
        videoInfo5.setImagePath("http://jzvd-pic.nathen.cn/jzvd-pic/2a877211-4b68-4e3a-87be-6d2730faef27.png");
        videoInfo5.setVideoPath("http://jzvd.nathen.cn/f55530ba8a59403da0621cbf4faef15e/adae4f2e3ecf4ea780beb057e7bce84c-5287d2089db37e62345123a1be272f8b.mp4");
        videoInfo5.setTitle("饺子很渴");
        videoInfoList.add(videoInfo5);

        WelfareVideoInfo videoInfo6 = new WelfareVideoInfo();
        videoInfo6.setImagePath("http://jzvd-pic.nathen.cn/jzvd-pic/aaeb5da9-ac50-4712-a28d-863fe40f1fc6.png");
        videoInfo6.setVideoPath("http://jzvd.nathen.cn/6340efd1962946ad80eeffd19b3be89c/65b499c0f16e4dd8900497e51ffa0949-5287d2089db37e62345123a1be272f8b.mp4");
        videoInfo6.setTitle("饺子这样不好");
        videoInfoList.add(videoInfo6);

        WelfareVideoInfo videoInfo7 = new WelfareVideoInfo();
        videoInfo7.setImagePath("http://jzvd-pic.nathen.cn/jzvd-pic/e565f9cc-eedc-45f0-99f8-5b0fa3aed567%281%29.jpg");
        videoInfo7.setVideoPath("http://jzvd.nathen.cn/f07fa9fddd1e45a6ae1570c7fe7967c1/c6db82685b894e25b523b1cb28d79f2e-5287d2089db37e62345123a1be272f8b.mp4");
        videoInfo7.setTitle("饺子别笑");
        videoInfoList.add(videoInfo7);

        WelfareVideoInfo videoInfo8 = new WelfareVideoInfo();
        videoInfo8.setImagePath("http://jzvd-pic.nathen.cn/jzvd-pic/3430ec64-e6a7-4d8e-b044-9d408e075b7c.jpg");
        videoInfo8.setVideoPath("http://jzvd.nathen.cn/d2e969f2ec734520b46ab0965d2b68bd/f124edfef6c24be8b1a7b7f996ccc5e0-5287d2089db37e62345123a1be272f8b.mp4");
        videoInfo8.setTitle("饺子坐火车");
        videoInfoList.add(videoInfo8);

        WelfareVideoInfo videoInfo9 = new WelfareVideoInfo();
        videoInfo9.setImagePath("http://jzvd-pic.nathen.cn/jzvd-pic/2204a578-609b-440e-8af7-a0ee17ff3aee.jpg");
        videoInfo9.setVideoPath("http://jzvd.nathen.cn/4f965ad507ef4194a60a943a34cfe147/32af151ea132471f92c9ced2cff785ea-5287d2089db37e62345123a1be272f8b.mp4");
        videoInfo9.setTitle("饺子打游戏");
        videoInfoList.add(videoInfo9);

        WelfareVideoInfo videoInfo10 = new WelfareVideoInfo();
        videoInfo10.setImagePath("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png");
        videoInfo10.setVideoPath("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4");
        videoInfo10.setTitle("饺子快长大");
        videoInfoList.add(videoInfo10);

        SmallVideoAdapter adapter = new SmallVideoAdapter(getContext(), videoInfoList);
        mAdapter = new LRecyclerViewAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                try {
                    JZVideoPlayer jzvd = view.findViewById(R.id.video_player);
                    JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    if (jzvd != null && JZUtils.dataSourceObjectsContainsUri(jzvd.dataSourceObjects, JZMediaManager.getCurrentDataSource())) {
                        JZVideoPlayer.releaseAllVideos();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public SmallVideoPresenter initPresenter() {
        return new SmallVideoPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }




}
