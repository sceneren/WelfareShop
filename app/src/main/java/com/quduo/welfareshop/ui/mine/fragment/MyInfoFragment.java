package com.quduo.welfareshop.ui.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.PreviewImageActivity;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.event.UpdateAvatarEvent;
import com.quduo.welfareshop.event.UpdateMyInfoSuccessEvent;
import com.quduo.welfareshop.event.UploadImageEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.mine.activity.EditMyInfoActivity;
import com.quduo.welfareshop.ui.mine.adapter.MyInfoImageAdapter;
import com.quduo.welfareshop.ui.mine.entity.MyUserDetailInfo;
import com.quduo.welfareshop.ui.mine.presenter.MyInfoPresenter;
import com.quduo.welfareshop.ui.mine.view.IMyInfoView;
import com.quduo.welfareshop.widgets.CustomGridView;
import com.quduo.welfareshop.widgets.RatioImageView;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
 * Time:2018/2/2 15:48
 * Description:我的资料
 */

public class MyInfoFragment extends BaseBackMvpFragment<IMyInfoView, MyInfoPresenter> implements IMyInfoView {

    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.baseimage)
    RatioImageView baseimage;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.avatar)
    SelectableRoundedImageView avatar;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.follow_number)
    TextView followNumber;
    @BindView(R.id.others_show_id)
    TextView othersShowId;
    @BindView(R.id.no_photo)
    TextView noPhoto;
    @BindView(R.id.photoGridView)
    CustomGridView photoGridView;
    @BindView(R.id.arrow)
    ImageView arrow;
    @BindView(R.id.image_layout)
    RelativeLayout imageLayout;
    @BindView(R.id.des)
    TextView des;
    @BindView(R.id.height)
    TextView height;
    @BindView(R.id.weight)
    TextView weight;
    @BindView(R.id.marital)
    TextView marital;
    @BindView(R.id.blood_type)
    TextView bloodType;
    @BindView(R.id.job)
    TextView job;
    @BindView(R.id.star)
    TextView star;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;

    private MyUserDetailInfo detailUserInfo;

    private List<MyUserDetailInfo.PhotosBean> list = new ArrayList<>();
    private ArrayList<String> imageList;
    private MyInfoImageAdapter adapter;


    public static MyInfoFragment newInstance() {
        Bundle args = new Bundle();
        MyInfoFragment fragment = new MyInfoFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void initToolbar() {
        initToolbarNav(toolbar, true);
    }

    @Override
    public void initView() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getData(false);
            }
        });

        adapter = new MyInfoImageAdapter(getContext(), list);
        photoGridView.setAdapter(adapter);
        photoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toPriviewActivity(position);
            }
        });

        presenter.getData(true);
    }

    private void toPriviewActivity(int position) {
        Intent intent = new Intent(_mActivity, PreviewImageActivity.class);
        if (imageList == null) {
            imageList = new ArrayList<>();
        }
        imageList.clear();
        for (MyUserDetailInfo.PhotosBean photosBean : list) {
            imageList.add(MyApplication.getInstance().getConfigInfo().getFile_domain() + photosBean.getUrl());
        }
        intent.putExtra(PreviewImageActivity.ARG_URLS, imageList);
        intent.putExtra(PreviewImageActivity.ARG_POSITION, position);
        startActivity(intent);
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
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
    public MyInfoPresenter initPresenter() {
        return new MyInfoPresenter(this);
    }

    @OnClick(R.id.edit_info)
    public void onClickEditInfo() {
        Intent intent = new Intent(_mActivity, EditMyInfoActivity.class);
        intent.putExtra(EditMyInfoActivity.ARG_USER_INFO, detailUserInfo);
        startActivity(intent);
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @OnClick(R.id.arrow)
    public void onClickArrow() {
        toPriviewActivity(0);
    }

    @OnClick(R.id.back)
    public void onClickBack() {
        _mActivity.onBackPressed();
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.MY_DETAIL_INFO_TAG);
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
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
    public void refreshFinish() {
        try {
            refreshLayout.finishRefresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void bindData(MyUserDetailInfo data) {
        try {
            detailUserInfo = data;
            GlideApp.with(this)
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.drawable.ic_default_avatar)
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.getAvatar())
                    .into(image);
            GlideApp.with(this)
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.drawable.ic_default_avatar)
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + data.getAvatar())
                    .into(avatar);
            nickname.setText(data.getNickname());
            followNumber.setText(MessageFormat.format("粉丝：{0}", data.getSubscribe()));
            othersShowId.setText(MessageFormat.format("ID:{0}", data.getId()));
            if (data.getPhotos().size() > 0) {
                imageLayout.setVisibility(View.VISIBLE);
                noPhoto.setVisibility(View.GONE);
            } else {
                imageLayout.setVisibility(View.GONE);
                noPhoto.setVisibility(View.VISIBLE);
            }
            list.clear();
            list.addAll(data.getPhotos());
            adapter.notifyDataSetChanged();
            des.setText(data.getSignature());
            if (!StringUtils.isEmpty(data.getHeight())) {
                if (data.getHeight().endsWith("CM")) {
                    height.setText(data.getHeight());
                } else {
                    height.setText(MessageFormat.format("{0}CM", data.getHeight()));
                }
            }
            if (!StringUtils.isEmpty(data.getWeight())) {
                if (data.getHeight().endsWith("KG")) {
                    weight.setText(data.getWeight());
                } else {
                    weight.setText(MessageFormat.format("{0}KG", data.getWeight()));
                }
            }
            marital.setText(data.getMarital());
            bloodType.setText(data.getBlood_type());
            job.setText(data.getJob());
            star.setText(data.getStar());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void updateAvatar(UpdateAvatarEvent event) {
        try {
            detailUserInfo.setAvatar(event.getAvatarPath());
            GlideApp.with(this)
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.drawable.ic_default_avatar)
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + event.getAvatarPath())
                    .into(image);
            GlideApp.with(this)
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.drawable.ic_default_avatar)
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + event.getAvatarPath())
                    .into(avatar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void uploadPhoto(UploadImageEvent event) {
        try {
            detailUserInfo.setPhotos(event.getPhotosBeanList());
            list.clear();
            list.addAll(event.getPhotosBeanList());
            adapter.notifyDataSetChanged();
            if (detailUserInfo.getPhotos().size() > 0) {
                imageLayout.setVisibility(View.VISIBLE);
                noPhoto.setVisibility(View.GONE);
            } else {
                imageLayout.setVisibility(View.GONE);
                noPhoto.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void updateMyInfoSuccess(UpdateMyInfoSuccessEvent event) {
        try {
            refreshLayout.autoRefresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
