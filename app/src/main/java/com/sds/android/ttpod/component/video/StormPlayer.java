package com.sds.android.ttpod.component.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.DownloadUtils;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import java.io.File;

/* renamed from: com.sds.android.ttpod.component.video.d */
/* loaded from: classes.dex */
public class StormPlayer implements VideoPlayerInterface {

    /* renamed from: a */
    private static String f4889a;

    public StormPlayer() {
    }

    public StormPlayer(String str) {
        f4889a = str;
    }

    @Override // com.sds.android.ttpod.component.video.VideoPlayerInterface
    /* renamed from: a */
    public void mo5782a(Context context, String str, String str2) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.storm.smart");
        if (launchIntentForPackage != null) {
            launchIntentForPackage.setData(Uri.parse(str));
            launchIntentForPackage.setAction("android.intent.action.VIEW");
            launchIntentForPackage.setFlags(32768);
            Bundle bundle = new Bundle();
            bundle.putString("title", str2);
            launchIntentForPackage.putExtras(bundle);
            ((Activity) context).startActivityForResult(launchIntentForPackage, 1);
            LogUtils.debug("StormPlayer", "storm play......");
        }
    }

    /* renamed from: a */
    public static boolean m5787a(Context context) {
        return m5786a(context, "com.storm.smart") >= 60;
    }

    @Override // com.sds.android.ttpod.component.video.VideoPlayerInterface
    /* renamed from: a */
    public void mo5783a() {
        String m5784b = m5784b();
        if (!StringUtils.isEmpty(m5784b)) {
            m5785a(m5784b);
        }
    }

    @Override // com.sds.android.ttpod.component.video.VideoPlayerInterface
    /* renamed from: c */
    public String mo5781c() {
        return "com.storm.smart";
    }

    /* renamed from: b */
    private static String m5784b() {
        if (EnvironmentUtils.CPU.cpuFamily() != 1) {
            return "";
        }
        if ((EnvironmentUtils.CPU.cpuFeatures() & 4) != 0) {
            return "neon";
        }
        return "v" + EnvironmentUtils.CPU.armArch();
    }

    /* renamed from: a */
    private static void m5785a(String str) {
        if (!StringUtils.isEmpty(f4889a)) {
            String replace = f4889a.replace("AndroidStorm_", "AndroidStorm_" + str);
            CommandCenter.getInstance().postInvokeResult(new Command(CommandID.ADD_DOWNLOAD_TASK, DownloadUtils.m4760a(replace, TTPodConfig.getAppPath() + File.separator + FileUtils.getFilename(replace), 0L, "StormPlugin", DownloadTaskInfo.TYPE_APP, true, "stormvideo")));
        }
    }

    /* renamed from: a */
    private static int m5786a(Context context, String str) {
        int i;
        Exception e;
        String str2 = null;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            str2 = packageInfo.versionName;
            i = packageInfo.versionCode;
        } catch (Exception e2) {
            i = 0;
            e = e2;
        }
        try {
            LogUtils.debug("StormPlayer", "version name:" + str2 + " code: " + i);
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return i;
        }
        return i;
    }
}
