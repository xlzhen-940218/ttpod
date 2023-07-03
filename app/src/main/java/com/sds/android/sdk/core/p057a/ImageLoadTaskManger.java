package com.sds.android.sdk.core.p057a;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.sds.android.sdk.core.a.d */
/* loaded from: classes.dex */
public final class ImageLoadTaskManger {

    /* renamed from: a */
    private boolean closeManager = false;

    /* renamed from: b */
    private ImageLoadThread imageLoadThreadUsePool = new ImageLoadThread(true);

    /* renamed from: c */
    private ImageLoadThread imageLoadThreadNoPool = new ImageLoadThread(false);

    public ImageLoadTaskManger() {
        this.imageLoadThreadNoPool.start();
        this.imageLoadThreadUsePool.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ImageLoadTaskManger.java */
    /* renamed from: com.sds.android.sdk.core.a.d$a */
    /* loaded from: classes.dex */
    public final class ImageLoadThread extends Thread {

        /* renamed from: b */
        private boolean useThreadPool;

        /* renamed from: c */
        private List<ImageLoadTask> imageLoadTasks;

        private ImageLoadThread(boolean useThreadPool) {
            this.imageLoadTasks = new ArrayList<>();
            this.useThreadPool = useThreadPool;
        }

        /* renamed from: a */
        void addThreadPool(ImageLoadTask imageLoadTask) {
            synchronized (this) {
                this.imageLoadTasks.add(imageLoadTask);
                int i = 80;
                if (this.useThreadPool) {
                    i = 40;
                }
                int size = this.imageLoadTasks.size();
                if (size > i) {
                    for (int i2 = size - i; i2 >= 0; i2--) {
                        this.imageLoadTasks.remove(0);
                    }
                }
                notify();
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            setPriority(1);
            while (!isInterrupted()) {
                ImageLoadTask imageLoadTask = null;
                synchronized (this) {
                    int size = this.imageLoadTasks.size();
                    if (size > 0) {
                        imageLoadTask = this.imageLoadTasks.get(size - 1);
                        this.imageLoadTasks.remove(size - 1);
                    }
                }
                if (imageLoadTask != null) {
                    if (!imageLoadTask.loadImageFormCache()) {
                        if (!this.useThreadPool) {
                            ImageLoadTaskManger.this.imageLoadThreadUsePool.addThreadPool(imageLoadTask);
                        } else {
                            imageLoadTask.downloadAndLoad();
                        }
                    }
                } else {
                    try {
                        synchronized (this) {
                            wait();
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }
            synchronized (this) {
                this.imageLoadTasks.clear();
            }
        }
    }

    /* renamed from: a */
    public boolean isCloseManager() {
        return this.closeManager;
    }

    /* renamed from: a */
    public void addThreadPool(ImageLoadTask imageLoadTask) {
        if (this.closeManager) {
            throw new IllegalStateException("Manager has been closed");
        }
        this.imageLoadThreadNoPool.addThreadPool(imageLoadTask);
    }
}
