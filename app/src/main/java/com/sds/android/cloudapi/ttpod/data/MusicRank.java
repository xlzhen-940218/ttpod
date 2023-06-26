package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class MusicRank extends FindSongCommonCategory implements Serializable {
    @SerializedName(value = "big_pic_url")
    private String mBigPicUrl;
    @SerializedName(value = "songlist")
    private ArrayList<SimpleSongInfo> mSongList;

    public void setBigPicUrl(String mBigPicUrl) {
        this.mBigPicUrl = mBigPicUrl;
    }

    public ArrayList<SimpleSongInfo> getSongList() {
        return this.mSongList;
    }

    public void setSongList(ArrayList<SimpleSongInfo> arrayList) {
        this.mSongList = arrayList;
    }

    /* loaded from: classes.dex */
    public static class SimpleSongInfo implements Serializable {
        @SerializedName(value = "singerName")
        private String mSingerName;
        @SerializedName(value = "songName")
        private String mSongName;

        public SimpleSongInfo() {
        }

        public String getSingerName() {
            return this.mSingerName;
        }

        public void setSingerName(String str) {
            this.mSingerName = str;
        }

        public String getSongName() {
            return this.mSongName;
        }

        public void setSongName(String str) {
            this.mSongName = str;
        }
    }

    public String getBigPicUrl() {
        return this.mBigPicUrl;
    }
}
