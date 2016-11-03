package com.example.kuldeepgupta.songsearch.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kuldeep.gupta on 10/25/2016.
 */

public class SongInfo implements Parcelable {

    private String id;
    private String title;
    private String artist;
    private int length;
    private String quality;

    public SongInfo() {

    }

    public String getFullTitle() {
        return artist + " - " + title;
    }

    // default getter and setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    protected SongInfo(Parcel in) {
        id = in.readString();
        title = in.readString();
        artist = in.readString();
        length = in.readInt();
        quality = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(artist);
        dest.writeInt(length);
        dest.writeString(quality);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SongInfo> CREATOR = new Parcelable.Creator<SongInfo>() {
        @Override
        public SongInfo createFromParcel(Parcel in) {
            return new SongInfo(in);
        }

        @Override
        public SongInfo[] newArray(int size) {
            return new SongInfo[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongInfo songInfo = (SongInfo) o;

        return id.equals(songInfo.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "SongInfo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", length=" + length +
                ", quality='" + quality + '\'' +
                '}';
    }
}
