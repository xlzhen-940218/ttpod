package com.sds.android.ttpod.component;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.ActionBarFrameLayout;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.a */
/* loaded from: classes.dex */
public final class ActionBarController implements ThemeManager.InterfaceC2019b {

    /* renamed from: a */
    private ActionBarFrameLayout f3607a;

    /* renamed from: b */
    private TextView f3608b;

    /* renamed from: c */
    private LinearLayout f3609c;

    /* renamed from: d */
    private RelativeLayout f3610d;

    /* renamed from: e */
    private Animation f3611e;

    /* renamed from: f */
    private List<C1070a> f3612f = new ArrayList();

    /* renamed from: g */
    private InterfaceC1073c f3613g;

    /* renamed from: h */
    private InterfaceC1073c f3614h;

    /* renamed from: i */
    private IconTextView f3615i;

    /* renamed from: j */
    private View f3616j;

    /* renamed from: k */
    private boolean f3617k;

    /* renamed from: l */
    private View f3618l;

    /* renamed from: m */
    private View f3619m;

    /* renamed from: n */
    private boolean f3620n;

    /* compiled from: ActionBarController.java */
    /* renamed from: com.sds.android.ttpod.component.a$b */
    /* loaded from: classes.dex */
    public interface InterfaceC1072b {
        /* renamed from: a */
        void mo5433a(C1070a c1070a);
    }

    /* compiled from: ActionBarController.java */
    /* renamed from: com.sds.android.ttpod.component.a$c */
    /* loaded from: classes.dex */
    public interface InterfaceC1073c {
        /* renamed from: a */
        void mo5380a(ActionBarController actionBarController);
    }

    private ActionBarController(ActionBarFrameLayout actionBarFrameLayout) {
        this.f3607a = actionBarFrameLayout;
        this.f3616j = actionBarFrameLayout.findViewById(R.id.linear_title);
        this.f3608b = (TextView) actionBarFrameLayout.findViewById(R.id.text_title);
        this.f3609c = (LinearLayout) actionBarFrameLayout.findViewById(R.id.linear_action);
        this.f3610d = (RelativeLayout) actionBarFrameLayout.findViewById(R.id.relative_custom);
        this.f3615i = (IconTextView) actionBarFrameLayout.findViewById(R.id.itv_avatar);
        m7185b(false);
        this.f3619m = actionBarFrameLayout.findViewById(R.id.search_input_layout);
        this.f3619m.setVisibility(View.GONE);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.component.a.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view != ActionBarController.this.f3616j || ActionBarController.this.f3613g == null) {
                    if (view == ActionBarController.this.f3607a && ActionBarController.this.f3614h != null) {
                        ActionBarController.this.f3614h.mo5380a(ActionBarController.this);
                        return;
                    }
                    return;
                }
                ActionBarController.this.f3613g.mo5380a(ActionBarController.this);
            }
        };
        this.f3618l = actionBarFrameLayout.findViewById(R.id.view_bottom_divider);
        this.f3618l.setVisibility(View.GONE);
        this.f3607a.setOnClickListener(onClickListener);
        this.f3616j.setOnClickListener(onClickListener);
        final View findViewById = actionBarFrameLayout.findViewById(R.id.relative_title);
        if (findViewById != null) {
            this.f3607a.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.component.a.2
                @Override // java.lang.Runnable
                public void run() {
                    Rect rect = new Rect();
                    findViewById.getHitRect(rect);
                    ActionBarController.this.f3607a.setTouchArea(rect);
                }
            }, 300L);
        }
    }

    /* renamed from: a */
    public View m7201a() {
        this.f3619m.setVisibility(View.VISIBLE);
        return this.f3619m;
    }

    /* renamed from: b */
    public IconTextView m7190b() {
        return this.f3615i;
    }

    /* renamed from: a */
    public void m7191a(boolean z) {
        this.f3609c.setVisibility(z ? View.INVISIBLE : View.VISIBLE);
    }

    /* renamed from: a */
    public void m7200a(int i) {
        this.f3607a.setBackgroundDrawable(new ColorDrawable(i));
    }

    /* renamed from: a */
    public void m7193a(CharSequence charSequence) {
        this.f3608b.setText(charSequence);
    }

    /* renamed from: b */
    public void m7189b(int i) {
        this.f3608b.setText(i);
    }

    /* renamed from: b */
    public void m7185b(boolean z) {
        this.f3620n = z;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f3615i.getLayoutParams();
        if (z) {
            int m7229a = DisplayUtils.m7229a(8);
            marginLayoutParams.leftMargin = m7229a;
            marginLayoutParams.rightMargin = (m7229a >> 2) * 3;
        } else {
            marginLayoutParams.leftMargin = 0;
            marginLayoutParams.rightMargin = 0;
        }
        this.f3615i.setLayoutParams(marginLayoutParams);
    }

    /* renamed from: c */
    public void m7183c(int i) {
        this.f3608b.setTextColor(i);
    }

    /* renamed from: c */
    public void m7180c(boolean z) {
        this.f3607a.setVisibility(z ? View.VISIBLE : View.GONE);
        this.f3607a.requestLayout();
    }

    /* renamed from: c */
    public boolean m7184c() {
        return this.f3607a.getVisibility() == View.VISIBLE;
    }

    /* renamed from: a */
    public void m7196a(InterfaceC1073c interfaceC1073c) {
        this.f3613g = interfaceC1073c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void hiddenImageProgress(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            view.clearAnimation();
            view.setVisibility(View.GONE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m7182c(View view) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setAnimation(m7176e());
            view.setVisibility(View.VISIBLE);
        }
    }

    /* renamed from: e */
    private Animation m7176e() {
        if (this.f3611e == null) {
            this.f3611e = AnimationUtils.loadAnimation(m7174f(), R.anim.unlimited_rotate);
        }
        return this.f3611e;
    }

    /* renamed from: f */
    private Context m7174f() {
        return this.f3607a.getContext();
    }

    /* renamed from: a */
    public static ActionBarController m7197a(View view) {
        ActionBarFrameLayout actionBarFrameLayout;
        if (view instanceof ActionBarFrameLayout) {
            actionBarFrameLayout = (ActionBarFrameLayout) view;
        } else {
            actionBarFrameLayout = (ActionBarFrameLayout) view.findViewById(R.id.view_switcher_standard_dialog_header);
        }
        if (actionBarFrameLayout == null) {
            throw new IllegalArgumentException("there's no dialog header layout in this view");
        }
        return new ActionBarController(actionBarFrameLayout);
    }

    /* renamed from: d */
    public C1070a m7178d(int i) {
        return m7199a(m7174f().getResources().getDrawable(i));
    }

    /* renamed from: a */
    public C1070a m7199a(Drawable drawable) {
        return m7198a(drawable, (String) null);
    }

    /* renamed from: a */
    public C1070a m7198a(Drawable drawable, String str) {
        C1070a m7192a = m7192a(str);
        m7192a.showTextOrImage(0);
        m7192a.setDrawable(drawable);
        return m7192a;
    }

    /* renamed from: e */
    public C1070a m7175e(int i) {
        C1070a m7192a = m7192a((String) null);
        m7192a.showTextOrImage(1);
        m7192a.setText(i);
        return m7192a;
    }

    @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        int i = 0;
        if (this.f3607a != null) {
            ThemeManager.m3271a(this.f3607a, -1, this.f3607a.getResources().getDimensionPixelSize(R.dimen.dialog_header_height), ThemeElement.TOP_BAR_BACKGROUND);
        }
        if (!this.f3617k) {
            if (ThemeManager.m3262a(ThemeElement.TOP_BAR_SEPARATOR, true, false) != null) {
                this.f3618l.setVisibility(View.VISIBLE);
                ThemeManager.m3269a(this.f3618l, ThemeElement.TOP_BAR_SEPARATOR);
            } else {
                this.f3618l.setVisibility(View.GONE);
            }
        }
        while (true) {
            int i2 = i;
            if (i2 >= this.f3609c.getChildCount()) {
                break;
            }
            TextView textView = (TextView) this.f3609c.getChildAt(i2).findViewById(R.id.text_variable);
            if (textView.getVisibility() == View.VISIBLE) {
                ThemeManager.m3269a(textView, ThemeElement.TOP_BAR_TEXT);
            }
            i = i2 + 1;
        }
        ThemeManager.m3269a(this.f3608b, ThemeElement.TOP_BAR_TEXT);
        if (!this.f3620n) {
            Drawable m3265a = ThemeManager.m3265a(ThemeElement.TOP_BAR_BACK_IMAGE);
            if (m3265a != null) {
                this.f3615i.setImageDrawable(m3265a);
                return;
            }
            this.f3615i.setText(R.string.icon_arrow_left);
            ThemeUtils.m8173a(this.f3615i, ThemeElement.TOP_BAR_TEXT);
        }
    }

    /* renamed from: d */
    public void m7179d() {
        this.f3618l.setVisibility(View.VISIBLE);
        this.f3617k = true;
    }

    /* renamed from: a */
    private C1070a m7192a(String str) {
        View inflate = LayoutInflater.from(m7174f()).inflate(R.layout.action_bar_action, (ViewGroup) this.f3609c, false);
        C1070a c1070a = new C1070a(inflate);
        if (str != null) {
            inflate.findViewById(R.id.image_variable).setContentDescription(str);
        }
        this.f3609c.addView(inflate);
        this.f3612f.add(c1070a);
        return c1070a;
    }

    /* compiled from: ActionBarController.java */
    /* renamed from: com.sds.android.ttpod.component.a$a */
    /* loaded from: classes.dex */
    public final class C1070a {

        /* renamed from: b */
        private View actionBarAction;

        /* renamed from: c */
        private RelativeLayout relativeVariable;

        /* renamed from: d */
        private IconTextView imageProgress;

        /* renamed from: e */
        private TextView textVariable;

        /* renamed from: f */
        private IconTextView imageVariable;

        /* renamed from: g */
        private InterfaceC1072b f3630g;

        /* renamed from: h */
        private Object f3631h;

        /* renamed from: i */
        private boolean f3632i;

        private C1070a(View view) {
            this.f3632i = true;
            this.actionBarAction = view;
            this.relativeVariable = (RelativeLayout) view.findViewById(R.id.relative_variable);
            this.imageProgress = (IconTextView) view.findViewById(R.id.image_progress);
            this.textVariable = (TextView) view.findViewById(R.id.text_variable);
            this.imageVariable = (IconTextView) view.findViewById(R.id.image_variable);
            this.imageProgress.setVisibility(View.INVISIBLE);
            this.relativeVariable.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.a.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (C1070a.this.f3630g != null && !C1070a.this.m7154d()) {
                        C1070a.this.f3630g.mo5433a(C1070a.this);
                    }
                }
            });
        }

        /* renamed from: a */
        public void m7165a(boolean z) {
            this.f3632i = z;
            if (m7157c()) {
                this.relativeVariable.setEnabled(z);
            }
        }

        /* renamed from: b */
        public void m7159b(boolean z) {
            this.relativeVariable.setVisibility(z ? 4 : 8);
            this.relativeVariable.setEnabled(false);
        }

        /* renamed from: a */
        public void m7172a() {
            m7159b(false);
        }

        /* renamed from: b */
        public void m7163b() {
            m7155c(true);
        }

        /* renamed from: c */
        public void m7155c(boolean z) {
            this.relativeVariable.setVisibility(z ? View.VISIBLE : View.GONE);
            this.relativeVariable.setEnabled(this.f3632i & z);
        }

        /* renamed from: c */
        public boolean m7157c() {
            return this.relativeVariable.getVisibility() == View.VISIBLE;
        }

        /* renamed from: a */
        public void m7167a(InterfaceC1072b interfaceC1072b) {
            this.f3630g = interfaceC1072b;
        }

        /* renamed from: d */
        public boolean m7154d() {
            return this.imageProgress.getVisibility() == View.VISIBLE;
        }

        /* renamed from: a */
        public void m7169a(Drawable drawable) {
            if (drawable != null) {
                this.imageProgress.setImageDrawable(drawable);
            }
        }

        /* renamed from: a */
        public void m7171a(int i) {
            if (i != 0) {
                this.imageProgress.setText(i);
            }
        }

        /* renamed from: a */
        public void m7170a(ColorStateList colorStateList) {
            if (colorStateList != null) {
                this.imageProgress.setTextColor(colorStateList);
            }
        }

        /* renamed from: e */
        public void m7152e() {
            ActionBarController.this.m7182c(this.imageProgress);
            this.relativeVariable.setEnabled(false);
            this.imageVariable.setVisibility(View.INVISIBLE);
            this.textVariable.setVisibility(View.INVISIBLE);
        }

        /* renamed from: a */
        public void m7166a(Object obj) {
            this.f3631h = obj;
        }

        /* renamed from: f */
        public Object m7150f() {
            return this.f3631h;
        }

        /* renamed from: g */
        public void m7149g() {
            ActionBarController.this.hiddenImageProgress(this.imageProgress);
            this.relativeVariable.setEnabled(true);
            this.imageVariable.setVisibility(View.VISIBLE);
            this.textVariable.setVisibility(View.VISIBLE);
        }

        /* renamed from: b */
        public void showTextOrImage(int i) {
            if (i == 0) {
                this.textVariable.setVisibility(View.INVISIBLE);
                this.imageVariable.setVisibility(View.VISIBLE);
                return;
            }
            this.imageVariable.setVisibility(View.INVISIBLE);
            this.textVariable.setVisibility(View.VISIBLE);
        }

        /* renamed from: c */
        public void setText(int i) {
            this.textVariable.setText(i);
        }

        /* renamed from: d */
        public void setImageResource(int i) {
            this.imageVariable.setImageResource(i);
        }

        /* renamed from: b */
        public void setDrawable(Drawable drawable) {
            this.imageVariable.setImageDrawable(drawable);
        }

        /* renamed from: e */
        public void setImageText(int i) {
            this.imageVariable.setText(i);
        }

        /* renamed from: b */
        public void setTextColor(ColorStateList colorStateList) {
            this.imageVariable.setTextColor(colorStateList);
        }
    }
}
