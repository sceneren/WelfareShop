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
    private static final int TYPE_UNPAY = 0;//待付款
    private static final int TYPE_UNSEND = 1;//待发货
    private static final int TYPE_UNRECEIVER = 2;//待收货
    private static final int TYPE_UNCOMMENT = 3;//待评价
    private Context context;
    private List<OrderInfo> list;

    private LayoutInflater inflater;
    private OnClickOrderItemListener onClickOrderItemListener;

    public MyOrderChildAdapter(Context context, List<OrderInfo> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void setOnClickOrderItemListener(OnClickOrderItemListener onClickOrderItemListener) {
        this.onClickOrderItemListener = onClickOrderItemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_UNPAY) {
            return new OrderUnPayViewHolder(inflater.inflate(R.layout.fragment_my_order_unpay_item, parent, false));
        } else if (viewType == TYPE_UNSEND) {
            return new OrderUnReceiverViewHolder(inflater.inflate(R.layout.fragment_my_order_unsend_item, parent, false));
        } else if (viewType == TYPE_UNRECEIVER) {
            return new OrderUnCommentViewHolder(inflater.inflate(R.layout.fragment_my_order_unreceiver_item, parent, false));
        } else if (viewType == TYPE_UNCOMMENT) {
            return new OrderUnCommentViewHolder(inflater.inflate(R.layout.fragment_my_order_unreceiver_item, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        OrderInfo info = list.get(position);
        if (holder instanceof OrderUnPayViewHolder) {
            OrderUnPayViewHolder viewHolder = (OrderUnPayViewHolder) holder;
            viewHolder.goodsName.setText(info.getGoods_name());
        } else if (holder instanceof UnsendViewHolder) {
            UnsendViewHolder viewHolder = (UnsendViewHolder) holder;
            viewHolder.goodsName.setText(info.getGoods_name());
        } else if (holder instanceof OrderUnReceiverViewHolder) {
            OrderUnReceiverViewHolder viewHolder = (OrderUnReceiverViewHolder) holder;
            viewHolder.goodsName.setText(info.getGoods_name());
        } else {
            OrderUnCommentViewHolder viewHolder = (OrderUnCommentViewHolder) holder;
            viewHolder.goodsName.setText(info.getGoods_name());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickOrderItemListener != null) {
                    onClickOrderItemListener.onClickOrderItem(position);
                }
            }
        });
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

    static class UnsendViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.goods_name)
        TextView goodsName;

        UnsendViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnClickOrderItemListener {
        void onClickOrderItem(int position);
    }

}
