package com.sds.android.ttpod.ThirdParty.update;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.core.statistic.HttpClientProxy;
import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateData;
import com.sds.android.ttpod.activities.search.OnlineSearchEntryActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: classes.dex */
public class Baidu implements UpdateInterface {
    private static final String BAIDU_APP_URL = "http://dl.ops.baidu.com/appsearch_AndroidPhone_1005737a.apk";
    private static final String BAIDU_DOMAIN = "http://m.baidu.com/api";
    private static final int BAIDU_VERSION_CODE = 16782394;
    private static final int HTTP_SUCCESS = 200;
    private static final int NUMBER_0X0F = 15;
    private static final int NUMBER_0XF0 = 240;
    private static final long NUMBER_0XFFFFFFFFL = 4294967295L;
    private static final int NUMBER_1024 = 1024;
    private static final int NUMBER_16 = 16;
    private static final int NUMBER_32 = 32;
    private static final int NUMBER_8 = 8;
    private static final String TAG = "Baidu";
    private static final String TOKEN = "ttpod";
    private static Map<String, String> mUpdateInfoMap;
    private BroadcastReceiver mAppInstallReceiver = new BroadcastReceiver() { // from class: com.sds.android.ttpod.ThirdParty.update.Baidu.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED") && VersionUpdateConst.PACKAGENAME_BAIDU.equals(intent.getData().getSchemeSpecificPart())) {
                Baidu.this.startBaduiApp();
            }
        }
    };
    private Context mContext;
    private static final Object FROM = "1003582l";
    private static String mRequestUrl = "";

    @Override // com.sds.android.ttpod.ThirdParty.update.UpdateInterface
    public boolean check(Context context, final VersionUpdateData versionUpdateData, final UpdateCallback updateCallback) {
        this.mContext = context;
        final String str = "http://m.baidu.com/api?from=" + FROM + "&token=ttpod&type=app&index=3";
        TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.ThirdParty.update.Baidu.1
            @Override // java.lang.Runnable
            public void run() {
                if (StringUtils.m8346a(Baidu.mRequestUrl)) {
                    String unused = Baidu.mRequestUrl = Baidu.this.getRequestUrl(HttpRequest.m8713a(str, (HashMap<String, Object>) null, (HashMap<String, Object>) null).m8688e());
                }
                if (!StringUtils.m8346a(Baidu.mRequestUrl)) {
                    try {
                        Map unused2 = Baidu.mUpdateInfoMap = Baidu.this.toUpdateInfo(Baidu.this.requestHttp(Baidu.mRequestUrl + "&from=" + Baidu.FROM + "&token=ttpod&type=app&index=3", Baidu.this.getContextJson()));
                        if (Baidu.mUpdateInfoMap != null) {
                            versionUpdateData.setAppstoreInstalled(Boolean.valueOf(Baidu.this.isBaiduAppInstalled()));
                            versionUpdateData.setSize(Baidu.this.getSize((String) Baidu.mUpdateInfoMap.get("size")));
                            versionUpdateData.setUpgradeType(VersionUpdateConst.UPDATE_BAIDU_TYPE);
                            String str2 = (String) Baidu.mUpdateInfoMap.get("patch_size");
                            if (!StringUtils.m8346a(str2)) {
                                versionUpdateData.setPatchSize(Baidu.this.getSize(Long.parseLong(str2)));
                            }
                            versionUpdateData.setToolName("百度手机助手");
                            versionUpdateData.setUpdateState(VersionUpdateData.UpdateState.NEED);
                        } else {
                            versionUpdateData.setUpdateState(VersionUpdateData.UpdateState.FAILED);
                        }
                    } catch (Exception e) {
                        versionUpdateData.setUpdateState(VersionUpdateData.UpdateState.FAILED);
                        e.printStackTrace();
                    }
                } else {
                    versionUpdateData.setUpdateState(VersionUpdateData.UpdateState.FAILED);
                }
                updateCallback.updateInfo(versionUpdateData);
            }
        });
        return true;
    }

    private void registerReceiver(Context context) {
        if (context != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addDataScheme("package");
            context.registerReceiver(this.mAppInstallReceiver, intentFilter);
        }
    }

    private void unRegisterReceiver() {
        try {
            if (this.mContext != null) {
                this.mContext.unregisterReceiver(this.mAppInstallReceiver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double getSize(String str) {
        if (StringUtils.m8346a(str)) {
            return 0.0d;
        }
        return getSize(Integer.parseInt(mUpdateInfoMap.get("size")));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double getSize(long j) {
        return new BigDecimal((j / 1024.0d) / 1024.0d).setScale(2, 4).doubleValue();
    }

    public InputStream requestHttp(String str, String str2) {
        try {
            HttpPost httpPost = new HttpPost(str);
            StringEntity stringEntity = new StringEntity(str2, "UTF-8");
            httpPost.setHeader(HttpClientProxy.HEADER_CONTENT_TYPE, "application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);
            HttpResponse execute = new DefaultHttpClient().execute(httpPost);
            if (execute.getStatusLine().getStatusCode() == 200) {
                return execute.getEntity().getContent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override // com.sds.android.ttpod.ThirdParty.update.UpdateInterface
    public void update(boolean z, UpdateCallback updateCallback) {
        updateCallback.statisticAppInstall(z, VersionUpdateConst.TYPE_BAIDU_UPDATE);
        if (z) {
            startBaduiApp();
            return;
        }
        registerReceiver(this.mContext);
        if (updateCallback.needInstallApp(BAIDU_APP_URL, VersionUpdateConst.TYPE_BAIDU_UPDATE)) {
            updateCallback.installApp(BAIDU_APP_URL, VersionUpdateConst.TYPE_BAIDU_UPDATE);
        } else {
            updateCallback.downloadApp(BAIDU_APP_URL, VersionUpdateConst.TYPE_BAIDU_UPDATE);
        }
    }

    @Override // com.sds.android.ttpod.ThirdParty.update.UpdateInterface
    public void cancelUpdate() {
    }

    @Override // com.sds.android.ttpod.ThirdParty.update.UpdateInterface
    public void setDownloadProgressListener(DownloadProgressListener downloadProgressListener) {
    }

    @Override // com.sds.android.ttpod.ThirdParty.update.UpdateInterface
    public void onResume() {
    }

    @Override // com.sds.android.ttpod.ThirdParty.update.UpdateInterface
    public void onDestroy() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint("WrongConstant")
    public void startBaduiApp() {
        unRegisterReceiver();
        if (mUpdateInfoMap != null) {
            Intent intent = new Intent("com.baidu.appsearch.extinvoker.LAUNCH");
            intent.putExtra("id", VersionUpdateConst.ID_BAIDU);
            intent.putExtra("backop", FeedbackItem.STATUS_WAITING);
            intent.putExtra("func", "11");
            Bundle bundle = new Bundle();
            String str = mUpdateInfoMap.get("sname");
            bundle.putString("sname", str);
            bundle.putString("packagename", mUpdateInfoMap.get("package"));
            int parseInt = Integer.parseInt(mUpdateInfoMap.get("versioncode"));
            LogUtils.m8388a(TAG, "update params:versionCode:" + parseInt);
            bundle.putInt("versioncode", parseInt);
            bundle.putString("downurl", mUpdateInfoMap.get("download_url"));
            String str2 = mUpdateInfoMap.get("signmd5");
            bundle.putString("signmd5", str2);
            bundle.putString("tj", str2 + str);
            bundle.putString("versionname", mUpdateInfoMap.get("versionname"));
            bundle.putString("fparam", "lc");
            bundle.putString("iconurl", mUpdateInfoMap.get("icon"));
            bundle.putString("updatetime", mUpdateInfoMap.get("updatetime"));
            bundle.putString("size", mUpdateInfoMap.get("size"));
            bundle.putString("changelog", mUpdateInfoMap.get("changelog"));
            String str3 = mUpdateInfoMap.get("patch");
            if (!StringUtils.m8346a(str3)) {
                bundle.putString("patch_url", str3);
            }
            String str4 = mUpdateInfoMap.get("patch_size");
            if (!StringUtils.m8346a(str4)) {
                bundle.putLong("patch_size", Integer.parseInt(str4));
            }
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("extra_client_downloadinfo", bundle);
            intent.addFlags(268468224);
            try {
                this.mContext.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int getVersionCode() {
        try {
            return this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private PackageInfo getPackageInfo() {
        try {
            return this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 64);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getSignMd5(String str) {
        long j = 0;
        if (str == null || str.length() < 32) {
            return "-1";
        }
        String substring = str.substring(8, 24);
        long j2 = 0;
        for (int i = 0; i < 8; i++) {
            j2 = (j2 * 16) + Integer.parseInt(substring.substring(i, i + 1), 16);
        }
        for (int i2 = 8; i2 < substring.length(); i2++) {
            j = (j * 16) + Integer.parseInt(substring.substring(i2, i2 + 1), 16);
        }
        return String.valueOf((j + j2) & 4294967295L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBaiduAppInstalled() {
        for (PackageInfo packageInfo : this.mContext.getPackageManager().getInstalledPackages(0)) {
            if (VersionUpdateConst.PACKAGENAME_BAIDU.equals(packageInfo.packageName)) {
                return packageInfo.versionCode > BAIDU_VERSION_CODE;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getContextJson() {
        try {
            String md5 = getMd5(this.mContext);
            String signMd5 = getSignMd5(md5);
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("package", "com.sds.android.ttpod");
            jSONObject.put("versioncode", getVersionCode());
            jSONObject.put("signmd5", signMd5);
            jSONObject.put("md5", md5);
            jSONArray.put(jSONObject);
            return jSONArray.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> toUpdateInfo(InputStream inputStream) {
        HashMap hashMap;
        Exception e;
        if (inputStream == null) {
            return null;
        }
        try {
            NodeList childNodes = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream).getDocumentElement().getElementsByTagName(OnlineSearchEntryActivity.KEY_THIRD_APP_IDENTITY).item(0).getChildNodes();
            hashMap = new HashMap();
            for (int i = 0; i < childNodes.getLength(); i++) {
                try {
                    Node item = childNodes.item(i);
                    hashMap.put(item.getNodeName(), item.getChildNodes().item(0).getNodeValue());
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return hashMap;
                }
            }
            return hashMap;
        } catch (Exception e3) {
            hashMap = null;
            e = e3;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getRequestUrl(InputStream inputStream) {
        try {
            NodeList elementsByTagName = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(inputStream).getDocumentElement().getElementsByTagName("url");
            if (elementsByTagName != null && elementsByTagName.getLength() > 0) {
                return elementsByTagName.item(0).getFirstChild().getNodeValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getMd5(Context context) {
        try {
            return getMd5ByFile(new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).publicSourceDir));
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String getMd5ByFile(File file) {
        FileInputStream fileInputStream;
        Throwable th;
        Exception e;
        String str = null;
        byte[] bArr = new byte[1024];
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Exception e1) {
            e = e1;
            fileInputStream = null;
        } catch (Throwable th2) {
            fileInputStream = null;
            th = th2;
            if (fileInputStream != null) {
            }
            //throw th;
        }
        try {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    messageDigest.update(bArr, 0, read);
                }
                str = toHex(messageDigest.digest());
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                //throw th;
            }
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            return str;
        }
        return str;
    }

    private static String toHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (byte b : bArr) {
            sb.append(cArr[(b & 240) >> 4]);
            sb.append(cArr[b & 15]);
        }
        return sb.toString();
    }
}
