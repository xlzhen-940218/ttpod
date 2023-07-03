package com.sds.android.ttpod.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.sds.android.sdk.lib.util.FileUtils;
import java.io.File;

/* renamed from: com.sds.android.ttpod.a.a */
/* loaded from: classes.dex */
public final class ApkUtils {
    /* renamed from: a */
    public static boolean m8311a(Context context, String str) {
        if (FileUtils.isFile(str)) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
            context.startActivity(intent);
            return true;
        }
        return false;
    }
}
