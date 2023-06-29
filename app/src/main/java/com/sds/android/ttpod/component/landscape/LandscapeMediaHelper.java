package com.sds.android.ttpod.component.landscape;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.TypedValue;

import com.sds.android.ttpod.component.landscape.p098b.Scene;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.lyric.Lyric;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;

/* renamed from: com.sds.android.ttpod.component.landscape.d */
/* loaded from: classes.dex */
public class LandscapeMediaHelper implements Scheduler.InterfaceC1275a {

    /* renamed from: b */
    private boolean f4559b;

    /* renamed from: c */
    private boolean f4560c;

    /* renamed from: d */
    private MediaItem f4561d;

    /* renamed from: e */
    private boolean f4562e;

    /* renamed from: h */
    private boolean f4565h;

    /* renamed from: i */
    private Bitmap f4566i;

    /* renamed from: j */
    private boolean f4567j;

    /* renamed from: k */
    private long f4568k;

    /* renamed from: l */
    private LyricProvider f4569l;

    /* renamed from: a */
    private int[] f4558a = new int[64];

    /* renamed from: g */
    private int f4564g = -1;

    /* renamed from: f */
    private int f4563f = 12;

    /* compiled from: LandscapeMediaHelper.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.d$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1265a {
        /* renamed from: d */
        void mo6148d();
    }

    /* compiled from: LandscapeMediaHelper.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.d$b */
    /* loaded from: classes.dex */
    public interface InterfaceC1266b {
        /* renamed from: a */
        void mo6144a(String str, String str2);
    }

    /* compiled from: LandscapeMediaHelper.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.d$c */
    /* loaded from: classes.dex */
    public interface InterfaceC1267c {
        /* renamed from: a */
        void mo6139a(int i);
    }

    /* compiled from: LandscapeMediaHelper.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.d$d */
    /* loaded from: classes.dex */
    public interface InterfaceC1268d {
        /* renamed from: a */
        void mo6136a(Bitmap bitmap);
    }

    /* compiled from: LandscapeMediaHelper.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.d$e */
    /* loaded from: classes.dex */
    public interface InterfaceC1269e {
        /* renamed from: a */
        void mo6135a(int[] iArr);
    }

    public LandscapeMediaHelper(Context context) {
        this.f4569l = new LyricProvider(context, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, LandscapeData.m6332d(), context.getResources().getDisplayMetrics()), LandscapeData.m6334b());
        Scheduler.m6112a().m6109a(this, 10);
    }

    /* renamed from: a */
    public void m6157a(MediaItem mediaItem, int i) {
        if (mediaItem != null) {
            synchronized (this) {
                this.f4559b = true;
                this.f4560c = true;
                this.f4561d = mediaItem;
                this.f4562e = true;
                if (this.f4564g != -1) {
                    if (i == this.f4564g) {
                        this.f4563f = 12;
                    } else if (i > this.f4564g) {
                        this.f4563f = 11;
                    } else {
                        this.f4563f = 10;
                    }
                }
                this.f4564g = i;
            }
        }
    }

    /* renamed from: a */
    public void m6159a(Bitmap bitmap) {
        synchronized (this) {
            if (bitmap != this.f4566i) {
                this.f4565h = true;
                this.f4566i = bitmap;
            }
        }
    }

    /* renamed from: a */
    public void m6158a(Lyric lyric) {
        this.f4569l.m6126a(lyric);
    }

    /* renamed from: a */
    public void m6160a(long j) {
        synchronized (this) {
            this.f4567j = true;
            this.f4568k = j;
        }
    }

    @Override // com.sds.android.ttpod.component.landscape.Scheduler.InterfaceC1275a
    /* renamed from: a */
    public void mo6105a(float f) {
        Scene m6118b = SceneManager.m6121a().m6118b();
        synchronized (this) {
            if (this.f4559b && (m6118b instanceof InterfaceC1265a)) {
                ((InterfaceC1265a) m6118b).mo6148d();
                this.f4559b = false;
            }
            if (this.f4560c && (m6118b instanceof InterfaceC1266b)) {
                ((InterfaceC1266b) m6118b).mo6144a(this.f4561d.getTitle(), this.f4561d.getArtist());
                this.f4560c = false;
            }
            if (this.f4562e && (m6118b instanceof InterfaceC1267c)) {
                ((InterfaceC1267c) m6118b).mo6139a(this.f4563f);
                this.f4562e = false;
            }
            if (this.f4565h && (m6118b instanceof InterfaceC1268d)) {
                ((InterfaceC1268d) m6118b).mo6136a(this.f4566i);
                this.f4565h = false;
            }
            if (this.f4567j && (m6118b instanceof LyricProvider.InterfaceC1273a)) {
                if (this.f4569l.m6130a() == null) {
                    this.f4569l.m6128a((LyricProvider.InterfaceC1273a) m6118b);
                }
                this.f4569l.m6129a(this.f4568k);
                this.f4567j = false;
            }
            if (m6118b instanceof InterfaceC1269e) {
                if (SupportFactory.getInstance(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PLAYING && SupportFactory.getInstance(BaseApplication.getApplication()).m2486a(this.f4558a, 64)) {
                    ((InterfaceC1269e) m6118b).mo6135a(this.f4558a);
                } else {
                    ((InterfaceC1269e) m6118b).mo6135a(null);
                }
            }
        }
    }
}
