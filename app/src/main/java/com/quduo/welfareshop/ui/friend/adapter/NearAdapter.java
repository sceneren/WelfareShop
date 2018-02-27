package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.widgets.CustomHeightRoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/1 18:00
 * Description:附近的人
 */

public class NearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> list;

    public NearAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NearViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_friend_near_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NearViewHolder viewHolder = (NearViewHolder) holder;
        String url = "http://e.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg";
        GlideApp.with(context)
                .asBitmap()
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_agreement)
                .into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class NearViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        CustomHeightRoundedImageView image;
        @BindView(R.id.sex)
        ImageView sex;
        @BindView(R.id.age)
        TextView age;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.distance)
        TextView distance;

        NearViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
