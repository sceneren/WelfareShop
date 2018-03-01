package com.quduo.welfareshop.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.ui.mine.entity.OrderInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/3/1 9:05
 * Description:订单子页面
 */

public class MyOrderChildAdapter extends RecyclerView.Adapter {
    private static final int TYPE_UNPAY = 0;
    private static final int TYPE_UNRECEIVER = 1;
    private static final int TYPE_UNCOMMENT = 2;
    private Context context;
    private List<OrderInfo> list;

    private LayoutInflater inflater;

    public MyOrderChildAdapter(Context context, List<OrderInfo> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_UNPAY) {
            return new OrderUnPayViewHolder(inflater.inflate(R.layout.fragment_my_order_unpay_item, parent, false));
        } else if (viewType == TYPE_UNRECEIVER) {
            return new OrderUnReceiverViewHolder(inflater.inflate(R.layout.fragment_my_order_unreceiver_item, parent, false));
        } else if (viewType == TYPE_UNCOMMENT) {
            return new OrderUnCommentViewHolder(inflater.inflate(R.layout.fragment_my_order_unreceiver_item, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OrderInfo info = list.get(position);
        if (holder instanceof OrderUnPayViewHolder) {
            OrderUnPayViewHolder viewHolder = (OrderUnPayViewHolder) holder;
            viewHolder.goodsName.setText(info.getGoods_name());
        } else if (holder instanceof OrderUnReceiverViewHolder) {
            OrderUnReceiverViewHolder viewHolder = (OrderUnReceiverViewHolder) holder;
            viewHolder.goodsName.setText(info.getGoods_name());
        } else {
            OrderUnCommentViewHolder viewHolder = (OrderUnCommentViewHolder) holder;
            viewHolder.goodsName.setText(info.getGoods_name());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    class OrderUnPayViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.goods_name)
        TextView goodsName;

        OrderUnPayViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class OrderUnReceiverViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.goods_name)
        TextView goodsName;

        OrderUnReceiverViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class OrderUnCommentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.goods_name)
        TextView goodsName;

        OrderUnCommentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
