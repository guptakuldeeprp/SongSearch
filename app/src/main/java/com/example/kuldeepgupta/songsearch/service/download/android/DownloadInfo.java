package com.example.kuldeepgupta.songsearch.service.download.android;

/**
 * Created by kuldeep.gupta on 10/26/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.kuldeepgupta.songsearch.domain.SongInfo;


public class DownloadInfo implements Parcelable {
    private final static String TAG = DownloadInfo.class.getSimpleName();


    public enum DownloadState {
        ERROR,
        NOT_STARTED,
        QUEUED,
        DOWNLOADING,
        COMPLETE
    }

    private volatile DownloadState mDownloadState = DownloadState.NOT_STARTED;
    private SongInfo mSongInfo;
    private String mDownloadLink;
    private String mFilename;
    private volatile Integer mProgress;
    private int mSize;
    private volatile ProgressBar mProgressBar;

    public DownloadInfo() {

    }

    public DownloadInfo(SongInfo songInfo, String downloadLink /*, String filename */) {
        this.mSongInfo = songInfo;
        mFilename = null;
        mProgress = 0;
        mProgressBar = null;
        mDownloadLink = downloadLink;
    }

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        //Log.d(TAG, "setProgressBar " + mFilename + " to " + progressBar);
        mProgressBar = progressBar;
    }

    public void setDownloadState(DownloadState state) {
        mDownloadState = state;
    }

    public DownloadState getDownloadState() {
        return mDownloadState;
    }

    public Integer getProgress() {
        return mProgress;
    }

    public void setProgress(Integer progress) {
        this.mProgress = progress;
    }

//    public String getFilename() {
//        return mFilename;
//    }


    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        this.mSize = size;
    }

    public String getDownloadLink() {
        return mDownloadLink;
    }

    public SongInfo getSongInfo() {
        return mSongInfo;
    }

    public String getFilename() {
        return mFilename;
    }

    public void setFilename(String mFilename) {
        this.mFilename = mFilename;
    }

    public void setDownloadLink(String mDownloadLink) {
        this.mDownloadLink = mDownloadLink;
    }

    public void setSongInfo(SongInfo mSongInfo) {
        this.mSongInfo = mSongInfo;
    }

    protected DownloadInfo(Parcel in) {
        mDownloadState = (DownloadState) in.readValue(DownloadState.class.getClassLoader());
        mSongInfo = (SongInfo) in.readValue(SongInfo.class.getClassLoader());
        mDownloadLink = in.readString();
        mFilename = in.readString();
        mProgress = in.readByte() == 0x00 ? null : in.readInt();
        mSize = in.readInt();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mDownloadState);
        dest.writeValue(mSongInfo);
        dest.writeString(mDownloadLink);
        dest.writeString(mFilename);
        if (mProgress == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(mProgress);
        }
        dest.writeInt(mSize);

    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DownloadInfo> CREATOR = new Parcelable.Creator<DownloadInfo>() {
        @Override
        public DownloadInfo createFromParcel(Parcel in) {
            return new DownloadInfo(in);
        }

        @Override
        public DownloadInfo[] newArray(int size) {
            return new DownloadInfo[size];
        }
    };
}