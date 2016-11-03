package com.example.kuldeepgupta.songsearch.provider.songinfo;

// @formatter:off
import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.kuldeepgupta.songsearch.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code songinfo} table.
 */
@SuppressWarnings({"ConstantConditions", "unused"})
public class SonginfoContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return SonginfoColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable SonginfoSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The context to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable SonginfoSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public SonginfoContentValues putId(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("id must not be null");
        mContentValues.put(SonginfoColumns.ID, value);
        return this;
    }


    public SonginfoContentValues putTitle(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("title must not be null");
        mContentValues.put(SonginfoColumns.TITLE, value);
        return this;
    }


    public SonginfoContentValues putArtist(@Nullable String value) {
        mContentValues.put(SonginfoColumns.ARTIST, value);
        return this;
    }

    public SonginfoContentValues putArtistNull() {
        mContentValues.putNull(SonginfoColumns.ARTIST);
        return this;
    }

    public SonginfoContentValues putLength(@Nullable Integer value) {
        mContentValues.put(SonginfoColumns.LENGTH, value);
        return this;
    }

    public SonginfoContentValues putLengthNull() {
        mContentValues.putNull(SonginfoColumns.LENGTH);
        return this;
    }

    public SonginfoContentValues putQuality(@Nullable String value) {
        mContentValues.put(SonginfoColumns.QUALITY, value);
        return this;
    }

    public SonginfoContentValues putQualityNull() {
        mContentValues.putNull(SonginfoColumns.QUALITY);
        return this;
    }
}
