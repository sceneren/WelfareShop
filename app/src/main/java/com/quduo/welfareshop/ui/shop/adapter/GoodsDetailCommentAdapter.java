package com.quduo.welfareshop.ui.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
 * Time:2018/2/27 16:20
 * Description:商品详情--评论
 */

public class GoodsDetailCommentAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public GoodsDetailCommentAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GoodsDetailCommentViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_goods_detail_comment_item, parent, false);
            holder = new GoodsDetailCommentViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GoodsDetailCommentViewHolder) convertView.getTag();
        }
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
        return convertView;
    }

    static class GoodsDetailCommentViewHolder {
        @BindView(R.id.nickname_and_time)
        TextView nicknameAndTime;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.image_layout)
        NineGridlayout imageLayout;

        GoodsDetailCommentViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
