package com.sds.android.ttpod.share;

import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.share.d */
/* loaded from: classes.dex */
public class ShareInfo implements Serializable {

    /* renamed from: a */
    private String f7434a;

    /* renamed from: b */
    private String f7435b;

    /* renamed from: c */
    private String f7436c;

    /* renamed from: d */
    private String f7437d;

    /* renamed from: e */
    private String f7438e;

    /* renamed from: f */
    private Long f7439f;

    /* renamed from: g */
    private long f7440g;

    /* renamed from: h */
    private boolean f7441h;

    /* renamed from: i */
    private String f7442i;

    /* renamed from: j */
    private String f7443j;

    /* renamed from: k */
    private boolean f7444k;

    /* renamed from: l */
    private String f7445l;

    /* renamed from: m */
    private boolean f7446m;

    /* renamed from: n */
    private String f7447n;

    /* renamed from: o */
    private String f7448o;

    /* renamed from: p */
    private long f7449p;

    /* renamed from: q */
    private long f7450q;

    public ShareInfo() {
        this.f7434a = "";
        this.f7435b = "";
        this.f7436c = "";
        this.f7437d = "";
        this.f7438e = "";
        this.f7439f = 0L;
        this.f7440g = 0L;
        this.f7442i = "";
        this.f7443j = "";
        this.f7445l = "";
        this.f7447n = "";
        this.f7448o = "";
    }

    public ShareInfo(String str, String str2, Long l) {
        this.f7434a = "";
        this.f7435b = "";
        this.f7436c = "";
        this.f7437d = "";
        this.f7438e = "";
        this.f7439f = 0L;
        this.f7440g = 0L;
        this.f7442i = "";
        this.f7443j = "";
        this.f7445l = "";
        this.f7447n = "";
        this.f7448o = "";
        this.f7437d = str;
        this.f7438e = str2;
        this.f7444k = true;
        m1971a(l);
    }

    /* renamed from: a */
    public String m1973a() {
        return this.f7445l;
    }

    /* renamed from: a */
    public void m1970a(String str) {
        this.f7445l = str;
    }

    /* renamed from: b */
    public boolean m1968b() {
        return this.f7444k;
    }

    /* renamed from: a */
    public void m1969a(boolean z) {
        this.f7444k = z;
    }

    /* renamed from: c */
    public String m1964c() {
        return this.f7435b;
    }

    /* renamed from: b */
    public void m1966b(String str) {
        this.f7435b = str;
    }

    /* renamed from: d */
    public String m1960d() {
        return this.f7434a == null ? "" : this.f7434a;
    }

    /* renamed from: c */
    public void m1962c(String str) {
        this.f7434a = str;
    }

    /* renamed from: e */
    public String m1958e() {
        return this.f7436c;
    }

    /* renamed from: d */
    public void m1959d(String str) {
        this.f7436c = str;
    }

    /* renamed from: f */
    public String m1956f() {
        return this.f7437d;
    }

    /* renamed from: e */
    public void m1957e(String str) {
        this.f7437d = str;
    }

    /* renamed from: g */
    public String m1954g() {
        return this.f7438e;
    }

    /* renamed from: h */
    public Long m1952h() {
        return this.f7439f;
    }

    /* renamed from: a */
    public void m1971a(Long l) {
        if (l == null) {
            this.f7439f = 0L;
        } else {
            this.f7439f = l;
        }
    }

    /* renamed from: i */
    public long m1950i() {
        return this.f7440g;
    }

    /* renamed from: a */
    public void m1972a(long j) {
        this.f7440g = j;
    }

    /* renamed from: j */
    public boolean m1948j() {
        return this.f7441h;
    }

    /* renamed from: b */
    public void m1965b(boolean z) {
        this.f7441h = z;
    }

    /* renamed from: k */
    public String m1947k() {
        return this.f7442i;
    }

    /* renamed from: f */
    public void m1955f(String str) {
        this.f7442i = str;
    }

    /* renamed from: l */
    public String m1946l() {
        return this.f7443j;
    }

    /* renamed from: g */
    public void m1953g(String str) {
        this.f7443j = str;
    }

    /* renamed from: m */
    public long m1945m() {
        return this.f7450q;
    }

    /* renamed from: b */
    public void m1967b(long j) {
        this.f7450q = j;
    }

    /* renamed from: n */
    public long m1944n() {
        return this.f7449p;
    }

    /* renamed from: c */
    public void m1963c(long j) {
        this.f7449p = j;
    }

    /* renamed from: o */
    public String m1943o() {
        return this.f7447n;
    }

    /* renamed from: h */
    public void m1951h(String str) {
        this.f7447n = str;
    }

    /* renamed from: i */
    public void m1949i(String str) {
        this.f7448o = str;
    }

    /* renamed from: p */
    public boolean m1942p() {
        return this.f7446m;
    }

    /* renamed from: c */
    public void m1961c(boolean z) {
        this.f7446m = z;
    }

    public String toString() {
        return "ShareInfo{mImageUrl='" + this.f7434a + "', mMessage='" + this.f7436c + "'}";
    }
}
