package com.quduo.welfareshop.ui.friend.dialog;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseActivity;
import com.quduo.welfareshop.event.DynamicCommentSuccessEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.ui.friend.entity.DynamicCommentInfo;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportHelper;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

public class CommentDynamicDialog extends BaseActivity {
    public static final String ARG_DYNAMIC_ID = "id";
    public static final String ARG_DYNAMIC_POSITION = "position";
    @BindView(R.id.content)
    EditText content;
    private int dynamicId = 0;
    private int position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFinishOnTouchOutside(true);
        setContentView(R.layout.dialog_dynamic_comment);
        ButterKnife.bind(this);
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = PtrLocalDisplay.SCREEN_WIDTH_PIXELS;
        getWindow().setAttributes(p);
        getWindow().setGravity(Gravity.BOTTOM);
        try {
            SupportHelper.showSoftInput(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dynamicId = getIntent().getIntExtra(ARG_DYNAMIC_ID, 0);
        position = getIntent().getIntExtra(ARG_DYNAMIC_POSITION, 0);
    }

    @OnClick(R.id.btn_comment)
    public void onClickBtnComment() {
        try {
            String contentStr = content.getText().toString().trim();
            if (StringUtils.isEmpty(contentStr)) {
                ToastUtils.showShort("请输入你要评论的内容");
                return;
            }
            sendComment(contentStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        hideSoftInput();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        OkGo.getInstance().cancelTag(ApiUtil.DYNAMIC_SEND_COMMENT_TAG);
        super.onDestroy();
    }

    private void hideSoftInput() {
        try {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            if (null != imm) {
                imm.hideSoftInputFromWindow(content.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendComment(final String contentStr) {
        try {
            showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("video_id", dynamicId);
            params.put("content", contentStr);
            OkGo.<LzyResponse<DynamicCommentInfo>>post(ApiUtil.API_PRE + ApiUtil.DYNAMIC_SEND_COMMENT)
                    .tag(ApiUtil.DYNAMIC_SEND_COMMENT_TAG)
                    .params(params)
                    .execute(new JsonCallback<LzyResponse<DynamicCommentInfo>>() {
                        @Override
                        public void onSuccess(Response<LzyResponse<DynamicCommentInfo>> response) {
                            try {
                                showMessage("评论成功");
                                EventBus.getDefault().post(new DynamicCommentSuccessEvent(position, response.body().data));
                                new Thread() {
                                    public void run() {
                                        try {
                                            Instrumentation inst = new Instrumentation();
                                            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    finish();
                                                }
                                            });
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                            } catch (Exception e) {
                                e.printStackTrace();
                                showMessage("评论失败请重试");
                            }
                        }

                        @Override
                        public void onError(Response<LzyResponse<DynamicCommentInfo>> response) {
                            super.onError(response);
                            try {
                                showMessage(response.getException().getMessage());
                            } catch (Exception e) {
                                e.printStackTrace();
                                showMessage("评论失败请重试");
                            }
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            hideLoadingDialog();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showLoadingDialog() {
        try {
            StyledDialog.buildLoading().setActivity(this).show();
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

    private void showMessage(String message) {
        try {
            ToastUtils.showShort(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        return super.dispatchKeyShortcutEvent(event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                return super.dispatchKeyEvent(event);
            } else if (event.getAction() == KeyEvent.ACTION_UP) {
                hideSoftInput();
                finish();
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

}
