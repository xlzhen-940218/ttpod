package com.sds.android.cloudapi.ttpod.api;


import com.sds.android.cloudapi.ttpod.data.FeedbackItem;

import com.sds.android.sdk.lib.p060b.BaseResultRest;
import com.sds.android.sdk.lib.p060b.GetRequestRest;
import com.sds.android.sdk.lib.p060b.PostRequestRest;
import com.sds.android.sdk.lib.p060b.RequestRest;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.StringUtils;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.sds.android.cloudapi.ttpod.a.g */
/* loaded from: classes.dex */
public class FeedbackAPI {

    /* renamed from: a */
    private static final String f2263a = FeedbackAPI.class.getSimpleName();

    /* renamed from: b */
    private static final String f2264b = "http://api.feedback.ttpod.cn/feedback/feedback/" + m8913b() + "/threads";

    /* renamed from: c */
    private static final JSONObject f2265c = m8920a();

    /* renamed from: a */
    public static RequestRest m8915a(String str, String str2, String str3) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("ip", str2);
        hashMap.put("proposalContent", str);
        if (!StringUtils.isEmpty(str3)) {
            hashMap.put("contactWay", str3);
        }
        PostRequestRest postRequestRest = new PostRequestRest(f2264b, m8914a(hashMap));
        postRequestRest.m8666b("Content-Type", "application/json");
        return postRequestRest;
    }

    /* renamed from: a */
    public static RequestRest m8918a(Long l, String str) {
        GetRequestRest getRequestRest = new GetRequestRest(f2264b);
        if (l != null) {
            getRequestRest.m8670a("after", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date(l.longValue())));
        }
        if (!StringUtils.isEmpty(str)) {
            getRequestRest.m8670a("status", str);
        }
        return getRequestRest;
    }

    /* renamed from: a */
    public static RequestRest m8919a(Long l) {
        GetRequestRest getRequestRest = new GetRequestRest(f2264b);
        getRequestRest.m8670a("after", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date(l.longValue())));
        getRequestRest.m8670a("source", 1);
        return getRequestRest;
    }

    /* renamed from: a */
    public static FeedbackItem m8917a(String str) {
        BaseResultRest m8668b = new GetRequestRest(f2264b + "/" + str).m8668b();
        if (!m8668b.m8677d()) {
            return null;
        }
        return (FeedbackItem) JSONUtils.fromJson(m8668b.m8676e(),  FeedbackItem.class);
    }

    /* renamed from: a */
    public static RequestRest m8916a(String str, Long l, String str2) {
        GetRequestRest getRequestRest = new GetRequestRest(m8912b(str));
        if (l != null) {
            getRequestRest.m8670a("after", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date(l.longValue())));
        }
        if (!StringUtils.isEmpty(str2)) {
            getRequestRest.m8670a("isRead", str2);
        }
        return getRequestRest;
    }

    /* renamed from: b */
    public static RequestRest m8911b(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", str2);
            jSONObject.put("content", str3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PostRequestRest postRequestRest = new PostRequestRest(m8912b(str), jSONObject.toString());
        postRequestRest.m8666b("Content-Type", "application/json");
        return postRequestRest;
    }

    /* renamed from: a */
    private static JSONObject m8920a() {
        HashMap<String, Object> m8488e = EnvironmentUtils.UUIDConfig.m8488e();
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, Object> entry : m8488e.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: a */
    private static String m8914a(Map<String, Object> map) {
        try {
            JSONObject jSONObject = new JSONObject(f2265c.toString());
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    private static String m8912b(String str) {
        return f2264b + "/" + str + "/" + "messages";
    }

    /* renamed from: b */
    private static String m8913b() {
        String m8499a = EnvironmentUtils.UUIDConfig.m8499a();
        if (StringUtils.isEmpty(m8499a)) {
            return null;
        }
        try {
            return URLEncoder.encode(m8499a, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return m8499a;
        }
    }
}
