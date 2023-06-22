package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class AlikeTTPodUser extends TTPodUser {
    private static final String KEY_SAME_SINGER_COUNT = "same_singer_num";
    private static final String KEY_SAME_SONG_COUNT = "same_song_num";
    @SerializedName(value = KEY_SAME_SINGER_COUNT)
    private int mSameSingerCount;
    @SerializedName(value = KEY_SAME_SONG_COUNT)
    private int mSameSongCount;

    public int getSameSongCount() {
        return this.mSameSongCount;
    }

    public int getSameSingerCount() {
        return this.mSameSingerCount;
    }
}
