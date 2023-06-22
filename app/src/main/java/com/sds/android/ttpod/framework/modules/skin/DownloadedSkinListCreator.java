package com.sds.android.ttpod.framework.modules.skin;

import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.p106a.SkinUtils;
import java.io.File;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.e */
/* loaded from: classes.dex */
public class DownloadedSkinListCreator implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        m3602a();
    }

    /* renamed from: a */
    private void m3602a() {
        ArrayList arrayList = null;
        File[] m4650a = SkinUtils.m4650a();
        if (m4650a != null && m4650a.length > 0) {
            arrayList = new ArrayList();
            for (File file : m4650a) {
                SkinItem skinItem = new SkinItem(file.getAbsolutePath(), 0);
                skinItem.m3573a(new SkinInfoLoader(skinItem.m3571b()).m3576a());
                arrayList.add(skinItem);
            }
        }
        CommandCenter.m4607a().m4595b(new Command(CommandID.UPDATE_DOWNLOADED_SKIN_LIST, arrayList), ModuleID.SKIN);
    }
}
