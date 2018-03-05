package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/26 17:11
 * Description:小说详情
 */

public class NovelDetailAdapter extends BaseQuickAdapter<String, NovelDetailAdapter.NovelDetailViewHolder> {
    private Context context;
    private List<String> list;

    public NovelDetailAdapter(Context context, List<String> list) {
        super(R.layout.fragment_welfare_novel_detail_item, list);
        this.context = context;
        this.list = list;
    }

    @Override
    protected void convert(NovelDetailViewHolder helper, String item) {

    }

    class NovelDetailViewHolder extends BaseViewHolder {
        @BindView(R.id.title)
        TextView title;

        NovelDetailViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
