package com.example.kuldeepgupta.songsearch.provider.downloadinfo;

// @formatter:off
import com.example.kuldeepgupta.songsearch.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * The information about downloaded song
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface DownloadinfoModel extends BaseModel {

    /**
     * Primary key.
     */
    long getId();

    /**
     * Get the {@code songinfo_id} value.
     */
    long getSonginfoId();

    /**
     * Get the {@code download_link} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getDownloadLink();

    /**
     * Get the {@code file_name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getFileName();

    /**
     * Get the {@code size} value.
     */
    int getSize();
}
