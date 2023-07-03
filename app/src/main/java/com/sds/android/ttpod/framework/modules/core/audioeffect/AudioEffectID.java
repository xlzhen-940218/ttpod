package com.sds.android.ttpod.framework.modules.core.audioeffect;

import com.sds.android.sdk.lib.util.SecurityUtils;

/* renamed from: com.sds.android.ttpod.framework.modules.core.audioeffect.b */
/* loaded from: classes.dex */
public class AudioEffectID {

    /* renamed from: a */
    private long f5848a;

    /* renamed from: b */
    private String f5849b;

    /* renamed from: c */
    private String f5850c;

    /* renamed from: d */
    private short[] f5851d;

    /* renamed from: e */
    private int f5852e;

    /* renamed from: f */
    private int f5853f;

    /* renamed from: g */
    private int f5854g;

    /* renamed from: h */
    private int f5855h;

    /* renamed from: i */
    private float f5856i;

    /* renamed from: j */
    private boolean f5857j;

    public AudioEffectID() {
        this.f5848a = 0L;
        this.f5849b = "";
        this.f5850c = "";
        this.f5851d = new short[]{0};
        this.f5852e = 0;
        this.f5853f = 0;
        this.f5854g = 0;
        this.f5855h = 0;
        this.f5856i = 0.0f;
        this.f5857j = false;
    }

    public AudioEffectID(long j, String str, String str2, short[] sArr, int i, int i2, int i3, int i4, float f, boolean z) {
        this.f5848a = 0L;
        this.f5849b = "";
        this.f5850c = "";
        this.f5851d = new short[]{0};
        this.f5852e = 0;
        this.f5853f = 0;
        this.f5854g = 0;
        this.f5855h = 0;
        this.f5856i = 0.0f;
        this.f5857j = false;
        this.f5848a = j;
        this.f5849b = str;
        this.f5850c = str2;
        this.f5851d = sArr;
        this.f5852e = i;
        this.f5853f = i2;
        this.f5854g = i3;
        this.f5855h = i4;
        this.f5856i = f;
        this.f5857j = z;
    }

    /* renamed from: a */
    public void m4378a(long j, String str, String str2, AudioEffectCache audioEffectCache) {
        this.f5848a = j;
        this.f5849b = str;
        this.f5850c = str2;
        this.f5851d = audioEffectCache.m4382n();
        this.f5852e = audioEffectCache.m4389h();
        this.f5853f = audioEffectCache.m4387i();
        this.f5854g = audioEffectCache.m4386j();
        this.f5855h = audioEffectCache.m4385k();
        this.f5856i = audioEffectCache.m4384l();
        this.f5857j = audioEffectCache.m4383m();
    }

    /* renamed from: a */
    public String m4379a() {
        String str = "Equalizer:{";
        for (short s = 0; s < this.f5851d.length; s = (short) (s + 1)) {
            str = str + "Level" + ((int) s) + ":" + ((int) this.f5851d[s]) + ",";
        }
        return SecurityUtils.MD5Hex.stringToHex("UserId:" + this.f5848a + " SongName:" + this.f5849b + " SingerName:" + this.f5850c + " Bass:" + this.f5852e + " Treble:" + this.f5853f + " Virtualizer:" + this.f5854g + " Reverb:" + this.f5855h + " Balance:" + this.f5856i + " Islimit:" + this.f5857j + " " + (str + "}"));
    }
}
