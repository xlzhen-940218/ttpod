package com.sds.android.sdk.lib.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/* renamed from: com.sds.android.sdk.lib.util.m */
/* loaded from: classes.dex */
public class UrlUtils {
    /* renamed from: a */
    public static String buildGetParamsUrl(String url, HashMap<String, Object> paramsMaps) {
        if (paramsMaps != null && paramsMaps.size() != 0) {
            ArrayList arrayList = new ArrayList(paramsMaps.size());
            for (String str2 : paramsMaps.keySet()) {
                arrayList.add(new BasicNameValuePair(str2, String.valueOf(paramsMaps.get(str2))));
            }
            StringBuilder sb = new StringBuilder(url);
            if (arrayList.size() > 0) {
                sb.append(sb.indexOf("?") == -1 ? "?" : "&").append(URLEncodedUtils.format(arrayList, "UTF-8"));
            }
            return sb.toString();
        }
        return url;
    }

    /* renamed from: a */
    public static String m8334a(String str) {
        if (!StringUtils.isEmpty(str) && str.indexOf(63) > 0) {
            return str.substring(0, str.indexOf(63));
        }
        return str;
    }
}
