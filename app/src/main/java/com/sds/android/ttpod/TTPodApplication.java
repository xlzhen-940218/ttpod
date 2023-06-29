package com.sds.android.ttpod;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.media.mediastore.MediaDBHelper;
import com.sds.android.ttpod.media.mediastore.MediaLibraryVersionManager;

/* loaded from: classes.dex */
public class TTPodApplication extends BaseApplication {

    /* renamed from: a */
    private BroadcastReceiver f2484a = new BroadcastReceiver() { // from class: com.sds.android.ttpod.TTPodApplication.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtils.error("MediaDBHelper", "onReceive");
            if (intent != null && StringUtils.equals(MediaDBHelper.ACTION_UPDATE_DB_VERSION, intent.getAction())) {
                MediaLibraryVersionManager.instance().setVersion(intent.getIntExtra(MediaDBHelper.KEY_DB_VERSION_OLD, 0), intent.getIntExtra(MediaDBHelper.KEY_DB_VERSION_NEW, 0));
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseApplication
    /* renamed from: a */
    public void mo4637a() {
        registerReceiver(this.f2484a, new IntentFilter(MediaDBHelper.ACTION_UPDATE_DB_VERSION));
        try {
            super.mo4637a();
            PopupsUtils.m6749a(this);
            ImageCacheUtils.m4757a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        if (m4631g()) {
            ImageCacheUtils.m4743b().m8804b();
            Cache.getInstance().m3196b();
        }
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        DisplayUtils.setConfiguration(this, configuration);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseApplication
    /* renamed from: b */
    public void mo4636b() {
        super.mo4636b();
    }
}
