package com.sds.android.ttpod.share.p137b;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.p021c.p022a.RemoteSSO;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.sdk.lib.util.UrlUtils;

import java.security.MessageDigest;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.share.b.g */
/* loaded from: classes.dex */
public class SinaWeiboAuthHandler extends AuthHandler {

    /* renamed from: b */
    private AuthCallback f7366b;

    /* renamed from: c */
    private String f7367c;

    /* renamed from: d */
    private ServiceConnection f7368d;

    public SinaWeiboAuthHandler(Activity activity) {
        super(activity);
        this.f7368d = new ServiceConnection() { // from class: com.sds.android.ttpod.share.b.g.1
            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                RemoteSSO m9780a = RemoteSSO.AbstractBinderC0329a.m9780a(iBinder);
                try {
                    if (!SinaWeiboAuthHandler.this.m2060a(m9780a.mo9779a(), "all", m9780a.mo9778b())) {
                        SinaWeiboAuthHandler.this.m2070a(SinaWeiboAuthHandler.this.f7366b, "sso授权失败");
                    }
                } catch (RemoteException e) {
                    SinaWeiboAuthHandler.this.m2070a(SinaWeiboAuthHandler.this.f7366b, e.getMessage());
                }
            }
        };
        this.f7367c = activity.getApplicationContext().getPackageName();
    }

    @Override // com.sds.android.ttpod.share.p137b.AuthHandler
    /* renamed from: a */
    public String mo2066a() {
        HashMap hashMap = new HashMap();
        hashMap.put("forcelogin", false);
        hashMap.put("client_id", "3374293008");
        hashMap.put("response_type", "token");
        hashMap.put("redirect_uri", "http://ttus.ttpod.com/thirdlogin/sina?code=6aef2d447c0e33be42045115551fbcc4");
        hashMap.put("display", "mobile");
        hashMap.put("scope", "all");
        return UrlUtils.buildGetParamsUrl("https://open.weibo.cn/oauth2/authorize", hashMap);
    }

    @Override // com.sds.android.ttpod.share.p137b.AuthHandler
    /* renamed from: c */
    public void mo2059c(AuthCallback authCallback) {
        this.f7366b = authCallback;
        Intent intent =  new Intent("com.sina.weibo.remotessoservice");
        intent.setPackage(activity.getPackageName());
        if (!this.activity.getApplicationContext().bindService(intent, this.f7368d, Context.BIND_AUTO_CREATE)) {
            m2070a(this.f7366b, "没有安装客户端");
        }
    }

    @Override // com.sds.android.ttpod.share.p137b.AuthHandler
    /* renamed from: a */
    public void mo2065a(int i, int i2, Intent intent) {
        if (i == 32973) {
            if (i2 == -1) {
                String stringExtra = intent.getStringExtra("error");
                if (stringExtra == null) {
                    stringExtra = intent.getStringExtra("error_type");
                }
                if (stringExtra != null) {
                    m2070a(this.f7366b, stringExtra);
                    return;
                }
                Bundle extras = intent.getExtras();
                if (extras == null || StringUtils.isEmpty(extras.getString("access_token"))) {
                    m2070a(this.f7366b, "Sina SSO授权失败");
                    return;
                } else {
                    m2071a(this.f7366b, intent.getExtras());
                    return;
                }
            }
            m2070a(this.f7366b, "授权失败");
        }
    }

    /* renamed from: a */
    private String m2064a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(this.f7367c, 64);
            for (int i = 0; i < packageInfo.signatures.length; i++) {
                byte[] byteArray = packageInfo.signatures[i].toByteArray();
                if (byteArray != null) {
                    return C2135a.m2058a(byteArray);
                }
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m2060a(String str, String str2, String str3) {
        boolean z = true;
        Intent intent = new Intent();
        intent.setClassName(str, str3);
        intent.putExtra("appKey", "3374293008");
        intent.putExtra("redirectUri", "http://ttus.ttpod.com/thirdlogin/sina?code=6aef2d447c0e33be42045115551fbcc4");
        intent.putExtra("packagename", this.f7367c);
        intent.putExtra("key_hash", m2064a(this.activity));
        Bundle bundle = new Bundle();
        bundle.putInt("_weibo_command_type", 3);
        bundle.putString("_weibo_transaction", String.valueOf(System.currentTimeMillis()));
        intent.putExtras(bundle);
        if (str2 != null) {
            intent.putExtra("scope", str2);
        }
        if (m2063a(intent)) {
            try {
                this.activity.startActivityForResult(intent, 32973);
            } catch (ActivityNotFoundException e) {
                z = false;
            }
            this.activity.getApplication().unbindService(this.f7368d);
            return z;
        }
        return false;
    }

    /* renamed from: a */
    private boolean m2063a(Intent intent) {
        ResolveInfo resolveActivity = this.activity.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null) {
            return false;
        }
        try {
            Signature[] signatureArr = this.activity.getPackageManager().getPackageInfo(resolveActivity.activityInfo.packageName, 64).signatures;
            for (Signature signature : signatureArr) {
                if ("30820295308201fea00302010202044b4ef1bf300d06092a864886f70d010105050030818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c74643020170d3130303131343130323831355a180f32303630303130323130323831355a30818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c746430819f300d06092a864886f70d010101050003818d00308189028181009d367115bc206c86c237bb56c8e9033111889b5691f051b28d1aa8e42b66b7413657635b44786ea7e85d451a12a82a331fced99c48717922170b7fc9bc1040753c0d38b4cf2b22094b1df7c55705b0989441e75913a1a8bd2bc591aa729a1013c277c01c98cbec7da5ad7778b2fad62b85ac29ca28ced588638c98d6b7df5a130203010001300d06092a864886f70d0101050500038181000ad4b4c4dec800bd8fd2991adfd70676fce8ba9692ae50475f60ec468d1b758a665e961a3aedbece9fd4d7ce9295cd83f5f19dc441a065689d9820faedbb7c4a4c4635f5ba1293f6da4b72ed32fb8795f736a20c95cda776402099054fccefb4a1a558664ab8d637288feceba9508aa907fc1fe2b1ae5a0dec954ed831c0bea4".equals(signature.toCharsString())) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SinaWeiboAuthHandler.java */
    /* renamed from: com.sds.android.ttpod.share.b.g$a */
    /* loaded from: classes.dex */
    public static class C2135a {

        /* renamed from: a */
        private static final char[] f7370a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        /* renamed from: a */
        public static String m2058a(byte[] bArr) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(bArr);
                byte[] digest = messageDigest.digest();
                char[] cArr = new char[32];
                int i = 0;
                for (int i2 = 0; i2 < 16; i2++) {
                    byte b = digest[i2];
                    int i3 = i + 1;
                    cArr[i] = f7370a[(b >>> 4) & 15];
                    i = i3 + 1;
                    cArr[i3] = f7370a[b & 15];
                }
                return new String(cArr);
            } catch (Exception e) {
                LogUtils.debug("SinaWeiboAuthHandler", "Exception:" + e.toString());
                return null;
            }
        }
    }
}
