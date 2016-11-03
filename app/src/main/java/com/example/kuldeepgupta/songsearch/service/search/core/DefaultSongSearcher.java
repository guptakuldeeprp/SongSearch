package com.example.kuldeepgupta.songsearch.service.search.core;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static com.example.kuldeepgupta.songsearch.util.CommonUtil.*;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by kuldeep.gupta on 10/25/2016.
 */

public class DefaultSongSearcher implements SongSearcher {


    protected SongSearcher.SongSearcherConfig config;

    public DefaultSongSearcher() {

    }

    @Override
    public void init(SongSearcher.SongSearcherConfig config) {
        this.config = config;
    }

    @Override
    public List<Map<String, String>> search(String song) throws IOException {
        if (config == null)
            throw new IllegalStateException(getClass().getName() + " is not initialized.");
        if (song == null)
            throw new NullPointerException("null String for search");
        String fullUrl = config.getUrlPrefix() + cleanUrlParts(song);
        URL url = new URL(fullUrl);
        Proxy proxy = null;
        if (config.getProxyHost() != null && config.getProxyPort() > 0)
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(config.getProxyHost(), config.getProxyPort()));
        HttpURLConnection request = proxy == null ? (HttpURLConnection) url.openConnection() : (HttpURLConnection) url.openConnection(proxy);
        request.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB;     rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13 (.NET CLR 3.5.30729)");
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        return processJsonResult(root.getAsJsonObject());
    }

    protected List<Map<String, String>> processJsonResult(JsonObject rootobj) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Map<String, String>> search(String song, String artist) throws IOException {

        if (artist != null) {
            if (artist.contains("feat"))
                return song == null ? search(artist.split("feat")[0]) : search(artist.split("feat")[0] + " " + song);
            if (artist.contains("Feat"))
                return song == null ? search(artist.split("Feat")[0]) : search(artist.split("Feat")[0] + " " + song);
            if (artist.contains("FEAT"))
                return song == null ? search(artist.split("FEAT")[0]) : search(artist.split("FEAT")[0] + " " + song);
        }

        return search(song);
    }

}
