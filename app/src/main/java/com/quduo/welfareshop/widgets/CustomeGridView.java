package com.quduo.welfareshop.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Case By: 解决嵌套GridView高度不正常
 * package:wiki.scene.shop.widgets
 * Author：scene on 2017/6/29 13:37
 */
public class CustomeGridView extends GridView {

    public CustomeGridView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public CustomeGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public CustomeGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}