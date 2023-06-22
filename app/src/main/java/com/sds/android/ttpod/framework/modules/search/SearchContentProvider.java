package com.sds.android.ttpod.framework.modules.search;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.sds.android.ttpod.framework.storage.database.SearchSqliteDb;


/* loaded from: classes.dex */
public class SearchContentProvider extends ContentProvider {

    /* renamed from: b */
    private static final String f6309b = SearchContentProvider.class.getName();

    /* renamed from: c */
    private static final String f6310c = "content://" + f6309b;

    /* renamed from: d */
    private static final String f6311d = SearchMediaLinkInfo.class.getSimpleName();

    /* renamed from: a */
    public static final Uri f6308a = Uri.parse(f6310c + "/" + f6311d + "/");

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return SearchSqliteDb.m3128b(SearchSqliteDb.m3129a(str));
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        SearchSqliteDb.m3131a(SearchSqliteDb.m3134a(contentValues));
        return uri;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        SearchMediaLinkInfo m3134a = SearchSqliteDb.m3134a(contentValues);
        SearchSqliteDb.m3130a(m3134a, m3134a.getMediaId());
        return 1;
    }
}
