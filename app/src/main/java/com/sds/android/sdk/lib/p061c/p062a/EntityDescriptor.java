package com.sds.android.sdk.lib.p061c.p062a;

import android.content.ContentValues;
import android.database.Cursor;
import com.sds.android.sdk.lib.p061c.p062a.p063a.Entity;
import com.sds.android.sdk.lib.p061c.p062a.p063a.FieldAnnotationReader;
import com.sds.android.sdk.lib.p061c.p062a.p063a.Index;
import com.sds.android.sdk.lib.util.StringUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.sds.android.sdk.lib.c.a.a */
/* loaded from: classes.dex */
public class EntityDescriptor {

    /* renamed from: a */
    private Class f2365a;

    /* renamed from: b */
    private FieldAnnotationReader f2366b;

    /* renamed from: c */
    private Entity f2367c;

    /* renamed from: d */
    private LinkedHashMap<String, FieldDescriptor> f2368d = new LinkedHashMap<>();

    /* renamed from: a */
    private boolean m8651a(String str, Method[] methodArr, FieldDescriptor fieldDescriptor) throws Exception {
        Class<?>[] parameterTypes;
        for (Method method : methodArr) {
            if (str.equals(method.getName()) && (parameterTypes = method.getParameterTypes()) != null && parameterTypes.length == 1 && parameterTypes[0] == fieldDescriptor.m8631a()) {
                int modifiers = method.getModifiers();
                if (!Modifier.isPublic(modifiers) || Modifier.isStatic(modifiers)) {
                    throw new Exception("EntityDescriptor: Invalid Language Modifier " + str);
                }
                fieldDescriptor.m8622b(method);
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public EntityDescriptor(Class cls) throws Exception {
        Class<?>[] parameterTypes;
        this.f2366b = null;
        this.f2367c = null;
        this.f2365a = cls;
        this.f2367c = (Entity) this.f2365a.getAnnotation(Entity.class);
        this.f2366b = FieldAnnotationReader.m8636a();
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (method.getDeclaringClass().isAssignableFrom(cls)) {
                String name = method.getName();
                if (name.startsWith("get")) {
                    String substring = name.substring("get".length());
                    if (substring.length() > 0 && (parameterTypes = method.getParameterTypes()) != null && parameterTypes.length <= 0) {
                        Class<?> returnType = method.getReturnType();
                        if (MappingPolicy.m8611a((Class) returnType)) {
                            int modifiers = method.getModifiers();
                            if (!Modifier.isPublic(modifiers) || Modifier.isStatic(modifiers)) {
                                throw new Exception("EntityDescriptor: Invalid Language Modifiers " + name);
                            }
                            FieldDescriptor fieldDescriptor = new FieldDescriptor();
                            if (this.f2366b.mo8635a(fieldDescriptor, method)) {
                                fieldDescriptor.m8627a(method);
                                fieldDescriptor.m8629a(returnType);
                                if (fieldDescriptor.m8617e() && fieldDescriptor.m8631a() != Long.class && fieldDescriptor.m8631a() != Integer.class && fieldDescriptor.m8631a() != Short.class) {
                                    throw new Exception("EntityDescriptor: AutoIncrement Field must be Long, Short or Integer !!");
                                }
                                String str = "set" + substring;
                                if (!m8651a(str, methods, fieldDescriptor)) {
                                    throw new Exception("EntityDescriptor: Invalid Setter " + str);
                                }
                                this.f2368d.put(substring, fieldDescriptor);
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    }
                } else {
                    continue;
                }
            }
        }
        Object[] array = this.f2368d.entrySet().toArray();
        Arrays.sort(array, new Comparator<Object>() { // from class: com.sds.android.sdk.lib.c.a.a.1
            @Override // java.util.Comparator
            public int compare(Object obj, Object obj2) {
                int m8625b = ((FieldDescriptor) ((Map.Entry) obj).getValue()).m8625b();
                int m8625b2 = ((FieldDescriptor) ((Map.Entry) obj2).getValue()).m8625b();
                if (m8625b == m8625b2) {
                    return 0;
                }
                if (m8625b < m8625b2) {
                    return -1;
                }
                return 1;
            }
        });
        this.f2368d.clear();
        for (Object obj : array) {
            Map.Entry entry = (Map.Entry) obj;
            this.f2368d.put((String) entry.getKey(), (FieldDescriptor) entry.getValue());
        }
    }

    /* renamed from: a */
    public String m8661a() {
        return (this.f2367c == null || this.f2367c.m8646a().length() <= 0) ? this.f2365a.getSimpleName() : this.f2367c.m8646a();
    }

    /* renamed from: a */
    private Object m8655a(Object obj, String str) throws ClassNotFoundException, NoSuchFieldException, InvocationTargetException {
        m8656a(obj);
        Method m8652a = m8652a(str);
        if (m8652a == null) {
            throw new NoSuchFieldException("No Such Getter " + str);
        }
        try {
            return m8652a.invoke(obj, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private void m8654a(Object obj, String str, Object obj2) throws ClassNotFoundException, NoSuchFieldException, InvocationTargetException {
        m8656a(obj);
        Method m8649b = m8649b(str);
        if (m8649b == null) {
            throw new NoSuchFieldException("No Such Setter " + str);
        }
        try {
            m8649b.invoke(obj, MappingPolicy.m8609a(obj2, m8647c(str)));
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Illegal Argument Cast " + str);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: a */
    public Object m8657a(Cursor cursor) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Object newInstance = this.f2365a.newInstance();
        int columnCount = cursor.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            try {
                m8654a(newInstance, cursor.getColumnName(i), cursor.getString(i));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return newInstance;
    }

    /* renamed from: a */
    public ContentValues m8653a(Object obj, boolean z) throws ClassNotFoundException {
        ContentValues contentValues = new ContentValues();
        m8658a(contentValues, obj, z);
        return contentValues;
    }

    /* renamed from: a */
    void m8658a(ContentValues contentValues, Object obj, boolean z) throws ClassNotFoundException {
        m8656a(obj);
        for (Map.Entry<String, FieldDescriptor> entry : this.f2368d.entrySet()) {
            try {
                String key = entry.getKey();
                Object m8655a = m8655a(obj, key);
                if (m8655a != null || z) {
                    MappingPolicy.m8612a(contentValues, key, m8655a);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    public String m8650b() {
        String str;
        Index[] m8644c;
        StringBuilder sb = new StringBuilder();
        if (this.f2367c == null || this.f2367c.m8645b().m8632a().length <= 0) {
            str = null;
        } else {
            sb.replace(0, sb.length(), "PRIMARY KEY (");
            sb.append(StringUtils.spliceStringAndArray(", ", this.f2367c.m8645b().m8632a()));
            sb.append(")");
            str = sb.toString();
        }
        ArrayList arrayList = new ArrayList();
        if (this.f2367c != null) {
            for (Index index : this.f2367c.m8644c()) {
                if (index.m8633b().length > 0) {
                    sb.replace(0, sb.length(), "INDEX ").append(index.m8634a()).append(" (");
                    sb.append(StringUtils.spliceStringAndArray(", ", index.m8633b()));
                    sb.append(")");
                    arrayList.add(sb.toString());
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry<String, FieldDescriptor> entry : this.f2368d.entrySet()) {
            FieldDescriptor value = entry.getValue();
            sb.replace(0, sb.length(), entry.getKey()).append(" ").append(MappingPolicy.m8608b(value.m8631a()));
            if (value.m8618d() && str == null) {
                sb.append(" PRIMARY KEY");
            }
            if (value.m8617e()) {
                sb.append(" AUTOINCREMENT");
            }
            if (value.m8616f() != null && value.m8616f().length() > 0) {
                sb.append(" DEFAULT ").append(value.m8616f());
            }
            if (value.m8615g() != null && value.m8615g().length() > 0) {
                sb.append(" COMMENT ").append("'").append(value.m8615g()).append("'");
            }
            arrayList2.add(sb.toString());
        }
        sb.replace(0, sb.length(), "CREATE TABLE IF NOT EXISTS ").append(m8661a()).append(" (").append(StringUtils.arrayToString(", ", arrayList2));
        if (str != null) {
            sb.append(", ").append(str);
        }
        if (arrayList.size() > 0) {
            sb.append(", ").append(StringUtils.arrayToString(", ", arrayList));
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: c */
    public String m8648c() {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE IF EXISTS ").append(m8661a());
        return sb.toString();
    }

    /* renamed from: a */
    public boolean m8660a(int i) {
        for (String str : this.f2368d.keySet()) {
            if (this.f2368d.get(str).m8620c() > i) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public List<String> m8659a(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (String str : this.f2368d.keySet()) {
            FieldDescriptor fieldDescriptor = this.f2368d.get(str);
            if (fieldDescriptor.m8620c() > i) {
                StringBuilder sb = new StringBuilder();
                sb.append("ALTER TABLE ").append(m8661a()).append(" ADD COLUMN ").append(str).append(" ").append(MappingPolicy.m8608b(fieldDescriptor.m8631a()));
                if (fieldDescriptor.m8618d()) {
                    sb.append(" PRIMARY KEY");
                }
                if (fieldDescriptor.m8617e()) {
                    sb.append(" AUTOINCREMENT");
                }
                if (fieldDescriptor.m8616f() != null && fieldDescriptor.m8616f().length() > 0) {
                    sb.append(" DEFAULT ").append(fieldDescriptor.m8616f());
                }
                if (fieldDescriptor.m8615g() != null && fieldDescriptor.m8615g().length() > 0) {
                    sb.append(" COMMENT ").append("'").append(fieldDescriptor.m8615g()).append("'");
                }
                arrayList.add(sb.toString());
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    protected boolean m8656a(Object obj) throws ClassNotFoundException {
        if (obj.getClass() != this.f2365a) {
            throw new ClassNotFoundException();
        }
        return true;
    }

    /* renamed from: a */
    protected Method m8652a(String str) {
        if (this.f2368d.containsKey(str)) {
            return this.f2368d.get(str).m8614h();
        }
        return null;
    }

    /* renamed from: b */
    protected Method m8649b(String str) {
        if (this.f2368d.containsKey(str)) {
            return this.f2368d.get(str).m8613i();
        }
        return null;
    }

    /* renamed from: c */
    protected Class m8647c(String str) {
        if (this.f2368d.containsKey(str)) {
            return this.f2368d.get(str).m8631a();
        }
        return null;
    }
}
