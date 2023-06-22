package com.sds.android.ttpod.widget.p141b;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.core.view.ViewCompat;

/* renamed from: com.sds.android.ttpod.widget.b.b */
/* loaded from: classes.dex */
public class SimpleFoldShading implements FoldShading {

    /* renamed from: a */
    private final Paint f8239a = new Paint();

    public SimpleFoldShading() {
        this.f8239a.setColor(ViewCompat.MEASURED_STATE_MASK);
    }

    @Override // com.sds.android.ttpod.widget.p141b.FoldShading
    /* renamed from: a */
    public void mo1363a(Canvas canvas, Rect rect, float f, int i) {
    }

    @Override // com.sds.android.ttpod.widget.p141b.FoldShading
    /* renamed from: b */
    public void mo1362b(Canvas canvas, Rect rect, float f, int i) {
        float m1364a = m1364a(f, i);
        if (m1364a > 0.0f) {
            this.f8239a.setAlpha((int) (m1364a * 192.0f));
            canvas.drawRect(rect, this.f8239a);
        }
    }

    /* renamed from: a */
    private float m1364a(float f, int i) {
        if (i == 48) {
            if (f <= -90.0f || f >= 0.0f) {
                return 0.0f;
            }
            return (-f) / 90.0f;
        } else if (f <= 0.0f || f >= 90.0f) {
            return 0.0f;
        } else {
            return f / 90.0f;
        }
    }
}
