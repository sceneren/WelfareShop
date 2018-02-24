package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.ui.welfare.entity.NovelModelInfo;
import com.quduo.welfareshop.widgets.CustomListView;
import com.quduo.welfareshop.widgets.CustomeGridView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/24 11:53
 * Description:小爽文
 */

public class NovelAdapter extends RecyclerView.Adapter {
    private static final int TYPE_LIST = 0;
    private static final int TYPE_GRID = 1;
    private Context context;
    private List<NovelModelInfo> list;

    public NovelAdapter(Context context, List<NovelModelInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == TYPE_LIST) {
            return new NovelListViewHolder(inflater.inflate(R.layout.fragment_welfare_novel_item_listview, parent, false));
        } else if (viewType == TYPE_GRID) {
            return new NovelGridViewHolder(inflater.inflate(R.layout.fragment_welfare_novel_item_gridview, parent, false));
        } else {
            return new NovelAdViewHolder(inflater.inflate(R.layout.fragment_welfare_novel_item_ad, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NovelModelInfo info = list.get(position);
        if (holder instanceof NovelListViewHolder) {
            NovelListViewHolder listViewHolder = (NovelListViewHolder) holder;
            NovelListAdapter listAdapter = new NovelListAdapter(context, info.getList());
            listViewHolder.listView.setAdapter(listAdapter);
        } else if (holder instanceof NovelGridViewHolder) {
            NovelGridViewHolder gridViewHolder = (NovelGridViewHolder) holder;
            NovelGridAdapter gridAdapter = new NovelGridAdapter(context, info.getList());
            gridViewHolder.gridView.setAdapter(gridAdapter);
        } else {
            NovelAdViewHolder adViewHolder = (NovelAdViewHolder) holder;
            adViewHolder.layoutAd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
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

    static class NovelListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.listView)
        CustomListView listView;

        NovelListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class NovelGridViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.gridView)
        CustomeGridView gridView;

        NovelGridViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class NovelAdViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_ad)
        LinearLayout layoutAd;

        NovelAdViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
