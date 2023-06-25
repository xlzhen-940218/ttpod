package com.sds.android.ttpod.framework.modules.skin;

import android.text.TextUtils;
import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.cloudapi.ttpod.result.OnlineBackgroundListResult;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.theme.BackgroundItem;
import com.sds.android.ttpod.framework.p106a.SkinUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b */
/* loaded from: classes.dex */
public class BackgroundListLoader implements Runnable {

    /* renamed from: a */
    private boolean f6400a;

    public BackgroundListLoader(Boolean bool) {
        this.f6400a = false;
        this.f6400a = bool.booleanValue();
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f6400a) {
            m3855a(m3856a(), CommandID.UPDATE_LOCAL_BACKGROUND_LIST);
        } else {
            m3855a(m3854b(), CommandID.UPDATE_BACKGROUND_LIST);
        }
    }

    /* renamed from: a */
    private void m3855a(ArrayList<BackgroundItem> arrayList, CommandID commandID) {
        CommandCenter.getInstance().m4595b(new Command(commandID, arrayList), ModuleID.THEME);
    }

    /* renamed from: b */
    private ArrayList<BackgroundItem> m3854b() {
        ArrayList<BackgroundItem> arrayList = new ArrayList<>();
        String m5295m = TTPodConfig.m5295m();
        String m3765a = CachedOnlineListReader.m3765a(m5295m, SkinUtils.m4645a(m5295m, "list_"), "bkg_list");
        OnlineBackgroundListResult onlineBackgroundListResult = TextUtils.isEmpty(m3765a) ? null : (OnlineBackgroundListResult) JSONUtils.fromJson(m3765a, OnlineBackgroundListResult.class);
        if (onlineBackgroundListResult == null || onlineBackgroundListResult.getSkinItems() == null) {
            return arrayList;
        }
        ArrayList<OnlineSkinItem> skinItems = onlineBackgroundListResult.getSkinItems();
        String mainUrl = onlineBackgroundListResult.getMainUrl();
        Iterator<OnlineSkinItem> it = skinItems.iterator();
        while (it.hasNext()) {
            OnlineSkinItem next = it.next();
            next.setPictureUrl(mainUrl + next.getRecommendPicUrl());
            next.setSkinUrl(mainUrl + next.getSkinUrl());
            BackgroundItem backgroundItem = new BackgroundItem(next);
            if (FileUtils.m8419a(backgroundItem.m3325h())) {
                backgroundItem.m3333a(BackgroundItem.EnumC2011a.ADD_BY_USER);
            }
            arrayList.add(backgroundItem);
        }
        return arrayList;
    }

    /* renamed from: a */
    public static ArrayList<BackgroundItem> m3856a() {
        File[] listFiles;
        ArrayList<BackgroundItem> arrayList = new ArrayList<>();
        File file = new File(TTPodConfig.m5293o());
        if (file.exists() && file.isDirectory() && (listFiles = file.listFiles(new FilenameFilter() { // from class: com.sds.android.ttpod.framework.modules.skin.b.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str) {
                return str.toLowerCase(Locale.US).endsWith(".jpg");
            }
        })) != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                arrayList.add(new BackgroundItem(file2.getName(), BackgroundItem.EnumC2011a.ADD_BY_USER));
            }
        }
        return arrayList;
    }
}
