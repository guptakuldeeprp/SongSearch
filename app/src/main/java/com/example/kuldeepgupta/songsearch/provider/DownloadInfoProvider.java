package com.example.kuldeepgupta.songsearch.provider;

// @formatter:off
import java.util.Arrays;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.kuldeepgupta.songsearch.BuildConfig;
import com.example.kuldeepgupta.songsearch.provider.base.BaseContentProvider;
import com.example.kuldeepgupta.songsearch.provider.downloadinfo.DownloadinfoColumns;
import com.example.kuldeepgupta.songsearch.provider.songinfo.SonginfoColumns;

public class DownloadInfoProvider extends BaseContentProvider {
    private static final String TAG = DownloadInfoProvider.class.getSimpleName();

    private static final boolean DEBUG = BuildConfig.DEBUG;

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "com.example.kuldeepgupta.songsearch.provider";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_DOWNLOADINFO = 0;
    private static final int URI_TYPE_DOWNLOADINFO_ID = 1;

    private static final int URI_TYPE_SONGINFO = 2;
    private static final int URI_TYPE_SONGINFO_ID = 3;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, DownloadinfoColumns.TABLE_NAME, URI_TYPE_DOWNLOADINFO);
        URI_MATCHER.addURI(AUTHORITY, DownloadinfoColumns.TABLE_NAME + "/#", URI_TYPE_DOWNLOADINFO_ID);
        URI_MATCHER.addURI(AUTHORITY, SonginfoColumns.TABLE_NAME, URI_TYPE_SONGINFO);
        URI_MATCHER.addURI(AUTHORITY, SonginfoColumns.TABLE_NAME + "/#", URI_TYPE_SONGINFO_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return DownloadInfoSQLiteOpenHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_DOWNLOADINFO:
                return TYPE_CURSOR_DIR + DownloadinfoColumns.TABLE_NAME;
            case URI_TYPE_DOWNLOADINFO_ID:
                return TYPE_CURSOR_ITEM + DownloadinfoColumns.TABLE_NAME;

            case URI_TYPE_SONGINFO:
                return TYPE_CURSOR_DIR + SonginfoColumns.TABLE_NAME;
            case URI_TYPE_SONGINFO_ID:
                return TYPE_CURSOR_ITEM + SonginfoColumns.TABLE_NAME;

        }
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        if (DEBUG) Log.d(TAG, "insert uri=" + uri + " values=" + values);
        return super.insert(uri, values);
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        if (DEBUG) Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
        return super.bulkInsert(uri, values);
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.update(uri, values, selection, selectionArgs);
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.delete(uri, selection, selectionArgs);
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (DEBUG)
            Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder
                    + " groupBy=" + uri.getQueryParameter(QUERY_GROUP_BY) + " having=" + uri.getQueryParameter(QUERY_HAVING) + " limit=" + uri.getQueryParameter(QUERY_LIMIT));
        return super.query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    protected QueryParams getQueryParams(Uri uri, String selection, String[] projection) {
        QueryParams res = new QueryParams();
        String id = null;
        int matchedId = URI_MATCHER.match(uri);
        switch (matchedId) {
            case URI_TYPE_DOWNLOADINFO:
            case URI_TYPE_DOWNLOADINFO_ID:
                res.table = DownloadinfoColumns.TABLE_NAME;
                res.idColumn = DownloadinfoColumns._ID;
                res.tablesWithJoins = DownloadinfoColumns.TABLE_NAME;
                if (SonginfoColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + SonginfoColumns.TABLE_NAME + " AS " + DownloadinfoColumns.PREFIX_SONGINFO + " ON " + DownloadinfoColumns.TABLE_NAME + "." + DownloadinfoColumns.SONGINFO_ID + "=" + DownloadinfoColumns.PREFIX_SONGINFO + "." + SonginfoColumns._ID;
                }
                res.orderBy = DownloadinfoColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_SONGINFO:
            case URI_TYPE_SONGINFO_ID:
                res.table = SonginfoColumns.TABLE_NAME;
                res.idColumn = SonginfoColumns._ID;
                res.tablesWithJoins = SonginfoColumns.TABLE_NAME;
                res.orderBy = SonginfoColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_DOWNLOADINFO_ID:
            case URI_TYPE_SONGINFO_ID:
                id = uri.getLastPathSegment();
        }
        if (id != null) {
            if (selection != null) {
                res.selection = res.table + "." + res.idColumn + "=" + id + " and (" + selection + ")";
            } else {
                res.selection = res.table + "." + res.idColumn + "=" + id;
            }
        } else {
            res.selection = selection;
        }
        return res;
    }
}
