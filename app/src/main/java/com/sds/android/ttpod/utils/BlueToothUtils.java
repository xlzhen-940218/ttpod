package com.sds.android.ttpod.utils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.a.c */
/* loaded from: classes.dex */
public class BlueToothUtils {
    /* renamed from: a */
    public static void m8308a(Context context, File[] fileArr) {
        if (fileArr != null && fileArr.length > 0) {
            if (m8309a(context)) {
                m8307b(context, fileArr);
            } else {
                PopupsUtils.m6760a((int) R.string.bluetooth_unsupport);
            }
        }
    }

    /* renamed from: a */
    public static boolean m8309a(Context context) {
        return BluetoothAdapter.getDefaultAdapter() != null;
    }

    /* renamed from: b */
    public static void m8307b(Context context, File[] fileArr) {
        String str;
        String str2;
        Intent intent = new Intent();
        intent.setType("*/*");
        if (fileArr.length > 1) {
            ArrayList arrayList = new ArrayList(fileArr.length);
            for (File file : fileArr) {
                arrayList.add(Uri.fromFile(file));
            }
            intent.putExtra("android.intent.extra.STREAM", arrayList);
            intent.setAction("android.intent.action.SEND_MULTIPLE");
        } else {
            intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(fileArr[0]));
            intent.setAction("android.intent.action.SEND");
        }
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intent, 0).iterator();
        while (true) {
            if (!it.hasNext()) {
                str = "";
                str2 = "";
                break;
            }
            ResolveInfo next = it.next();
            if (next.activityInfo.packageName.contains("bluetooth")) {
                str2 = next.activityInfo.packageName;
                str = next.activityInfo.name;
                break;
            }
        }
        intent.setClassName(str2, str);
        if (context instanceof Activity) {
            try {
                ((Activity) context).startActivityForResult(intent, 1);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
