package com.quduo.welfareshop.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.blankj.utilcode.util.SizeUtils;

import java.lang.ref.WeakReference;

public class NoTouchRecyclerView extends RecyclerView {
    private static final long TIME_AUTO_POLL = 16;
    private boolean running; //标示是否正在自动轮询
    private boolean canRun;//标示是否可以自动轮询,可在不需要的是否置false
    private AutoPollTask autoPollTask;

    public NoTouchRecyclerView(Context context) {
        super(context);
        init();
    }

    public NoTouchRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NoTouchRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        autoPollTask = new AutoPollTask(this);
    }

    static class AutoPollTask implements Runnable {
        private final WeakReference<NoTouchRecyclerView> mReference;

        //使用弱引用持有外部类引用->防止内存泄漏
        public AutoPollTask(NoTouchRecyclerView reference) {
            this.mReference = new WeakReference<NoTouchRecyclerView>(reference);
        }

        @Override
        public void run() {
            NoTouchRecyclerView recyclerView = mReference.get();
            if (recyclerView != null && recyclerView.running && recyclerView.canRun) {
                recyclerView.scrollBy(0, SizeUtils.dp2px(0.6f));
                recyclerView.postDelayed(recyclerView.autoPollTask, TIME_AUTO_POLL);
                //处理隐藏
                if (recyclerView.getChildCount() > 0 && !recyclerView.canScrollVertically(1)) {
                    recyclerView.setVisibility(GONE);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }

    //开启:如果正在运行,先停止->再开启
    public void start() {
        if (running)
            stop();
        canRun = true;
        running = true;
        postDelayed(autoPollTask, TIME_AUTO_POLL);
    }

    public void stop() {
        running = false;
        removeCallbacks(autoPollTask);
    }
}
