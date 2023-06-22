package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class EqualizerAnimationWaveView extends EqualizerIndicatorWaveView {

    /* renamed from: c */
    private float[][] f8108c;

    /* renamed from: d */
    private Handler f8109d;

    public EqualizerAnimationWaveView(Context context) {
        this(context, null);
        setWillNotDraw(false);
    }

    public EqualizerAnimationWaveView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        setWillNotDraw(false);
    }

    public EqualizerAnimationWaveView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8109d = new Handler() { // from class: com.sds.android.ttpod.widget.audioeffect.EqualizerAnimationWaveView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                EqualizerAnimationWaveView.this.setWaveValue(EqualizerAnimationWaveView.this.f8108c[message.arg1]);
                if (message.arg1 + 1 < 6) {
                    EqualizerAnimationWaveView.this.f8109d.sendMessageDelayed(EqualizerAnimationWaveView.this.f8109d.obtainMessage(100, message.arg1 + 1, 0), 50L);
                }
            }
        };
        this.f8108c = (float[][]) Array.newInstance(Float.TYPE, 6, 10);
        setWillNotDraw(false);
    }

    public void setAnimationWaveValue(short[] sArr) {
        this.f8109d.removeMessages(100);
        for (int i = 0; i < 10; i++) {
            m1417a(this.f8111a[i], sArr[i], this.f8108c, i);
        }
        setWaveValue(this.f8108c[0]);
        this.f8109d.sendMessageDelayed(this.f8109d.obtainMessage(100, 1, 0), 50L);
    }

    /* renamed from: a */
    private void m1417a(float f, float f2, float[][] fArr, int i) {
        float f3 = (f2 - f) / 6.0f;
        for (int i2 = 0; i2 < 6; i2++) {
            fArr[i2][i] = ((i2 + 1) * f3) + f;
        }
    }

    @Override // com.sds.android.ttpod.widget.audioeffect.EqualizerIndicatorWaveView, com.sds.android.ttpod.widget.audioeffect.EqualizerBaseWaveView
    /* renamed from: a */
    public void mo1411a(int i, float f) {
        this.f8109d.removeMessages(100);
        super.mo1411a(i, f);
    }
}
