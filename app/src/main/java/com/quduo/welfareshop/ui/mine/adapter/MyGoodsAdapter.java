package com.quduo.welfareshop.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.welfareshop.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/3/1 10:55
 * Description:收藏的商品
 */

public class MyGoodsAdapter extends RecyclerView.Adapter<MyGoodsAdapter.MyGoodsViewHolder> {
    private Context context;
    private List<String> list;

    public MyGoodsAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public MyGoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyGoodsViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_my_goods_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyGoodsViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.buyNow.setVisibility(View.VISIBLE);
            holder.hasNo.setVisibility(View.GONE);
        } else {
            holder.buyNow.setVisibility(View.GONE);
            holder.hasNo.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyGoodsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.goods_price)
        TextView goodsPrice;
        @BindView(R.id.buy_now)
        TextView buyNow;
        @BindView(R.id.has_no)
        ImageView hasNo;

        MyGoodsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
