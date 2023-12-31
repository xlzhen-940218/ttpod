package com.sds.android.ttpod.framework.storage.environment;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.SharedPreferencesUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;

import java.util.Set;

/* loaded from: classes.dex */
public final class EnvironmentContentProvider extends ContentProvider {

    /* renamed from: e */
    private SharedPreferences sharedPreferences;

    /* renamed from: c */
    private static final String className = EnvironmentContentProvider.class.getName();

    /* renamed from: d */
    private static final String contentHost = "content://" + className;

    /* renamed from: a */
    static final String preferencesContent = contentHost + "/" + Preferences.class.getSimpleName() + "/";

    /* renamed from: b */
    static final String runTimeHost = contentHost + "/" + RunTime.class.getSimpleName() + "/";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.framework.storage.environment.EnvironmentContentProvider$a */
    /* loaded from: classes.dex */
    public enum EnumC2029a {
        STRING,
        SET,
        MAP
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.sharedPreferences = getContext().getSharedPreferences("preference", 0);
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        m3105d(uri);
        if (m3110a(uri)) {
            return m3108a(uri, str);
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        m3105d(uri);
        if (m3110a(uri)) {
            return m3106c(uri);
        }
        return 0;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        m3105d(uri);
        if (m3110a(uri)) {
            return m3109a(uri, contentValues, str);
        }
        return 0;
    }

    /* renamed from: a */
    private boolean m3110a(Uri uri) {
        return uri.toString().startsWith(preferencesContent);
    }

    /* renamed from: b */
    private String m3107b(Uri uri) {
        m3105d(uri);
        return uri.toString().substring(m3110a(uri) ? preferencesContent.length() : runTimeHost.length());
    }

    /* renamed from: a */
    private Cursor m3108a(Uri uri, String str) {
        String m3107b = m3107b(uri);
        if (this.sharedPreferences.contains(m3107b)) {
            if (StringUtils.equals(EnumC2029a.STRING.name(), str)) {
                MatrixCursor matrixCursor = new MatrixCursor(new String[]{m3107b});
                matrixCursor.addRow(new Object[]{this.sharedPreferences.getString(m3107b, "")});
                return matrixCursor;
            } else if (StringUtils.equals(EnumC2029a.SET.name(), str)) {
                Set<String> m3101a = AccessHelper.m3101a(this.sharedPreferences.getString(m3107b, null));
                String[] strArr = new String[m3101a.size()];
                Object[] objArr = new Object[m3101a.size()];
                int i = 0;
                for (String str2 : m3101a) {
                    strArr[i] = str2;
                    objArr[i] = str2;
                    i++;
                }
                MatrixCursor matrixCursor2 = new MatrixCursor(strArr);
                matrixCursor2.addRow(objArr);
                return matrixCursor2;
            }
        }
        return null;
    }

    /* renamed from: c */
    private int m3106c(Uri uri) {
        SharedPreferencesUtils.m8348a(this.sharedPreferences.edit().remove(m3107b(uri)));
        return 0;
    }

    /* renamed from: a */
    private int m3109a(Uri uri, ContentValues contentValues, String str) {
        String m3107b = m3107b(uri);
        if (StringUtils.equals(EnumC2029a.STRING.name(), str)) {
            SharedPreferencesUtils.m8348a(this.sharedPreferences.edit().putString(m3107b, contentValues.getAsString(m3107b)));
        } else if (StringUtils.equals(EnumC2029a.SET.name(), str)) {
            SharedPreferencesUtils.m8348a(this.sharedPreferences.edit().putString(m3107b, contentValues.getAsString(m3107b)));
        }
        try {
            if (m3107b.contains("PREFIX")) {
                m3107b = m3107b.substring(0, m3107b.indexOf("PREFIX") + "PREFIX".length());
            }
            if (PreferencesID.valueOf(m3107b).isNotifyChanged()) {
                BaseApplication.getApplication().sendBroadcast(new Intent(Action.PREFERENCES_CHANGED).putExtra("preferences_id", m3107b));
            }
        } catch (Exception e) {
            LogUtils.warning("EnvironmentContentProvider", "key:" + m3107b + " not existed!");
        }
        return 0;
    }

    /* renamed from: d */
    private void m3105d(Uri uri) {
        if (EnvironmentUtils.AppConfig.getTestMode()) {
            String uri2 = uri.toString();
            if (!uri2.startsWith(preferencesContent) && !uri2.startsWith(runTimeHost)) {
                throw new UnsupportedOperationException(uri2 + ": Type not be supported!");
            }
        }
    }
}
