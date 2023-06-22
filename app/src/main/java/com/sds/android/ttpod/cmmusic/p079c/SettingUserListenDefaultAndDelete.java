package com.sds.android.ttpod.cmmusic.p079c;

import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* renamed from: com.sds.android.ttpod.cmmusic.c.i */
/* loaded from: classes.dex */
public class SettingUserListenDefaultAndDelete {
    /* renamed from: a */
    public static String m7317a(String str) {
        try {
            List<NameValuePair> m7335a = BaseNameValue.m7335a();
            m7335a.add(new BasicNameValuePair("crbtId", str));
            JSONObject jSONObject = new JSONObject(BaseNameValue.m7333a("http://open.ttpod.com/api/cl/query", m7335a, "/1.0/crbt/box/default"));
            jSONObject.getString("resCode");
            return jSONObject.getString("resMsg");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
