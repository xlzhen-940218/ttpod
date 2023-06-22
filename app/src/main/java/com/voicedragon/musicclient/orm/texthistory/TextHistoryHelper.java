package com.voicedragon.musicclient.orm.texthistory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class TextHistoryHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "voicedragon_textsearch";
    private static final int DATABASE_VERSION = 1;
    private Dao<OrmTextHistory, Long> mDaoTextHistory;
    private static final AtomicInteger usageCounter = new AtomicInteger(0);
    private static TextHistoryHelper helper = null;

    private TextHistoryHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static synchronized TextHistoryHelper getHelper(Context context) {
        TextHistoryHelper textHistoryHelper;
        synchronized (TextHistoryHelper.class) {
            if (helper == null) {
                helper = new TextHistoryHelper(context);
            }
            usageCounter.incrementAndGet();
            textHistoryHelper = helper;
        }
        return textHistoryHelper;
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(TextHistoryHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, OrmTextHistory.class);
        } catch (SQLException e) {
            Log.e(TextHistoryHelper.class.getName(), "Can't create database", e);
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
            this.mDaoTextHistory = null;
            helper = null;
        }
    }

    public Dao<OrmTextHistory, Long> getDaoTextHistory() {
        if (this.mDaoTextHistory == null) {
            try {
                this.mDaoTextHistory = getDao(OrmTextHistory.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.mDaoTextHistory;
    }
}
