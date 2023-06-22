package com.voicedragon.musicclient.orm.playlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class PlaylistHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "songmenu";
    private static final int DATABASE_VERSION = 3;
    private Dao<OrmFavorite, Integer> mDaoFavourite;
    private Dao<OrmLocal, Integer> mDaoLocal;
    private Dao<OrmSongMenu, Integer> mDaoSongMenu;
    private Dao<OrmSongMenuSongs, Integer> mDaoSongMenuSongs;
    private static final AtomicInteger usageCounter = new AtomicInteger(0);
    private static PlaylistHelper helper = null;

    public static synchronized PlaylistHelper getHelper(Context context) {
        PlaylistHelper playlistHelper;
        synchronized (PlaylistHelper.class) {
            if (helper == null) {
                helper = new PlaylistHelper(context);
            }
            usageCounter.incrementAndGet();
            playlistHelper = helper;
        }
        return playlistHelper;
    }

    private PlaylistHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, OrmLocal.class);
            TableUtils.createTable(connectionSource, OrmFavorite.class);
            TableUtils.createTable(connectionSource, OrmSongMenu.class);
            TableUtils.createTable(connectionSource, OrmSongMenuSongs.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        if (newVersion >= 2) {
            try {
               // Logger.m2e("onupgrade", "onupgrade");
                TableUtils.createTable(connectionSource, OrmLocal.class);
                TableUtils.createTable(connectionSource, OrmFavorite.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Dao<OrmLocal, Integer> getDao_local() {
        if (this.mDaoLocal == null) {
            try {
                this.mDaoLocal = getDao(OrmLocal.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.mDaoLocal;
    }

    public Dao<OrmFavorite, Integer> getFavouriteDao() {
        if (this.mDaoFavourite == null) {
            try {
                this.mDaoFavourite = getDao(OrmFavorite.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.mDaoFavourite;
    }

    public Dao<OrmSongMenu, Integer> getSongMenuDao() {
        if (this.mDaoSongMenu == null) {
            try {
                this.mDaoSongMenu = getDao(OrmSongMenu.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.mDaoSongMenu;
    }

    public Dao<OrmSongMenuSongs, Integer> getSongMenuSongsDao() {
        if (this.mDaoSongMenuSongs == null) {
            try {
                this.mDaoSongMenuSongs = getDao(OrmSongMenuSongs.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.mDaoSongMenuSongs;
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper, android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public void close() {
        if (usageCounter.decrementAndGet() == 0) {
            super.close();
            this.mDaoLocal = null;
            this.mDaoFavourite = null;
            this.mDaoSongMenu = null;
            this.mDaoSongMenuSongs = null;
            helper = null;
        }
    }
}
