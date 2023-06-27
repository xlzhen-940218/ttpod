package com.sds.android.ttpod.component.p091g.p093b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.view.View;
import com.sds.android.ttpod.framework.modules.skin.p132d.Lyric;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;
import com.sds.android.ttpod.framework.modules.skin.view.TTPodButton;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.mediastore.MediaItem;

/* renamed from: com.sds.android.ttpod.component.g.b.b */
/* loaded from: classes.dex */
public class LyricPanelViewController extends PanelViewController implements LyricView.TouchListener {

    /* renamed from: a */
    protected TTPodButton f4252a;

    /* renamed from: b */
    private Paint.Align f4253b;

    public LyricPanelViewController(Context context, String str) {
        super(context, str);
        this.f4253b = Paint.Align.CENTER;
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: b_ */
    public void mo6403b_() {
        super.mo6403b_();
        if (this.lyricView != null) {
            m6540a((View) this.lyricView, true);
            this.f4253b = this.lyricView.getAlign();
            this.lyricView.setEnabled(true);
            this.lyricView.setTouchListener(this);
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.view.LyricView.InterfaceC1998c
    /* renamed from: a */
    public void mo3408a(long j) {
        if (this.f4224g != null) {
            this.f4224g.mo3717a(14, Long.valueOf(j));
            this.f4224g.mo3717a(4, null);
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.view.LyricView.InterfaceC1998c
    /* renamed from: a */
    public void mo3409a(int i) {
        if (this.f4224g != null) {
            this.f4224g.mo3717a(8, null);
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: b */
    public void mo6441b() {
        if (this.lyricView != null) {
            this.lyricView.setTouchListener(null);
        }
        super.mo6441b();
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6447a(MediaItem mediaItem, Bitmap bitmap, Lyric lyric) {
        super.mo6447a(mediaItem, bitmap, lyric);
        m6510b(Preferences.m3044S());
        m6523h(Preferences.m3048Q());
    }

    /* renamed from: b */
    private void m6510b(int i) {
        if (this.lyricView != null) {
            if (i < 0) {
                this.lyricView.setAlign(this.f4253b);
            } else {
                this.lyricView.setAlign(Paint.Align.values()[i]);
            }
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController, com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6456a(View view) {
        PlayerPortraitViewController c = m6469c();
        if (view == this.f4252a && c != null) {
            c.m6405w();
        } else {
            super.mo6456a(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6511a(Object obj, TTPodButton tTPodButton) {
        super.mo6511a(obj, tTPodButton);
        if ("LyricAdjustButton".equals(obj)) {
            this.f4252a = tTPodButton;
            this.f4252a.setContentDescription("play_page_lyric_adjust");
        }
    }
}
