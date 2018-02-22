package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.PreviewImageActivity;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;
import com.w4lle.library.NineGridlayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/2 10:37
 * Description:人气榜
 */
public class RankAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<String> list;

    public RankAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FriendRankViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_friend_rank_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FriendRankViewHolder viewHolder = (FriendRankViewHolder) holder;
        String url = "http://e.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg";
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(url)
                .into(viewHolder.avatar);
        final ArrayList<String> imageList = new ArrayList<>();
        for (int i = 0; i < (position + 1); i++) {
            imageList.add(url);
        }
        NineGridImageAdapter imageAdapter = new NineGridImageAdapter(context, imageList);
        viewHolder.imageLayout.setOnItemClickListerner(new NineGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(context, PreviewImageActivity.class);
                intent.putExtra(PreviewImageActivity.ARG_URLS, imageList);
                intent.putExtra(PreviewImageActivity.ARG_POSITION, position);
                context.startActivity(intent);
            }
        });
        viewHolder.imageLayout.setAdapter(imageAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class FriendRankViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar)
        SelectableRoundedImageView avatar;
        @BindView(R.id.nickname)
        TextView nickname;
        @BindView(R.id.sex)
        ImageView sex;
        @BindView(R.id.age)
        TextView age;
        @BindView(R.id.image_layout)
        NineGridlayout imageLayout;

        FriendRankViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
