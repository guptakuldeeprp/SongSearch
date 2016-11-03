package com.example.kuldeepgupta.songsearch.async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;


import com.example.kuldeepgupta.songsearch.service.SongSearchDownloadService;
import com.example.kuldeepgupta.songsearch.service.download.android.DownloadInfo;
import com.example.kuldeepgupta.songsearch.util.CommonUtil;
import com.example.kuldeepgupta.songsearch.util.ProviderUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by kuldeep.gupta on 10/26/2016.
 */

public class SongDownloadTask extends AsyncTask<Void, Integer, Boolean> {

    private static final String TAG = SongDownloadTask.class.getName();
    private DownloadInfo downloadInfo;
    private Context context;
    private boolean isFirst = true;

    public SongDownloadTask(Context context, DownloadInfo downloadInfo) {
        this.context = context;
        this.downloadInfo = downloadInfo;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        BufferedInputStream in = null;
        boolean success = false;
        try {
            in = new BufferedInputStream(SongSearchDownloadService.download(context, downloadInfo));
            File songFile = new File(CommonUtil.getSongDownloadDir(context), downloadInfo.getSongInfo().getFullTitle().replaceAll("\\s+","").replaceAll("[\\-\\(\\)\"]","")+".mp3");
            Log.i(TAG,"Saving song at location " + songFile);
            FileOutputStream outputStream = new FileOutputStream(songFile);
            byte data[] = new byte[1024];
            int count;
            int sumCount = 0;

            while ((count = in.read(data, 0, 1024)) != -1) {
                outputStream.write(data, 0, count);

                sumCount += count;
                publishProgress(sumCount);
            }
            Log.i(TAG,"song saved " + songFile);
            downloadInfo.setFilename(songFile.getPath());
            //ProviderUtil.saveDownloadInfo(context, downloadInfo);
            ProviderUtil.saveDownloadInfoIfAbsent(context, downloadInfo);
            success = true;

        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
        }
        return success;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result)
            downloadInfo.setDownloadState(DownloadInfo.DownloadState.ERROR);
        else
            downloadInfo.setDownloadState(DownloadInfo.DownloadState.COMPLETE);

        // change the button from 'download' to 'listen'

    }

    @Override
    protected void onPreExecute() {
        downloadInfo.setDownloadState(DownloadInfo.DownloadState.DOWNLOADING);
        Log.i(TAG,"Setting max progress: " + downloadInfo.getSize());
        downloadInfo.getProgressBar().setMax(downloadInfo.getSize());
    }


    @Override
    protected void onProgressUpdate(Integer... values) {

        downloadInfo.setProgress(values[0]);
        ProgressBar bar = downloadInfo.getProgressBar();

        if (bar != null) {
            if(isFirst)
            {
                bar.setMax(downloadInfo.getSize());
                isFirst = false;
            }
            //Log.i(TAG,"Setting update progress: " + downloadInfo.getProgress());
            bar.setProgress(downloadInfo.getProgress());
            bar.invalidate();
        }
    }

}

