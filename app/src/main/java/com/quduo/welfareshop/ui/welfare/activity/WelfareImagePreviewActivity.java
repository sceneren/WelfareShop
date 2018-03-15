package com.quduo.welfareshop.ui.welfare.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseBackActivity;
import com.quduo.welfareshop.ui.welfare.adapter.WelfareGalleryPreviewImageAdapter;
import com.quduo.welfareshop.widgets.HackyViewPager;

import java.text.MessageFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/2/22 12:00
 * Description:图库预览大图
 */

public class WelfareImagePreviewActivity extends BaseBackActivity {
    public static final String ARG_URLS = "arg_urls";
    public static final String ARG_POSITION = "arg_position";
    @BindView(R.id.viewPager)
    HackyViewPager viewPager;
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.current_position)
    TextView currentPosition;

    private List<String> imageUrls;
    private int position;

    private Dialog noScoreDialog;
    private Dialog openVipDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_image);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        imageUrls = intent.getStringArrayListExtra(ARG_URLS);
        position = intent.getIntExtra(ARG_POSITION, 0);
        if (imageUrls == null || imageUrls.size() <= 0) {
            ToastUtils.showShort("该图片暂时无法查看");
            finish();
        }
        initToolbar();
        initView();
    }

    @Override
    public void initView() {
        if (imageUrls.size() > 1) {
            currentPosition.setVisibility(View.VISIBLE);
        } else {
            currentPosition.setVisibility(View.GONE);
        }
        WelfareGalleryPreviewImageAdapter adapter = new WelfareGalleryPreviewImageAdapter(WelfareImagePreviewActivity.this, imageUrls);
        adapter.setOnClickOpenVipListener(new WelfareGalleryPreviewImageAdapter.OnClickOpenVipListener() {
            @Override
            public void onClickOpenVip() {
                showOpenVipDialog();
            }
        });
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        currentPosition.setText(MessageFormat.format("{0}/{1}", position + 1, imageUrls.size()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition.setText(MessageFormat.format("{0}/{1}", position + 1, imageUrls.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initToolbar() {
        try {
            toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void showLoadingDialog() {
        try {
            StyledDialog.buildLoading().setActivity(WelfareImagePreviewActivity.this).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideLoadingDialog() {
        try {
            StyledDialog.dismissLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showNoScoreDialog() {
        try {
            noScoreDialog = StyledDialog.buildIosAlert("提示", "趣币不足，请充值", new MyDialogListener() {
                @Override
                public void onFirst() {

                }

                @Override
                public void onSecond() {

                }
            }).setBtnText("确定", "取消")
                    .show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideNoScoreDialog() {
        try {
            StyledDialog.dismiss(noScoreDialog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showOpenVipDialog() {
        try {
            openVipDialog = StyledDialog.buildIosAlert("消耗3查看", "您还剩余0趣币", new MyDialogListener() {
                @Override
                public void onFirst() {

                }

                @Override
                public void onSecond() {

                }
            }).setBtnText("确定", "取消").show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideOpenVipDialog() {
        try {
            if (openVipDialog != null) {
                StyledDialog.dismiss(openVipDialog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
