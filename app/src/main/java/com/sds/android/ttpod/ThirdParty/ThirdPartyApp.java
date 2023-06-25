package com.sds.android.ttpod.ThirdParty;

import com.sds.android.sdk.lib.util.StringUtils;
import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.ThirdParty.c */
/* loaded from: classes.dex */
public class ThirdPartyApp implements Serializable {

    /* renamed from: a */
    private String f2486a;

    /* renamed from: b */
    private String f2487b;

    /* renamed from: c */
    private String f2488c;

    /* renamed from: d */
    private String f2489d;

    /* renamed from: e */
    private long f2490e;

    /* renamed from: f */
    private long f2491f;

    /* renamed from: a */
    public String m8326a() {
        return this.f2486a;
    }

    /* renamed from: b */
    public String m8325b() {
        return this.f2487b;
    }

    /* renamed from: c */
    public String m8324c() {
        return this.f2488c;
    }

    /* renamed from: d */
    public String m8323d() {
        return this.f2489d;
    }

    /* renamed from: e */
    public void m8322e() {
        this.f2491f = System.currentTimeMillis();
    }

    /* renamed from: f */
    public long m8321f() {
        return this.f2491f;
    }

    /* renamed from: g */
    public boolean m8320g() {
        return (StringUtils.isEmpty(this.f2487b) || StringUtils.isEmpty(this.f2488c) || StringUtils.isEmpty(this.f2489d)) ? false : true;
    }

    /* renamed from: h */
    public long m8319h() {
        return this.f2490e;
    }
}
