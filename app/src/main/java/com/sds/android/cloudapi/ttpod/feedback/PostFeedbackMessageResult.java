package com.sds.android.cloudapi.ttpod.feedback;

import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.sdk.lib.p060b.BaseResultRest;

/* renamed from: com.sds.android.cloudapi.ttpod.b.c */
/* loaded from: classes.dex */
public class PostFeedbackMessageResult extends BaseResultRest<FeedbackMessage> {
    public PostFeedbackMessageResult(BaseResultRest baseResultRest) {
        setCode(baseResultRest.getCode());
        setContent(baseResultRest.getContent());
        m8682a(baseResultRest.m8681b());
    }

    @Override // com.sds.android.sdk.lib.p060b.BaseResultRest
    /* renamed from: a */
    public boolean isConnected(int code) {
        return 201 == code;
    }
}
