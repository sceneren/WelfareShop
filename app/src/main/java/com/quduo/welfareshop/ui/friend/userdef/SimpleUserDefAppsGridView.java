package com.quduo.welfareshop.ui.friend.userdef;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.ui.friend.entity.AppBean;

import java.util.ArrayList;

public class SimpleUserDefAppsGridView extends SimpleAppsGridView {
    private OnClickItemListener onClickItemListener;

    public SimpleUserDefAppsGridView(Context context) {
        super(context);
    }

    public SimpleUserDefAppsGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    protected void init() {
        GridView gv_apps = (GridView) view.findViewById(R.id.gv_apps);
        gv_apps.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gv_apps.setNumColumns(2);
        ArrayList<AppBean> mAppBeanList = new ArrayList<>();
        mAppBeanList.add(new AppBean(R.drawable.ic_chat_gallery, "图片"));
        mAppBeanList.add(new AppBean(R.drawable.ic_chat_camare, "拍照"));
        ChattingAppsAdapter adapter = new ChattingAppsAdapter(getContext(), mAppBeanList);
        gv_apps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ToastUtils.showShort(mAppBeanList.get(position).getFuncName());
                if (onClickItemListener != null)
                    if (position == 0) {
                        onClickItemListener.onClickGrallery();
                    } else {
                        onClickItemListener.onClickCamera();
                    }
            }
        });
        gv_apps.setAdapter(adapter);
    }

    public interface OnClickItemListener {
        void onClickCamera();

        void onClickGrallery();
    }
}
