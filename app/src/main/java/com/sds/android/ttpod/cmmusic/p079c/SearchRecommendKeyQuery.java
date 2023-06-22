package com.sds.android.ttpod.cmmusic.p079c;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.sds.android.ttpod.cmmusic.c.h */
/* loaded from: classes.dex */
public class SearchRecommendKeyQuery {
    /* renamed from: a */
    public static ArrayList<HashMap<String, String>> m7319a() {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        try {
            HttpResponse execute = new DefaultHttpClient().execute(new HttpPost("http://open.ttpod.com/api/cailing/get/tag_5"));
            if (execute.getStatusLine().getStatusCode() == 200) {
                JSONArray jSONArray = new JSONObject(EntityUtils.toString(execute.getEntity())).getJSONArray("data");
                for (int i = 0; i < 4; i++) {
                    String string = ((JSONObject) jSONArray.get(i)).getString("resource_name");
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("recommendKey", string);
                    arrayList.add(hashMap);
                }
                if (arrayList.size() > 0) {
                    return arrayList;
                }
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (arrayList.size() <= 0) {
            arrayList = null;
        }
        return arrayList;
    }

    /* renamed from: a */
    public static ArrayList<HashMap<String, String>> m7318a(String str) {
        String str2 = "http://open.ttpod.com/api/cailing/search/" + str;
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        try {
            HttpResponse execute = new DefaultHttpClient().execute(new HttpPost(str2));
            if (execute.getStatusLine().getStatusCode() == 200) {
                JSONArray jSONArray = new JSONObject(EntityUtils.toString(execute.getEntity())).getJSONArray("data");
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= 4) {
                        return arrayList;
                    }
                    String string = ((JSONObject) jSONArray.get(i2)).getString("resource_name");
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("recommendKey", string);
                    arrayList.add(hashMap);
                    i = i2 + 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList.size() > 0 ? arrayList : null;
    }
}
