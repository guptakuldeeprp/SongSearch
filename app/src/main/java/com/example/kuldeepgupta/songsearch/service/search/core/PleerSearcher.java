package com.example.kuldeepgupta.songsearch.service.search.core;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kuldeep.gupta on 10/25/2016.
 */

public class PleerSearcher extends DefaultSongSearcher {

    public static enum Bitrate {
        HIGH,
        LOW,
        VBR,
        UNKNOWN;

        public static Bitrate getBitrate(String input) {
            if (input.trim().equalsIgnoreCase("vbr"))
                return VBR;
            else {
                try {
                    if (Integer.parseInt(input.trim()) >= 256)
                        return HIGH;
                    else
                        return LOW;
                } catch (Exception e) {
                    //ignore error
                }
            }

            return UNKNOWN;
        }

    }


    private Pattern p = Pattern.compile("^[\\x20-\\x7d]*$");
    private static final Matcher[] MODIFICATION_MATCHERS = new Matcher[]{
            Pattern.compile("rework", Pattern.CASE_INSENSITIVE).matcher(""),
            Pattern.compile("remix", Pattern.CASE_INSENSITIVE).matcher(""),
            Pattern.compile("cover", Pattern.CASE_INSENSITIVE).matcher(""),
            Pattern.compile("mix", Pattern.CASE_INSENSITIVE).matcher("")
    };

    public PleerSearcher() {

    }

    @Override
    protected List<Map<String, String>> processJsonResult(JsonObject rootobj) {
        JsonArray arr = rootobj.getAsJsonArray("tracks");
        try {
            rootobj = arr.get(0).getAsJsonObject();
        } catch (IndexOutOfBoundsException e) {
            if (config.isExceptionOnFailure())
                throw new RuntimeException("song not found!");
            else
                return Collections.EMPTY_LIST;
        }


        // Set<String> keys = new HashSet<>();
        // Collections.addAll(keys, config.getKeysOfInterest());

        List<Map<String, String>> result = new ArrayList<Map<String, String>>();

        for (int i = 0; i < arr.size(); i++) {
            rootobj = arr.get(i).getAsJsonObject();
            //Matcher m1 = p.matcher(rootobj.get("artist").toString().replace("\"", "").replace("[", "~").replace("]", "~"));
            //Matcher m2 = p.matcher(rootobj.get("track").toString().replace("\"", "").replace("[", "~").replace("]", "~"));
            if (!config.isAllowModifiedSongs() && isSongModified(rootobj))
                continue;

            Map<String, String> songMap = new HashMap<>();
            for (String key : config.getKeysOfInterest()) {
                songMap.put(key, rootobj.get(key).toString().replace("\"", ""));
            }

            songMap.put("bitrate", Bitrate.getBitrate(rootobj.get("bitrate").toString()).name());
            result.add(songMap);

        }

        return result;
    }

    private boolean isSongModified(JsonObject rootobj) {
        for (Matcher matcher : MODIFICATION_MATCHERS) {
            if (matcher.reset(rootobj.get("artist").toString()).matches() || matcher.reset(rootobj.get("track").toString()).matches()) {
                return true;
            }
        }
        return false;
    }
}
