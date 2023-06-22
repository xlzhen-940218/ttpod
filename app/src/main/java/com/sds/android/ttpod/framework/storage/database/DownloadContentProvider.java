package com.sds.android.ttpod.framework.storage.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DownloadContentProvider extends ContentProvider {

    /* renamed from: b */
    private static final String f6974b = DownloadContentProvider.class.getName();

    /* renamed from: c */
    private static final String f6975c = "content://" + f6974b;

    /* renamed from: d */
    private static final String f6976d = DownloadTaskInfo.class.getSimpleName();

    /* renamed from: a */
    public static final Uri f6973a = Uri.parse(f6975c + "/");

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        SqliteDb.m3126a(getContext());
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        try {
            List arrayList = new ArrayList();
            int indexOf = str.indexOf("=");
            String substring = str.substring(0, indexOf);
            if (substring.equals("save_path")) {
                arrayList = SqliteDb.m3118b(new DownloadTaskInfo(str.substring(indexOf + 1)));
            } else if (substring.equals("info_type")) {
                arrayList = SqliteDb.m3118b(new DownloadTaskInfo(Integer.valueOf(str.substring(indexOf + 1))));
            }
            return SqliteDb.m3120a(arrayList);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return uri;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        try {
            SqliteDb.m3117c(new DownloadTaskInfo(str));
            return 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 1;
    }
}
