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
    private final ArrayList<StateDrawableCreator> stateDrawableCreators = new ArrayList<>();

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.DrawableCreator
    /* renamed from: a */
    public Drawable getDrawable(Resources resources) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Iterator<StateDrawableCreator> it = this.stateDrawableCreators.iterator();
        while (it.hasNext()) {
            StateDrawableCreator next = it.next();
            stateListDrawable.addState(next.states, next.drawableCreator.getDrawable(resources));
        }
        return stateListDrawable;
    }

    /* renamed from: a */
    public void addStateDrawableCreator(int[] states, DrawableCreator drawableCreator) {
        if (states != null && drawableCreator != null) {
            this.stateDrawableCreators.add(new StateDrawableCreator(states, drawableCreator));
        }
    }

    /* compiled from: StateListDrawableCreator.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.skin.c.l$a */
    /* loaded from: classes.dex */
    private static final class StateDrawableCreator {

        /* renamed from: a */
        private int[] states;

        /* renamed from: b */
        private DrawableCreator drawableCreator;

        private StateDrawableCreator(int[] states, DrawableCreator drawableCreator) {
            this.states = states;
            this.drawableCreator = drawableCreator;
        }
    }
}
