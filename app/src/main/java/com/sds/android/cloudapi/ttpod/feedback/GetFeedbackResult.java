package com.sds.android.cloudapi.ttpod.feedback;

import com.google.gson.reflect.TypeToken;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.p060b.BaseResultRest;
import com.sds.android.sdk.lib.p060b.DataListResultRest;
import com.sds.android.sdk.lib.util.JSONUtils;
import java.util.ArrayList;

/* renamed from: com.sds.android.cloudapi.ttpod.b.b */
/* loaded from: classes.dex */
public class GetFeedbackResult extends DataListResultRest<FeedbackItem> {
    public GetFeedbackResult(BaseResultRest baseResultRest) {
        setCode(baseResultRest.getCode());
        setContent(baseResultRest.getContent());
        m8682a(baseResultRest.m8681b());
    }

    @Override // com.sds.android.sdk.lib.p060b.DataListResultRest
    /* renamed from: a */
    public ArrayList<FeedbackItem> getData() {
        ArrayList<FeedbackItem> arrayList = (ArrayList) JSONUtils.fromJson(getContent(), new TypeToken<ArrayList<FeedbackItem>>() { // from class: com.sds.android.cloudapi.ttpod.b.b.1
        }.getType());
        return arrayList == null ? new ArrayList<>(0) : arrayList;
    }
}
