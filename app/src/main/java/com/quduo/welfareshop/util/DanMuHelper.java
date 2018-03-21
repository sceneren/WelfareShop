package com.quduo.welfareshop.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.anbetter.danmuku.model.DanMuModel;
import com.anbetter.danmuku.model.utils.DimensionUtil;
import com.anbetter.danmuku.view.IDanMuParent;
import com.quduo.welfareshop.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public final class DanMuHelper {

    private ArrayList<WeakReference<IDanMuParent>> mDanMuViewParents;
    private Context mContext;

    public DanMuHelper(Context context) {
        this.mContext = context.getApplicationContext();
        this.mDanMuViewParents = new ArrayList<>();
    }

    public void release() {
        if (mDanMuViewParents != null) {
            for (WeakReference<IDanMuParent> danMuViewParentsRef : mDanMuViewParents) {
                if (danMuViewParentsRef != null) {
                    IDanMuParent danMuParent = danMuViewParentsRef.get();
                    if (danMuParent != null)
                        danMuParent.release();
                }
            }
            mDanMuViewParents.clear();
            mDanMuViewParents = null;
        }

        mContext = null;
    }

    public void add(final IDanMuParent danMuViewParent) {
        if (danMuViewParent != null) {
            danMuViewParent.clear();
        }

        if (mDanMuViewParents != null) {
            mDanMuViewParents.add(new WeakReference<>(danMuViewParent));
        }
    }

    public void addDanMu(String content) {
        if (mDanMuViewParents != null) {
            WeakReference<IDanMuParent> danMuViewParent = mDanMuViewParents.get(0);
            DanMuModel danMuView = createDanMuView(content);
            if (danMuViewParent != null && danMuView != null && danMuViewParent.get() != null) {
                danMuViewParent.get().add(danMuView);
            }
        }
    }

    private DanMuModel createDanMuView(String content) {
        final DanMuModel danMuView = new DanMuModel();
        danMuView.setDisplayType(DanMuModel.RIGHT_TO_LEFT);
        danMuView.setPriority(DanMuModel.NORMAL);
        danMuView.marginLeft = DimensionUtil.dpToPx(mContext, 30);

        // 显示的文本内容
        danMuView.textSize = DimensionUtil.spToPx(mContext, 12);
        danMuView.textColor = ContextCompat.getColor(mContext, R.color.white);
        danMuView.textMarginLeft = DimensionUtil.dpToPx(mContext, 5);
        danMuView.text = content;
        // 弹幕文本背景
        danMuView.textBackground = ContextCompat.getDrawable(mContext, R.drawable.bg_danmu);
        danMuView.textBackgroundMarginLeft = DimensionUtil.dpToPx(mContext, 15);
        danMuView.textBackgroundPaddingTop = DimensionUtil.dpToPx(mContext, 3);
        danMuView.textBackgroundPaddingBottom = DimensionUtil.dpToPx(mContext, 3);
        danMuView.textBackgroundPaddingRight = DimensionUtil.dpToPx(mContext, 15);
        danMuView.enableTouch(false);
        return danMuView;
    }

}