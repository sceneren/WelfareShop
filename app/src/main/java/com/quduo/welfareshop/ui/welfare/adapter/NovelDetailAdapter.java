package com.quduo.welfareshop.ui.welfare.adapter;

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
 * Author:scene
 * Time:2018/2/26 17:11
 * Description:小说详情
 */

public class NovelDetailAdapter extends RecyclerView.Adapter<NovelDetailAdapter.NovelDetailViewHolder> {
    private Context context;
    private List<String> list;

    public NovelDetailAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public NovelDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NovelDetailViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_welfare_novel_detail_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NovelDetailViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class NovelDetailViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;

        NovelDetailViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
