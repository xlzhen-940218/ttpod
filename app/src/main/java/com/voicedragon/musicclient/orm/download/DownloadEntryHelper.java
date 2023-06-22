package com.voicedragon.musicclient.orm.download;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class DownloadEntryHelper extends OrmLiteSqliteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private Dao<OrmDownloadEntry, Long> mDaoPlayListEntry;
    private static final String DATABASE_NAME = String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()) + "/Doreso/music/doreso.db";
    private static final AtomicInteger usageCounter = new AtomicInteger(0);
    private static DownloadEntryHelper helper = null;

    private DownloadEntryHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static synchronized DownloadEntryHelper getHelper(Context context) {
        DownloadEntryHelper downloadEntryHelper;
        synchronized (DownloadEntryHelper.class) {
            if (helper == null) {
                helper = new DownloadEntryHelper(context);
            }
            usageCounter.incrementAndGet();
            downloadEntryHelper = helper;
        }
        return downloadEntryHelper;
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(DownloadEntryHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, OrmDownloadEntry.class);
        } catch (SQLException e) {
            Log.e(DownloadEntryHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper, android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public void close() {
        if (usageCounter.decrementAndGet() == 0) {
            super.close();
            this.mDaoPlayListEntry = null;
            helper = null;
        }
    }

    public Dao<OrmDownloadEntry, Long> getDaoDownloadEntry() {
        if (this.mDaoPlayListEntry == null) {
            try {
                this.mDaoPlayListEntry = getDao(OrmDownloadEntry.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.mDaoPlayListEntry;
    }

    public void saveDownloadEntry(OrmDownloadEntry entry) {
        try {
            getDaoDownloadEntry().create(entry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDownloadEntry(OrmDownloadEntry entry) {
        try {
            DeleteBuilder<OrmDownloadEntry, Long> db = helper.getDaoDownloadEntry().deleteBuilder();
            db.where().eq(OrmDownloadEntry.MD5, entry.getMd5());
            db.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDownloadEntryState(OrmDownloadEntry entry) {
        try {
            UpdateBuilder<OrmDownloadEntry, Long> ub = helper.getDaoDownloadEntry().updateBuilder();
            ub.updateColumnValue(OrmDownloadEntry.DOWNLOADED, Integer.valueOf(entry.getDownloadState())).where().eq(OrmDownloadEntry.MD5, entry.getMd5());
            ub.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
