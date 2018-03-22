package com.quduo.welfareshop.ui.shop.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.PreviewImageActivity;
import com.quduo.welfareshop.ui.friend.adapter.NineGridImageAdapter;
import com.quduo.welfareshop.ui.shop.entity.GoodsCommentInfo;
import com.w4lle.library.NineGridlayout;

import java.text.MessageFormat;
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
    private Activity activity;
    private List<GoodsCommentInfo> list;
    private LayoutInflater inflater;

    public GoodsDetailCommentAdapter(Activity activity, List<GoodsCommentInfo> list) {
        this.activity = activity;
        this.list = list;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
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

        GoodsCommentInfo info = list.get(position);
        final ArrayList<String> imageList = new ArrayList<>();
        for (String str : info.getImages()) {
            imageList.add(MyApplication.getInstance().getConfigInfo().getFile_domain() + str);
        }
        NineGridImageAdapter imageAdapter = new NineGridImageAdapter(activity, imageList);
        holder.imageLayout.setOnItemClickListerner(new NineGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(activity, PreviewImageActivity.class);
                intent.putExtra(PreviewImageActivity.ARG_URLS, imageList);
                intent.putExtra(PreviewImageActivity.ARG_POSITION, position);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
            }
        });
        holder.imageLayout.setAdapter(imageAdapter);
        holder.content.setText(info.getContent());
        holder.nicknameAndTime.setText(MessageFormat.format("{0}/{1}", info.getNick_name(), info.getCreate_time()));
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
