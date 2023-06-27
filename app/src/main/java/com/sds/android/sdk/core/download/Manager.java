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
    private List<ThreadPool> f2314b = new ArrayList();

    /* renamed from: a */
    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    /* renamed from: c */
    private ThreadPool m8738c(String str) {
        for (int size = this.f2314b.size() - 1; size >= 0; size--) {
            if (this.f2314b.get(size).m8578a().equals(str)) {
                return this.f2314b.get(size);
            }
        }
        return null;
    }

    /* renamed from: a */
    public void m8742a(String str, int i) {
        if (StringUtils.isEmpty(str)) {
            throw new IllegalStateException(str + " is empty!");
        }
        if (m8738c(str) != null) {
            throw new IllegalStateException(str + " already existed!!");
        }
        this.f2314b.add(new ThreadPool(str, i, 15L));
    }

    /* renamed from: a */
    public boolean m8743a(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new IllegalStateException(str + " is empty!");
        }
        return m8738c(str) != null;
    }

    /* renamed from: b */
    public void m8739b(String str) {
        this.f2314b.remove(m8738c(str));
    }

    /* renamed from: a */
    public void start(String str, TaskInfo taskInfo, Task.TaskCallback abstractC0578a) {
        ThreadPool threadPool = m8738c(str);
        if (threadPool == null) {
            throw new IllegalStateException(str + " not exist!");
        }
        Task task = new Task(taskInfo, abstractC0578a);
        taskInfo.setAttachTask(task);
        threadPool.m8576a((Runnable) task);
    }

    /* renamed from: a */
    public void m8741a(String str, TaskInfo taskInfo) {
        ThreadPool m8738c = m8738c(str);
        if (m8738c == null) {
            throw new IllegalStateException(str + " not exist!");
        }
        if (taskInfo.getAttachTask() != null) {
            taskInfo.getAttachTask().m8728b();
            m8738c.m8574b(taskInfo.getAttachTask());
            return;
        }
        taskInfo.setState(3);
    }
}
