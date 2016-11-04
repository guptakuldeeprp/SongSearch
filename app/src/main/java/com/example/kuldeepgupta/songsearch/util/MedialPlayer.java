package com.example.kuldeepgupta.songsearch.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.File;
import java.io.IOException;

/**
 * Created by kuldeep.gupta on 11/4/2016.
 */

public class MedialPlayer {

    private static final MedialPlayer INSTANCE = new MedialPlayer();
    private MediaPlayer mediaPlayer;

    private MedialPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public static MedialPlayer getInstance() {
        return INSTANCE;
    }


    public void play(Context context, String fileName) throws IOException {
        pause();

        mediaPlayer.setDataSource(context, Uri.fromFile(new File(fileName)));
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    public void pause() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
    }
}
