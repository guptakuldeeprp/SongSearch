package com.example.kuldeepgupta.songsearch.provider.songinfo;

// @formatter:off
import com.example.kuldeepgupta.songsearch.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Song Information
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface SonginfoModel extends BaseModel {

    /**
     * Primary key.
     */
    long getId();

    /**
     * Get the {@code id} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getSongId();

    /**
     * Get the {@code title} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getTitle();

    /**
     * Get the {@code artist} value.
     * Can be {@code null}.
     */
    @Nullable
    String getArtist();

    /**
     * Get the {@code length} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getLength();

    /**
     * Get the {@code quality} value.
     * Can be {@code null}.
     */
    @Nullable
    String getQuality();
}
