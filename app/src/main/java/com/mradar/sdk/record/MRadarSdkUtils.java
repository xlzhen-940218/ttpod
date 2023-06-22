package com.mradar.sdk.record;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.voicedragon.musicclient.orm.action.OrmAction;
import com.voicedragon.musicclient.util.MRadar;
import com.voicedragon.musicclient.util.MRadarUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class MRadarSdkUtils {
    public static Map<String, String> GetOptions(Context c) {
        Map<String, String> params = new HashMap<>();
        switch (getNetworkType(c)) {
            case 0:
                params.put(MRadarSdk.COMPRESS_QUALITY, MRadarSdk.SPEEX_WIFI);
                params.put(MRadarSdk.NET_WORK, "wifi");
                break;
            case 1:
                params.put(MRadarSdk.COMPRESS_QUALITY, "8");
                params.put(MRadarSdk.NET_WORK, "2g");
                break;
            case 2:
                params.put(MRadarSdk.COMPRESS_QUALITY, "8");
                params.put(MRadarSdk.NET_WORK, "3g");
                break;
            default:
                params.put(MRadarSdk.COMPRESS_QUALITY, "8");
                break;
        }
        params.put("deviceId", getUserid(c));
        params.put("device_model", getModel());
        params.put("device_os", getOS());
        params.put("pre_recorder", MRadarSdk.IsAdvance ? "1" : "0");
        params.put("app_version", getVersion(c));
        params.put("lang", MRadarUtil.getCountry(c));
        params.put(OrmAction.UID, MRadar.Login.UID);
        if (MRadarSdk.IsLocation) {
            params.put("coordinate", getAddress(c));
        }
        return params;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isNetworkEnable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        return nInfo != null && nInfo.isAvailable();
    }

    protected static int getNetworkType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        if (nInfo == null || nInfo.getType() == 1) {
            return 0;
        }
        return (nInfo.getSubtype() == 1 || nInfo.getSubtype() == 4 || nInfo.getSubtype() == 2) ? 1 : 2;
    }

    private static String getVersion(Context c) {
        PackageManager packageManager = c.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(c.getPackageName(), 0);
            String version = packageInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getUserid(Context c) {
        TelephonyManager tm = (TelephonyManager) c.getSystemService("phone");
        return tm.getDeviceId();
    }

    private static String getModel() {
        String mtype = Build.MODEL.replaceAll(" ", "_");
        return mtype;
    }

    private static String getOS() {
        String mtype = Build.VERSION.RELEASE;
        return mtype;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static double computeDb(byte[] buffer, int len) {
        int size = len >> 3;
        float fDB = 0.0f;
        float mu = 0.0f;
        for (int i = 0; i < size; i++) {
            short retVal = (short) ((buffer[i << 3] & 255) | ((short) (buffer[(i << 3) + 1] << 8)));
            int temp = ((retVal >> 15) ^ retVal) - (retVal >> 15);
            fDB += temp * temp;
            mu += temp;
        }
        float mu2 = mu / size;
        double db = Math.log10(((fDB / size) - (mu2 * mu2)) + 1.0f);
        return Math.min(db, 8.0d) / 8.0d;
    }

    public static String getAddress(Context mcontext) {
        Location location;
        LocationManager locationManager = (LocationManager) mcontext.getSystemService("location");
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(1);
        String provider = locationManager.getBestProvider(criteria, true);
        if (provider != null && (location = locationManager.getLastKnownLocation(provider)) != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            String address = "(" + roundChange(lat, 2) + "," + roundChange(lng, 2) + ")";
            return address;
        }
        return "(-1,-1)";
    }

    public static double roundChange(double a, int b) {
        if (b >= 0) {
            int k = 1;
            for (int i = 0; i < b; i++) {
                k *= 10;
            }
            return Math.round(k * a) / k;
        }
        return a;
    }
}
