package com.quduo.welfareshop.ui.mine.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.quduo.welfareshop.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/3/19 11:02
 * Description:删除图片设为主页
 */

public class DeleteImageDialog extends Dialog {

    private OnClickDeleteDialogListener onClickDeleteDialogListener;

    public DeleteImageDialog(@NonNull Context context) {
        super(context, R.style.bottom_dialog);
    }

    public DeleteImageDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected DeleteImageDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_delete_image);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
    }

    public void setOnClickDeleteDialogListener(OnClickDeleteDialogListener onClickDeleteDialogListener) {
        if (this.onClickDeleteDialogListener != null) {
            this.onClickDeleteDialogListener = null;
        }
        this.onClickDeleteDialogListener = onClickDeleteDialogListener;
    }

    @Override
    public void show() {
        super.show();
        try {
            Window window = getWindow();
            if (window != null) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.width = (int) (PtrLocalDisplay.SCREEN_WIDTH_PIXELS * 0.9);
                params.gravity = Gravity.BOTTOM;
                window.setAttributes(params);
                window.setWindowAnimations(R.style.pop_animation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.delete)
    void onClickDelete() {
        if (onClickDeleteDialogListener != null) {
            onClickDeleteDialogListener.onClickDeleteImage();
        }
    }

    @OnClick(R.id.cancel)
    void onClickCancel() {
        dismiss();
    }

    @OnClick(R.id.set_cover)
    void onClickSetCover() {
        if (onClickDeleteDialogListener != null) {
            onClickDeleteDialogListener.onClickSetCover();
        }
    }

    public interface OnClickDeleteDialogListener {
        void onClickSetCover();

        void onClickDeleteImage();
    }
}
