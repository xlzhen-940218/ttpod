package com.sds.android.ttpod.cmmusic.p079c;

import com.sds.android.ttpod.cmmusic.p078b.SondContentInfo;
import com.sds.android.ttpod.cmmusic.p081e.BaseDeviceGetInfo;
import com.sds.android.ttpod.cmmusic.p081e.GetDeviceInfo;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.sds.android.ttpod.cmmusic.c.b */
/* loaded from: classes.dex */
public class BaseNameValue {
    /* renamed from: a */
    public static String m7333a(String str, List<NameValuePair> list, String str2) {
        try {
            HttpPost httpPost = new HttpPost(str);
            list.add(new BasicNameValuePair("url", str2));
            httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            HttpResponse execute = new DefaultHttpClient().execute(httpPost);
            if (execute.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(execute.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* renamed from: a */
    public static List<NameValuePair> m7335a() {
        return m7332a(true);
    }

    /* renamed from: b */
    public static List<NameValuePair> m7331b() {
        return m7332a(false);
    }

    /* renamed from: a */
    private static List<NameValuePair> m7332a(boolean z) {
        ArrayList arrayList = new ArrayList();
        try {
            BaseDeviceGetInfo m7273a = GetDeviceInfo.m7273a();
            if (z) {
                arrayList.add(new BasicNameValuePair("IMSI", m7273a.m7297b()));
            } else {
                arrayList.add(new BasicNameValuePair("IMSI", m7273a.m7299a()));
            }
            arrayList.add(new BasicNameValuePair("appID", m7273a.m7295c()));
            arrayList.add(new BasicNameValuePair("pubKey", m7273a.m7293d()));
            arrayList.add(new BasicNameValuePair("netMode", m7273a.m7291e()));
            arrayList.add(new BasicNameValuePair("packageName", m7273a.m7289f()));
            arrayList.add(new BasicNameValuePair("version", m7273a.m7287g()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /* renamed from: a */
    public static List<SondContentInfo> m7334a(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("data");
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= jSONArray.length()) {
                    return arrayList;
                }
                JSONObject jSONObject = (JSONObject) jSONArray.get(i2);
                arrayList.add(new SondContentInfo(jSONObject.getString("resource_name"), jSONObject.getString("resource_songer"), jSONObject.getString("cailing_id"), jSONObject.getString("zhenling_id"), jSONObject.getString("music_id"), jSONObject.getString("time_out")));
                i = i2 + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }
}
