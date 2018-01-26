package com.quduo.welfareshop.itemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Case By: 自定义LinearLayoutManager分割线
 * package:wiki.scene.shop.itemDecoration
 * Author：scene on 2017/6/28 17:30
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private boolean hasTopSpace = false;
    private boolean hasLeftAndRightSapce = false;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    public SpacesItemDecoration(int space, boolean hasTopSpace, boolean hasLeftAndRightSapce) {
        this.space = space;
        this.hasTopSpace = hasTopSpace;
        this.hasLeftAndRightSapce = hasLeftAndRightSapce;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (hasLeftAndRightSapce) {
            outRect.left = space;
            outRect.right = space;
        }
        outRect.bottom = space;
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0) {
            if (hasTopSpace) {
                outRect.top = space;
            }
        } else {
            outRect.top = 0;
        }
    }
}