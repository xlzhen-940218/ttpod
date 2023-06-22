package com.sds.android.ttpod.cmmusic.p079c;

import com.sds.android.ttpod.cmmusic.p081e.BaseDeviceGetInfo;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* renamed from: com.sds.android.ttpod.cmmusic.c.f */
/* loaded from: classes.dex */
public class ListenOrderInfo {
    /* renamed from: a */
    public static BaseDeviceGetInfo m7322a(String str, String str2, String str3) {
        try {
            List<NameValuePair> m7335a = BaseNameValue.m7335a();
            m7335a.add(new BasicNameValuePair("musicId", str));
            JSONObject jSONObject = new JSONObject(BaseNameValue.m7333a("http://open.ttpod.com/api/cl/query", m7335a, "/1.0/crbt/prelisten"));
            String string = jSONObject.getString("resCode");
            String string2 = jSONObject.getString("resMsg");
            String string3 = jSONObject.getString("price");
            String string4 = jSONObject.getString("invalidDate");
            BaseDeviceGetInfo baseDeviceGetInfo = new BaseDeviceGetInfo();
            baseDeviceGetInfo.m7298a(string);
            baseDeviceGetInfo.m7296b(string2);
            baseDeviceGetInfo.m7286g(str2);
            baseDeviceGetInfo.m7284h(str3);
            baseDeviceGetInfo.m7294c(str);
            baseDeviceGetInfo.m7288f(string3);
            baseDeviceGetInfo.m7290e(string4);
            return baseDeviceGetInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static BaseDeviceGetInfo m7323a(BaseDeviceGetInfo baseDeviceGetInfo) {
        try {
            List<NameValuePair> m7335a = BaseNameValue.m7335a();
            m7335a.add(new BasicNameValuePair("musicId", baseDeviceGetInfo.m7282j()));
            JSONObject jSONObject = new JSONObject(BaseNameValue.m7333a("http://open.ttpod.com/api/cl/query", m7335a, "/1.0/crbt/order"));
            String string = jSONObject.getString("resCode");
            String string2 = jSONObject.getString("resMsg");
            BaseDeviceGetInfo baseDeviceGetInfo2 = new BaseDeviceGetInfo();
            baseDeviceGetInfo2.m7298a(string);
            baseDeviceGetInfo2.m7296b(string2);
            return baseDeviceGetInfo2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
