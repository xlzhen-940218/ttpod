package com.sds.android.ttpod.component.p085b;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.ttpod.R;

/* renamed from: com.sds.android.ttpod.component.b.c */
/* loaded from: classes.dex */
public abstract class Card {

    /* renamed from: a */
    private Context f3824a;

    /* renamed from: b */
    private TextView f3825b;

    /* renamed from: c */
    private ViewGroup f3826c;

    /* renamed from: d */
    private View f3827d;

    /* renamed from: b */
    protected abstract void mo6994b();

    public Card(Context context, int i) {
        this.f3824a = context;
        this.f3827d = View.inflate(context, R.layout.activity_setting_card, null);
        this.f3826c = (ViewGroup) this.f3827d.findViewById(R.id.card_content);
        this.f3825b = (TextView) this.f3827d.findViewById(R.id.card_title);
        this.f3825b.setText(i);
    }

    /* renamed from: d */
    public Context m6993d() {
        return this.f3824a;
    }

    /* renamed from: a */
    public void m6995a(boolean z) {
        this.f3825b.setVisibility(z ? View.VISIBLE : View.GONE);
    }

    /* renamed from: e */
    public View m6992e() {
        return this.f3827d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: f */
    public ViewGroup m6991f() {
        return this.f3826c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m6996a(View view) {
        this.f3826c.addView(view);
    }
}
