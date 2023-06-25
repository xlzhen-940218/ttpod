package com.sds.android.ttpod.component.landscape;

import android.content.Context;
import android.text.TextPaint;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric;
import com.sds.android.ttpod.framework.modules.skin.p132d.Lyric;
import com.sds.android.ttpod.framework.modules.skin.p132d.OnMeasureTextListener;
import com.sds.android.ttpod.framework.modules.skin.p132d.Sentence;

/* renamed from: com.sds.android.ttpod.component.landscape.g */
/* loaded from: classes.dex */
public class LyricProvider {

    /* renamed from: a */
    private Lyric f4586a;

    /* renamed from: b */
    private FormattedLyric f4587b;

    /* renamed from: c */
    private InterfaceC1273a f4588c;

    /* renamed from: d */
    private float f4589d;

    /* renamed from: e */
    private int f4590e;

    /* renamed from: f */
    private int f4591f;

    /* renamed from: g */
    private TextPaint f4592g = new TextPaint();

    /* renamed from: h */
    private OnMeasureTextListener f4593h = new OnMeasureTextListener() { // from class: com.sds.android.ttpod.component.landscape.g.1
        @Override // com.sds.android.ttpod.framework.modules.skin.p132d.OnMeasureTextListener
        /* renamed from: a */
        public int mo3467a(String str) {
            return (int) (LyricProvider.this.f4592g.measureText(str) + 0.96f);
        }

        @Override // com.sds.android.ttpod.framework.modules.skin.p132d.OnMeasureTextListener
        /* renamed from: a */
        public float mo3486a() {
            return LyricProvider.this.f4589d;
        }
    };

    /* compiled from: LyricProvider.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.g$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1273a {
        /* renamed from: a */
        void mo6122a(String str, String str2, boolean z);
    }

    public LyricProvider(Context context, float f, int i) {
        this.f4589d = f;
        this.f4590e = i;
        this.f4592g.setTextSize(this.f4589d);
        this.f4591f = -1;
    }

    /* renamed from: a */
    public void m6128a(InterfaceC1273a interfaceC1273a) {
        this.f4588c = interfaceC1273a;
    }

    /* renamed from: a */
    public InterfaceC1273a m6130a() {
        return this.f4588c;
    }

    /* renamed from: a */
    public void m6126a(Lyric lyric) {
        this.f4586a = lyric;
        m6124b();
    }

    /* renamed from: b */
    private void m6124b() {
        this.f4587b = null;
        if (this.f4586a != null && this.f4590e > 10 && this.f4589d > 5.0f) {
            this.f4587b = this.f4586a.mo3631a(1, this.f4590e, this.f4593h);
        }
    }

    /* renamed from: a */
    private void m6125a(String str, String str2, float f, boolean z) {
        if (this.f4588c != null) {
            this.f4588c.mo6122a(str, str2, z);
        }
    }

    /* renamed from: a */
    public void m6129a(long j) {
        if (this.f4587b != null && j >= 0) {
            int mo3628a = this.f4587b.mo3628a(j);
            Sentence mo3629a = this.f4587b.mo3629a(mo3628a);
            String mo3635g = mo3629a == null ? "" : mo3629a.mo3635g();
            if (this.f4591f != mo3628a) {
                m6125a(mo3635g, null, (float) j, true);
            } else {
                LogUtils.verbose("LyricProvider", "setPlayPos");
            }
            this.f4591f = mo3628a;
        } else if (this.f4591f != -1) {
            if (this.f4588c != null) {
                this.f4588c.mo6122a(null, null, true);
            }
            this.f4591f = -1;
        }
    }
}
