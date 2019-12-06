package com.example.yujian.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;



public class MusicService extends Service{
    private final IBinder iBinder = new MusicBinder();

    private MediaPlayer mediaPlayer = new MediaPlayer();;


    public class MusicBinder extends Binder{
        MusicService getService(){
            return MusicService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    public void prepare(String path){
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,"未找到该音乐文件",Toast.LENGTH_SHORT).show();
        }
    }

    public void start(){
        mediaPlayer.start();
    }

    public void stop(){
        mediaPlayer.stop();
    }

    public void pause(){
        mediaPlayer.pause();
    }

    public int getDuration(){
        return mediaPlayer.getDuration();
    } //获取载入文件的时长

    public int getCurrentPosition(){
        return mediaPlayer.getCurrentPosition();
    }//当前播放器的播放位置

    public void seekTo(int msec){
        mediaPlayer.seekTo(msec);
    }  //从指定的位置开始播放视频

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener){
        mediaPlayer.setOnCompletionListener(onCompletionListener);
    }

}
