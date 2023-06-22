package com.sds.android.ttpod.utils;

import android.content.Context;
import android.content.Intent;

/* renamed from: com.sds.android.ttpod.a.u */
/* loaded from: classes.dex */
public class ShortcutUtil {
    /* renamed from: a */
    public static void m8234a(Context context, Class cls, int i, int i2) {
        Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intent.putExtra("duplicate", false);
        intent.putExtra("android.intent.extra.shortcut.NAME", context.getString(i2));
        intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(context.getApplicationContext(), i));
        Intent intent2 = new Intent("android.intent.action.MAIN");
        intent2.setClassName(context, cls.getName());
        intent.putExtra("android.intent.extra.shortcut.INTENT", intent2);
        context.sendBroadcast(intent);
    }
}
