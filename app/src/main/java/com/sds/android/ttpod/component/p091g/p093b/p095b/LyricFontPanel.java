package com.sds.android.ttpod.component.p091g.p093b.p095b;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* renamed from: com.sds.android.ttpod.component.g.b.b.b */
/* loaded from: classes.dex */
public class LyricFontPanel extends PopupWindow {

    /* renamed from: a */
    private View.OnTouchListener f4269a;

    /* renamed from: b */
    private RelativeLayout f4270b;

    /* renamed from: c */
    private RelativeLayout f4271c;

    /* renamed from: d */
    private RelativeLayout f4272d;

    /* renamed from: e */
    private InterfaceC1229a f4273e;

    /* renamed from: f */
    private int f4274f;

    /* renamed from: g */
    private int f4275g;

    /* compiled from: LyricFontPanel.java */
    /* renamed from: com.sds.android.ttpod.component.g.b.b.b$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1229a {
        /* renamed from: e */
        void mo6427e(int i);

        /* renamed from: g */
        void mo6424g(int i);
    }

    /* renamed from: c */
    static /* synthetic */ int m6488c(LyricFontPanel lyricFontPanel, int i) {
        int i2 = lyricFontPanel.f4275g + i;
        lyricFontPanel.f4275g = i2;
        return i2;
    }

    /* renamed from: d */
    static /* synthetic */ int m6486d(LyricFontPanel lyricFontPanel, int i) {
        int i2 = lyricFontPanel.f4275g - i;
        lyricFontPanel.f4275g = i2;
        return i2;
    }

    public LyricFontPanel(Context context, int i, int i2) {
        super(View.inflate(context, R.layout.popups_lyric_font_panel, null), i, i2, true);
        this.f4269a = new View.OnTouchListener() { // from class: com.sds.android.ttpod.component.g.b.b.b.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    if (view == LyricFontPanel.this.f4270b) {
                        LyricFontPanel.this.f4275g = 0;
                    } else if (view != LyricFontPanel.this.f4271c) {
                        if (view == LyricFontPanel.this.f4272d) {
                            LyricFontPanel.m6486d(LyricFontPanel.this, 1);
                        }
                    } else {
                        LyricFontPanel.m6488c(LyricFontPanel.this, 1);
                    }
                    if (LyricFontPanel.this.f4275g > 16) {
                        LyricFontPanel.this.f4275g = 16;
                    }
                    if (LyricFontPanel.this.f4275g < -10) {
                        LyricFontPanel.this.f4275g = -10;
                    }
                    ((ImageView) LyricFontPanel.this.f4270b.findViewById(R.id.lyric_font_check)).setVisibility(LyricFontPanel.this.f4275g == 0 ? View.VISIBLE : View.INVISIBLE);
                    LyricFontPanel.this.f4273e.mo6424g(LyricFontPanel.this.f4275g);
                }
                return false;
            }
        };
        this.f4274f = 0;
        this.f4275g = 0;
        setAnimationStyle(16973826);
        setBackgroundDrawable(new ColorDrawable(0));
        View contentView = getContentView();
        if (contentView != null) {
            m6495a(contentView);
        }
        setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.sds.android.ttpod.component.g.b.b.b.1
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                if (LyricFontPanel.this.f4275g != LyricFontPanel.this.f4274f) {
                    LyricFontPanel.this.f4274f = LyricFontPanel.this.f4275g;
                    Preferences.m2903c(LyricFontPanel.this.f4275g);
                    LyricFontPanel.this.f4273e.mo6427e(LyricFontPanel.this.f4275g);
                }
            }
        });
    }

    /* renamed from: a */
    public void m6494a(InterfaceC1229a interfaceC1229a) {
        this.f4273e = interfaceC1229a;
    }

    /* renamed from: a */
    private void m6495a(View view) {
        this.f4270b = (RelativeLayout) view.findViewById(R.id.lyric_font_normal_pl);
        this.f4271c = (RelativeLayout) view.findViewById(R.id.lyric_font_bigger_pl);
        this.f4272d = (RelativeLayout) view.findViewById(R.id.lyric_font_smaller_pl);
        this.f4270b.setOnTouchListener(this.f4269a);
        this.f4271c.setOnTouchListener(this.f4269a);
        this.f4272d.setOnTouchListener(this.f4269a);
        this.f4275g = Preferences.m3048Q();
        this.f4274f = this.f4275g;
        ((ImageView) this.f4270b.findViewById(R.id.lyric_font_check)).setVisibility(((double) this.f4275g) == 0.0d ? View.VISIBLE : View.INVISIBLE);
    }
}
