package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.friend.entity.ChatMessageInfo;
import com.quduo.welfareshop.ui.friend.userdef.QqUtils;
import com.quduo.welfareshop.util.Time2StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Author:scene
 * Time:2018/2/2 11:47
 * Description:我的消息
 */
public class MessageAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ChatMessageInfo> list;
    private OnClickMessageItemListener onClickMessageItemListener;

    public MessageAdapter(Context context, List<ChatMessageInfo> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnClickMessageItemListener(OnClickMessageItemListener onClickMessageItemListener) {
        this.onClickMessageItemListener = onClickMessageItemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_friend_message_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ChatMessageInfo info = list.get(position);
        MessageViewHolder viewHolder = (MessageViewHolder) holder;
        String url = info.getOtherAvatar();
        if (!url.startsWith("http")) {
            url = MyApplication.getInstance().getConfigInfo().getFile_domain() + info.getOtherAvatar();
        }
        MultiTransformation multiTransformation = new MultiTransformation(
                new CenterCrop(), new RoundedCornersTransformation(SizeUtils.dp2px(5), 0)
        );
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .apply(RequestOptions.bitmapTransform(multiTransformation))
                .load(url)
                .into(viewHolder.avatar);
        viewHolder.nickname.setText(info.getOtherNickName());
        if (info.getMessageType() == 0) {
            QqUtils.spannableEmoticonFilter(viewHolder.message, info.getMessageContent());
        } else if (info.getMessageType() == 1) {
            viewHolder.message.setText("【图片】");
        } else {
            viewHolder.message.setText("【语音】");
        }
        viewHolder.time.setText(Time2StringUtils.millisDistanceCurrent(info.getTime()));
        viewHolder.contentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickMessageItemListener != null) {
                    onClickMessageItemListener.onClickContent(position);
                }
            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickMessageItemListener != null) {
                    onClickMessageItemListener.onClickDelete(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.nickname)
        TextView nickname;
        @BindView(R.id.message)
        TextView message;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.content_layout)
        LinearLayout contentLayout;
        @BindView(R.id.delete)
        TextView delete;

        MessageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnClickMessageItemListener {
        void onClickContent(int position);

        void onClickDelete(int position);
    }
}
