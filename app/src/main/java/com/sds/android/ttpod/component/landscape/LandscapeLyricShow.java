package com.sds.android.ttpod.component.landscape;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;

/* loaded from: classes.dex */
public class LandscapeLyricShow extends LyricView {
    public LandscapeLyricShow(Context context) {
        super(context);
        m6372j();
    }

    public LandscapeLyricShow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m6372j();
    }

    public LandscapeLyricShow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m6372j();
    }

    /* renamed from: j */
    private void m6372j() {
        setColorNormal(-1);
        setColorHighlight(-14434053);
        setTextSizeHighlight(18.0f);
        setTextSizeNormal(18.0f);
        setAlign(Paint.Align.LEFT);
        setEnabled(true);
    }
}
