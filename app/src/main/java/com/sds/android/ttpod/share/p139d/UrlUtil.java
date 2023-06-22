package com.sds.android.ttpod.share.p139d;

import android.os.Bundle;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

/* renamed from: com.sds.android.ttpod.share.d.d */
/* loaded from: classes.dex */
public class UrlUtil {
    /* renamed from: a */
    public static Bundle m1926a(String str) {
        try {
            URL url = new URL(str);
            Bundle m1925b = m1925b(url.getQuery());
            m1925b.putAll(m1925b(url.getRef()));
            return m1925b;
        } catch (MalformedURLException e) {
            return new Bundle();
        }
    }

    /* renamed from: b */
    public static Bundle m1925b(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String str2 : str.split("&")) {
                String[] split = str2.split("=");
                if (split != null && split.length > 1) {
                    bundle.putString(URLDecoder.decode(split[0]), URLDecoder.decode(split[1]));
                }
            }
        }
        return bundle;
    }
}
