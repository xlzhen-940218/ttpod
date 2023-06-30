package com.sds.android.ttpod.share.p139d;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;


/* renamed from: com.sds.android.ttpod.share.d.a */
/* loaded from: classes.dex */
public class AccessTokenUtil {
    /* renamed from: a */
    public static void m1940a(Context context, String str, Bundle bundle) {
        try {
            String string = bundle.getString("access_token");
            String string2 = bundle.getString("expires_in");
            String string3 = bundle.getString("openid");
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putString("access_token", string);
            edit.putString("expires_in", string2);
            edit.putString("openid", string3);
            edit.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public static void m1939b(Context context, String str, Bundle bundle) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
            String string = sharedPreferences.getString("access_token", "");
            String string2 = sharedPreferences.getString("expires_in", "0");
            String string3 = sharedPreferences.getString("openid", "");
            bundle.putString("access_token", string);
            bundle.putString("expires_in", string2);
            bundle.putString("openid", string3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m1941a(Context context, String str) {
        context.getSharedPreferences(str, 0).edit().clear().commit();
    }
}
