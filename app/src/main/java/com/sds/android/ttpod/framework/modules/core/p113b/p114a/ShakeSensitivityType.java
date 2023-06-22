package com.sds.android.ttpod.framework.modules.core.p113b.p114a;



/* renamed from: com.sds.android.ttpod.framework.modules.core.b.a.c */
/* loaded from: classes.dex */
public enum ShakeSensitivityType {
    SMOOTH_SHAKE(584),
    EASY_SHAKE(560),
    NORMAL_SHAKE(496),
    HARD_SHAKE(448),
    EXTREME_SHAKE(384);
    
    private int mValue;

    ShakeSensitivityType(int i) {
        this.mValue = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int value() {
        return this.mValue;
    }
}
