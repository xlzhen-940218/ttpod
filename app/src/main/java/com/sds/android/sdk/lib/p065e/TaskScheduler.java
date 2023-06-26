package com.sds.android.sdk.lib.p065e;

import android.os.AsyncTask;
import android.os.Looper;
import com.sds.android.sdk.lib.util.LogUtils;

/* renamed from: com.sds.android.sdk.lib.e.a */
/* loaded from: classes.dex */
public class TaskScheduler {
    /* renamed from: a */
    public static void start(Runnable runnable) {
        start(runnable, null);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.sds.android.sdk.lib.e.a$1] */
    /* renamed from: a */
    public static void start(final Runnable runnable, final Runnable runnable2) {
        if (runnable == null) {
            if (runnable2 != null) {
                runnable2.run();
            }
        } else if (Looper.myLooper() != Looper.getMainLooper()) {
            runnable.run();
            if (runnable2 != null) {
                runnable2.run();
            }
        } else {
            new AsyncTask<Object, Object, Object>() { // from class: com.sds.android.sdk.lib.e.a.1
                @Override // android.os.AsyncTask
                protected Object doInBackground(Object... objArr) {
                    runnable.run();
                    return null;
                }

                @Override // android.os.AsyncTask
                protected void onPostExecute(Object obj) {
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                }

                @Override // android.os.AsyncTask
                protected void onCancelled() {
                    LogUtils.debug("TaskScheduler", "AsyncTask onCancelled class=%s", runnable.getClass().getName());
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                }
            }.execute(new Object[0]);
        }
    }

    /* renamed from: a */
    public static void m8582a(AbstractAsyncTaskC0595a abstractAsyncTaskC0595a) {
        abstractAsyncTaskC0595a.exec();
    }

    /* compiled from: TaskScheduler.java */
    /* renamed from: com.sds.android.sdk.lib.e.a$a */
    /* loaded from: classes.dex */
    public static abstract class AbstractAsyncTaskC0595a<Input, Output> extends AsyncTask<Input, Object, Output> {

        /* renamed from: a */
        private Input input;

        /* renamed from: a */
        protected abstract Output mo1981a(Input input);

        /* renamed from: b */
        protected abstract void postExecute(Output output);

        public AbstractAsyncTaskC0595a(Input input) {
            this.input = input;
        }

        @Override // android.os.AsyncTask
        protected final Output doInBackground(Input... inputArr) {
            return mo1981a(inputArr[0]);
        }

        @Override // android.os.AsyncTask
        protected final void onPostExecute(Output output) {
            postExecute(output);
        }

        /* renamed from: a */
        public void exec() {
            execute(this.input);
        }
    }
}
