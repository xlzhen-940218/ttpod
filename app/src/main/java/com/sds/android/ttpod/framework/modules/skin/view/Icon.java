package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import java.util.Vector;

/* loaded from: classes.dex */
public class Icon extends androidx.appcompat.widget.AppCompatImageView {

    /* renamed from: a */
    private int state;

    /* renamed from: b */
    private Vector<Drawable> stateImageVector;

    /* renamed from: c */
    private StateChangedListener stateChangedListener;

    /* renamed from: com.sds.android.ttpod.framework.modules.skin.view.Icon$a */
    /* loaded from: classes.dex */
    public interface StateChangedListener {
        /* renamed from: a */
        void changed(int index);
    }

    public Icon(Context context) {
        super(context);
        this.state = 0;
        this.stateImageVector = new Vector<>();
    }

    public Icon(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Icon(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.state = 0;
        this.stateImageVector = new Vector<>();
    }

    public void setStateChangedListener(StateChangedListener stateChangedListener) {
        this.stateChangedListener = stateChangedListener;
    }

    public void setState(int state) {
        if (state < 0) {
            state = 0;
        }
        if (state >= this.stateImageVector.size()) {
            state = this.stateImageVector.size() - 1;
        }
        if (state >= 0) {
            this.state = state;
            setImageDrawable(this.stateImageVector.get(this.state));
        }
        if (this.stateChangedListener != null) {
            this.stateChangedListener.changed(state);
        }
    }

    public int getState() {
        return this.state;
    }

    /* renamed from: a */
    public void addStateImage(int index, Drawable drawable) {
        int state;
        this.stateImageVector.add(index, drawable);
        if (index < this.state) {
            state = this.state;
            this.state = state + 1;
        } else {
            state = this.state;
        }
        this.state = state;
        setState(this.state);
    }

    /* renamed from: a */
    public void m3488a(Drawable drawable) {
        this.stateImageVector.add(drawable);
        setState(this.state);
    }
}
