package com.example.kuldeepgupta.songsearch.provider.songinfo;

// @formatter:off
import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.example.kuldeepgupta.songsearch.provider.base.AbstractSelection;

/**
 * Selection for the {@code songinfo} table.
 */
@SuppressWarnings({"unused", "WeakerAccess", "Recycle"})
public class SonginfoSelection extends AbstractSelection<SonginfoSelection> {
    @Override
    protected Uri baseUri() {
        return SonginfoColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code SonginfoCursor} object, which is positioned before the first entry, or null.
     */
    public SonginfoCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new SonginfoCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public SonginfoCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code SonginfoCursor} object, which is positioned before the first entry, or null.
     */
    public SonginfoCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new SonginfoCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public SonginfoCursor query(Context context) {
        return query(context, null);
    }


    public SonginfoSelection id(long... value) {
        addEquals("songinfo." + SonginfoColumns._ID, toObjectArray(value));
        return this;
    }

    public SonginfoSelection idNot(long... value) {
        addNotEquals("songinfo." + SonginfoColumns._ID, toObjectArray(value));
        return this;
    }

    public SonginfoSelection orderById(boolean desc) {
        orderBy("songinfo." + SonginfoColumns._ID, desc);
        return this;
    }

    public SonginfoSelection orderById() {
        return orderById(false);
    }

    public SonginfoSelection id(String... value) {
        addEquals(SonginfoColumns.ID, value);
        return this;
    }

    public SonginfoSelection idNot(String... value) {
        addNotEquals(SonginfoColumns.ID, value);
        return this;
    }

    public SonginfoSelection idLike(String... value) {
        addLike(SonginfoColumns.ID, value);
        return this;
    }

    public SonginfoSelection idContains(String... value) {
        addContains(SonginfoColumns.ID, value);
        return this;
    }

    public SonginfoSelection idStartsWith(String... value) {
        addStartsWith(SonginfoColumns.ID, value);
        return this;
    }

    public SonginfoSelection idEndsWith(String... value) {
        addEndsWith(SonginfoColumns.ID, value);
        return this;
    }

    public SonginfoSelection orderBySongId(boolean desc) {
        orderBy(SonginfoColumns.ID, desc);
        return this;
    }

    public SonginfoSelection orderBySongId() {
        orderBy(SonginfoColumns.ID, false);
        return this;
    }

    public SonginfoSelection title(String... value) {
        addEquals(SonginfoColumns.TITLE, value);
        return this;
    }

    public SonginfoSelection titleNot(String... value) {
        addNotEquals(SonginfoColumns.TITLE, value);
        return this;
    }

    public SonginfoSelection titleLike(String... value) {
        addLike(SonginfoColumns.TITLE, value);
        return this;
    }

    public SonginfoSelection titleContains(String... value) {
        addContains(SonginfoColumns.TITLE, value);
        return this;
    }

    public SonginfoSelection titleStartsWith(String... value) {
        addStartsWith(SonginfoColumns.TITLE, value);
        return this;
    }

    public SonginfoSelection titleEndsWith(String... value) {
        addEndsWith(SonginfoColumns.TITLE, value);
        return this;
    }

    public SonginfoSelection orderByTitle(boolean desc) {
        orderBy(SonginfoColumns.TITLE, desc);
        return this;
    }

    public SonginfoSelection orderByTitle() {
        orderBy(SonginfoColumns.TITLE, false);
        return this;
    }

    public SonginfoSelection artist(String... value) {
        addEquals(SonginfoColumns.ARTIST, value);
        return this;
    }

    public SonginfoSelection artistNot(String... value) {
        addNotEquals(SonginfoColumns.ARTIST, value);
        return this;
    }

    public SonginfoSelection artistLike(String... value) {
        addLike(SonginfoColumns.ARTIST, value);
        return this;
    }

    public SonginfoSelection artistContains(String... value) {
        addContains(SonginfoColumns.ARTIST, value);
        return this;
    }

    public SonginfoSelection artistStartsWith(String... value) {
        addStartsWith(SonginfoColumns.ARTIST, value);
        return this;
    }

    public SonginfoSelection artistEndsWith(String... value) {
        addEndsWith(SonginfoColumns.ARTIST, value);
        return this;
    }

    public SonginfoSelection orderByArtist(boolean desc) {
        orderBy(SonginfoColumns.ARTIST, desc);
        return this;
    }

    public SonginfoSelection orderByArtist() {
        orderBy(SonginfoColumns.ARTIST, false);
        return this;
    }

    public SonginfoSelection length(Integer... value) {
        addEquals(SonginfoColumns.LENGTH, value);
        return this;
    }

    public SonginfoSelection lengthNot(Integer... value) {
        addNotEquals(SonginfoColumns.LENGTH, value);
        return this;
    }

    public SonginfoSelection lengthGt(int value) {
        addGreaterThan(SonginfoColumns.LENGTH, value);
        return this;
    }

    public SonginfoSelection lengthGtEq(int value) {
        addGreaterThanOrEquals(SonginfoColumns.LENGTH, value);
        return this;
    }

    public SonginfoSelection lengthLt(int value) {
        addLessThan(SonginfoColumns.LENGTH, value);
        return this;
    }

    public SonginfoSelection lengthLtEq(int value) {
        addLessThanOrEquals(SonginfoColumns.LENGTH, value);
        return this;
    }

    public SonginfoSelection orderByLength(boolean desc) {
        orderBy(SonginfoColumns.LENGTH, desc);
        return this;
    }

    public SonginfoSelection orderByLength() {
        orderBy(SonginfoColumns.LENGTH, false);
        return this;
    }

    public SonginfoSelection quality(String... value) {
        addEquals(SonginfoColumns.QUALITY, value);
        return this;
    }

    public SonginfoSelection qualityNot(String... value) {
        addNotEquals(SonginfoColumns.QUALITY, value);
        return this;
    }

    public SonginfoSelection qualityLike(String... value) {
        addLike(SonginfoColumns.QUALITY, value);
        return this;
    }

    public SonginfoSelection qualityContains(String... value) {
        addContains(SonginfoColumns.QUALITY, value);
        return this;
    }

    public SonginfoSelection qualityStartsWith(String... value) {
        addStartsWith(SonginfoColumns.QUALITY, value);
        return this;
    }

    public SonginfoSelection qualityEndsWith(String... value) {
        addEndsWith(SonginfoColumns.QUALITY, value);
        return this;
    }

    public SonginfoSelection orderByQuality(boolean desc) {
        orderBy(SonginfoColumns.QUALITY, desc);
        return this;
    }

    public SonginfoSelection orderByQuality() {
        orderBy(SonginfoColumns.QUALITY, false);
        return this;
    }
}
