package com.sds.android.ttpod.framework.support.search.p135a;

import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.framework.support.search.SearchTaskType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.io.File;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.support.search.a.a */
/* loaded from: classes.dex */
public abstract class LyrPicSearchTaskBaseInfo {

    /* renamed from: c */
    private String f7274c;

    /* renamed from: d */
    private String f7275d;

    /* renamed from: e */
    private boolean f7276e;

    /* renamed from: l */
    private boolean f7283l;

    /* renamed from: m */
    private boolean f7284m;

    /* renamed from: a */
    private List f7272a = null;

    /* renamed from: b */
    private Object f7273b = null;

    /* renamed from: f */
    private boolean f7277f = false;

    /* renamed from: g */
    private String f7278g = "";

    /* renamed from: h */
    private String f7279h = "";

    /* renamed from: i */
    private int f7280i = EnvironmentUtils.DeviceConfig.m8476d();

    /* renamed from: k */
    private MediaItem f7282k = null;

    /* renamed from: j */
    private String[] f7281j = null;

    /* renamed from: d */
    public abstract SearchTaskType mo2186d();

    /* renamed from: a */
    public boolean m2211a() {
        return this.f7284m;
    }

    /* renamed from: a */
    public void m2207a(boolean z) {
        this.f7284m = z;
    }

    /* renamed from: b */
    public boolean m2206b() {
        return this.f7276e;
    }

    /* renamed from: b */
    public void m2204b(boolean z) {
        this.f7276e = z;
    }

    /* renamed from: c */
    public boolean m2203c() {
        return this.f7283l;
    }

    /* renamed from: c */
    public void m2201c(boolean z) {
        this.f7283l = z;
    }

    /* renamed from: e */
    public boolean m2198e() {
        return this.f7277f;
    }

    /* renamed from: d */
    public void m2199d(boolean z) {
        this.f7277f = z;
    }

    /* renamed from: f */
    public String m2197f() {
        return this.f7278g;
    }

    /* renamed from: a */
    public void m2208a(String str) {
        this.f7278g = str;
    }

    /* renamed from: g */
    public String m2196g() {
        return this.f7279h;
    }

    /* renamed from: b */
    public void m2205b(String str) {
        this.f7279h = str;
    }

    /* renamed from: h */
    public String[] m2195h() {
        if (this.f7281j == null) {
            this.f7281j = m2191l();
        }
        return this.f7281j;
    }

    /* renamed from: i */
    public MediaItem m2194i() {
        return this.f7282k;
    }

    /* renamed from: a */
    public void m2210a(MediaItem mediaItem) {
        this.f7282k = mediaItem;
    }

    /* renamed from: j */
    public String m2193j() {
        return this.f7274c;
    }

    /* renamed from: c */
    public void m2202c(String str) {
        this.f7274c = str;
    }

    /* renamed from: k */
    public String m2192k() {
        return this.f7275d;
    }

    /* renamed from: d */
    public void m2200d(String str) {
        this.f7275d = str;
    }

    /* renamed from: a */
    public void m2209a(Object obj) {
        this.f7273b = obj;
    }

    /* renamed from: l */
    private String[] m2191l() {
        String localDataSource;
        String str;
        String str2;
        String str3;
        String str4 = null;
        if (this.f7282k == null || this.f7282k.getLocalDataSource() == null) {
            return null;
        }
        int lastIndexOf = this.f7282k.getLocalDataSource().lastIndexOf(124);
        if (lastIndexOf > 0) {
            int i = lastIndexOf + 1;
            localDataSource = this.f7282k.getLocalDataSource().substring(0, lastIndexOf);
            str4 = this.f7282k.getLocalDataSource().substring(i);
        } else {
            localDataSource = this.f7282k.getLocalDataSource();
        }
        String str5 = "";
        if (localDataSource == null) {
            str = localDataSource;
            str2 = "";
        } else {
            int lastIndexOf2 = localDataSource.lastIndexOf(File.separatorChar) + 1;
            if (lastIndexOf2 > 0) {
                String substring = localDataSource.substring(lastIndexOf2);
                str3 = localDataSource.substring(0, lastIndexOf2);
                localDataSource = substring;
            } else {
                str3 = localDataSource;
            }
            int lastIndexOf3 = localDataSource.lastIndexOf(46);
            if (lastIndexOf3 <= 0) {
                str = str3;
                str5 = localDataSource;
                str2 = "";
            } else {
                String substring2 = localDataSource.substring(lastIndexOf3 + 1);
                str = str3;
                str5 = localDataSource.substring(0, lastIndexOf3);
                str2 = substring2;
            }
        }
        return new String[]{str, str5, str2, str4};
    }
}
