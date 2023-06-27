package com.sds.android.cloudapi.ttpod.api;

/* renamed from: com.sds.android.cloudapi.ttpod.a.aa */
/* loaded from: classes.dex */
public final class UrlList {

    /* renamed from: a */
    public static final String recommend = getHost() + "/recomm";

    /* renamed from: b */
    public static final String moduleCategory = getHost() + "/module/category";

    /* renamed from: c */
    public static final String moduleSubCategory = getHost() + "/category/sub_category";

    /* renamed from: a */
    public static String getHost() {
        return "http://127.0.0.1:18098";
    }
}
