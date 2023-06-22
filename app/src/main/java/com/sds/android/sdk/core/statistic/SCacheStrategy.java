package com.sds.android.sdk.core.statistic;

import android.os.Handler;
import com.sds.android.sdk.core.statistic.SUtils;
import com.sds.android.sdk.lib.util.LogUtils;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes.dex */
public class SCacheStrategy implements IStrategy {
    private static final int MAX_CACHE_EVENTS = 20;
    private static final int MAX_REMAIN_FILE = 100;
    private static final long MAX_TIME = 45000;
    private static String mCacheFilePath;
    private static long mDeep;
    private static String mDeepFilePath;
    private static LinkedList<String> mFilePathList = new LinkedList<>();
    private static Handler mHandler;
    private static HashMap<String, Object> mMap;
    private static String mURL;
    private LinkedList<SEvent> mCacheEventList = new LinkedList<>();
    private int mCurrentWriteCount;
    private String mCurrentWriteFilePath;
    private long mStartTime;

    static /* synthetic */ long access$308() {
        long j = mDeep;
        mDeep = 1 + j;
        return j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SCacheStrategy(Handler handler, String str) {
        mHandler = handler;
        mCacheFilePath = str;
        mDeepFilePath = mCacheFilePath + File.separator + SEvent.FIELD_DEEP;
    }

    @Override // com.sds.android.sdk.core.statistic.IStrategy
    public void onCreate() {
        sendLoadFileEventMessage();
        this.mCurrentWriteFilePath = SUtils.buildCurrentWirteFilePath(mCacheFilePath, SManager.PREFIX_STATISTIC_FILE_NAME);
    }

    @Override // com.sds.android.sdk.core.statistic.IStrategy
    public void setURL(String str) {
        mURL = str;
    }

    @Override // com.sds.android.sdk.core.statistic.IStrategy
    public void setGeneralParameters(Map map) {
        mMap = (HashMap) map;
    }

    @Override // com.sds.android.sdk.core.statistic.IStrategy
    public LinkedList<SEvent> getLastCacheEventList() {
        return new LinkedList<>(this.mCacheEventList);
    }

    @Override // com.sds.android.sdk.core.statistic.IStrategy
    public void onAddEvent(SEvent sEvent) {
        long timestamp = sEvent.timestamp();
        this.mCurrentWriteCount++;
        if (this.mStartTime == 0) {
            this.mStartTime = timestamp;
        }
        this.mCacheEventList.offer(sEvent);
        sendWriteEventMessage(this.mCurrentWriteFilePath, sEvent);
        if (20 <= this.mCurrentWriteCount || MAX_TIME <= timestamp - this.mStartTime) {
            this.mCurrentWriteCount = 0;
            sendPostListEventMessage(new LinkedList(this.mCacheEventList), this.mCurrentWriteFilePath);
            this.mCacheEventList.clear();
            this.mCurrentWriteFilePath = SUtils.buildCurrentWirteFilePath(mCacheFilePath, SManager.PREFIX_STATISTIC_FILE_NAME);
        }
        this.mStartTime = System.currentTimeMillis();
    }

    @Override // com.sds.android.sdk.core.statistic.IStrategy
    public void onDestroy() {
    }

    private static void sendWriteEventMessage(String str, SEvent sEvent) {
        mHandler.post(new WriteEventRunnable(str, sEvent));
    }

    private static void sendPostListEventMessage(LinkedList<SEvent> linkedList, String str) {
        mHandler.post(new PostListEventRunnable(linkedList, str));
    }

    private static void sendPostFileMessage(String str) {
        mHandler.post(new PostFileRunnable(str));
    }

    private static void sendLoadFileEventMessage() {
        mHandler.post(new LoadFileEventRunnable());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class PostListEventRunnable implements Runnable {
        private String mFilePath;
        private LinkedList<SEvent> mList;

        PostListEventRunnable(LinkedList<SEvent> linkedList, String str) {
            this.mList = linkedList;
            this.mFilePath = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            SCacheStrategy.handleResult(SUtils.postListEvent(this.mList, SCacheStrategy.mMap, SCacheStrategy.mURL), this.mFilePath);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class PostFileRunnable implements Runnable {
        private String mFilePath;

        PostFileRunnable(String str) {
            this.mFilePath = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            SCacheStrategy.handleResult(SUtils.postFileEvent(SCacheStrategy.mMap, SCacheStrategy.mURL, this.mFilePath), this.mFilePath);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleResult(SUtils.PostResult postResult, String str) {
        if (SUtils.PostResult.POST_SUCCESSED == postResult) {
            handlePostSuccessed(str);
        } else if (SUtils.PostResult.POST_FAILED == postResult) {
            handlePostFailed(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class WriteEventRunnable implements Runnable {
        private String mFileName;
        private SEvent mSEvent;

        WriteEventRunnable(String str, SEvent sEvent) {
            this.mFileName = str;
            this.mSEvent = sEvent;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SCacheStrategy.mDeep >= Long.MAX_VALUE) {
                long unused = SCacheStrategy.mDeep = 0L;
            } else {
                SCacheStrategy.access$308();
            }
            this.mSEvent.append(SEvent.FIELD_DEEP, Long.valueOf(SCacheStrategy.mDeep));
            LogUtils.m8381c("SCacheStrategy", "WriteEventRunnable: " + this.mSEvent);
            SUtils.writeEventToFile(this.mFileName, this.mSEvent);
            SUtils.writeDeep(SCacheStrategy.mDeepFilePath, SCacheStrategy.mDeep);
        }
    }

    private static void handlePostFailed(String str) {
        mFilePathList.add(str);
        if (mFilePathList.size() > 100) {
            mFilePathList.removeFirst();
        }
    }

    private static void handlePostSuccessed(String str) {
        mFilePathList.remove(str);
        SUtils.deleteFile(str);
        if (mFilePathList.size() > 0) {
            sendPostFileMessage(mFilePathList.remove());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class LoadFileEventRunnable implements Runnable {
        LoadFileEventRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            long initEventFiles = SUtils.initEventFiles(SCacheStrategy.mFilePathList, SCacheStrategy.mCacheFilePath, SManager.PREFIX_STATISTIC_FILE_NAME, 100);
            long unused = SCacheStrategy.mDeep = SUtils.readDeep(SCacheStrategy.mDeepFilePath);
            SSystemEvent sSystemEvent = new SSystemEvent("SYS_LOG", "loss");
            sSystemEvent.setPostStrategy(SPostStrategy.IMMEDIATELAY_WRITE_FILE);
            sSystemEvent.append("totals", Long.valueOf(initEventFiles));
            sSystemEvent.post();
            LogUtils.m8381c("Loss", "loss: " + initEventFiles);
        }
    }
}
