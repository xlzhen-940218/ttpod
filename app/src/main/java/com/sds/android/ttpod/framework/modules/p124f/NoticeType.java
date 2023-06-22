package com.sds.android.ttpod.framework.modules.p124f;

/* renamed from: com.sds.android.ttpod.framework.modules.f.e */
/* loaded from: classes.dex */
public enum NoticeType {
    COMMENT(1),
    REPOST(2);
    
    private int mValue;

    NoticeType(int i) {
        this.mValue = i;
    }

    public int value() {
        return this.mValue;
    }
}
