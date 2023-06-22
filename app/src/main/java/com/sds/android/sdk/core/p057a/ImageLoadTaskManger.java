package com.sds.android.sdk.core.p057a;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.sds.android.sdk.core.a.d */
/* loaded from: classes.dex */
public final class ImageLoadTaskManger {

    /* renamed from: a */
    private boolean f2282a = false;

    /* renamed from: b */
    private C0569a f2283b = new C0569a(true);

    /* renamed from: c */
    private C0569a f2284c = new C0569a(false);

    public ImageLoadTaskManger() {
        this.f2284c.start();
        this.f2283b.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ImageLoadTaskManger.java */
    /* renamed from: com.sds.android.sdk.core.a.d$a */
    /* loaded from: classes.dex */
    public final class C0569a extends Thread {

        /* renamed from: b */
        private boolean f2286b;

        /* renamed from: c */
        private List<ImageLoadTask> f2287c;

        private C0569a(boolean z) {
            this.f2287c = new ArrayList();
            this.f2286b = z;
        }

        /* renamed from: a */
        void m8787a(ImageLoadTask imageLoadTask) {
            synchronized (this) {
                this.f2287c.add(imageLoadTask);
                int i = 80;
                if (this.f2286b) {
                    i = 40;
                }
                int size = this.f2287c.size();
                if (size > i) {
                    for (int i2 = size - i; i2 >= 0; i2--) {
                        this.f2287c.remove(0);
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
                    int size = this.f2287c.size();
                    if (size > 0) {
                        imageLoadTask = this.f2287c.get(size - 1);
                        this.f2287c.remove(size - 1);
                    }
                }
                if (imageLoadTask != null) {
                    if (!imageLoadTask.m8797a()) {
                        if (!this.f2286b) {
                            ImageLoadTaskManger.this.f2283b.m8787a(imageLoadTask);
                        } else {
                            imageLoadTask.m8794b();
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
                this.f2287c.clear();
            }
        }
    }

    /* renamed from: a */
    public boolean m8790a() {
        return this.f2282a;
    }

    /* renamed from: a */
    public void m8789a(ImageLoadTask imageLoadTask) {
        if (this.f2282a) {
            throw new IllegalStateException("Manager has been closed");
        }
        this.f2284c.m8787a(imageLoadTask);
    }
}
