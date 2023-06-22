package com.sds.android.sdk.core.p058b;

import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.PostMethodRequest;
import com.sds.android.sdk.lib.request.Request;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.sds.android.sdk.core.b.a */
/* loaded from: classes.dex */
public class ExceptionReportAPI {
    /* renamed from: a */
    public static Request<BaseResult> m8753a(HashMap<String, Object> hashMap) {
        try {
            return new PostMethodRequest(BaseResult.class, "http://feedback.ttpod.itlily.com/bug", "bug").m8552a("json_bug", m8752b(hashMap).toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    private static JSONObject m8752b(HashMap<String, Object> hashMap) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (hashMap.size() > 0) {
            for (String str : hashMap.keySet()) {
                jSONObject.put(str, hashMap.get(str));
            }
        }
        return jSONObject;
    }
}
