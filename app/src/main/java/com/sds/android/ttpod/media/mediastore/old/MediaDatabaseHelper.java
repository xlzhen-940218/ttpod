package com.sds.android.ttpod.media.mediastore.old;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;


/* loaded from: classes.dex */
public final class MediaDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_2_X_VERSION = 24;
    public static final String DATABASE_TABLE_ALBUMS = "albums";
    public static final String DATABASE_TABLE_ARTISTS = "artists";
    public static final String DATABASE_TABLE_GENRES = "genres";
    public static final String DATABASE_TABLE_MEDIAS = "medias";
    public static final String DATABASE_TABLE_ONLINE_MEDIAS = "online_medias";
    public static final String DATABASE_TABLE_PLAYLISTS = "playlists";
    public static final String DATABASE_TABLE_PLAYLISTS_MAP = "playlists_map";
    private static final int DATABASE_VERSION_26 = 26;
    private static final int DATABASE_VERSION_27 = 27;
    private static final int DATABASE_VERSION_28 = 28;
    private static final int DATABASE_VERSION_29 = 29;
    private static final int DATABASE_VERSION_30 = 30;
    private static final int DATABASE_VERSION_31 = 31;
    private static final int DATABASE_VERSION_32 = 32;
    private static final int DATABASE_VERSION_33 = 33;
    private static final int DATABASE_VERSION_35 = 35;
    private static final int DATABASE_VERSION_37 = 37;
    private static final int DATABASE_VERSION_38 = 38;
    private static final int DATABASE_VERSION_41 = 41;
    private static final int DATABASE_VERSION_45 = 45;
    private static final int DATABASE_VERSION_46 = 46;
    private static final int DATABASE_VERSION_NEWEST = 46;
    public static final String DATABASE_VIEW_ALBUMS = "album_info";
    public static final String DATABASE_VIEW_ARTISTS = "artist_info";
    public static final String DATABASE_VIEW_FOLDERS = "folder_info";
    public static final String DATABASE_VIEW_GENRES = "genre_info";
    public static final String DATABASE_VIEW_MEDIAS = "media_info";
    public static final String DATABASE_VIEW_ONLINE_FAVORITE = "online_favorite_media_info";
    public static final String DATABASE_VIEW_PLAYLISTS = "playlist_info";
    public static final String DBTABASE_NAME = "MediaStoreOld_new";
    private static final String LOG_TAG = "MediaDataBaseHelper";

    public MediaDatabaseHelper(Context context) {
        super(context, DBTABASE_NAME, (SQLiteDatabase.CursorFactory) null, 46);
    }

    private void createMediasTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE medias (_id INTEGER  PRIMARY KEY NULL,_data TEXT UNIQUE NOT NULL,_folder TEXT  NULL,_display_name TEXT  NULL,_size INTEGER  NULL,_order INTEGER DEFAULT 0 NOT NULL,artist_id INTEGER  NULL,album_id INTEGER  NULL,genre_id INTEGER  NULL,song_id INTEGER NULL,artist_order INTEGER DEFAULT 0 NOT NULL,album_order INTEGER DEFAULT 0 NOT NULL,genre_order INTEGER DEFAULT 0 NOT NULL,folder_order INTEGER DEFAULT 0 NOT NULL,title TEXT NOT NULL,title_key TEXT NOT NULL,composer TEXT NULL,mime_type TEXT NULL,date_added INTEGER NULL,date_modified INTEGER NULL,duration INTEGER NULL,track INTEGER NULL,year INTEGER NULL,rating INTEGER NULL,audio_bitrate INTEGER NULL,sample_rate INTEGER NULL,channels INTEGER NULL,comment TEXT NULL,protect_status INTEGER NULL,bookmark INTEGER DEFAULT 0 NULL,use_count INTEGER DEFAULT 0 NULL);");
    }

    private void createArtistsTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE artists (_id INTEGER PRIMARY KEY,artist_key TEXT NOT NULL,artist TEXT NOT NULL); ");
    }

    private void createAlbumsTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE albums (_id INTEGER PRIMARY KEY NULL,album_key TEXT NOT NULL,album TEXT NOT NULL); ");
    }

    private void createGenresTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE genres (_id INTEGER NOT NULL PRIMARY KEY,genre_key TEXT NOT NULL,genre TEXT NOT NULL);");
    }

    private void createPlaylistsTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE playlists (_id INTEGER PRIMARY KEY NULL,_data TEXT UNIQUE NULL,_order INTEGER DEFAULT 0 NOT NULL,name TEXT  NOT NULL,date_added INTEGER  NULL,date_modified INTEGER  NULL);");
    }

    private void createPlaylistsMapTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE playlists_map (_id INTEGER PRIMARY KEY NULL,media_id INTEGER NOT NULL,playlist_id INTEGER NOT NULL,play_order INTEGER);");
    }

    private void createOnlineMediasTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE online_medias(_id INTEGER PRIMARY KEY NULL,_data TEXT,title TEXT,artist TEXT,song_id INTEGER,artist_id INTEGER,duration,date_added,date_modified);");
    }

    private void createMediaLinkTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE medialink(_id INTEGER PRIMARY KEY,lyric_path TEXT,lyric_searchtime INTEGER NOT NULL DEFAULT 0,image_artist_path TEXT,image_searchtime INTEGER NOT NULL DEFAULT 0);");
    }

    private void createOnlineFavoriteMediaView(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE VIEW online_favorite_media_info AS SELECT ifnull(media_info._id, -online_medias._id) AS _id,ifnull(media_info._data, online_medias._data) AS _data,_folder,_display_name,_size,_order,online_medias.artist_id AS artist_id,album_id,genre_id,online_medias.song_id AS song_id,online_medias.title AS title,title_key,online_medias.artist AS artist,artist_key,album,album_key,genre,genre_key,composer,mime_type,online_medias.date_added AS date_added,online_medias.date_modified AS date_modified,online_medias.duration AS duration,track,year,10 AS rating,audio_bitrate,sample_rate,channels,comment,protect_status,bookmark,use_count FROM online_medias LEFT OUTER JOIN media_info ON media_info.song_id=online_medias.song_id GROUP BY song_id");
    }

    private void createMediaView(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE VIEW media_info AS SELECT medias._id as _id,_data,_folder,_display_name,_size,_order,artist_id,album_id,genre_id,song_id,title,title_key,artist,artist_key,album,album_key,genre,genre_key,composer,mime_type,date_added,date_modified,duration,track,year,rating,audio_bitrate,sample_rate,channels,comment,protect_status,bookmark,use_count FROM medias LEFT OUTER JOIN artists ON medias.artist_id=artists._id LEFT OUTER JOIN albums ON medias.album_id=albums._id LEFT OUTER JOIN genres ON medias.genre_id=genres._id; ");
    }

    private void createArtistView(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE VIEW artist_info AS SELECT media_info.artist_id AS _id,artist,artist_key,COUNT(DISTINCT album) AS number_of_albums,COUNT(*) AS number_of_tracks FROM media_info GROUP BY artist_id;");
    }

    private void createAlbumView(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE VIEW album_info AS SELECT media_info.album_id AS _id,album,album_key,MIN(year) AS minyear,MAX(year) AS maxyear,artist,artist_id,artist_key,COUNT(*) AS number_of_tracks FROM media_info GROUP BY album_id;");
    }

    private void createGenreView(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE VIEW genre_info AS SELECT media_info.genre_id AS _id,genre,genre_key,COUNT(*) AS number_of_tracks FROM media_info GROUP BY genre_id;");
    }

    private void createFolderView(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE VIEW [folder_info] AS SELECT media_info._id AS _id, _folder, COUNT(*) AS number_of_tracks FROM media_info GROUP BY _folder;");
    }

    private void createPlaylistView(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE VIEW [playlist_info] AS SELECT * FROM playlists LEFT OUTER JOIN (SELECT playlist_id, COUNT(*) AS number_of_tracks FROM playlists_map GROUP BY playlist_id) temp ON playlists._id = playlist_id;");
    }

    private void createArtistAlbumMapView(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE VIEW artists_albums_map AS SELECT DISTINCT artist_id, album_id FROM medias");
    }

    private void createTitleKeyIndex(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE INDEX titlekey_index ON medias(title_key ASC); ");
    }

    private void createArtistKeyIndex(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE INDEX artistkey_index on artists(artist_key ASC);");
    }

    private void createAlbumKeyIndex(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE INDEX albumkey_index ON albums(album_key ASC);");
    }

    private void createGenreKeyIndex(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE INDEX genrekey_index ON genres(genre_key ASC);");
    }

    private void createFolderKeyIndex(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE INDEX folder_index ON medias(_folder ASC);");
    }

    private void createMediaDeleteTrigger(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TRIGGER media_delete INSTEAD OF DELETE ON media_info BEGIN DELETE FROM medias where _id=old._id; DELETE FROM playlists_map where media_id=old._id; END;");
    }

    private void createPlaylistCleanupTrigger(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TRIGGER playlist_cleanup DELETE ON playlists BEGIN DELETE FROM playlists_map WHERE playlist_id = old._id; END;");
    }

    private void createMediaCleanupTrigger(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TRIGGER media_cleanup DELETE ON medias BEGIN DELETE FROM playlists_map WHERE media_id = old._id; END;");
    }

    private void createMediaLinkDeleteTrigger(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TRIGGER tri_medias_delete AFTER DELETE ON medias FOR EACH ROW BEGIN DELETE FROM medialink WHERE _id=old._id;END;");
    }

    private void addColumn(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE " + str + " ADD " + str2 + " " + str3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        createDatabase(sQLiteDatabase);
    }

    private void createDatabase(SQLiteDatabase sQLiteDatabase) {
        createMediasTable(sQLiteDatabase);
        createArtistsTable(sQLiteDatabase);
        createAlbumsTable(sQLiteDatabase);
        createGenresTable(sQLiteDatabase);
        createPlaylistsTable(sQLiteDatabase);
        createPlaylistsMapTable(sQLiteDatabase);
        createOnlineMediasTable(sQLiteDatabase);
        createMediaLinkTable(sQLiteDatabase);
        createMediaView(sQLiteDatabase);
        createArtistView(sQLiteDatabase);
        createAlbumView(sQLiteDatabase);
        createGenreView(sQLiteDatabase);
        createFolderView(sQLiteDatabase);
        createPlaylistView(sQLiteDatabase);
        createArtistAlbumMapView(sQLiteDatabase);
        createOnlineFavoriteMediaView(sQLiteDatabase);
        createIndex(sQLiteDatabase);
        createTrigger(sQLiteDatabase);
    }

    private void createIndex(SQLiteDatabase sQLiteDatabase) {
        createTitleKeyIndex(sQLiteDatabase);
        createArtistKeyIndex(sQLiteDatabase);
        createAlbumKeyIndex(sQLiteDatabase);
        createGenreKeyIndex(sQLiteDatabase);
        createFolderKeyIndex(sQLiteDatabase);
    }

    private void createTrigger(SQLiteDatabase sQLiteDatabase) {
        createMediaCleanupTrigger(sQLiteDatabase);
        createMediaDeleteTrigger(sQLiteDatabase);
        createPlaylistCleanupTrigger(sQLiteDatabase);
        createMediaLinkDeleteTrigger(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        LogUtils.info(LOG_TAG, "databaseUpgrade:" + i + "->" + i2);
        if (i <= 24) {
            try {
                upgradeToVer25(sQLiteDatabase);
            } catch (Exception e) {
                LogUtils.error(LOG_TAG, "database upgrade to 25 error", e);
            }
        }
        if (i < 26) {
            try {
                upgradeToVer26(sQLiteDatabase);
            } catch (Exception e2) {
                LogUtils.error(LOG_TAG, "database upgrade to 26 error", e2);
            }
        }
        if (i < 27) {
            try {
                upgradeToVer27(sQLiteDatabase);
            } catch (Exception e3) {
                LogUtils.error(LOG_TAG, "database upgrade to 27 error", e3);
            }
        }
        if (i < 28) {
            try {
                upgradeToVer28(sQLiteDatabase);
            } catch (Exception e4) {
                LogUtils.error(LOG_TAG, "database upgrade to 28 error", e4);
            }
        }
        if (i < 29) {
            try {
                upgradeToVer29(sQLiteDatabase);
            } catch (Exception e5) {
                LogUtils.error(LOG_TAG, "database upgrade to 29 error", e5);
            }
        }
        if (i < 30) {
            try {
                upgradeToVer30(sQLiteDatabase);
            } catch (Exception e6) {
                LogUtils.error(LOG_TAG, "database upgrade to 30 error", e6);
            }
        }
        if (i < 31) {
            try {
                upgradeToVer31(sQLiteDatabase);
            } catch (Exception e7) {
                LogUtils.error(LOG_TAG, "database upgrade to 31 error", e7);
            }
        }
        if (i < 32) {
            try {
                upgradeToVer32(sQLiteDatabase);
            } catch (Exception e8) {
                LogUtils.error(LOG_TAG, "database upgrade to 32 error", e8);
            }
        }
        if (i < 33) {
            try {
                upgradeToVer33(sQLiteDatabase);
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }
        if (i < 35) {
            try {
                upgradeToVer35(sQLiteDatabase);
            } catch (Exception e10) {
                LogUtils.error(LOG_TAG, "database upgrade to 35 error", e10);
            }
        }
        if (i < DATABASE_VERSION_38) {
            try {
                upgradeToVer38(sQLiteDatabase);
            } catch (Exception e11) {
                LogUtils.error(LOG_TAG, "database upgrade to 38 error", e11);
            }
        }
        if (i < DATABASE_VERSION_41) {
            try {
                upgradeToVer41(sQLiteDatabase);
            } catch (Exception e12) {
                LogUtils.error(LOG_TAG, "database upgrade to 41 error", e12);
            }
        }
        if (i < DATABASE_VERSION_45) {
            try {
                upgradeToVer45(sQLiteDatabase);
            } catch (Exception e13) {
                LogUtils.error(LOG_TAG, "database upgrade to 45 error", e13);
            }
        }
        if (i < 46) {
            try {
                upgradeToVer46(sQLiteDatabase);
            } catch (Exception e14) {
                LogUtils.error(LOG_TAG, "database upgrade to 46 error", e14);
            }
        }
    }

    public void trim() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (readableDatabase != null) {
            readableDatabase.execSQL("vacuum;");
        }
    }

    private void upgradeToVer25(SQLiteDatabase sQLiteDatabase) {
        addColumn(sQLiteDatabase, DATABASE_TABLE_MEDIAS, MediaStoreOld.MediasColumns.COMMENT, "TEXT");
    }

    private void upgradeToVer26(SQLiteDatabase sQLiteDatabase) {
        createPlaylistView(sQLiteDatabase);
    }

    private void upgradeToVer27(SQLiteDatabase sQLiteDatabase) {
        processMetaDigitalKey(sQLiteDatabase, DATABASE_TABLE_MEDIAS, MediaStoreOld.MediasColumns.TITLE_DIGITAL_KEY, true);
        processMetaDigitalKey(sQLiteDatabase, "artists", MediaStoreOld.ArtistColumns.ARTIST_DIGITAL_KEY, true);
    }

    private void upgradeToVer28(SQLiteDatabase sQLiteDatabase) {
        addColumn(sQLiteDatabase, DATABASE_TABLE_MEDIAS, "song_id", "INTEGER");
    }

    private void upgradeToVer29(SQLiteDatabase sQLiteDatabase) {
        createMediaLinkTable(sQLiteDatabase);
    }

    private void upgradeToVer30(SQLiteDatabase sQLiteDatabase) {
    }

    private void upgradeToVer31(SQLiteDatabase sQLiteDatabase) {
    }

    private void upgradeToVer32(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("drop view artist_info");
        createArtistView(sQLiteDatabase);
        sQLiteDatabase.execSQL("drop view album_info");
        createAlbumView(sQLiteDatabase);
        sQLiteDatabase.execSQL("drop view genre_info");
        createGenreView(sQLiteDatabase);
    }

    private void upgradeToVer33(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("drop view media_info");
        createMediaView(sQLiteDatabase);
        createOnlineMediasTable(sQLiteDatabase);
        createOnlineFavoriteMediaView(sQLiteDatabase);
    }

    private void upgradeToVer35(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("drop view artist_info;");
        createArtistView(sQLiteDatabase);
        sQLiteDatabase.execSQL("delete from medialink;");
    }

    private void upgradeToVer38(SQLiteDatabase sQLiteDatabase) {
        addColumn(sQLiteDatabase, DATABASE_TABLE_ONLINE_MEDIAS, MediaStoreOld.MediasColumns.ARTIST_ID, "INTEGER");
    }

    private void upgradeToVer41(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS media_cleanup");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS media_delete");
        createMediaCleanupTrigger(sQLiteDatabase);
        createMediaDeleteTrigger(sQLiteDatabase);
    }

    private void upgradeToVer45(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS online_media_insert");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS online_media_delete");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS media_rating_update");
        addColumn(sQLiteDatabase, "playlists", "_order", "INTEGER DEFAULT 0 NOT NULL");
    }

    private void upgradeToVer46(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP VIEW favorites_media_info;");
        createOnlineFavoriteMediaView(sQLiteDatabase);
    }

    private void processMetaDigitalKey(SQLiteDatabase sQLiteDatabase, String str, String str2, boolean z) {
        if (z) {
            addColumn(sQLiteDatabase, str, str2, "TEXT");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x00f1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized boolean needUpgradeDatabase(Context context) {
        SQLiteDatabase sQLiteDatabase;
        SQLiteDatabase sQLiteDatabase2 = null;
        boolean z = false;
        Throwable th;
        SQLiteDatabase sQLiteDatabase3 = null;
        synchronized (this) {
            String path = context.getDatabasePath(DBTABASE_NAME).getPath();
            try {
                sQLiteDatabase2 = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            } catch (Throwable th1) {
                th = th1;
                sQLiteDatabase = null;
            }
            try {
                int version = sQLiteDatabase2.getVersion();
                z = version != 46;
                LogUtils.debug(LOG_TAG, String.format("lookdbversion: curDbVersion %d , support Version_Newest %d.", Integer.valueOf(version), 46));
                if (version > 46) {
                    try {
                        sQLiteDatabase2.close();
                        LogUtils.debug(LOG_TAG, String.format("curVersion %d > Version_Newest %d will delete db.", Integer.valueOf(version), 46));
                        FileUtils.exists(path);
                    } catch (Exception e) {
                        LogUtils.error(LOG_TAG, "TTMessage1:" + e.getMessage());
                    } catch (Throwable th2) {
                        th = th2;
                        if (sQLiteDatabase2 != null) {
                        }
                        throw th;
                    }
                } else {
                    sQLiteDatabase3 = sQLiteDatabase2;
                }
                if (sQLiteDatabase3 != null) {
                    try {
                        sQLiteDatabase3.close();
                    } catch (Exception e2) {
                        LogUtils.verbose(LOG_TAG, "TTMessage2:" + e2.getMessage());
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                sQLiteDatabase = sQLiteDatabase2;
                try {
                    LogUtils.debug(LOG_TAG, "TTMessage:" + th.getMessage());
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.close();
                            z = false;
                        } catch (Exception e3) {
                            LogUtils.verbose(LOG_TAG, "TTMessage2:" + e3.getMessage());
                            z = false;
                        }
                    } else {
                        z = false;
                    }
                    return z;
                } catch (Throwable th4) {
                    th = th4;
                    sQLiteDatabase2 = sQLiteDatabase;
                    if (sQLiteDatabase2 != null) {
                        try {
                            sQLiteDatabase2.close();
                        } catch (Exception e4) {
                            LogUtils.verbose(LOG_TAG, "TTMessage2:" + e4.getMessage());
                        }
                    }
                    //throw th;
                }
            }
        }
        return z;
    }
}