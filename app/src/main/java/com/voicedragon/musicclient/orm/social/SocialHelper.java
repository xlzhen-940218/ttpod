package com.voicedragon.musicclient.orm.social;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class SocialHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "social.db";
    private static final int DATABASE_VERSION = 1;
    private static HashMap<String, SocialHelper> mHelpers = new HashMap<>();
    private Dao<OrmComment, String> mDaoComment;
    private Dao<OrmMsg, String> mDaoMsg;
    private Dao<OrmMusic, String> mDaoMusic;
    private Dao<OrmTopicFav, String> mDaoTopicFav;
    private Dao<OrmTopicFollow, String> mDaoTopicFollow;
    private Dao<OrmTopicMe, String> mDaoTopicMe;
    private Dao<OrmTopicSquare, String> mDaoTopicSquare;
    private Dao<OrmUser, String> mDaoUser;
    private String mUID;
    private AtomicInteger usageCounter;

    /* JADX INFO: Access modifiers changed from: protected */
    public static synchronized SocialHelper getHelper(Context context, String uid) {
        SocialHelper helper;
        synchronized (SocialHelper.class) {
            helper = mHelpers.get(uid);
            if (helper == null) {
                helper = new SocialHelper(context, uid);
                mHelpers.put(uid, helper);
            }
            helper.usageCounter.incrementAndGet();
        }
        return helper;
    }

    protected SocialHelper(Context context, String uid) {
        super(context, String.valueOf(uid) + DATABASE_NAME, null, 1);
        this.usageCounter = new AtomicInteger(0);
        this.mUID = uid;
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(SocialHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, OrmTopicSquare.class);
            TableUtils.createTable(connectionSource, OrmTopicFollow.class);
            TableUtils.createTable(connectionSource, OrmTopicFav.class);
            TableUtils.createTable(connectionSource, OrmTopicMe.class);
            TableUtils.createTable(connectionSource, OrmMusic.class);
            TableUtils.createTable(connectionSource, OrmUser.class);
            TableUtils.createTable(connectionSource, OrmComment.class);
            TableUtils.createTable(connectionSource, OrmMsg.class);
        } catch (SQLException e) {
            Log.e(SocialHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper, android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public void close() {
        if (this.usageCounter.decrementAndGet() == 0) {
            mHelpers.remove(this.mUID);
            super.close();
            this.mDaoTopicSquare = null;
            this.mDaoTopicFollow = null;
            this.mDaoTopicFav = null;
            this.mDaoTopicMe = null;
            this.mDaoMusic = null;
            this.mDaoUser = null;
            this.mDaoComment = null;
            this.mDaoMsg = null;
        }
    }

    public Dao<OrmTopicSquare, String> getDaoTopicSquare() {
        if (this.mDaoTopicSquare == null) {
            try {
                this.mDaoTopicSquare = getDao(OrmTopicSquare.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.mDaoTopicSquare;
    }

    public Dao<OrmTopicFav, String> getDaoTopicFav() {
        if (this.mDaoTopicFav == null) {
            try {
                this.mDaoTopicFav = getDao(OrmTopicFav.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.mDaoTopicFav;
    }

    public Dao<OrmTopicFollow, String> getDaoTopicFollow() {
        if (this.mDaoTopicFollow == null) {
            try {
                this.mDaoTopicFollow = getDao(OrmTopicFollow.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.mDaoTopicFollow;
    }

    public Dao<OrmTopicMe, String> getDaoTopicMe() {
        if (this.mDaoTopicMe == null) {
            try {
                this.mDaoTopicMe = getDao(OrmTopicMe.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.mDaoTopicMe;
    }

    public Dao<OrmMusic, String> getDaoMusic() {
        if (this.mDaoMusic == null) {
            try {
                this.mDaoMusic = getDao(OrmMusic.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.mDaoMusic;
    }

    public Dao<OrmUser, String> getDaoUser() {
        if (this.mDaoUser == null) {
            try {
                this.mDaoUser = getDao(OrmUser.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.mDaoUser;
    }

    public Dao<OrmComment, String> getDaoComment() {
        if (this.mDaoComment == null) {
            try {
                this.mDaoComment = getDao(OrmComment.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.mDaoComment;
    }

    public Dao<OrmMsg, String> getDaoMsg() {
        if (this.mDaoMsg == null) {
            try {
                this.mDaoMsg = getDao(OrmMsg.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.mDaoMsg;
    }

    public void clearTable(Class<?> dataCalss) throws SQLException {
        TableUtils.clearTable(getConnectionSource(), dataCalss);
    }

    public void saveSquareTopicsInBatchTasks(final List<OrmTopicSquare> list) throws Exception {
        getDaoTopicSquare().callBatchTasks(new Callable<Object>() { // from class: com.voicedragon.musicclient.orm.social.SocialHelper.1
            @Override // java.util.concurrent.Callable
            public Object call() throws Exception {
                for (OrmTopicSquare ormTopic : list) {
                    SocialHelper.this.mDaoTopicSquare.createIfNotExists(ormTopic);
                }
                return null;
            }
        });
    }

    public void saveFollowTopicsInBatchTasks(final List<OrmTopicFollow> list) throws Exception {
        getDaoTopicFollow().callBatchTasks(new Callable<Object>() { // from class: com.voicedragon.musicclient.orm.social.SocialHelper.2
            @Override // java.util.concurrent.Callable
            public Object call() throws Exception {
                for (OrmTopicFollow ormTopic : list) {
                    SocialHelper.this.mDaoTopicFollow.create(ormTopic);
                }
                return null;
            }
        });
    }

    public void saveFavTopicsInBatchTasks(final List<OrmTopicFav> list) throws Exception {
        getDaoTopicFav().callBatchTasks(new Callable<Object>() { // from class: com.voicedragon.musicclient.orm.social.SocialHelper.3
            @Override // java.util.concurrent.Callable
            public Object call() throws Exception {
                for (OrmTopicFav ormTopic : list) {
                    SocialHelper.this.mDaoTopicFav.createIfNotExists(ormTopic);
                }
                return null;
            }
        });
    }

    public void saveMeTopicsInBatchTasks(final List<OrmTopicMe> list) throws Exception {
        getDaoTopicMe().callBatchTasks(new Callable<Object>() { // from class: com.voicedragon.musicclient.orm.social.SocialHelper.4
            @Override // java.util.concurrent.Callable
            public Object call() throws Exception {
                for (OrmTopicMe ormTopic : list) {
                    SocialHelper.this.mDaoTopicMe.create(ormTopic);
                }
                return null;
            }
        });
    }

    public void saveCommentsInBatchTasks(final List<OrmComment> list) throws Exception {
        getDaoComment().callBatchTasks(new Callable<Object>() { // from class: com.voicedragon.musicclient.orm.social.SocialHelper.5
            @Override // java.util.concurrent.Callable
            public Object call() throws Exception {
                for (OrmComment ormComment : list) {
                    SocialHelper.this.mDaoComment.create(ormComment);
                }
                return null;
            }
        });
    }

    public void saveMsgsInBatchTasks(final List<OrmMsg> list) throws Exception {
        getDaoMsg().callBatchTasks(new Callable<Object>() { // from class: com.voicedragon.musicclient.orm.social.SocialHelper.6
            @Override // java.util.concurrent.Callable
            public Object call() throws Exception {
                for (OrmMsg ormMsg : list) {
                    SocialHelper.this.mDaoMsg.createOrUpdate(ormMsg);
                }
                return null;
            }
        });
    }
}
