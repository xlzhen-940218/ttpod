package com.sds.android.ttpod.activities.ktv;


import com.google.gson.annotations.SerializedName;
import com.sds.android.sdk.lib.request.BaseResult;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.activities.ktv.h */
/* loaded from: classes.dex */
public class KtvSongListResult extends BaseResult {
    @SerializedName(value = "faildata")

    /* renamed from: a */
    private ArrayList<KtvSongInfo> f2656a = new ArrayList<>();

    /* renamed from: a */
    public ArrayList<KtvSongInfo> m8085a() {
        return this.f2656a;
    }
}
