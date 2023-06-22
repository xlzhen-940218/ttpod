package com.sds.android.ttpod.cmmusic.p079c;

import com.sds.android.ttpod.cmmusic.p078b.SondContentInfo;
import com.sds.android.ttpod.cmmusic.p080d.CmmusicStatistic;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/* renamed from: com.sds.android.ttpod.cmmusic.c.g */
/* loaded from: classes.dex */
public class MusicSerchQuery {
    /* renamed from: a */
    public static List<SondContentInfo> m7321a(String str, String str2, String str3) {
        CmmusicStatistic.m7310b(str);
        try {
            HttpPost httpPost = new HttpPost("http://open.ttpod.com/api/cailing/search/" + str);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("IMEI", str2));
            arrayList.add(new BasicNameValuePair("IMSI", str3));
            httpPost.setEntity(new UrlEncodedFormEntity(arrayList, "UTF-8"));
            HttpResponse execute = new DefaultHttpClient().execute(httpPost);
            if (execute.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            return BaseNameValue.m7334a(EntityUtils.toString(execute.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static List<SondContentInfo> m7320a(String str, String str2, String str3, String str4) {
        try {
            HttpPost httpPost = new HttpPost("http://open.ttpod.com/api/cailing/search/" + str + "/" + str4);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("IMEI", str2));
            arrayList.add(new BasicNameValuePair("IMSI", str3));
            httpPost.setEntity(new UrlEncodedFormEntity(arrayList, "UTF-8"));
            HttpResponse execute = new DefaultHttpClient().execute(httpPost);
            if (execute.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            return BaseNameValue.m7334a(EntityUtils.toString(execute.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
