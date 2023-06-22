package com.sds.android.ttpod.cmmusic.p079c;

import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.cmmusic.p078b.SondContentInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.sds.android.ttpod.cmmusic.c.j */
/* loaded from: classes.dex */
public class UserSelectListenQuery {
    /* renamed from: a */
    public static List<SondContentInfo> m7316a() {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(BaseNameValue.m7333a("http://open.ttpod.com/api/cl/query", BaseNameValue.m7335a(), "/1.0/crbt/box/query")).getJSONArray("ToneInfo");
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= jSONArray.length()) {
                    return arrayList;
                }
                JSONObject jSONObject = (JSONObject) jSONArray.get(i2);
                arrayList.add(new SondContentInfo(jSONObject.getString("toneName"), jSONObject.getString("singerName"), jSONObject.getString("toneID"), jSONObject.getString("toneValidDay")));
                i = i2 + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    /* renamed from: b */
    public static boolean m7315b() {
        return StringUtils.m8344a(GetPreListen.m7327a("600907000003273431").m7285h(), "000000");
    }
}
