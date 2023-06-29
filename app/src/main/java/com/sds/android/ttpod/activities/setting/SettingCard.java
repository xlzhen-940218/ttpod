package com.sds.android.ttpod.activities.setting;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p085b.Card;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.activities.setting.b */
/* loaded from: classes.dex */
public class SettingCard extends Card {

    /* renamed from: a */
    private SettingItem[] f2996a;

    /* renamed from: b */
    private List<C0887a> f2997b;

    /* renamed from: c */
    private ActionItem.InterfaceC1135b f2998c;

    public SettingCard(Context context, SettingItem[] settingItemArr, int i, ActionItem.InterfaceC1135b interfaceC1135b) {
        super(context, i);
        this.f2997b = new ArrayList();
        m7798a(settingItemArr);
        mo6994b();
        this.f2998c = interfaceC1135b;
    }

    /* renamed from: a */
    public void m7798a(SettingItem[] settingItemArr) {
        this.f2996a = settingItemArr;
    }

    /* renamed from: a */
    public SettingItem[] m7802a() {
        return this.f2996a;
    }

    /* renamed from: g */
    private View m7795g() {
        View inflate = View.inflate(m6993d(), R.layout.activity_setting_divider, null);
        inflate.setPadding(DisplayUtils.dp2px(8), 0, 0, 0);
        return inflate;
    }

    /* renamed from: h */
    private View m7794h() {
        return View.inflate(m6993d(), R.layout.activity_setting_head_or_footer_divider, null);
    }

    /* renamed from: i */
    private View m7793i() {
        return View.inflate(m6993d(), R.layout.activity_setting_card_item, null);
    }

    @Override // com.sds.android.ttpod.component.p085b.Card
    /* renamed from: b */
    protected void mo6994b() {
        SettingItem[] settingItemArr;
        if (this.f2996a != null && this.f2996a.length > 0) {
            int i = 0;
            for (SettingItem settingItem : this.f2996a) {
                if (i == 0) {
                    m6996a(m7794h());
                } else {
                    m6996a(m7795g());
                }
                View m7793i = m7793i();
                C0887a c0887a = new C0887a(m7793i);
                c0887a.f3000b.setTag(settingItem);
                c0887a.m7789a(settingItem, i);
                this.f2997b.add(c0887a);
                m6996a(m7793i);
                i++;
            }
            m6991f().addView(m7795g());
        }
    }

    /* renamed from: a */
    public void m7799a(SettingItem settingItem, int i) {
        C0887a c0887a = this.f2997b.get(i);
        if (c0887a != null) {
            c0887a.m7790a(settingItem);
        }
    }

    /* renamed from: b */
    private C0887a m7797b(int i) {
        for (C0887a c0887a : this.f2997b) {
            if (((SettingItem) c0887a.f3000b.getTag()).m7005e() == i) {
                return c0887a;
            }
        }
        return null;
    }

    /* renamed from: a */
    public View m7801a(int i) {
        C0887a m7797b = m7797b(i);
        if (m7797b != null) {
            return m7797b.f3002d;
        }
        return null;
    }

    /* renamed from: c */
    public void m7796c() {
        SettingItem[] settingItemArr;
        if (this.f2996a != null && this.f2996a.length > 0) {
            int i = 0;
            for (SettingItem settingItem : this.f2996a) {
                C0887a c0887a = this.f2997b.get(i);
                if (c0887a != null) {
                    c0887a.m7790a(settingItem);
                }
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SettingCard.java */
    /* renamed from: com.sds.android.ttpod.activities.setting.b$a */
    /* loaded from: classes.dex */
    public class C0887a {

        /* renamed from: b */
        private View f3000b;

        /* renamed from: c */
        private IconTextView f3001c;

        /* renamed from: d */
        private View f3002d;

        /* renamed from: e */
        private IconTextView f3003e;

        /* renamed from: f */
        private TextView f3004f;

        /* renamed from: g */
        private TextView f3005g;

        /* renamed from: h */
        private TextView f3006h;

        public C0887a(View view) {
            this.f3000b = view;
            this.f3001c = (IconTextView) this.f3000b.findViewById(R.id.itv_label_icon);
            this.f3003e = (IconTextView) this.f3000b.findViewById(R.id.itv_action_view);
            this.f3004f = (TextView) this.f3000b.findViewById(R.id.tv_content);
            this.f3005g = (TextView) this.f3000b.findViewById(R.id.label_title);
            this.f3006h = (TextView) this.f3000b.findViewById(R.id.label_subtitle);
            this.f3002d = this.f3000b.findViewById(R.id.new_flag);
        }

        /* renamed from: a */
        public void m7789a(final SettingItem settingItem, final int i) {
            m7790a(settingItem);
            this.f3000b.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.setting.b.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (settingItem instanceof Checkable) {
                        ((Checkable) settingItem).setChecked(!((Checkable) settingItem).isChecked());
                        C0887a.this.m7784e(settingItem);
                    }
                    if (SettingCard.this.f2998c != null) {
                        SettingCard.this.f2998c.mo5409a(settingItem, i);
                    }
                }
            });
        }

        /* renamed from: a */
        public void m7790a(SettingItem settingItem) {
            m7785d(settingItem);
            m7786c(settingItem);
            m7784e(settingItem);
            m7787b(settingItem);
            this.f3000b.setEnabled(settingItem.m7780c());
        }

        /* renamed from: b */
        private void m7787b(SettingItem settingItem) {
            CharSequence g = settingItem.m7003g();
            this.f3006h.setText(g);
            this.f3006h.setVisibility(TextUtils.isEmpty(g) ? View.GONE : View.VISIBLE);
        }

        /* renamed from: c */
        private void m7786c(SettingItem settingItem) {
            this.f3005g.setText(settingItem.m7006d());
        }

        /* renamed from: d */
        private void m7785d(SettingItem settingItem) {
            int i = settingItem.m7001i();
            if (i != 0) {
                this.f3001c.setText(i);
                return;
            }
            Drawable icon = settingItem.getIcon();
            this.f3001c.setVisibility(icon == null ? View.GONE : View.VISIBLE);
            this.f3001c.setImageDrawable(icon);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public void m7784e(SettingItem settingItem) {
            if (settingItem instanceof Checkable) {
                this.f3004f.setVisibility(View.INVISIBLE);
                this.f3003e.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams layoutParams = this.f3003e.getLayoutParams();
                layoutParams.height = DisplayUtils.dp2px(30);
                layoutParams.width = DisplayUtils.dp2px(48);
                this.f3003e.setLayoutParams(layoutParams);
                this.f3003e.setImageResource(((Checkable) settingItem).isChecked() ? R.drawable.icon_setting_checked : R.drawable.icon_setting_uncheck);
                this.f3003e.setContentDescription("" + settingItem.m7005e());
                return;
            }
            int m7781b = settingItem.m7781b();
            if (m7781b != 0) {
                this.f3004f.setVisibility(View.INVISIBLE);
                this.f3003e.setVisibility(View.VISIBLE);
                this.f3003e.setText(m7781b);
            } else if (settingItem.m7783a() != null) {
                this.f3004f.setVisibility(View.INVISIBLE);
                this.f3003e.setVisibility(View.VISIBLE);
                this.f3003e.setImageDrawable(settingItem.m7783a());
            } else {
                this.f3004f.setVisibility(View.VISIBLE);
                this.f3003e.setVisibility(View.GONE);
                Object f = settingItem.m7004f();
                if (f instanceof CharSequence) {
                    this.f3004f.setText((CharSequence) f);
                }
            }
        }
    }
}
