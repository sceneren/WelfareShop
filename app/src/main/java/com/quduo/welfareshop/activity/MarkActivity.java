package com.quduo.welfareshop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mcxtzhang.swipemenulib.CstViewPager;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.adapter.MyPagerAdapter;
import com.quduo.welfareshop.base.BaseActivity;
import com.quduo.welfareshop.util.FileUtils;
import com.quduo.welfareshop.util.PageFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 书签和目录
 * Created by Administrator on 2016/1/6.
 */
public class MarkActivity extends BaseActivity {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.pager)
    CstViewPager pager;
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    private PageFactory pageFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);
        unbinder = ButterKnife.bind(this);

        initData();
    }


    private void initData() {
        pageFactory = PageFactory.getInstance();
        initToolbar();
        setTabsValue();
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), pageFactory.getBookPath()));
        tabs.setupWithViewPager(pager);
    }

    private void initToolbar() {
        try {
            toolbarTitle.setText(FileUtils.getFileName(pageFactory.getBookPath()));
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

    private void setTabsValue() {
        String tabTitle[] = {"目录", "书签"};
        tabs.addTab(tabs.newTab().setText(tabTitle[0]));
        tabs.addTab(tabs.newTab().setText(tabTitle[1]));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
