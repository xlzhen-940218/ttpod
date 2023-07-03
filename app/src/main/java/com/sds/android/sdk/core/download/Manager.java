package com.sds.android.sdk.core.download;

import com.sds.android.sdk.lib.p065e.ThreadPool;
import com.sds.android.sdk.lib.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.sdk.core.download.a */
/* loaded from: classes.dex */
public final class Manager {

    /* renamed from: a */
    private static Manager instance = null;

    /* renamed from: b */
    private List<ThreadPool> threadPools = new ArrayList();

    /* renamed from: a */
    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    /* renamed from: c */
    private ThreadPool getThreadPoolByName(String threadPoolName) {
        for (int size = this.threadPools.size() - 1; size >= 0; size--) {
            if (this.threadPools.get(size).getThreadPoolName().equals(threadPoolName)) {
                return this.threadPools.get(size);
            }
        }
        return null;
    }

    /* renamed from: a */
    public void addThreadPool(String threadPoolName, int maxThreadCount) {
        if (StringUtils.isEmpty(threadPoolName)) {
            throw new IllegalStateException(threadPoolName + " is empty!");
        }
        if (getThreadPoolByName(threadPoolName) != null) {
            throw new IllegalStateException(threadPoolName + " already existed!!");
        }
        this.threadPools.add(new ThreadPool(threadPoolName, maxThreadCount, 15L));
    }

    /* renamed from: a */
    public boolean threadPoolExist(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new IllegalStateException(str + " is empty!");
        }
        return getThreadPoolByName(str) != null;
    }

    /* renamed from: b */
    public void removeThreadPoolByName(String threadPoolName) {
        this.threadPools.remove(getThreadPoolByName(threadPoolName));
    }

    /* renamed from: a */
    public void start(String str, TaskInfo taskInfo, Task.TaskCallback abstractC0578a) {
        ThreadPool threadPool = getThreadPoolByName(str);
        if (threadPool == null) {
            throw new IllegalStateException(str + " not exist!");
        }
        Task task = new Task(taskInfo, abstractC0578a);
        taskInfo.setAttachTask(task);
        threadPool.execute((Runnable) task);
    }

    /* renamed from: a */
    public void closeTaskInfo(String str, TaskInfo taskInfo) {
        ThreadPool threadPool = getThreadPoolByName(str);
        if (threadPool == null) {
            throw new IllegalStateException(str + " not exist!");
        }
        if (taskInfo.getAttachTask() != null) {
            taskInfo.getAttachTask().close();
            threadPool.remove(taskInfo.getAttachTask());
            return;
        }
        taskInfo.setState(3);
    }
}
