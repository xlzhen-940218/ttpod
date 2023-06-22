package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class MusicCircleFirstPublishMore {
    private static final String KEY_ALBUMS = "albums";
    private static final String KEY_SINGLES = "singles";
    @SerializedName(value = "albums")
    private ArrayList<WeekAlbum> mAlbumList;
    @SerializedName(value = KEY_SINGLES)
    private ArrayList<MusicCircleFirstPublish> mSingleList;

    /* loaded from: classes.dex */
    public static class WeekAlbum {
        private static final String KEY_ALBUM = "album";
        private static final String KEY_WEEK = "week";
        private static final String KEY_YEAR = "year";
        @SerializedName(value = KEY_WEEK)
        private int mWeek;
        @SerializedName(value = "album")
        private ArrayList<MusicCircleFirstPublish> mWeekAlbumList;
        @SerializedName(value = "year")
        private int mYear;

        public int getYear() {
            return this.mYear;
        }

        public int getWeek() {
            return this.mWeek;
        }

        public ArrayList<MusicCircleFirstPublish> getWeekAlbumList() {
            return this.mWeekAlbumList;
        }
    }

    public ArrayList<MusicCircleFirstPublish> getSingleList() {
        return this.mSingleList;
    }

    public ArrayList<WeekAlbum> getAlbumList() {
        return this.mAlbumList;
    }
}
