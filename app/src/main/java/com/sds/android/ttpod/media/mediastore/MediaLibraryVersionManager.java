package com.sds.android.ttpod.media.mediastore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.collection.SparseArrayCompat;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.media.mediastore.old.MediaDatabaseHelper;
import com.sds.android.ttpod.media.mediastore.old.MediaStoreOld;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class MediaLibraryVersionManager {
    private static final String TAG = "MediaLibraryVersionManager";
    static final int VERSION_OLD_MEDIA_DB = -1;
    private static MediaLibraryVersionManager mInstance;
    private int mOldVersion = 0;
    private int mNewVersion = 0;

    public static MediaLibraryVersionManager instance() {
        if (mInstance == null) {
            mInstance = new MediaLibraryVersionManager();
        }
        return mInstance;
    }

    private MediaLibraryVersionManager() {
    }

    public void setVersion(int i, int i2) {
        this.mOldVersion = i;
        this.mNewVersion = i2;
    }

    public void doCompact(Context context) {
        doVersionCompact(context);
        this.mOldVersion = this.mNewVersion;
    }

    public boolean isCompacted(Context context) {
        if (this.mOldVersion != 0 && this.mOldVersion != this.mNewVersion) {
            if (this.mOldVersion != -1 || context.getDatabasePath(MediaDatabaseHelper.DBTABASE_NAME).exists()) {
                return false;
            }
            this.mNewVersion = this.mOldVersion;
            return false;
        }
        return true;
    }

    private void doVersionCompact(Context context) {
        LogUtils.m8381c(TAG, "OldVersion:" + this.mOldVersion + " NewVersion:" + this.mNewVersion);
        if (this.mOldVersion < 700) {
            doUpdate2Version700(context);
        }
        if (this.mOldVersion < 701) {
            doUpdate2Version701(context);
        }
        if (this.mOldVersion < 702) {
            doUpdate2Version702(context);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void doUpdate2Version700(Context context) {
        MediaDatabaseHelper mediaDatabaseHelper;
        Throwable th;
        Exception e;
        try {
            mediaDatabaseHelper = new MediaDatabaseHelper(context);
        } catch (Exception e1) {
            e = e1;
            mediaDatabaseHelper = null;
        } catch (Throwable th1) {
            th = th1;
            mediaDatabaseHelper = null;
            if (mediaDatabaseHelper != null) {
            }
            //throw th;
        }
        try {
            try {
                SQLiteDatabase readableDatabase = mediaDatabaseHelper.getReadableDatabase();
                if (readableDatabase != null) {
                    processLocalMedia(context, readableDatabase);
                    processLocalPlaylist(context, readableDatabase);
                    readableDatabase.close();
                }
                if (mediaDatabaseHelper != null) {
                    mediaDatabaseHelper.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (mediaDatabaseHelper != null) {
                    mediaDatabaseHelper.close();
                }
                //throw th;
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            if (mediaDatabaseHelper != null) {
                mediaDatabaseHelper.close();
            }
        }
    }

    private void doUpdate2Version701(Context context) {
        List<MediaItem> queryMediaItemList = MediaStorage.queryMediaItemList(context, MediaStorage.GROUP_ID_RECENTLY_ADD_OLD, MediaStorage.MEDIA_ORDER_BY_ADD_TIME_DESC);
        MediaStorage.insertGroup(context, "recently_add", MediaStorage.GROUP_ID_RECENTLY_ADD, GroupType.CUSTOM_LOCAL);
        if (queryMediaItemList.size() > 0) {
            MediaStorage.insertMediaItems(context, MediaStorage.GROUP_ID_RECENTLY_ADD, queryMediaItemList);
        }
        List<MediaItem> queryMediaItemList2 = MediaStorage.queryMediaItemList(context, MediaStorage.GROUP_ID_RECENTLY_PLAY_OLD, MediaStorage.MEDIA_ORDER_BY_PLAY_TIME_DESC);
        MediaStorage.insertGroup(context, "recently_play", MediaStorage.GROUP_ID_RECENTLY_PLAY, GroupType.CUSTOM_LOCAL);
        if (queryMediaItemList2.size() > 0) {
            MediaStorage.insertMediaItems(context, MediaStorage.GROUP_ID_RECENTLY_PLAY, queryMediaItemList2);
        }
    }

    private void doUpdate2Version702(Context context) {
        int i = 100;
        List<String> queryMediaIDs = MediaStorage.queryMediaIDs(context, MediaStorage.GROUP_ID_RECENTLY_PLAY, MediaStorage.MEDIA_ORDER_BY_PLAY_TIME_DESC);
        if (queryMediaIDs.size() <= 100) {
            return;
        }
        while (true) {
            int i2 = i;
            if (i2 < queryMediaIDs.size()) {
                MediaStorage.deleteMediaItem(context, MediaStorage.GROUP_ID_RECENTLY_PLAY, queryMediaIDs.get(i2));
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    @SuppressLint("Range")
    private MediaItem createOnlineMediaItemFromCursor(Cursor cursor) {
        return new MediaItem(null, Long.valueOf(cursor.getLong(cursor.getColumnIndex("song_id"))), null, null, cursor.getString(cursor.getColumnIndex("title")), cursor.getString(cursor.getColumnIndex("artist")), null, null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, Long.valueOf(cursor.getLong(cursor.getColumnIndex("date_added"))), Long.valueOf(cursor.getLong(cursor.getColumnIndex("date_modified"))), null, true, cursor.getString(cursor.getColumnIndex("_data")), MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
    }

    private MediaItem createMediaItemFromCursor(Cursor cursor) {
        int i;
        try {
            String string = cursor.getString(16);
            if (string.startsWith("/")) {
                int indexOf = string.indexOf(124);
                if (indexOf <= 0) {
                    i = 0;
                } else {
                    String[] split = string.substring(indexOf + 1).split("-");
                    if (split.length != 2) {
                        return null;
                    }
                    int intValue = Integer.valueOf(split[0]).intValue();
                    if (intValue == 0) {
                        intValue = 1;
                    }
                    string = string.substring(0, indexOf);
                    i = intValue;
                }
                return new MediaItem(null, null, string, FileUtils.m8400l(string), cursor.getString(17), cursor.getString(18), cursor.getString(19), cursor.getString(20), cursor.getString(21), cursor.getString(23), Integer.valueOf(i), Integer.valueOf(cursor.getInt(11)), Integer.valueOf(cursor.getInt(9)), 0, 0, Integer.valueOf(cursor.getInt(7)), Integer.valueOf(cursor.getInt(8)), Integer.valueOf(cursor.getInt(15)), cursor.getString(22), Integer.valueOf(cursor.getInt(14)), Integer.valueOf(cursor.getInt(12)), Long.valueOf(cursor.getLong(5)), Long.valueOf(cursor.getLong(4)), Long.valueOf(cursor.getLong(13)), cursor.getInt(6) > 0, null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void processLocalMedia(Context context, SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query(MediaDatabaseHelper.DATABASE_VIEW_MEDIAS, MediaStoreOld.Medias.CURSOR_COLS, null, null, null, null, null);
        if (query != null) {
            int count = query.getCount();
            ArrayList arrayList = new ArrayList(count);
            ArrayList arrayList2 = new ArrayList(count);
            while (query.moveToNext()) {
                MediaItem createMediaItemFromCursor = createMediaItemFromCursor(query);
                if (createMediaItemFromCursor != null) {
                    arrayList.add(createMediaItemFromCursor);
                    if (createMediaItemFromCursor.getFav()) {
                        arrayList2.add(createMediaItemFromCursor);
                    }
                }
            }
            query.close();
            if (!arrayList.isEmpty()) {
                MediaStorage.insertMediaItems(context, MediaStorage.GROUP_ID_ALL_LOCAL, arrayList);
            }
            if (!arrayList2.isEmpty()) {
                MediaStorage.insertMediaItems(context, MediaStorage.GROUP_ID_FAV_LOCAL, arrayList2);
            }
        }
    }

    private void processLocalPlaylist(Context context, SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query("playlists", new String[]{"_id", "name"}, null, null, null, null, null);
        if (query != null) {
            SparseArrayCompat sparseArrayCompat = new SparseArrayCompat(query.getCount());
            while (query.moveToNext()) {
                String buildGroupID = MediaStorage.buildGroupID();
                sparseArrayCompat.append(query.getInt(0), buildGroupID);
                MediaStorage.insertGroup(context, query.getString(1), buildGroupID, GroupType.CUSTOM_LOCAL);
            }
            query.close();
            int size = sparseArrayCompat.size();
            String[] strArr = new String[MediaStoreOld.Medias.CURSOR_COLS.length];
            System.arraycopy(MediaStoreOld.Medias.CURSOR_COLS, 0, strArr, 0, strArr.length);
            strArr[0] = "media_id as _id";
            for (int i = 0; i < size; i++) {
                int keyAt = sparseArrayCompat.keyAt(i);
                processLocalPlaylistMember(context, sQLiteDatabase, "playlists_map inner join media_info on media_id=media_info._id", strArr, keyAt, (String) sparseArrayCompat.get(keyAt));
            }
        }
    }

    private void processLocalPlaylistMember(Context context, SQLiteDatabase sQLiteDatabase, String str, String[] strArr, long j, String str2) {
        Cursor query = sQLiteDatabase.query(str, strArr, "playlist_id=" + j, null, null, null, null);
        if (query != null) {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(createMediaItemFromCursor(query));
            }
            query.close();
            if (!arrayList.isEmpty()) {
                MediaStorage.insertMediaItems(context, str2, arrayList);
            }
        }
    }

    private void processOnlineFavorite(Context context, SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query(MediaDatabaseHelper.DATABASE_TABLE_ONLINE_MEDIAS, new String[]{"song_id", "title", "artist", "_data", "date_added", "date_modified"}, null, null, null, null, null);
        if (query != null) {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(createOnlineMediaItemFromCursor(query));
            }
            query.close();
            if (!arrayList.isEmpty()) {
                MediaStorage.insertMediaItems(context, MediaStorage.buildOnlineFavGroupID(), arrayList);
            }
        }
    }
}
