package com.sds.android.ttpod.cmmusic.p079c;

import com.sds.android.ttpod.cmmusic.p078b.SondContentInfo;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/* renamed from: com.sds.android.ttpod.cmmusic.c.e */
/* loaded from: classes.dex */
public class HotActivityContentJson {
    /* renamed from: a */
    public static List<SondContentInfo> m7326a(String str) {
        try {
            return m7324b("http://open.ttpod.com/api/cailing/get/" + str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static List<SondContentInfo> m7325a(String str, Integer num) {
        ArrayList arrayList = new ArrayList();
        try {
            return m7324b("http://open.ttpod.com/api/cailing/get/" + str + "/" + num);
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    /* renamed from: b */
    private static List<SondContentInfo> m7324b(String str) {
        try {
            HttpResponse execute = new DefaultHttpClient().execute(new HttpPost(str));
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
