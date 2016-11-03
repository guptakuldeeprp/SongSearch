package com.example.kuldeepgupta.songsearch.service.search.core;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by kuldeep.gupta on 10/25/2016.
 */

public interface SongSearcher {

    List<Map<String, String>> search(String term) throws IOException;

    List<Map<String, String>> search(String term, String artist) throws IOException;

    void init(SongSearcherConfig searcherConfig);

    public static class SongSearcherConfig {

        private String urlPrefix;
        private String[] keysOfInterest;
        private String proxyHost;
        private int proxyPort;
        private boolean exceptionOnFailure;
        /* Implementation SongSearcher(s) may choose to ignore this option*/
        private boolean allowModifiedSongs; // like remixes and cover

        public SongSearcherConfig() {

        }

        public String getUrlPrefix() {
            return urlPrefix;
        }

        public SongSearcherConfig setUrlPrefix(String urlPrefix) {
            this.urlPrefix = urlPrefix;
            return this;
        }

        public String[] getKeysOfInterest() {
            return keysOfInterest;
        }

        public SongSearcherConfig setKeysOfInterest(String[] keysOfInterest) {
            this.keysOfInterest = keysOfInterest;
            return this;
        }

        public String getProxyHost() {
            return proxyHost;
        }

        public SongSearcherConfig setProxyHost(String proxyHost) {
            this.proxyHost = proxyHost;
            return this;
        }

        public int getProxyPort() {
            return proxyPort;
        }

        public SongSearcherConfig setProxyPort(int proxyPort) {
            this.proxyPort = proxyPort;
            return this;
        }

        public boolean isExceptionOnFailure() {
            return exceptionOnFailure;
        }

        public SongSearcherConfig setExceptionOnFailure(boolean exceptionOnFailure) {
            this.exceptionOnFailure = exceptionOnFailure;
            return this;
        }

        public boolean isAllowModifiedSongs() {
            return allowModifiedSongs;
        }

        public SongSearcherConfig setAllowModifiedSongs(boolean allowModifiedSongs) {
            this.allowModifiedSongs = allowModifiedSongs;
            return this;
        }
    }
}
