package com.quduo.welfareshop.itemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Case By: 夺宝的分割线
 * package:wiki.scene.shop.itemDecoration
 * Author：scene on 2017/6/28 17:28
 */
public class IndianaItemDecoration extends RecyclerView.ItemDecoration {
    private int spacing = PtrLocalDisplay.designedDP2px(1);

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % 2;
        if (position == 0) {
            outRect.bottom = spacing;
        } else {
            outRect.right = column * spacing / 2; // column * ((1f / spanCount) * spacing)
            outRect.left = spacing - (column + 1) * spacing / 2; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position > 2) {
                outRect.top = spacing; // item top
            }
        }
    }
}