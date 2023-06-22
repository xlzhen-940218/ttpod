package com.sds.android.ttpod.component.lockscreen.p101a.p102a;

import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.component.lockscreen.p101a.p103b.FloatProperty;
import com.sds.android.ttpod.component.lockscreen.p101a.p103b.IntProperty;
import com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.j */
/* loaded from: classes.dex */
public class PropertyValuesHolder implements Cloneable {

    /* renamed from: i */
    private static final TypeEvaluator f4664i = new IntEvaluator();

    /* renamed from: j */
    private static final TypeEvaluator f4665j = new FloatEvaluator();

    /* renamed from: k */
    private static Class[] f4666k = {Float.TYPE, Float.class, Double.TYPE, Integer.TYPE, Double.class, Integer.class};

    /* renamed from: l */
    private static Class[] f4667l = {Integer.TYPE, Integer.class, Float.TYPE, Double.TYPE, Float.class, Double.class};

    /* renamed from: m */
    private static Class[] f4668m = {Double.TYPE, Double.class, Float.TYPE, Integer.TYPE, Float.class, Integer.class};

    /* renamed from: n */
    private static final HashMap<Class, HashMap<String, Method>> f4669n = new HashMap<>();

    /* renamed from: o */
    private static final HashMap<Class, HashMap<String, Method>> f4670o = new HashMap<>();

    /* renamed from: a */
    String f4671a;

    /* renamed from: b */
    protected Property f4672b;

    /* renamed from: c */
    Method f4673c;

    /* renamed from: d */
    Class f4674d;

    /* renamed from: e */
    KeyframeSet f4675e;

    /* renamed from: f */
    final ReentrantReadWriteLock f4676f;

    /* renamed from: g */
    final Object[] f4677g;

    /* renamed from: h */
    private Method f4678h;

    /* renamed from: p */
    private TypeEvaluator f4679p;

    /* renamed from: q */
    private Object f4680q;

    private PropertyValuesHolder(String str) {
        this.f4673c = null;
        this.f4678h = null;
        this.f4675e = null;
        this.f4676f = new ReentrantReadWriteLock();
        this.f4677g = new Object[1];
        this.f4671a = str;
    }

    private PropertyValuesHolder(Property property) {
        this.f4673c = null;
        this.f4678h = null;
        this.f4675e = null;
        this.f4676f = new ReentrantReadWriteLock();
        this.f4677g = new Object[1];
        this.f4672b = property;
        if (property != null) {
            this.f4671a = property.m5964a();
        }
    }

    /* renamed from: a */
    public static PropertyValuesHolder m6018a(String str, int... iArr) {
        return new C1302b(str, iArr);
    }

    /* renamed from: a */
    public static PropertyValuesHolder m6025a(Property<?, Integer> property, int... iArr) {
        return new C1302b(property, iArr);
    }

    /* renamed from: a */
    public static PropertyValuesHolder m6019a(String str, float... fArr) {
        return new C1301a(str, fArr);
    }

    /* renamed from: a */
    public static PropertyValuesHolder m6026a(Property<?, Float> property, float... fArr) {
        return new C1301a(property, fArr);
    }

    /* renamed from: a */
    public void mo6009a(int... iArr) {
        this.f4674d = Integer.TYPE;
        this.f4675e = KeyframeSet.m6063a(iArr);
    }

    /* renamed from: a */
    public void mo6014a(float... fArr) {
        this.f4674d = Float.TYPE;
        this.f4675e = KeyframeSet.m6064a(fArr);
    }

    /* renamed from: a */
    private Method m6024a(Class cls, String str, Class cls2) {
        Method method;
        Class<?>[] clsArr;
        Method method2 = null;
        String m6020a = m6020a(str, this.f4671a);
        if (cls2 == null) {
            try {
                return cls.getMethod(m6020a, null);
            } catch (NoSuchMethodException e) {
                try {
                    method = cls.getDeclaredMethod(m6020a, null);
                } catch (NoSuchMethodException e2) {
                    method = null;
                }
                method.setAccessible(true);
                return method;
            }
        }
        Class<?>[] clsArr2 = new Class[1];
        if (this.f4674d.equals(Float.class)) {
            clsArr = f4666k;
        } else if (this.f4674d.equals(Integer.class)) {
            clsArr = f4667l;
        } else {
            clsArr = this.f4674d.equals(Double.class) ? f4668m : new Class[]{this.f4674d};
        }
        for (Class<?> cls3 : clsArr) {
            clsArr2[0] = cls3;
            try {
                method2 = cls.getMethod(m6020a, clsArr2);
                this.f4674d = cls3;
                return method2;
            } catch (NoSuchMethodException e4) {
                try {
                    method2 = cls.getDeclaredMethod(m6020a, clsArr2);
                    method2.setAccessible(true);
                    this.f4674d = cls3;
                    return method2;
                } catch (NoSuchMethodException e5) {
                }
            }
        }
        LogUtils.m8381c("PropertyValuesHolder", "Couldn't find setter/getter for property " + this.f4671a + " with value type " + this.f4674d);
        return method2;
    }

    /* renamed from: a */
    private Method m6023a(Class cls, HashMap<Class, HashMap<String, Method>> hashMap, String str, Class cls2) {
        Method method = null;
        try {
            this.f4676f.writeLock().lock();
            HashMap<String, Method> hashMap2 = hashMap.get(cls);
            if (hashMap2 != null) {
                method = hashMap2.get(this.f4671a);
            }
            if (method == null) {
                method = m6024a(cls, str, cls2);
                if (hashMap2 == null) {
                    hashMap2 = new HashMap<>();
                    hashMap.put(cls, hashMap2);
                }
                hashMap2.put(this.f4671a, method);
            }
            Method method2 = method;
            return method2;
        } finally {
            this.f4676f.writeLock().unlock();
        }
    }

    /* renamed from: a */
    void mo6010a(Class cls) {
        this.f4673c = m6023a(cls, f4669n, "set", this.f4674d);
    }

    /* renamed from: b */
    private void m6016b(Class cls) {
        this.f4678h = m6023a(cls, f4670o, "get", null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m6022a(Object obj) {
        if (this.f4672b != null) {
            try {
                this.f4672b.mo5963a(obj);
                Iterator<Keyframe> it = this.f4675e.f4644e.iterator();
                while (it.hasNext()) {
                    Keyframe next = it.next();
                    if (!next.m6081a()) {
                        next.mo6071a(this.f4672b.mo5963a(obj));
                    }
                }
                return;
            } catch (ClassCastException e) {
                LogUtils.m8381c("PropertyValuesHolder", "No such property (" + this.f4672b.m5964a() + ") on target object " + obj + ". Trying reflection instead");
                this.f4672b = null;
            }
        }
        Class<?> cls = obj.getClass();
        if (this.f4673c == null) {
            mo6010a((Class) cls);
        }
        Iterator<Keyframe> it2 = this.f4675e.f4644e.iterator();
        while (it2.hasNext()) {
            Keyframe next2 = it2.next();
            if (!next2.m6081a()) {
                if (this.f4678h == null) {
                    m6016b((Class) cls);
                }
                try {
                    next2.mo6071a(this.f4678h.invoke(obj, new Object[0]));
                } catch (IllegalAccessException e2) {
                    LogUtils.m8381c("PropertyValuesHolder", e2.toString());
                } catch (InvocationTargetException e3) {
                    LogUtils.m8381c("PropertyValuesHolder", e3.toString());
                }
            }
        }
    }

    @Override // 
    /* renamed from: a */
    public PropertyValuesHolder clone() {
        try {
            PropertyValuesHolder propertyValuesHolder = (PropertyValuesHolder) super.clone();
            propertyValuesHolder.f4671a = this.f4671a;
            propertyValuesHolder.f4672b = this.f4672b;
            propertyValuesHolder.f4675e = this.f4675e.clone();
            propertyValuesHolder.f4679p = this.f4679p;
            return propertyValuesHolder;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo6008b(Object obj) {
        if (this.f4672b != null) {
            this.f4672b.mo5962a(obj, mo6007d());
        }
        if (this.f4673c != null) {
            try {
                this.f4677g[0] = mo6007d();
                this.f4673c.invoke(obj, this.f4677g);
            } catch (IllegalAccessException e) {
                LogUtils.m8381c("PropertyValuesHolder", e.toString());
            } catch (InvocationTargetException e2) {
                LogUtils.m8381c("PropertyValuesHolder", e2.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m6017b() {
        TypeEvaluator typeEvaluator;
        if (this.f4679p == null) {
            if (this.f4674d == Integer.class) {
                typeEvaluator = f4664i;
            } else {
                typeEvaluator = this.f4674d == Float.class ? f4665j : null;
            }
            this.f4679p = typeEvaluator;
        }
        if (this.f4679p != null) {
            this.f4675e.m6065a(this.f4679p);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo6011a(float f) {
        this.f4680q = this.f4675e.mo6066a(f);
    }

    /* renamed from: a */
    public void m6021a(String str) {
        this.f4671a = str;
    }

    /* renamed from: a */
    public void m6027a(Property property) {
        this.f4672b = property;
    }

    /* renamed from: c */
    public String m6015c() {
        return this.f4671a;
    }

    /* renamed from: d */
    Object mo6007d() {
        return this.f4680q;
    }

    public String toString() {
        return this.f4671a + ": " + this.f4675e.toString();
    }

    /* renamed from: a */
    static String m6020a(String str, String str2) {
        if (str2 != null && str2.length() != 0) {
            char upperCase = Character.toUpperCase(str2.charAt(0));
            return str + upperCase + str2.substring(1);
        }
        return str;
    }

    /* compiled from: PropertyValuesHolder.java */
    /* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.j$b */
    /* loaded from: classes.dex */
    static class C1302b extends PropertyValuesHolder {

        /* renamed from: h */
        IntKeyframeSet f4684h;

        /* renamed from: i */
        int f4685i;

        /* renamed from: j */
        private IntProperty f4686j;

        public C1302b(String str, int... iArr) {
            super(str);
            mo6009a(iArr);
        }

        public C1302b(Property property, int... iArr) {
            super(property);
            mo6009a(iArr);
            if (property instanceof IntProperty) {
                this.f4686j = (IntProperty) this.f4672b;
            }
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.PropertyValuesHolder
        /* renamed from: a */
        public void mo6009a(int... iArr) {
            super.mo6009a(iArr);
            this.f4684h = (IntKeyframeSet) this.f4675e;
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.PropertyValuesHolder
        /* renamed from: a */
        public void mo6011a(float f) {
            this.f4685i = this.f4684h.m6082b(f);
        }

        @Override
            // com.sds.android.ttpod.component.lockscreen.p101a.p102a.PropertyValuesHolder
            /* renamed from: d */
        Object mo6007d() {
            return Integer.valueOf(this.f4685i);
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.PropertyValuesHolder
        /* renamed from: e */
        public C1302b clone() {
            C1302b c1302b = (C1302b) super.clone();
            c1302b.f4684h = (IntKeyframeSet) c1302b.f4675e;
            return c1302b;
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.PropertyValuesHolder
        /* renamed from: b */
        public void mo6008b(Object obj) {
            if (this.f4686j != null) {
                this.f4686j.mo5966a((IntProperty) obj, this.f4685i);
            } else if (this.f4672b != null) {
                this.f4672b.mo5962a(obj, Integer.valueOf(this.f4685i));
            } else if (this.f4673c != null) {
                try {
                    this.f4677g[0] = Integer.valueOf(this.f4685i);
                    this.f4673c.invoke(obj, this.f4677g);
                } catch (IllegalAccessException e) {
                    LogUtils.m8381c("PropertyValuesHolder", e.toString());
                } catch (InvocationTargetException e2) {
                    LogUtils.m8381c("PropertyValuesHolder", e2.toString());
                }
            }
        }

        @Override
            // com.sds.android.ttpod.component.lockscreen.p101a.p102a.PropertyValuesHolder
            /* renamed from: a */
        void mo6010a(Class cls) {
            if (this.f4672b == null) {
                super.mo6010a(cls);
            }
        }
    }

    /* compiled from: PropertyValuesHolder.java */
    /* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.j$a */
    /* loaded from: classes.dex */
    static class C1301a extends PropertyValuesHolder {

        /* renamed from: h */
        FloatKeyframeSet f4681h;

        /* renamed from: i */
        float f4682i;

        /* renamed from: j */
        private FloatProperty f4683j;

        public C1301a(String str, float... fArr) {
            super(str);
            mo6014a(fArr);
        }

        public C1301a(Property property, float... fArr) {
            super(property);
            mo6014a(fArr);
            if (property instanceof FloatProperty) {
                this.f4683j = (FloatProperty) this.f4672b;
            }
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.PropertyValuesHolder
        /* renamed from: a */
        public void mo6014a(float... fArr) {
            super.mo6014a(fArr);
            this.f4681h = (FloatKeyframeSet) this.f4675e;
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.PropertyValuesHolder
        /* renamed from: a */
        public void mo6011a(float f) {
            this.f4682i = this.f4681h.m6085b(f);
        }

        @Override
            // com.sds.android.ttpod.component.lockscreen.p101a.p102a.PropertyValuesHolder
            /* renamed from: d */
        Object mo6007d() {
            return Float.valueOf(this.f4682i);
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.PropertyValuesHolder
        /* renamed from: e */
        public C1301a clone() {
            C1301a c1301a = (C1301a) super.clone();
            c1301a.f4681h = (FloatKeyframeSet) c1301a.f4675e;
            return c1301a;
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.PropertyValuesHolder
        /* renamed from: b */
        public void mo6008b(Object obj) {
            if (this.f4683j != null) {
                this.f4683j.mo5968a((FloatProperty) obj, this.f4682i);
            } else if (this.f4672b != null) {
                this.f4672b.mo5962a(obj, Float.valueOf(this.f4682i));
            } else if (this.f4673c != null) {
                try {
                    this.f4677g[0] = Float.valueOf(this.f4682i);
                    this.f4673c.invoke(obj, this.f4677g);
                } catch (IllegalAccessException e) {
                    LogUtils.m8381c("PropertyValuesHolder", e.toString());
                } catch (InvocationTargetException e2) {
                    LogUtils.m8381c("PropertyValuesHolder", e2.toString());
                }
            }
        }

        @Override
            // com.sds.android.ttpod.component.lockscreen.p101a.p102a.PropertyValuesHolder
            /* renamed from: a */
        void mo6010a(Class cls) {
            if (this.f4672b == null) {
                super.mo6010a(cls);
            }
        }
    }
}
