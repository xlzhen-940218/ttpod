package com.sds.android.ttpod.component.video;

import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.DownloadUtils;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import java.io.File;

/* renamed from: com.sds.android.ttpod.component.video.a */
/* loaded from: classes.dex */
public abstract class BasePlayer implements VideoPlayerInterface {

    /* renamed from: a */
    private static String f4888a;

    /* renamed from: b */
    protected abstract String mo5788b();

    public BasePlayer() {
    }

    public BasePlayer(String str) {
        f4888a = str;
    }

    @Override // com.sds.android.ttpod.component.video.VideoPlayerInterface
    /* renamed from: a */
    public void mo5783a() {
        if (!StringUtils.isEmpty(f4888a)) {
            CommandCenter.getInstance().postInvokeResult(new Command(CommandID.ADD_DOWNLOAD_TASK, DownloadUtils.m4760a(f4888a, TTPodConfig.getAppPath() + File.separator + FileUtils.getFilename(f4888a), 0L, mo5788b(), DownloadTaskInfo.TYPE_APP, true, "stormvideo")));
        }
    }
}
