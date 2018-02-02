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
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/2 11:47
 * Description:我的关注
 */
public class FollowAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<String> list;

    public FollowAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FollowViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_friend_follow_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FollowViewHolder viewHolder = (FollowViewHolder) holder;
        String url = "http://e.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg";
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(url)
                .into(viewHolder.avatar);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class FollowViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar)
        SelectableRoundedImageView avatar;
        @BindView(R.id.nickname)
        TextView nickname;
        @BindView(R.id.sex)
        ImageView sex;
        @BindView(R.id.age)
        TextView age;
        @BindView(R.id.des)
        TextView des;
        @BindView(R.id.distance)
        TextView distance;
        @BindView(R.id.chat)
        TextView chat;

        FollowViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
