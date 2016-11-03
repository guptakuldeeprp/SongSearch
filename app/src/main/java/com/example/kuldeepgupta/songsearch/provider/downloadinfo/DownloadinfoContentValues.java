package com.example.kuldeepgupta.songsearch.provider.downloadinfo;

// @formatter:off
import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.kuldeepgupta.songsearch.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code downloadinfo} table.
 */
@SuppressWarnings({"ConstantConditions", "unused"})
public class DownloadinfoContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return DownloadinfoColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable DownloadinfoSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The context to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable DownloadinfoSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public DownloadinfoContentValues putSonginfoId(long value) {
        mContentValues.put(DownloadinfoColumns.SONGINFO_ID, value);
        return this;
    }


    public DownloadinfoContentValues putDownloadLink(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("downloadLink must not be null");
        mContentValues.put(DownloadinfoColumns.DOWNLOAD_LINK, value);
        return this;
    }


    public DownloadinfoContentValues putFileName(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("fileName must not be null");
        mContentValues.put(DownloadinfoColumns.FILE_NAME, value);
        return this;
    }


    public DownloadinfoContentValues putSize(int value) {
        mContentValues.put(DownloadinfoColumns.SIZE, value);
        return this;
    }

}
