package com.quduo.welfareshop.activity;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewTreeObserver;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.quduo.welfareshop.MainFragment;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseActivity;
import com.quduo.welfareshop.util.keyboard.OnSoftKeyboardStateChangedListener;

import java.util.ArrayList;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/1/25 11:58
 * Description:主界面
 */
public class MainActivity extends BaseActivity {
    private ArrayList<OnSoftKeyboardStateChangedListener> mKeyboardStateListeners;      //软键盘状态监听列表
    private ViewTreeObserver.OnGlobalLayoutListener mLayoutChangeListener;
    private boolean mIsSoftKeyboardShowing;

    private static int keyBoardHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_main);
        LogUtils.e("当前渠道号：" + MyApplication.getInstance().getResourceId());
        init();
        keyBoardHeight = SPUtils.getInstance().getInt("KEYBOARD_HEIGHT", 0);
        if (keyBoardHeight == 0) {
            initKeyBoardHeightListener();
        }
    }

    private void init() {
        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance());
        }
    }

    public static int getKeyBoardHeight() {
        return keyBoardHeight;
    }

    private void initKeyBoardHeightListener() {
        mIsSoftKeyboardShowing = false;
        mKeyboardStateListeners = new ArrayList<>();
        mLayoutChangeListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //判断窗口可见区域大小
                Rect r = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //如果屏幕高度和Window可见区域高度差值大于整个屏幕高度的1/3，则表示软键盘显示中，否则软键盘为隐藏状态。
                int heightDifference = PtrLocalDisplay.SCREEN_HEIGHT_PIXELS - (r.bottom - r.top);
                boolean isKeyboardShowing = heightDifference > PtrLocalDisplay.SCREEN_HEIGHT_PIXELS / 3;
                keyBoardHeight = heightDifference;
                //如果之前软键盘状态为显示，现在为关闭，或者之前为关闭，现在为显示，则表示软键盘的状态发生了改变
                if ((mIsSoftKeyboardShowing && !isKeyboardShowing) || (!mIsSoftKeyboardShowing && isKeyboardShowing)) {
                    mIsSoftKeyboardShowing = isKeyboardShowing;
                    SPUtils.getInstance().put("KEYBOARD_HEIGHT", keyBoardHeight);
                    for (int i = 0; i < mKeyboardStateListeners.size(); i++) {
                        OnSoftKeyboardStateChangedListener listener = mKeyboardStateListeners.get(i);
                        listener.OnSoftKeyboardStateChanged(mIsSoftKeyboardShowing, heightDifference);
                    }
                    removeKeyBoardHeightListener();
                }
            }
        };
        //注册布局变化监听
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(mLayoutChangeListener);
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    protected void onDestroy() {
        removeKeyBoardHeightListener();
        super.onDestroy();
    }


    private void removeKeyBoardHeightListener() {
        //移除布局变化监听
        try {
            if (mLayoutChangeListener != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(mLayoutChangeListener);
                } else {
                    getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(mLayoutChangeListener);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
