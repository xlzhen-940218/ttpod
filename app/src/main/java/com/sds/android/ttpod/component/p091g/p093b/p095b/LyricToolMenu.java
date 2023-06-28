package com.sds.android.ttpod.component.p091g.p093b.p095b;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* renamed from: com.sds.android.ttpod.component.g.b.b.c */
/* loaded from: classes.dex */
public class LyricToolMenu extends PopupWindow {

    /* renamed from: a */
    private View.OnClickListener setOnClickListener;

    /* renamed from: b */
    private ImageView slowDownView;

    /* renamed from: c */
    private ImageView resetView;

    /* renamed from: d */
    private ImageView slowUpView;

    /* renamed from: e */
    private ImageView fontEdit;

    /* renamed from: f */
    private ImageView colorEdit;

    /* renamed from: g */
    private ImageView switchTrcView;

    /* renamed from: h */
    private ImageView deleteLyricView;

    /* renamed from: i */
    private Callback callback;

    /* renamed from: j */
    private View parent;

    /* renamed from: k */
    private Context context;

    /* compiled from: LyricToolMenu.java */
    /* renamed from: com.sds.android.ttpod.component.g.b.b.c$a */
    /* loaded from: classes.dex */
    public interface Callback {
        /* renamed from: a */
        void setFont(View view, int i);

        /* renamed from: b */
        void setColor(View view, int i);

        /* renamed from: b */
        void kalaOkEnabled(boolean z);

        /* renamed from: s */
        void slowUp();

        /* renamed from: t */
        void slowDown();

        /* renamed from: u */
        void reset();

        /* renamed from: v */
        void deleteLyric();
    }

    /* renamed from: a */
    public void setVisibility(int visibility) {
        this.switchTrcView.setVisibility(visibility);
    }

    /* renamed from: a */
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public LyricToolMenu(Context context, int i, int i2) {
        super(View.inflate(context, R.layout.popups_lyric_tool_menu, null), i, i2, true);
        this.setOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.component.g.b.b.c.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LyricToolMenu.this.callback != null) {
                    if (view == LyricToolMenu.this.slowDownView) {
                        LyricToolMenu.this.callback.slowDown();
                    } else if (view == LyricToolMenu.this.resetView) {
                        LyricToolMenu.this.callback.reset();
                    } else if (view == LyricToolMenu.this.slowUpView) {
                        LyricToolMenu.this.callback.slowUp();
                    } else if (view != LyricToolMenu.this.fontEdit) {
                        if (view != LyricToolMenu.this.colorEdit) {
                            if (view != LyricToolMenu.this.switchTrcView) {
                                if (view == LyricToolMenu.this.deleteLyricView) {
                                    LyricToolMenu.this.callback.deleteLyric();
                                    return;
                                }
                                return;
                            }
                            boolean kalaOkEnabled = Preferences.kalaOkEnabled();
                            Drawable drawable = LyricToolMenu.this.context.getResources().getDrawable(!kalaOkEnabled ? R.drawable.img_lyric_kala_off : R.drawable.img_lyric_kala_on);
                            PopupsUtils.m6721a(LyricToolMenu.this.context.getString(kalaOkEnabled ? R.string.kara_ok_off : R.string.kara_ok_on));
                            LyricToolMenu.this.switchTrcView.setImageDrawable(drawable);
                            LyricToolMenu.this.callback.kalaOkEnabled(!kalaOkEnabled);
                            Preferences.m2812y(kalaOkEnabled ? false : true);
                            return;
                        }
                        int[] iArr = {0, 0};
                        LyricToolMenu.this.colorEdit.getLocationOnScreen(iArr);
                        LyricToolMenu.this.callback.setColor(LyricToolMenu.this.parent, iArr[1]);
                    } else {
                        int[] iArr2 = {0, 0};
                        LyricToolMenu.this.fontEdit.getLocationOnScreen(iArr2);
                        LyricToolMenu.this.callback.setFont(LyricToolMenu.this.parent, iArr2[1]);
                    }
                }
            }
        };
        this.slowDownView = null;
        this.resetView = null;
        this.slowUpView = null;
        this.fontEdit = null;
        this.colorEdit = null;
        this.switchTrcView = null;
        this.deleteLyricView = null;
        this.callback = null;
        this.parent = null;
        this.context = context;
        setAnimationStyle(R.style.Dialog_Window_Push_Anim);
        setBackgroundDrawable(new ColorDrawable(0));
        View contentView = getContentView();
        if (contentView != null) {
            m6482a(context, contentView);
        }
    }

    /* renamed from: a */
    private void m6482a(Context context, View view) {
        view.setFocusableInTouchMode(true);
        this.slowDownView = (ImageView) view.findViewById(R.id.lyric_slow_down);
        this.resetView = (ImageView) view.findViewById(R.id.lyric_reset);
        this.slowUpView = (ImageView) view.findViewById(R.id.lyric_slow_up);
        this.fontEdit = (ImageView) view.findViewById(R.id.lyric_font_edit);
        this.colorEdit = (ImageView) view.findViewById(R.id.lyric_color_edit);
        this.switchTrcView = (ImageView) view.findViewById(R.id.lyric_switch_trc);
        this.deleteLyricView = (ImageView) view.findViewById(R.id.iv_delete_lyric);
        this.slowDownView.setOnClickListener(this.setOnClickListener);
        this.resetView.setOnClickListener(this.setOnClickListener);
        this.slowUpView.setOnClickListener(this.setOnClickListener);
        this.fontEdit.setOnClickListener(this.setOnClickListener);
        this.colorEdit.setOnClickListener(this.setOnClickListener);
        this.switchTrcView.setOnClickListener(this.setOnClickListener);
        this.deleteLyricView.setOnClickListener(this.setOnClickListener);
        this.switchTrcView.setImageDrawable(context.getResources().getDrawable(Preferences.kalaOkEnabled()
                ? R.drawable.img_lyric_kala_off : R.drawable.img_lyric_kala_on));
    }

    @Override // android.widget.PopupWindow
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        this.parent = parent;
    }
}
