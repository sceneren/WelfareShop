package com.quduo.welfareshop.itemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;

/**
 * Case By: 商城首页
 * package:wiki.scene.shop.itemDecoration
 * Author：scene on 2017/6/28 17:28
 */
public class ShopIndexItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item positiono
        int space = SizeUtils.dp2px(2);
        if (position > 0) {
            if (position % 2 == 0) {
                //右边的
                outRect.left = space / 2;
            } else {
                //左边的
                outRect.right = space / 2;
            }
            outRect.bottom = space;
        }
    }
}