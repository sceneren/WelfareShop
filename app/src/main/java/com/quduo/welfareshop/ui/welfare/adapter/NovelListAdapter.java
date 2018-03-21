package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

public class NovelListAdapter extends BaseAdapter {
    private Context context;
    private List<NovelInfo> list;
    private LayoutInflater inflater;

    public NovelListAdapter(Context context, List<NovelInfo> list) {
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
        NovelListItemViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_welfare_novel_item_listview_item, parent, false);
            holder = new NovelListItemViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (NovelListItemViewHolder) convertView.getTag();
        }
        NovelInfo info = list.get(position);
        holder.title.setText(info.getTitle());
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + list.get(position).getThumb())
                .into(holder.image);
        String str = info.getDescription();
        str = str.replace("\n", "");
        str = str.replace("\r", "");
        holder.des.setText(str);
        holder.readNumber.setText(MessageFormat.format("阅读：{0}", info.getView_times()));
        return convertView;
    }

    static class NovelListItemViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.baseimage)
        RatioImageView baseimage;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.des)
        TextView des;
        @BindView(R.id.read_number)
        TextView readNumber;

        NovelListItemViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
