package com.quduo.welfareshop.util;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.quduo.welfareshop.MyApplication;

import java.io.IOException;

public class PlaySoundUtil {
    private static volatile PlaySoundUtil instance = null;

    private PlaySoundUtil() {
    }

    public static PlaySoundUtil getInstance() {
        if (instance == null) {
            synchronized (PlaySoundUtil.class) {
                if (instance == null) {
                    instance = new PlaySoundUtil();
                }
            }
        }
        return instance;
    }

    private MediaPlayer mediaPlayer;

    public void playSoundByMedia(int rawId) {
        try {
            if (mediaPlayer == null) {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if (mediaPlayer != null) {
                            if (mediaPlayer.isPlaying()) {
                                mediaPlayer.stop();
                            }
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                    }
                });

            }
            try {
                AssetFileDescriptor file = MyApplication.getInstance().getResources().openRawResourceFd(rawId);
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(0.50f, 0.50f);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
