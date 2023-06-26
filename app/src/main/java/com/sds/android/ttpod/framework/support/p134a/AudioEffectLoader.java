package com.sds.android.ttpod.framework.support.p134a;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.sds.android.cloudapi.ttpod.data.AudioEffectItem;
import com.sds.android.cloudapi.ttpod.p055a.CloudAudioEffectAPI;
import com.sds.android.cloudapi.ttpod.result.AudioEffectItemResult;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectCache;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectUtils;
import com.sds.android.ttpod.framework.modules.core.audioeffect.EqualizerPreset;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.audiofx.TTEqualizer;
import com.sds.android.ttpod.media.mediastore.MediaItem;

import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.sds.android.ttpod.framework.support.a.a */
/* loaded from: classes.dex */
public class AudioEffectLoader {

    /* renamed from: a */
    private int f7008a;

    /* renamed from: b */
    private MediaPlayerProxy f7009b;

    /* renamed from: c */
    private TTEqualizer.Settings f7010c;

    /* renamed from: d */
    private Handler f7011d = new Handler();

    /* renamed from: e */
    private String f7012e = "";

    /* renamed from: f */
    private int f7013f = 0;

    /* renamed from: g */
    private int f7014g = 0;

    /* renamed from: h */
    private int f7015h = 0;

    /* renamed from: i */
    private int f7016i = 0;

    /* renamed from: j */
    private float f7017j = 0.0f;

    /* renamed from: k */
    private boolean f7018k = true;

    /* renamed from: l */
    private boolean f7019l = false;

    /* renamed from: m */
    private boolean f7020m = false;

    /* renamed from: n */
    private boolean f7021n = false;

    /* renamed from: o */
    private String f7022o = "";

    /* renamed from: p */
    private String f7023p = "";

    /* renamed from: q */
    private int f7024q = 0;

    /* renamed from: r */
    private Context f7025r;

    public AudioEffectLoader(Context context, MediaPlayerProxy mediaPlayerProxy) {
        this.f7008a = 0;
        this.f7010c = null;
        this.f7025r = null;
        this.f7025r = context;
        if (mediaPlayerProxy == null) {
            throw new IllegalArgumentException("attachedMediaPlayerProxy must not be null!");
        }
        this.f7009b = mediaPlayerProxy;
        this.f7008a = 4;
        this.f7010c = new TTEqualizer.Settings(EqualizerPreset.m4334b(), (short) 10, EqualizerPreset.m4333b(EqualizerPreset.m4334b()));
    }

    /* renamed from: a */
    public void m2751a(boolean z) {
        this.f7019l = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public String m2763a() {
        AudioEffectParam audioEffectParam = new AudioEffectParam();
        audioEffectParam.m4438b(this.f7013f);
        audioEffectParam.m4432c(this.f7014g);
        audioEffectParam.m4428d(this.f7015h);
        audioEffectParam.m4425e(this.f7016i);
        audioEffectParam.m4447a(this.f7017j);
        audioEffectParam.m4440a(this.f7018k);
        audioEffectParam.m4434b(Preferences.m2960ak());
        audioEffectParam.m4446a(this.f7024q);
        audioEffectParam.m4441a(this.f7023p);
        audioEffectParam.m4422f(this.f7008a);
        audioEffectParam.m4430c(this.f7012e);
        if (this.f7010c == null) {
            this.f7010c = new TTEqualizer.Settings(EqualizerPreset.m4334b(), (short) 10, EqualizerPreset.m4333b(EqualizerPreset.m4334b()));
        }
        audioEffectParam.m4435b(this.f7010c.toString());
        return JSONUtils.toJson(audioEffectParam);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m2750b() {
        this.f7010c = m2733i();
        this.f7014g = Preferences.m2958am();
        this.f7013f = Preferences.m2962aj();
        this.f7015h = Preferences.m2957an();
        this.f7016i = m2737e(Preferences.m2964ai());
        this.f7017j = Preferences.m2956ao();
        this.f7018k = Preferences.m2959al();
        this.f7023p = "";
        this.f7024q = 0;
        this.f7008a = 4;
        m2744b(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2754a(Boolean bool) {
        this.f7021n = bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean m2743c() {
        return this.f7021n;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m2746b(Boolean bool) {
        this.f7020m = bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2759a(AudioEffectItem audioEffectItem, boolean z) {
        this.f7020m = z;
        if (z) {
            this.f7013f = audioEffectItem.getDataBass();
            this.f7014g = audioEffectItem.getDataTreble();
            this.f7015h = audioEffectItem.getDataVirtualizer();
            this.f7016i = m2737e(audioEffectItem.getDataReverb());
            this.f7017j = audioEffectItem.getDataBalance();
            this.f7018k = audioEffectItem.getDataIsLimit();
            this.f7023p = audioEffectItem.getNickName();
            this.f7024q = audioEffectItem.getStyle();
            this.f7012e = audioEffectItem.getID();
            this.f7010c = new TTEqualizer.Settings(EqualizerPreset.m4336a(this.f7024q), (short) 10, audioEffectItem.getDataEqualizer());
            this.f7019l = false;
            m2744b(true);
            this.f7008a = 3;
            return;
        }
        m2748b(audioEffectItem, false);
    }

    /* renamed from: a */
    public void m2755a(MediaItem mediaItem, int i) {
        if (mediaItem == null) {
            Preferences.m3077B(false);
            if (this.f7010c == null) {
                this.f7010c = new TTEqualizer.Settings(EqualizerPreset.m4334b(), (short) 10, EqualizerPreset.m4333b(EqualizerPreset.m4334b()));
            }
            switch (i) {
                case 1:
                    this.f7013f = Preferences.m2962aj();
                    m2749b(this.f7013f);
                    break;
                case 2:
                    this.f7014g = Preferences.m2958am();
                    m2742c(this.f7014g);
                    break;
                case 3:
                    this.f7015h = Preferences.m2957an();
                    m2739d(this.f7015h);
                    break;
                case 4:
                    this.f7016i = m2737e(Preferences.m2964ai());
                    m2761a(this.f7016i);
                    break;
                case 5:
                    this.f7017j = Preferences.m2956ao();
                    m2762a(this.f7017j);
                    break;
                case 6:
                    this.f7018k = Preferences.m2959al();
                    m2741c(this.f7018k);
                    break;
                case 7:
                    this.f7010c = new TTEqualizer.Settings(Preferences.m2968ag());
                    m2757a(this.f7010c);
                    break;
                case 8:
                    m2744b(false);
                    break;
            }
            Preferences.m2868j(this.f7010c.toString());
            Preferences.m2869j(this.f7013f);
            Preferences.m2865k(this.f7014g);
            Preferences.m2861l(this.f7015h);
            Preferences.m2873i(this.f7016i);
            Preferences.m3027a(this.f7017j);
            Preferences.m3073D(this.f7018k);
            this.f7023p = "";
            this.f7024q = 0;
            this.f7012e = "";
            this.f7008a = 4;
        } else if (this.f7020m) {
            this.f7020m = false;
        } else if (this.f7021n) {
            this.f7021n = false;
            m2744b(false);
        } else if (this.f7019l) {
            m2750b();
            this.f7008a = 4;
        } else {
            if (mediaItem.isOnline() && StringUtils.isEmpty(mediaItem.getLocalDataSource())) {
                Preferences.m3075C(false);
                m2747b(mediaItem);
            } else {
                Preferences.m3075C(false);
                m2756a(mediaItem);
            }
            m2744b(true);
        }
    }

    /* renamed from: b */
    private void m2744b(boolean z) {
        m2757a(this.f7010c);
        m2761a(this.f7016i);
        m2749b(this.f7013f);
        m2742c(this.f7014g);
        m2739d(this.f7015h);
        m2741c(this.f7018k);
        m2762a(this.f7017j);
        if (z) {
            this.f7025r.sendBroadcast(new Intent(Action.AUDIOEFFECT_CHANGED));
        }
    }

    /* renamed from: b */
    private void m2748b(AudioEffectItem audioEffectItem, boolean z) {
        m2757a(new TTEqualizer.Settings(EqualizerPreset.m4336a(this.f7024q), (short) 10, audioEffectItem.getDataEqualizer()));
        m2761a(audioEffectItem.getDataReverb());
        m2749b(audioEffectItem.getDataBass());
        m2742c(audioEffectItem.getDataTreble());
        m2739d(audioEffectItem.getDataVirtualizer());
        m2741c(audioEffectItem.getDataIsLimit());
        m2762a(audioEffectItem.getDataBalance());
        if (z) {
            this.f7025r.sendBroadcast(new Intent(Action.AUDIOEFFECT_CHANGED));
        }
    }

    /* renamed from: a */
    private void m2756a(final MediaItem mediaItem) {
        if (Preferences.m2974ad()) {
            String m4338b = AudioEffectUtils.m4338b(mediaItem.getSongID(), mediaItem.getTitle(), mediaItem.getArtist());
            if (mediaItem.getSongID() != null && AudioEffectUtils.m4339b(mediaItem) && FileUtils.m8414b(m4338b)) {
                this.f7008a = 1;
                m2745b(m4338b);
                if (Preferences.m2972ae()) {
                    Preferences.m3077B(false);
                    return;
                }
                return;
            }
            m2734h();
            if (2 == EnvironmentUtils.DeviceConfig.m8476d() || !Preferences.m3066H() || Preferences.m2972ae()) {
                if (Preferences.m2972ae()) {
                    Preferences.m3077B(false);
                }
                this.f7011d.post(new Runnable() { // from class: com.sds.android.ttpod.framework.support.a.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CloudAudioEffectAPI.m8927a(mediaItem.getTitle(), mediaItem.getArtist(), 1, 1).m8544a(new RequestCallback<AudioEffectItemResult>() { // from class: com.sds.android.ttpod.framework.support.a.a.1.1
                            @Override // com.sds.android.sdk.lib.request.RequestCallback
                            /* renamed from: a */
                            public void onRequestSuccess(AudioEffectItemResult audioEffectItemResult) {
                                AudioEffectItem audioEffectItem;
                                if (audioEffectItemResult.getEffectList().size() > 0 && (audioEffectItem = audioEffectItemResult.getEffectList().get(0)) != null && audioEffectItem.getPickCount() > 1) {
                                    AudioEffectLoader.this.m2753a(mediaItem.getSongID(), mediaItem.getTitle(), mediaItem.getArtist(), audioEffectItem);
                                }
                            }

                            @Override // com.sds.android.sdk.lib.request.RequestCallback
                            /* renamed from: b */
                            public void onRequestFailure(AudioEffectItemResult audioEffectItemResult) {
                            }
                        });
                    }
                });
                return;
            }
            return;
        }
        m2736f();
    }

    /* renamed from: f */
    private void m2736f() {
        this.f7013f = Preferences.m2962aj();
        this.f7014g = Preferences.m2958am();
        this.f7015h = Preferences.m2957an();
        this.f7016i = m2737e(Preferences.m2964ai());
        this.f7017j = Preferences.m2956ao();
        this.f7018k = Preferences.m2959al();
        this.f7023p = "";
        this.f7024q = 0;
        this.f7012e = "";
        this.f7010c = new TTEqualizer.Settings(EqualizerPreset.m4334b(), (short) 10, EqualizerPreset.m4333b(EqualizerPreset.m4334b()));
        String m2968ag = Preferences.m2968ag();
        if (!StringUtils.isEmpty(m2968ag)) {
            this.f7010c = new TTEqualizer.Settings(m2968ag);
        }
        this.f7008a = 4;
    }

    /* renamed from: a */
    private String m2760a(long j) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(j));
    }

    /* renamed from: a */
    private boolean m2752a(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        try {
            return (new Date(System.currentTimeMillis()).getTime() - new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(str).getTime()) / 3600000 > 24;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: g */
    private void m2735g() {
        this.f7013f = 0;
        this.f7014g = 0;
        this.f7015h = 0;
        this.f7016i = 0;
        this.f7017j = 0.0f;
        this.f7018k = true;
        this.f7023p = "";
        this.f7024q = 0;
        this.f7012e = "";
        this.f7010c = new TTEqualizer.Settings(EqualizerPreset.m4334b(), (short) 10, EqualizerPreset.m4333b(EqualizerPreset.m4334b()));
    }

    /* renamed from: h */
    private void m2734h() {
        m2735g();
        this.f7008a = 0;
    }

    /* renamed from: b */
    private void m2745b(String str) {
        String m8403i = FileUtils.m8403i(str);
        if (m8403i != null) {
            AudioEffectCache audioEffectCache = (AudioEffectCache) JSONUtils.fromJson(m8403i, AudioEffectCache.class);
            if (audioEffectCache != null) {
                this.f7023p = audioEffectCache.m4392g();
                this.f7024q = audioEffectCache.m4398e();
                this.f7013f = audioEffectCache.m4389h();
                this.f7014g = audioEffectCache.m4387i();
                this.f7015h = audioEffectCache.m4386j();
                this.f7016i = m2737e(audioEffectCache.m4385k());
                this.f7017j = audioEffectCache.m4384l();
                this.f7018k = audioEffectCache.m4383m();
                this.f7012e = audioEffectCache.m4416a();
                this.f7010c = new TTEqualizer.Settings(EqualizerPreset.m4336a(this.f7024q), (short) 10, audioEffectCache.m4382n());
                this.f7022o = m2760a(audioEffectCache.m4381o());
                return;
            }
            m2734h();
            return;
        }
        m2734h();
    }

    /* renamed from: b */
    private void m2747b(MediaItem mediaItem) {
        if (Preferences.m2974ad()) {
            try {
                String m4338b = AudioEffectUtils.m4338b(mediaItem.getSongID(), mediaItem.getTitle(), mediaItem.getArtist());
                if (FileUtils.m8414b(m4338b) && AudioEffectUtils.m4339b(mediaItem)) {
                    this.f7008a = 1;
                    m2745b(m4338b);
                } else {
                    m2734h();
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        m2736f();
    }

    /* renamed from: i */
    private TTEqualizer.Settings m2733i() {
        TTEqualizer.Settings settings;
        try {
            String m2968ag = Preferences.m2968ag();
            if (m2968ag != null && !StringUtils.isEmpty(m2968ag)) {
                settings = new TTEqualizer.Settings(m2968ag);
            } else {
                settings = new TTEqualizer.Settings(EqualizerPreset.m4334b(), (short) 10, EqualizerPreset.m4333b(EqualizerPreset.m4334b()));
            }
            return settings;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    public String m2740d() {
        if (this.f7010c == null) {
            this.f7010c = new TTEqualizer.Settings(EqualizerPreset.m4334b(), (short) 10, EqualizerPreset.m4333b(EqualizerPreset.m4334b()));
        }
        return this.f7010c.toString();
    }

    /* renamed from: a */
    private void m2757a(TTEqualizer.Settings settings) {
        if (settings != null) {
            this.f7009b.m2714a(settings);
        }
        if (settings == null || settings.isFlat()) {
            this.f7009b.m2710a(false);
        } else {
            this.f7009b.m2710a(true);
        }
    }

    /* renamed from: a */
    private void m2761a(int i) {
        this.f7009b.m2693e(i);
        if (i == 0) {
            this.f7009b.m2688f(false);
        } else {
            this.f7009b.m2688f(true);
        }
    }

    /* renamed from: b */
    private void m2749b(int i) {
        this.f7009b.m2706b(i);
        if (i == 0) {
            this.f7009b.m2703b(false);
        } else {
            this.f7009b.m2703b(true);
        }
    }

    /* renamed from: c */
    private void m2741c(boolean z) {
        this.f7009b.m2699c(z);
    }

    /* renamed from: c */
    private void m2742c(int i) {
        this.f7009b.m2701c(i);
        if (i == 0) {
            this.f7009b.m2695d(false);
        } else {
            this.f7009b.m2695d(true);
        }
    }

    /* renamed from: d */
    private void m2739d(int i) {
        this.f7009b.m2697d(i);
        if (i == 0) {
            this.f7009b.m2691e(false);
        } else {
            this.f7009b.m2691e(true);
        }
    }

    /* renamed from: a */
    private void m2762a(float f) {
        this.f7009b.m2729a(f);
    }

    /* renamed from: e */
    public void m2738e() {
        m2735g();
        Preferences.m2868j(this.f7010c.toString());
        Preferences.m2869j(this.f7013f);
        Preferences.m2865k(this.f7014g);
        Preferences.m2861l(this.f7015h);
        Preferences.m2873i(this.f7016i);
        Preferences.m3027a(this.f7017j);
        Preferences.m3073D(this.f7018k);
        m2744b(true);
        this.f7008a = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2753a(Long l, String str, String str2, AudioEffectItem audioEffectItem) {
        String m4340a = AudioEffectUtils.m4340a(l, str, str2);
        AudioEffectCache audioEffectCache = new AudioEffectCache();
        audioEffectCache.m4411a(audioEffectItem.getID());
        audioEffectCache.m4412a(l);
        audioEffectCache.m4405b(str2);
        audioEffectCache.m4402c(str);
        audioEffectCache.m4414a(audioEffectItem.getStyle());
        audioEffectCache.m4407b(audioEffectItem.getOutput());
        audioEffectCache.m4399d(audioEffectItem.getDevice());
        audioEffectCache.m4403c(audioEffectItem.getTotal());
        audioEffectCache.m4400d(audioEffectItem.getPickCount());
        audioEffectCache.m4393f(audioEffectItem.getNickName());
        audioEffectCache.m4396e(audioEffectItem.getPic());
        audioEffectCache.m4413a(audioEffectItem.getUserId());
        audioEffectCache.m4397e(audioEffectItem.getDataBass());
        audioEffectCache.m4394f(audioEffectItem.getDataTreble());
        audioEffectCache.m4391g(audioEffectItem.getDataVirtualizer());
        audioEffectCache.m4388h(audioEffectItem.getDataReverb());
        audioEffectCache.m4415a(audioEffectItem.getDataBalance());
        audioEffectCache.m4410a(audioEffectItem.getDataIsLimit());
        audioEffectCache.m4409a(audioEffectItem.getDataEqualizer());
        audioEffectCache.m4406b(System.currentTimeMillis());
        FileUtils.m8416a(JSONUtils.toJson(audioEffectCache), m4340a);
    }

    /* renamed from: e */
    private int m2737e(int i) {
        if (i > Preferences.m2970af() - 1) {
            return 0;
        }
        return i;
    }
}
