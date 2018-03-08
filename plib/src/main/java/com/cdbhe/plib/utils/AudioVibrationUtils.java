package com.cdbhe.plib.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;

/**
 * Created by Kevin on 2018/2/1.
 */

public class AudioVibrationUtils implements MediaPlayer.OnCompletionListener{
    private static MediaPlayer mediaPlayer;
    private Vibrator vibrator;

    public static AudioVibrationUtils init(Context context, int resId){
        return  new AudioVibrationUtils(context,resId);
    }

    private AudioVibrationUtils(Context context, int resId) {
        mediaPlayer = MediaPlayer.create(context,resId);
        mediaPlayer.setOnCompletionListener(this);
        vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    /**
     * 音频播放+震动
     */
    public void doPlay(){
        if(mediaPlayer.isPlaying()){//正在播放
            mediaPlayer.stop();
        }
        mediaPlayer.start();

        //震动
        long[] pattern = {100, 200, 0, 0};   // 停止 开启 停止 开启
        vibrator.vibrate(pattern, -1);
    }

    public static void playAudio(Context context,int resId,boolean isLoop){
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(context,resId);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        });
        mediaPlayer.setLooping(isLoop);
        mediaPlayer.start();
    }

    /**
     * 播放音频
     * @param isLoop
     */
    public void playAudio(boolean... isLoop){
        if(mediaPlayer.isPlaying()){//正在播放
            mediaPlayer.stop();
        }
        if(isLoop == null || isLoop.length == 0){
            mediaPlayer.setLooping(false);
        }else{
            mediaPlayer.setLooping(isLoop[0]);
        }
        mediaPlayer.start();
    }

    //震动
    public void playVibrate(){
        long[] pattern = {100, 200, 0, 0};   // 停止 开启 停止 开启
        vibrator.vibrate(pattern, -1);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.stop();
        mediaPlayer.release();
        vibrator.cancel();
    }
}
