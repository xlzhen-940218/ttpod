package com.sds.android.ttpod.framework.modules.skin.p128a;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.a.a */
/* loaded from: classes.dex */
public class CategoryItem implements Serializable, Comparable<CategoryItem> {
    @SerializedName(value = "id")

    /* renamed from: a */
    private int f6392a;
    @SerializedName(value = "name")

    /* renamed from: b */
    private String f6393b;
    @SerializedName(value = "orderNum")

    /* renamed from: c */
    private int f6394c;
    @SerializedName(value = "recommendPicUrl")

    /* renamed from: d */
    private String f6395d;

    /* renamed from: e */
    private String f6396e;

    /* renamed from: a */
    public int m3867a() {
        return this.f6392a;
    }

    /* renamed from: b */
    public String m3863b() {
        return this.f6393b;
    }

    /* renamed from: c */
    public int m3862c() {
        return this.f6394c;
    }

    /* renamed from: d */
    public String m3861d() {
        return this.f6395d;
    }

    /* renamed from: e */
    public String m3860e() {
        return this.f6396e;
    }

    /* renamed from: a */
    public void m3864a(String str) {
        this.f6396e = str;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(CategoryItem categoryItem) {
        return m3862c() - categoryItem.m3862c();
    }
}
