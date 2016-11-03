package com.example.kuldeepgupta.songsearch.provider.downloadinfo;

// @formatter:off
import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.example.kuldeepgupta.songsearch.provider.base.AbstractSelection;
import com.example.kuldeepgupta.songsearch.provider.songinfo.*;

/**
 * Selection for the {@code downloadinfo} table.
 */
@SuppressWarnings({"unused", "WeakerAccess", "Recycle"})
public class DownloadinfoSelection extends AbstractSelection<DownloadinfoSelection> {
    @Override
    protected Uri baseUri() {
        return DownloadinfoColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DownloadinfoCursor} object, which is positioned before the first entry, or null.
     */
    public DownloadinfoCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DownloadinfoCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public DownloadinfoCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DownloadinfoCursor} object, which is positioned before the first entry, or null.
     */
    public DownloadinfoCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DownloadinfoCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public DownloadinfoCursor query(Context context) {
        return query(context, null);
    }


    public DownloadinfoSelection id(long... value) {
        addEquals("downloadinfo." + DownloadinfoColumns._ID, toObjectArray(value));
        return this;
    }

    public DownloadinfoSelection idNot(long... value) {
        addNotEquals("downloadinfo." + DownloadinfoColumns._ID, toObjectArray(value));
        return this;
    }

    public DownloadinfoSelection orderById(boolean desc) {
        orderBy("downloadinfo." + DownloadinfoColumns._ID, desc);
        return this;
    }

    public DownloadinfoSelection orderById() {
        return orderById(false);
    }

    public DownloadinfoSelection songinfoId(long... value) {
        addEquals(DownloadinfoColumns.SONGINFO_ID, toObjectArray(value));
        return this;
    }

    public DownloadinfoSelection songinfoIdNot(long... value) {
        addNotEquals(DownloadinfoColumns.SONGINFO_ID, toObjectArray(value));
        return this;
    }

    public DownloadinfoSelection songinfoIdGt(long value) {
        addGreaterThan(DownloadinfoColumns.SONGINFO_ID, value);
        return this;
    }

    public DownloadinfoSelection songinfoIdGtEq(long value) {
        addGreaterThanOrEquals(DownloadinfoColumns.SONGINFO_ID, value);
        return this;
    }

    public DownloadinfoSelection songinfoIdLt(long value) {
        addLessThan(DownloadinfoColumns.SONGINFO_ID, value);
        return this;
    }

    public DownloadinfoSelection songinfoIdLtEq(long value) {
        addLessThanOrEquals(DownloadinfoColumns.SONGINFO_ID, value);
        return this;
    }

    public DownloadinfoSelection orderBySonginfoId(boolean desc) {
        orderBy(DownloadinfoColumns.SONGINFO_ID, desc);
        return this;
    }

    public DownloadinfoSelection orderBySonginfoId() {
        orderBy(DownloadinfoColumns.SONGINFO_ID, false);
        return this;
    }

    public DownloadinfoSelection songinfoId(String... value) {
        addEquals(SonginfoColumns.ID, value);
        return this;
    }

    public DownloadinfoSelection songinfoIdNot(String... value) {
        addNotEquals(SonginfoColumns.ID, value);
        return this;
    }

    public DownloadinfoSelection songinfoIdLike(String... value) {
        addLike(SonginfoColumns.ID, value);
        return this;
    }

    public DownloadinfoSelection songinfoIdContains(String... value) {
        addContains(SonginfoColumns.ID, value);
        return this;
    }

    public DownloadinfoSelection songinfoIdStartsWith(String... value) {
        addStartsWith(SonginfoColumns.ID, value);
        return this;
    }

    public DownloadinfoSelection songinfoIdEndsWith(String... value) {
        addEndsWith(SonginfoColumns.ID, value);
        return this;
    }

//    public DownloadinfoSelection orderBySonginfoId(boolean desc) {
//        orderBy(SonginfoColumns.ID, desc);
//        return this;
//    }
//
//    public DownloadinfoSelection orderBySonginfoId() {
//        orderBy(SonginfoColumns.ID, false);
//        return this;
//    }

    public DownloadinfoSelection songinfoTitle(String... value) {
        addEquals(SonginfoColumns.TITLE, value);
        return this;
    }

    public DownloadinfoSelection songinfoTitleNot(String... value) {
        addNotEquals(SonginfoColumns.TITLE, value);
        return this;
    }

    public DownloadinfoSelection songinfoTitleLike(String... value) {
        addLike(SonginfoColumns.TITLE, value);
        return this;
    }

    public DownloadinfoSelection songinfoTitleContains(String... value) {
        addContains(SonginfoColumns.TITLE, value);
        return this;
    }

    public DownloadinfoSelection songinfoTitleStartsWith(String... value) {
        addStartsWith(SonginfoColumns.TITLE, value);
        return this;
    }

    public DownloadinfoSelection songinfoTitleEndsWith(String... value) {
        addEndsWith(SonginfoColumns.TITLE, value);
        return this;
    }

    public DownloadinfoSelection orderBySonginfoTitle(boolean desc) {
        orderBy(SonginfoColumns.TITLE, desc);
        return this;
    }

    public DownloadinfoSelection orderBySonginfoTitle() {
        orderBy(SonginfoColumns.TITLE, false);
        return this;
    }

    public DownloadinfoSelection songinfoArtist(String... value) {
        addEquals(SonginfoColumns.ARTIST, value);
        return this;
    }

    public DownloadinfoSelection songinfoArtistNot(String... value) {
        addNotEquals(SonginfoColumns.ARTIST, value);
        return this;
    }

    public DownloadinfoSelection songinfoArtistLike(String... value) {
        addLike(SonginfoColumns.ARTIST, value);
        return this;
    }

    public DownloadinfoSelection songinfoArtistContains(String... value) {
        addContains(SonginfoColumns.ARTIST, value);
        return this;
    }

    public DownloadinfoSelection songinfoArtistStartsWith(String... value) {
        addStartsWith(SonginfoColumns.ARTIST, value);
        return this;
    }

    public DownloadinfoSelection songinfoArtistEndsWith(String... value) {
        addEndsWith(SonginfoColumns.ARTIST, value);
        return this;
    }

    public DownloadinfoSelection orderBySonginfoArtist(boolean desc) {
        orderBy(SonginfoColumns.ARTIST, desc);
        return this;
    }

    public DownloadinfoSelection orderBySonginfoArtist() {
        orderBy(SonginfoColumns.ARTIST, false);
        return this;
    }

    public DownloadinfoSelection songinfoLength(Integer... value) {
        addEquals(SonginfoColumns.LENGTH, value);
        return this;
    }

    public DownloadinfoSelection songinfoLengthNot(Integer... value) {
        addNotEquals(SonginfoColumns.LENGTH, value);
        return this;
    }

    public DownloadinfoSelection songinfoLengthGt(int value) {
        addGreaterThan(SonginfoColumns.LENGTH, value);
        return this;
    }

    public DownloadinfoSelection songinfoLengthGtEq(int value) {
        addGreaterThanOrEquals(SonginfoColumns.LENGTH, value);
        return this;
    }

    public DownloadinfoSelection songinfoLengthLt(int value) {
        addLessThan(SonginfoColumns.LENGTH, value);
        return this;
    }

    public DownloadinfoSelection songinfoLengthLtEq(int value) {
        addLessThanOrEquals(SonginfoColumns.LENGTH, value);
        return this;
    }

    public DownloadinfoSelection orderBySonginfoLength(boolean desc) {
        orderBy(SonginfoColumns.LENGTH, desc);
        return this;
    }

    public DownloadinfoSelection orderBySonginfoLength() {
        orderBy(SonginfoColumns.LENGTH, false);
        return this;
    }

    public DownloadinfoSelection songinfoQuality(String... value) {
        addEquals(SonginfoColumns.QUALITY, value);
        return this;
    }

    public DownloadinfoSelection songinfoQualityNot(String... value) {
        addNotEquals(SonginfoColumns.QUALITY, value);
        return this;
    }

    public DownloadinfoSelection songinfoQualityLike(String... value) {
        addLike(SonginfoColumns.QUALITY, value);
        return this;
    }

    public DownloadinfoSelection songinfoQualityContains(String... value) {
        addContains(SonginfoColumns.QUALITY, value);
        return this;
    }

    public DownloadinfoSelection songinfoQualityStartsWith(String... value) {
        addStartsWith(SonginfoColumns.QUALITY, value);
        return this;
    }

    public DownloadinfoSelection songinfoQualityEndsWith(String... value) {
        addEndsWith(SonginfoColumns.QUALITY, value);
        return this;
    }

    public DownloadinfoSelection orderBySonginfoQuality(boolean desc) {
        orderBy(SonginfoColumns.QUALITY, desc);
        return this;
    }

    public DownloadinfoSelection orderBySonginfoQuality() {
        orderBy(SonginfoColumns.QUALITY, false);
        return this;
    }

    public DownloadinfoSelection downloadLink(String... value) {
        addEquals(DownloadinfoColumns.DOWNLOAD_LINK, value);
        return this;
    }

    public DownloadinfoSelection downloadLinkNot(String... value) {
        addNotEquals(DownloadinfoColumns.DOWNLOAD_LINK, value);
        return this;
    }

    public DownloadinfoSelection downloadLinkLike(String... value) {
        addLike(DownloadinfoColumns.DOWNLOAD_LINK, value);
        return this;
    }

    public DownloadinfoSelection downloadLinkContains(String... value) {
        addContains(DownloadinfoColumns.DOWNLOAD_LINK, value);
        return this;
    }

    public DownloadinfoSelection downloadLinkStartsWith(String... value) {
        addStartsWith(DownloadinfoColumns.DOWNLOAD_LINK, value);
        return this;
    }

    public DownloadinfoSelection downloadLinkEndsWith(String... value) {
        addEndsWith(DownloadinfoColumns.DOWNLOAD_LINK, value);
        return this;
    }

    public DownloadinfoSelection orderByDownloadLink(boolean desc) {
        orderBy(DownloadinfoColumns.DOWNLOAD_LINK, desc);
        return this;
    }

    public DownloadinfoSelection orderByDownloadLink() {
        orderBy(DownloadinfoColumns.DOWNLOAD_LINK, false);
        return this;
    }

    public DownloadinfoSelection fileName(String... value) {
        addEquals(DownloadinfoColumns.FILE_NAME, value);
        return this;
    }

    public DownloadinfoSelection fileNameNot(String... value) {
        addNotEquals(DownloadinfoColumns.FILE_NAME, value);
        return this;
    }

    public DownloadinfoSelection fileNameLike(String... value) {
        addLike(DownloadinfoColumns.FILE_NAME, value);
        return this;
    }

    public DownloadinfoSelection fileNameContains(String... value) {
        addContains(DownloadinfoColumns.FILE_NAME, value);
        return this;
    }

    public DownloadinfoSelection fileNameStartsWith(String... value) {
        addStartsWith(DownloadinfoColumns.FILE_NAME, value);
        return this;
    }

    public DownloadinfoSelection fileNameEndsWith(String... value) {
        addEndsWith(DownloadinfoColumns.FILE_NAME, value);
        return this;
    }

    public DownloadinfoSelection orderByFileName(boolean desc) {
        orderBy(DownloadinfoColumns.FILE_NAME, desc);
        return this;
    }

    public DownloadinfoSelection orderByFileName() {
        orderBy(DownloadinfoColumns.FILE_NAME, false);
        return this;
    }

    public DownloadinfoSelection size(int... value) {
        addEquals(DownloadinfoColumns.SIZE, toObjectArray(value));
        return this;
    }

    public DownloadinfoSelection sizeNot(int... value) {
        addNotEquals(DownloadinfoColumns.SIZE, toObjectArray(value));
        return this;
    }

    public DownloadinfoSelection sizeGt(int value) {
        addGreaterThan(DownloadinfoColumns.SIZE, value);
        return this;
    }

    public DownloadinfoSelection sizeGtEq(int value) {
        addGreaterThanOrEquals(DownloadinfoColumns.SIZE, value);
        return this;
    }

    public DownloadinfoSelection sizeLt(int value) {
        addLessThan(DownloadinfoColumns.SIZE, value);
        return this;
    }

    public DownloadinfoSelection sizeLtEq(int value) {
        addLessThanOrEquals(DownloadinfoColumns.SIZE, value);
        return this;
    }

    public DownloadinfoSelection orderBySize(boolean desc) {
        orderBy(DownloadinfoColumns.SIZE, desc);
        return this;
    }

    public DownloadinfoSelection orderBySize() {
        orderBy(DownloadinfoColumns.SIZE, false);
        return this;
    }
}
