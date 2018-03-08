package com.quduo.welfareshop.ui.friend.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseActivity;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.util.ImageSelectorUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/3/8 13:10
 * Description:完善资料第一步
 */

public class EditUserInfoStep2Dialog extends BaseActivity {
    private static final int REQUEST_AVATAR_LIST_CODE = 10001;
    private static final int REQUEST_IMAGE_LIST_CODE = 10002;
    Unbinder unbinder;
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.delete1)
    ImageView delete1;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.delete2)
    ImageView delete2;
    @BindView(R.id.image3)
    ImageView image3;
    @BindView(R.id.delete3)
    ImageView delete3;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.skip)
    TextView skip;

    private String avaterPath;
    private List<String> imagePathList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_edit_userinfo_step_2);
        unbinder = ButterKnife.bind(this);
        setFinishOnTouchOutside(false);
    }

    @OnClick(R.id.avatar)
    public void onClickAvatar() {
        ImageSelectorUtil.openImageList(EditUserInfoStep2Dialog.this, 1, REQUEST_AVATAR_LIST_CODE);
    }

    @OnClick(R.id.image1)
    public void onClickImage1() {
        if (imagePathList.size() < 3) {
            ImageSelectorUtil.openImageList(EditUserInfoStep2Dialog.this, 3 - imagePathList.size(), REQUEST_IMAGE_LIST_CODE);
        }
    }

    @OnClick(R.id.delete1)
    public void onClickDelete1() {
        if (imagePathList.size() > 0) {
            imagePathList.remove(0);
        }
        showImages();
    }

    @OnClick(R.id.delete2)
    public void onClickDelete2() {
        if (imagePathList.size() > 2) {
            imagePathList.remove(1);
        } else {
            imagePathList.remove(0);
        }
        showImages();
    }

    @OnClick(R.id.delete3)
    public void onClickDelete3() {
        if (imagePathList.size() > 2) {
            imagePathList.remove(2);
        } else {
            imagePathList.remove(1);
        }

        showImages();
    }

    @OnClick(R.id.next)
    public void onClickNext() {
        startActivity(new Intent(EditUserInfoStep2Dialog.this, EditUserInfoStep3Dialog.class));
        finish();
    }

    @OnClick(R.id.skip)
    public void onClickSkip() {
        startActivity(new Intent(EditUserInfoStep2Dialog.this, EditUserInfoStep3Dialog.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            // 头像选择结果回调
            if (requestCode == REQUEST_AVATAR_LIST_CODE) {
                List<String> pathList = data.getStringArrayListExtra("result");
                for (String path : pathList) {
                    avaterPath = path;
                }
                showAvatar();
            } else if (requestCode == REQUEST_IMAGE_LIST_CODE) {
                List<String> pathList = data.getStringArrayListExtra("result");
                imagePathList.addAll(pathList);
                showImages();
            }
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void showAvatar() {
        GlideApp.with(this)
                .asBitmap()
                .centerCrop()
                .load(avaterPath)
                .into(avatar);
    }

    private void showImages() {
        switch (imagePathList.size()) {
            case 1:
                image1.setImageResource(R.drawable.ic_dialog_add_image);
                GlideApp.with(this)
                        .asBitmap()
                        .centerCrop()
                        .load(imagePathList.get(0))
                        .into(image2);
                image3.setImageResource(0);
                delete1.setVisibility(View.GONE);
                delete2.setVisibility(View.VISIBLE);
                delete3.setVisibility(View.GONE);
                break;
            case 2:
                image1.setImageResource(R.drawable.ic_dialog_add_image);
                GlideApp.with(this)
                        .asBitmap()
                        .centerCrop()
                        .load(imagePathList.get(0))
                        .into(image2);
                GlideApp.with(this)
                        .asBitmap()
                        .centerCrop()
                        .load(imagePathList.get(1))
                        .into(image3);
                delete1.setVisibility(View.GONE);
                delete2.setVisibility(View.VISIBLE);
                delete3.setVisibility(View.VISIBLE);
                break;
            case 3:
                GlideApp.with(this)
                        .asBitmap()
                        .centerCrop()
                        .load(imagePathList.get(0))
                        .into(image1);
                GlideApp.with(this)
                        .asBitmap()
                        .centerCrop()
                        .load(imagePathList.get(1))
                        .into(image2);
                GlideApp.with(this)
                        .asBitmap()
                        .centerCrop()
                        .load(imagePathList.get(2))
                        .into(image3);
                delete1.setVisibility(View.VISIBLE);
                delete2.setVisibility(View.VISIBLE);
                delete3.setVisibility(View.VISIBLE);
                break;
            case 0:
                image1.setImageResource(R.drawable.ic_dialog_add_image);
                image2.setImageResource(0);
                image3.setImageResource(0);
                delete1.setVisibility(View.GONE);
                delete2.setVisibility(View.GONE);
                delete3.setVisibility(View.GONE);
                break;
        }
    }
}
