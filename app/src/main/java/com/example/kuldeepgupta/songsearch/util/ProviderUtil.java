package com.example.kuldeepgupta.songsearch.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.kuldeepgupta.songsearch.domain.SongInfo;
import com.example.kuldeepgupta.songsearch.provider.downloadinfo.DownloadinfoColumns;
import com.example.kuldeepgupta.songsearch.provider.downloadinfo.DownloadinfoContentValues;
import com.example.kuldeepgupta.songsearch.provider.downloadinfo.DownloadinfoCursor;
import com.example.kuldeepgupta.songsearch.provider.downloadinfo.DownloadinfoSelection;
import com.example.kuldeepgupta.songsearch.provider.songinfo.SonginfoColumns;
import com.example.kuldeepgupta.songsearch.provider.songinfo.SonginfoContentValues;
import com.example.kuldeepgupta.songsearch.provider.songinfo.SonginfoCursor;
import com.example.kuldeepgupta.songsearch.provider.songinfo.SonginfoSelection;
import com.example.kuldeepgupta.songsearch.service.download.android.DownloadInfo;


import java.util.List;
import java.util.ArrayList;


/**
 * Created by kuldeep.gupta on 11/1/2016.
 */

public class ProviderUtil {

    private static final String TAG = ProviderUtil.class.getName();

    public static long saveSongInfo(Context context, SongInfo songInfo) {
        if (songInfo == null) {
            Log.w(TAG, "Song info to save is null. Returning.");
            return -1;
        }
        SonginfoContentValues songVal = new SonginfoContentValues();
        songVal.putId(songInfo.getId());
        songVal.putTitle(songInfo.getTitle());
        songVal.putArtist(songInfo.getArtist());
        songVal.putQuality(songInfo.getQuality());
        songVal.putLength(songInfo.getLength());
        Uri result = context.getContentResolver().insert(SonginfoColumns.CONTENT_URI, songVal.values());
        return Long.parseLong(result.getLastPathSegment());

    }

    public static long saveDownloadInfo(Context context, DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            Log.w(TAG, "Download info to save is null. Returning.");
            return -1;
        }
        long songInfoId = saveSongInfo(context, downloadInfo.getSongInfo());
        Log.i(TAG, "songInfoId of saved song: " + songInfoId);

        DownloadinfoContentValues downloadVal = new DownloadinfoContentValues();
        if (songInfoId == -1) {
            Log.w(TAG, "Song info save failed. Returning.");
            return -1;
        }
        downloadVal.putSonginfoId(songInfoId);
        downloadVal.putDownloadLink(downloadInfo.getDownloadLink());
        downloadVal.putFileName(downloadInfo.getFilename());
        downloadVal.putSize(downloadInfo.getSize());
        Uri result = context.getContentResolver().insert(DownloadinfoColumns.CONTENT_URI, downloadVal.values());
        return Long.parseLong(result.getLastPathSegment());
    }


    public static long saveDownloadInfoIfAbsent(Context context, DownloadInfo downloadInfo) {
        // need a stronger check for knowing whether already saved
        if (downloadInfo == null) {
            Log.w(TAG, "Download info to save is null. Returning.");
            return -1;
        }
        DownloadinfoCursor downloadInfoCursor = new DownloadinfoSelection().fileName(downloadInfo.getFilename()).query(context);
        if (downloadInfoCursor.moveToNext()) {
            Log.i(TAG, "Song already saved, skipping");
            return 0;
        }
        return saveDownloadInfo(context, downloadInfo);

    }

    public static List<DownloadInfo> getDownloadedSongs(Context context) {
        DownloadinfoCursor downloadInfoCursor = new DownloadinfoSelection().query(context);
        List<DownloadInfo> downloadInfos = new ArrayList<DownloadInfo>();
        while (downloadInfoCursor.moveToNext()) {
            DownloadInfo downloadInfo = getDownloadInfo(downloadInfoCursor);
            downloadInfo.setSongInfo(getSongInfo(context, downloadInfoCursor.getSonginfoId()));
            downloadInfos.add(downloadInfo);
        }
        return downloadInfos;
    }

    public static SongInfo getSongInfo(Context context, long id) {
        SonginfoCursor songInfoCursor = new SonginfoSelection().id(id).query(context);

        // Cursor songInfoCursor = context.getContentResolver().query(SonginfoColumns.CONTENT_URI, SonginfoColumns.ALL_COLUMNS, null, null, null);
        if (songInfoCursor.moveToNext())
            return getSongInfo(songInfoCursor);
        else {
            SonginfoSelection songinfoSelection = new SonginfoSelection();
            SonginfoCursor cursor = songinfoSelection.query(context);
            Log.i(TAG, "printing all saved song infos");
            while (cursor.moveToNext()) {
                System.out.println(getSongInfo(cursor));
            }
            throw new RuntimeException("inconsistent download info and sound info");
        }
    }

    private static SongInfo getSongInfo(Cursor cursor) {
        SongInfo songInfo = new SongInfo();
        songInfo.setId(cursor.getString(cursor.getColumnIndex(SonginfoColumns.ID)));
        songInfo.setTitle(cursor.getString(cursor.getColumnIndex(SonginfoColumns.TITLE)));
        songInfo.setArtist(cursor.getString(cursor.getColumnIndex(SonginfoColumns.ARTIST)));
        songInfo.setLength(cursor.getInt(cursor.getColumnIndex(SonginfoColumns.LENGTH)));
        return songInfo;
    }

    private static SongInfo getSongInfo(SonginfoCursor cursor) {
        SongInfo songInfo = new SongInfo();
        songInfo.setId(cursor.getSongId());
        songInfo.setTitle(cursor.getTitle());
        songInfo.setArtist(cursor.getArtist());
        songInfo.setLength(cursor.getLength());
        return songInfo;
    }


    private static DownloadInfo getDownloadInfo(DownloadinfoCursor cursor) {

        DownloadInfo downloadInfo = new DownloadInfo();
        downloadInfo.setFilename(cursor.getFileName());
        downloadInfo.setSize(cursor.getSize());
        downloadInfo.setDownloadLink(cursor.getDownloadLink());
        return downloadInfo;
    }


}
