package com.example.kuldeepgupta.songsearch.provider.downloadinfo;

// @formatter:off
import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.kuldeepgupta.songsearch.provider.base.AbstractCursor;
import com.example.kuldeepgupta.songsearch.provider.songinfo.*;

/**
 * Cursor wrapper for the {@code downloadinfo} table.
 */
@SuppressWarnings({"WeakerAccess", "unused", "UnnecessaryLocalVariable"})
public class DownloadinfoCursor extends AbstractCursor implements DownloadinfoModel {
    public DownloadinfoCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    @Override
    public long getId() {
        Long res = getLongOrNull(DownloadinfoColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code songinfo_id} value.
     */
    @Override
    public long getSonginfoId() {
        Long res = getLongOrNull(DownloadinfoColumns.SONGINFO_ID);
        if (res == null)
            throw new NullPointerException("The value of 'songinfo_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code id} value.
     * Cannot be {@code null}.
     */
//    @NonNull
//    public String getSonginfoId() {
//        String res = getStringOrNull(SonginfoColumns.ID);
//        if (res == null)
//            throw new NullPointerException("The value of 'id' in the database was null, which is not allowed according to the model definition");
//        return res;
//    }

    /**
     * Get the {@code title} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getSonginfoTitle() {
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
    public String getSonginfoArtist() {
        String res = getStringOrNull(SonginfoColumns.ARTIST);
        return res;
    }

    /**
     * Get the {@code length} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getSonginfoLength() {
        Integer res = getIntegerOrNull(SonginfoColumns.LENGTH);
        return res;
    }

    /**
     * Get the {@code quality} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSonginfoQuality() {
        String res = getStringOrNull(SonginfoColumns.QUALITY);
        return res;
    }

    /**
     * Get the {@code download_link} value.
     * Cannot be {@code null}.
     */
    @NonNull
    @Override
    public String getDownloadLink() {
        String res = getStringOrNull(DownloadinfoColumns.DOWNLOAD_LINK);
        if (res == null)
            throw new NullPointerException("The value of 'download_link' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code file_name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    @Override
    public String getFileName() {
        String res = getStringOrNull(DownloadinfoColumns.FILE_NAME);
        if (res == null)
            throw new NullPointerException("The value of 'file_name' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code size} value.
     */
    @Override
    public int getSize() {
        Integer res = getIntegerOrNull(DownloadinfoColumns.SIZE);
        if (res == null)
            throw new NullPointerException("The value of 'size' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
