package com.sds.android.ttpod.component.p087d.p088a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.sds.android.ttpod.common.p082a.BaseDialog;

/* renamed from: com.sds.android.ttpod.component.d.a.o */
/* loaded from: classes.dex */
public abstract class ScrollableDialog extends BaseDialog {
    /* renamed from: b */
    protected abstract View mo6793b(Context context);

    public ScrollableDialog(Context context) {
        super(context);
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: a */
    protected final View inflate(Context context) {
        ScrollView scrollView = new ScrollView(context);
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        scrollView.addView(mo6793b(context));
        return scrollView;
    }
}
