package com.sds.android.ttpod.framework.modules.p124f;

/* renamed from: com.sds.android.ttpod.framework.modules.f.d */
/* loaded from: classes.dex */
public enum MusiccircleContentType {
    SINGLE_SONG(1),
    DJ(2),
    SONG_LIST(3);
    
    private int mValue;

    MusiccircleContentType(int i) {
        this.mValue = i;
    }

    public int value() {
        return this.mValue;
    }
}
