package com.sds.android.ttpod.framework.modules.skin.view;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.core.view.ViewCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric;
import com.sds.android.ttpod.framework.modules.skin.p132d.Lyric;
import com.sds.android.ttpod.framework.modules.skin.p132d.LyricInfo;
import com.sds.android.ttpod.framework.modules.skin.p132d.OnMeasureTextListener;
import com.sds.android.ttpod.framework.modules.skin.p132d.Sentence;
import java.io.File;

@SuppressLint({"HandlerLeak"})
/* loaded from: classes.dex */
public class LyricView extends View implements OnMeasureTextListener {

    /* renamed from: aD */
    private static int f6733aD = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4.0f, Resources.getSystem().getDisplayMetrics());

    /* renamed from: A */
    private int f6734A;

    /* renamed from: B */
    private int f6735B;

    /* renamed from: C */
    private int f6736C;

    /* renamed from: D */
    private int f6737D;

    /* renamed from: E */
    private int f6738E;

    /* renamed from: F */
    private int f6739F;

    /* renamed from: G */
    private int f6740G;

    /* renamed from: H */
    private int f6741H;

    /* renamed from: I */
    private int f6742I;

    /* renamed from: J */
    private int f6743J;

    /* renamed from: K */
    private int f6744K;

    /* renamed from: L */
    private int f6745L;

    /* renamed from: M */
    private int f6746M;

    /* renamed from: N */
    private int f6747N;

    /* renamed from: O */
    private int f6748O;

    /* renamed from: P */
    private int f6749P;

    /* renamed from: Q */
    private boolean f6750Q;

    /* renamed from: R */
    private Rect f6751R;

    /* renamed from: S */
    private Rect f6752S;

    /* renamed from: T */
    private int f6753T;

    /* renamed from: U */
    private int f6754U;

    /* renamed from: V */
    private Rect f6755V;

    /* renamed from: W */
    private Rect f6756W;

    /* renamed from: Z */
    private boolean f6757Z;

    /* renamed from: a */
    private Context f6758a;

    /* renamed from: aA */
    private int f6759aA;

    /* renamed from: aB */
    private int f6760aB;

    /* renamed from: aC */
    private InterfaceC1998c f6761aC;

    /* renamed from: aE */
    private int f6762aE;

    /* renamed from: aF */
    private boolean f6763aF;

    /* renamed from: aG */
    private boolean f6764aG;

    /* renamed from: aH */
    private Handler f6765aH;

    /* renamed from: aI */
    private TextPaint f6766aI;

    /* renamed from: aJ */
    private boolean f6767aJ;

    /* renamed from: aK */
    private BroadcastReceiver f6768aK;

    /* renamed from: aa */
    private int f6769aa;

    /* renamed from: ab */
    private int f6770ab;

    /* renamed from: ac */
    private int f6771ac;

    /* renamed from: ad */
    private Bitmap f6772ad;

    /* renamed from: ae */
    private Bitmap f6773ae;

    /* renamed from: af */
    private Bitmap f6774af;

    /* renamed from: ag */
    private Bitmap f6775ag;

    /* renamed from: ah */
    private int f6776ah;

    /* renamed from: ai */
    private int f6777ai;

    /* renamed from: aj */
    private boolean f6778aj;

    /* renamed from: ak */
    private boolean f6779ak;

    /* renamed from: al */
    private boolean f6780al;

    /* renamed from: am */
    private boolean f6781am;

    /* renamed from: an */
    private int f6782an;

    /* renamed from: ao */
    private int f6783ao;

    /* renamed from: ap */
    private int f6784ap;

    /* renamed from: aq */
    private int f6785aq;

    /* renamed from: ar */
    private LinearGradient f6786ar;

    /* renamed from: as */
    private LinearGradient f6787as;

    /* renamed from: at */
    private LinearGradient f6788at;

    /* renamed from: au */
    private LinearGradient f6789au;

    /* renamed from: av */
    private LinearGradient f6790av;

    /* renamed from: aw */
    private String[] f6791aw;

    /* renamed from: ax */
    private boolean f6792ax;

    /* renamed from: ay */
    private int f6793ay;

    /* renamed from: az */
    private int f6794az;

    /* renamed from: b */
    private boolean f6795b;

    /* renamed from: c */
    private boolean f6796c;

    /* renamed from: d */
    private boolean f6797d;

    /* renamed from: e */
    private boolean f6798e;

    /* renamed from: f */
    private Paint.Align f6799f;

    /* renamed from: g */
    private EnumC1996a f6800g;

    /* renamed from: h */
    private Lyric f6801h;

    /* renamed from: i */
    private FormattedLyric f6802i;

    /* renamed from: j */
    private int f6803j;

    /* renamed from: k */
    private String f6804k;

    /* renamed from: l */
    private String f6805l;

    /* renamed from: m */
    private long f6806m;

    /* renamed from: n */
    private int f6807n;

    /* renamed from: o */
    private int f6808o;

    /* renamed from: p */
    private float f6809p;

    /* renamed from: q */
    private float f6810q;

    /* renamed from: r */
    private Typeface f6811r;

    /* renamed from: s */
    private Typeface f6812s;

    /* renamed from: t */
    private int f6813t;

    /* renamed from: u */
    private float f6814u;

    /* renamed from: v */
    private float f6815v;

    /* renamed from: w */
    private TextPaint f6816w;

    /* renamed from: x */
    private TextPaint f6817x;

    /* renamed from: y */
    private int f6818y;

    /* renamed from: z */
    private TextPaint f6819z;

    /* renamed from: com.sds.android.ttpod.framework.modules.skin.view.LyricView$c */
    /* loaded from: classes.dex */
    public interface InterfaceC1998c {
        /* renamed from: a */
        void mo3409a(int i);

        /* renamed from: a */
        void mo3408a(long j);
    }

    /* renamed from: b */
    static /* synthetic */ int m3456b(LyricView lyricView) {
        int i = lyricView.f6762aE + 1;
        lyricView.f6762aE = i;
        return i;
    }

    public void setMtvStroke(boolean z) {
        this.f6779ak = z;
    }

    public void setMtvGradient(boolean z) {
        this.f6780al = z;
    }

    public void setColorStrokeNormal(int i) {
        this.f6770ab = i;
    }

    /* renamed from: b */
    public boolean m3462b() {
        return this.f6763aF;
    }

    /* renamed from: c */
    public void m3451c() {
        this.f6763aF = true;
    }

    /* renamed from: d */
    public boolean m3443d() {
        return this.f6764aG;
    }

    public void setColorBySkin(boolean z) {
        this.f6764aG = z;
    }

    public int getFadeEdgeLength() {
        return this.f6754U;
    }

    public void setFadeEdgeLength(int i) {
        this.f6754U = i;
    }

    public void setMtvPostionDown(boolean z) {
        this.f6781am = z;
    }

    /* renamed from: a */
    public int m3483a(int i) {
        if (this.f6801h == null || this.f6802i == null) {
            return 0;
        }
        int mo3674a = this.f6801h.mo3674a(i);
        m3444c(true);
        return mo3674a;
    }

    /* renamed from: e */
    public int m3437e() {
        if (this.f6801h == null) {
            return 0;
        }
        return (int) (this.f6801h.mo3670e() - this.f6801h.mo3671d());
    }

    /* renamed from: a */
    public boolean m3465a(boolean z) {
        if (this.f6801h != null) {
            return this.f6801h.mo3673a(z);
        }
        return true;
    }

    /* renamed from: f */
    public void m3434f() {
        if (this.f6801h != null && this.f6802i != null) {
            this.f6801h.mo3675a();
            m3444c(true);
        }
    }

    /* renamed from: g */
    public void m3431g() {
        m3444c(true);
    }

    @Override // android.view.View
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append('{');
        sb.append(System.identityHashCode(this));
        sb.append(' ');
        switch (getVisibility()) {
            case 0:
                sb.append('V');
                break;
            case 4:
                sb.append('I');
                break;
            case 8:
                sb.append('G');
                break;
            default:
                sb.append('.');
                break;
        }
        sb.append(' ');
        sb.append(getLeft());
        sb.append(',');
        sb.append(getTop());
        sb.append('-');
        sb.append(getRight());
        sb.append(',');
        sb.append(getBottom());
        sb.append("}");
        return sb.toString();
    }

    public int getState() {
        return this.f6803j;
    }

    public void setState(int i) {
        if (this.f6803j != i && i != 0) {
            this.f6803j = i;
            this.f6765aH.removeMessages(3);
            this.f6765aH.removeMessages(4);
            this.f6765aH.sendMessageDelayed(this.f6765aH.obtainMessage(3, i, 0), 0L);
        }
    }

    /* renamed from: com.sds.android.ttpod.framework.modules.skin.view.LyricView$a */
    /* loaded from: classes.dex */
    public enum EnumC1996a {
        Normal(0),
        MTV(1),
        Single(2);

        EnumC1996a(int i) {
        }
    }

    public void setSlowScroll(boolean z) {
        if (z != this.f6796c) {
            this.f6796c = z;
            m3444c(true);
        }
    }

    public void setFadeColor(boolean z) {
        if (z != this.f6797d) {
            this.f6797d = z;
            m3444c(true);
        }
    }

    public void setKalaOK(boolean z) {
        if (z != this.f6798e) {
            this.f6798e = z;
            m3444c(true);
        }
    }

    public Paint.Align getAlign() {
        return this.f6799f;
    }

    public void setAlign(Paint.Align align) {
        if (align != this.f6799f) {
            this.f6799f = align;
            m3438d(true);
        }
    }

    public EnumC1996a getDisplayMode() {
        return this.f6800g;
    }

    public void setDisplayMode(EnumC1996a enumC1996a) {
        if (enumC1996a != this.f6800g) {
            this.f6800g = enumC1996a;
            if (this.f6800g != EnumC1996a.MTV) {
                m3422l();
            } else {
                this.f6816w.setTextAlign(Paint.Align.LEFT);
                this.f6817x.setTextAlign(Paint.Align.LEFT);
            }
            m3423k();
        }
    }

    public LyricView(Context context) {
        super(context);
        this.f6803j = 1;
        this.f6813t = -256;
        this.f6814u = 0.0f;
        this.f6815v = 0.0f;
        this.f6751R = new Rect();
        this.f6752S = new Rect();
        this.f6755V = new Rect();
        this.f6756W = new Rect();
        this.f6779ak = true;
        this.f6780al = true;
        this.f6791aw = new String[]{"", "●", "●●", "●●●", "●●●●", "●●●●●"};
        this.f6792ax = false;
        this.f6765aH = new Handler(Looper.getMainLooper()) { // from class: com.sds.android.ttpod.framework.modules.skin.view.LyricView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        LyricView.this.m3461b(message.arg1);
                        return;
                    case 2:
                        try {
                            new AsyncTaskC1997b(LyricView.this.f6801h, LyricView.m3456b(LyricView.this)).execute(new Void[0]);
                            return;
                        } catch (NoClassDefFoundError e) {
                            LogUtils.debug("LyricView", "lyric format error: no class def found, reason:" + e.toString());
                            return;
                        }
                    case 3:
                        LyricView.this.f6803j = message.arg1;
                        LyricView.this.f6801h = null;
                        LyricView.this.f6802i = null;
                        switch (LyricView.this.f6803j) {
                            case 1:
                                LyricView.this.f6804k = LyricView.this.f6805l;
                                break;
                            case 2:
                                LyricView.this.f6804k = LyricView.this.f6758a.getString(R.string.lyric_searching);
                                break;
                            case 3:
                                LyricView.this.f6804k = LyricView.this.f6758a.getString(R.string.lyric_search_failed);
                                break;
                            case 4:
                                LyricView.this.f6804k = LyricView.this.f6758a.getString(R.string.lyric_downloading);
                                break;
                            case 5:
                                LyricView.this.f6804k = LyricView.this.f6758a.getString(R.string.lyric_download_failed);
                                break;
                            case 6:
                                LyricView.this.f6804k = LyricView.this.f6758a.getString(R.string.lyric_network_error);
                                break;
                            case 7:
                                LyricView.this.f6804k = LyricView.this.f6758a.getString(R.string.lyric_file_not_support);
                                break;
                            case 8:
                                LyricView.this.f6804k = LyricView.this.f6758a.getString(R.string.lyric_server_no_resource);
                                break;
                            default:
                                LyricView.this.f6804k = "unknow state";
                                break;
                        }
                        LyricView.this.m3452b(true);
                        LyricView.this.m3452b(false);
                        LyricView.this.m3438d(true);
                        return;
                    case 4:
                        LyricView.this.f6801h = (Lyric) message.obj;
                        LyricView.this.f6802i = null;
                        LyricView.this.f6803j = 0;
                        LyricView.this.f6804k = LyricView.this.f6805l;
                        LyricView.this.m3423k();
                        return;
                    case 5:
                        LyricView.this.m3444c(false);
                        return;
                    default:
                        return;
                }
            }
        };
        this.f6766aI = new TextPaint();
        this.f6766aI.setColor(-1);
        this.f6766aI.setStyle(Paint.Style.STROKE);
        this.f6767aJ = false;
        this.f6768aK = new BroadcastReceiver() { // from class: com.sds.android.ttpod.framework.modules.skin.view.LyricView.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    LyricView.this.f6795b = true;
                } else if ("android.intent.action.SCREEN_ON".equals(action)) {
                    LyricView.this.f6795b = false;
                }
            }
        };
        this.f6758a = context;
        m3425j();
    }

    /* renamed from: j */
    private void m3425j() {
        float m3449c = m3449c(2, 16.0f);
        this.f6815v = m3449c;
        this.f6814u = m3449c;
        this.f6809p = m3449c;
        this.f6810q = m3449c;
        this.f6771ac = 0;
        this.f6770ab = -1342177280;
        this.f6796c = true;
        this.f6797d = true;
        this.f6798e = false;
        this.f6799f = Paint.Align.CENTER;
        this.f6800g = EnumC1996a.Normal;
        this.f6807n = -1;
        this.f6808o = this.f6813t;
        this.f6816w = new TextPaint();
        this.f6816w.setAntiAlias(true);
        this.f6817x = new TextPaint();
        this.f6817x.setAntiAlias(true);
        setEnabled(false);
        this.f6805l = this.f6758a.getString(R.string.lyric_ttpod);
        this.f6804k = this.f6805l;
        int scaledTouchSlop = ViewConfiguration.get(this.f6758a).getScaledTouchSlop();
        this.f6818y = scaledTouchSlop * scaledTouchSlop;
        int m3449c2 = (int) m3449c(1, 1.0f);
        this.f6817x.setStrokeCap(Paint.Cap.ROUND);
        this.f6817x.setStrokeJoin(Paint.Join.ROUND);
        this.f6817x.setStrokeWidth(m3449c2);
        this.f6816w.setStrokeCap(Paint.Cap.ROUND);
        this.f6816w.setStrokeJoin(Paint.Join.ROUND);
        this.f6816w.setStrokeWidth(m3449c2);
    }

    public LyricView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LyricView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6803j = 1;
        this.f6813t = -256;
        this.f6814u = 0.0f;
        this.f6815v = 0.0f;
        this.f6751R = new Rect();
        this.f6752S = new Rect();
        this.f6755V = new Rect();
        this.f6756W = new Rect();
        this.f6779ak = true;
        this.f6780al = true;
        this.f6791aw = new String[]{"", "●", "●●", "●●●", "●●●●", "●●●●●"};
        this.f6792ax = false;
        this.f6765aH = new Handler(Looper.getMainLooper()) { // from class: com.sds.android.ttpod.framework.modules.skin.view.LyricView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        LyricView.this.m3461b(message.arg1);
                        return;
                    case 2:
                        try {
                            new AsyncTaskC1997b(LyricView.this.f6801h, LyricView.m3456b(LyricView.this)).execute(new Void[0]);
                            return;
                        } catch (NoClassDefFoundError e) {
                            LogUtils.debug("LyricView", "lyric format error: no class def found, reason:" + e.toString());
                            return;
                        }
                    case 3:
                        LyricView.this.f6803j = message.arg1;
                        LyricView.this.f6801h = null;
                        LyricView.this.f6802i = null;
                        switch (LyricView.this.f6803j) {
                            case 1:
                                LyricView.this.f6804k = LyricView.this.f6805l;
                                break;
                            case 2:
                                LyricView.this.f6804k = LyricView.this.f6758a.getString(R.string.lyric_searching);
                                break;
                            case 3:
                                LyricView.this.f6804k = LyricView.this.f6758a.getString(R.string.lyric_search_failed);
                                break;
                            case 4:
                                LyricView.this.f6804k = LyricView.this.f6758a.getString(R.string.lyric_downloading);
                                break;
                            case 5:
                                LyricView.this.f6804k = LyricView.this.f6758a.getString(R.string.lyric_download_failed);
                                break;
                            case 6:
                                LyricView.this.f6804k = LyricView.this.f6758a.getString(R.string.lyric_network_error);
                                break;
                            case 7:
                                LyricView.this.f6804k = LyricView.this.f6758a.getString(R.string.lyric_file_not_support);
                                break;
                            case 8:
                                LyricView.this.f6804k = LyricView.this.f6758a.getString(R.string.lyric_server_no_resource);
                                break;
                            default:
                                LyricView.this.f6804k = "unknow state";
                                break;
                        }
                        LyricView.this.m3452b(true);
                        LyricView.this.m3452b(false);
                        LyricView.this.m3438d(true);
                        return;
                    case 4:
                        LyricView.this.f6801h = (Lyric) message.obj;
                        LyricView.this.f6802i = null;
                        LyricView.this.f6803j = 0;
                        LyricView.this.f6804k = LyricView.this.f6805l;
                        LyricView.this.m3423k();
                        return;
                    case 5:
                        LyricView.this.m3444c(false);
                        return;
                    default:
                        return;
                }
            }
        };
        this.f6766aI = new TextPaint();
        this.f6766aI.setColor(-1);
        this.f6766aI.setStyle(Paint.Style.STROKE);
        this.f6767aJ = false;
        this.f6768aK = new BroadcastReceiver() { // from class: com.sds.android.ttpod.framework.modules.skin.view.LyricView.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    LyricView.this.f6795b = true;
                } else if ("android.intent.action.SCREEN_ON".equals(action)) {
                    LyricView.this.f6795b = false;
                }
            }
        };
        this.f6758a = context;
        m3425j();
    }

    public void setLyric(Lyric lyric) {
        if (this.f6803j != 0 || lyric == null || !lyric.equals(this.f6801h)) {
            this.f6806m = 0L;
            this.f6765aH.removeMessages(3);
            this.f6765aH.removeMessages(4);
            this.f6765aH.sendMessageDelayed(this.f6765aH.obtainMessage(4, lyric), 0L);
        }
    }

    /* renamed from: h */
    public boolean m3429h() {
        return this.f6801h != null && this.f6801h.mo3672b() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m3452b(boolean z) {
        if (z) {
            if (this.f6772ad != null) {
                this.f6772ad.recycle();
                this.f6772ad = null;
            }
            if (this.f6773ae != null) {
                this.f6773ae.recycle();
                this.f6773ae = null;
                return;
            }
            return;
        }
        if (this.f6774af != null) {
            this.f6774af.recycle();
            this.f6774af = null;
        }
        if (this.f6775ag != null) {
            this.f6775ag.recycle();
            this.f6775ag = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public void m3423k() {
        if (getWidth() != 0 && getHeight() != 0) {
            this.f6746M = -1;
            m3452b(true);
            m3452b(false);
            if (this.f6800g == EnumC1996a.MTV) {
                m3415s();
            } else {
                m3417q();
            }
            if (this.f6801h != null) {
                this.f6803j = 0;
                this.f6765aH.removeMessages(2);
                this.f6765aH.sendEmptyMessage(2);
                return;
            }
            m3444c(true);
        }
    }

    public Lyric getLyric() {
        return this.f6801h;
    }

    /* renamed from: l */
    private void m3422l() {
        this.f6786ar = null;
        this.f6787as = null;
        this.f6788at = null;
        this.f6789au = null;
        this.f6790av = null;
    }

    /* renamed from: m */
    private void m3421m() {
        int i = this.f6739F >> 3;
        this.f6790av = null;
        int i2 = this.f6779ak ? i : this.f6755V.top + i;
        int i3 = this.f6779ak ? this.f6739F - i : this.f6755V.bottom - i;
        this.f6786ar = new LinearGradient(0.0f, i2, 0.0f, i3, this.f6782an, this.f6807n, Shader.TileMode.CLAMP);
        this.f6787as = new LinearGradient(0.0f, i2, 0.0f, i3, this.f6783ao, this.f6808o, Shader.TileMode.CLAMP);
        int i4 = this.f6779ak ? i : this.f6756W.top + i;
        int i5 = this.f6779ak ? this.f6739F - i : this.f6756W.bottom - i;
        this.f6788at = new LinearGradient(0.0f, i4, 0.0f, i5, this.f6782an, this.f6807n, Shader.TileMode.CLAMP);
        this.f6789au = new LinearGradient(0.0f, i4, 0.0f, i5, this.f6783ao, this.f6808o, Shader.TileMode.CLAMP);
        this.f6776ah = -1;
        this.f6777ai = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m3444c(boolean z) {
        boolean z2 = true;
        if (this.f6802i != null) {
            if (z) {
                this.f6750Q = true;
            }
            if (this.f6800g == EnumC1996a.MTV) {
                if (z) {
                    m3421m();
                }
                z2 = m3420n();
            } else {
                z2 = m3419o();
            }
        } else if (z && this.f6800g == EnumC1996a.MTV) {
            this.f6790av = null;
        }
        if (z2) {
            m3438d(z);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00a6  */
    /* renamed from: n */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean m3420n() {
        boolean z = false;
        int mo3628a = this.f6802i.mo3628a(this.f6806m);
        if (mo3628a < 0) {
            this.f6742I = 0;
            this.f6743J = 1;
            this.f6784ap = -1;
            this.f6785aq = -1;
            if (this.f6746M != 0) {
                this.f6746M = 0;
                this.f6757Z = true;
                return true;
            }
            return false;
        }
        this.f6757Z = (mo3628a & 1) == 0;
        Sentence mo3629a = this.f6802i.mo3629a(mo3628a);
        int mo3636f = mo3629a.mo3636f();
        int mo3637e = (int) (this.f6806m - mo3629a.mo3637e());
        int mo3611c = mo3629a.mo3611c(mo3637e);
        if (mo3629a.mo3635g().length() == 0 && mo3636f >= 7000) {
            int i = ((mo3636f - mo3637e) / 1000) + 1;
            if (i >= this.f6791aw.length) {
                i = 0;
            }
            if (this.f6757Z && i != this.f6784ap) {
                this.f6784ap = i;
                this.f6776ah = -1;
                z = true;
            } else if (!this.f6757Z && i != this.f6785aq) {
                this.f6785aq = i;
                this.f6777ai = -1;
                z = true;
            }
            if (mo3611c == 0 && this.f6746M == mo3611c) {
                return z;
            }
            this.f6746M = mo3611c;
            m3464a(true, mo3628a);
            if ((mo3636f < 1000 && mo3637e >= 500) || (mo3636f < 1000 && mo3637e >= (mo3636f >> 1))) {
                m3464a(false, mo3628a + 1);
                return true;
            } else if (!this.f6757Z) {
                this.f6743J = mo3628a <= 2 ? 1 : mo3628a - 1;
                return true;
            } else {
                this.f6742I = mo3628a > 2 ? mo3628a - 1 : 0;
                return true;
            }
        }
        this.f6784ap = -1;
        this.f6785aq = -1;
        z = false;
        if (mo3611c == 0) {
        }
        this.f6746M = mo3611c;
        m3464a(true, mo3628a);
        if (mo3636f < 1000) {
        }
        if (!this.f6757Z) {
        }
        return z;
    }

    /* renamed from: a */
    private void m3464a(boolean z, int i) {
        if (z) {
            if (this.f6757Z) {
                this.f6742I = i;
            } else {
                this.f6743J = i;
            }
        } else if (this.f6757Z) {
            this.f6743J = i;
        } else {
            this.f6742I = i;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00b5  */
    /* renamed from: o */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean m3419o() {
        int mo3637e;
        int i;
        int i2;
        boolean z;
        int mo3628a = this.f6802i.mo3628a(this.f6806m);
        this.f6742I = mo3628a;
        this.f6745L = 0;
        Sentence mo3629a = this.f6802i.mo3629a(mo3628a);
        int i3 = (int) this.f6806m;
        if (mo3628a >= 0) {
            int mo3638b = mo3629a.mo3638b();
            mo3637e = mo3629a.mo3636f();
            i = (int) (this.f6806m - mo3629a.mo3637e());
            i2 = mo3638b;
        } else {
            mo3637e = (int) this.f6802i.mo3629a(0).mo3637e();
            i = i3;
            i2 = -1;
        }
        if (mo3637e < 1) {
            mo3637e = 1;
        }
        if (this.f6796c) {
            this.f6747N = this.f6735B - ((this.f6739F * i) / mo3637e);
        } else if (i >= 0 && i < 1000) {
            this.f6747N = (this.f6735B + this.f6738E) - ((this.f6739F * i) / (mo3637e < 1000 ? mo3637e : 1000));
        } else {
            this.f6747N = this.f6735B - this.f6738E;
        }
        int i4 = this.f6737D >> 1;
        int i5 = this.f6747N;
        while (i4 > 0 && this.f6742I > 0) {
            int i6 = i5 - this.f6739F;
            this.f6742I--;
            i4--;
            if (i2 == this.f6802i.mo3629a(this.f6742I).mo3638b()) {
                this.f6745L++;
                i5 = i6;
            } else {
                i5 = i6;
            }
        }
        boolean z2 = this.f6750Q;
        if (this.f6798e) {
            int mo3611c = mo3629a == null ? 0 : mo3629a.mo3611c(i);
            if (mo3611c != this.f6746M) {
                this.f6746M = mo3611c;
                z = true;
                if (this.f6744K == i5 || z) {
                    if (this.f6742I >= 0) {
                        if (this.f6797d && i >= 0 && i < 1000) {
                            if (mo3637e >= 1000) {
                                mo3637e = 1000;
                            }
                            m3485a(i / mo3637e);
                        } else {
                            this.f6748O = this.f6808o;
                            if (this.f6797d && this.f6800g == EnumC1996a.Single) {
                                this.f6749P = 0;
                            } else {
                                this.f6749P = this.f6807n;
                            }
                        }
                    }
                    this.f6744K = i5;
                    return true;
                }
                return z;
            }
        }
        z = z2;
        if (this.f6744K == i5) {
        }
        if (this.f6742I >= 0) {
        }
        this.f6744K = i5;
        return true;
    }

    /* renamed from: a */
    private void m3485a(float f) {
        int alpha = Color.alpha(this.f6808o);
        int red = Color.red(this.f6808o);
        int green = Color.green(this.f6808o);
        int blue = Color.blue(this.f6808o);
        if (this.f6800g == EnumC1996a.Single) {
            int i = (int) (255.0f * f);
            this.f6748O = Color.argb(i, red, green, blue);
            this.f6749P = Color.argb(255 - i, red, green, blue);
            return;
        }
        int alpha2 = Color.alpha(this.f6807n);
        int red2 = Color.red(this.f6807n);
        int green2 = Color.green(this.f6807n);
        int blue2 = Color.blue(this.f6807n);
        int abs = alpha == alpha2 ? 0 : (int) (Math.abs(alpha2 - alpha) * f);
        int abs2 = (int) (Math.abs(red2 - red) * f);
        int abs3 = (int) (Math.abs(green2 - green) * f);
        int abs4 = (int) (Math.abs(blue2 - blue) * f);
        if (this.f6798e) {
            this.f6748O = this.f6808o;
        } else {
            this.f6748O = Color.argb(m3481a(alpha2, alpha, abs), m3481a(red2, red, abs2), m3481a(green2, green, abs3), m3481a(blue2, blue, abs4));
        }
        this.f6749P = Color.argb(m3481a(alpha, alpha2, abs), m3481a(red, red2, abs2), m3481a(green, green2, abs3), m3481a(blue, blue2, abs4));
    }

    /* renamed from: a */
    private int m3481a(int i, int i2, int i3) {
        if (i > i2) {
            i3 = -i3;
        }
        return i + i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m3438d(boolean z) {
        if (z && this.f6800g == EnumC1996a.MTV) {
            this.f6777ai = -1;
            this.f6776ah = -1;
        }
        m3418p();
        postInvalidate();
    }

    /* renamed from: p */
    private void m3418p() {
        if (this.f6800g != EnumC1996a.MTV) {
            this.f6816w.setTextAlign(this.f6799f);
            this.f6817x.setTextAlign(this.f6799f);
        }
    }

    public void setTypefaceNormal(Typeface typeface) {
        if (this.f6811r != typeface) {
            this.f6811r = typeface;
            m3423k();
        }
    }

    public void setTypefaceHighlight(Typeface typeface) {
        if (this.f6812s != typeface) {
            this.f6812s = typeface;
            m3423k();
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.OnMeasureTextListener
    /* renamed from: a */
    public int mo3467a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 1;
        }
        return (int) (this.f6819z.measureText(str) + 0.96f);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.OnMeasureTextListener
    /* renamed from: a */
    public float mo3486a() {
        return Math.min(this.f6810q, this.f6809p);
    }

    public void setPlayingTime(long j) {
        if (!this.f6795b && j != this.f6806m && !this.f6792ax) {
            if (j < 10) {
                j = 10;
            }
            this.f6806m = j;
            if (this.f6802i != null && !this.f6765aH.hasMessages(5)) {
                this.f6765aH.sendEmptyMessage(5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m3461b(float f) {
        boolean z = true;
        float m3449c = m3449c(2, f);
        boolean z2 = false;
        if (m3449c != this.f6809p) {
            this.f6809p = m3449c;
            z2 = true;
        }
        if (m3449c != this.f6810q) {
            this.f6810q = m3449c;
        } else {
            z = z2;
        }
        if (z) {
            m3423k();
        }
    }

    public void setTextSize(float f) {
        this.f6765aH.removeMessages(1);
        this.f6765aH.sendMessage(this.f6765aH.obtainMessage(1, (int) f, 0));
    }

    public void setTextSizeNormal(float f) {
        m3482a(2, f);
    }

    /* renamed from: a */
    public void m3482a(int i, float f) {
        setTextSizeRawNormal(m3449c(i, f));
    }

    public void setTextSizeRawNormal(float f) {
        if (f != this.f6809p) {
            this.f6809p = f;
            m3423k();
        }
    }

    public void setTextSizeHighlight(float f) {
        m3459b(2, f);
    }

    /* renamed from: b */
    public void m3459b(int i, float f) {
        setTextSizeRawHighlight(m3449c(i, f));
    }

    public void setTextSizeRawHighlight(float f) {
        if (f != this.f6810q) {
            this.f6810q = f;
            m3423k();
        }
    }

    /* renamed from: c */
    private float m3449c(int i, float f) {
        return TypedValue.applyDimension(i, f, this.f6758a.getResources().getDisplayMetrics());
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (i != i3 || i2 != i4) {
            m3423k();
        }
    }

    /* renamed from: a */
    public void m3484a(float f, float f2, float f3, int i) {
        this.f6816w.setShadowLayer(f, f2, f3, i);
        this.f6817x.setShadowLayer(f, f2, f3, i);
        m3438d(true);
    }

    /* renamed from: q */
    private void m3417q() {
        m3416r();
        this.f6816w.setFakeBoldText(false);
        this.f6817x.setFakeBoldText(false);
        this.f6738E = this.f6739F >> 1;
        this.f6734A = getWidth() - 20;
        int height = getHeight();
        this.f6751R.left = 10;
        this.f6751R.right = this.f6751R.left + this.f6734A;
        this.f6736C = height >> 1;
        this.f6737D = (height / this.f6739F) + 2;
        this.f6735B = this.f6736C + this.f6739F;
        this.f6752S.left = this.f6751R.left;
        this.f6752S.bottom = this.f6736C - ((int) m3449c(1, 1.0f));
        this.f6752S.top = this.f6752S.bottom - this.f6739F;
        this.f6753T = mo3467a("00:00");
        this.f6752S.right = this.f6752S.left + 20 + this.f6753T;
    }

    /* renamed from: r */
    private void m3416r() {
        this.f6816w.setTypeface(this.f6811r == null ? this.f6812s : this.f6811r);
        this.f6817x.setTypeface(this.f6812s != null ? this.f6812s : this.f6811r);
        this.f6816w.setColor(this.f6807n);
        this.f6817x.setColor(this.f6808o);
        this.f6816w.setTextSize(this.f6809p);
        this.f6817x.setTextSize(this.f6810q);
        this.f6819z = this.f6810q >= this.f6809p ? this.f6817x : this.f6816w;
        Paint.FontMetrics fontMetrics = this.f6816w.getFontMetrics();
        Paint.FontMetrics fontMetrics2 = this.f6817x.getFontMetrics();
        int i = (int) (fontMetrics.bottom - fontMetrics.top);
        int i2 = (int) (fontMetrics2.bottom - fontMetrics2.top);
        this.f6739F = this.f6810q >= this.f6809p ? i2 : i;
        this.f6739F += this.f6739F >> 2;
        if (this.f6739F < 2) {
            this.f6739F = 2;
        }
        this.f6740G = ((this.f6739F - i) >> 1) + ((int) fontMetrics.bottom);
        this.f6741H = ((this.f6739F - i2) >> 1) + ((int) fontMetrics2.bottom);
        m3418p();
    }

    /* renamed from: s */
    private void m3415s() {
        m3416r();
        this.f6816w.setFakeBoldText(true);
        this.f6817x.setFakeBoldText(true);
        this.f6734A = getWidth() - 12;
        this.f6769aa = this.f6734A - (this.f6734A >> 2);
        int height = getHeight();
        int i = height - this.f6739F;
        this.f6756W.set(6, i, this.f6734A + 6, height);
        if (this.f6781am) {
            this.f6755V.set(6, i - this.f6739F, this.f6734A + 6, i);
        } else {
            this.f6755V.set(6, 0, this.f6734A + 6, this.f6739F);
        }
        this.f6751R.set(6, 0, this.f6734A + 6, height);
        this.f6777ai = -1;
        this.f6776ah = -1;
        this.f6784ap = -1;
        this.f6785aq = -1;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        m3413u();
        if (this.f6802i == null) {
            m3458b(canvas);
        } else {
            int save = canvas.save();
            if (this.f6800g == EnumC1996a.MTV) {
                m3441d(canvas);
            } else {
                m3448c(canvas);
                if (this.f6792ax && this.f6800g == EnumC1996a.Normal) {
                    m3480a(canvas);
                }
            }
            canvas.restoreToCount(save);
        }
        this.f6750Q = false;
    }

    /* renamed from: a */
    private void m3480a(Canvas canvas) {
        canvas.drawLine(this.f6751R.left, this.f6736C, this.f6751R.right, this.f6736C, this.f6817x);
        this.f6817x.setColor(Color.argb(128, 0, 0, 0));
        canvas.drawRect(this.f6752S, this.f6817x);
        this.f6817x.setColor(this.f6808o);
        String curPlayTimeStr = getCurPlayTimeStr();
        this.f6817x.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(curPlayTimeStr, this.f6752S.centerX(), this.f6752S.bottom - this.f6741H, this.f6817x);
        m3418p();
    }

    private String getCurPlayTimeStr() {
        int i = (int) (this.f6806m / 1000);
        int i2 = i / 60;
        return String.format("%02d:%02d", Integer.valueOf(i2), Integer.valueOf(i - (i2 * 60)));
    }

    /* renamed from: b */
    private void m3458b(Canvas canvas) {
        int height = 0;
        Paint.FontMetrics fontMetrics = this.f6816w.getFontMetrics();
        this.f6751R.bottom = this.f6735B - this.f6738E;
        this.f6751R.top = this.f6751R.bottom - this.f6739F;
        if (this.f6800g == EnumC1996a.Single) {
            m3457b(canvas, this.f6805l, this.f6816w, this.f6807n, mo3467a(this.f6805l));
        } else if (this.f6800g == EnumC1996a.Normal) {
            if (m3414t()) {
                this.f6817x.setUnderlineText(true);
                this.f6817x.setTextAlign(Paint.Align.LEFT);
                m3477a(canvas, this.f6804k, this.f6817x, this.f6808o, mo3467a(this.f6804k));
                this.f6817x.setTextAlign(this.f6799f);
                this.f6817x.setUnderlineText(false);
                this.f6751R.bottom += this.f6738E;
                this.f6751R.top -= this.f6738E;
                return;
            }
            m3457b(canvas, this.f6804k, this.f6816w, this.f6807n, mo3467a(this.f6804k));
        } else {
            this.f6751R.set(6, 0, this.f6734A + 6, getHeight());
            int m3450c = m3450c(mo3467a(this.f6804k));
            this.f6816w.setColor(-1);
            int i = (int) ((this.f6751R.bottom - ((height - ((int) (fontMetrics.bottom - fontMetrics.top))) >> 1)) - this.f6816w.getFontMetrics().descent);
            if (this.f6790av == null) {
                int i2 = this.f6739F >> 3;
                this.f6790av = new LinearGradient(0.0f, (i - this.f6739F) + i2, 0.0f, i - i2, this.f6782an, this.f6807n, Shader.TileMode.CLAMP);
            }
            m3476a(canvas, this.f6804k, false, m3450c, i + this.f6740G, false, false);
        }
    }

    /* renamed from: t */
    private boolean m3414t() {
        return (this.f6803j == 1 || this.f6803j == 2 || this.f6803j == 4 || this.f6803j == 0 || this.f6803j == 8) ? false : true;
    }

    /* renamed from: a */
    private void m3477a(Canvas canvas, String str, TextPaint textPaint, int i, int i2) {
        int m3450c = m3450c(i2);
        textPaint.setColor(i);
        canvas.drawText(str, m3450c, this.f6751R.bottom - this.f6741H, textPaint);
    }

    /* renamed from: b */
    private void m3457b(Canvas canvas, String str, TextPaint textPaint, int i, int i2) {
        int i3 = textPaint == this.f6817x ? this.f6741H : this.f6740G;
        int m3460b = m3460b(i2);
        textPaint.setColor(i);
        canvas.drawText(str, m3460b, this.f6751R.bottom - i3, textPaint);
    }

    /* renamed from: a */
    private void m3478a(Canvas canvas, String str, int i) {
        int m3460b = m3460b(i);
        int m3450c = m3450c(i);
        canvas.save();
        canvas.clipRect(m3450c, this.f6751R.top, this.f6746M + m3450c, this.f6751R.bottom);
        this.f6817x.setColor(this.f6808o);
        canvas.drawText(str, m3460b, this.f6751R.bottom - this.f6741H, this.f6817x);
        canvas.restore();
        canvas.save();
        canvas.clipRect(m3450c + this.f6746M, this.f6751R.top, this.f6751R.right, this.f6751R.bottom);
        this.f6817x.setColor(this.f6807n);
        canvas.drawText(str, m3460b, this.f6751R.bottom - this.f6741H, this.f6817x);
        canvas.restore();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.framework.modules.skin.view.LyricView$3 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C19953 {

        /* renamed from: a */
        static final /* synthetic */ int[] f6822a = new int[Paint.Align.values().length];

        static {
            try {
                f6822a[Paint.Align.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f6822a[Paint.Align.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* renamed from: b */
    private int m3460b(int i) {
        int i2 = this.f6751R.left;
        switch (C19953.f6822a[this.f6799f.ordinal()]) {
            case 1:
                return i2 + (this.f6734A >> 1);
            case 2:
                return this.f6751R.right;
            default:
                return i2;
        }
    }

    /* renamed from: c */
    private int m3450c(int i) {
        int i2 = this.f6751R.left;
        switch (C19953.f6822a[this.f6799f.ordinal()]) {
            case 1:
                return i2 + ((this.f6734A - i) >> 1);
            case 2:
                return this.f6751R.right - i;
            default:
                return i2;
        }
    }

    /* renamed from: c */
    private void m3448c(Canvas canvas) {
        int i;
        int i2;
        TextPaint textPaint;
        int i3;
        int i4 = this.f6742I;
        int i5 = this.f6744K;
        int mo3630a = this.f6802i.mo3630a();
        int mo3625b = this.f6802i.mo3625b();
        if (mo3625b < 0) {
            i = -1;
        } else {
            i = this.f6802i.mo3629a(mo3625b).mo3638b();
        }
        if (this.f6754U <= 0 || this.f6800g != EnumC1996a.Normal) {
            i2 = 0;
        } else {
            i2 = canvas.saveLayer(this.f6751R.left, 0.0f, this.f6751R.right, getHeight(), null, Canvas.ALL_SAVE_FLAG);
        }
        int i6 = 0;
        while (i6 < this.f6737D && i4 < mo3630a) {
            if (i4 >= 0) {
                Sentence mo3629a = this.f6802i.mo3629a(i4);
                int mo3638b = mo3629a.mo3638b();
                String mo3635g = mo3629a.mo3635g();
                if (mo3635g.length() != 0) {
                    this.f6751R.bottom = i5;
                    this.f6751R.top = i5 - this.f6739F;
                    int i7 = this.f6807n;
                    int mo3640a = mo3629a.mo3640a();
                    if (i4 == mo3625b) {
                        if (this.f6798e) {
                            m3478a(canvas, mo3635g, mo3640a);
                        } else {
                            int i8 = this.f6748O;
                            if (this.f6800g == EnumC1996a.Normal && this.f6745L != 0) {
                                i8 = this.f6808o;
                            }
                            m3457b(canvas, mo3635g, this.f6817x, i8, mo3640a);
                        }
                    } else if (this.f6800g == EnumC1996a.Single) {
                        if (i4 == mo3625b - 1) {
                            m3457b(canvas, mo3635g, this.f6816w, this.f6749P, mo3640a);
                        }
                    } else {
                        TextPaint textPaint2 = this.f6816w;
                        if (mo3638b == i - 1) {
                            i7 = this.f6745L == 0 ? this.f6749P : this.f6807n;
                        }
                        if (mo3638b == i) {
                            if (this.f6798e) {
                                i3 = i4 < mo3625b ? this.f6808o : this.f6807n;
                            } else {
                                i3 = this.f6745L == 0 ? this.f6748O : this.f6808o;
                            }
                            textPaint = this.f6817x;
                            i7 = i3;
                        } else {
                            textPaint = this.f6816w;
                        }
                        m3457b(canvas, mo3635g, textPaint, i7, mo3640a);
                    }
                }
            }
            i6++;
            i5 = this.f6739F + i5;
            i4++;
        }
        if (this.f6754U > 0 && this.f6800g == EnumC1996a.Normal) {
            Paint paint = new Paint(1);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, this.f6754U, 0, (int) ViewCompat.MEASURED_STATE_MASK, Shader.TileMode.CLAMP));
            canvas.drawRect(this.f6751R.left, 0.0f, this.f6751R.right, this.f6754U, paint);
            int height = getHeight();
            paint.setShader(new LinearGradient(0.0f, height - this.f6754U, 0.0f, height, (int) ViewCompat.MEASURED_STATE_MASK, 0, Shader.TileMode.CLAMP));
            canvas.drawRect(this.f6751R.left, height - this.f6754U, this.f6751R.right, height, paint);
            paint.setXfermode(null);
            canvas.restoreToCount(i2);
        }
    }

    /* renamed from: d */
    private void m3441d(Canvas canvas) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = this.f6757Z;
        if (this.f6757Z || this.f6742I >= this.f6743J) {
            z = true;
        } else {
            z4 = true;
            z = false;
        }
        int mo3630a = this.f6802i.mo3630a();
        if (this.f6742I < mo3630a) {
            Sentence mo3629a = this.f6802i.mo3629a(this.f6742I);
            this.f6751R.set(this.f6755V);
            int mo3640a = mo3629a.mo3640a();
            int i = mo3640a - this.f6734A;
            if (this.f6784ap < 0) {
                if (this.f6757Z && i > 0) {
                    int i2 = this.f6746M - this.f6769aa;
                    if (i2 > 0) {
                        if (i2 <= i) {
                            i = i2;
                        }
                        this.f6751R.left -= i;
                    }
                } else if (!z && mo3640a > this.f6734A) {
                    this.f6751R.left -= i;
                }
            }
            m3475a(canvas, z4, mo3629a.mo3635g(), true, z);
            this.f6776ah = this.f6742I;
        }
        boolean z5 = !this.f6757Z;
        if (!this.f6757Z || this.f6742I <= this.f6743J) {
            z2 = z5;
            z3 = true;
        } else {
            z2 = true;
            z3 = false;
        }
        if (this.f6743J < mo3630a) {
            Sentence mo3629a2 = this.f6802i.mo3629a(this.f6743J);
            this.f6751R.set(this.f6756W);
            int mo3640a2 = mo3629a2.mo3640a() - this.f6734A;
            if (this.f6785aq < 0) {
                if (mo3640a2 <= 0) {
                    this.f6751R.left -= mo3640a2;
                } else if (!this.f6757Z) {
                    int i3 = this.f6746M - this.f6769aa;
                    if (i3 > 0) {
                        if (i3 <= mo3640a2) {
                            mo3640a2 = i3;
                        }
                        this.f6751R.left -= mo3640a2;
                    }
                } else if (this.f6742I > this.f6743J) {
                    this.f6751R.left -= mo3640a2;
                }
            }
            m3475a(canvas, z2, mo3629a2.mo3635g(), false, z3);
            this.f6777ai = this.f6743J;
        }
        this.f6778aj = this.f6757Z;
    }

    /* renamed from: a */
    private void m3466a(String str, int i, int i2, Canvas canvas, TextPaint textPaint) {
        Paint.Style style = textPaint.getStyle();
        textPaint.setStyle(Paint.Style.STROKE);
        canvas.drawText(str, i, i2, textPaint);
        textPaint.setStyle(style);
    }

    /* renamed from: a */
    private void m3463a(boolean z, String str, boolean z2) {
        Canvas canvas;
        Canvas canvas2;
        Canvas canvas3;
        Canvas canvas4;
        Canvas canvas5 = null;
        if (z2 && (this.f6776ah != this.f6742I || (this.f6757Z && !this.f6778aj))) {
            m3452b(true);
            int mo3467a = mo3467a(str) + 12;
            try {
                this.f6772ad = Bitmap.createBitmap(mo3467a, this.f6739F, Bitmap.Config.ARGB_8888);
                canvas3 = new Canvas(this.f6772ad);
            } catch (Throwable th) {
                this.f6772ad = null;
                canvas3 = null;
            }
            try {
                this.f6773ae = Bitmap.createBitmap(mo3467a, this.f6739F, Bitmap.Config.ARGB_8888);
                canvas4 = new Canvas(this.f6773ae);
            } catch (Throwable th2) {
                this.f6773ae = null;
                canvas4 = null;
            }
            canvas = canvas4;
            canvas5 = canvas3;
        } else if (z2 || (this.f6777ai == this.f6743J && (this.f6757Z || !this.f6778aj))) {
            canvas = null;
        } else {
            m3452b(false);
            int mo3467a2 = mo3467a(str) + 12;
            try {
                this.f6774af = Bitmap.createBitmap(mo3467a2, this.f6739F, Bitmap.Config.ARGB_8888);
                canvas2 = new Canvas(this.f6774af);
            } catch (Throwable th3) {
                this.f6774af = null;
                canvas2 = null;
            }
            try {
                this.f6775ag = Bitmap.createBitmap(mo3467a2, this.f6739F, Bitmap.Config.ARGB_8888);
                canvas = new Canvas(this.f6775ag);
                canvas5 = canvas2;
            } catch (Throwable th4) {
                this.f6775ag = null;
                canvas = null;
                canvas5 = canvas2;
            }
        }
        if (canvas5 != null) {
            m3476a(canvas5, str, z, 6, this.f6739F, false, z2);
        }
        if (canvas != null) {
            m3476a(canvas, str, z, 6, this.f6739F, true, z2);
        }
    }

    /* renamed from: a */
    private void m3476a(Canvas canvas, String str, boolean z, int i, int i2, boolean z2, boolean z3) {
        LinearGradient linearGradient;
        TextPaint textPaint = z ? this.f6817x : this.f6816w;
        int i3 = i2 - (z ? this.f6741H : this.f6740G);
        if (this.f6779ak) {
            textPaint.setColor(z2 ? this.f6771ac : this.f6770ab);
            m3466a(str, i, i3, canvas, textPaint);
        }
        textPaint.setColor(z2 ? this.f6808o : this.f6807n);
        if (this.f6780al) {
            if (this.f6802i != null || this.f6790av == null) {
                linearGradient = z2 ? z3 ? this.f6787as : this.f6789au : z3 ? this.f6786ar : this.f6788at;
            } else {
                linearGradient = this.f6790av;
            }
            textPaint.setShader(linearGradient);
        }
        canvas.drawText(str, i, i3, textPaint);
        if (this.f6780al) {
            textPaint.setShader(null);
        }
    }

    /* renamed from: u */
    private void m3413u() {
        float textSize = this.f6817x.getTextSize();
        float textSize2 = this.f6816w.getTextSize();
        if (textSize != this.f6810q || textSize2 != this.f6809p) {
            this.f6817x.setTextSize(this.f6810q);
            this.f6816w.setTextSize(this.f6809p);
        }
    }

    /* renamed from: a */
    private void m3475a(Canvas canvas, boolean z, String str, boolean z2, boolean z3) {
        String str2;
        boolean z4;
        int i = z2 ? this.f6784ap : this.f6785aq;
        if (i >= 0) {
            z4 = false;
            str2 = this.f6791aw[i];
        } else {
            str2 = str;
            z4 = z;
        }
        if (!z4) {
            z3 = z4;
        }
        if (this.f6779ak) {
            m3463a(z4, str, z2);
            if (i >= 0) {
                canvas.translate(0.0f, this.f6751R.top);
                m3476a(canvas, str2, false, this.f6751R.left, this.f6739F, false, z2);
                canvas.translate(0.0f, -this.f6751R.top);
            } else {
                m3479a(canvas, z2 ? this.f6772ad : this.f6774af, this.f6751R.left - 6, this.f6751R.top, (Paint) null);
            }
        } else {
            if (z3) {
                canvas.save();
                canvas.clipRect(this.f6751R.left + this.f6746M, this.f6751R.top, this.f6751R.right + 6, this.f6751R.bottom);
            }
            if (!z4 || z3) {
                m3476a(canvas, str2, z4, this.f6751R.left, this.f6751R.bottom, false, z2);
            }
            if (z3) {
                canvas.restore();
            }
        }
        if (z4) {
            if (z3) {
                canvas.save();
                canvas.clipRect(this.f6751R.left - 6, this.f6751R.top, this.f6751R.left + this.f6746M, this.f6751R.bottom);
            }
            if (this.f6779ak) {
                m3479a(canvas, z2 ? this.f6773ae : this.f6775ag, this.f6751R.left - 6, this.f6751R.top, (Paint) null);
            } else {
                m3476a(canvas, str, true, this.f6751R.left, this.f6751R.bottom, true, z2);
            }
            if (z3) {
                canvas.restore();
            }
        }
    }

    /* renamed from: a */
    private void m3479a(Canvas canvas, Bitmap bitmap, float f, float f2, Paint paint) {
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, f, f2, paint);
        }
    }

    public int getColorNormal() {
        return this.f6807n;
    }

    public void setColorNormal(int i) {
        this.f6807n = i;
        m3444c(true);
    }

    public int getColorHighlight() {
        return this.f6808o;
    }

    public void setColorHighlight(int i) {
        if (i == -1) {
            i = this.f6813t;
        }
        this.f6808o = i;
        this.f6746M = 0;
        m3444c(true);
    }

    public int getColorGradientUGuest() {
        return this.f6782an;
    }

    public void setColorGradientUGuest(int i) {
        this.f6782an = i;
        m3444c(true);
    }

    public int getColorGradientUHost() {
        return this.f6783ao;
    }

    public void setColorGradientUHost(int i) {
        this.f6783ao = i;
        m3444c(true);
    }

    public int getDefaultColorHighlight() {
        return this.f6813t;
    }

    public void setDefaultColorHighlight(int i) {
        this.f6813t = i;
    }

    public void setDefaultFontSizeNormal(float f) {
        this.f6814u = m3449c(2, f);
    }

    public void setDefaultFontSizeHighlight(float f) {
        this.f6815v = m3449c(2, f);
    }

    public float getDefaultFontSizeNormal() {
        return this.f6814u;
    }

    public float getDefaultFontSizeHighlight() {
        return this.f6815v;
    }

    public float getTextSizeNormal() {
        return this.f6809p;
    }

    public float getTextSizeHighlight() {
        return this.f6810q;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009e  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (!isEnabled() || this.f6800g != EnumC1996a.Normal || motionEvent == null) {
            return super.onTouchEvent(motionEvent);
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (motionEvent.getPointerCount() == 1) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.f6793ay = x;
                    this.f6794az = y;
                    z = false;
                    break;
                case 1:
                    if (this.f6802i == null && m3414t() && this.f6761aC != null) {
                        int i = x - this.f6793ay;
                        int i2 = y - this.f6794az;
                        if (this.f6751R.contains(x, y) && (i * i) + (i2 * i2) < this.f6818y) {
                            this.f6761aC.mo3409a(this.f6803j);
                            z = true;
                            break;
                        }
                    }
                    if (this.f6792ax) {
                        m3433f(y);
                        z = true;
                        break;
                    }
                    break;
                case 2:
                    if (this.f6792ax) {
                        try {
                            m3436e(y);
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        z = true;
                        break;
                    } else {
                        int abs = Math.abs(x - this.f6793ay) << 2;
                        int abs2 = Math.abs(y - this.f6794az);
                        if (abs2 > abs && abs2 > f6733aD) {
                            m3442d(y);
                            m3412v();
                            z = true;
                            break;
                        }
                    }
                    break;
                case 3:
                    if (this.f6792ax) {
                    }
                    break;
            }
            return !z || super.onTouchEvent(motionEvent);
        }
        z = false;
        if (z) {
        }
        return z;
    }

    /* renamed from: v */
    private void m3412v() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    /* renamed from: d */
    private void m3442d(int i) {
        int i2;
        int i3 = 1;
        if (this.f6802i != null) {
            this.f6792ax = true;
            this.f6794az = i;
            this.f6759aA = this.f6802i.mo3625b();
            this.f6760aB = this.f6747N;
            if (!this.f6796c) {
                int i4 = (int) this.f6806m;
                if (this.f6759aA >= 0) {
                    Sentence mo3629a = this.f6802i.mo3629a(this.f6759aA);
                    i2 = (int) (this.f6806m - mo3629a.mo3637e());
                    i3 = mo3629a.mo3636f();
                } else {
                    int mo3637e = (int) this.f6802i.mo3629a(0).mo3637e();
                    if (mo3637e < 1) {
                        i2 = i4;
                    } else {
                        i3 = mo3637e;
                        i2 = i4;
                    }
                }
                this.f6760aB = this.f6735B - ((i2 * this.f6739F) / i3);
            }
        }
    }

    /* renamed from: e */
    private void m3436e(int i) {
        int i2;
        int mo3636f;
        long mo3637e;
        if (this.f6802i == null) {
            this.f6792ax = false;
            return;
        }
        int i3 = i - this.f6794az;
        int i4 = this.f6735B - i3;
        int i5 = this.f6759aA;
        int i6 = this.f6760aB;
        int i7 = this.f6739F + i6;
        int i8 = i3 / this.f6739F;
        int i9 = i5 - i8;
        int i10 = i6 - (this.f6739F * i8);
        int i11 = this.f6739F + i10;
        if (i3 > 0) {
            if (i4 < i10) {
                i2 = i9 - 1;
                i10 -= this.f6739F;
            }
            i2 = i9;
        } else {
            if (i4 >= i11) {
                int i12 = i9 + 1;
                int i13 = this.f6739F + i11;
                i2 = i12;
                i10 = i11;
            }
            i2 = i9;
        }
        int mo3630a = this.f6802i.mo3630a();
        if (mo3630a > 0 && i2 >= -1 && i2 < mo3630a) {
            int i14 = i4 - i10;
            if (i2 == -1) {
                int mo3637e2 = (int) this.f6802i.mo3629a(0).mo3637e();
                if (mo3637e2 < 1) {
                    mo3637e = 0;
                    mo3636f = 1;
                } else {
                    mo3636f = mo3637e2;
                    mo3637e = 0;
                }
            } else {
                Sentence mo3629a = this.f6802i.mo3629a(i2);
                mo3636f = mo3629a.mo3636f();
                mo3637e = mo3629a.mo3637e();
            }
            this.f6806m = mo3637e + ((mo3636f * i14) / this.f6739F);
            if (this.f6806m < 10) {
                this.f6806m = 10L;
            } else if (this.f6806m > this.f6801h.mo3669f()) {
                this.f6806m = this.f6801h.mo3669f();
            }
            m3444c(false);
        }
    }

    /* renamed from: b */
    public boolean m3453b(String str) {
        if (StringUtils.isEmpty(str) || this.f6801h == null) {
            return false;
        }
        LyricInfo mo3668g = this.f6801h.mo3668g();
        return StringUtils.m8344a(mo3668g.m3666a(), str) && new File(str).lastModified() == mo3668g.m3662b();
    }

    /* renamed from: f */
    private void m3433f(int i) {
        this.f6792ax = false;
        invalidate();
        if (this.f6761aC != null) {
            this.f6761aC.mo3408a(this.f6806m);
        }
    }

    public void setTouchListener(InterfaceC1998c interfaceC1998c) {
        this.f6761aC = interfaceC1998c;
    }

    /* renamed from: com.sds.android.ttpod.framework.modules.skin.view.LyricView$b */
    /* loaded from: classes.dex */
    private class AsyncTaskC1997b extends AsyncTask<Void, Void, FormattedLyric> {

        /* renamed from: b */
        private Lyric f6824b;

        /* renamed from: c */
        private int f6825c;

        public AsyncTaskC1997b(Lyric lyric, int i) {
            this.f6824b = lyric;
            this.f6825c = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public FormattedLyric doInBackground(Void... voidArr) {
            if (this.f6824b == null) {
                return null;
            }
            return this.f6824b.mo3631a(LyricView.this.f6800g == EnumC1996a.MTV ? 2 : 1, LyricView.this.f6734A, LyricView.this);
        }

        @Override // android.os.AsyncTask
        protected void onCancelled() {
            super.onCancelled();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public void onPostExecute(FormattedLyric formattedLyric) {
            if (LyricView.this.f6762aE == this.f6825c) {
                if (formattedLyric == null || formattedLyric.mo3630a() <= 0) {
                    LyricView.this.f6765aH.sendMessageDelayed(LyricView.this.f6765aH.obtainMessage(3, 7, 0), 0L);
                    return;
                }
                LyricView.this.f6802i = formattedLyric;
                LyricView.this.m3444c(true);
                LyricView.this.f6776ah = -1;
                LyricView.this.f6777ai = -1;
            }
        }
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        getContext().registerReceiver(this.f6768aK, intentFilter);
        this.f6767aJ = true;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f6767aJ) {
            getContext().unregisterReceiver(this.f6768aK);
        }
    }

    /* renamed from: i */
    public void m3427i() {
        this.f6795b = false;
    }
}
