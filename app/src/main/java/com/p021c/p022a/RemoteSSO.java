package com.p021c.p022a;
import com.google.gson.annotations.SerializedName;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.c.a.a */
/* loaded from: classes.dex */
public interface RemoteSSO extends IInterface {
    /* renamed from: a */
    String mo9779a() throws RemoteException;

    /* renamed from: b */
    String mo9778b() throws RemoteException;

    /* compiled from: RemoteSSO.java */
    /* renamed from: com.c.a.a$a */
    /* loaded from: classes.dex */
    public static abstract class AbstractBinderC0329a extends Binder implements RemoteSSO {
        /* renamed from: a */
        public static RemoteSSO m9780a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.sina.sso.RemoteSSO");
            if (queryLocalInterface != null && (queryLocalInterface instanceof RemoteSSO)) {
                return (RemoteSSO) queryLocalInterface;
            }
            return new C0330a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.sina.sso.RemoteSSO");
                    String a = mo9779a();
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case 2:
                    parcel.enforceInterface("com.sina.sso.RemoteSSO");
                    String b = mo9778b();
                    parcel2.writeNoException();
                    parcel2.writeString(b);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.sina.sso.RemoteSSO");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* compiled from: RemoteSSO.java */
        /* renamed from: com.c.a.a$a$a */
        /* loaded from: classes.dex */
        private static class C0330a implements RemoteSSO {

            /* renamed from: a */
            private IBinder f1382a;

            C0330a(IBinder iBinder) {
                this.f1382a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1382a;
            }

            @Override // com.p021c.p022a.RemoteSSO
            /* renamed from: a */
            public String mo9779a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sina.sso.RemoteSSO");
                    this.f1382a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p021c.p022a.RemoteSSO
            /* renamed from: b */
            public String mo9778b() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sina.sso.RemoteSSO");
                    this.f1382a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
