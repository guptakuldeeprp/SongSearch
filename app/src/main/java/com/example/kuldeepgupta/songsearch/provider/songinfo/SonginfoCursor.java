package com.example.kuldeepgupta.songsearch.provider.songinfo;

// @formatter:off
import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.kuldeepgupta.songsearch.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code songinfo} table.
 */
@SuppressWarnings({"WeakerAccess", "unused", "UnnecessaryLocalVariable"})
public class SonginfoCursor extends AbstractCursor implements SonginfoModel {
    public SonginfoCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    @Override
    public long getId() {
        Long res = getLongOrNull(SonginfoColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code id} value.
     * Cannot be {@code null}.
     */
    @NonNull
    @Override
    public String getSongId() {
        String res = getStringOrNull(SonginfoColumns.ID);
        if (res == null)
            throw new NullPointerException("The value of 'id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code title} value.
     * Cannot be {@code null}.
     */
    @NonNull
    @Override
    public String getTitle() {
        String res = getStringOrNull(SonginfoColumns.TITLE);
        if (res == null)
            throw new NullPointerException("The value of 'title' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code artist} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getArtist() {
        String res = getStringOrNull(SonginfoColumns.ARTIST);
        return res;
    }

    /**
     * Get the {@code length} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public Integer getLength() {
        Integer res = getIntegerOrNull(SonginfoColumns.LENGTH);
        return res;
    }

    /**
     * Get the {@code quality} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getQuality() {
        String res = getStringOrNull(SonginfoColumns.QUALITY);
        return res;
    }
}
