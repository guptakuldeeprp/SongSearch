package com.example.kuldeepgupta.songsearch.async;

import android.content.Context;
import android.os.AsyncTask;

import com.example.kuldeepgupta.songsearch.service.download.android.DownloadInfo;
import com.example.kuldeepgupta.songsearch.util.ProviderUtil;

import java.util.List;

/**
 * Created by kuldeep.gupta on 11/1/2016.
 */

public class LibraryReadTask extends AsyncTask<String, Void, List<DownloadInfo>> {

    private Context mContext;
    private AsyncResponse<List<DownloadInfo>> response;

    public LibraryReadTask(Context context, AsyncResponse<List<DownloadInfo>> response) {
        this.mContext = context;
        this.response = response;
    }

    @Override
    protected List<DownloadInfo> doInBackground(String... strings) {
        return ProviderUtil.getDownloadedSongs(mContext);
    }

    @Override
    protected void onPostExecute(List<DownloadInfo> downloadInfos) {
        response.processFinish(downloadInfos);
    }
}
