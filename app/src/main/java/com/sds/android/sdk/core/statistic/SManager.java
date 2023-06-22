package com.sds.android.sdk.core.statistic;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.sds.android.sdk.lib.util.LogUtils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes.dex */
public class SManager {
    public static final String PREFIX_STATISTIC_FILE_NAME = "statistic_";
    private static HashMap<String, Object> mMap;
    private static String mURL;
    private static WorkerHander mWorker;
    private static HandlerThread mWorkerThread = new HandlerThread("Statistic");
    private String mCacheFileDir;
    private IStrategy mStrategy;

    static {
        mWorkerThread.start();
        mWorker = new WorkerHander(mWorkerThread.getLooper());
    }

    public SManager(String str) {
        this.mCacheFileDir = str;
        this.mStrategy = new SCacheStrategy(mWorker, str);
    }

    public void onCreate() {
        this.mStrategy.onCreate();
    }

    public void onDestroy() {
        this.mStrategy.onDestroy();
    }

    public void setURL(String str) {
        mURL = str;
        this.mStrategy.setURL(str);
    }

    public LinkedList<SEvent> getLastCacheEventList() {
        return this.mStrategy.getLastCacheEventList();
    }

    public void setGeneralParameters(Map map) {
        mMap = (HashMap) map;
        this.mStrategy.setGeneralParameters(map);
    }

    public void addEvent(SEvent sEvent) {
        LogUtils.m8381c("SManager", "addEvent event: " + sEvent);
        if (SPostStrategy.IMMEDIATELAY_POST == sEvent.getPostStrategy()) {
            addEventImmediatelyPost(sEvent);
        } else if (SPostStrategy.IMMEDIATELAY_WRITE_FILE == sEvent.getPostStrategy()) {
            addEventImmediatelyWriteFile(sEvent);
        } else {
            addEventNormal(sEvent);
        }
    }

    private void addEventNormal(SEvent sEvent) {
        this.mStrategy.onAddEvent(sEvent);
    }

    private void addEventImmediatelyPost(SEvent sEvent) {
        mWorker.sendMessage(mWorker.obtainMessage(11, sEvent));
    }

    private void addEventImmediatelyWriteFile(SEvent sEvent) {
        SUtils.writeEventToFile(SUtils.buildCurrentWirteFilePath(this.mCacheFileDir, PREFIX_STATISTIC_FILE_NAME), sEvent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class WorkerHander extends Handler {
        private static final int MESSAGE_POST_IMMEDIATELY_EVENT = 11;

        public WorkerHander(Looper looper) {
            super(looper);
        }

        private void postImmediatelyEvent(SEvent sEvent) {
            SUtils.postEvent(sEvent, SManager.mMap, SManager.mURL);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 11:
                    postImmediatelyEvent((SEvent) message.obj);
                    return;
                default:
                    return;
            }
        }
    }
}
