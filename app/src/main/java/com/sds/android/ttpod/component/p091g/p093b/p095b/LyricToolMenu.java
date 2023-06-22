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
    private View.OnClickListener f4278a;

    /* renamed from: b */
    private ImageView f4279b;

    /* renamed from: c */
    private ImageView f4280c;

    /* renamed from: d */
    private ImageView f4281d;

    /* renamed from: e */
    private ImageView f4282e;

    /* renamed from: f */
    private ImageView f4283f;

    /* renamed from: g */
    private ImageView f4284g;

    /* renamed from: h */
    private ImageView f4285h;

    /* renamed from: i */
    private InterfaceC1231a f4286i;

    /* renamed from: j */
    private View f4287j;

    /* renamed from: k */
    private Context f4288k;

    /* compiled from: LyricToolMenu.java */
    /* renamed from: com.sds.android.ttpod.component.g.b.b.c$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1231a {
        /* renamed from: a */
        void mo6455a(View view, int i);

        /* renamed from: b */
        void mo6436b(View view, int i);

        /* renamed from: b */
        void mo6433b(boolean z);

        /* renamed from: s */
        void mo6409s();

        /* renamed from: t */
        void mo6408t();

        /* renamed from: u */
        void mo6407u();

        /* renamed from: v */
        void mo6406v();
    }

    /* renamed from: a */
    public void m6483a(int i) {
        this.f4284g.setVisibility(i);
    }

    /* renamed from: a */
    public void m6481a(InterfaceC1231a interfaceC1231a) {
        this.f4286i = interfaceC1231a;
    }

    public LyricToolMenu(Context context, int i, int i2) {
        super(View.inflate(context, R.layout.popups_lyric_tool_menu, null), i, i2, true);
        this.f4278a = new View.OnClickListener() { // from class: com.sds.android.ttpod.component.g.b.b.c.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LyricToolMenu.this.f4286i != null) {
                    if (view == LyricToolMenu.this.f4279b) {
                        LyricToolMenu.this.f4286i.mo6408t();
                    } else if (view == LyricToolMenu.this.f4280c) {
                        LyricToolMenu.this.f4286i.mo6407u();
                    } else if (view == LyricToolMenu.this.f4281d) {
                        LyricToolMenu.this.f4286i.mo6409s();
                    } else if (view != LyricToolMenu.this.f4282e) {
                        if (view != LyricToolMenu.this.f4283f) {
                            if (view != LyricToolMenu.this.f4284g) {
                                if (view == LyricToolMenu.this.f4285h) {
                                    LyricToolMenu.this.f4286i.mo6406v();
                                    return;
                                }
                                return;
                            }
                            boolean m3046R = Preferences.m3046R();
                            Drawable drawable = LyricToolMenu.this.f4288k.getResources().getDrawable(!m3046R ? R.drawable.img_lyric_kala_off : R.drawable.img_lyric_kala_on);
                            PopupsUtils.m6721a(LyricToolMenu.this.f4288k.getString(m3046R ? R.string.kara_ok_off : R.string.kara_ok_on));
                            LyricToolMenu.this.f4284g.setImageDrawable(drawable);
                            LyricToolMenu.this.f4286i.mo6433b(!m3046R);
                            Preferences.m2812y(m3046R ? false : true);
                            return;
                        }
                        int[] iArr = {0, 0};
                        LyricToolMenu.this.f4283f.getLocationOnScreen(iArr);
                        LyricToolMenu.this.f4286i.mo6436b(LyricToolMenu.this.f4287j, iArr[1]);
                    } else {
                        int[] iArr2 = {0, 0};
                        LyricToolMenu.this.f4282e.getLocationOnScreen(iArr2);
                        LyricToolMenu.this.f4286i.mo6455a(LyricToolMenu.this.f4287j, iArr2[1]);
                    }
                }
            }
        };
        this.f4279b = null;
        this.f4280c = null;
        this.f4281d = null;
        this.f4282e = null;
        this.f4283f = null;
        this.f4284g = null;
        this.f4285h = null;
        this.f4286i = null;
        this.f4287j = null;
        this.f4288k = context;
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
        this.f4279b = (ImageView) view.findViewById(R.id.lyric_slow_down);
        this.f4280c = (ImageView) view.findViewById(R.id.lyric_reset);
        this.f4281d = (ImageView) view.findViewById(R.id.lyric_slow_up);
        this.f4282e = (ImageView) view.findViewById(R.id.lyric_font_edit);
        this.f4283f = (ImageView) view.findViewById(R.id.lyric_color_edit);
        this.f4284g = (ImageView) view.findViewById(R.id.lyric_switch_trc);
        this.f4285h = (ImageView) view.findViewById(R.id.iv_delete_lyric);
        this.f4279b.setOnClickListener(this.f4278a);
        this.f4280c.setOnClickListener(this.f4278a);
        this.f4281d.setOnClickListener(this.f4278a);
        this.f4282e.setOnClickListener(this.f4278a);
        this.f4283f.setOnClickListener(this.f4278a);
        this.f4284g.setOnClickListener(this.f4278a);
        this.f4285h.setOnClickListener(this.f4278a);
        this.f4284g.setImageDrawable(context.getResources().getDrawable(Preferences.m3046R() ? R.drawable.img_lyric_kala_off : R.drawable.img_lyric_kala_on));
    }

    @Override // android.widget.PopupWindow
    public void showAtLocation(View view, int i, int i2, int i3) {
        super.showAtLocation(view, i, i2, i3);
        this.f4287j = view;
    }
}
