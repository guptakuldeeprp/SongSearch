package com.example.kuldeepgupta.songsearch.service;

import android.content.Context;
import android.util.Log;

import com.example.kuldeepgupta.songsearch.R;
import com.example.kuldeepgupta.songsearch.async.SongDownloadTask;
import com.example.kuldeepgupta.songsearch.domain.SongInfo;
import com.example.kuldeepgupta.songsearch.service.download.android.DownloadInfo;
import com.example.kuldeepgupta.songsearch.service.download.core.SongDownloader;
import com.example.kuldeepgupta.songsearch.service.search.core.PleerSearcher;
import com.example.kuldeepgupta.songsearch.service.search.core.SongSearcher;
import com.example.kuldeepgupta.songsearch.util.CommonUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by kuldeep.gupta on 10/26/2016.
 */

public class SongSearchDownloadService {

    private static final String TAG = SongSearchDownloadService.class.getName();
    // private static final String FAILURE = "No song found";

    private static List<Map<String, String>> _search(Context context, String searchString) throws IOException {
        SongSearcher.SongSearcherConfig config = new SongSearcher.SongSearcherConfig();
        config.setAllowModifiedSongs(false)
                .setProxyHost(context.getString(R.string.proxy_host))
                .setProxyPort(context.getResources().getInteger(R.integer.proxy_port))
                .setExceptionOnFailure(false)
                .setUrlPrefix(context.getString(R.string.pleer_search_url_prefix))
                .setKeysOfInterest(new String[]{"artist", "track", "length", "id", "file", "bitrate"});

        SongSearcher searcher = new PleerSearcher();
        searcher.init(config);
        return searcher.search(searchString);
    }

    public static List<SongInfo> search(Context context, String searchString) {
        List<SongInfo> results = new ArrayList<>();
        try {

            List<Map<String, String>> searcherResult = _search(context, searchString);
            for (Map<String, String> songMap : searcherResult) {
                results.add(getSongInfo(songMap));
            }

        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return results;

    }

    public static List<DownloadInfo> searchForDownload(Context context, String searchString) {
        List<DownloadInfo> results = new ArrayList<>();
        try {

            List<Map<String, String>> searcherResult = _search(context, searchString);
            for (Map<String, String> songMap : searcherResult) {
                DownloadInfo downloadInfo = new DownloadInfo(getSongInfo(songMap), songMap.get("file"));
                results.add(downloadInfo);
            }

        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return results;

    }

    public static SongInfo getSongInfo(Map<String, String> songMap) {
        SongInfo songInfo = new SongInfo();
        songInfo.setId(songMap.get("id"));
        songInfo.setTitle(songMap.get("track"));
        songInfo.setArtist(songMap.get("artist"));
        songInfo.setLength(Integer.parseInt(songMap.get("length").trim()));
        songInfo.setQuality(songMap.get("bitrate"));
        return songInfo;
    }

    public static InputStream download(Context context, DownloadInfo downloadInfo) throws IOException {
        //String downloadPath = context.getString(R.string.)
        SongDownloader.SongDownloaderConfig config = new SongDownloader.SongDownloaderConfig()
                .setProxyHost(context.getString(R.string.proxy_host))
                .setProxyPort(context.getResources().getInteger(R.integer.proxy_port))
                .setDownloadLink(downloadInfo.getDownloadLink());
                //.setDownloadLink("http://www.pleer.com/browser-extension/files/" + downloadInfo.getSongInfo().getId() + ".mp3");
        SongDownloader downloader = new SongDownloader();
        downloader.init(config);
        return downloader.download(downloadInfo);
    }

    public static class SearchResult {
        public String id;
        public SongInfo songInfo;
        public String downloadLink;
        public String listenLink;

        public SearchResult(String id, SongInfo songInfo) {
            this.id = id;
            this.songInfo = songInfo;
        }

        public String getId() {
            return id;
        }

        public SongInfo getSongInfo() {
            return songInfo;
        }

        public String getDownloadLink() {
            return downloadLink;
        }

        public void setDownloadLink(String downloadLink) {
            this.downloadLink = downloadLink;
        }

        public String getListenLink() {
            return listenLink;
        }

        public void setListenLink(String listenLink) {
            this.listenLink = listenLink;
        }
    }

}
