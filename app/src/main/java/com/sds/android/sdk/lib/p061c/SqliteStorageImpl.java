package com.sds.android.sdk.lib.p061c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.p061c.p062a.EntityDescriptor;
import com.sds.android.sdk.lib.util.LogUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.sds.android.sdk.lib.c.c */
/* loaded from: classes.dex */
public class SqliteStorageImpl extends SqliteStorage {

    /* renamed from: a */
    private Map<String, EntityDescriptor> f2380a = new HashMap();

    /* renamed from: b */
    private Context f2381b;

    /* renamed from: c */
    private String f2382c;

    /* renamed from: d */
    private int f2383d;

    /* renamed from: e */
    private InterfaceC0592a f2384e;

    /* renamed from: f */
    private SQLiteDatabase f2385f;

    /* compiled from: SqliteStorageImpl.java */
    /* renamed from: com.sds.android.sdk.lib.c.c$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0592a {
        /* renamed from: a */
        void mo3116a(int i, int i2);
    }

    /* renamed from: a */
    public void m8596a(Class cls) {
        if (!this.f2380a.containsValue(cls)) {
            try {
                EntityDescriptor entityDescriptor = new EntityDescriptor(cls);
                if (!entityDescriptor.m8660a(this.f2383d)) {
                    throw new RuntimeException("SqliteStroageImpl Field version must be less than current database version !!");
                }
                this.f2380a.put(cls.getSimpleName(), entityDescriptor);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* renamed from: a */
    private EntityDescriptor m8592a(String str) {
        if (this.f2380a.containsKey(str)) {
            return this.f2380a.get(str);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public SQLiteDatabase m8588c() {
        SQLiteDatabase sQLiteDatabase;
        synchronized (this.f2381b) {
            if (this.f2385f == null) {
                this.f2385f = new C0593b(this.f2381b).getWritableDatabase();
            }
            sQLiteDatabase = this.f2385f;
        }
        return sQLiteDatabase;
    }

    public SqliteStorageImpl(Context context, String str, int i, InterfaceC0592a interfaceC0592a) {
        this.f2381b = context;
        this.f2382c = str;
        this.f2383d = i;
        this.f2384e = interfaceC0592a;
    }

    /* renamed from: a */
    public void m8599a() {
        Thread thread = new Thread(new Runnable() { // from class: com.sds.android.sdk.lib.c.c.1
            @Override // java.lang.Runnable
            public void run() {
                SqliteStorageImpl.this.m8588c();
            }
        });
        thread.setDaemon(true);
        thread.setPriority(10);
        thread.start();
    }

    /* renamed from: b */
    public boolean m8591b() {
        return m8588c().isOpen();
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0076  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public <Entity> List<Entity> m8593a(Entity entity, boolean z) {
        EntityDescriptor m8592a;
        Cursor cursor;
        Cursor cursor2;
        Exception e;
        Throwable th;
        if (m8591b() && (m8592a = m8592a(entity.getClass().getSimpleName())) != null) {
            String m8661a = m8592a.m8661a();
            try {
                Map.Entry<String, String[]> m8598a = m8598a(m8592a, (Object) entity);
                cursor = m8588c().query(z, m8661a, null, m8598a.getKey(), m8598a.getValue(), null, null, null, null);
                try {
                    ArrayList arrayList = new ArrayList();
                    if (cursor == null || !cursor.moveToFirst()) {
                        if (cursor != null) {
                            cursor.close();
                            return arrayList;
                        }
                        return arrayList;
                    }
                    do {
                        try {
                            arrayList.add(m8592a.m8657a(cursor));
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        }
                    } while (cursor.moveToNext());
                    if (cursor != null) {
                        cursor.close();
                        return arrayList;
                    }
                    return arrayList;
                } catch (Exception e2) {
                    e = e2;
                    cursor2 = cursor;
                    try {
                        e.printStackTrace();
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        return null;
                    } catch (Throwable th1) {
                        th = th1;
                        cursor = cursor2;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                cursor2 = null;
            } catch (Throwable th3) {
                th = th3;
                cursor = null;
            }
        }
        return null;
    }

    /* renamed from: a */
    public <Entity> long m8595a(Entity entity) {
        EntityDescriptor m8592a;
        if (m8591b() && (m8592a = m8592a(entity.getClass().getSimpleName())) != null) {
            try {
                return m8588c().insertOrThrow(m8592a.m8661a(), null, m8592a.m8653a((Object) entity, false));
            } catch (SQLException e) {
                e.printStackTrace();
                return -1L;
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
                return -1L;
            }
        }
        return -1L;
    }

    /* renamed from: a */
    public <Entity> long m8594a(Entity entity, Entity entity2) {
        EntityDescriptor m8592a;
        if (m8591b() && entity.getClass() == entity2.getClass() && (m8592a = m8592a(entity.getClass().getSimpleName())) != null) {
            String m8661a = m8592a.m8661a();
            try {
                Map.Entry<String, String[]> m8598a = m8598a(m8592a, (Object) entity2);
                return m8588c().update(m8661a, m8592a.m8653a((Object) entity, false), m8598a.getKey(), m8598a.getValue());
            } catch (Exception e) {
                e.printStackTrace();
                return -1L;
            }
        }
        return -1L;
    }

    /* renamed from: b */
    public <Entity> long m8589b(Entity entity) {
        EntityDescriptor m8592a;
        if (m8591b() && (m8592a = m8592a(entity.getClass().getSimpleName())) != null) {
            String m8661a = m8592a.m8661a();
            try {
                Map.Entry<String, String[]> m8598a = m8598a(m8592a, (Object) entity);
                return m8588c().delete(m8661a, m8598a.getKey(), m8598a.getValue());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return -1L;
            }
        }
        return -1L;
    }

    /* renamed from: a */
    private static Map.Entry<String, String[]> m8598a(EntityDescriptor entityDescriptor, Object obj) throws ClassNotFoundException {
        int i;
        ContentValues m8653a = entityDescriptor.m8653a(obj, false);
        if (m8653a.size() <= 0) {
            return new Pair(null, null);
        }
        String[] strArr = new String[m8653a.size()];
        int i2 = 0;
        String str = "";
        for (Map.Entry<String, Object> entry : m8653a.valueSet()) {
            Object value = entry.getValue();
            if (value instanceof Boolean) {
                int i3 = i2 + 1;
                strArr[i2] = ((Boolean) value).booleanValue() ? "1" : "0";
                i = i3;
            } else {
                i = i2 + 1;
                strArr[i2] = entry.getValue().toString();
            }
            String str2 = str + entry.getKey() + "=? ";
            if (i < m8653a.size()) {
                str2 = str2 + "AND ";
            }
            i2 = i;
            str = str2;
        }
        return new Pair(str, strArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SqliteStorageImpl.java */
    /* renamed from: com.sds.android.sdk.lib.c.c$b */
    /* loaded from: classes.dex */
    public class C0593b extends SQLiteOpenHelper {
        public C0593b(Context context) {
            super(context, SqliteStorageImpl.this.f2382c, (SQLiteDatabase.CursorFactory) null, SqliteStorageImpl.this.f2383d);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                for (Map.Entry entry : SqliteStorageImpl.this.f2380a.entrySet()) {
                    sQLiteDatabase.execSQL(((EntityDescriptor) entry.getValue()).m8650b());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            super.onOpen(sQLiteDatabase);
            onCreate(sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            try {
                for (Map.Entry entry : SqliteStorageImpl.this.f2380a.entrySet()) {
                    List<String> m8659a = ((EntityDescriptor) entry.getValue()).m8659a(i, i2);
                    for (int i3 = 0; i3 < m8659a.size(); i3++) {
                        sQLiteDatabase.execSQL(m8659a.get(i3));
                    }
                }
                onCreate(sQLiteDatabase);
                if (SqliteStorageImpl.this.f2384e != null) {
                    SqliteStorageImpl.this.f2384e.mo3116a(i, i2);
                }
            } catch (Exception e) {
                LogUtils.error("SqliteStorageImpl", "Upgrade Database error! drop and reCreate!");
                m8584a(sQLiteDatabase);
                e.printStackTrace();
            }
        }

        /* renamed from: a */
        private void m8584a(SQLiteDatabase sQLiteDatabase) {
            for (Map.Entry entry : SqliteStorageImpl.this.f2380a.entrySet()) {
                sQLiteDatabase.execSQL(((EntityDescriptor) entry.getValue()).m8648c());
            }
            onCreate(sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            m8584a(sQLiteDatabase);
        }
    }
}
