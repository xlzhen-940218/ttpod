package com.sds.android.sdk.lib.util;

import java.util.ArrayList;
import java.util.Map;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/* renamed from: com.sds.android.sdk.lib.util.m */
/* loaded from: classes.dex */
public class UrlUtils {
    /* renamed from: a */
    public static String m8333a(String str, Map<String, Object> map) {
        if (map != null && map.size() != 0) {
            ArrayList arrayList = new ArrayList(map.size());
            for (String str2 : map.keySet()) {
                arrayList.add(new BasicNameValuePair(str2, String.valueOf(map.get(str2))));
            }
            StringBuilder sb = new StringBuilder(str);
            if (arrayList.size() > 0) {
                sb.append(sb.indexOf("?") == -1 ? "?" : "&").append(URLEncodedUtils.format(arrayList, "UTF-8"));
            }
            return sb.toString();
        }
        return str;
    }

    /* renamed from: a */
    public static String m8334a(String str) {
        if (!StringUtils.isEmpty(str) && str.indexOf(63) > 0) {
            return str.substring(0, str.indexOf(63));
        }
        return str;
    }
}
