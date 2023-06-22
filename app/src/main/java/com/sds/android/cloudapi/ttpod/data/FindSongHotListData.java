package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class FindSongHotListData extends RecommendData implements Serializable {
    @SerializedName(value = "author")
    private String mAuthor = "";
    @SerializedName(value = "listen_count")
    private long mListenCount;
    @SerializedName(value = "rec_alg")
    private int mRecommentAlgorithm;
    @SerializedName(value = "rec_type")
    private int mRecommentType;

    public String getAuthor() {
        return this.mAuthor;
    }

    public long getListenCount() {
        return this.mListenCount;
    }

    public int getRecommentType() {
        return this.mRecommentType;
    }

    public int getRecommentAlgorithm() {
        return this.mRecommentAlgorithm;
    }
}
