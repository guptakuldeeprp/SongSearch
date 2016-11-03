package com.example.kuldeepgupta.songsearch.adapter;

import com.example.kuldeepgupta.songsearch.domain.SongInfo;
import com.example.kuldeepgupta.songsearch.service.download.android.DownloadInfo;

/**
 * Created by kuldeep.gupta on 10/26/2016.
 */

public class SongListItem {

    private SongInfo songInfo;
    private DownloadInfo downloadInfo;

    public SongInfo getSongInfo() {
        return songInfo;
    }

    public void setSongInfo(SongInfo songInfo) {
        this.songInfo = songInfo;
    }

    public DownloadInfo getDownloadInfo() {
        return downloadInfo;
    }

    public void setDownloadInfo(DownloadInfo downloadInfo) {
        this.downloadInfo = downloadInfo;
    }
}
