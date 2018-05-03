package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.friend.entity.DynamicCommentInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//个人主页的动态评论
public class HomePageDynamicCommentAdapter extends BaseAdapter {
    private Context mContext;
    private List<DynamicCommentInfo> list;
    private LayoutInflater inflater;
    private boolean isShowAll;

    public HomePageDynamicCommentAdapter(Context mContext, List<DynamicCommentInfo> list, boolean isShowAll) {
        this.mContext = mContext;
        this.list = list;
        this.isShowAll = isShowAll;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        } else {
            if (list.size() > 2) {
                if (isShowAll) {
                    return list.size();
                } else {
                    return 2;
                }
            } else {
                return list.size();
            }
        }
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
        HomePageDynamicCommentViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_friend_others_home_page_dynamic_comment_item, parent, false);
            holder = new HomePageDynamicCommentViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (HomePageDynamicCommentViewHolder) convertView.getTag();
        }
        GlideApp.with(mContext)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + list.get(position).getAvatar())
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .into(holder.avatar);
        holder.nickname.setText(list.get(position).getName());
        holder.content.setText(list.get(position).getContent());
        return convertView;
    }

    static class HomePageDynamicCommentViewHolder {
        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.nickname)
        TextView nickname;
        @BindView(R.id.content)
        TextView content;

        HomePageDynamicCommentViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
