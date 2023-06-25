package com.sds.android.ttpod.framework.modules.skin;

import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.storage.p133a.Cache;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.k */
/* loaded from: classes.dex */
public class SkinCacheCreator extends SkinReader implements Runnable {

    /* renamed from: a */
    private String f6654a;

    /* renamed from: c */
    private String f6655c;

    /* renamed from: d */
    private CommandID f6656d;

    public SkinCacheCreator(String str, String str2, CommandID commandID) {
        this.f6654a = str;
        this.f6655c = str2;
        this.f6656d = commandID;
    }

    @Override // java.lang.Runnable
    public void run() {
        SkinCache m3577a = m3577a(this.f6654a);
        if (m3577a != null) {
            CommandCenter.getInstance().m4596b(new Command(CommandID.SET_SKIN_PROTOCOL_PATH, this.f6654a));
            Cache.getInstance().m3213a(m3577a);
            CommandCenter.getInstance().m4595b(new Command(this.f6656d, m3577a), ModuleID.SKIN);
        } else if (this.f6655c != null) {
            CommandCenter.getInstance().m4595b(new Command(CommandID.LOAD_SKIN_ERROR, new Object[0]), ModuleID.SKIN);
            CommandCenter.getInstance().m4596b(new Command(CommandID.SET_SKIN_PROTOCOL_PATH, this.f6655c));
            SkinCache m3577a2 = m3577a(this.f6655c);
            if (m3577a2 != null) {
                Cache.getInstance().m3213a(m3577a2);
                CommandCenter.getInstance().m4595b(new Command(this.f6656d, m3577a2), ModuleID.SKIN);
            }
        }
    }

    /* renamed from: a */
    private SkinCache m3577a(String str) {
        SkinCache skinCache = new SkinCache(str);
        skinCache.m3580h();
        if (skinCache.getSerializableSkin() != null) {
            skinCache.handleClose();
            return skinCache;
        }
        skinCache.clear();
        return null;
    }
}
