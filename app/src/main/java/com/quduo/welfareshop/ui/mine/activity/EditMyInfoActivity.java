package com.quduo.welfareshop.ui.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.blankj.utilcode.util.StringUtils;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.event.EditMyInfoEvent;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.mine.adapter.EditInfoPhotoAdapter;
import com.quduo.welfareshop.ui.mine.presenter.EditMyInfoPresenter;
import com.quduo.welfareshop.ui.mine.view.IEditMyInfoView;
import com.quduo.welfareshop.util.ImageSelectorUtil;
import com.quduo.welfareshop.widgets.CircleImageView;
import com.quduo.welfareshop.widgets.CustomeGridView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.joda.time.DateTime;
import org.joda.time.Instant;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/2/2 16:38
 * Description:编辑资料
 */
public class EditMyInfoActivity extends BaseMvpActivity<IEditMyInfoView, EditMyInfoPresenter> implements IEditMyInfoView {
    private static final int REQUEST_LIST_CODE = 10001;
    private static final int REQUEST_CAMERA_CODE = 10002;

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
    Unbinder unbinder;

    private List<String> sexList = null;
    private EditInfoPhotoAdapter adapter;

    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_edit_my_info);
        unbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
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
        adapter = new EditInfoPhotoAdapter(EditMyInfoActivity.this, list);
        photoGridView.setAdapter(adapter);
        photoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.size() < 9) {
                    if (position == 0) {
                        ImageSelectorUtil.openImageList(EditMyInfoActivity.this, 9 - list.size(), REQUEST_LIST_CODE);
                    } else {
                        //删除图片
                        list.remove(position - 1);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    //删除图片
                    list.remove(position);
                    adapter.notifyDataSetChanged();
                }
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
    public EditMyInfoPresenter initPresenter() {
        return new EditMyInfoPresenter(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAMERA_CODE && resultCode == RESULT_OK && data != null) {
            String path = data.getStringExtra("result"); // 图片地址
            list.add(path);
            adapter.notifyDataSetChanged();
        }
        // 图片选择结果回调
        if (requestCode == REQUEST_LIST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra("result");
            for (String path : pathList) {
                list.add(path);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @OnClick(R.id.nickname)
    public void onClick() {
        Intent intent = new Intent(EditMyInfoActivity.this, EditSingleActivity.class);
        intent.putExtra(EditSingleActivity.ARG_TITLE, "昵称");
        startActivity(intent);
    }

    @Subscribe
    public void onUpdateSuccess(EditMyInfoEvent event) {
        if (event == null) {
            return;
        }
        if (event.getTitle().equals("昵称")) {
            nickname.setText(event.getContent());
        }
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.sex)
    public void onClickSex() {
        String sexStr = sex.getText().toString().trim();
        chooseSex(sexStr);
    }

    private void chooseSex(String sexStr) {
        if (sexList == null) {
            sexList = new ArrayList<>();
            sexList.add("男");
            sexList.add("女");
            sexList.add("保密");
        }
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(EditMyInfoActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                sex.setText(sexList.get(options1));
            }
        }).build();
        optionsPickerView.setPicker(sexList);
        switch (sexStr) {
            case "男":
                optionsPickerView.setSelectOptions(0);
                break;
            case "女":
                optionsPickerView.setSelectOptions(1);
                break;
            default:
                optionsPickerView.setSelectOptions(2);
                break;
        }
        optionsPickerView.show();
    }

    @OnClick(R.id.birthday)
    public void onClickBirthDay() {
        String birthDayStr = birthday.getText().toString().trim();
        long birthDayMillis;
        if (StringUtils.isEmpty(birthDayStr)) {
            birthDayMillis = new Instant().getMillis();
        } else {
            birthDayMillis = new Instant(birthDayStr).getMillis();
        }
        chooseBirthDay(birthDayMillis);
    }

    private void chooseBirthDay(long birthDayMillis) {
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                //选中事件回调
                DateTime dateTime = new DateTime(date);
                birthday.setText(dateTime.toString("yyyy-MM-dd"));
            }
        })
                .setRange(1900, 2050)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "").build();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(birthDayMillis);
        pvTime.setDate(calendar);
        pvTime.show();
    }

}
