package com.sds.android.ttpod.media.mediastore;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class MediaDBHelper extends SQLiteOpenHelper {
    public static final String ACTION_UPDATE_DB_VERSION = MediaDBHelper.class.getName() + "update_db_version";
    private static final String DB_NAME = "media.db";
    private static final int DB_VERSION = 702;
    static final int DB_VERSION_7_0_0 = 700;
    static final int DB_VERSION_7_0_1 = 701;
    static final int DB_VERSION_7_0_2 = 702;
    static final String GROUP_COLUMN_ADDED_TIMESTAMP = "group_added_time";
    static final String GROUP_COLUMN_ASSOCIATE_TABLE_LOCAL_NAME = "associate_table_local_name";
    static final String GROUP_COLUMN_ASSOCIATE_TABLE_ONLINE_NAME = "associate_table_online_name";
    static final String GROUP_COLUMN_CUR_BASE_ORDERBY = "base_orderby";
    static final String GROUP_COLUMN_MAP_TABLE_NAME = "map_table_name";
    static final String GROUP_COLUMN_NAME = "name";
    static final String GROUP_MAP_ADDED_TIMESTAMP = "group_map_added_time";
    static final String GROUP_MAP_COLUMN_MEDIA_ID = "_id";
    static final String GROUP_MAP_COLUMN_SORT_ORDER = "sort_order";
    public static final String KEY_DB_VERSION_NEW = "db_version_new";
    public static final String KEY_DB_VERSION_OLD = "db_version_old";
    static final String MEDIA_COLUMN_ADDED_TIMESTAMP = "added_time";
    static final String MEDIA_COLUMN_ALBUM = "album";
    static final String MEDIA_COLUMN_ALBUM_KEY = "album_key";
    static final String MEDIA_COLUMN_ARTIST = "artist";
    static final String MEDIA_COLUMN_ARTIST_KEY = "artist_key";
    static final String MEDIA_COLUMN_BIT_RATE = "bit_rate";
    static final String MEDIA_COLUMN_CHANNELS = "channels";
    static final String MEDIA_COLUMN_COMMENT = "comment";
    static final String MEDIA_COLUMN_COMPOSER = "composer";
    static final String MEDIA_COLUMN_DURATION = "duration";
    static final String MEDIA_COLUMN_ERROR_STATUS = "error_status";
    static final String MEDIA_COLUMN_EXTRA = "extra";
    static final String MEDIA_COLUMN_FILE_NAME_KEY = "file_name_key";
    static final String MEDIA_COLUMN_FOLDER = "folder";
    static final String MEDIA_COLUMN_GENRE = "genre";
    static final String MEDIA_COLUMN_GENRE_KEY = "genre_key";
    static final String MEDIA_COLUMN_GRADE = "grade";
    static final String MEDIA_COLUMN_ID = "_id";
    static final String MEDIA_COLUMN_LAST_PLAY_TIMESTAMP = "last_play_time";
    static final String MEDIA_COLUMN_LOCAL_DATA_SOURCE = "local_data_source";
    static final String MEDIA_COLUMN_MIME_TYPE = "mime_type";
    static final String MEDIA_COLUMN_MODIFIED_TIME_STAMP = "modified_time";
    static final String MEDIA_COLUMN_SAMPLE_RATE = "sample_rate";
    static final String MEDIA_COLUMN_SIZE = "size";
    static final String MEDIA_COLUMN_SONG_ID = "song_id";
    static final String MEDIA_COLUMN_START_TIME = "start_time";
    static final String MEDIA_COLUMN_TITLE = "title";
    static final String MEDIA_COLUMN_TITLE_KEY = "title_key";
    static final String MEDIA_COLUMN_TRACK = "track";
    static final String MEDIA_COLUMN_USE_COUNT = "use_count";
    static final String MEDIA_COLUMN_YEAR = "year";
    private static final String TAG = "MediaDBHelper";
    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface TransactionCallback<Result> {
        Result runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaDBHelper(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 702);
        this.mContext = context;
        openDatabaseAsync();
    }

    private void openDatabaseAsync() {
        Thread thread = new Thread(new Runnable() { // from class: com.sds.android.ttpod.media.mediastore.MediaDBHelper.1
            @Override // java.lang.Runnable
            public void run() {
                MediaDBHelper.this.tryConnectDatabase();
            }
        });
        thread.setPriority(10);
        thread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SQLiteDatabase tryConnectDatabase() {
        SQLiteDatabase sQLiteDatabase;
        synchronized (this.mContext) {
            if (this.mSQLiteDatabase == null) {
                this.mSQLiteDatabase = getWritableDatabase();
            }
            sQLiteDatabase = this.mSQLiteDatabase;
        }
        return sQLiteDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = super.getWritableDatabase();
        } catch (Exception e) {
            sQLiteDatabase = null;
        }
        return sQLiteDatabase;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createMediaTable(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("CREATE TABLE " + str + " (_id TEXT PRIMARY KEY NOT NULL,song_id INTEGER NULL," + MEDIA_COLUMN_LOCAL_DATA_SOURCE + " TEXT DEFAULT NULL,folder TEXT NULL," + MEDIA_COLUMN_SIZE + " INTEGER NULL,title TEXT NULL,title_key TEXT NULL,artist TEXT NULL,artist_key TEXT NULL,album TEXT NULL,album_key TEXT NULL,genre TEXT NULL,genre_key TEXT NULL,file_name_key TEXT NULL,composer TEXT NULL,mime_type TEXT NULL," + MEDIA_COLUMN_START_TIME + " INTEGER DEFAULT 0 NULL,duration INTEGER DEFAULT 0 NULL,track INTEGER NULL,year INTEGER NULL," + MEDIA_COLUMN_GRADE + " INTEGER NULL," + MEDIA_COLUMN_BIT_RATE + " INTEGER NULL,sample_rate INTEGER NULL,channels INTEGER NULL,comment TEXT NULL," + MEDIA_COLUMN_ERROR_STATUS + " INTEGER DEFAULT 0 NULL,use_count INTEGER DEFAULT 0 NULL,added_time INTEGER NULL," + MEDIA_COLUMN_MODIFIED_TIME_STAMP + " INTEGER NULL,last_play_time INTEGER NULL," + MEDIA_COLUMN_EXTRA + " TEXT NULL);");
    }

    private void createGroupTable(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("CREATE TABLE " + str + " (name TEXT NOT NULL," + GROUP_COLUMN_MAP_TABLE_NAME + " TEXT PRIMARY KEY NOT NULL," + GROUP_COLUMN_ADDED_TIMESTAMP + " INTEGER NOT NULL," + GROUP_COLUMN_ASSOCIATE_TABLE_LOCAL_NAME + " TEXT DEFAULT NULL," + GROUP_COLUMN_ASSOCIATE_TABLE_ONLINE_NAME + " TEXT DEFAULT NULL, " + GROUP_COLUMN_CUR_BASE_ORDERBY + " TEXT DEFAULT NULL);");
    }

    void createGroupMapTable(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE " + str + " (sort_order INTEGER PRIMARY KEY AUTOINCREMENT,group_map_added_time INTEGER NOT NULL,_id TEXT KEY NOT NULL);");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        createMediaTable(sQLiteDatabase, "media_local");
        createGroupTable(sQLiteDatabase, "group_");
        doInsertLocalCustomGroup(sQLiteDatabase, "all_local", MediaStorage.GROUP_ID_ALL_LOCAL);
        doInsertLocalCustomGroup(sQLiteDatabase, "fav_local", MediaStorage.GROUP_ID_FAV_LOCAL);
        doInsertLocalCustomGroup(sQLiteDatabase, "recently_add", MediaStorage.GROUP_ID_RECENTLY_ADD);
        doInsertLocalCustomGroup(sQLiteDatabase, "recently_play", MediaStorage.GROUP_ID_RECENTLY_PLAY);
        LogUtils.error("MediaDB", "onCreate");
        notifyUpdateDbVersion(-1, 702);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        LogUtils.warning(TAG, "onUpgrade: From " + i + " to " + i2);
        notifyUpdateDbVersion(i, i2);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        LogUtils.warning(TAG, "onDowngrade: From " + i + " to " + i2);
        notifyUpdateDbVersion(i, i2);
    }

    private void notifyUpdateDbVersion(int i, int i2) {
        this.mContext.sendBroadcast(new Intent(ACTION_UPDATE_DB_VERSION).putExtra(KEY_DB_VERSION_OLD, i).putExtra(KEY_DB_VERSION_NEW, i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertMediaItemToGroup(final String str, final ContentValues contentValues) {
        doWithTransaction(null, new TransactionCallback<Void>() { // from class: com.sds.android.ttpod.media.mediastore.MediaDBHelper.2
            @Override // com.sds.android.ttpod.media.mediastore.MediaDBHelper.TransactionCallback
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                if (str.equals(MediaStorage.GROUP_ID_ALL_LOCAL)) {
                    sQLiteDatabase.insert("media_local", null, contentValues);
                }
                List customMediaIDs = MediaDBHelper.this.getCustomMediaIDs(sQLiteDatabase, str);
                String asString = contentValues.getAsString("_id");
                if (!customMediaIDs.contains(asString)) {
                    Long asLong = contentValues.getAsLong("song_id");
                    if (asLong == null || !StringUtils.equals(MediaItem.genIDWithSongID(asLong), asString)) {
                        MediaDBHelper.this.associateLocalMediaTable(sQLiteDatabase, str);
                    } else {
                        sQLiteDatabase.insert(MediaDBHelper.this.associateOnlineMediaTable(sQLiteDatabase, str), null, contentValues);
                    }
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("_id", asString);
                    contentValues2.put("group_map_added_time", Long.valueOf(System.currentTimeMillis()));
                    sQLiteDatabase.insert(str, null, contentValues2);
                }
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertMediaItemsToGroup(final String str, final ContentValues[] contentValuesArr) {
        doWithTransaction(null, new TransactionCallback<Void>() { // from class: com.sds.android.ttpod.media.mediastore.MediaDBHelper.3
            @Override // com.sds.android.ttpod.media.mediastore.MediaDBHelper.TransactionCallback
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                String str2;
                String str3;
                long j;
                String str4;
                String str5;
                String str6 = str;
                String str7 = null;
                String str8 = null;
                long currentTimeMillis = System.currentTimeMillis();
                if (str.equals(MediaStorage.GROUP_ID_ALL_LOCAL)) {
                    for (ContentValues contentValues : contentValuesArr) {
                        sQLiteDatabase.insert("media_local", null, contentValues);
                    }
                }
                List customMediaIDs = MediaDBHelper.this.getCustomMediaIDs(sQLiteDatabase, str6);
                ContentValues[] contentValuesArr2 = contentValuesArr;
                int length = contentValuesArr2.length;
                int i = 0;
                while (i < length) {
                    ContentValues contentValues2 = contentValuesArr2[i];
                    String asString = contentValues2.getAsString("_id");
                    if (customMediaIDs.contains(asString)) {
                        str2 = str7;
                        str3 = str8;
                        j = currentTimeMillis;
                    } else {
                        Object obj = contentValues2.get("song_id");
                        if (obj != null && contentValues2.get("_id").equals(MediaItem.genIDWithSongID((Long) obj))) {
                            String associateOnlineMediaTable = str7 == null ? MediaDBHelper.this.associateOnlineMediaTable(sQLiteDatabase, str6) : str7;
                            sQLiteDatabase.insert(associateOnlineMediaTable, null, contentValues2);
                            String str9 = str8;
                            str5 = associateOnlineMediaTable;
                            str4 = str9;
                        } else if (str8 == null) {
                            str4 = MediaDBHelper.this.associateLocalMediaTable(sQLiteDatabase, str6);
                            str5 = str7;
                        } else {
                            str4 = str8;
                            str5 = str7;
                        }
                        ContentValues contentValues3 = new ContentValues();
                        contentValues3.put("_id", asString);
                        contentValues3.put("group_map_added_time", Long.valueOf(currentTimeMillis));
                        sQLiteDatabase.insert(str6, null, contentValues3);
                        str3 = str4;
                        str2 = str5;
                        j = 1 + currentTimeMillis;
                    }
                    i++;
                    currentTimeMillis = j;
                    str8 = str3;
                    str7 = str2;
                }
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> getCustomMediaIDs(SQLiteDatabase sQLiteDatabase, String str) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT _id FROM " + str, null);
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                arrayList.add(rawQuery.getString(0));
            }
            rawQuery.close();
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String associateOnlineMediaTable(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor rawQuery = sQLiteDatabase.rawQuery(Builder.buildCustomGroupAssociateMediaTableNameSelection(str), null);
        if (rawQuery != null && rawQuery.moveToNext()) {
            String string = rawQuery.getString(1);
            if (string == null) {
                string = Builder.buildOnlineMediaTableName();
                createMediaTable(sQLiteDatabase, string);
                ContentValues contentValues = new ContentValues();
                contentValues.put(GROUP_COLUMN_ASSOCIATE_TABLE_ONLINE_NAME, string);
                sQLiteDatabase.update("group_", contentValues, "map_table_name=" + Builder.embraceWithSingleQuotationMarks(str), null);
            }
            rawQuery.close();
            return string;
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
        throw new IllegalArgumentException("do not have the table " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String associateLocalMediaTable(SQLiteDatabase sQLiteDatabase, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(GROUP_COLUMN_ASSOCIATE_TABLE_LOCAL_NAME, "media_local");
        sQLiteDatabase.update("group_", contentValues, "map_table_name=" + Builder.embraceWithSingleQuotationMarks(str), null);
        return "media_local";
    }

    void deleteTable(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("DROP TABLE " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deleteGroup(final String str) {
        doWithTransaction(null, new TransactionCallback<Void>() { // from class: com.sds.android.ttpod.media.mediastore.MediaDBHelper.4
            @Override // com.sds.android.ttpod.media.mediastore.MediaDBHelper.TransactionCallback
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                String associateOnlineTableName = MediaDBHelper.this.getAssociateOnlineTableName(sQLiteDatabase, str);
                if (associateOnlineTableName != null) {
                    MediaDBHelper.this.deleteTable(sQLiteDatabase, associateOnlineTableName);
                }
                MediaDBHelper.this.deleteTable(sQLiteDatabase, str);
                sQLiteDatabase.delete("group_", "map_table_name=" + Builder.embraceWithSingleQuotationMarks(str), null);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearGroup(final String str) {
        doWithTransaction(tryConnectDatabase(), new TransactionCallback<Void>() { // from class: com.sds.android.ttpod.media.mediastore.MediaDBHelper.5
            @Override // com.sds.android.ttpod.media.mediastore.MediaDBHelper.TransactionCallback
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                String str2 = str;
                String associateOnlineTableName = MediaDBHelper.this.getAssociateOnlineTableName(sQLiteDatabase, str2);
                if (associateOnlineTableName != null) {
                    sQLiteDatabase.execSQL("DELETE FROM " + associateOnlineTableName);
                }
                if (str.equals(MediaStorage.GROUP_ID_ALL_LOCAL)) {
                    sQLiteDatabase.execSQL("DELETE FROM media_local");
                }
                sQLiteDatabase.execSQL("DELETE FROM " + str2);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deleteMediaItems(final String str, final String str2) {
        doWithTransaction(tryConnectDatabase(), new TransactionCallback<Object>() { // from class: com.sds.android.ttpod.media.mediastore.MediaDBHelper.6
            @Override // com.sds.android.ttpod.media.mediastore.MediaDBHelper.TransactionCallback
            public Object runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                String str3 = str;
                String associateOnlineTableName = MediaDBHelper.this.getAssociateOnlineTableName(sQLiteDatabase, str3);
                if (associateOnlineTableName != null) {
                    sQLiteDatabase.delete(associateOnlineTableName, str2, null);
                }
                sQLiteDatabase.delete(str3, str2, null);
                if (str.equals(MediaStorage.GROUP_ID_ALL_LOCAL)) {
                    sQLiteDatabase.delete("media_local", str2, null);
                }
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateGroup(String str, ContentValues contentValues) {
        tryConnectDatabase().update("group_", contentValues, "map_table_name=" + Builder.embraceWithSingleQuotationMarks(str), null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateMediaItem(final String str, final ContentValues contentValues, final String str2) {
        doWithTransaction(null, new TransactionCallback<Void>() { // from class: com.sds.android.ttpod.media.mediastore.MediaDBHelper.7
            @Override // com.sds.android.ttpod.media.mediastore.MediaDBHelper.TransactionCallback
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                Object obj = contentValues.get("song_id");
                if (obj != null && contentValues.get("_id").equals(MediaItem.genIDWithSongID((Long) obj))) {
                    String str3 = str;
                    if (MediaStorage.GROUP_ID_FAV.equals(str)) {
                        str3 = MediaStorage.buildOnlineFavGroupID();
                    }
                    Cursor rawQuery = sQLiteDatabase.rawQuery(Builder.buildCustomGroupAssociateMediaTableNameSelection(str3), null);
                    if (rawQuery != null) {
                        if (rawQuery.moveToNext()) {
                            String string = rawQuery.getString(1);
                            rawQuery.close();
                            sQLiteDatabase.update(string, contentValues, str2, null);
                        } else {
                            rawQuery.close();
                        }
                    }
                } else {
                    sQLiteDatabase.update("media_local", contentValues, str2, null);
                }
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void exchangeSortOrder(final String str, final ContentValues[] contentValuesArr) {
        doWithTransaction(null, new TransactionCallback<Void>() { // from class: com.sds.android.ttpod.media.mediastore.MediaDBHelper.8
            @Override // com.sds.android.ttpod.media.mediastore.MediaDBHelper.TransactionCallback
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                ContentValues[] contentValuesArr2;
                ContentValues contentValues = new ContentValues();
                for (ContentValues contentValues2 : contentValuesArr) {
                    String asString = contentValues2.getAsString("exchange_key_1");
                    String asString2 = contentValues2.getAsString("exchange_key_2");
                    long j = 0;
                    long j2 = 0;
                    Cursor rawQuery = sQLiteDatabase.rawQuery(Builder.buildAddCustomGroupSelection(str, asString), null);
                    if (rawQuery != null) {
                        if (rawQuery.moveToNext()) {
                            j = rawQuery.getLong(0);
                            j2 = rawQuery.getLong(1);
                        }
                        rawQuery.close();
                        long j3 = 0;
                        long j4 = 0;
                        Cursor rawQuery2 = sQLiteDatabase.rawQuery(Builder.buildAddCustomGroupSelection(str, asString2), null);
                        if (rawQuery2 != null) {
                            if (rawQuery2.moveToNext()) {
                                j3 = rawQuery2.getLong(0);
                                j4 = rawQuery2.getLong(1);
                            }
                            rawQuery2.close();
                            contentValues.put("_id", asString2);
                            contentValues.put("group_map_added_time", Long.valueOf(j4));
                            sQLiteDatabase.update(str, contentValues, "sort_order=" + j, null);
                            contentValues.put("_id", asString);
                            contentValues.put("group_map_added_time", Long.valueOf(j2));
                            sQLiteDatabase.update(str, contentValues, "sort_order=" + j3, null);
                        }
                    }
                }
                MediaDBHelper.this.updateCurOrderBy(sQLiteDatabase, str, "sort_order");
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCurOrderBy(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        sQLiteDatabase.execSQL("UPDATE group_ SET base_orderby=" + Builder.embraceWithSingleQuotationMarks(str2) + " WHERE " + GROUP_COLUMN_MAP_TABLE_NAME + "=" + Builder.embraceWithSingleQuotationMarks(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cursor queryMediaItem(final String str, final String str2, final String str3) {
        return (Cursor) doWithTransaction(null, new TransactionCallback<Cursor>() { // from class: com.sds.android.ttpod.media.mediastore.MediaDBHelper.9
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.sds.android.ttpod.media.mediastore.MediaDBHelper.TransactionCallback
            public Cursor runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                long j;
                String str4;
                String str5;
                String str6 = str2;
                if (str.startsWith(MediaStorage.GROUP_ID_ALBUM_PREFIX)) {
                    return sQLiteDatabase.rawQuery(Builder.buildMediaItemSelectionFromMediaLocal((str6 == null ? "" : str6 + " AND ") + "album=" + Builder.embraceWithSingleQuotationMarks(str.substring(MediaStorage.GROUP_ID_ALBUM_PREFIX.length())), str3), null);
                } else if (str.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX)) {
                    return sQLiteDatabase.rawQuery(Builder.buildMediaItemSelectionFromMediaLocal((str6 == null ? "" : str6 + " AND ") + "folder=" + Builder.embraceWithSingleQuotationMarks(str.substring(MediaStorage.GROUP_ID_FOLDER_PREFIX.length())), str3), null);
                } else if (str.startsWith(MediaStorage.GROUP_ID_ARTIST_PREFIX)) {
                    return sQLiteDatabase.rawQuery(Builder.buildMediaItemSelectionFromMediaLocal((str6 == null ? "" : str6 + " AND ") + "artist=" + Builder.embraceWithSingleQuotationMarks(str.substring(MediaStorage.GROUP_ID_ARTIST_PREFIX.length())), str3), null);
                } else if (str.startsWith(MediaStorage.GROUP_ID_GENRE_PREFIX)) {
                    return sQLiteDatabase.rawQuery(Builder.buildMediaItemSelectionFromMediaLocal((str6 == null ? "" : str6 + " AND ") + "genre=" + Builder.embraceWithSingleQuotationMarks(str.substring(MediaStorage.GROUP_ID_GENRE_PREFIX.length())), str3), null);
                } else if (str.equals(MediaStorage.GROUP_ID_RECENTLY_ADD_OLD)) {
                    return sQLiteDatabase.rawQuery(Builder.buildMediaItemSelectionFromMediaLocal(str6, str3), null);
                } else {
                    if (str.equals(MediaStorage.GROUP_ID_RECENTLY_PLAY_OLD)) {
                        return sQLiteDatabase.rawQuery(Builder.buildMediaItemSelectionFromMediaLocal((str6 == null ? "" : str6 + " AND ") + "last_play_time<>'0'", str3), null);
                    } else if (str.equals(MediaStorage.GROUP_ID_FAV)) {
                        String str7 = str3 == null ? "sort_order" : str3;
                        if (str7.endsWith(" DESC")) {
                            str7 = str7.substring(0, str7.length() - " DESC".length());
                        }
                        String buildMediaItemSelectionSelection = Builder.buildMediaItemSelectionSelection(MediaStorage.GROUP_ID_FAV_LOCAL, "media_local", "'1'", str7);
                        String onlineFavTable = Builder.getOnlineFavTable(sQLiteDatabase);
                        if (onlineFavTable != null) {
                            String associateOnlineTableName = MediaDBHelper.this.getAssociateOnlineTableName(sQLiteDatabase, onlineFavTable);
                            String buildMediaItemSelectionSelection2 = Builder.buildMediaItemSelectionSelection(onlineFavTable, associateOnlineTableName, "'1'", str7);
                            str4 = "SELECT * FROM (" + buildMediaItemSelectionSelection + ") WHERE song_id NOT IN (SELECT song_id FROM " + associateOnlineTableName + ") OR song_id IS NULL";
                            str5 = buildMediaItemSelectionSelection2;
                        } else {
                            str4 = buildMediaItemSelectionSelection;
                            str5 = null;
                        }
                        if (str5 != null) {
                            str4 = str4 + " UNION " + str5;
                        }
                        return sQLiteDatabase.rawQuery("SELECT * FROM (" + str4 + ")" + (str2 == null ? "" : " WHERE " + str2) + " ORDER BY " + Builder.unionOrderBy(str3), null);
                    } else {
                        Cursor rawQuery = sQLiteDatabase.rawQuery(Builder.buildCustomGroupAssociateMediaTableNameSelection(str), null);
                        if (rawQuery != null) {
                            if (rawQuery.moveToNext()) {
                                String string = rawQuery.getString(0);
                                String string2 = rawQuery.getString(1);
                                String string3 = rawQuery.getString(2);
                                rawQuery.close();
                                String buildMediaItemSelectionFromGroupMapSelection = Builder.buildMediaItemSelectionFromGroupMapSelection(str, string, string2, Builder.getOnlineFavTable(sQLiteDatabase), str6, str3);
                                Cursor rawQuery2 = sQLiteDatabase.rawQuery(buildMediaItemSelectionFromGroupMapSelection, null);
                                if (StringUtils.isEmpty(str6) && !StringUtils.equals(string3, str3)) {
                                    Cursor rawQuery3 = sQLiteDatabase.rawQuery("SELECT MAX(sort_order) FROM " + str, null);
                                    if (rawQuery3 != null) {
                                        j = rawQuery3.moveToNext() ? rawQuery3.getLong(0) : 0L;
                                        rawQuery3.close();
                                    } else {
                                        j = 0;
                                    }
                                    sQLiteDatabase.execSQL("INSERT INTO " + str + " (_id,group_map_added_time)  SELECT _id,group_map_added_time FROM (" + buildMediaItemSelectionFromGroupMapSelection + ")");
                                    if (j > 0) {
                                        sQLiteDatabase.execSQL("DELETE FROM " + str + " WHERE sort_order<=" + j);
                                    }
                                    MediaDBHelper.this.updateCurOrderBy(sQLiteDatabase, str, str3);
                                }
                                return rawQuery2;
                            }
                            rawQuery.close();
                        }
                        return null;
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertLocalCustomGroup(SQLiteDatabase sQLiteDatabase, final String str, final String str2) {
        doWithTransaction(sQLiteDatabase, new TransactionCallback<Void>() { // from class: com.sds.android.ttpod.media.mediastore.MediaDBHelper.10
            @Override // com.sds.android.ttpod.media.mediastore.MediaDBHelper.TransactionCallback
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase2) throws Exception {
                MediaDBHelper.this.doInsertLocalCustomGroup(sQLiteDatabase2, str, str2);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doInsertLocalCustomGroup(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        createGroupMapTable(sQLiteDatabase, str2);
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", str);
        contentValues.put(GROUP_COLUMN_MAP_TABLE_NAME, str2);
        contentValues.put(GROUP_COLUMN_ASSOCIATE_TABLE_LOCAL_NAME, "media_local");
        contentValues.put(GROUP_COLUMN_ADDED_TIMESTAMP, Long.valueOf(System.currentTimeMillis()));
        sQLiteDatabase.insert("group_", null, contentValues);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertMixCustomGroup(SQLiteDatabase sQLiteDatabase, final String str, final String str2) {
        doWithTransaction(sQLiteDatabase, new TransactionCallback<Void>() { // from class: com.sds.android.ttpod.media.mediastore.MediaDBHelper.11
            @Override // com.sds.android.ttpod.media.mediastore.MediaDBHelper.TransactionCallback
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase2) throws Exception {
                MediaDBHelper.this.createGroupMapTable(sQLiteDatabase2, str2);
                String buildOnlineMediaTableName = Builder.buildOnlineMediaTableName();
                MediaDBHelper.this.createMediaTable(sQLiteDatabase2, buildOnlineMediaTableName);
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", str);
                contentValues.put(MediaDBHelper.GROUP_COLUMN_MAP_TABLE_NAME, str2);
                contentValues.put(MediaDBHelper.GROUP_COLUMN_ASSOCIATE_TABLE_LOCAL_NAME, "media_local");
                contentValues.put(MediaDBHelper.GROUP_COLUMN_ASSOCIATE_TABLE_ONLINE_NAME, buildOnlineMediaTableName);
                contentValues.put(MediaDBHelper.GROUP_COLUMN_ADDED_TIMESTAMP, Long.valueOf(System.currentTimeMillis()));
                sQLiteDatabase2.insert("group_", null, contentValues);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertOnlineCustomGroup(SQLiteDatabase sQLiteDatabase, final String str, final String str2) {
        doWithTransaction(sQLiteDatabase, new TransactionCallback<Void>() { // from class: com.sds.android.ttpod.media.mediastore.MediaDBHelper.12
            @Override // com.sds.android.ttpod.media.mediastore.MediaDBHelper.TransactionCallback
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase2) throws Exception {
                MediaDBHelper.this.createGroupMapTable(sQLiteDatabase2, str2);
                String buildOnlineMediaTableName = Builder.buildOnlineMediaTableName();
                MediaDBHelper.this.createMediaTable(sQLiteDatabase2, buildOnlineMediaTableName);
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", str);
                contentValues.put(MediaDBHelper.GROUP_COLUMN_MAP_TABLE_NAME, str2);
                contentValues.put(MediaDBHelper.GROUP_COLUMN_ASSOCIATE_TABLE_ONLINE_NAME, buildOnlineMediaTableName);
                contentValues.put(MediaDBHelper.GROUP_COLUMN_ADDED_TIMESTAMP, Long.valueOf(System.currentTimeMillis()));
                sQLiteDatabase2.insert("group_", null, contentValues);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cursor queryArtistGroup(String str) {
        return tryConnectDatabase().rawQuery(Builder.buildArtistGroupSelection(str), null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cursor queryFolderGroup(String str) {
        return tryConnectDatabase().rawQuery(Builder.buildFolderGroupSelection(str), null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cursor queryGenreGroup(String str) {
        return tryConnectDatabase().rawQuery(Builder.buildGenreGroupSelection(str), null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cursor queryAlbumGroup(String str) {
        return tryConnectDatabase().rawQuery(Builder.buildAlbumGroupSelection(str), null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cursor queryCustomLocalGroup() {
        return queryCustomGroup(Builder.buildCustomLocalGroupSelection());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cursor queryCustomOnlineGroup() {
        return queryCustomGroup(Builder.buildCustomOnlineGroupSelection());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cursor queryCustomMixGroup() {
        return queryCustomGroup(Builder.buildCustomMixGroupSelection());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cursor queryCustomAllGroup() {
        return queryCustomGroup(Builder.buildCustomAllGroupSelection());
    }

    Cursor queryCustomGroup(final String str) {
        return (Cursor) doWithTransaction(null, new TransactionCallback<Cursor>() { // from class: com.sds.android.ttpod.media.mediastore.MediaDBHelper.13
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.sds.android.ttpod.media.mediastore.MediaDBHelper.TransactionCallback
            public Cursor runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                Cursor rawQuery;
                Cursor rawQuery2 = sQLiteDatabase.rawQuery(str, null);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                if (rawQuery2 != null) {
                    while (rawQuery2.moveToNext()) {
                        arrayList.add(rawQuery2.getString(0));
                        String string = rawQuery2.getString(1);
                        arrayList2.add(string);
                        sQLiteDatabase.execSQL(Builder.buildDeleteUnUsedItemFromCustomGroupSelection(string, rawQuery2.getString(2), rawQuery2.getString(3)));
                    }
                    rawQuery2.close();
                    if (arrayList.size() <= 0 || (rawQuery = sQLiteDatabase.rawQuery(Builder.buildCustomGroupMapCountSelection(arrayList2), null)) == null) {
                        return null;
                    }
                    if (rawQuery.moveToNext()) {
                        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"name", MediaDBHelper.GROUP_COLUMN_MAP_TABLE_NAME, "COUNT"});
                        Iterator it = arrayList.iterator();
                        int i = 0;
                        while (it.hasNext()) {
                            matrixCursor.addRow(new Object[]{(String) it.next(), arrayList2.get(i), Integer.valueOf(rawQuery.getInt(i))});
                            i++;
                        }
                        rawQuery.close();
                        return matrixCursor;
                    }
                    rawQuery.close();
                    return null;
                }
                return null;
            }
        });
    }

    private <Result> Result doWithTransaction(SQLiteDatabase sQLiteDatabase, TransactionCallback<Result> transactionCallback) {
        Result result = null;
        if (sQLiteDatabase == null) {
            sQLiteDatabase = tryConnectDatabase();
        }
        DebugUtils.m8426a(sQLiteDatabase, "database");
        if (sQLiteDatabase != null) {
            sQLiteDatabase.beginTransaction();
            try {
                result = transactionCallback.runInTransaction(sQLiteDatabase);
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                sQLiteDatabase.endTransaction();
            }
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAssociateOnlineTableName(SQLiteDatabase sQLiteDatabase, String str) {
        String r0 = null;
        Cursor rawQuery = sQLiteDatabase.rawQuery(Builder.buildCustomGroupAssociateMediaTableNameSelection(str), null);
        if (rawQuery != null) {
            r0 = rawQuery.moveToNext() ? rawQuery.getString(1) : null;
            rawQuery.close();
        }
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Builder {
        private static final String COUNT_FUNCTION = "COUNT(*)";
        private static final String TABLE_GROUP = "group_";
        private static final String TABLE_MEDIA_LOCAL = "media_local";
        private static final String TABLE_MEDIA_ONLINE = "media_online_";

        Builder() {
        }

        static String embraceWithSingleQuotationMarks(String str) {
            return "'" + str.replace("'", "''") + "'";
        }

        static String buildOnlineMediaTableName() {
            return TABLE_MEDIA_ONLINE + (System.currentTimeMillis() & System.nanoTime());
        }

        static String buildArtistGroupSelection(String str) {
            return "SELECT artist," + embraceWithSingleQuotationMarks(MediaStorage.GROUP_ID_ARTIST_PREFIX) + " || artist," + COUNT_FUNCTION + " FROM " + TABLE_MEDIA_LOCAL + (str == null ? "" : " WHERE " + str) + " GROUP BY artist";
        }

        static String buildAlbumGroupSelection(String str) {
            return "SELECT album," + embraceWithSingleQuotationMarks(MediaStorage.GROUP_ID_ALBUM_PREFIX) + " || album," + COUNT_FUNCTION + " FROM " + TABLE_MEDIA_LOCAL + (str == null ? "" : " WHERE " + str) + " GROUP BY album";
        }

        static String buildFolderGroupSelection(String str) {
            return "SELECT folder," + embraceWithSingleQuotationMarks(MediaStorage.GROUP_ID_FOLDER_PREFIX) + " || folder," + COUNT_FUNCTION + " FROM " + TABLE_MEDIA_LOCAL + (str == null ? "" : " WHERE " + str) + " GROUP BY folder";
        }

        static String buildGenreGroupSelection(String str) {
            return "SELECT genre," + embraceWithSingleQuotationMarks(MediaStorage.GROUP_ID_GENRE_PREFIX) + " || genre," + COUNT_FUNCTION + " FROM " + TABLE_MEDIA_LOCAL + (str == null ? "" : " WHERE " + str) + " GROUP BY genre";
        }

        static String buildCustomLocalGroupSelection() {
            return "SELECT name,map_table_name,associate_table_local_name,associate_table_online_name FROM group_ WHERE associate_table_local_name NOT NULL AND associate_table_online_name IS NULL ORDER BY group_added_time DESC";
        }

        static String buildCustomOnlineGroupSelection() {
            return "SELECT name, map_table_name,associate_table_local_name,associate_table_online_name FROM group_ WHERE associate_table_online_name NOT NULL AND associate_table_local_name IS NULL ORDER BY group_added_time DESC";
        }

        static String buildCustomMixGroupSelection() {
            return "SELECT name, map_table_name,associate_table_local_name,associate_table_online_name FROM group_ WHERE associate_table_online_name NOT NULL AND associate_table_local_name NOT NULL ORDER BY group_added_time DESC";
        }

        static String buildCustomAllGroupSelection() {
            return "SELECT name, map_table_name,associate_table_local_name,associate_table_online_name FROM group_ ORDER BY group_added_time DESC";
        }

        static String buildCustomGroupAssociateMediaTableNameSelection(String str) {
            return "SELECT associate_table_local_name,associate_table_online_name,base_orderby FROM group_ WHERE map_table_name=" + embraceWithSingleQuotationMarks(str);
        }

        static String buildDeleteUnUsedItemFromCustomGroupSelection(String str, String str2, String str3) {
            String str4 = "";
            if (str2 != null && str3 != null) {
                str4 = "(SELECT " + str + "._id FROM " + str + " WHERE " + str + "._id NOT IN (SELECT " + str + "._id FROM " + str + "," + str2 + " WHERE " + str + "._id=" + str2 + "._id UNION  SELECT " + str + "._id FROM " + str + "," + str3 + " WHERE " + str + "._id=" + str3 + "._id))";
            } else if (str2 != null) {
                str4 = "(SELECT " + str + "._id FROM " + str + " WHERE " + str + "._id NOT IN (SELECT " + str + "._id FROM " + str + "," + str2 + " WHERE " + str + "._id=" + str2 + "._id))";
            } else if (str3 != null) {
                str4 = "(SELECT " + str + "._id FROM " + str + " WHERE " + str + "._id NOT IN (SELECT " + str + "._id FROM " + str + "," + str3 + " WHERE " + str + "._id=" + str3 + "._id))";
            }
            return "DELETE FROM " + str + " WHERE " + str + "._id IN " + str4;
        }

        static String buildCustomGroupMapCountSelection(List<String> list) {
            if (list.isEmpty()) {
                throw new IllegalArgumentException("groupMapNameList must not empty!");
            }
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ");
            for (String str : list) {
                sb.append("(").append("SELECT ").append(COUNT_FUNCTION).append(" FROM ").append(str).append(")").append(",");
            }
            return sb.substring(0, sb.length() - 1).toString();
        }

        static String buildAddCustomGroupSelection(String str, String str2) {
            return "SELECT sort_order,group_map_added_time FROM " + str + " WHERE _id=" + embraceWithSingleQuotationMarks(str2);
        }

        static String buildMediaItemSelectionFromMediaLocal(String str, String str2) {
            return "SELECT _id,song_id,local_data_source,folder,title,artist,album,genre,composer,mime_type,start_time,duration,track,year,grade,bit_rate,sample_rate,channels,comment,error_status,use_count,added_time,modified_time,last_play_time,(SELECT COUNT(*) FROM group_id_customfav_local WHERE media_local._id=group_id_customfav_local._id),extra FROM media_local" + (str != null ? " WHERE " + str : "") + (str2 != null ? " ORDER BY " + unionOrderBy(str2) : "");
        }

        static String buildMediaItemSelectionFromGroupMapSelection(String str, String str2, String str3, String str4, String str5, String str6) {
            String str7 = str6 == null ? "sort_order" : str6;
            String substring = str7.endsWith(" DESC") ? str7.substring(0, str7.length() - " DESC".length()) : str7;
            String buildMediaItemSelectionSelection = str2 != null ? buildMediaItemSelectionSelection(str, str2, "(SELECT COUNT(*) FROM group_id_customfav_local WHERE " + str2 + "._id=" + MediaStorage.GROUP_ID_FAV_LOCAL + "._id)", substring) : null;
            String buildMediaItemSelectionSelection2 = str3 != null ? buildMediaItemSelectionSelection(str, str3, buildOnlineFavGroupSelection(str3, str4), substring) : null;
            if (buildMediaItemSelectionSelection == null || buildMediaItemSelectionSelection2 != null) {
                if (buildMediaItemSelectionSelection == null && buildMediaItemSelectionSelection2 != null) {
                    buildMediaItemSelectionSelection = buildMediaItemSelectionSelection2;
                } else if (buildMediaItemSelectionSelection != null && buildMediaItemSelectionSelection2 != null) {
                    buildMediaItemSelectionSelection = buildMediaItemSelectionSelection + " UNION " + buildMediaItemSelectionSelection2;
                } else {
                    throw new IllegalArgumentException("invalid query");
                }
            }
            return "SELECT * FROM (" + buildMediaItemSelectionSelection + ")" + (str5 == null ? "" : " WHERE " + str5) + " ORDER BY " + unionOrderBy(str6);
        }

        public static String buildMediaItemSelectionSelection(String str, String str2, String str3, String str4) {
            DebugUtils.m8426a(str3, "favCountSelection");
            DebugUtils.m8426a(str2, "associateMediaTableName");
            DebugUtils.m8426a(str, "groupMapTableName");
            DebugUtils.m8426a(str4, "orderBy");
            return "SELECT " + str + "._id AS _id,song_id," + MediaDBHelper.MEDIA_COLUMN_LOCAL_DATA_SOURCE + ",folder,title,artist,album,genre,composer,mime_type," + MediaDBHelper.MEDIA_COLUMN_START_TIME + ",duration,track,year," + MediaDBHelper.MEDIA_COLUMN_GRADE + "," + MediaDBHelper.MEDIA_COLUMN_BIT_RATE + ",sample_rate,channels,comment," + MediaDBHelper.MEDIA_COLUMN_ERROR_STATUS + ",use_count,added_time," + MediaDBHelper.MEDIA_COLUMN_MODIFIED_TIME_STAMP + ",last_play_time," + str3 + "," + MediaDBHelper.MEDIA_COLUMN_EXTRA + ",group_map_added_time," + str4 + " FROM " + str2 + "," + str + " WHERE " + str2 + "._id=" + str + "._id";
        }

        static String buildOnlineFavGroupSelection(String str, String str2) {
            return str2 == null ? "'0'" : "(SELECT COUNT(*) FROM " + str2 + " WHERE " + str + "._id=" + str2 + "._id)";
        }

        static boolean isCustomGroupExisted(SQLiteDatabase sQLiteDatabase, String str) {
            boolean z = false;
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type='table' and name=" + embraceWithSingleQuotationMarks(str), null);
            if (rawQuery != null) {
                if (rawQuery.moveToNext() && rawQuery.getInt(0) > 0) {
                    z = true;
                }
                rawQuery.close();
            }
            return z;
        }

        static String unionOrderBy(String str) {
            if (StringUtils.equals(str, MediaStorage.MEDIA_ORDER_BY_ALBUM_DESC) || StringUtils.equals(str, "album_key")) {
                return str + ",track";
            }
            return str;
        }

        static String getOnlineFavTable(SQLiteDatabase sQLiteDatabase) {
            long j;
            try {
                j = EnvironmentUtils.UUIDConfig.m8486g();
            } catch (Exception e) {
                e.printStackTrace();
                j = 0;
            }
            if (j > 0) {
                String buildOnlineFavGroupID = MediaStorage.buildOnlineFavGroupID();
                if (isCustomGroupExisted(sQLiteDatabase, buildOnlineFavGroupID)) {
                    return buildOnlineFavGroupID;
                }
            }
            return null;
        }
    }
}
