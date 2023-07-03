package com.sds.android.sdk.lib.p060b;

import java.io.Serializable;

/* renamed from: com.sds.android.sdk.lib.b.b */
/* loaded from: classes.dex */
public class BaseResultRest<T> implements Serializable {

    /* renamed from: a */
    private String f2346a = null;

    /* renamed from: b */
    private int code;

    /* renamed from: c */
    private String content;

    /* renamed from: d */
    private int age;

    /* renamed from: b */
    public String m8681b() {
        return this.f2346a;
    }

    /* renamed from: a */
    public void m8682a(String str) {
        this.f2346a = str;
    }

    public String toString() {
        return getClass().getSimpleName() + "{mCode=" + this.code + ", mContent='" + this.content + "'}";
    }

    public BaseResultRest() {
    }

    public BaseResultRest(int code, String content) {
        this.code = code;
        this.content = content;
    }

    /* renamed from: c */
    public int getCode() {
        return this.code;
    }

    /* renamed from: d */
    public boolean isConnected() {
        return isConnected(this.code);
    }

    /* renamed from: a */
    protected boolean isConnected(int code) {
        return this.code == 200;
    }

    /* renamed from: b */
    public void setCode(int code) {
        this.code = code;
    }

    /* renamed from: e */
    public String getContent() {
        return this.content == null ? "" : this.content;
    }

    /* renamed from: b */
    public void setContent(String str) {
        this.content = str;
    }

    /* renamed from: f */
    public int getAge() {
        return this.age;
    }
}
