package com.quduo.welfareshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quduo.welfareshop.R;

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
    private List<String> list;
    private LayoutInflater inflater;

    public RechargeAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
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
        RechargeViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_recharge_item, parent, false);
            holder = new RechargeViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (RechargeViewHolder) convertView.getTag();
        }
        holder.price.setText(MessageFormat.format("{0}积分", list.get(position)));
        holder.sendPrice.setText(MessageFormat.format("赠{0}积分", list.get(position)));
        holder.money.setText(MessageFormat.format("￥{0}", list.get(position)));
        return convertView;
    }

    static class RechargeViewHolder {
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.send_price)
        TextView sendPrice;
        @BindView(R.id.money)
        TextView money;

        RechargeViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
