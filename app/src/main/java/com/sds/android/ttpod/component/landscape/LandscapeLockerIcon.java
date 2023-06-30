package com.sds.android.ttpod.component.landscape;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.sds.android.ttpod.framework.modules.skin.view.Icon;

/* loaded from: classes.dex */
public class LandscapeLockerIcon extends Icon {

    /* renamed from: com.sds.android.ttpod.component.landscape.LandscapeLockerIcon$a */
    /* loaded from: classes.dex */
    public interface OnLockerStateChangeListener {
        /* renamed from: a */
        void changed(int change);
    }

    public LandscapeLockerIcon(Context context) {
        super(context);
    }

    public LandscapeLockerIcon(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LandscapeLockerIcon(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    public void setOnOffDrawable(Drawable offDrawable, Drawable onDrawable) {
        if (offDrawable != null && onDrawable != null) {
            addStateImage(0, offDrawable);
            addStateImage(1, onDrawable);
        }
    }

    /* renamed from: a */
    public void m6375a() {
        setState(1);
    }

    public void setOnLockerStateChangeListener(final OnLockerStateChangeListener interfaceC1250a) {
        if (interfaceC1250a != null) {
            setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.landscape.LandscapeLockerIcon.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (LandscapeLockerIcon.this.getState() == 0) {
                        interfaceC1250a.changed(1);
                        LandscapeLockerIcon.this.setState(1);
                        return;
                    }
                    interfaceC1250a.changed(0);
                    LandscapeLockerIcon.this.setState(0);
                }
            });
        } else {
            setOnClickListener(null);
        }
    }
}
