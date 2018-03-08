package com.quduo.welfareshop.ui.red.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.R;

import java.util.List;

public class RedUnparkAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RedUnparkAdapter(List<String> list) {
        super(R.layout.fragment_my_red_unpark_item, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
