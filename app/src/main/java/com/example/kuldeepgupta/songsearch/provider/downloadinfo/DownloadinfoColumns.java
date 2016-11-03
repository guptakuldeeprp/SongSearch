package com.example.kuldeepgupta.songsearch.provider.downloadinfo;

// @formatter:off
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.kuldeepgupta.songsearch.provider.DownloadInfoProvider;
import com.example.kuldeepgupta.songsearch.provider.base.AbstractSelection;
import com.example.kuldeepgupta.songsearch.provider.downloadinfo.DownloadinfoColumns;
import com.example.kuldeepgupta.songsearch.provider.songinfo.SonginfoColumns;

/**
 * The information about downloaded song
 */
@SuppressWarnings("unused")
public class DownloadinfoColumns implements BaseColumns {
    public static final String TABLE_NAME = "downloadinfo";
    public static final Uri CONTENT_URI = Uri.parse(DownloadInfoProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String SONGINFO_ID = "songinfo_id";

    public static final String DOWNLOAD_LINK = "download_link";

    public static final String FILE_NAME = "file_name";

    public static final String SIZE = "size";


    public static final String DEFAULT_ORDER = null;

    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            SONGINFO_ID,
            DOWNLOAD_LINK,
            FILE_NAME,
            SIZE
    };

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(SONGINFO_ID) || c.contains("." + SONGINFO_ID)) return true;
            if (c.equals(DOWNLOAD_LINK) || c.contains("." + DOWNLOAD_LINK)) return true;
            if (c.equals(FILE_NAME) || c.contains("." + FILE_NAME)) return true;
            if (c.equals(SIZE) || c.contains("." + SIZE)) return true;
        }
        return false;
    }

    public static final String PREFIX_SONGINFO = TABLE_NAME + "__" + SonginfoColumns.TABLE_NAME;
}
