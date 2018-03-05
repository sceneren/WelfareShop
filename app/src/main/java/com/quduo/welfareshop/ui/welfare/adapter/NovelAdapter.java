package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
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

public class NovelAdapter extends BaseQuickAdapter<NovelModelInfo, BaseViewHolder> {
    private static final int TYPE_LIST = 0;
    private static final int TYPE_GRID = 1;
    private static final int TYPE_AD = 2;
    private Context context;
    private List<NovelModelInfo> list;

    private OnNovelItemClickListener onNovelItemClickListener;

    public NovelAdapter(Context context, List<NovelModelInfo> list) {
        super(list);
        this.context = context;
        this.list = list;
        setMultiTypeDelegate(new MultiTypeDelegate<NovelModelInfo>() {
            @Override
            protected int getItemType(NovelModelInfo entity) {
                //根据你的实体类来判断布局类型
                return entity.getType();
            }
        });
        getMultiTypeDelegate()
                .registerItemType(TYPE_LIST, R.layout.fragment_welfare_novel_item_listview)
                .registerItemType(TYPE_GRID, R.layout.fragment_welfare_novel_item_gridview)
                .registerItemType(TYPE_AD, R.layout.fragment_welfare_novel_item_ad);
    }

    public void setOnNovelItemClickListener(OnNovelItemClickListener onNovelItemClickListener) {
        this.onNovelItemClickListener = onNovelItemClickListener;
    }

    @Override
    protected void convert(final BaseViewHolder holder, NovelModelInfo info) {
        if (holder instanceof NovelListViewHolder) {
            NovelListViewHolder listViewHolder = (NovelListViewHolder) holder;
            NovelListAdapter listAdapter = new NovelListAdapter(context, info.getList());
            listViewHolder.listView.setAdapter(listAdapter);
            listViewHolder.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int childPosition, long id) {
                    if (onNovelItemClickListener != null) {
                        onNovelItemClickListener.onClickNovel(holder.getLayoutPosition(), childPosition);
                    }
                }
            });
        } else if (holder instanceof NovelGridViewHolder) {
            NovelGridViewHolder gridViewHolder = (NovelGridViewHolder) holder;
            NovelGridAdapter gridAdapter = new NovelGridAdapter(context, info.getList());
            gridViewHolder.gridView.setAdapter(gridAdapter);
            gridViewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int childPosition, long id) {
                    if (onNovelItemClickListener != null) {
                        onNovelItemClickListener.onClickNovel(holder.getLayoutPosition(), childPosition);
                    }
                }
            });
        } else if (holder instanceof NovelAdViewHolder) {
            NovelAdViewHolder adViewHolder = (NovelAdViewHolder) holder;
            adViewHolder.layoutAd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

//    @Override
//    public int getItemViewType(int position) {
//        if(list.get(position).getType()>2){
//            return super.getItemViewType(position);
//        }else{
//            return list.get(position).getType();
//        }
//    }

    static class NovelListViewHolder extends BaseViewHolder {
        @BindView(R.id.listView)
        CustomListView listView;

        NovelListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class NovelGridViewHolder extends BaseViewHolder {
        @BindView(R.id.gridView)
        CustomeGridView gridView;

        NovelGridViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class NovelAdViewHolder extends BaseViewHolder {
        @BindView(R.id.layout_ad)
        LinearLayout layoutAd;

        NovelAdViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnNovelItemClickListener {
        void onClickNovel(int position, int childPosition);
    }
}
