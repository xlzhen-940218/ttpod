package com.sds.android.ttpod.activities.web;

import android.webkit.JavascriptInterface;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.OnlineMediaUtils;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class WebJsInterface {
    private static final String KEY_SONG_ID = "songId";
    private static final String KEY_STATUS = "status";
    private static final String TAG = WebJsInterface.class.getSimpleName();
    private JsCallback mJsCallback;

    public WebJsInterface() {
    }

    public WebJsInterface(JsCallback jsCallback) {
        this.mJsCallback = jsCallback;
    }

    @JavascriptInterface
    public String imei() {
        String m8485a = EnvironmentUtils.C0604c.m8485a();
        return StringUtils.m8346a(m8485a) ? EnvironmentUtils.C0604c.m8478c() : m8485a;
    }

    @JavascriptInterface
    public void pause() {
        LogUtils.m8379d(TAG, "pause from javascript");
        CommandCenter.m4607a().m4596b(new Command(CommandID.PAUSE, new Object[0]));
    }

    @JavascriptInterface
    public void resume() {
        LogUtils.m8379d(TAG, "pause from javascript");
        CommandCenter.m4607a().m4596b(new Command(CommandID.RESUME, new Object[0]));
    }

    @JavascriptInterface
    public void stop() {
        LogUtils.m8379d(TAG, "stop from javascript");
        CommandCenter.m4607a().m4596b(new Command(CommandID.STOP, new Object[0]));
    }

    @JavascriptInterface
    public String getGeneralParameters() {
        return JSONUtils.toJson(EnvironmentUtils.C0603b.m8488e());
    }

    @JavascriptInterface
    public void next() {
        LogUtils.m8379d(TAG, "next from javascript");
        CommandCenter.m4607a().m4596b(new Command(CommandID.NEXT, new Object[0]));
    }

    @JavascriptInterface
    public void previous() {
        LogUtils.m8379d(TAG, "previous from javascript");
        CommandCenter.m4607a().m4596b(new Command(CommandID.PREVIOUS, new Object[0]));
    }

    @JavascriptInterface
    public void play(String str) {
        if (!StringUtils.m8346a(str)) {
            List<Long> m8338b = StringUtils.m8338b(str, ",");
            if (m8338b.size() != 0) {
                OnlineMediaUtils.m4675a(m8338b, new OnlineMediaUtils.InterfaceC1790a<List<MediaItem>>() { // from class: com.sds.android.ttpod.activities.web.WebJsInterface.1
                    @Override // com.sds.android.ttpod.framework.p106a.OnlineMediaUtils.InterfaceC1790a
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo4039a(List<MediaItem> list) {
                        if (list != null && list.size() > 0) {
                            CommandCenter.m4607a().m4606a(new Command(CommandID.SYNC_NET_TEMPORARY_GROUP, list));
                            CommandCenter.m4607a().m4606a(new Command(CommandID.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, list.get(0)));
                        }
                    }
                });
            }
        }
    }

    @JavascriptInterface
    public void doDownload(String str, String str2, int i) {
        LogUtils.m8380c(TAG, "doDownload from javascript url:%s name:%s type:%d", str, str2, Integer.valueOf(i));
        if (this.mJsCallback != null) {
            this.mJsCallback.doDownload(str, str2, i);
        }
    }

    @JavascriptInterface
    public String getPlayerStatus() {
        LogUtils.m8388a(TAG, "getPlayerStatus form javascript");
        MediaItem m3225N = Cache.m3218a().m3225N();
        JSONObject jSONObject = new JSONObject();
        long j = 0L;
        PlayStatus playStatus = PlayStatus.STATUS_STOPPED;
        if (!m3225N.isNull() && m3225N.isOnline()) {
            j = m3225N.getSongID();
            playStatus = SupportFactory.m2397a(BaseApplication.getApplication()).m2463m();
        }
        addJson(jSONObject, KEY_SONG_ID, j);
        addJson(jSONObject, "status", playStatus.toString());
        return jSONObject.toString();
    }

    private void addJson(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.putOpt(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
