package com.quduo.welfareshop.ui.mine.adapter;

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
 * Time:2018/3/14 11:23
 * Description:订单详情推荐商品
 */

public class OrderDetailRecommendGoodsAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public OrderDetailRecommendGoodsAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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

        RecommendGoodsViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_shop_item, parent, false);
            holder = new RecommendGoodsViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (RecommendGoodsViewHolder) convertView.getTag();
        }
        String url = "http://pic19.nipic.com/20120214/3145425_134109747000_2.jpg";
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(url)
                .into(holder.image);
        return convertView;
    }

    static class RecommendGoodsViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.buy_number)
        TextView buyNumber;
        @BindView(R.id.btn_buy)
        TextView btnBuy;

        RecommendGoodsViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
