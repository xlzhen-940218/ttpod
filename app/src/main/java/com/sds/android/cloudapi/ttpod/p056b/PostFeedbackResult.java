package com.sds.android.cloudapi.ttpod.p056b;

import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.p060b.BaseResultRest;

/* renamed from: com.sds.android.cloudapi.ttpod.b.d */
/* loaded from: classes.dex */
public class PostFeedbackResult extends BaseResultRest<FeedbackItem> {
    public PostFeedbackResult(BaseResultRest baseResultRest) {
        m8680b(baseResultRest.m8678c());
        m8679b(baseResultRest.m8676e());
        m8682a(baseResultRest.m8681b());
    }

    @Override // com.sds.android.sdk.lib.p060b.BaseResultRest
    /* renamed from: a */
    public boolean mo8683a(int i) {
        return 201 == i;
    }
}
