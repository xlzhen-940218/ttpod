package com.mradar.sdk.record;

import android.content.Context;
import android.os.AsyncTask;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* renamed from: com.mradar.sdk.record.d */
/* loaded from: classes.dex */
public class GetIpAddress extends AsyncTask<Void, Void, Void> {

    /* renamed from: a */
    private Context f2215a;

    /* renamed from: b */
    private String f2216b = "";

    public GetIpAddress(Context context) {
        this.f2215a = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a */
    public Void doInBackground(Void... voidArr) {
        try {
            this.f2216b = InetAddress.getByName(MRadarSdk.host).getHostAddress();
            return null;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            this.f2216b = "";
            return null;
        } catch (Exception e2) {
            this.f2216b = "";
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a */
    public void onPostExecute(Void r3) {
        super.onPostExecute(r3);
        DoresoUtils.m8992a(this.f2215a, this.f2216b);
    }
}