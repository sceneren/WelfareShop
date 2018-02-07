package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.friend.entity.ChatMessageInfo;
import com.quduo.welfareshop.ui.friend.userdef.QqUtils;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;

import org.joda.time.DateTime;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/6 11:35
 * Description:聊天记录
 */

public class ChatAdapter extends RecyclerView.Adapter {
    private final int TYPE_TEXT = 0;
    private final int TYPE_IMAGE = 1;
    private final int TYPE_AUDIO = 2;

    private Context context;
    private List<ChatMessageInfo> list;

    public ChatAdapter(Context context, List<ChatMessageInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TEXT:
                return new TextViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_chat_send_text_item, parent, false));
            case TYPE_IMAGE:
                return new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_chat_send_image_item, parent, false));
            case TYPE_AUDIO:
            default:
                return new AudioViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_chat_send_audio_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChatMessageInfo chatMessageInfo = list.get(position);
        if (getItemViewType(position) == TYPE_TEXT) {
            if (holder != null && holder instanceof TextViewHolder) {
                TextViewHolder textViewHolder = (TextViewHolder) holder;
                QqUtils.spannableEmoticonFilter(textViewHolder.content, chatMessageInfo.getMessageContent());
                String url = "http://e.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg";
                GlideApp.with(context)
                        .asBitmap()
                        .centerCrop()
                        .load(url)
                        .into(textViewHolder.mineAvatar);
                if (position == 0) {
                    textViewHolder.time.setVisibility(View.VISIBLE);
                } else {
                    //2条消息间隔5分钟才显示时间戳
                    if (Math.abs(list.get(position - 1).getTime() - chatMessageInfo.getTime()) > 300000) {
                        textViewHolder.time.setVisibility(View.VISIBLE);
                    } else {
                        textViewHolder.time.setVisibility(View.GONE);
                    }
                }
                DateTime dateTime = new DateTime(chatMessageInfo.getTime());
                textViewHolder.time.setText(dateTime.toString("yyyy-MM-dd HH:mm"));
            }
        } else if (getItemViewType(position) == TYPE_IMAGE) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            String url = "http://e.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg";
            GlideApp.with(context)
                    .asBitmap()
                    .centerCrop()
                    .load(url)
                    .into(imageViewHolder.mineAvatar);
            GlideApp.with(context)
                    .asBitmap()
                    .centerCrop()
                    .load(chatMessageInfo.getMessageContent())
                    .into(imageViewHolder.image);
            if (position == 0) {
                imageViewHolder.time.setVisibility(View.VISIBLE);
            } else {
                //2条消息间隔5分钟才显示时间戳
                if (Math.abs(list.get(position - 1).getTime() - chatMessageInfo.getTime()) > 300000) {
                    imageViewHolder.time.setVisibility(View.VISIBLE);
                } else {
                    imageViewHolder.time.setVisibility(View.GONE);
                }
            }
            DateTime dateTime = new DateTime(chatMessageInfo.getTime());
            imageViewHolder.time.setText(dateTime.toString("yyyy-MM-dd HH:mm"));
        } else {
            AudioViewHolder audioViewHolder = (AudioViewHolder) holder;
            String url = "http://e.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg";
            GlideApp.with(context)
                    .asBitmap()
                    .centerCrop()
                    .load(url)
                    .into(audioViewHolder.mineAvatar);
            if (position == 0) {
                audioViewHolder.time.setVisibility(View.VISIBLE);
            } else {
                //2条消息间隔5分钟才显示时间戳
                if (Math.abs(list.get(position - 1).getTime() - chatMessageInfo.getTime()) > 300000) {
                    audioViewHolder.time.setVisibility(View.VISIBLE);
                } else {
                    audioViewHolder.time.setVisibility(View.GONE);
                }
            }
            DateTime dateTime = new DateTime(chatMessageInfo.getTime());
            audioViewHolder.time.setText(dateTime.toString("yyyy-MM-dd HH:mm", Locale.CHINESE));
            audioViewHolder.audioTime.setText(String.format("%d″", chatMessageInfo.getAudioTime()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getMessageType();
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.mine_avatar)
        SelectableRoundedImageView mineAvatar;
        @BindView(R.id.content)
        TextView content;

        TextViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.mine_avatar)
        SelectableRoundedImageView mineAvatar;
        @BindView(R.id.image)
        SelectableRoundedImageView image;

        ImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class AudioViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.mine_avatar)
        SelectableRoundedImageView mineAvatar;
        @BindView(R.id.audio_time)
        TextView audioTime;
        @BindView(R.id.audio_image)
        ImageView audioImage;

        AudioViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
