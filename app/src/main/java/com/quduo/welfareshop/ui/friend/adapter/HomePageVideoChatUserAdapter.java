package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.friend.entity.VideoChatUserInfo;
import com.quduo.welfareshop.widgets.RatioImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageVideoChatUserAdapter extends BaseAdapter {
    private Context mContext;
    private List<VideoChatUserInfo> list;
    private LayoutInflater inflater;

    public HomePageVideoChatUserAdapter(Context mContext, List<VideoChatUserInfo> list) {
        this.mContext = mContext;
        this.list = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
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

        VideoChatAvatarViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_friend_others_home_page_video_chat_avatar_item, parent, false);
            holder = new VideoChatAvatarViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (VideoChatAvatarViewHolder) convertView.getTag();
        }
        GlideApp.with(mContext)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + list.get(position).getAvatar())
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .into(holder.avatar);
        return convertView;
    }

    static class VideoChatAvatarViewHolder {
        @BindView(R.id.avatar)
        RatioImageView avatar;

        VideoChatAvatarViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
