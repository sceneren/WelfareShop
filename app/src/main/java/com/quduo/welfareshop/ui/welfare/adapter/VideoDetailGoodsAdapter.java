package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/27 13:34
 * Description:视频详情--商品推荐
 */

public class VideoDetailGoodsAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public VideoDetailGoodsAdapter(Context context, List<String> list) {
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

        VideoDetailGoodsViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_shop_item, parent, false);
            holder = new VideoDetailGoodsViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (VideoDetailGoodsViewHolder) convertView.getTag();
        }
        String url = "http://pic19.nipic.com/20120214/3145425_134109747000_2.jpg";
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(url)
                .into(holder.image);
        return convertView;
    }

    static class VideoDetailGoodsViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.buy_number)
        TextView buyNumber;
        @BindView(R.id.btn_buy)
        TextView btnBuy;

        VideoDetailGoodsViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
