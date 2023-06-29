package com.sds.android.ttpod.framework.modules.skin.category;


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
    private ArrayList<CategoryItem> data;
    @SerializedName(value = "ctime")

    /* renamed from: b */
    private long ctime;
    @SerializedName(value = "extra")

    /* renamed from: c */
    private OnlineSkinListResult.OnlineThemeListExtra extra;

    /* renamed from: a */
    public ArrayList<CategoryItem> getData() {
        return this.data;
    }

    /* renamed from: b */
    public long getCTime() {
        return this.ctime;
    }

    /* renamed from: c */
    public String getPicUrl() {
        return this.extra.getPicUrl();
    }
}
