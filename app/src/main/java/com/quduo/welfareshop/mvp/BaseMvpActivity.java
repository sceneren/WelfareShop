package com.quduo.welfareshop.mvp;

import android.content.Intent;
import android.os.Bundle;

import com.quduo.welfareshop.R;

import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * Case By: 基类
 * package:
 * Author：scene on 2017/6/27 10:52
 */
public abstract class BaseMvpActivity<V, T extends BasePresenter<V>> extends SwipeBackActivity {
    public T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.dettach();
        super.onDestroy();
    }


    // 实例化presenter
    public abstract T initPresenter();

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }
}
