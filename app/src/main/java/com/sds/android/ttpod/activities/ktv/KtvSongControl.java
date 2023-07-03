package com.sds.android.ttpod.activities.ktv;

import android.content.Context;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;


import com.sds.android.sdk.lib.request.PostContentRequest;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.WaitingDialog;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.utils.MD5Tools;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.sds.android.ttpod.activities.ktv.f */
/* loaded from: classes.dex */
public class KtvSongControl {

    /* renamed from: g */
    private static KtvSongControl f2642g;

    /* renamed from: a */
    private WaitingDialog f2643a;

    /* renamed from: b */
    private String f2644b;

    /* renamed from: c */
    private String f2645c;

    /* renamed from: d */
    private String f2646d;

    /* renamed from: e */
    private long f2647e;

    /* renamed from: f */
    private KtvConnectCallback f2648f;

    /* renamed from: a */
    public static KtvSongControl m8118a() {
        if (f2642g == null) {
            f2642g = new KtvSongControl();
        }
        return f2642g;
    }

    /* renamed from: a */
    public void m8114a(KtvConnectCallback ktvConnectCallback) {
        this.f2648f = ktvConnectCallback;
    }

    /* renamed from: a */
    public void m8116a(Context context, String str) {
        m8107b(context, "正在连接ktv,请等待...");
        KtvApi.m8124a(str).m8544a(new RequestCallback<KtvResult>() { // from class: com.sds.android.ttpod.activities.ktv.f.1
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(KtvResult ktvResult) {
                KtvSongControl.this.f2645c = ktvResult.m8119c();
                KtvSongControl.this.f2644b = ktvResult.m8120b();
                LogUtils.debug("KtvSongControl", "mRoomInfo: " + KtvSongControl.this.f2645c + " mUrlDomain:" + KtvSongControl.this.f2644b);
                Preferences.m2900c(KtvSongControl.this.f2645c);
                Preferences.m3012a(KtvSongControl.this.f2644b);
                KtvSongControl.this.m8101d();
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(KtvResult ktvResult) {
                KtvSongControl.this.m8092i();
                if (ktvResult.getCode() == 5) {
                    PopupsUtils.m6721a("请到服务中心，开台吧！");
                } else {
                    PopupsUtils.m6721a(ktvResult.getMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m8101d() {
        int m8093h = m8093h();
        this.f2647e = m8099e();
        HashMap hashMap = new HashMap();
        hashMap.put("appid", "D6A10423AD08459E8E384604C56E6836");
        hashMap.put("userid", Long.valueOf(this.f2647e));
        hashMap.put("username", m8097f());
        hashMap.put("userpic", m8095g());
        hashMap.put("bindType", 2);
        hashMap.put("roominfo", this.f2645c);
        hashMap.put("time", Integer.valueOf(m8093h));
        hashMap.put("sign", m8111a(this.f2645c, null, m8093h));
        KtvApi.m8123a(this.f2644b + "/room", hashMap).m8544a(new RequestCallback<KtvResult>() { // from class: com.sds.android.ttpod.activities.ktv.f.2
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(KtvResult ktvResult) {
                KtvSongControl.this.f2646d = ktvResult.m8121a();
                LogUtils.debug("KtvSongControl", "mCheckCode: " + KtvSongControl.this.f2646d);
                Preferences.m2933b(KtvSongControl.this.f2646d);
                Preferences.m3025a(KtvSongControl.this.f2647e);
                if (KtvSongControl.this.f2648f != null) {
                    KtvSongControl.this.f2648f.success();
                }
                KtvSongControl.this.m8092i();
                PopupsUtils.m6721a("连接KTV成功，可以点歌啦！");
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(KtvResult ktvResult) {
                KtvSongControl.this.m8092i();
                LogUtils.debug("KtvSongControl", "bindKtvRoom fail: " + ktvResult.getCode());
                KtvSongControl.this.m8109b();
                if (KtvSongControl.this.f2648f != null) {
                    KtvSongControl.this.f2648f.fail();
                }
                PopupsUtils.m6721a(ktvResult.getMessage());
            }
        });
    }

    /* renamed from: a */
    public void m8115a(Context context, final List<KtvSongInfo> list) {
        if (list != null) {
            if (m8104c()) {
                //StatisticUtils.m4917a(169, (int) 65537, 1L);
                m8107b(context, "正在点歌,请等待...");
                String str = this.f2644b + "/song/vod";
                int m8093h = m8093h();
                this.f2647e = this.f2647e <= 0 ? Preferences.m2892e() : this.f2647e;
                m8110a(str, "D6A10423AD08459E8E384604C56E6836", this.f2645c, JSONUtils.toJson(list), String.valueOf(this.f2647e), m8093h, m8111a(this.f2645c, this.f2646d, m8093h)).m8544a(new RequestCallback<KtvSongListResult>() { // from class: com.sds.android.ttpod.activities.ktv.f.3
                    @Override // com.sds.android.sdk.lib.request.RequestCallback
                    /* renamed from: a */
                    public void onRequestSuccess(KtvSongListResult ktvSongListResult) {
                        StringBuffer stringBuffer;
                        KtvSongControl.this.m8092i();
                        //StatisticUtils.m4909a("ktv", "click", "play-song", 1L);
                        //StatisticUtils.m4917a(170, (int) 65537, 1L);
                        if (list.size() == 1 && list.size() == ktvSongListResult.m8085a().size()) {
                            stringBuffer = new StringBuffer("歌曲不能匹配");
                        } else {
                            stringBuffer = new StringBuffer("点歌成功");
                            if (ktvSongListResult.m8085a().size() > 0) {
                                stringBuffer.append(",有" + ktvSongListResult.m8085a().size() + "首歌曲不能匹配!");
                            }
                        }
                        PopupsUtils.m6721a(stringBuffer.toString());
                    }

                    @Override // com.sds.android.sdk.lib.request.RequestCallback
                    /* renamed from: b */
                    public void onRequestFailure(KtvSongListResult ktvSongListResult) {
                        KtvSongControl.this.m8092i();
                        //StatisticUtils.m4909a("ktv", "click", "play-song", -1L);
                        if (ktvSongListResult.getCode() == 6 || ktvSongListResult.getCode() == 3) {
                            KtvSongControl.this.m8109b();
                            if (KtvSongControl.this.f2648f != null) {
                                KtvSongControl.this.f2648f.fail();
                            }
                            PopupsUtils.m6721a("请重新连接KTV!");
                            return;
                        }
                        PopupsUtils.m6721a(ktvSongListResult.getMessage());
                    }
                });
                return;
            }
            m8117a(context);
        }
    }

    /* renamed from: b */
    public void m8109b() {
        Preferences.m2933b((String) null);
        Preferences.m2900c((String) null);
    }

    /* renamed from: c */
    public boolean m8104c() {
        this.f2644b = Preferences.m2944b();
        this.f2645c = Preferences.m2898d();
        this.f2646d = Preferences.m2904c();
        return (StringUtils.isEmpty(this.f2644b) || StringUtils.isEmpty(this.f2645c) || StringUtils.isEmpty(this.f2646d)) ? false : true;
    }

    /* renamed from: e */
    private long m8099e() {
        long currentTimeMillis = System.currentTimeMillis();
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null) {
            return m2954aq.getUserId();
        }
        return currentTimeMillis;
    }

    /* renamed from: f */
    private String m8097f() {
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq == null) {
            return "ttpod_ktv";
        }
        return m2954aq.getUserName();
    }

    /* renamed from: g */
    private String m8095g() {
        TTPodUser m2954aq = Preferences.m2954aq();
        return m2954aq != null ? m2954aq.getAvatarUrl() : "";
    }

    /* renamed from: h */
    private int m8093h() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /* renamed from: a */
    private String m8111a(String str, String str2, int i) {
        if (i <= 0) {
            i = m8093h();
        }
        StringBuffer stringBuffer = new StringBuffer("32433A3F98F34716A5D663B4D5AFF7D5");
        if (!StringUtils.isEmpty(str2)) {
            stringBuffer.append(str2);
        }
        stringBuffer.append(str);
        stringBuffer.append(i);
        LogUtils.debug("KtvSongControl", "sing:" + stringBuffer.toString());
        return MD5Tools.toMD5(stringBuffer.toString()).toLowerCase();
    }

    /* renamed from: b */
    private void m8107b(Context context, String str) {
        try {
            if (this.f2643a == null) {
                this.f2643a = new WaitingDialog(context);
                if (!StringUtils.isEmpty(str)) {
                    this.f2643a.setText((CharSequence) str);
                }
            }
            if (!this.f2643a.isShowing()) {
                this.f2643a.show();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public void m8092i() {
        if (this.f2643a != null && this.f2643a.isShowing()) {
            this.f2643a.dismiss();
            this.f2643a = null;
        }
    }

    /* renamed from: a */
    public static PostContentRequest<KtvSongListResult> m8110a(String str, String str2, String str3, String str4, String str5, int i, String str6) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appid", str2);
            jSONObject.put("roominfo", str3);
            jSONObject.put("musicinfo", new JSONArray(str4));
            jSONObject.put("userid", str5);
            jSONObject.put("sign", str6);
            jSONObject.put("time", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PostContentRequest<KtvSongListResult> postContentRequest = new PostContentRequest<>(KtvSongListResult.class, str, jSONObject.toString());
        postContentRequest.m8533d("Content-Type", "application/json");
        postContentRequest.m8533d("Accept-Gzip", "not-gzip");
        return postContentRequest;
    }

    /* renamed from: a */
    public static void m8117a(Context context) {
        if (!m8108b(context)) {
            if (context instanceof KtvActivity) {
                ((KtvActivity) context).showDownloadDialog();
                return;
            }
            return;
        }
        //StatisticUtils.m4910a("ktv", "click", "camera");
        FragmentLoaderActivity.startFragmentLoaderActivity(context, 8);
    }

    /* renamed from: b */
    public static boolean m8108b(Context context) {
        return new File(context.getFilesDir(), "apk/Ktv.apk").exists();
    }
}
