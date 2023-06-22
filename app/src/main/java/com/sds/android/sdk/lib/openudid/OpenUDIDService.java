package com.sds.android.sdk.lib.openudid;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
public class OpenUDIDService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new Binder() { // from class: com.sds.android.sdk.lib.openudid.OpenUDIDService.1
            @Override // android.os.Binder
            public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
                SharedPreferences sharedPreferences = OpenUDIDService.this.getSharedPreferences("openudid_prefs", 0);
                parcel2.writeInt(parcel.readInt());
                parcel2.writeString(sharedPreferences.getString("openudid", null));
                return true;
            }
        };
    }
}
