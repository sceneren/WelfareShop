package com.quduo.welfareshop.ui.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.PreviewImageActivity;
import com.quduo.welfareshop.ui.friend.adapter.NineGridImageAdapter;
import com.w4lle.library.NineGridlayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/28 16:02
 * Description:买家秀
 */

public class GoodsCommentAdapter extends RecyclerView.Adapter<GoodsCommentAdapter.GoodsCommentViewHolder> {
    private Context context;
    private List<String> list;

    public GoodsCommentAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public GoodsCommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsCommentViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_goods_detail_comment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(GoodsCommentViewHolder holder, int position) {
        String url = "http://e.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg";
        final ArrayList<String> imageList = new ArrayList<>();
        for (int i = 0; i < (position + 1); i++) {
            imageList.add(url);
        }
        NineGridImageAdapter imageAdapter = new NineGridImageAdapter(context, imageList);
        holder.imageLayout.setOnItemClickListerner(new NineGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(context, PreviewImageActivity.class);
                intent.putExtra(PreviewImageActivity.ARG_URLS, imageList);
                intent.putExtra(PreviewImageActivity.ARG_POSITION, position);
                context.startActivity(intent);
            }
        });
        holder.imageLayout.setAdapter(imageAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class GoodsCommentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nickname_and_time)
        TextView nicknameAndTime;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.image_layout)
        NineGridlayout imageLayout;

        GoodsCommentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
