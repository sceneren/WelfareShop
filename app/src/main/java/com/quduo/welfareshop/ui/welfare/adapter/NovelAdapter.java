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
import com.quduo.welfareshop.widgets.CustomGridView;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/24 11:53
 * Description:小爽文
 */

public class NovelAdapter extends BaseQuickAdapter<NovelModelInfo, BaseViewHolder> {
    private static final int TYPE_LIST = 1;
    private static final int TYPE_GRID = 2;
    private static final int TYPE_AD = 0;
    private Context context;

    private OnNovelItemClickListener onNovelItemClickListener;

    public NovelAdapter(Context context, List<NovelModelInfo> list) {
        super(list);
        this.context = context;
        setMultiTypeDelegate(new MultiTypeDelegate<NovelModelInfo>() {
            @Override
            protected int getItemType(NovelModelInfo entity) {
                //根据你的实体类来判断布局类型
                return entity.getType();
            }
        });
        getMultiTypeDelegate()
                .registerItemType(TYPE_AD, R.layout.fragment_welfare_novel_item_ad)
                .registerItemType(TYPE_LIST, R.layout.fragment_welfare_novel_item_listview)
                .registerItemType(TYPE_GRID, R.layout.fragment_welfare_novel_item_gridview);
    }

    public void setOnNovelItemClickListener(OnNovelItemClickListener onNovelItemClickListener) {
        this.onNovelItemClickListener = onNovelItemClickListener;
    }

    @Override
    protected void convert(final BaseViewHolder holder, NovelModelInfo info) {
        if (holder.getItemViewType() == TYPE_LIST) {
            NovelListAdapter listAdapter = new NovelListAdapter(context, info.getNovel());
            CustomListView listView = holder.getView(R.id.listView);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int childPosition, long id) {
                    if (onNovelItemClickListener != null) {
                        onNovelItemClickListener.onClickNovel(holder.getLayoutPosition(), childPosition);
                    }
                }
            });
        } else if (holder.getItemViewType() == TYPE_GRID) {
            NovelGridAdapter gridAdapter = new NovelGridAdapter(context, info.getNovel());
            CustomGridView gridView = holder.getView(R.id.gridView);
            gridView.setAdapter(gridAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int childPosition, long id) {
                    if (onNovelItemClickListener != null) {
                        onNovelItemClickListener.onClickNovel(holder.getLayoutPosition(), childPosition);
                    }
                }
            });
        } else if (holder.getItemViewType() == TYPE_AD) {
            LinearLayout layoutAd = holder.getView(R.id.layout_ad);
            layoutAd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public interface OnNovelItemClickListener {
        void onClickNovel(int position, int childPosition);
    }
}
