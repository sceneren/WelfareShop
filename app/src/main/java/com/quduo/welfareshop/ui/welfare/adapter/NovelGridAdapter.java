package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.NovelInfo;
import com.quduo.welfareshop.widgets.RatioImageView;

import java.text.MessageFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/24 11:53
 * Description:小爽文
 */

public class NovelGridAdapter extends BaseAdapter {
    private Context context;
    private List<NovelInfo> list;
    private LayoutInflater inflater;

    public NovelGridAdapter(Context context, List<NovelInfo> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
        NovelIGridtemViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_welfare_novel_item_gridview_item, parent, false);
            holder = new NovelIGridtemViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (NovelIGridtemViewHolder) convertView.getTag();
        }
        NovelInfo info = list.get(position);
        holder.title.setText(info.getTitle());
        String url;
        if (StringUtils.isTrimEmpty(list.get(position).getThumb_shu())) {
            url = list.get(position).getThumb();
        } else {
            url = list.get(position).getThumb_shu();
        }
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + url)
                .into(holder.image);
        holder.readNumber.setText(MessageFormat.format("阅读：{0}", info.getView_times()));
        return convertView;
    }

    static class NovelIGridtemViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.image)
        RatioImageView image;
        @BindView(R.id.read_number)
        TextView readNumber;

        NovelIGridtemViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
