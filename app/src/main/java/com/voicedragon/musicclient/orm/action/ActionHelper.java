package com.voicedragon.musicclient.orm.action;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class ActionHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "action";
    private static final int DATABASE_VERSION = 1;
    private Dao<OrmAction, Integer> mDaoActionInfo;
    private static final AtomicInteger usageCounter = new AtomicInteger(0);
    private static ActionHelper helper = null;

    public static synchronized ActionHelper getHelper(Context context) {
        ActionHelper actionHelper;
        synchronized (ActionHelper.class) {
            if (helper == null) {
                helper = new ActionHelper(context);
            }
            usageCounter.incrementAndGet();
            actionHelper = helper;
        }
        return actionHelper;
    }

    private ActionHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, OrmAction.class);
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
            this.mDaoActionInfo = null;
            helper = null;
        }
    }

    public Dao<OrmAction, Integer> getActionDao() {
        if (this.mDaoActionInfo == null) {
            try {
                this.mDaoActionInfo = getDao(OrmAction.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.mDaoActionInfo;
    }
}
