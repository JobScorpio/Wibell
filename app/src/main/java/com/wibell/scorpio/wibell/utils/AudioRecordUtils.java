package com.wibell.scorpio.wibell.utils;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Scorpio on 2016/9/19.
 */
public class AudioRecordUtils {
    public static final String LOG_TAG = "AudioRecord";
    public static String FileName = null;

    //语音操作对象
    private static MediaPlayer mPlayer = null;
    private static MediaRecorder mRecorder = null;

    public static void startRecord() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(FileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        mRecorder.start();
    }

    public static void stopRecord(){
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }
}
