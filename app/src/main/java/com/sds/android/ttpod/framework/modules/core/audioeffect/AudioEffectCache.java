package com.sds.android.ttpod.framework.modules.core.audioeffect;


import com.google.gson.annotations.SerializedName;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.ttpod.media.mediastore.old.MediaStoreOld;

import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.framework.modules.core.audioeffect.a */
/* loaded from: classes.dex */
public class AudioEffectCache implements Serializable {
    @SerializedName(value = "_ID")

    /* renamed from: a */
    private String f5827a = "";
    @SerializedName(value = "song_id")

    /* renamed from: b */
    private long f5828b = 0;
    @SerializedName(value = "artist")

    /* renamed from: c */
    private String f5829c = "";
    @SerializedName(value = "title")

    /* renamed from: d */
    private String f5830d = "";
    @SerializedName(value = "style")

    /* renamed from: e */
    private int f5831e = 0;
    @SerializedName(value = "output")

    /* renamed from: f */
    private int f5832f = 0;
    @SerializedName(value = "device")

    /* renamed from: g */
    private String f5833g = "";
    @SerializedName(value = "total")

    /* renamed from: h */
    private int f5834h = 0;
    @SerializedName(value = "pick_cout")

    /* renamed from: i */
    private int f5835i = 0;
    @SerializedName(value = "nickname")

    /* renamed from: j */
    private String f5836j = "";
    @SerializedName(value = User.KEY_AVATAR)

    /* renamed from: k */
    private String f5837k = "";
    @SerializedName(value = "user_id")

    /* renamed from: l */
    private long f5838l = 0;
    @SerializedName(value = "bass")

    /* renamed from: m */
    private int f5839m = 0;
    @SerializedName(value = "treble")

    /* renamed from: n */
    private int f5840n = 0;
    @SerializedName(value = "virtualizer")

    /* renamed from: o */
    private int f5841o = 0;
    @SerializedName(value = "reverb")

    /* renamed from: p */
    private int f5842p = 0;
    @SerializedName(value = "balance")

    /* renamed from: q */
    private float f5843q = 0.0f;
    @SerializedName(value = "limit")

    /* renamed from: r */
    private boolean f5844r = false;
    @SerializedName(value = "equalizer")

    /* renamed from: s */
    private short[] f5845s = {0};
    @SerializedName(value = "time")

    /* renamed from: t */
    private long f5846t = 0;
    @SerializedName(value = "media_path")

    /* renamed from: u */
    private String f5847u = "";

    /* renamed from: a */
    public void m4411a(String str) {
        this.f5827a = str;
    }

    /* renamed from: a */
    public String m4416a() {
        return this.f5827a;
    }

    /* renamed from: a */
    public void m4412a(Long l) {
        this.f5828b = l.longValue();
    }

    /* renamed from: b */
    public Long m4408b() {
        return Long.valueOf(this.f5828b);
    }

    /* renamed from: b */
    public void m4405b(String str) {
        this.f5829c = str;
    }

    /* renamed from: c */
    public String m4404c() {
        return this.f5829c;
    }

    /* renamed from: c */
    public void m4402c(String str) {
        this.f5830d = str;
    }

    /* renamed from: d */
    public String m4401d() {
        return this.f5830d;
    }

    /* renamed from: a */
    public void m4414a(int i) {
        this.f5831e = i;
    }

    /* renamed from: e */
    public int m4398e() {
        return this.f5831e;
    }

    /* renamed from: b */
    public void m4407b(int i) {
        this.f5832f = i;
    }

    /* renamed from: f */
    public int m4395f() {
        return this.f5832f;
    }

    /* renamed from: d */
    public void m4399d(String str) {
        this.f5833g = str;
    }

    /* renamed from: c */
    public void m4403c(int i) {
        this.f5834h = i;
    }

    /* renamed from: d */
    public void m4400d(int i) {
        this.f5835i = i;
    }

    /* renamed from: e */
    public void m4396e(String str) {
        this.f5837k = str;
    }

    /* renamed from: f */
    public void m4393f(String str) {
        this.f5836j = str;
    }

    /* renamed from: g */
    public String m4392g() {
        return this.f5836j;
    }

    /* renamed from: a */
    public void m4413a(long j) {
        this.f5838l = j;
    }

    /* renamed from: e */
    public void m4397e(int i) {
        this.f5839m = i;
    }

    /* renamed from: h */
    public int m4389h() {
        return this.f5839m;
    }

    /* renamed from: f */
    public void m4394f(int i) {
        this.f5840n = i;
    }

    /* renamed from: i */
    public int m4387i() {
        return this.f5840n;
    }

    /* renamed from: g */
    public void m4391g(int i) {
        this.f5841o = i;
    }

    /* renamed from: j */
    public int m4386j() {
        return this.f5841o;
    }

    /* renamed from: h */
    public void m4388h(int i) {
        this.f5842p = i;
    }

    /* renamed from: k */
    public int m4385k() {
        return this.f5842p;
    }

    /* renamed from: a */
    public void m4415a(float f) {
        this.f5843q = f;
    }

    /* renamed from: l */
    public float m4384l() {
        return this.f5843q;
    }

    /* renamed from: a */
    public void m4410a(boolean z) {
        this.f5844r = z;
    }

    /* renamed from: m */
    public boolean m4383m() {
        return this.f5844r;
    }

    /* renamed from: a */
    public void m4409a(short[] sArr) {
        this.f5845s = sArr;
    }

    /* renamed from: n */
    public short[] m4382n() {
        return this.f5845s;
    }

    /* renamed from: b */
    public void m4406b(long j) {
        this.f5846t = j;
    }

    /* renamed from: o */
    public long m4381o() {
        return this.f5846t;
    }

    /* renamed from: g */
    public void m4390g(String str) {
        this.f5847u = str;
    }

    /* renamed from: p */
    public String m4380p() {
        return this.f5847u;
    }
}
