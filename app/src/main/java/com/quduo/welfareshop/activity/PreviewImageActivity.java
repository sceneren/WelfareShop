package com.quduo.welfareshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.adapter.PreviewImageAdapter;
import com.quduo.welfareshop.mvp.BaseBackActivity;
import com.quduo.welfareshop.widgets.HackyViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/2/22 12:00
 * Description:预览大图
 */

public class PreviewImageActivity extends BaseBackActivity {
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
        viewPager.setAdapter(new PreviewImageAdapter(PreviewImageActivity.this, imageUrls));
        viewPager.setCurrentItem(position);
        currentPosition.setText(String.format("%d/%d", position + 1, imageUrls.size()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition.setText(String.format("%d/%d", position + 1, imageUrls.size()));
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
}
