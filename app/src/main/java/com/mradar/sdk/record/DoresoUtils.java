package com.mradar.sdk.record;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.core.app.ActivityCompat;

import com.sds.android.cloudapi.ttpod.data.FeedbackItem;


import java.util.HashMap;
import java.util.Map;

/* renamed from: com.mradar.sdk.record.c */
/* loaded from: classes.dex */
public class DoresoUtils {
    /* renamed from: a */
    public static Map<String, String> m8993a(Context context) {
        HashMap hashMap = new HashMap();
        switch (m8988c(context)) {
            case 0:
                hashMap.put("compress_quality", "10");
                hashMap.put("network_type", "wifi");
                break;
            case 1:
                hashMap.put("compress_quality", "8");
                hashMap.put("network_type", "2g");
                break;
            case 2:
                hashMap.put("compress_quality", "8");
                hashMap.put("network_type", "3g");
                break;
            default:
                hashMap.put("compress_quality", "8");
                break;
        }
        hashMap.put("deviceId", m8984g(context));
        hashMap.put("device_model", m8995a());
        hashMap.put("device_os", m8990b());
        hashMap.put("pre_recorder", MRadarSdk.IsAdvance ? "1" : FeedbackItem.STATUS_WAITING);
        hashMap.put("app_version", m8985f(context));
        if (MRadarSdk.IsLocation) {
            hashMap.put("coordinate", m8987d(context));
        }
        return hashMap;
    }

    /* renamed from: b */
    public static boolean m8989b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    /* renamed from: c */
    protected static int m8988c(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.getType() != 1) {
            return (activeNetworkInfo.getSubtype() == 1 || activeNetworkInfo.getSubtype() == 4 || activeNetworkInfo.getSubtype() == 2) ? 1 : 2;
        }
        return 0;
    }

    /* renamed from: f */
    private static String m8985f(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: g */
    private static String m8984g(Context context) {
        return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    /* renamed from: a */
    private static String m8995a() {
        return Build.MODEL.replaceAll(" ", "_");
    }

    /* renamed from: b */
    private static String m8990b() {
        return Build.VERSION.RELEASE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static double m8991a(byte[] bArr, int i) {
        int i2 = 0;
        float f = 0.0f;
        int i3 = i >> 3;
        float f2 = 0.0f;
        for (int i4 = 0; i4 < i3; i4++) {
            short s = (short) (((short) (bArr[(i4 << 3) + 1] << 8)) | (bArr[i4 << 3] & 255));
            f2 += i2 * i2;
            f += ((s >> 15) ^ s) - (s >> 15);
        }
        float f3 = f / i3;
        return Math.min(Math.log10(((f2 / i3) - (f3 * f3)) + 1.0f), 8.0d) / 8.0d;
    }

    /* renamed from: d */
    public static String m8987d(Context context) {
        Location lastKnownLocation;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String bestProvider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return  "(-1,-1)";
        }
        if (bestProvider != null && (lastKnownLocation = locationManager.getLastKnownLocation(bestProvider)) != null) {
            double latitude = lastKnownLocation.getLatitude();
            double longitude = lastKnownLocation.getLongitude();
            return "(" + m8994a(latitude, 2) + "," + m8994a(longitude, 2) + ")";
        }
        return "(-1,-1)";
    }

    /* renamed from: a */
    public static double m8994a(double d, int i) {
        if (i >= 0) {
            int i2 = 1;
            for (int i3 = 0; i3 < i; i3++) {
                i2 *= 10;
            }
            return Math.round(i2 * d) / i2;
        }
        return d;
    }

    /* renamed from: e */
    public static String m8986e(Context context) {
        return "http://" + context.getSharedPreferences("ip", 0).getString("default", MRadarSdk.ip);
    }

    /* renamed from: a */
    public static boolean m8992a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("ip", 0).edit();
        edit.putString("default", str);
        MRadarSdk.IsLocation = true;

        return edit.commit();
    }
}