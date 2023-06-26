package com.sds.android.sdk.lib.request;

import android.os.AsyncTask;
import java.lang.reflect.Method;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.sds.android.sdk.lib.request.a */
/* loaded from: classes.dex */
public class AsyncRequestTask {

    /* renamed from: a */
    private HashMap<Integer, AsyncTaskC0600a> f2407a = new HashMap<>();

    /* renamed from: a */
    public <R extends BaseResult> void m8562a(Request<R> request, RequestCallback<R> requestCallback, Object... objArr) {
        m8565a(request.hashCode(), request, requestCallback, objArr);
    }

    /* renamed from: a */
    public <R extends BaseResult> void m8565a(int i, Request<R> request, RequestCallback<R> requestCallback, Object... objArr) {
        R m8528i;
        try {
            this.f2407a.put(Integer.valueOf(i), m8564a(this.f2407a.get(Integer.valueOf(i)), new AsyncTaskC0600a<>(request, requestCallback), objArr));
        } catch (NoClassDefFoundError e) {
            if (requestCallback != null && (m8528i = request.m8528i()) != null) {
                m8528i.setCode(-3);
                m8528i.setMessage(e.toString());
                requestCallback.onRequestFailure(m8528i);
            }
        }
    }

    /* renamed from: a */
    private <R extends BaseResult> AsyncTaskC0600a<R> m8564a(AsyncTaskC0600a asyncTaskC0600a, AsyncTaskC0600a<R> asyncTaskC0600a2, Object... objArr) {
        if (asyncTaskC0600a != null) {
            asyncTaskC0600a.cancel(true);
        }
        asyncTaskC0600a2.execute(objArr);
        return asyncTaskC0600a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AsyncRequestTask.java */
    /* renamed from: com.sds.android.sdk.lib.request.a$a */
    /* loaded from: classes.dex */
    public class AsyncTaskC0600a<R extends BaseResult> extends AsyncTask<Object, Object, R> {

        /* renamed from: b */
        private final Request<R> f2409b;

        /* renamed from: c */
        private final RequestCallback<R> f2410c;

       //  JADX WARN: Multi-variable type inference failed
        @Override // android.os.AsyncTask
        protected   void onPostExecute(R obj) {
            m8561a(obj);
        }

        AsyncTaskC0600a(Request<R> request, RequestCallback<R> requestCallback) {
            this.f2409b = request;
            this.f2410c = requestCallback;
        }

        /* renamed from: a */
        protected void m8561a(R r) {
            AsyncRequestTask.this.f2407a.remove(Integer.valueOf(this.f2409b.hashCode()));
            if (this.f2410c != null) {
                if (r != null && r.getCode() == 1) {
                    Class<?> m8534d = this.f2409b.m8534d();
                    if (m8534d != null) {
                        m8560a(m8534d, r);
                    }
                    this.f2410c.onRequestSuccess(r);
                    return;
                }
                if (r == null) {
                    r = this.f2409b.m8528i();
                    if (r != null) {
                        r.setCode(0);
                        r.setMessage("error");
                    } else {
                        return;
                    }
                }
                m8558b(r);
                this.f2410c.onRequestFailure(r);
            }
        }

        /* renamed from: b */
        private void m8558b(R r) {
            if (r.getCode() == 30305 || r.getCode() == 30301) {
                try {
                    Class<?> cls = Class.forName("com.sds.android.ttpod.activities.main.MainActivity");
                    cls.getMethod("handleAccessTokenInvalid", new Class[0]).invoke(cls, new Object[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /* renamed from: a */
        private void m8560a(Class<?> cls, R r) {
            Method[] declaredMethods;
            try {
                for (Method method : cls.getDeclaredMethods()) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length == 1 && parameterTypes[0] == BaseResult.class) {
                        method.setAccessible(true);
                        method.invoke(null, r);
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public R doInBackground(Object... objArr) {
            if (objArr == null || objArr.length <= 0) {
                return this.f2409b.execute();
            }
            return null;
        }
    }
}
