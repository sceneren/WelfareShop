package com.quduo.welfareshop.ui.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.PreviewImageActivity;
import com.quduo.welfareshop.event.UploadImageEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.itemDecoration.GridSpacingItemDecoration;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.mine.adapter.AlbumAdapter;
import com.quduo.welfareshop.ui.mine.dialog.DeleteImageDialog;
import com.quduo.welfareshop.ui.mine.entity.MyUserDetailInfo;
import com.quduo.welfareshop.ui.mine.presenter.AlbumPresenter;
import com.quduo.welfareshop.ui.mine.view.IAlbumView;
import com.quduo.welfareshop.util.ImageSelectorUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnMultiCompressListener;

/**
 * Author:scene
 * Time:2018/3/19 9:46
 * Description:相册
 */

public class AlbumActivity extends BaseMvpActivity<IAlbumView, AlbumPresenter> implements IAlbumView {
    public static final String ARG_IMAGES = "images";
    public static final String ARG_IS_MINE = "is_mine";

    private static final int REQUEST_LIST_CODE = 10001;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private boolean isCanUpdate = false;

    private List<MyUserDetailInfo.PhotosBean> list;
    private AlbumAdapter adapter;

    private DeleteImageDialog deleteImageDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        unbinder = ButterKnife.bind(this);
        isCanUpdate = getIntent().getBooleanExtra(ARG_IS_MINE, false);
        initToolbar();
        initView();
    }

    private void initView() {
        try {
            list = (List<MyUserDetailInfo.PhotosBean>) getIntent().getSerializableExtra(ARG_IMAGES);
            initRecyclerView();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initToolbar() {
        if (isCanUpdate) {
            toolbarText.setText("上传");
        }
        toolbarTitle.setText("相册");
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initRecyclerView() {
        if (list != null) {
            adapter = new AlbumAdapter(list);
            recyclerView.setLayoutManager(new GridLayoutManager(AlbumActivity.this, 2));
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, SizeUtils.dp2px(5), true));
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    toPreviewActivity(position);
                }
            });
            if (isCanUpdate) {
                adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                        showDeleteDialog(position);
                        return true;
                    }
                });
            }

        }
    }

    @Override
    public void showLoadingPage() {

    }

    @Override
    public void showContentPage() {

    }

    @Override
    public void showErrorPage() {
    }

    @Override
    public AlbumPresenter initPresenter() {
        return new AlbumPresenter(this);
    }

    @Override
    protected void onDestroy() {
        OkGo.getInstance().cancelTag(ApiUtil.UPLOAD_PHOTO_TAG);
        OkGo.getInstance().cancelTag(ApiUtil.DELETE_PHOTO_TAG);
        super.onDestroy();
        unbinder.unbind();
    }

    private ArrayList<String> images;

    private void toPreviewActivity(int position) {
        Intent intent = new Intent(AlbumActivity.this, PreviewImageActivity.class);
        if (images == null) {
            images = new ArrayList<>();
        }
        images.clear();
        for (MyUserDetailInfo.PhotosBean photosBean : list) {
            images.add(MyApplication.getInstance().getConfigInfo().getFile_domain() + photosBean.getUrl());
        }
        intent.putExtra(PreviewImageActivity.ARG_URLS, images);
        intent.putExtra(PreviewImageActivity.ARG_POSITION, position);
        startActivity(intent);
        overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            // 图片选择结果回调
            if (requestCode == REQUEST_LIST_CODE) {
                List<String> pathList = data.getStringArrayListExtra("result");
                List<File> files = new ArrayList<>();
                for (String filePath : pathList) {
                    files.add(new File(filePath));
                }
                Luban.compress(AlbumActivity.this, files)
                        .launch(new OnMultiCompressListener() {
                            @Override
                            public void onStart() {
                                showLoadingDialog();
                            }

                            @Override
                            public void onSuccess(List<File> fileList) {
                                presenter.uploadPhoto(fileList);
                            }

                            @Override
                            public void onError(Throwable e) {
                                hideLoadingDialog();
                                showMessage("图片上传失败请重试");
                            }
                        });


            }
        }

    }

    @Override
    public void showLoadingDialog() {
        try {
            hideLoadingDialog();
            StyledDialog.buildLoading().setActivity(AlbumActivity.this).show();
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
            ToastUtils.showShort(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadPhotoSuccess(List<MyUserDetailInfo.PhotosBean> data) {
        try {
            list.clear();
            list.addAll(data);
            adapter.notifyDataSetChanged();
            EventBus.getDefault().post(new UploadImageEvent(list));
            if (deleteImageDialog != null) {
                deleteImageDialog.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePhotoSuccess(int position) {
        try {
            list.remove(position);
            adapter.notifyDataSetChanged();
            EventBus.getDefault().post(new UploadImageEvent(list));
            if (deleteImageDialog != null) {
                deleteImageDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.toolbar_text)
    public void onClickUpload() {
        ImageSelectorUtil.openImageList(AlbumActivity.this, 9, REQUEST_LIST_CODE);
    }

    private void showDeleteDialog(final int position) {
        try {
            if (deleteImageDialog == null) {
                deleteImageDialog = new DeleteImageDialog(AlbumActivity.this);
            }
            deleteImageDialog.setOnClickDeleteDialogListener(new DeleteImageDialog.OnClickDeleteDialogListener() {
                @Override
                public void onClickSetCover() {

                }

                @Override
                public void onClickDeleteImage() {
                    presenter.deletePhoto(position, list.get(position).getId());
                }
            });
            deleteImageDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
