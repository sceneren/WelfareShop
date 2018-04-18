package com.quduo.welfareshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.bean.RechargeTypeInfo;

import java.text.MessageFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/3/23 16:49
 * Description:充值
 */

public class RechargeAdapter extends BaseAdapter {
    private Context context;
    private List<RechargeTypeInfo> list;
    private LayoutInflater inflater;

    private OnClickRechargeListener onClickRechargeListener;

    public RechargeAdapter(Context context, List<RechargeTypeInfo> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void setOnClickRechargeListener(OnClickRechargeListener onClickRechargeListener) {
        this.onClickRechargeListener = onClickRechargeListener;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        RechargeViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_recharge_item, parent, false);
            holder = new RechargeViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (RechargeViewHolder) convertView.getTag();
        }
        RechargeTypeInfo info = list.get(position);
        holder.price.setText(MessageFormat.format("{0}积分", info.getScore()));
        holder.sendPrice.setText(MessageFormat.format("赠{0}积分", info.getGift()));
        holder.sendPrice.setVisibility(info.getGift() == 0 ? View.GONE : View.VISIBLE);
        holder.money.setText(MessageFormat.format("￥{0}", info.getMoney()));
        holder.money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickRechargeListener != null) {
                    onClickRechargeListener.onClickRecharge(position);
                }
            }
        });
        holder.experience.setVisibility(info.getScore() < 10 ? View.VISIBLE : View.GONE);
        holder.czth.setVisibility(position == 1 ? View.VISIBLE : View.GONE);
        return convertView;
    }

    static class RechargeViewHolder {
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.send_price)
        TextView sendPrice;
        @BindView(R.id.money)
        TextView money;
        @BindView(R.id.experience)
        TextView experience;
        @BindView(R.id.czth)
        ImageView czth;

        RechargeViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnClickRechargeListener {
        void onClickRecharge(int position);
    }
}
