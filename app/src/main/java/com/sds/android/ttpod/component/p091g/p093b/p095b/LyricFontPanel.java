package com.sds.android.ttpod.component.p091g.p093b.p095b;

import android.annotation.SuppressLint;
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
    private View.OnTouchListener onTouchListener;

    /* renamed from: b */
    private RelativeLayout fontNormalLayout;

    /* renamed from: c */
    private RelativeLayout fontBiggerLayout;

    /* renamed from: d */
    private RelativeLayout fontSmallerLayout;

    /* renamed from: e */
    private LyricFontPanelCallback lyricFontPanelCallback;

    /* renamed from: f */
    private int fontSize;

    /* renamed from: g */
    private int currentSize;

    /* compiled from: LyricFontPanel.java */
    /* renamed from: com.sds.android.ttpod.component.g.b.b.b$a */
    /* loaded from: classes.dex */
    public interface LyricFontPanelCallback {
        /* renamed from: e */
        void onDismiss(int i);

        /* renamed from: g */
        void onTouch(int i);
    }

    /* renamed from: c */
    static /* synthetic */ int currentSizeBigger(LyricFontPanel lyricFontPanel, int number) {
        int newSize = lyricFontPanel.currentSize + number;
        lyricFontPanel.currentSize = newSize;
        return newSize;
    }

    /* renamed from: d */
    static /* synthetic */ int currentSizeSmaller(LyricFontPanel lyricFontPanel, int number) {
        int newSize = lyricFontPanel.currentSize - number;
        lyricFontPanel.currentSize = newSize;
        return newSize;
    }

    @SuppressLint("ClickableViewAccessibility")
    public LyricFontPanel(Context context, int i, int i2) {
        super(View.inflate(context, R.layout.popups_lyric_font_panel, null), i, i2, true);
        // from class: com.sds.android.ttpod.component.g.b.b.b.2
// android.view.View.OnTouchListener
        this.onTouchListener = (view, motionEvent) -> {
            if (motionEvent.getAction() == 1) {
                if (view == LyricFontPanel.this.fontNormalLayout) {
                    LyricFontPanel.this.currentSize = 0;
                } else if (view != LyricFontPanel.this.fontBiggerLayout) {
                    if (view == LyricFontPanel.this.fontSmallerLayout) {
                        LyricFontPanel.currentSizeSmaller(LyricFontPanel.this, 1);
                    }
                } else {
                    LyricFontPanel.currentSizeBigger(LyricFontPanel.this, 1);
                }
                if (LyricFontPanel.this.currentSize > 16) {
                    LyricFontPanel.this.currentSize = 16;
                }
                if (LyricFontPanel.this.currentSize < -10) {
                    LyricFontPanel.this.currentSize = -10;
                }
                LyricFontPanel.this.fontNormalLayout.findViewById(R.id.lyric_font_check)
                        .setVisibility(LyricFontPanel.this.currentSize == 0 ? View.VISIBLE : View.INVISIBLE);
                LyricFontPanel.this.lyricFontPanelCallback.onTouch(LyricFontPanel.this.currentSize);
            }
            return false;
        };
        this.fontSize = 0;
        this.currentSize = 0;
        setAnimationStyle(android.R.style.Animation_Dialog);
        setBackgroundDrawable(new ColorDrawable(0));
        View contentView = getContentView();
        if (contentView != null) {
            initView(contentView);
        }
        setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.sds.android.ttpod.component.g.b.b.b.1
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                if (LyricFontPanel.this.currentSize != LyricFontPanel.this.fontSize) {
                    LyricFontPanel.this.fontSize = LyricFontPanel.this.currentSize;
                    Preferences.setLyricFontSize(LyricFontPanel.this.currentSize);
                    LyricFontPanel.this.lyricFontPanelCallback.onDismiss(LyricFontPanel.this.currentSize);
                }
            }
        });
    }

    /* renamed from: a */
    public void m6494a(LyricFontPanelCallback interfaceC1229a) {
        this.lyricFontPanelCallback = interfaceC1229a;
    }

    /* renamed from: a */
    @SuppressLint("ClickableViewAccessibility")
    private void initView(View view) {
        this.fontNormalLayout = (RelativeLayout) view.findViewById(R.id.lyric_font_normal_pl);
        this.fontBiggerLayout = (RelativeLayout) view.findViewById(R.id.lyric_font_bigger_pl);
        this.fontSmallerLayout = (RelativeLayout) view.findViewById(R.id.lyric_font_smaller_pl);
        this.fontNormalLayout.setOnTouchListener(this.onTouchListener);
        this.fontBiggerLayout.setOnTouchListener(this.onTouchListener);
        this.fontSmallerLayout.setOnTouchListener(this.onTouchListener);
        this.currentSize = Preferences.getLyricFontSize();
        this.fontSize = this.currentSize;
        ((ImageView) this.fontNormalLayout.findViewById(R.id.lyric_font_check))
                .setVisibility(((double) this.currentSize) == 0.0d ? View.VISIBLE : View.INVISIBLE);
    }
}
