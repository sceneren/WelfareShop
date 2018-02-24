package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

public class MidnightVideoAdapter extends RecyclerView.Adapter<MidnightVideoAdapter.MidnightVideoViewHolder> {
    private Context context;
    private List<String> list;

    public MidnightVideoAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MidnightVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MidnightVideoViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_welfare_midnight_video_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MidnightVideoViewHolder holder, int position) {
        String url = "http://imgsrc.baidu.com/imgad/pic/item/3bf33a87e950352a0c1c9a355843fbf2b2118b8a.jpg";
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(url)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MidnightVideoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
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
