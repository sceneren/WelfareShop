package com.quduo.welfareshop.ui.red.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.R;

import java.util.List;

public class RedHistoryAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RedHistoryAdapter(List<String> list) {
        super(R.layout.fragment_my_red_history_item, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.time, "2018-12-28");
        helper.setText(R.id.money, "100.11元");
    }
}
