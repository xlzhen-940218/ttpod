package com.sds.android.ttpod.cmmusic.p079c;

import com.sds.android.ttpod.cmmusic.p081e.BaseDeviceGetInfo;
import org.json.JSONObject;

/* renamed from: com.sds.android.ttpod.cmmusic.c.c */
/* loaded from: classes.dex */
public class CaiLingFuctionOpenAndCancel {
    /* renamed from: a */
    public static BaseDeviceGetInfo m7330a() {
        try {
            return m7329a(BaseNameValue.m7333a("http://open.ttpod.com/api/cl/query", BaseNameValue.m7335a(), "/1.0/crbt/open"));
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseDeviceGetInfo();
        }
    }

    /* renamed from: b */
    public static BaseDeviceGetInfo m7328b() {
        try {
            return m7329a(BaseNameValue.m7333a("http://open.ttpod.com/api/cl/query", BaseNameValue.m7335a(), "/1.0/crbt/open/check"));
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseDeviceGetInfo();
        }
    }

    /* renamed from: a */
    private static BaseDeviceGetInfo m7329a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new BaseDeviceGetInfo(jSONObject.getString("resCode"), jSONObject.getString("resMsg"), "5");
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseDeviceGetInfo();
        }
    }
}
