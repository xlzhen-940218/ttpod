package com.sds.android.ttpod.activities.ktv;


import com.google.gson.annotations.SerializedName;
import com.sds.android.sdk.lib.request.BaseResult;

/* renamed from: com.sds.android.ttpod.activities.ktv.e */
/* loaded from: classes.dex */
public class KtvResult extends BaseResult {
    @SerializedName(value = "checkcode")

    /* renamed from: a */
    private String f2639a;
    @SerializedName(value = "roominfo")

    /* renamed from: b */
    private String f2640b;
    @SerializedName(value = "requesturl")

    /* renamed from: c */
    private String f2641c;

    /* renamed from: a */
    public String m8121a() {
        return this.f2639a;
    }

    /* renamed from: b */
    public String m8120b() {
        return this.f2641c;
    }

    /* renamed from: c */
    public String m8119c() {
        return this.f2640b;
    }
}
