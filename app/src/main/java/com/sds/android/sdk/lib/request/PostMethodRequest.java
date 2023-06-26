package com.sds.android.sdk.lib.request;

import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.LogUtils;
import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONException;
import org.json.JSONObject;


/* renamed from: com.sds.android.sdk.lib.request.k */
/* loaded from: classes.dex */
public class PostMethodRequest<R extends BaseResult> extends MethodRequest<R> {

    /* renamed from: a */
    private HashMap<String, Object> f2419a;

    public PostMethodRequest(Class<R> cls, String str, String str2) {
        super(cls, str, str2);
        this.f2419a = new HashMap<>();
    }

    public PostMethodRequest(Class<R> cls, String str) {
        super(cls, str, null);
        this.f2419a = new HashMap<>();
    }

    /* renamed from: a */
    public PostMethodRequest<R> m8553a(String str, File file) {
        this.f2419a.put(str, file);
        clear();
        return this;
    }

    /* renamed from: a */
    public PostMethodRequest<R> m8552a(String str, Object obj) {
        this.f2419a.put(str, obj);
        clear();
        return this;
    }

    @Override // com.sds.android.sdk.lib.request.Request
    /* renamed from: a */
    protected HttpRequest.Response doHttpRequest(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2, HashMap<String, Object> hashMap3) {
        try {
            String m8551a = m8551a(hashMap2, hashMap3);
            LogUtils.debug("MethodRequest", "post url: %s", str);
            LogUtils.debug("MethodRequest", "post argument: %s", m8551a);
            if (this.f2419a.size() > 0) {
                Charset forName = Charset.forName("UTF-8");
                MultipartEntity multipartEntity = new MultipartEntity();
                multipartEntity.addPart("json_data", new StringBody(m8551a, forName));
                for (String str2 : this.f2419a.keySet()) {
                    Object obj = this.f2419a.get(str2);
                    if (obj instanceof File) {
                        multipartEntity.addPart(str2, new FileBody((File) obj));
                    } else {
                        multipartEntity.addPart(str2, new StringBody(obj.toString(), forName));
                    }
                }
                return HttpRequest.m8712a(str, hashMap, multipartEntity);
            }
            return HttpRequest.m8714a(str, hashMap, m8551a);
        } catch (Exception e) {
            LogUtils.error("MethodRequest", "%s create arguments error, cause by %s", e.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    protected String m8551a(HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        m8550a(jSONObject);
        m8548b(jSONObject, hashMap2);
        m8549a(jSONObject, hashMap);
        return jSONObject.toString();
    }

    /* renamed from: b */
    private void m8548b(JSONObject jSONObject, HashMap<String, Object> hashMap) throws JSONException {
        if (hashMap != null && hashMap.size() > 0) {
            for (String str : hashMap.keySet()) {
                jSONObject.put(str, hashMap.get(str));
            }
        }
    }

    /* renamed from: a */
    protected void m8550a(JSONObject jSONObject) throws JSONException {
        jSONObject.put("method", getMethod());
    }

    /* renamed from: a */
    protected void m8549a(JSONObject jSONObject, HashMap<String, Object> hashMap) throws JSONException {
        if (hashMap.size() > 0) {
            JSONObject jSONObject2 = new JSONObject();
            for (String str : hashMap.keySet()) {
                jSONObject2.put(str, hashMap.get(str));
            }
            jSONObject.put("args", jSONObject2);
        }
    }
}
