package com.sds.android.ttpod.framework.modules.core.p121g;

import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.media.mediastore.MediaLibraryVersionManager;
import java.lang.reflect.Method;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.framework.modules.core.g.b */
/* loaded from: classes.dex */
public class VersionCompactModule extends BaseModule {

    /* renamed from: a */
    private volatile boolean f6074a = false;

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID id() {
        return ModuleID.VERSION_COMPACT;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        map.put(CommandID.CHECK_VERSION_COMPACT, ReflectUtils.loadMethod(getClass(), "checkVersionCompact", new Class[0]));
        map.put(CommandID.DO_VERSION_COMPACT, ReflectUtils.loadMethod(getClass(), "doVersionCompact", new Class[0]));
    }

    public Boolean checkVersionCompact() {
        boolean z = true;
        LogUtils.error("MediaDBHelper", "checkVersionCompact");
        if (this.f6074a) {
            return true;
        }
        return Boolean.valueOf((MediaLibraryVersionManager.instance().isCompacted(sContext) && OldPreferenceCompact.m4147a()) ? false : false);
    }

    public void doVersionCompact() {
        this.f6074a = true;
        CommandCenter.getInstance().m4604a(new Command(CommandID.DO_VERSION_COMPACT_STARTED, new Object[0]), ModuleID.VERSION_COMPACT);
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.g.b.1
            @Override // java.lang.Runnable
            public void run() {
                if (!OldPreferenceCompact.m4147a()) {
                    OldPreferenceCompact.m4144b();
                }
                if (!MediaLibraryVersionManager.instance().isCompacted(VersionCompactModule.sContext)) {
                    MediaLibraryVersionManager.instance().doCompact(VersionCompactModule.sContext);
                }
                VersionCompactModule.this.f6074a = false;
                CommandCenter.getInstance().m4595b(new Command(CommandID.DO_VERSION_COMPACT_FINISHED, new Object[0]), ModuleID.VERSION_COMPACT);
            }
        });
    }
}
