package com.sds.android.ttpod.cmmusic.p079c;


import java.util.ArrayList;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.sds.android.ttpod.cmmusic.c.a */
/* loaded from: classes.dex */
public class AdSeatInfoQuery {
    /* renamed from: a */
    public static ArrayList<HashMap<String, String>> m7336a() {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        try {
            HttpPost httpPost = new HttpPost("http://open.ttpod.com/api/cailing/getad");
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new BasicNameValuePair("code", "clindex"));
            httpPost.setEntity(new UrlEncodedFormEntity(arrayList2, "UTF-8"));
            HttpResponse execute = new DefaultHttpClient().execute(httpPost);
            if (execute.getStatusLine().getStatusCode() == 200) {
                JSONArray jSONArray = new JSONObject(EntityUtils.toString(execute.getEntity())).getJSONArray("data");
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= jSONArray.length()) {
                        break;
                    }
                    JSONObject jSONObject = (JSONObject) jSONArray.get(i2);
                    String string = jSONObject.getString("href");
                    String string2 = jSONObject.getString("img");
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("href", string);
                    hashMap.put("img", string2);
                    arrayList.add(hashMap);
                    i = i2 + 1;
                }
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }
}
