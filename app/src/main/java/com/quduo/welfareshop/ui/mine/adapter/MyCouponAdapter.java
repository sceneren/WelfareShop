package com.quduo.welfareshop.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quduo.welfareshop.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by scene on 2018/3/1.
 */

public class MyCouponAdapter extends RecyclerView.Adapter<MyCouponAdapter.MyCouponViewHolder> {
    private Context context;
    private List<String> list;

    public MyCouponAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public MyCouponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyCouponViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_my_coupon_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyCouponViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyCouponViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.to_use)
        TextView toUse;

        MyCouponViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
