package com.example.kuldeepgupta.songsearch.provider;

// @formatter:off
import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.kuldeepgupta.songsearch.BuildConfig;
import com.example.kuldeepgupta.songsearch.provider.downloadinfo.DownloadinfoColumns;
import com.example.kuldeepgupta.songsearch.provider.songinfo.SonginfoColumns;

public class DownloadInfoSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = DownloadInfoSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "songsearch.db";
    private static final int DATABASE_VERSION = 1;
    private static DownloadInfoSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final DownloadInfoSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    public static final String SQL_CREATE_TABLE_DOWNLOADINFO = "CREATE TABLE IF NOT EXISTS "
            + DownloadinfoColumns.TABLE_NAME + " ( "
            + DownloadinfoColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DownloadinfoColumns.SONGINFO_ID + " INTEGER NOT NULL, "
            + DownloadinfoColumns.DOWNLOAD_LINK + " TEXT NOT NULL, "
            + DownloadinfoColumns.FILE_NAME + " TEXT NOT NULL, "
            + DownloadinfoColumns.SIZE + " INTEGER NOT NULL "
            + ", CONSTRAINT fk_songinfo_id FOREIGN KEY (" + DownloadinfoColumns.SONGINFO_ID + ") REFERENCES songinfo (_id) ON DELETE CASCADE"
            + " );";

    public static final String SQL_CREATE_TABLE_SONGINFO = "CREATE TABLE IF NOT EXISTS "
            + SonginfoColumns.TABLE_NAME + " ( "
            + SonginfoColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SonginfoColumns.ID + " TEXT NOT NULL, "
            + SonginfoColumns.TITLE + " TEXT NOT NULL, "
            + SonginfoColumns.ARTIST + " TEXT, "
            + SonginfoColumns.LENGTH + " INTEGER, "
            + SonginfoColumns.QUALITY + " TEXT "
            + " );";


    public static DownloadInfoSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static DownloadInfoSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static DownloadInfoSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new DownloadInfoSQLiteOpenHelper(context);
    }

    private DownloadInfoSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new DownloadInfoSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static DownloadInfoSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new DownloadInfoSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private DownloadInfoSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new DownloadInfoSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_DOWNLOADINFO);
        db.execSQL(SQL_CREATE_TABLE_SONGINFO);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
