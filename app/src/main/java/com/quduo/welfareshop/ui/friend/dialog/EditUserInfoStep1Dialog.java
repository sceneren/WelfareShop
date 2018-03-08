package com.quduo.welfareshop.ui.friend.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.blankj.utilcode.util.StringUtils;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseActivity;

import org.joda.time.DateTime;
import org.joda.time.Instant;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/3/8 13:10
 * Description:完善资料第一步
 */

public class EditUserInfoStep1Dialog extends BaseActivity {
    @BindView(R.id.male)
    RadioButton male;
    @BindView(R.id.female)
    RadioButton female;
    @BindView(R.id.sex)
    RadioGroup sex;
    @BindView(R.id.birthday)
    TextView birthday;
    @BindView(R.id.layout_birthday)
    RelativeLayout layoutBirthday;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.skip)
    TextView skip;
    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_edit_userinfo_step_1);
        unbinder = ButterKnife.bind(this);
        setFinishOnTouchOutside(false);
    }

    @OnClick(R.id.layout_birthday)
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
                .setLabel("", "", "", "", "", "")
                .isDialog(true)
                .build();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(birthDayMillis);
        pvTime.setDate(calendar);
        pvTime.show();
    }

    @OnClick(R.id.next)
    public void onClickNext() {
        if (sex.getCheckedRadioButtonId() == R.id.female) {
            startActivity(new Intent(EditUserInfoStep1Dialog.this, EditUserInfoStep2Dialog.class));
        } else {
            startActivity(new Intent(EditUserInfoStep1Dialog.this, EditUserInfoStep3Dialog.class));
        }
        finish();
    }

    @OnClick(R.id.skip)
    public void onClickSkpi(){
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
