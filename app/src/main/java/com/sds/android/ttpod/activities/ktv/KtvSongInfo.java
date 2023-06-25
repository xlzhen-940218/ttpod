package com.sds.android.ttpod.activities.ktv;

import com.google.gson.JsonObject;

import com.google.gson.annotations.SerializedName;
import com.sds.android.sdk.lib.util.StringUtils;

/* renamed from: com.sds.android.ttpod.activities.ktv.g */
/* loaded from: classes.dex */
public class KtvSongInfo {
    @SerializedName(value = "musicname")

    /* renamed from: a */
    private String musicname;
    @SerializedName(value = "singername")

    /* renamed from: b */
    private String singername;
    @SerializedName(value = "musicno")

    /* renamed from: c */
    private String musicno;

    public KtvSongInfo(String str, String str2) {
        this.musicname = str;
        this.singername = str2;
    }

    public String toString() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("musicName", this.musicname);
        jsonObject.addProperty("singerName", this.singername);
        if (!StringUtils.isEmpty(this.musicno)) {
            jsonObject.addProperty("musicno", this.musicno);
        }
        return jsonObject.toString();
    }
}
