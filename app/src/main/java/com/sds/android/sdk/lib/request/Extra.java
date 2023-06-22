package com.sds.android.sdk.lib.request;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* renamed from: com.sds.android.sdk.lib.request.d */
/* loaded from: classes.dex */
public class Extra implements Serializable {
    @SerializedName(value = "version")

    /* renamed from: a */
    private Object f2411a;
    @SerializedName(value = "all_page")

    /* renamed from: b */
    private int f2412b;
    @SerializedName(value = "curr_page")

    /* renamed from: c */
    private int f2413c;
    @SerializedName(value = "size")

    /* renamed from: d */
    private int f2414d;
    @SerializedName(value = "is_update")

    /* renamed from: e */
    private String f2415e = "";
    @SerializedName(value = "all_count")

    /* renamed from: f */
    private int f2416f;

    public Extra() {
    }

    public Extra(int i, int i2) {
        this.f2412b = i;
        this.f2416f = i2;
    }

    /* renamed from: a */
    public String m8557a() {
        return this.f2415e;
    }

    /* renamed from: b */
    public int m8556b() {
        return this.f2412b;
    }

    /* renamed from: c */
    public Object m8555c() {
        return this.f2411a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Extra extra = (Extra) obj;
        return this.f2412b == extra.f2412b && this.f2413c == extra.f2413c && this.f2414d == extra.f2414d;
    }

    public int hashCode() {
        return (((this.f2412b * 31) + this.f2413c) * 31) + this.f2414d;
    }
}
