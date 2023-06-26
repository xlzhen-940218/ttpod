package com.sds.android.ttpod.utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import java.io.File;

/* renamed from: com.sds.android.ttpod.a.h */
/* loaded from: classes.dex */
public class FileOpenUtils {
    /* renamed from: a */
    public static boolean m8281a(Context context, DownloadTaskInfo downloadTaskInfo) {
        String savePath = downloadTaskInfo.getSavePath();
        if (FileUtils.m8414b(savePath)) {
            Integer type = downloadTaskInfo.getType();
            if (type == DownloadTaskInfo.TYPE_AUDIO) {
                CommandCenter.getInstance().execute(new Command(CommandID.PLAY, savePath));
            } else if (type == DownloadTaskInfo.TYPE_APP) {
                return ApkUtils.m8311a(context, savePath);
            } else {
                if (type != DownloadTaskInfo.TYPE_VIDEO) {
                    return false;
                }
                //MVStatistic.m5076a("other_channel");
                VideoPlayManager.m5814a((Activity) context, Uri.fromFile(new File(savePath)).toString(), FileUtils.getFilename(savePath));
            }
            return true;
        }
        return false;
    }
}
