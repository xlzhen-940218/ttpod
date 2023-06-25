package com.sds.android.ttpod.framework.base.p108a;

import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.framework.modules.CommandID;

/* renamed from: com.sds.android.ttpod.framework.base.a.a */
/* loaded from: classes.dex */
public final class Command {

    /* renamed from: a */
    private CommandID commandID;

    /* renamed from: b */
    private Object[] objects;

    public Command(CommandID commandID, Object... objArr) {
        objArr = objArr == null ? new Object[1] : objArr;
        m4609a(commandID, objArr);
        this.objects = (Object[]) objArr.clone();
        this.commandID = commandID;
    }

    /* renamed from: a */
    public CommandID getCommandId() {
        return this.commandID;
    }

    /* renamed from: b */
    public Object[] getObjects() {
        return this.objects;
    }

    /* renamed from: a */
    private void m4609a(CommandID commandID, Object... objArr) {
        if (EnvironmentUtils.C0602a.m8502i()) {
            Class[] paramTypes = commandID.getParamTypes();
            if (paramTypes.length != objArr.length) {
                throw new IllegalArgumentException("Command(" + commandID.name() + ") Param Count is " + objArr.length + " while expect to be " + commandID.getParamTypes().length + "!");
            }
            for (int i = 0; i < objArr.length; i++) {
                Class cls = paramTypes[i];
                Class<?> cls2 = objArr[i] == null ? null : objArr[i].getClass();
                if (cls2 != null && !cls.isAssignableFrom(cls2)) {
                    throw new IllegalArgumentException("Command(" + commandID.name() + ") Param Type not match! it is " + cls2.getName() + " while except to be " + cls.getName());
                }
            }
        }
    }
}
