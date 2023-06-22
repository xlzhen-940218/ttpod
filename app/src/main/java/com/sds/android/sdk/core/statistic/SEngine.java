package com.sds.android.sdk.core.statistic;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.sds.android.sdk.core.statistic.IService;
import com.sds.android.sdk.lib.util.LogUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public final class SEngine {
    private static final int MAX_CACHE = 100;
    private static volatile IService mIService;
    private static HashMap<String, Object> mMap;
    private static SEngine mSingleton;
    private static String mURL;
    private static ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.sds.android.sdk.core.statistic.SEngine.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder != null) {
                IService unused = SEngine.mIService = IService.Stub.asInterface(iBinder);
                try {
                    SEngine.mIService.setURL(SEngine.mURL);
                    SEngine.mIService.setGeneralParameters(SEngine.mMap);
                    if (SEngine.mCacheEventList.size() > 0) {
                        synchronized (SEngine.class) {
                            Iterator it = SEngine.mCacheEventList.iterator();
                            while (it.hasNext()) {
                                SEngine.mIService.post((SEvent) it.next());
                            }
                            SEngine.mCacheEventList.clear();
                        }
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            IService unused = SEngine.mIService = null;
        }
    };
    private static LinkedList<SEvent> mCacheEventList = new LinkedList<>();

    public static SEngine instance() {
        if (mSingleton == null) {
            synchronized (SEngine.class) {
                if (mSingleton == null) {
                    mSingleton = new SEngine();
                }
            }
        }
        return mSingleton;
    }

    private SEngine() {
    }

    public static void bindToService(Context context) {
        if (mIService == null) {
            try {
                Intent intent = new Intent(context, SService.class);
                context.startService(intent);
                context.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void unbindFromService(Context context) {
        if (mIService != null) {
            try {
                context.unbindService(mServiceConnection);
                context.stopService(new Intent(context, SService.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void setURL(String str) {
        mURL = str;
    }

    public static void setGeneralParameter(HashMap<String, Object> hashMap) {
        mMap = hashMap;
    }

    public static List<SEvent> getLastCacheEventList() {
        if (mIService != null) {
            try {
                return mIService.getLastCacheEventList();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void post(SEvent sEvent) {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (SEngine.class) {
            if (mIService != null) {
                try {
                    beforePost(sEvent);
                    mIService.post(sEvent);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                handleUnbind(sEvent);
            }
        }
        LogUtils.m8381c("SEngine", "SEngine post time: " + (System.currentTimeMillis() - currentTimeMillis));
    }

    private static void beforePost(SEvent sEvent) throws RemoteException {
        if (!sEvent.getPageParameter() || Page.mCurrentPage == null || Page.mPageProperties == null) {
            return;
        }
        sEvent.append(Page.mPageProperties.getProPerties());
    }

    private static void handleUnbind(SEvent sEvent) {
        if (mCacheEventList.size() < 100) {
            mCacheEventList.add(sEvent);
        }
    }

    /* loaded from: classes.dex */
    public static class Page {
        private static Object mCurrentPage;
        private static SPageProperties mPageProperties = new SPageProperties();

        public static void create(Object obj) {
            LogUtils.m8381c("SEngine", "enterPage: " + obj);
            if (obj != null) {
                mCurrentPage = obj;
            }
        }

        public static void enter(Object obj) {
            LogUtils.m8381c("SEngine", "enterPage: " + obj);
            if (obj != null) {
                mCurrentPage = obj;
                if (mPageProperties != null) {
                    mPageProperties.getProPerties().clear();
                    mPageProperties.append(SEvent.FIELD_PAGE, obj);
                }
            }
        }

        public static void leave(Object obj) {
            LogUtils.m8381c("SEngine", "exitPage: " + obj);
            if (obj != null) {
                mCurrentPage = null;
                if (mPageProperties != null) {
                    mPageProperties.getProPerties().clear();
                }
            }
        }

        public static void updatePageProperties(SPageProperties sPageProperties) {
            LogUtils.m8381c("SEngine", "setPageProperties: " + mCurrentPage);
            if (sPageProperties != null && mCurrentPage != null && mPageProperties != null) {
                mPageProperties.getProPerties().putAll(sPageProperties.getProPerties());
            }
        }

        public static void updatePageProperties(String str, Object obj) {
            LogUtils.m8381c("SEngine", "clickListItem: " + mCurrentPage);
            if (mCurrentPage != null && str != null && mPageProperties != null) {
                mPageProperties.append(str, obj);
            }
        }
    }
}
