package com.sds.android.cloudapi.ttpod.p056b;

import com.google.gson.reflect.TypeToken;
import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.sdk.lib.p060b.BaseResultRest;
import com.sds.android.sdk.lib.p060b.DataListResultRest;
import com.sds.android.sdk.lib.util.JSONUtils;
import java.util.ArrayList;

/* renamed from: com.sds.android.cloudapi.ttpod.b.a */
/* loaded from: classes.dex */
public class GetFeedbackMessageResult extends DataListResultRest<FeedbackMessage> {
    public GetFeedbackMessageResult(BaseResultRest baseResultRest) {
        m8680b(baseResultRest.m8678c());
        m8679b(baseResultRest.m8676e());
        m8682a(baseResultRest.m8681b());
    }

    @Override // com.sds.android.sdk.lib.p060b.DataListResultRest
    /* renamed from: a */
    public ArrayList<FeedbackMessage> mo8674a() {
        ArrayList<FeedbackMessage> arrayList = (ArrayList) JSONUtils.fromJson(m8676e(), new TypeToken<ArrayList<FeedbackMessage>>() { // from class: com.sds.android.cloudapi.ttpod.b.a.1
        }.getType());
        return arrayList == null ? new ArrayList<>(0) : arrayList;
    }
}
