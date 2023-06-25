package com.sds.android.ttpod.component.p087d.p088a;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AudioEffectUser;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectCache;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectID;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.modules.core.audioeffect.EqualizerPreset;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.audiofx.TTEqualizer;
import com.sds.android.ttpod.media.mediastore.MediaItem;

/* renamed from: com.sds.android.ttpod.component.d.a.c */
/* loaded from: classes.dex */
public class EffectShareDialog extends BaseDialog {

    /* renamed from: a */
    private View f3890a;

    /* renamed from: b */
    private CheckedTextView f3891b;

    /* renamed from: c */
    private TextView f3892c;

    /* renamed from: d */
    private MediaItem f3893d;

    /* renamed from: e */
    private int f3894e;

    /* renamed from: f */
    private int f3895f;

    /* renamed from: g */
    private String f3896g;

    /* renamed from: h */
    private short[] f3897h;

    /* renamed from: i */
    private int f3898i;

    /* renamed from: j */
    private int f3899j;

    /* renamed from: k */
    private float f3900k;

    /* renamed from: l */
    private int f3901l;

    /* renamed from: m */
    private boolean f3902m;

    /* renamed from: n */
    private int f3903n;

    /* renamed from: o */
    private AudioEffectUser f3904o;

    /* renamed from: p */
    private boolean f3905p;

    public EffectShareDialog(Context context, MediaItem mediaItem, AudioEffectUser audioEffectUser, boolean z) {
        super(context);
        this.f3894e = -1;
        this.f3895f = 0;
        this.f3896g = "";
        this.f3897h = new short[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.f3898i = 0;
        this.f3899j = 0;
        this.f3900k = 0.0f;
        this.f3901l = 0;
        this.f3902m = false;
        this.f3903n = 0;
        this.f3893d = mediaItem;
        this.f3904o = audioEffectUser;
        this.f3905p = z;
        m6890b();
        m6887e();
        m6885g();
        m7261a(R.string.save, new BaseDialog.InterfaceC1064a<MediaInfoEditDialog>() { // from class: com.sds.android.ttpod.component.d.a.c.1
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MediaInfoEditDialog mediaInfoEditDialog) {
                EffectShareDialog.this.m6888c();
            }
        }, R.string.cancel, (BaseDialog.InterfaceC1064a) null);
        this.f3896g = Build.MODEL;
    }

    /* renamed from: b */
    private void m6890b() {
        AudioEffectParam m2457s = SupportFactory.m2397a(BaseApplication.getApplication()).m2457s();
        if (m2457s != null) {
            this.f3894e = EqualizerPreset.m4335a(new TTEqualizer.Settings(m2457s.m4421g()).getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m6888c() {
        Boolean bool;
        String title = this.f3893d.getTitle();
        String artist = this.f3893d.getArtist();
        if (this.f3893d != null) {
            if (this.f3894e == -1) {
                m7242f(false);
                PopupsUtils.m6721a("没有选择音乐类型!");
                return;
            }
            m7242f(true);
            TTPodUser m2954aq = Preferences.m2954aq();
            if (m2954aq != null && this.f3904o != null && this.f3904o.getAllowAdd() && this.f3891b.getVisibility() == View.VISIBLE && this.f3891b.isChecked()) {
                bool = true;
            } else {
                bool = false;
            }
            String m4379a = new AudioEffectID(m2954aq == null ? 0L : m2954aq.getUserId(), title, artist, this.f3897h, this.f3898i, this.f3899j, this.f3901l, this.f3903n, this.f3900k, this.f3902m).m4379a();
            AudioEffectCache audioEffectCache = new AudioEffectCache();
            audioEffectCache.m4411a(m4379a);
            audioEffectCache.m4412a(this.f3893d.getSongID());
            audioEffectCache.m4405b(artist);
            audioEffectCache.m4402c(title);
            audioEffectCache.m4414a(this.f3894e);
            audioEffectCache.m4407b(this.f3895f);
            audioEffectCache.m4399d(this.f3896g);
            audioEffectCache.m4393f(getContext().getString(R.string.me));
            audioEffectCache.m4397e(this.f3898i);
            audioEffectCache.m4394f(this.f3899j);
            audioEffectCache.m4391g(this.f3901l);
            audioEffectCache.m4388h(this.f3903n);
            audioEffectCache.m4415a(this.f3900k);
            audioEffectCache.m4410a(this.f3902m);
            audioEffectCache.m4409a(this.f3897h);
            audioEffectCache.m4406b(System.currentTimeMillis());
            audioEffectCache.m4390g(this.f3893d.getLocalDataSource());
            LogUtils.debug("EffectShareDialog", "saveToLocal " + audioEffectCache);
            CommandCenter.getInstance().m4596b(new Command(CommandID.SAVE_EFFECT, this.f3893d, audioEffectCache, bool));
            //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_AJUST_SAVE_OK, SPage.PAGE_NONE, SPage.PAGE_NONE);
            //AudioEffectStatistic.m5250v();
        }
    }

    /* renamed from: e */
    private void m6887e() {
        AudioEffectParam m2457s = SupportFactory.m2397a(BaseApplication.getApplication()).m2457s();
        if (m2457s != null) {
            this.f3897h = new TTEqualizer.Settings(m2457s.m4421g()).getBandLevels();
            this.f3900k = m2457s.m4426e();
            this.f3898i = m2457s.m4448a();
            this.f3899j = m2457s.m4439b();
            this.f3901l = m2457s.m4433c();
            this.f3903n = m2457s.m4429d();
            this.f3902m = m2457s.m4423f();
        }
    }

    /* renamed from: f */
    private boolean m6886f() {
        for (int length = this.f3897h.length - 1; length >= 0; length--) {
            if (this.f3897h[length] != 0) {
                return true;
            }
        }
        return (this.f3898i == 0 && this.f3899j == 0 && this.f3900k == 0.0f && this.f3901l == 0 && this.f3903n == 0) ? false : true;
    }

    /* renamed from: g */
    private void m6885g() {
        int i;
        int i2 = 8;
        this.f3891b = (CheckedTextView) this.f3890a.findViewById(R.id.checked_share);
        this.f3892c = (TextView) this.f3890a.findViewById(R.id.checked_share_noajust);
        if (this.f3904o == null || !this.f3904o.getAllowAdd()) {
            i = 8;
        } else if (m6886f()) {
            this.f3891b.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.d.a.c.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    EffectShareDialog.this.f3891b.toggle();
                }
            });
            i = 8;
            i2 = 0;
        } else {
            i = 0;
        }
        this.f3891b.setVisibility(i2);
        this.f3892c.setVisibility(i);
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: a */
    protected View mo2034a(Context context) {
        this.f3890a = View.inflate(context, R.layout.dialog_effect_share, null);
        setTitle(R.string.effect_share_dialog_title);
        return this.f3890a;
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: a */
    protected <T> T mo2037a() {
        return null;
    }
}
