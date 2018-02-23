package com.quduo.welfareshop.ui.welfare.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.welfare.adapter.BeautyVideoAdapter;
import com.quduo.welfareshop.ui.welfare.entity.VideoModelInfo;
import com.quduo.welfareshop.ui.welfare.entity.WelfareGalleryInfo;
import com.quduo.welfareshop.ui.welfare.presenter.BeautyVideoPresenter;
import com.quduo.welfareshop.ui.welfare.view.IBeautyVideoView;
import com.quduo.welfareshop.util.BannerImageLoader;
import com.quduo.welfareshop.util.FileUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/2/23 15:03
 * Description:美女视频
 */

public class BeautyVideoFragment extends BaseMvpFragment<IBeautyVideoView, BeautyVideoPresenter> implements IBeautyVideoView {
    @BindView(R.id.recyclerView)
    LRecyclerView recyclerView;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;

    private View headerView;
    private Banner banner;

    private List<WelfareGalleryInfo> bannerList;
    private LRecyclerViewAdapter mAdapter;

    public static BeautyVideoFragment newInstance() {
        Bundle args = new Bundle();
        BeautyVideoFragment fragment = new BeautyVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welfare_beauty_video, container, false);
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
        initRecyclerView();
        initHeaderView();
        bindHeaderView();
        initFooterView();
    }

    private List<VideoModelInfo> list;

    private void initRecyclerView() {
        String jsonStr = FileUtils.getAssetsJson(getContext(), "beautyvideo.json");
        Type listType = new TypeToken<LinkedList<VideoModelInfo>>() {
        }.getType();
        Gson gson = new Gson();
        list = gson.fromJson(jsonStr, listType);
        BeautyVideoAdapter adapter = new BeautyVideoAdapter(getContext(), list);
        mAdapter = new LRecyclerViewAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    private void initHeaderView() {
        headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_welfare_beauty_video_header, null);
        banner = headerView.findViewById(R.id.banner);
        banner.setImageLoader(new BannerImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mAdapter.addHeaderView(headerView);
        //banner.start();
    }

    private void bindHeaderView() {
        if (bannerList == null) {
            bannerList = new ArrayList<>();
        } else {
            bannerList.clear();
        }
        WelfareGalleryInfo info2 = new WelfareGalleryInfo();
        info2.setUrl("http://f.hiphotos.baidu.com/image/h%3D300/sign=4a0a3dd10155b31983f9847573ab8286/503d269759ee3d6db032f61b48166d224e4ade6e.jpg");
        info2.setPicWidth(1023);
        info2.setPicHeight(682);
        info2.setTitle("标题2");
        bannerList.add(info2);

        WelfareGalleryInfo info9 = new WelfareGalleryInfo();
        info9.setUrl("http://a.hiphotos.baidu.com/image/h%3D300/sign=71f6f27f2c7f9e2f6f351b082f31e962/500fd9f9d72a6059f550a1832334349b023bbae3.jpg");
        info9.setPicWidth(650);
        info9.setPicHeight(488);
        info9.setTitle("标题9");
        bannerList.add(info9);

        WelfareGalleryInfo info5 = new WelfareGalleryInfo();
        info5.setUrl("http://b.hiphotos.baidu.com/image/h%3D300/sign=03d791b0e0c4b7452b94b116fffd1e78/58ee3d6d55fbb2fb4a944f8b444a20a44723dcef.jpg");
        info5.setPicWidth(1023);
        info5.setPicHeight(682);
        info5.setTitle("标题5");
        bannerList.add(info5);
        List<String> images = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < bannerList.size(); i++) {
            images.add(bannerList.get(i).getUrl());
            titles.add(bannerList.get(i).getTitle());
        }
        banner.setImages(images);
        banner.setBannerTitles(titles);
        banner.start();
    }

    private void initFooterView() {
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_more_content, null);
        mAdapter.addFooterView(footerView);
    }

    @Override
    public BeautyVideoPresenter initPresenter() {
        return new BeautyVideoPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
