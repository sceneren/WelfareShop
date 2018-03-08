package com.quduo.welfareshop.ui.friend.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.quduo.welfareshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/3/7 16:16
 * Description:筛选附近的人
 */

public class FriendChooseDialog extends Dialog {


    @BindView(R.id.all)
    TextView all;
    @BindView(R.id.male)
    TextView male;
    @BindView(R.id.female)
    TextView female;
    @BindView(R.id.cancel)
    TextView cancel;

    public FriendChooseDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
    }

    public FriendChooseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected FriendChooseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_friend_near_choose);
        ButterKnife.bind(this);
    }

    @Override
    public void show() {
        setCanceledOnTouchOutside(false);
        super.show();
        try {
            Window window = getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = (int) (PtrLocalDisplay.SCREEN_WIDTH_PIXELS * 0.8);                     //使用这种方式更改了dialog的框宽
            window.setAttributes(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.all)
    public void onClickAll() {
        dismiss();
    }

    @OnClick(R.id.male)
    public void onClickMale() {
        dismiss();
    }

    @OnClick(R.id.female)
    public void onClickFemale() {
        dismiss();
    }

    @OnClick(R.id.cancel)
    public void onClickCancel() {
        dismiss();
    }

}
