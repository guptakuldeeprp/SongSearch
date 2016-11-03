package com.example.kuldeepgupta.songsearch.provider.songinfo;

// @formatter:off
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.kuldeepgupta.songsearch.provider.DownloadInfoProvider;
import com.example.kuldeepgupta.songsearch.provider.base.AbstractSelection;
import com.example.kuldeepgupta.songsearch.provider.downloadinfo.DownloadinfoColumns;
import com.example.kuldeepgupta.songsearch.provider.songinfo.SonginfoColumns;

/**
 * Song Information
 */
@SuppressWarnings("unused")
public class SonginfoColumns implements BaseColumns {
    public static final String TABLE_NAME = "songinfo";
    public static final Uri CONTENT_URI = Uri.parse(DownloadInfoProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String ID = "id";

    public static final String TITLE = "title";

    public static final String ARTIST = "artist";

    public static final String LENGTH = "length";

    public static final String QUALITY = "quality";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." + TITLE;

    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            ID,
            TITLE,
            ARTIST,
            LENGTH,
            QUALITY
    };

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(ID) || c.contains("." + ID)) return true;
            if (c.equals(TITLE) || c.contains("." + TITLE)) return true;
            if (c.equals(ARTIST) || c.contains("." + ARTIST)) return true;
            if (c.equals(LENGTH) || c.contains("." + LENGTH)) return true;
            if (c.equals(QUALITY) || c.contains("." + QUALITY)) return true;
        }
        return false;
    }

}
