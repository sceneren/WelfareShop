package com.quduo.welfareshop.widgets;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by my
 */
public class RenderingView extends View implements View.OnClickListener {
    //是否layout完成
    private boolean mInit;

    private Paint mPaint;

    private RectF mRectF;

    // 0 - 1
    private float mProgress;

    private Bitmap mCurBitmap;
    private Rect mBitmapRect;
    private RectF mDrawableRectF;

    private Rect mSrc;
    private RectF mDst;

    private static final int DEFAULT_ANIMATION_DURATION = 1000;

    private OnClickListener mClickListener;

    private ObjectAnimator mAnimator;

    public RenderingView(Context context) {
        this(context, null);
    }

    public RenderingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RenderingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mInit = false;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        mRectF = new RectF();
        mBitmapRect = new Rect();
        mDrawableRectF = new RectF();
        mSrc = new Rect();
        mDst = new RectF();
        mProgress = 0;
        mAnimator = ObjectAnimator.ofFloat(this, "progress", 1);
        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        super.setOnClickListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            doInit(getViewRect(this));
        }
    }

    /**
     * 获取某个View的正确的位置
     *
     * @param view v
     * @return 位置
     */
    public static RectF getViewRect(View view) {
        int vLeft, vTop;
        int location[] = new int[2];
        view.getLocationInWindow(location);
        vLeft = location[0];
        vTop = location[1];
        return new RectF(vLeft, vTop, vLeft + view.getWidth(), vTop + view.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mInit && mCurBitmap != null) {
            mSrc.set(mBitmapRect.left, mBitmapRect.top, (int) (mBitmapRect.right * mProgress + 0.5f), mBitmapRect.bottom);
            mDst.set(mDrawableRectF.left, mDrawableRectF.top, mDrawableRectF.right * mProgress, mDrawableRectF.bottom);
            canvas.drawBitmap(mCurBitmap, mSrc, mDst, mPaint);
        }
    }

    private void doInit(RectF rectF) {
        if (!mInit || !mRectF.equals(rectF)) {
            mRectF.set(rectF);
            if (mCurBitmap != null) {
                setInformation();
            }
            mInit = true;
        }
    }

    public float getProgress() {
        return mProgress;
    }

    public void setProgress(float progress) {
        this.mProgress = progress;
        invalidate();
    }

    public void setImageBitmap(Bitmap bitmap) {
        mCurBitmap = bitmap;
        if (mCurBitmap != null && mInit) {
            setInformation();
        }
    }

    /**
     * 设置一些信息
     * 图片显示默认是fitCenter样式的
     */
    public void setInformation() {
        float w = mCurBitmap.getWidth();
        float h = mCurBitmap.getHeight();
        float viewW = mRectF.width();
        float viewH = mRectF.height();
        mBitmapRect.set(0, 0, (int) w, (int) h);
        if (w / h > viewW / viewH) {
            //顶着宽
            float curH = h * viewW / w;
            mDrawableRectF.set(0, (viewH - curH) / 2, viewW, (viewH - curH) / 2 + curH);
        } else {
            //顶高
            float curW = w * viewH / h;
            mDrawableRectF.set((viewW - curW) / 2, 0, (viewW - curW) / 2 + curW, viewH);
        }
        beginAnimation(DEFAULT_ANIMATION_DURATION);
    }

    public void beginAnimation(int duration) {
        setProgress(0);
        mAnimator.cancel();
        mAnimator.setDuration(duration).start();
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        mClickListener = l;
    }

    @Override
    public void onClick(View v) {
        beginAnimation(DEFAULT_ANIMATION_DURATION);
        if (mClickListener != null) {
            mClickListener.onClick(v);
        }
    }
}