package com.sds.android.ttpod.framework.support.search.p135a;

import com.sds.android.ttpod.framework.support.search.SearchTaskType;

/* renamed from: com.sds.android.ttpod.framework.support.search.a.c */
/* loaded from: classes.dex */
public class PictureSearchTaskInfo extends LyrPicSearchTaskBaseInfo {

    /* renamed from: a */
    private int f7285a;

    /* renamed from: b */
    private int f7286b;

    /* renamed from: c */
    private int f7287c;

    @Override // com.sds.android.ttpod.framework.support.search.p135a.LyrPicSearchTaskBaseInfo
    /* renamed from: d */
    public SearchTaskType getSearchTaskType() {
        return SearchTaskType.PICTURE_SEARCH_TASK_TYPE;
    }

    /* renamed from: l */
    public int m2185l() {
        return this.f7287c;
    }

    /* renamed from: a */
    public void m2189a(int i) {
        this.f7287c = i;
    }

    /* renamed from: b */
    public void m2188b(int i) {
        this.f7285a = i;
    }

    /* renamed from: c */
    public void m2187c(int i) {
        this.f7286b = i;
    }
}
