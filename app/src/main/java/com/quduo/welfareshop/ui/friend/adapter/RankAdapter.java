package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.request.RequestOptions;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.widgets.MultiImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

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
                .load(url)
                .centerCrop()
                .into(viewHolder.avatar);
        List<String> imageList = new ArrayList<>();
        for (int i = 0; i < (position + 1); i++) {
            imageList.add(url);
        }
        viewHolder.multiImageView.setList(imageList);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class FriendRankViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.nickname)
        TextView nickname;
        @BindView(R.id.sex)
        ImageView sex;
        @BindView(R.id.age)
        TextView age;
        @BindView(R.id.multiImageView)
        MultiImageView multiImageView;

        FriendRankViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
