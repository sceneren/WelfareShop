package com.quduo.welfareshop.itemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;

/**
 * Case By: 自定义GirdLayoutManager类型的分割线
 * package:wiki.scene.shop.itemDecoration
 * Author：scene on 2017/6/28 17:28
 */
public class MyFollowImageSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int space = 10;

    public MyFollowImageSpacingItemDecoration() {
        space = SizeUtils.dp2px(5);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        if (position < 2) {
            //第一行
            outRect.top = space;
        }
        if (position % 2 == 0) {
            //第一列
            outRect.left = space;
            outRect.right = space / 2;
        } else {
            outRect.left = space / 2;
            outRect.right = space;
        }
        outRect.bottom = space;
    }
}