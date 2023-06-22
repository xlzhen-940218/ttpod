package com.sds.android.ttpod.framework.storage.database;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import com.sds.android.sdk.lib.p061c.SqliteStorageImpl;
import com.sds.android.ttpod.framework.modules.search.SearchContentProvider;
import com.sds.android.ttpod.framework.modules.search.SearchMediaLinkInfo;

import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.storage.database.a */
/* loaded from: classes.dex */
public class SearchSqliteDb {

    /* renamed from: a */
    private static SqliteStorageImpl f6977a;

    /* renamed from: b */
    private static final String[] f6978b = {"media_id", "lyric_path", "lyric_search_time", "artist", "artist_search_time"};

    /* renamed from: a */
    public static void m3133a(Context context) {
        if (f6977a == null) {
            f6977a = new SqliteStorageImpl(context, "search.db", 16777217, new SqliteStorageImpl.InterfaceC0592a() { // from class: com.sds.android.ttpod.framework.storage.database.a.1
                @Override // com.sds.android.sdk.lib.p061c.SqliteStorageImpl.InterfaceC0592a
                /* renamed from: a */
                public void mo3116a(int i, int i2) {
                }
            });
            f6977a.m8596a(SearchMediaLinkInfo.class);
            f6977a.m8599a();
        }
    }

    /* renamed from: a */
    public static void m3131a(SearchMediaLinkInfo searchMediaLinkInfo) {
        f6977a.m8595a(searchMediaLinkInfo);
    }

    /* renamed from: a */
    public static void m3137a(ContentResolver contentResolver, SearchMediaLinkInfo searchMediaLinkInfo) {
        contentResolver.insert(SearchContentProvider.f6308a, m3127c(searchMediaLinkInfo));
    }

    /* renamed from: a */
    public static SearchMediaLinkInfo m3129a(String str) {
        SearchMediaLinkInfo searchMediaLinkInfo = new SearchMediaLinkInfo();
        searchMediaLinkInfo.setMediaId(str);
        List m8593a = f6977a.m8593a(searchMediaLinkInfo, false);
        if (m8593a == null || m8593a.isEmpty()) {
            return null;
        }
        return (SearchMediaLinkInfo) m8593a.get(0);
    }

    /* renamed from: a */
    public static SearchMediaLinkInfo m3135a(ContentResolver contentResolver, String str) {
        return m3132a(contentResolver.query(SearchContentProvider.f6308a, null, str, null, null));
    }

    /* renamed from: a */
    public static void m3130a(SearchMediaLinkInfo searchMediaLinkInfo, String str) {
        SearchMediaLinkInfo searchMediaLinkInfo2 = new SearchMediaLinkInfo();
        searchMediaLinkInfo2.setMediaId(str);
        f6977a.m8594a(searchMediaLinkInfo, searchMediaLinkInfo2);
    }

    /* renamed from: a */
    public static void m3136a(ContentResolver contentResolver, SearchMediaLinkInfo searchMediaLinkInfo, String str) {
        contentResolver.update(SearchContentProvider.f6308a, m3127c(searchMediaLinkInfo), str, null);
    }

    /* renamed from: b */
    public static Cursor m3128b(SearchMediaLinkInfo searchMediaLinkInfo) {
        if (searchMediaLinkInfo == null) {
            return null;
        }
        MatrixCursor matrixCursor = new MatrixCursor(f6978b);
        matrixCursor.addRow(new Object[]{searchMediaLinkInfo.getMediaId(), searchMediaLinkInfo.getLyricPath(), searchMediaLinkInfo.getLyricSearchTime(), searchMediaLinkInfo.getArtist(), searchMediaLinkInfo.getArtistSearchTime()});
        return matrixCursor;
    }

    /* renamed from: a */
    @SuppressLint("Range")
    private static SearchMediaLinkInfo m3132a(Cursor cursor) {
        if (cursor == null || !cursor.moveToFirst()) {
            return null;
        }
        SearchMediaLinkInfo searchMediaLinkInfo = new SearchMediaLinkInfo();
        searchMediaLinkInfo.setMediaId(cursor.getString(cursor.getColumnIndex("media_id")));
        searchMediaLinkInfo.setLyricPath(cursor.getString(cursor.getColumnIndex("lyric_path")));
        searchMediaLinkInfo.setLyricSearchTime(Long.valueOf(cursor.getLong(cursor.getColumnIndex("lyric_search_time"))));
        searchMediaLinkInfo.setArtist(cursor.getString(cursor.getColumnIndex("artist")));
        searchMediaLinkInfo.setArtistSearchTime(Long.valueOf(cursor.getLong(cursor.getColumnIndex("artist_search_time"))));
        cursor.close();
        return searchMediaLinkInfo;
    }

    /* renamed from: c */
    public static ContentValues m3127c(SearchMediaLinkInfo searchMediaLinkInfo) {
        if (searchMediaLinkInfo == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("media_id", searchMediaLinkInfo.getMediaId());
        contentValues.put("lyric_path", searchMediaLinkInfo.getLyricPath());
        contentValues.put("lyric_search_time", searchMediaLinkInfo.getLyricSearchTime());
        contentValues.put("artist", searchMediaLinkInfo.getArtist());
        contentValues.put("artist_search_time", searchMediaLinkInfo.getArtistSearchTime());
        return contentValues;
    }

    /* renamed from: a */
    public static SearchMediaLinkInfo m3134a(ContentValues contentValues) {
        if (contentValues == null) {
            return null;
        }
        SearchMediaLinkInfo searchMediaLinkInfo = new SearchMediaLinkInfo();
        searchMediaLinkInfo.setMediaId(contentValues.getAsString("media_id"));
        searchMediaLinkInfo.setLyricPath(contentValues.getAsString("lyric_path"));
        searchMediaLinkInfo.setLyricSearchTime(contentValues.getAsLong("lyric_search_time"));
        searchMediaLinkInfo.setArtist(contentValues.getAsString("artist"));
        searchMediaLinkInfo.setArtistSearchTime(contentValues.getAsLong("artist_search_time"));
        return searchMediaLinkInfo;
    }
}
