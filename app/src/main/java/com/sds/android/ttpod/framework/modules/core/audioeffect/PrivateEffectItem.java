package com.sds.android.ttpod.framework.modules.core.audioeffect;

import com.sds.android.ttpod.media.mediastore.MediaItem;

/* renamed from: com.sds.android.ttpod.framework.modules.core.audioeffect.f */
/* loaded from: classes.dex */
public class PrivateEffectItem {

    /* renamed from: a */
    private long f5887a;

    /* renamed from: b */
    private String f5888b;

    /* renamed from: c */
    private String f5889c;

    /* renamed from: d */
    private String f5890d;

    /* renamed from: e */
    private String f5891e;

    /* renamed from: f */
    private String f5892f;

    /* renamed from: g */
    private MediaItem f5893g;

    /* renamed from: h */
    private long f5894h;

    /* renamed from: i */
    private short[] f5895i;

    /* renamed from: j */
    private int f5896j;

    /* renamed from: k */
    private int f5897k;

    /* renamed from: l */
    private int f5898l;

    public PrivateEffectItem(String str, AudioEffectCache audioEffectCache) {
        this.f5887a = 0L;
        this.f5895i = new short[]{10};
        this.f5896j = 0;
        this.f5897k = 0;
        this.f5898l = 0;
        this.f5887a = audioEffectCache.m4408b().longValue();
        this.f5888b = audioEffectCache.m4401d() + " - " + audioEffectCache.m4404c();
        this.f5889c = audioEffectCache.m4392g();
        this.f5890d = EqualizerPreset.m4336a(audioEffectCache.m4398e());
        this.f5891e = str;
        this.f5892f = audioEffectCache.m4380p();
        this.f5895i = audioEffectCache.m4382n();
        this.f5896j = audioEffectCache.m4389h();
        this.f5897k = audioEffectCache.m4387i();
        this.f5898l = audioEffectCache.m4386j();
    }

    /* renamed from: a */
    public String m4332a() {
        return this.f5888b;
    }

    /* renamed from: b */
    public String m4329b() {
        return this.f5889c;
    }

    /* renamed from: c */
    public String m4328c() {
        return this.f5891e;
    }

    /* renamed from: d */
    public String m4327d() {
        return this.f5892f;
    }

    /* renamed from: e */
    public long m4326e() {
        return this.f5887a;
    }

    /* renamed from: f */
    public MediaItem m4325f() {
        return this.f5893g;
    }

    /* renamed from: a */
    public void m4330a(MediaItem mediaItem) {
        this.f5893g = mediaItem;
    }

    /* renamed from: g */
    public long m4324g() {
        return this.f5894h;
    }

    /* renamed from: a */
    public void m4331a(long j) {
        this.f5894h = j;
    }

    /* renamed from: h */
    public short[] m4323h() {
        return this.f5895i;
    }

    /* renamed from: i */
    public int m4322i() {
        return this.f5896j;
    }

    /* renamed from: j */
    public int m4321j() {
        return this.f5897k;
    }

    /* renamed from: k */
    public int m4320k() {
        return this.f5898l;
    }
}
