package com.turbo21;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/*
Used https://www.youtube.com/watch?v=DI1CQVlpR0U for reference on how to play background music
 */

public class BackgroundMusicService extends android.app.Service {
    MediaPlayer mediaPlayer;

    @Override
    @Nullable
    public IBinder onBind(Intent intent){return null;}

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("myLog", "Starting playing");
        mediaPlayer = MediaPlayer.create(this, R.raw.spinning_monkeys);
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
