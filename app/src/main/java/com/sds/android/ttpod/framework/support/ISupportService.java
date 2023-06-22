package com.sds.android.ttpod.framework.support;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.sds.android.sdk.core.statistic.StatisticEvent;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.List;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.framework.support.b */
/* loaded from: classes.dex */
public interface ISupportService extends IInterface {
    /* renamed from: a */
    List<DownloadTaskInfo> mo2385a(int[] iArr) throws RemoteException;

    /* renamed from: a */
    void mo2395a() throws RemoteException;

    /* renamed from: a */
    void mo2394a(int i) throws RemoteException;

    /* renamed from: a */
    void mo2393a(long j) throws RemoteException;

    /* renamed from: a */
    void mo2392a(StatisticEvent statisticEvent) throws RemoteException;

    /* renamed from: a */
    void mo2391a(DownloadTaskInfo downloadTaskInfo) throws RemoteException;

    /* renamed from: a */
    void mo2390a(String str) throws RemoteException;

    /* renamed from: a */
    void mo2389a(String str, int i, int i2, String str2, String str3, boolean z) throws RemoteException;

    /* renamed from: a */
    void mo2388a(String str, boolean z) throws RemoteException;

    /* renamed from: a */
    void mo2387a(Map map) throws RemoteException;

    /* renamed from: a */
    void mo2386a(boolean z) throws RemoteException;

    /* renamed from: a */
    boolean mo2384a(int[] iArr, int i) throws RemoteException;

    /* renamed from: b */
    int mo2383b() throws RemoteException;

    /* renamed from: b */
    DownloadTaskInfo mo2381b(DownloadTaskInfo downloadTaskInfo) throws RemoteException;

    /* renamed from: b */
    void mo2382b(long j) throws RemoteException;

    /* renamed from: b */
    void mo2380b(String str) throws RemoteException;

    /* renamed from: b */
    boolean mo2379b(int[] iArr, int i) throws RemoteException;

    /* renamed from: c */
    int mo2378c() throws RemoteException;

    /* renamed from: c */
    DownloadTaskInfo mo2377c(DownloadTaskInfo downloadTaskInfo) throws RemoteException;

    /* renamed from: d */
    float mo2376d() throws RemoteException;

    /* renamed from: d */
    int mo2375d(DownloadTaskInfo downloadTaskInfo) throws RemoteException;

    /* renamed from: e */
    int mo2374e() throws RemoteException;

    /* renamed from: f */
    MediaItem mo2373f() throws RemoteException;

    /* renamed from: g */
    void mo2372g() throws RemoteException;

    /* renamed from: h */
    void mo2371h() throws RemoteException;

    /* renamed from: i */
    String mo2370i() throws RemoteException;

    /* renamed from: j */
    String mo2369j() throws RemoteException;

    /* renamed from: k */
    long mo2368k() throws RemoteException;

    /* renamed from: l */
    void mo2367l() throws RemoteException;

    /* compiled from: ISupportService.java */
    /* renamed from: com.sds.android.ttpod.framework.support.b$a */
    /* loaded from: classes.dex */
    public static abstract class AbstractBinderC2061a extends Binder implements ISupportService {
        public AbstractBinderC2061a() {
            attachInterface(this, "com.sds.android.ttpod.framework.support.ISupportService");
        }

        /* renamed from: a */
        public static ISupportService m2509a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.sds.android.ttpod.framework.support.ISupportService");
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISupportService)) {
                return (ISupportService) queryLocalInterface;
            }
            return new C2062a(iBinder);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    mo2395a();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    int b = mo2383b();
                    parcel2.writeNoException();
                    parcel2.writeInt(b);
                    return true;
                case 3:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    int c = mo2378c();
                    parcel2.writeNoException();
                    parcel2.writeInt(c);
                    return true;
                case 4:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    mo2394a(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    float d = mo2376d();
                    parcel2.writeNoException();
                    parcel2.writeFloat(d);
                    return true;
                case 6:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    int e = mo2374e();
                    parcel2.writeNoException();
                    parcel2.writeInt(e);
                    return true;
                case 7:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    int readInt = parcel.readInt();
                    int[] iArr = readInt >= 0 ? new int[readInt] : null;
                    boolean a = mo2384a(iArr, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(a ? 1 : 0);
                    parcel2.writeIntArray(iArr);
                    return true;
                case 8:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    int readInt2 = parcel.readInt();
                    int[] iArr2 = readInt2 >= 0 ? new int[readInt2] : null;
                    boolean b2 = mo2379b(iArr2, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(b2 ? 1 : 0);
                    parcel2.writeIntArray(iArr2);
                    return true;
                case 9:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    MediaItem f = mo2373f();
                    parcel2.writeNoException();
                    if (f != null) {
                        parcel2.writeInt(1);
                        f.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 10:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    mo2372g();
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    mo2371h();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    mo2388a(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    String i3 = mo2370i();
                    parcel2.writeNoException();
                    parcel2.writeString(i3);
                    return true;
                case 14:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    String j = mo2369j();
                    parcel2.writeNoException();
                    parcel2.writeString(j);
                    return true;
                case 15:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    mo2392a(parcel.readInt() != 0 ? StatisticEvent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    mo2387a(parcel.readHashMap(getClass().getClassLoader()));
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    mo2386a(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    mo2391a(parcel.readInt() != 0 ? DownloadTaskInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    DownloadTaskInfo b3 = mo2381b(parcel.readInt() != 0 ? DownloadTaskInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (b3 != null) {
                        parcel2.writeInt(1);
                        b3.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 20:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    DownloadTaskInfo c2 = mo2377c(parcel.readInt() != 0 ? DownloadTaskInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (c2 != null) {
                        parcel2.writeInt(1);
                        c2.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 21:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    mo2390a(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    mo2380b(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    int d2 = mo2375d(parcel.readInt() != 0 ? DownloadTaskInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(d2);
                    return true;
                case 24:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    mo2389a(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    long k = mo2368k();
                    parcel2.writeNoException();
                    parcel2.writeLong(k);
                    return true;
                case 26:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    mo2393a(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    List<DownloadTaskInfo> a2 = mo2385a(parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(a2);
                    return true;
                case 28:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    mo2382b(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface("com.sds.android.ttpod.framework.support.ISupportService");
                    mo2367l();
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.sds.android.ttpod.framework.support.ISupportService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: ISupportService.java */
        /* renamed from: com.sds.android.ttpod.framework.support.b$a$a */
        /* loaded from: classes.dex */
        public static class C2062a implements ISupportService {

            /* renamed from: a */
            private IBinder f7132a;

            C2062a(IBinder iBinder) {
                this.f7132a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f7132a;
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: a */
            public void mo2395a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.f7132a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: b */
            public int mo2383b() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.f7132a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: c */
            public int mo2378c() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.f7132a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: a */
            public void mo2394a(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeInt(i);
                    this.f7132a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: d */
            public float mo2376d() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.f7132a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: e */
            public int mo2374e() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.f7132a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: a */
            public boolean mo2384a(int[] iArr, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    if (iArr == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(iArr.length);
                    }
                    obtain.writeInt(i);
                    this.f7132a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    boolean z = obtain2.readInt() != 0;
                    obtain2.readIntArray(iArr);
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: b */
            public boolean mo2379b(int[] iArr, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    if (iArr == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(iArr.length);
                    }
                    obtain.writeInt(i);
                    this.f7132a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    boolean z = obtain2.readInt() != 0;
                    obtain2.readIntArray(iArr);
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: f */
            public MediaItem mo2373f() throws RemoteException {
                MediaItem mediaItem;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.f7132a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        mediaItem = MediaItem.CREATOR.createFromParcel(obtain2);
                    } else {
                        mediaItem = null;
                    }
                    return mediaItem;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: g */
            public void mo2372g() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.f7132a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: h */
            public void mo2371h() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.f7132a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: a */
            public void mo2388a(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    this.f7132a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: i */
            public String mo2370i() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.f7132a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: j */
            public String mo2369j() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.f7132a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: a */
            public void mo2392a(StatisticEvent statisticEvent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    if (statisticEvent != null) {
                        obtain.writeInt(1);
                        statisticEvent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7132a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: a */
            public void mo2387a(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeMap(map);
                    this.f7132a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: a */
            public void mo2386a(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeInt(z ? 1 : 0);
                    this.f7132a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: a */
            public void mo2391a(DownloadTaskInfo downloadTaskInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    if (downloadTaskInfo != null) {
                        obtain.writeInt(1);
                        downloadTaskInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7132a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: b */
            public DownloadTaskInfo mo2381b(DownloadTaskInfo downloadTaskInfo) throws RemoteException {
                DownloadTaskInfo downloadTaskInfo2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    if (downloadTaskInfo != null) {
                        obtain.writeInt(1);
                        downloadTaskInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7132a.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        downloadTaskInfo2 = DownloadTaskInfo.CREATOR.createFromParcel(obtain2);
                    } else {
                        downloadTaskInfo2 = null;
                    }
                    return downloadTaskInfo2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: c */
            public DownloadTaskInfo mo2377c(DownloadTaskInfo downloadTaskInfo) throws RemoteException {
                DownloadTaskInfo downloadTaskInfo2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    if (downloadTaskInfo != null) {
                        obtain.writeInt(1);
                        downloadTaskInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7132a.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        downloadTaskInfo2 = DownloadTaskInfo.CREATOR.createFromParcel(obtain2);
                    } else {
                        downloadTaskInfo2 = null;
                    }
                    return downloadTaskInfo2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: a */
            public void mo2390a(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeString(str);
                    this.f7132a.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: b */
            public void mo2380b(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeString(str);
                    this.f7132a.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: d */
            public int mo2375d(DownloadTaskInfo downloadTaskInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    if (downloadTaskInfo != null) {
                        obtain.writeInt(1);
                        downloadTaskInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7132a.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: a */
            public void mo2389a(String str, int i, int i2, String str2, String str3, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(z ? 1 : 0);
                    this.f7132a.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: k */
            public long mo2368k() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.f7132a.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: a */
            public void mo2393a(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeLong(j);
                    this.f7132a.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: a */
            public List<DownloadTaskInfo> mo2385a(int[] iArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeIntArray(iArr);
                    this.f7132a.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DownloadTaskInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: b */
            public void mo2382b(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    obtain.writeLong(j);
                    this.f7132a.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.ISupportService
            /* renamed from: l */
            public void mo2367l() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sds.android.ttpod.framework.support.ISupportService");
                    this.f7132a.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
