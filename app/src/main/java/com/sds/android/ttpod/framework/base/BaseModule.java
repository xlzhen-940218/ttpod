package com.sds.android.ttpod.framework.base;

import android.content.Context;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.ModuleManager;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.framework.base.b */
/* loaded from: classes.dex */
public abstract class BaseModule {
    public static final long TIME_OUT_FOREVER = Long.MIN_VALUE;
    protected static Context sContext = null;

    /* renamed from: id */
    protected abstract ModuleID id();

    protected abstract void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException;

    public static void setContext(Context context) {
        sContext = context;
    }

    public long timeOutInMills() {
        return Long.MIN_VALUE;
    }

    public void onCreate() {
        Map<CommandID, Method> requestCommandMap = requestCommandMap();
        assertCommandMap(requestCommandMap);
        CommandCenter.getInstance().m4597a(this, requestCommandMap);
    }

    public void onPreDestroy() {
    }

    public void onDestroy() {
        CommandCenter.getInstance().m4599a(this);
    }

    private Map<CommandID, Method> requestCommandMap() {
        HashMap hashMap = new HashMap();
        try {
            onLoadCommandMap(hashMap);
            return hashMap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void assertCommandMap(Map<CommandID, Method> map) {
        if (EnvironmentUtils.AppConfig.getTestMode()) {
            for (CommandID commandID : map.keySet()) {
                if (commandID.getCommandType().equals(CommandType.TO_MODULE) && !commandID.getModuleID().equals(id())) {
                    throw new IllegalArgumentException("the CommandID." + commandID.name() + " is not belong to this module(ModuleID." + id().name() + ")!");
                }
                if (commandID.getCommandType().equals(CommandType.FROM_MODULE) && !ModuleManager.m4114a(commandID, id())) {
                    throw new IllegalArgumentException("the CommandID." + commandID.name() + " can not register in Module, because the CommandType is CommandType.FROM_MODULE and not be Declared in " + getClass() + " with annotation ObserverCommand !");
                }
            }
        }
    }
}
