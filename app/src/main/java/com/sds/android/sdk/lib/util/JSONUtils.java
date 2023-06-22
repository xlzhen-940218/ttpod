package com.sds.android.sdk.lib.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.InputStream;
import java.lang.reflect.Type;
import org.json.JSONObject;

/* renamed from: com.sds.android.sdk.lib.util.e */
/* loaded from: classes.dex */
public class JSONUtils {

    /* renamed from: a */
    private static Gson gson = new GsonBuilder().create();

    /* renamed from: a */
    public static Gson getGson() {
        return gson;
    }

    /* renamed from: a */
    public static <T> T fromJson(String str, Class<T> cls) {
        try {
            return (T) getGson().fromJson(str, cls);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static <T> T fromJson(String str, Type type) {
        try {
            return (T) getGson().fromJson(str, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String toJson(Object obj) {
        try {
            return getGson().toJson(obj);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public static JSONObject create(InputStream inputStream) {
        try {
            return new JSONObject(StringUtils.m8347a(inputStream));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static String build(Object obj) {
        String m8342a = StringUtils.m8342a(",", obj);
        return new StringBuilder(m8342a.length() + 2).append('[').append(m8342a).append(']').toString();
    }
}
