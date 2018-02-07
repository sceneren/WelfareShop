package com.quduo.welfareshop.ui.friend.userdef;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;


import com.quduo.welfareshop.R;

import sj.keyboard.XhsEmoticonsKeyBoard;
import sj.keyboard.utils.EmoticonsKeyboardUtils;

public class SimpleUserdefEmoticonsKeyBoard extends XhsEmoticonsKeyBoard {

    public final int APPS_HEIGHT = 120;

    public SimpleUserdefEmoticonsKeyBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void inflateKeyboardBar(){
        mInflater.inflate(R.layout.activity_chat_bottom, this);
    }

    @Override
    protected View inflateFunc(){
        return mInflater.inflate(R.layout.view_func_emoticon_userdef, null);
    }

    @Override
    public void reset() {
        EmoticonsKeyboardUtils.closeSoftKeyboard(getContext());
        mLyKvml.hideAllFuncView();
        mBtnFace.setImageResource(R.drawable.ic_chat_bottom_emoji);
    }

    @Override
    public void onFuncChange(int key) {
        if (FUNC_TYPE_EMOTION == key) {
            mBtnFace.setImageResource(R.drawable.ic_chat_bottom_text);
        } else {
            mBtnFace.setImageResource(R.drawable.ic_chat_bottom_emoji);
        }
        checkVoice();
    }

    @Override
    public void OnSoftClose() {
        super.OnSoftClose();
        if (mLyKvml.getCurrentFuncKey() == FUNC_TYPE_APPPS) {
            setFuncViewHeight(EmoticonsKeyboardUtils.dip2px(getContext(), APPS_HEIGHT));
        }
    }

    @Override
    protected void showText() {
        mEtChat.setVisibility(VISIBLE);
        mBtnFace.setVisibility(VISIBLE);
        mBtnVoice.setVisibility(GONE);
    }

    @Override
    protected void showVoice() {
        mEtChat.setVisibility(GONE);
        mBtnFace.setVisibility(GONE);
        mBtnVoice.setVisibility(VISIBLE);
        reset();
    }

    @Override
    protected void checkVoice() {
        if (mBtnVoice.isShown()) {
            mBtnVoiceOrText.setImageResource(R.drawable.ic_chat_bottom_text);
        } else {
            mBtnVoiceOrText.setImageResource(R.drawable.ic_chat_bottom_audio);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == com.keyboard.view.R.id.btn_voice_or_text) {
            if (mEtChat.isShown()) {
                mBtnVoiceOrText.setImageResource(R.drawable.ic_chat_bottom_text);
                showVoice();
            } else {
                showText();
                mBtnVoiceOrText.setImageResource(R.drawable.ic_chat_bottom_audio);
                EmoticonsKeyboardUtils.openSoftKeyboard(mEtChat);
            }
        } else if (i == com.keyboard.view.R.id.btn_face) {
            toggleFuncView(FUNC_TYPE_EMOTION);
        } else if (i == com.keyboard.view.R.id.btn_multimedia) {
            toggleFuncView(FUNC_TYPE_APPPS);
            setFuncViewHeight(EmoticonsKeyboardUtils.dip2px(getContext(), APPS_HEIGHT));
        }
    }
}
