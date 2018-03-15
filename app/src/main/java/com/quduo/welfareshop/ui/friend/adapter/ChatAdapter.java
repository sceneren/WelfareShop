package com.quduo.welfareshop.ui.friend.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.PreviewImageActivity;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.friend.audio.MediaManager;
import com.quduo.welfareshop.ui.friend.entity.ChatMessageInfo;
import com.quduo.welfareshop.ui.friend.userdef.QqUtils;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/6 11:35
 * Description:聊天记录
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TYPE_TEXT = 0;
    private final int TYPE_IMAGE = 1;
    private final int TYPE_AUDIO = 2;

    private Context context;
    private List<ChatMessageInfo> list;

    private int voicePlayPosition = -1;

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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ChatMessageInfo chatMessageInfo = list.get(position);
        if (getItemViewType(position) == TYPE_TEXT) {
            if (holder != null && holder instanceof TextViewHolder) {
                TextViewHolder textViewHolder = (TextViewHolder) holder;
                QqUtils.spannableEmoticonFilter(textViewHolder.content, chatMessageInfo.getMessageContent());
                GlideApp.with(context)
                        .asBitmap()
                        .centerCrop()
                        .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + MyApplication.getInstance().getUserInfo().getAvatar())
                        .into(textViewHolder.mineAvatar);
                if (position == 0) {
                    textViewHolder.time.setVisibility(View.VISIBLE);
                } else {
                    //2条消息间隔5分钟才显示时间戳
                    if (Math.abs(list.get(position - 1).getTime() - chatMessageInfo.getTime()) > 120000) {
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
            GlideApp.with(context)
                    .asBitmap()
                    .centerCrop()
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + MyApplication.getInstance().getUserInfo().getAvatar())
                    .into(imageViewHolder.mineAvatar);
            GlideApp.with(context)
                    .asBitmap()
                    .centerCrop()
                    .load(chatMessageInfo.getMessageContent())
                    .into(imageViewHolder.image);
            imageViewHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PreviewImageActivity.class);
                    ArrayList<String> imageUrls = new ArrayList<>();
                    imageUrls.add(chatMessageInfo.getMessageContent());
                    intent.putExtra(PreviewImageActivity.ARG_URLS, imageUrls);
                    context.startActivity(intent);
                }
            });
            if (position == 0) {
                imageViewHolder.time.setVisibility(View.VISIBLE);
            } else {
                //2条消息间隔5分钟才显示时间戳
                if (Math.abs(list.get(position - 1).getTime() - chatMessageInfo.getTime()) > 120000) {
                    imageViewHolder.time.setVisibility(View.VISIBLE);
                } else {
                    imageViewHolder.time.setVisibility(View.GONE);
                }
            }
            DateTime dateTime = new DateTime(chatMessageInfo.getTime());
            imageViewHolder.time.setText(dateTime.toString("yyyy-MM-dd HH:mm"));
        } else {
            final AudioViewHolder audioViewHolder = (AudioViewHolder) holder;
            GlideApp.with(context)
                    .asBitmap()
                    .centerCrop()
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + MyApplication.getInstance().getUserInfo().getAvatar())
                    .into(audioViewHolder.mineAvatar);
            if (position == 0) {
                audioViewHolder.time.setVisibility(View.VISIBLE);
            } else {
                //2条消息间隔5分钟才显示时间戳
                if (Math.abs(list.get(position - 1).getTime() - chatMessageInfo.getTime()) > 120000) {
                    audioViewHolder.time.setVisibility(View.VISIBLE);
                } else {
                    audioViewHolder.time.setVisibility(View.GONE);
                }
            }
            DateTime dateTime = new DateTime(chatMessageInfo.getTime());
            audioViewHolder.time.setText(dateTime.toString("yyyy-MM-dd HH:mm", Locale.CHINESE));
            audioViewHolder.audioTime.setText(String.format("%s″", chatMessageInfo.getAudioTime()));

            audioViewHolder.audioImage.setId(position);
            if (position == voicePlayPosition) {
                audioViewHolder.audioImage.setBackgroundResource(R.drawable.voice_play_send);
                audioViewHolder.audioImage.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AnimationDrawable animationDrawable = (AnimationDrawable) audioViewHolder.audioImage.getBackground();
                        animationDrawable.start();
                    }
                }, 50);
            } else {
                audioViewHolder.audioImage.setBackgroundResource(R.drawable.ic_audio_item_voice_3);
            }

            audioViewHolder.audioLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //如果点击的是正在播放的语音就停止
                    if (voicePlayPosition == audioViewHolder.audioImage.getId()) {
                        audioViewHolder.audioImage.setBackgroundResource(R.drawable.ic_audio_item_voice_3);
                        stopPlayVoice();
                    } else {
                        audioViewHolder.audioImage.setBackgroundResource(R.drawable.ic_audio_item_voice_3);
                        stopPlayVoice();
                        voicePlayPosition = audioViewHolder.audioImage.getId();
                        audioViewHolder.audioImage.setBackgroundResource(R.drawable.voice_play_send);
                        audioViewHolder.audioImage.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                AnimationDrawable drawable = (AnimationDrawable) audioViewHolder.audioImage.getBackground();
                                drawable.start();
                            }
                        }, 50);

                        String voicePath = chatMessageInfo.getMessageContent() == null ? "" : chatMessageInfo.getMessageContent();
                        MediaManager.playSound(voicePath, new MediaPlayer.OnCompletionListener() {

                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                voicePlayPosition = -1;
                                audioViewHolder.audioImage.setBackgroundResource(R.drawable.ic_audio_item_voice_3);
                            }
                        });
                    }


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
        return list.get(position).getMessageType();
    }

    public void stopPlayVoice() {
        if (voicePlayPosition != -1) {
            View voicePlay = ((Activity) context).findViewById(voicePlayPosition);
            if (voicePlay != null) {
                voicePlay.setBackgroundResource(R.drawable.ic_audio_item_voice_3);
            }
            MediaManager.pause();
            voicePlayPosition = -1;
        }
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
        @BindView(R.id.audio_layout)
        LinearLayout audioLayout;

        AudioViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
