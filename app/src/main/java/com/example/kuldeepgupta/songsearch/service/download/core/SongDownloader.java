package com.example.kuldeepgupta.songsearch.service.download.core;

import android.util.Log;

import com.example.kuldeepgupta.songsearch.service.download.android.DownloadInfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by kuldeep.gupta on 10/26/2016.
 */

public class SongDownloader {

    private static final String TAG = SongDownloader.class.getName();
    private SongDownloaderConfig config;

    public void init(SongDownloaderConfig config) {
        this.config = config;
    }


    public InputStream download(DownloadInfo downloadInfo) throws IOException {
        if (config == null)
            throw new IllegalStateException(getClass().getName() + " is not initialized.");

        if (downloadInfo == null)
            throw new NullPointerException("download info cannot be null");

        //URL url = new URL(downloadInfo.getDownloadLink());

        Log.i(TAG,"download link: http://www.pleer.com/browser-extension/files/" + downloadInfo.getSongInfo().getId() + ".mp3");
        URL url = new URL("http://www.pleer.com/browser-extension/files/" + downloadInfo.getSongInfo().getId() + ".mp3");
        Proxy proxy = null;
        if (config.getProxyHost() != null && config.getProxyPort() > 0)
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(config.getProxyHost(), config.getProxyPort()));
        URLConnection urlConnection = proxy == null ? url.openConnection() : url.openConnection(proxy);
        urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB;     rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13 (.NET CLR 3.5.30729)");
        urlConnection.connect();
        downloadInfo.setSize(urlConnection.getContentLength());
        return urlConnection.getInputStream();
    }

    public static class SongDownloaderConfig {
        private String downloadLink;
        private String proxyHost;
        private int proxyPort;

        public String getDownloadLink() {
            return downloadLink;
        }

        public SongDownloaderConfig setDownloadLink(String downloadLink) {
            this.downloadLink = downloadLink;
            return this;
        }

        public String getProxyHost() {
            return proxyHost;
        }

        public SongDownloaderConfig setProxyHost(String proxyHost) {
            this.proxyHost = proxyHost;
            return this;
        }

        public int getProxyPort() {
            return proxyPort;
        }

        public SongDownloaderConfig setProxyPort(int proxyPort) {
            this.proxyPort = proxyPort;
            return this;
        }
    }


}
