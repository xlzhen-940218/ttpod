package com.sds.android.ttpod.framework.modules.skin.p132d;

import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.s */
/* loaded from: classes.dex */
public final class TrcTimeRegion implements Serializable {

    /* renamed from: a */
    private final int f6642a;

    /* renamed from: b */
    private final int f6643b;

    /* renamed from: c */
    private int f6644c;

    /* renamed from: a */
    public int m3606a() {
        return this.f6644c;
    }

    /* renamed from: a */
    public void m3605a(int i) {
        this.f6644c = i;
    }

    public TrcTimeRegion(int i, int i2) {
        this.f6642a = i;
        this.f6643b = i2 < 1 ? 1 : i2;
    }

    public String toString() {
        return "TrcTimeRegion [mCharsCount=" + this.f6642a + ", mDuration=" + this.f6643b + ", mTextWidth=" + this.f6644c + "]";
    }

    /* renamed from: b */
    public int m3604b() {
        return this.f6642a;
    }

    /* renamed from: c */
    public int m3603c() {
        return this.f6643b;
    }
}
