package com.quduo.welfareshop.ui.mine.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.mine.adapter.EditInfoPhotoAdapter;
import com.quduo.welfareshop.ui.mine.presenter.EditMyInfoPresenter;
import com.quduo.welfareshop.ui.mine.view.IEditMyInfoView;
import com.quduo.welfareshop.widgets.CircleImageView;
import com.quduo.welfareshop.widgets.CustomeGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/2 16:38
 * Description:编辑资料
 */
public class EditMyInfoActivity extends BaseMvpActivity<IEditMyInfoView, EditMyInfoPresenter> implements IEditMyInfoView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.photoGridView)
    CustomeGridView photoGridView;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.birthday)
    TextView birthday;
    @BindView(R.id.des)
    TextView des;
    @BindView(R.id.emotion)
    TextView emotion;
    @BindView(R.id.job)
    TextView job;
    @BindView(R.id.blood)
    TextView blood;
    @BindView(R.id.height)
    TextView height;
    @BindView(R.id.weight)
    TextView weight;
    @BindView(R.id.wechat_num)
    TextView wechatNum;
    @BindView(R.id.phone_num)
    TextView phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_edit_my_info);
        ButterKnife.bind(this);
        initToolbar();
        initView();
    }

    private void initToolbar() {
        toolbarTitle.setText("编辑");
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void initView() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        photoGridView.setAdapter(new EditInfoPhotoAdapter(EditMyInfoActivity.this, list));
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
    public EditMyInfoPresenter initPresenter() {
        return new EditMyInfoPresenter(this);
    }
}
