package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/24 10:37
 * Description:午夜影院
 */

public class MidnightVideoAdapter extends BaseQuickAdapter<String, MidnightVideoAdapter.MidnightVideoViewHolder> {
    private Context context;

    public MidnightVideoAdapter(Context context, List<String> list) {
        super(R.layout.fragment_welfare_midnight_video_item, list);
        this.context = context;
    }


    @Override
    protected void convert(MidnightVideoViewHolder helper, String item) {
        String url = "http://imgsrc.baidu.com/imgad/pic/item/3bf33a87e950352a0c1c9a355843fbf2b2118b8a.jpg";
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(url)
                .into(helper.imageView);
    }

    static class MidnightVideoViewHolder extends BaseViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.tag_bg)
        ImageView tagBg;
        @BindView(R.id.tag)
        TextView tag;
        @BindView(R.id.play_number)
        TextView playNumber;
        @BindView(R.id.follow_number)
        TextView followNumber;
        @BindView(R.id.des)
        TextView des;
        @BindView(R.id.video_time)
        TextView videoTime;

        MidnightVideoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
