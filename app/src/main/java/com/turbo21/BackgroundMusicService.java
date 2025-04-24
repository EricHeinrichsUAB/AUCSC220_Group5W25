package com.turbo21;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/*
Service for playing background music
Used https://www.youtube.com/watch?v=DI1CQVlpR0U for reference on how to play background music
 */

public class BackgroundMusicService extends android.app.Service {
    private static MediaPlayer mediaPlayer;
    public static boolean isRunning = false;

    @Override
    @Nullable
    /* Service not bindable. Can only be started and stopped with startService and stopService */
    public IBinder onBind(Intent intent){return null;}


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!isRunning) {
            Log.d("myLog", "Starting music");
            mediaPlayer = MediaPlayer.create(this, R.raw.background_jazz);
            /* Audio from https://www.youtube.com/watch?v=Sg4uMnX0QfI */
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            isRunning = true;
        } else {
            Log.d("myLog", "Music already running");
        }
        /* service restarts if killed*/
        return START_STICKY;
    }

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        isRunning = false;
        Log.d("myLog", "Music stopped");
    }
}
