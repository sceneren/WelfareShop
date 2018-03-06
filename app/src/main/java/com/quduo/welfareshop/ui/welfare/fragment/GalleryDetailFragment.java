package com.quduo.welfareshop.ui.welfare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arjinmc.recyclerviewdecoration.RecyclerViewItemDecoration;
import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.PreviewImageActivity;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.mine.adapter.MyFollowImageAdapter;
import com.quduo.welfareshop.ui.welfare.entity.WelfareGalleryInfo;
import com.quduo.welfareshop.ui.welfare.presenter.GalleryDetailPresenter;
import com.quduo.welfareshop.ui.welfare.view.IGalleryDetailView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/3/5 16:57
 * Description:图库详情
 */

public class GalleryDetailFragment extends BaseBackMvpFragment<IGalleryDetailView, GalleryDetailPresenter> implements IGalleryDetailView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;

    private List<WelfareGalleryInfo> galleryList;
    private MyFollowImageAdapter adapter;

    public static GalleryDetailFragment newInstance() {
        Bundle args = new Bundle();
        GalleryDetailFragment fragment = new GalleryDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
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
    public void initToolbar() {
        toolbarTitle.setText("图库详情");
        initToolbarNav(toolbar, true);
    }

    @Override
    public void initView() {
        showContentPage();
        initRecyclerView();
    }

    private void initRecyclerView() {

        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
            }
        });

        galleryList = new ArrayList<>();

        WelfareGalleryInfo info1 = new WelfareGalleryInfo();
        info1.setUrl("http://pic.yesky.com/uploadImages/2014/341/34/73EC7ME20K98.jpg");
        info1.setPicWidth(738);
        info1.setPicHeight(872);
        info1.setTitle("标题1");
        galleryList.add(info1);

        WelfareGalleryInfo info2 = new WelfareGalleryInfo();
        info2.setUrl("http://imgsrc.baidu.com/imgad/pic/item/aa18972bd40735faa97ff38495510fb30f24087b.jpg");
        info2.setPicWidth(1023);
        info2.setPicHeight(682);
        info2.setTitle("标题2");
        galleryList.add(info2);

        WelfareGalleryInfo info3 = new WelfareGalleryInfo();
        info3.setUrl("http://image.tianjimedia.com/uploadImages/2015/187/56/3N637PII75MH.jpg");
        info3.setPicWidth(800);
        info3.setPicHeight(1130);
        info3.setTitle("标题3");
        galleryList.add(info3);

        WelfareGalleryInfo info4 = new WelfareGalleryInfo();
        info4.setUrl("http://image.tianjimedia.com/uploadImages/2015/162/56/QS0HA8S4RIHA.jpg");
        info4.setPicWidth(800);
        info4.setPicHeight(1200);
        info4.setTitle("标题4");
        galleryList.add(info4);

        WelfareGalleryInfo info5 = new WelfareGalleryInfo();
        info5.setUrl("http://imgsrc.baidu.com/imgad/pic/item/77094b36acaf2edd47c3f4d4861001e9390193d9.jpg");
        info5.setPicWidth(1023);
        info5.setPicHeight(682);
        info5.setTitle("标题5");
        galleryList.add(info5);

        WelfareGalleryInfo info6 = new WelfareGalleryInfo();
        info6.setUrl("http://scimg.jb51.net/allimg/170109/106-1F1091F62D57.jpg");
        info6.setPicWidth(482);
        info6.setPicHeight(461);
        info6.setTitle("标题6");
        galleryList.add(info6);

        WelfareGalleryInfo info7 = new WelfareGalleryInfo();
        info7.setUrl("http://pic.yesky.com/uploadImages/2015/205/54/XSPU82GZ851J.jpg");
        info7.setPicWidth(712);
        info7.setPicHeight(954);
        info7.setTitle("标题7");
        galleryList.add(info7);

        WelfareGalleryInfo info8 = new WelfareGalleryInfo();
        info8.setUrl("http://pic6.nipic.com/20100413/3017209_003630346705_2.jpg");
        info8.setPicWidth(768);
        info8.setPicHeight(1027);
        info8.setTitle("标题8");
        galleryList.add(info8);

        WelfareGalleryInfo info9 = new WelfareGalleryInfo();
        info9.setUrl("http://scimg.jb51.net/allimg/161109/106-161109120310V8.jpg");
        info9.setPicWidth(650);
        info9.setPicHeight(488);
        info9.setTitle("标题9");
        galleryList.add(info9);

        WelfareGalleryInfo info10 = new WelfareGalleryInfo();
        info10.setUrl("http://imgsrc.baidu.com/imgad/pic/item/060828381f30e924ae26530b47086e061d95f70e.jpg");
        info10.setPicWidth(1024);
        info10.setPicHeight(680);
        info10.setTitle("标题10");
        galleryList.add(info10);

        WelfareGalleryInfo info11 = new WelfareGalleryInfo();
        info11.setUrl("http://scimg.jb51.net/allimg/150708/14-150FQ15305155.jpg");
        info11.setPicWidth(650);
        info11.setPicHeight(433);
        info11.setTitle("标题11");
        galleryList.add(info11);

        WelfareGalleryInfo info12 = new WelfareGalleryInfo();
        info12.setUrl("http://pic19.nipic.com/20120214/3145425_134109747000_2.jpg");
        info12.setPicWidth(683);
        info12.setPicHeight(1024);
        info1.setTitle("标题1");
        galleryList.add(info12);

        WelfareGalleryInfo info13 = new WelfareGalleryInfo();
        info13.setUrl("http://imgsrc.baidu.com/imgad/pic/item/3bf33a87e950352a0c1c9a355843fbf2b2118b8a.jpg");
        info13.setPicWidth(1023);
        info13.setPicHeight(682);
        info13.setTitle("标题13");
        galleryList.add(info13);

        WelfareGalleryInfo info14 = new WelfareGalleryInfo();
        info14.setUrl("http://img06.tooopen.com/images/20161214/tooopen_sy_190503622531.jpg");
        info14.setPicWidth(720);
        info14.setPicHeight(1079);
        info14.setTitle("标题14");
        galleryList.add(info14);

        WelfareGalleryInfo info15 = new WelfareGalleryInfo();
        info15.setUrl("http://pic.yesky.com/uploadImages/2015/131/58/62KPG7ZYL453.jpg");
        info15.setPicWidth(800);
        info15.setPicHeight(1030);
        info15.setTitle("标题15");
        galleryList.add(info15);

        WelfareGalleryInfo info16 = new WelfareGalleryInfo();
        info16.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1519640437382&di=f26aa168fd77bdb855b3b69cc9a61e78&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2015%2F153%2F33%2FX2PGS3F9U748.jpg");
        info16.setPicWidth(728);
        info16.setPicHeight(1066);
        info16.setTitle("标题16");
        galleryList.add(info16);

        adapter = new MyFollowImageAdapter(getContext(), galleryList);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //防止item位置互换
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewItemDecoration.Builder builder = new RecyclerViewItemDecoration.Builder(getContext());
        builder.color("#00000000");
        builder.dashWidth(SizeUtils.dp2px(5));
        builder.dashGap(SizeUtils.dp2px(5));
        builder.thickness(SizeUtils.dp2px(5));
        builder.gridBottomVisible(true); //控制下面边框
        builder.gridTopVisible(true); //控制上面边框
        builder.gridLeftVisible(true); //控制左边边框
        builder.gridRightVisible(true); //控制右边边框
        recyclerView.addItemDecoration(builder.create());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ArrayList<String> imageUrlList = new ArrayList<>();
                for (WelfareGalleryInfo info : galleryList) {
                    imageUrlList.add(info.getUrl());
                }
                Intent intent = new Intent(getContext(), PreviewImageActivity.class);
                intent.putExtra(PreviewImageActivity.ARG_URLS, imageUrlList);
                intent.putExtra(PreviewImageActivity.ARG_POSITION, position);
                startActivity(intent);
            }
        });
    }

    @Override
    public GalleryDetailPresenter initPresenter() {
        return new GalleryDetailPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
