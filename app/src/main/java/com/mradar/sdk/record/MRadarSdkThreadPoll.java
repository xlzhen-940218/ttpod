package com.mradar.sdk.record;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class MRadarSdkThreadPoll {
    private static MRadarSdkThreadPoll instance;
    private ExecutorService mPool = Executors.newSingleThreadExecutor();

    private MRadarSdkThreadPoll() {
    }

    public static MRadarSdkThreadPoll getInstance() {
        if (instance == null) {
            synchronized (MRadarSdkThreadPoll.class) {
                instance = new MRadarSdkThreadPoll();
            }
        }
        return instance;
    }

    public ExecutorService getThreadPool() {
        return this.mPool;
    }
}
