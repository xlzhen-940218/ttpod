package com.sds.android.ttpod.cmmusic.p079c;

import com.sds.android.ttpod.cmmusic.p081e.BaseDeviceGetInfo;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* renamed from: com.sds.android.ttpod.cmmusic.c.d */
/* loaded from: classes.dex */
public class GetPreListen {
    /* renamed from: a */
    public static BaseDeviceGetInfo m7327a(String str) {
        BaseDeviceGetInfo baseDeviceGetInfo;
        try {
            List<NameValuePair> m7331b = BaseNameValue.m7331b();
            m7331b.add(new BasicNameValuePair("musicId", str));
            JSONObject jSONObject = new JSONObject(BaseNameValue.m7333a("http://open.ttpod.com/api/cl/query", m7331b, "/1.0/crbt/prelisten"));
            String string = jSONObject.getString("resCode");
            if (string.equals("999011")) {
                String string2 = jSONObject.getString("resMsg");
                baseDeviceGetInfo = new BaseDeviceGetInfo();
                baseDeviceGetInfo.m7298a(string);
                baseDeviceGetInfo.m7296b(string2);
            } else {
                String string3 = jSONObject.getString("resMsg");
                String string4 = jSONObject.getString("streamUrl");
                baseDeviceGetInfo = new BaseDeviceGetInfo();
                baseDeviceGetInfo.m7298a(string);
                baseDeviceGetInfo.m7296b(string3);
                baseDeviceGetInfo.m7292d(string4);
            }
            return baseDeviceGetInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseDeviceGetInfo();
        }
    }
}
