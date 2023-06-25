package com.sds.android.ttpod.framework.base.p108a;

import android.os.Handler;
import android.os.Looper;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.framework.base.CommandType;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.ModuleManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: com.sds.android.ttpod.framework.base.a.b */
/* loaded from: classes.dex */
public final class CommandCenter {

    /* renamed from: a */
    private static CommandCenter instance = new CommandCenter();

    /* renamed from: b */
    private Handler handler = new Handler(Looper.getMainLooper());

    /* renamed from: c */
    private Map<Object, Map<CommandID, C1803a>> f5713c = new HashMap();

    /* renamed from: d */
    private Map<ModuleID, Map<CommandID, Set<Object>>> moduleIDMapMap = new EnumMap(ModuleID.class);

    /* renamed from: a */
    public static CommandCenter getInstance() {
        return instance;
    }

    private CommandCenter() {
    }

    /* renamed from: a */
    public void m4597a(Object obj, Map<CommandID, Method> map) {
        DebugUtils.m8426a(obj, "target");
        DebugUtils.m8427a();
        if (this.f5713c.containsKey(obj)) {
            throw new IllegalArgumentException("the target Already registered!");
        }
        if (map == null) {
            throw new IllegalArgumentException("the commandMap must not be null!");
        }
        if (!map.isEmpty()) {
            m4592b(obj, map);
        }
    }

    /* renamed from: b */
    private void m4592b(Object obj, Map<CommandID, Method> map) {
        EnumMap enumMap = new EnumMap(CommandID.class);
        for (CommandID commandID : map.keySet()) {
            Method method = map.get(commandID);
            enumMap.put(commandID, new C1803a(method, method.getParameterTypes(), method.getReturnType()));
            m4598a(obj, commandID);
        }
        this.f5713c.put(obj, enumMap);
    }

    /* renamed from: a */
    private void m4598a(Object obj, CommandID commandID) {
        Map<CommandID, Set<Object>> map = this.moduleIDMapMap.get(commandID.getModuleID());
        EnumMap enumMap = map == null ? new EnumMap(CommandID.class) : (EnumMap) map;
        Set<Object> set = (Set<Object>) enumMap.get(commandID);
        if (set == null) {
            set = new HashSet<>();
        }
        set.add(obj);
        enumMap.put(commandID, set);
        this.moduleIDMapMap.put(commandID.getModuleID(), enumMap);
    }

    /* renamed from: a */
    public void m4599a(Object obj) {
        DebugUtils.m8426a(obj, "target");
        DebugUtils.m8427a();
        m4593b(obj);
    }

    /* renamed from: b */
    private void m4593b(Object obj) {
        Iterator<Object> it = this.f5713c.keySet().iterator();
        while (it.hasNext()) {
            if (it.next() == obj) {
                it.remove();
                m4590c(obj);
                return;
            }
        }
    }

    /* renamed from: c */
    private void m4590c(Object obj) {
        Iterator<ModuleID> it = this.moduleIDMapMap.keySet().iterator();
        while (it.hasNext()) {
            Map<CommandID, Set<Object>> map = this.moduleIDMapMap.get(it.next());
            Iterator<CommandID> it2 = map.keySet().iterator();
            while (it2.hasNext()) {
                Set<Object> set = map.get(it2.next());
                if (set == null) {
                    it2.remove();
                } else {
                    if (set.contains(obj)) {
                        set.remove(obj);
                    }
                    if (set.isEmpty()) {
                        it2.remove();
                    }
                }
            }
            if (map.isEmpty()) {
                it.remove();
            }
        }
    }

    /* renamed from: a */
    public void m4606a(Command command) {
        m4591c(command, null);
        m4594b(command, (Class) null);
    }

    /* renamed from: a */
    public void m4604a(Command command, ModuleID moduleID) {
        m4600a(moduleID);
        m4591c(command, moduleID);
        m4594b(command, (Class) null);
    }

    /* renamed from: a */
    public <Result> Result m4602a(Command command, Class<Result> cls) {
        m4591c(command, null);
        if (!command.getCommandId().getCommandType().equals(CommandType.TO_MODULE)) {
            throw new IllegalArgumentException("id of Command with result must be CommandType.TO_MODULE");
        }
        return (Result) m4594b(command, cls);
    }

    /* renamed from: b */
    public void m4596b(Command command) {
        m4605a(command, 0);
    }

    /* renamed from: a */
    public void m4605a(final Command command, int i) {
        m4591c(command, null);
        this.handler.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.framework.base.a.b.1
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.this.m4594b(command, null);
            }
        }, i);
    }

    /* renamed from: b */
    public void m4595b(Command command, ModuleID moduleID) {
        m4603a(command, moduleID, 0);
    }

    /* renamed from: a */
    public void m4603a(final Command command, ModuleID moduleID, int i) {
        m4600a(moduleID);
        m4591c(command, moduleID);
        this.handler.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.framework.base.a.b.2
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.this.m4594b(command, null);
            }
        }, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public <Result> Result m4594b(Command command, Class<Result> cls) {
        DebugUtils.m8427a();
        CommandID m4610a = command.getCommandId();
        ModuleID moduleID = m4610a.getModuleID();
        ModuleManager.getInstance().m4115a(m4610a);
        ModuleManager.getInstance().m4110b(moduleID);
        Map<CommandID, Set<Object>> map = this.moduleIDMapMap.get(moduleID);
        if (map == null || map.isEmpty()) {
            if (EnvironmentUtils.C0602a.m8502i() && cls != null) {
                throw new IllegalArgumentException("exeCommand(CommandID." + command.getCommandId().name() + ") with a result must have one target!");
            }
            this.moduleIDMapMap.remove(moduleID);
            return null;
        }
        Set<Object> set = map.get(m4610a);
        if (set == null || set.isEmpty()) {
            if (EnvironmentUtils.C0602a.m8502i() && cls != null) {
                throw new IllegalArgumentException("exeCommand(CommandID." + command.getCommandId().name() + ") with a result must have one target!");
            }
            map.remove(m4610a);
            if (!map.isEmpty()) {
                return null;
            }
            this.moduleIDMapMap.remove(moduleID);
            return null;
        } else if (EnvironmentUtils.C0602a.m8502i() && cls != null && set.size() != 1) {
            throw new IllegalArgumentException("exeCommand(CommandID." + command.getCommandId().name() + ") with a result must only have one target!");
        } else {
            Object obj = null;
            for (Object obj2 : set) {
                obj = this.f5713c.get(obj2).get(m4610a).invoke(obj2, command, cls);
            }
            return (Result) obj;
        }
    }

    /* renamed from: a */
    private void m4600a(ModuleID moduleID) {
        if (moduleID == null) {
            throw new IllegalArgumentException("fromModuleID must not be null!");
        }
    }

    /* renamed from: c */
    private void m4591c(Command command, ModuleID moduleID) {
        if (command == null) {
            throw new IllegalArgumentException("command can not be null!");
        }
        if (EnvironmentUtils.C0602a.m8502i()) {
            if (command.getCommandId().getCommandType().equals(CommandType.FROM_MODULE)) {
                if (moduleID == null) {
                    throw new IllegalArgumentException("Command with CommandType.FROM_MODULE should assign fromModuleID");
                }
                if (!command.getCommandId().getModuleID().equals(moduleID)) {
                    throw new IllegalArgumentException("command(CommandID." + command.getCommandId().name() + ") can not send from module(ModuleID." + moduleID.name() + ")");
                }
            } else if (moduleID != null) {
                throw new IllegalArgumentException("Command with CommandType.TO_MODULE should not assign fromModuleID");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CommandCenter.java */
    /* renamed from: com.sds.android.ttpod.framework.base.a.b$a */
    /* loaded from: classes.dex */
    public static final class C1803a {

        /* renamed from: a */
        private Method method;

        /* renamed from: b */
        private Class[] f5720b;

        /* renamed from: c */
        private Class f5721c;

        private C1803a(Method method, Class[] clsArr, Class cls) {
            this.method = method;
            this.f5720b = clsArr;
            this.f5721c = cls;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public <Result> Result invoke(Object obj, Command command, Class<Result> cls) {
            Result result;
            m4589a(command, cls);
            if (EnvironmentUtils.C0602a.m8502i() || EnvironmentUtils.C0602a.m8503h()) {
                try {
                    result = (Result) this.method.invoke(obj, command.getObjects());
                } catch (IllegalArgumentException e) {
                    throw e;
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2);
                } catch (Exception e3) {
                    e3.printStackTrace();
                    result = null;
                }
            } else {
                try {
                    result = (Result) this.method.invoke(obj, command.getObjects());
                } catch (Throwable th) {
                    th.printStackTrace();
                    result = null;
                }
            }
            if (result != null) {
                return result;
            }
            return null;
        }

        /* renamed from: a */
        private <Result> void m4589a(Command command, Class<Result> cls) {
            if (EnvironmentUtils.C0602a.m8502i()) {
                Object[] m4608b = command.getObjects();
                for (int i = 0; i < m4608b.length; i++) {
                    Class<?> cls2 = m4608b[i] == null ? null : m4608b[i].getClass();
                    if (!m4587a(this.f5720b[i], cls2)) {
                        throw new IllegalArgumentException("Command(CommandID." + command.getCommandId().name() + ") param " + i + " Type not match, " + this.f5720b[i].getName() + " while expected to be " + cls2.getName());
                    }
                }
                if (cls != null && !m4587a(cls, this.f5721c)) {
                    throw new IllegalArgumentException("Command(CommandID." + command.getCommandId().name() + ") Return Type not match, " + cls.getName() + " while expected to be " + this.f5721c.getName());
                }
            }
        }

        /* renamed from: a */
        private static boolean m4587a(Class cls, Class cls2) {
            return cls2 == null || cls.isAssignableFrom(cls2);
        }
    }
}
