package com.quduo.welfareshop.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.widgets.RatioImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/3/1 11:41
 * Description:我收藏的视频
 */

public class MyFollowVideoAdapter extends RecyclerView.Adapter<MyFollowVideoAdapter.MyFollowVideoViewHolder> {
    private Context context;
    private List<String> list;

    public MyFollowVideoAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public MyFollowVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyFollowVideoViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_my_follow_video_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyFollowVideoViewHolder holder, int position) {
        String url = "http://scimg.jb51.net/allimg/150708/14-150FQ15305155.jpg";
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

    class MyFollowVideoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.baseimage)
        RatioImageView baseimage;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.play_number)
        TextView playNumber;
        @BindView(R.id.follow_number)
        TextView followNumber;

        MyFollowVideoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
