package com.quduo.welfareshop.ui.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.shop.entity.GoodsInfo;

import java.text.MessageFormat;
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
    private List<GoodsInfo> list;
    private LayoutInflater inflater;

    public OrderDetailRecommendGoodsAdapter(Context context, List<GoodsInfo> list) {
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
        GoodsInfo info = list.get(position);
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + info.getThumb())
                .placeholder(R.drawable.ic_default_shop)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
        holder.name.setText(info.getName());
        holder.price.setText(MessageFormat.format("￥{0}", info.getPrice()));
        holder.buyNumber.setText(MessageFormat.format("{0}人购买", info.getSales()));
        Number num = Float.parseFloat(info.getPrice()) * 100;
        int giveNum = num.intValue() / 100;
        holder.zeng.setText(MessageFormat.format("{0}积分{1}钻石", giveNum, giveNum));
        return convertView;
    }

    static class RecommendGoodsViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.buy_number)
        TextView buyNumber;
        @BindView(R.id.btn_buy)
        TextView btnBuy;
        @BindView(R.id.zeng)
        TextView zeng;

        RecommendGoodsViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
