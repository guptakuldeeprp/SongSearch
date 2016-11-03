package com.example.kuldeepgupta.songsearch.async;

import android.content.Context;
import android.os.AsyncTask;

import com.example.kuldeepgupta.songsearch.service.SongSearchDownloadService;
import com.example.kuldeepgupta.songsearch.service.download.android.DownloadInfo;

import java.util.List;

/**
 * Created by kuldeep.gupta on 10/27/2016.
 */

public class SongSearchTask extends AsyncTask<String,Void,List<DownloadInfo>> {

    private Context mContext;
    private AsyncResponse<List<DownloadInfo>> response;

    public SongSearchTask(Context context, AsyncResponse<List<DownloadInfo>> response) {
        this.mContext = context;
        this.response = response;
    }


    @Override
    protected List<DownloadInfo> doInBackground(String... songSearchString) {
        return SongSearchDownloadService.searchForDownload(mContext, songSearchString[0]);
    }

    @Override
    protected void onPostExecute(List<DownloadInfo> downloadInfos) {
        response.processFinish(downloadInfos);
    }
}
