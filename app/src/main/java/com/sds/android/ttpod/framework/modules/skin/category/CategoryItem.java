package com.sds.android.ttpod.framework.modules.skin.category;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.a.a */
/* loaded from: classes.dex */
public class CategoryItem implements Serializable, Comparable<CategoryItem> {
    @SerializedName(value = "id")

    /* renamed from: a */
    private int id;
    @SerializedName(value = "name")

    /* renamed from: b */
    private String name;
    @SerializedName(value = "orderNum")

    /* renamed from: c */
    private int orderNum;
    @SerializedName(value = "recommendPicUrl")

    /* renamed from: d */
    private String recommendPicUrl;

    /* renamed from: e */
    private String picUrl;

    /* renamed from: a */
    public int getId() {
        return this.id;
    }

    /* renamed from: b */
    public String getName() {
        return this.name;
    }

    /* renamed from: c */
    public int getOrderNum() {
        return this.orderNum;
    }

    /* renamed from: d */
    public String getRecommendPicUrl() {
        return this.recommendPicUrl;
    }

    /* renamed from: e */
    public String getPicUrl() {
        return this.picUrl;
    }

    /* renamed from: a */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(CategoryItem categoryItem) {
        return getOrderNum() - categoryItem.getOrderNum();
    }
}
