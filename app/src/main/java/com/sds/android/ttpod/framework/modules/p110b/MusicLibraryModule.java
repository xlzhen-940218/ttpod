package com.sds.android.ttpod.framework.modules.p110b;

import com.sds.android.cloudapi.ttpod.api.FindSongAPI;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.ModuleRequestHelper;
import java.lang.reflect.Method;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.framework.modules.b.b */
/* loaded from: classes.dex */
public class MusicLibraryModule extends BaseModule {
    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID id() {
        return ModuleID.MUSIC_LIBRARY;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.GET_MUSIC_CATEGORY, ReflectUtils.loadMethod(cls, "getMusicCategory", Integer.class, Integer.class));
        map.put(CommandID.GET_MUSIC_SUB_CATEGORY, ReflectUtils.loadMethod(cls, "getSubMusicCategory", Long.class, Integer.class, Integer.class));
    }

    public void getMusicCategory(Integer num, Integer num2) {
        ModuleRequestHelper.execute(FindSongAPI.m8908a(num.intValue(), num2.intValue()), CommandID.UPDATE_MUSIC_CATEGORY, id(), null);
    }

    public void getSubMusicCategory(Long l, Integer num, Integer num2) {
        ModuleRequestHelper.execute(FindSongAPI.m8905a(l.longValue(), num.intValue(), num2.intValue()), CommandID.UPDATE_MUSIC_SUB_CATEGORY, id(), null);
    }
}
