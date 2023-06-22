package com.sds.android.sdk.core.statistic;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import com.sds.android.sdk.core.statistic.IService;
import com.sds.android.sdk.lib.util.LogUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes.dex */
public class SService extends Service {
    public static final String KEY_SEVENT = "sEvent";
    public static final String STATISTIC_DATA = "data";
    public static final String STATISTIC_PARAM = "param";
    private static long mIndex;
    private SBinder mSBinder;
    private SManager mSManager;
    private String mSessionID;

    /* loaded from: classes.dex */
    class SBinder extends IService.Stub {
        SBinder() {
        }

        @Override // com.sds.android.sdk.core.statistic.IService
        public void post(SEvent sEvent) throws RemoteException {
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (SService.this) {
                long unused = SService.mIndex = SService.this.increaseNumber(SService.mIndex);
                sEvent.append(SEvent.FIELD_SESSION_ID, SService.this.mSessionID);
                sEvent.append(SEvent.FIELD_INDEX, Long.valueOf(SService.mIndex));
                SService.this.mSManager.addEvent(sEvent);
            }
            LogUtils.m8381c("SService", "post durtion: " + (System.currentTimeMillis() - currentTimeMillis));
        }

        @Override // com.sds.android.sdk.core.statistic.IService
        public void setURL(String str) throws RemoteException {
            SService.this.mSManager.setURL(str);
        }

        @Override // com.sds.android.sdk.core.statistic.IService
        public void setGeneralParameters(Map map) throws RemoteException {
            SService.this.mSManager.setGeneralParameters(map);
        }

        @Override // com.sds.android.sdk.core.statistic.IService
        public List<SEvent> getLastCacheEventList() throws RemoteException {
            LinkedList<SEvent> lastCacheEventList;
            synchronized (SService.this) {
                lastCacheEventList = SService.this.mSManager.getLastCacheEventList();
            }
            return lastCacheEventList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long increaseNumber(long j) {
        if (j < Long.MAX_VALUE) {
            return 1 + j;
        }
        return 0L;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (this.mSBinder == null) {
            this.mSBinder = new SBinder();
        }
        return this.mSBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mSessionID = buildSessionID();
        this.mSManager = new SManager(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + getPackageName() + "/cache/");
        this.mSManager.onCreate();
    }

    private String buildSessionID() {
        return UUID.randomUUID().toString();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        mIndex = 0L;
        this.mSManager.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Bundle extras;
        if (intent != null && (extras = intent.getExtras()) != null) {
            this.mSManager.addEvent((SEvent) extras.getParcelable(KEY_SEVENT));
        }
        return super.onStartCommand(intent, i, i2);
    }
}
