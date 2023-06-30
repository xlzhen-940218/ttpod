package com.sds.android.ttpod.activities.musiccircle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;

/* renamed from: com.sds.android.ttpod.activities.musiccircle.b */
/* loaded from: classes.dex */
public class MusiccircleFooter implements ThemeManager.InterfaceC2019b {

    /* renamed from: a */
    private View f2773a;

    /* renamed from: b */
    private TextView f2774b;

    /* renamed from: c */
    private ImageView f2775c;

    /* renamed from: d */
    private Animation f2776d;

    public MusiccircleFooter(LayoutInflater layoutInflater, View.OnClickListener onClickListener) {
        this.f2773a = layoutInflater.inflate(R.layout.musiccircle_list_footer, (ViewGroup) null, false);
        this.f2774b = (TextView) this.f2773a.findViewById(R.id.text_rear_content);
        this.f2775c = (ImageView) this.f2773a.findViewById(R.id.icon_progressing);
        this.f2773a.setOnClickListener(onClickListener);
    }

    @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        ThemeManager.m3269a(this.f2774b, ThemeElement.COMMON_CONTENT_TEXT);
        ThemeManager.m3269a(this.f2773a.findViewById(R.id.layout_root), "BackgroundMaskColor");
    }

    /* renamed from: a */
    public View m7934a() {
        return this.f2773a;
    }

    /* renamed from: a */
    public void m7932a(boolean z, int i, String str) {
        this.f2773a.setEnabled(z);
        this.f2774b.setText(str);
        if (this.f2773a.getVisibility() == 8) {
            this.f2773a.setVisibility(View.VISIBLE);
        }
        this.f2775c.setVisibility(i);
        if (i == 0) {
            this.f2775c.setAnimation(m7933a(this.f2775c.getContext()));
        } else {
            this.f2775c.clearAnimation();
        }
    }

    /* renamed from: a */
    private Animation m7933a(Context context) {
        if (this.f2776d == null) {
            this.f2776d = AnimationUtils.loadAnimation(context, R.anim.unlimited_rotate);
        }
        return this.f2776d;
    }
}
