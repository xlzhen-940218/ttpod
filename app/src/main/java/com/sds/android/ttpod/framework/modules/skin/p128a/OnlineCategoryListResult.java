package com.sds.android.ttpod.framework.modules.skin.p128a;


import com.google.gson.annotations.SerializedName;
import com.sds.android.cloudapi.ttpod.result.OnlineSkinListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import java.io.Serializable;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.a.b */
/* loaded from: classes.dex */
public class OnlineCategoryListResult extends BaseResult implements Serializable {
    @SerializedName(value = "data")

    /* renamed from: a */
    private ArrayList<CategoryItem> f6397a;
    @SerializedName(value = "ctime")

    /* renamed from: b */
    private long f6398b;
    @SerializedName(value = "extra")

    /* renamed from: c */
    private OnlineSkinListResult.OnlineThemeListExtra f6399c;

    /* renamed from: a */
    public ArrayList<CategoryItem> m3859a() {
        return this.f6397a;
    }

    /* renamed from: b */
    public long m3858b() {
        return this.f6398b;
    }

    /* renamed from: c */
    public String m3857c() {
        return this.f6399c.getPicUrl();
    }
}
