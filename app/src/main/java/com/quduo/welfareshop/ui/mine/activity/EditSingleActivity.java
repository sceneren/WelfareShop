package com.quduo.welfareshop.ui.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.event.EditMyInfoEvent;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.mine.presenter.EditSinglePresenter;
import com.quduo.welfareshop.ui.mine.view.IEditSingleView;
import com.quduo.welfareshop.widgets.ClearEditText;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/2/5 9:40
 * Description:文本单项修改
 */

public class EditSingleActivity extends BaseMvpActivity<IEditSingleView, EditSinglePresenter> implements IEditSingleView {
    public static final String ARG_TITLE = "title";
    public static final String ARG_CONTENT = "content";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.content)
    ClearEditText content;
    Unbinder unbinder;

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_single);
        unbinder = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbarText.setText("确定");
        title = getIntent().getStringExtra(ARG_TITLE);
        String contentStr = getIntent().getStringExtra(ARG_CONTENT);
        if (title.equals("电话")) {
            content.setInputType(InputType.TYPE_CLASS_PHONE);
        }
        toolbarTitle.setText(title);
        content.setText(contentStr);
        content.setSelection(contentStr.length());
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


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
    public EditSinglePresenter initPresenter() {
        return new EditSinglePresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.toolbar_text)
    public void onClickSave() {
        String contentStr = content.getText().toString().trim();
        if (StringUtils.isEmpty(contentStr)) {
            ToastUtils.showShort("请输入" + title);
            return;
        }
        if (title.equals("电话") && !RegexUtils.isMobileSimple(contentStr)) {
            ToastUtils.showShort("请输入正确的联系方式");
            return;
        }

        EditMyInfoEvent event = new EditMyInfoEvent(title, contentStr);
        EventBus.getDefault().post(event);
        onBackPressed();
    }
}
