package com.sds.android.ttpod.framework.modules.skin.p130c;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.l */
/* loaded from: classes.dex */
public class StateListDrawableCreator extends DrawableCreator {

    /* renamed from: a */
    private final ArrayList<C1983a> f6543a = new ArrayList<>();

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.DrawableCreator
    /* renamed from: a */
    public Drawable mo3716a(Resources resources) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Iterator<C1983a> it = this.f6543a.iterator();
        while (it.hasNext()) {
            C1983a next = it.next();
            stateListDrawable.addState(next.f6544a, next.f6545b.mo3716a(resources));
        }
        return stateListDrawable;
    }

    /* renamed from: a */
    public void m3715a(int[] iArr, DrawableCreator drawableCreator) {
        if (iArr != null && drawableCreator != null) {
            this.f6543a.add(new C1983a(iArr, drawableCreator));
        }
    }

    /* compiled from: StateListDrawableCreator.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.skin.c.l$a */
    /* loaded from: classes.dex */
    private static final class C1983a {

        /* renamed from: a */
        private int[] f6544a;

        /* renamed from: b */
        private DrawableCreator f6545b;

        private C1983a(int[] iArr, DrawableCreator drawableCreator) {
            this.f6544a = iArr;
            this.f6545b = drawableCreator;
        }
    }
}
