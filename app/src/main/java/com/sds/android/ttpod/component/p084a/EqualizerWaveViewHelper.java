package com.sds.android.ttpod.component.p084a;

import com.sds.android.ttpod.widget.audioeffect.EqualizerAnimationWaveView;

/* renamed from: com.sds.android.ttpod.component.a.c */
/* loaded from: classes.dex */
public class EqualizerWaveViewHelper {
    /* renamed from: a */
    public static void m7147a(EqualizerAnimationWaveView equalizerAnimationWaveView, short[] sArr) {
        if (equalizerAnimationWaveView != null && sArr != null && sArr.length == 10) {
            equalizerAnimationWaveView.setWaveShadowColor(-66);
            equalizerAnimationWaveView.setWaveShadowRadius(1.0f);
            equalizerAnimationWaveView.setWaveColor(-1);
            equalizerAnimationWaveView.setWaveStrokeWidth(2);
            equalizerAnimationWaveView.setWaveValue(sArr);
        }
    }
}
