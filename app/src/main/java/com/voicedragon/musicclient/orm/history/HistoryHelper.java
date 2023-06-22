package com.voicedragon.musicclient.orm.history;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class HistoryHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "voicedragon";
    private static final int DATABASE_VERSION = 2;
    private Dao<OrmHistory, Long> mDaoHistory;
    private Dao<OrmUnidentify, Long> mDaoUnidentify;
    private static final AtomicInteger usageCounter = new AtomicInteger(0);
    private static HistoryHelper helper = null;

    public static synchronized HistoryHelper getHelper(Context context) {
        HistoryHelper historyHelper;
        synchronized (HistoryHelper.class) {
            if (helper == null) {
                helper = new HistoryHelper(context);
            }
            usageCounter.incrementAndGet();
            historyHelper = helper;
        }
        return historyHelper;
    }

    private HistoryHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, OrmHistory.class);
            TableUtils.createTable(connectionSource, OrmUnidentify.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper, android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public void close() {
        if (usageCounter.decrementAndGet() == 0) {
            super.close();
            this.mDaoHistory = null;
            this.mDaoUnidentify = null;
            helper = null;
        }
    }

    public Dao<OrmHistory, Long> getHistoryDao() {
        if (this.mDaoHistory == null) {
            try {
                this.mDaoHistory = getDao(OrmHistory.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.mDaoHistory;
    }

    public Dao<OrmUnidentify, Long> getUnidentifyDao() {
        if (this.mDaoUnidentify == null) {
            try {
                this.mDaoUnidentify = getDao(OrmUnidentify.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.mDaoUnidentify;
    }
}
