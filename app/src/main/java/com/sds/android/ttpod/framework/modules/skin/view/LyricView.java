package com.sds.android.ttpod.framework.modules.skin.view;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

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
import com.sds.android.ttpod.framework.modules.skin.lyric.FormattedLyric;
import com.sds.android.ttpod.framework.modules.skin.lyric.Lyric;
import com.sds.android.ttpod.framework.modules.skin.lyric.LyricInfo;
import com.sds.android.ttpod.framework.modules.skin.lyric.OnMeasureTextListener;
import com.sds.android.ttpod.framework.modules.skin.lyric.Sentence;
import java.io.File;

@SuppressLint({"HandlerLeak"})
/* loaded from: classes.dex */
public class LyricView extends View implements OnMeasureTextListener {

    /* renamed from: aD */
    private static int dp4 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
            , 4.0f
            , Resources.getSystem().getDisplayMetrics());

    /* renamed from: A */
    private int width;

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
    private int lrcLineIndex;

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
    private int currentColor = Color.YELLOW;

    /* renamed from: P */
    private int prevTextColor = Color.WHITE;//当前的下一句歌词的颜色

    /* renamed from: Q */
    private boolean f6750Q;

    /* renamed from: R */
    private Rect f6751R;

    /* renamed from: S */
    private Rect currentPlayTimeRect;

    /* renamed from: T */
    private int currentTextWidth;

    /* renamed from: U */
    private int fadeEdgeLength;

    /* renamed from: V */
    private Rect f6755V;

    /* renamed from: W */
    private Rect f6756W;

    /* renamed from: Z */
    private boolean f6757Z;

    /* renamed from: a */
    private Context context;

    /* renamed from: aA */
    private int currentLrcIndex;

    /* renamed from: aB */
    private int f6760aB;

    /* renamed from: aC */
    private TouchListener touchListener;

    /* renamed from: aE */
    private int f6762aE;

    /* renamed from: aF */
    private boolean mtvOrSingle;

    /* renamed from: aG */
    private boolean colorBySkin;

    /* renamed from: aH */
    private Handler handler;

    /* renamed from: aI */
    private TextPaint strokeTextPaint;

    /* renamed from: aJ */
    private boolean registerScreenOnOffBroadcastReceiver;

    /* renamed from: aK */
    private BroadcastReceiver screenOnOffBroadcastReceiver;

    /* renamed from: aa */
    private int f6769aa;

    /* renamed from: ab */
    private int colorStrokeNormal;

    /* renamed from: ac */
    private int colorStrokeSelected;

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
    private boolean mtvStroke;

    /* renamed from: al */
    private boolean mtvGradient;

    /* renamed from: am */
    private boolean mtvPositionDown;

    /* renamed from: an */
    private int colorGradientUGuest;

    /* renamed from: ao */
    private int colorGradientUHost;

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
    private LinearGradient gradientUHost;

    /* renamed from: av */
    private LinearGradient gradientUGuest;

    /* renamed from: aw */
    private String[] countDownTexts;

    /* renamed from: ax */
    private boolean f6792ax;

    /* renamed from: ay */
    private int upX;

    /* renamed from: az */
    private int upY;

    /* renamed from: b */
    private boolean screenOfOrOn;

    /* renamed from: c */
    private boolean slowScroll;

    /* renamed from: d */
    private boolean fadeColor;

    /* renamed from: e */
    private boolean kalaok;

    /* renamed from: f */
    private Paint.Align align;

    /* renamed from: g */
    private LyricDisplayEnum lyricDisplayEnum;

    /* renamed from: h */
    private Lyric lyric;

    /* renamed from: i */
    private FormattedLyric formattedLyric;

    /* renamed from: j */
    private int state;

    /* renamed from: k */
    private String lyricStatusText;

    /* renamed from: l */
    private String normalLyricText;

    /* renamed from: m */
    private long playingTime;

    /* renamed from: n */
    private int textColor;

    /* renamed from: o */
    private int colorHighlight;

    /* renamed from: p */
    private float textSizeNormal;

    /* renamed from: q */
    private float textSizeHighlight;

    /* renamed from: r */
    private Typeface typefaceNormal;

    /* renamed from: s */
    private Typeface typefaceHighlight;

    /* renamed from: t */
    private int defaultColorHighlight;

    /* renamed from: u */
    private float defaultFontSizeNormal;

    /* renamed from: v */
    private float defaultFontSizeHighlight;

    /* renamed from: w */
    private TextPaint normalTextPaint;

    /* renamed from: x */
    private TextPaint highlightTextPaint;

    /* renamed from: y */
    private int f6818y;

    /* renamed from: z */
    private TextPaint currentTextPaint;

    /* renamed from: com.sds.android.ttpod.framework.modules.skin.view.LyricView$c */
    /* loaded from: classes.dex */
    public interface TouchListener {
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

    public void setMtvStroke(boolean mtvStroke) {
        this.mtvStroke = mtvStroke;
    }

    public void setMtvGradient(boolean mtvGradient) {
        this.mtvGradient = mtvGradient;
    }

    public void setColorStrokeNormal(int colorStrokeNormal) {
        this.colorStrokeNormal = colorStrokeNormal;
    }

    /* renamed from: b */
    public boolean getMtvOrSingle() {
        return this.mtvOrSingle;
    }

    /* renamed from: c */
    public void setMtvOrSingle() {
        this.mtvOrSingle = true;
    }

    /* renamed from: d */
    public boolean getColorBySkin() {
        return this.colorBySkin;
    }

    public void setColorBySkin(boolean colorBySkin) {
        this.colorBySkin = colorBySkin;
    }

    public int getFadeEdgeLength() {
        return this.fadeEdgeLength;
    }

    public void setFadeEdgeLength(int i) {
        this.fadeEdgeLength = i;
    }

    public void setMtvPositionDown(boolean mtvPositionDown) {
        this.mtvPositionDown = mtvPositionDown;
    }

    /* renamed from: a */
    public int m3483a(int i) {
        if (this.lyric == null || this.formattedLyric == null) {
            return 0;
        }
        int mo3674a = this.lyric.next(i);
        m3444c(true);
        return mo3674a;
    }

    /* renamed from: e */
    public int m3437e() {
        if (this.lyric == null) {
            return 0;
        }
        return (int) (this.lyric.getCurrent() - this.lyric.getOffset());
    }

    /* renamed from: a */
    public boolean m3465a(boolean z) {
        if (this.lyric != null) {
            return this.lyric.mo3673a(z);
        }
        return true;
    }

    /* renamed from: f */
    public void m3434f() {
        if (this.lyric != null && this.formattedLyric != null) {
            this.lyric.syncToCurrent();
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
            case VISIBLE:
                sb.append('V');
                break;
            case INVISIBLE:
                sb.append('I');
                break;
            case GONE:
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
        return this.state;
    }

    public void setState(int i) {
        if (this.state != i && i != 0) {
            this.state = i;
            this.handler.removeMessages(3);
            this.handler.removeMessages(4);
            this.handler.sendMessageDelayed(this.handler.obtainMessage(3, i, 0), 0L);
        }
    }

    /* renamed from: com.sds.android.ttpod.framework.modules.skin.view.LyricView$a */
    /* loaded from: classes.dex */
    public enum LyricDisplayEnum {
        Normal(0),
        MTV(1),
        Single(2);

        LyricDisplayEnum(int i) {
        }
    }

    public void setSlowScroll(boolean slowScroll) {
        if (slowScroll != this.slowScroll) {
            this.slowScroll = slowScroll;
            m3444c(true);
        }
    }

    public void setFadeColor(boolean fadeColor) {
        if (fadeColor != this.fadeColor) {
            this.fadeColor = fadeColor;
            m3444c(true);
        }
    }

    public void setKalaOK(boolean kalaok) {
        if (kalaok != this.kalaok) {
            this.kalaok = kalaok;
            m3444c(true);
        }
    }

    public Paint.Align getAlign() {
        return this.align;
    }

    public void setAlign(Paint.Align align) {
        if (align != this.align) {
            this.align = align;
            m3438d(true);
        }
    }

    public LyricDisplayEnum getDisplayMode() {
        return this.lyricDisplayEnum;
    }

    public void setDisplayMode(LyricDisplayEnum lyricDisplayEnum) {
        if (lyricDisplayEnum != this.lyricDisplayEnum) {
            this.lyricDisplayEnum = lyricDisplayEnum;
            if (this.lyricDisplayEnum != LyricDisplayEnum.MTV) {
                m3422l();
            } else {
                this.normalTextPaint.setTextAlign(Paint.Align.LEFT);
                this.highlightTextPaint.setTextAlign(Paint.Align.LEFT);
            }
            m3423k();
        }
    }

    public LyricView(Context context) {
        super(context);
        this.state = 1;
        this.defaultColorHighlight = -256;
        this.defaultFontSizeNormal = 0.0f;
        this.defaultFontSizeHighlight = 0.0f;
        this.f6751R = new Rect();
        this.currentPlayTimeRect = new Rect();
        this.f6755V = new Rect();
        this.f6756W = new Rect();
        this.mtvStroke = true;
        this.mtvGradient = true;
        this.countDownTexts = new String[]{"", "●", "●●", "●●●", "●●●●", "●●●●●"};
        this.f6792ax = false;
        this.handler = new Handler(Looper.getMainLooper()) { // from class: com.sds.android.ttpod.framework.modules.skin.view.LyricView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        LyricView.this.setTextSize(message.arg1);
                        return;
                    case 2:
                        try {
                            new AsyncTaskC1997b(LyricView.this.lyric, LyricView.m3456b(LyricView.this)).execute(new Void[0]);
                            return;
                        } catch (NoClassDefFoundError e) {
                            LogUtils.debug("LyricView", "lyric format error: no class def found, reason:" + e.toString());
                            return;
                        }
                    case 3:
                        LyricView.this.state = message.arg1;
                        LyricView.this.lyric = null;
                        LyricView.this.formattedLyric = null;
                        switch (LyricView.this.state) {
                            case 1:
                                LyricView.this.lyricStatusText = LyricView.this.normalLyricText;
                                break;
                            case 2:
                                LyricView.this.lyricStatusText = LyricView.this.context.getString(R.string.lyric_searching);
                                break;
                            case 3:
                                LyricView.this.lyricStatusText = LyricView.this.context.getString(R.string.lyric_search_failed);
                                break;
                            case 4:
                                LyricView.this.lyricStatusText = LyricView.this.context.getString(R.string.lyric_downloading);
                                break;
                            case 5:
                                LyricView.this.lyricStatusText = LyricView.this.context.getString(R.string.lyric_download_failed);
                                break;
                            case 6:
                                LyricView.this.lyricStatusText = LyricView.this.context.getString(R.string.lyric_network_error);
                                break;
                            case 7:
                                LyricView.this.lyricStatusText = LyricView.this.context.getString(R.string.lyric_file_not_support);
                                break;
                            case 8:
                                LyricView.this.lyricStatusText = LyricView.this.context.getString(R.string.lyric_server_no_resource);
                                break;
                            default:
                                LyricView.this.lyricStatusText = "unknow state";
                                break;
                        }
                        LyricView.this.imageRecycle(true);
                        LyricView.this.imageRecycle(false);
                        LyricView.this.m3438d(true);
                        return;
                    case 4:
                        LyricView.this.lyric = (Lyric) message.obj;
                        LyricView.this.formattedLyric = null;
                        LyricView.this.state = 0;
                        LyricView.this.lyricStatusText = LyricView.this.normalLyricText;
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
        this.strokeTextPaint = new TextPaint();
        this.strokeTextPaint.setColor(-1);
        this.strokeTextPaint.setStyle(Paint.Style.STROKE);
        this.registerScreenOnOffBroadcastReceiver = false;
        this.screenOnOffBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    LyricView.this.screenOfOrOn = true;
                } else if ("android.intent.action.SCREEN_ON".equals(action)) {
                    LyricView.this.screenOfOrOn = false;
                }
            }
        };
        this.context = context;
        m3425j();
    }

    /* renamed from: j */
    private void m3425j() {
        float dp16 = applyDimension(COMPLEX_UNIT_SP, 16.0f);
        this.defaultFontSizeHighlight = dp16;
        this.defaultFontSizeNormal = dp16;
        this.textSizeNormal = dp16;
        this.textSizeHighlight = dp16;
        this.colorStrokeSelected = 0;
        this.colorStrokeNormal = -1342177280;
        this.slowScroll = true;
        this.fadeColor = true;
        this.kalaok = false;
        this.align = Paint.Align.CENTER;
        this.lyricDisplayEnum = LyricDisplayEnum.Normal;
        this.textColor = -1;
        this.colorHighlight = this.defaultColorHighlight;
        this.normalTextPaint = new TextPaint();
        this.normalTextPaint.setAntiAlias(true);
        this.highlightTextPaint = new TextPaint();
        this.highlightTextPaint.setAntiAlias(true);
        setEnabled(false);
        this.normalLyricText = this.context.getString(R.string.lyric_ttpod);
        this.lyricStatusText = this.normalLyricText;
        int scaledTouchSlop = ViewConfiguration.get(this.context).getScaledTouchSlop();
        this.f6818y = scaledTouchSlop * scaledTouchSlop;
        int dp1 = (int) applyDimension(1, 1.0f);
        this.highlightTextPaint.setStrokeCap(Paint.Cap.ROUND);
        this.highlightTextPaint.setStrokeJoin(Paint.Join.ROUND);
        this.highlightTextPaint.setStrokeWidth(dp1);
        this.normalTextPaint.setStrokeCap(Paint.Cap.ROUND);
        this.normalTextPaint.setStrokeJoin(Paint.Join.ROUND);
        this.normalTextPaint.setStrokeWidth(dp1);
    }

    public LyricView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LyricView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.state = 1;
        this.defaultColorHighlight = -256;
        this.defaultFontSizeNormal = 0.0f;
        this.defaultFontSizeHighlight = 0.0f;
        this.f6751R = new Rect();
        this.currentPlayTimeRect = new Rect();
        this.f6755V = new Rect();
        this.f6756W = new Rect();
        this.mtvStroke = true;
        this.mtvGradient = true;
        this.countDownTexts = new String[]{"", "●", "●●", "●●●", "●●●●", "●●●●●"};
        this.f6792ax = false;
        this.handler = new Handler(Looper.getMainLooper()) { // from class: com.sds.android.ttpod.framework.modules.skin.view.LyricView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        LyricView.this.setTextSize(message.arg1);
                        return;
                    case 2:
                        try {
                            new AsyncTaskC1997b(LyricView.this.lyric, LyricView.m3456b(LyricView.this)).execute(new Void[0]);
                            return;
                        } catch (NoClassDefFoundError e) {
                            LogUtils.debug("LyricView", "lyric format error: no class def found, reason:" + e.toString());
                            return;
                        }
                    case 3:
                        LyricView.this.state = message.arg1;
                        LyricView.this.lyric = null;
                        LyricView.this.formattedLyric = null;
                        switch (LyricView.this.state) {
                            case 1:
                                LyricView.this.lyricStatusText = LyricView.this.normalLyricText;
                                break;
                            case 2:
                                LyricView.this.lyricStatusText = LyricView.this.context.getString(R.string.lyric_searching);
                                break;
                            case 3:
                                LyricView.this.lyricStatusText = LyricView.this.context.getString(R.string.lyric_search_failed);
                                break;
                            case 4:
                                LyricView.this.lyricStatusText = LyricView.this.context.getString(R.string.lyric_downloading);
                                break;
                            case 5:
                                LyricView.this.lyricStatusText = LyricView.this.context.getString(R.string.lyric_download_failed);
                                break;
                            case 6:
                                LyricView.this.lyricStatusText = LyricView.this.context.getString(R.string.lyric_network_error);
                                break;
                            case 7:
                                LyricView.this.lyricStatusText = LyricView.this.context.getString(R.string.lyric_file_not_support);
                                break;
                            case 8:
                                LyricView.this.lyricStatusText = LyricView.this.context.getString(R.string.lyric_server_no_resource);
                                break;
                            default:
                                LyricView.this.lyricStatusText = "unknow state";
                                break;
                        }
                        LyricView.this.imageRecycle(true);
                        LyricView.this.imageRecycle(false);
                        LyricView.this.m3438d(true);
                        return;
                    case 4:
                        LyricView.this.lyric = (Lyric) message.obj;
                        LyricView.this.formattedLyric = null;
                        LyricView.this.state = 0;
                        LyricView.this.lyricStatusText = LyricView.this.normalLyricText;
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
        this.strokeTextPaint = new TextPaint();
        this.strokeTextPaint.setColor(-1);
        this.strokeTextPaint.setStyle(Paint.Style.STROKE);
        this.registerScreenOnOffBroadcastReceiver = false;
        this.screenOnOffBroadcastReceiver = new BroadcastReceiver() { // from class: com.sds.android.ttpod.framework.modules.skin.view.LyricView.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    LyricView.this.screenOfOrOn = true;
                } else if ("android.intent.action.SCREEN_ON".equals(action)) {
                    LyricView.this.screenOfOrOn = false;
                }
            }
        };
        this.context = context;
        m3425j();
    }

    public void setLyric(Lyric lyric) {
        if (this.state != 0 || lyric == null || !lyric.equals(this.lyric)) {
            this.playingTime = 0L;
            this.handler.removeMessages(3);
            this.handler.removeMessages(4);
            this.handler.sendMessageDelayed(this.handler.obtainMessage(4, lyric), 0L);
        }
    }

    /* renamed from: h */
    public boolean m3429h() {
        return this.lyric != null && this.lyric.getLrcLineListSize() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void imageRecycle(boolean z) {
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
            imageRecycle(true);
            imageRecycle(false);
            if (this.lyricDisplayEnum == LyricDisplayEnum.MTV) {
                m3415s();
            } else {
                m3417q();
            }
            if (this.lyric != null) {
                this.state = 0;
                this.handler.removeMessages(2);
                this.handler.sendEmptyMessage(2);
                return;
            }
            m3444c(true);
        }
    }

    public Lyric getLyric() {
        return this.lyric;
    }

    /* renamed from: l */
    private void m3422l() {
        this.f6786ar = null;
        this.f6787as = null;
        this.f6788at = null;
        this.gradientUHost = null;
        this.gradientUGuest = null;
    }

    /* renamed from: m */
    private void m3421m() {
        int i = this.f6739F >> 3;
        this.gradientUGuest = null;
        int i2 = this.mtvStroke ? i : this.f6755V.top + i;
        int i3 = this.mtvStroke ? this.f6739F - i : this.f6755V.bottom - i;
        this.f6786ar = new LinearGradient(0.0f, i2, 0.0f, i3, this.colorGradientUGuest, this.textColor, Shader.TileMode.CLAMP);
        this.f6787as = new LinearGradient(0.0f, i2, 0.0f, i3, this.colorGradientUHost, this.colorHighlight, Shader.TileMode.CLAMP);
        int i4 = this.mtvStroke ? i : this.f6756W.top + i;
        int i5 = this.mtvStroke ? this.f6739F - i : this.f6756W.bottom - i;
        this.f6788at = new LinearGradient(0.0f, i4, 0.0f, i5, this.colorGradientUGuest, this.textColor, Shader.TileMode.CLAMP);
        this.gradientUHost = new LinearGradient(0.0f, i4, 0.0f, i5, this.colorGradientUHost, this.colorHighlight, Shader.TileMode.CLAMP);
        this.f6776ah = -1;
        this.f6777ai = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m3444c(boolean z) {
        boolean z2 = true;
        if (this.formattedLyric != null) {
            if (z) {
                this.f6750Q = true;
            }
            if (this.lyricDisplayEnum == LyricDisplayEnum.MTV) {
                if (z) {
                    m3421m();
                }
                z2 = m3420n();
            } else {
                z2 = m3419o();
            }
        } else if (z && this.lyricDisplayEnum == LyricDisplayEnum.MTV) {
            this.gradientUGuest = null;
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
        int mo3628a = this.formattedLyric.getIndexByLrcTime(this.playingTime);
        if (mo3628a < 0) {
            this.lrcLineIndex = 0;
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
        Sentence mo3629a = this.formattedLyric.getCurrentIndex(mo3628a);
        int mo3636f = mo3629a.getDuration();
        int mo3637e = (int) (this.playingTime - mo3629a.getNextTime());
        int mo3611c = mo3629a.mo3611c(mo3637e);
        if (mo3629a.getCurrentLrcText().length() == 0 && mo3636f >= 7000) {
            int i = ((mo3636f - mo3637e) / 1000) + 1;
            if (i >= this.countDownTexts.length) {
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
                this.lrcLineIndex = mo3628a > 2 ? mo3628a - 1 : 0;
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
                this.lrcLineIndex = i;
            } else {
                this.f6743J = i;
            }
        } else if (this.f6757Z) {
            this.f6743J = i;
        } else {
            this.lrcLineIndex = i;
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
        int mo3628a = this.formattedLyric.getIndexByLrcTime(this.playingTime);
        this.lrcLineIndex = mo3628a;
        this.f6745L = 0;
        Sentence mo3629a = this.formattedLyric.getCurrentIndex(mo3628a);
        int i3 = (int) this.playingTime;
        if (mo3628a >= 0) {
            int mo3638b = mo3629a.getIndex();
            mo3637e = mo3629a.getDuration();
            i = (int) (this.playingTime - mo3629a.getNextTime());
            i2 = mo3638b;
        } else {
            mo3637e = (int) this.formattedLyric.getCurrentIndex(0).getNextTime();
            i = i3;
            i2 = -1;
        }
        if (mo3637e < 1) {
            mo3637e = 1;
        }
        if (this.slowScroll) {
            this.f6747N = this.f6735B - ((this.f6739F * i) / mo3637e);
        } else if (i >= 0 && i < 1000) {
            this.f6747N = (this.f6735B + this.f6738E) - ((this.f6739F * i) / (mo3637e < 1000 ? mo3637e : 1000));
        } else {
            this.f6747N = this.f6735B - this.f6738E;
        }
        int i4 = this.f6737D >> 1;
        int i5 = this.f6747N;
        while (i4 > 0 && this.lrcLineIndex > 0) {
            int i6 = i5 - this.f6739F;
            this.lrcLineIndex--;
            i4--;
            if (i2 == this.formattedLyric.getCurrentIndex(this.lrcLineIndex).getIndex()) {
                this.f6745L++;
                i5 = i6;
            } else {
                i5 = i6;
            }
        }
        boolean z2 = this.f6750Q;
        if (this.kalaok) {
            int mo3611c = mo3629a == null ? 0 : mo3629a.mo3611c(i);
            if (mo3611c != this.f6746M) {
                this.f6746M = mo3611c;
                z = true;
                if (this.f6744K == i5 || z) {
                    if (this.lrcLineIndex >= 0) {
                        if (this.fadeColor && i >= 0 && i < 1000) {
                            if (mo3637e >= 1000) {
                                mo3637e = 1000;
                            }
                            m3485a(i / mo3637e);
                        } else {
                            this.currentColor = this.colorHighlight;
                            if (this.fadeColor && this.lyricDisplayEnum == LyricDisplayEnum.Single) {
                                this.prevTextColor = 0;
                            } else {
                                this.prevTextColor = this.textColor;
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
        if (this.lrcLineIndex >= 0) {
        }
        this.f6744K = i5;
        return true;
    }

    /* renamed from: a */
    private void m3485a(float f) {
        int alpha = Color.alpha(this.colorHighlight);
        int red = Color.red(this.colorHighlight);
        int green = Color.green(this.colorHighlight);
        int blue = Color.blue(this.colorHighlight);
        if (this.lyricDisplayEnum == LyricDisplayEnum.Single) {
            int i = (int) (255.0f * f);
            this.currentColor = Color.argb(i, red, green, blue);
            this.prevTextColor = Color.argb(255 - i, red, green, blue);
            return;
        }
        int alpha2 = Color.alpha(this.textColor);
        int red2 = Color.red(this.textColor);
        int green2 = Color.green(this.textColor);
        int blue2 = Color.blue(this.textColor);
        int abs = alpha == alpha2 ? 0 : (int) (Math.abs(alpha2 - alpha) * f);
        int abs2 = (int) (Math.abs(red2 - red) * f);
        int abs3 = (int) (Math.abs(green2 - green) * f);
        int abs4 = (int) (Math.abs(blue2 - blue) * f);
        if (this.kalaok) {
            this.currentColor = this.colorHighlight;
        } else {
            this.currentColor = Color.argb(m3481a(alpha2, alpha, abs), m3481a(red2, red, abs2), m3481a(green2, green, abs3), m3481a(blue2, blue, abs4));
        }
        this.prevTextColor = Color.argb(m3481a(alpha, alpha2, abs), m3481a(red, red2, abs2), m3481a(green, green2, abs3), m3481a(blue, blue2, abs4));
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
        if (z && this.lyricDisplayEnum == LyricDisplayEnum.MTV) {
            this.f6777ai = -1;
            this.f6776ah = -1;
        }
        setTextAlign();
        postInvalidate();
    }

    /* renamed from: p */
    private void setTextAlign() {
        if (this.lyricDisplayEnum != LyricDisplayEnum.MTV) {
            this.normalTextPaint.setTextAlign(this.align);
            this.highlightTextPaint.setTextAlign(this.align);
        }
    }

    public void setTypefaceNormal(Typeface typefaceNormal) {
        if (this.typefaceNormal != typefaceNormal) {
            this.typefaceNormal = typefaceNormal;
            m3423k();
        }
    }

    public void setTypefaceHighlight(Typeface typefaceHighlight) {
        if (this.typefaceHighlight != typefaceHighlight) {
            this.typefaceHighlight = typefaceHighlight;
            m3423k();
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.OnMeasureTextListener
    /* renamed from: a */
    public int measureLrcTextWidth(String lrcText) {
        if (TextUtils.isEmpty(lrcText)) {
            return 1;
        }
        return (int) (this.currentTextPaint.measureText(lrcText) + 0.96f);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.OnMeasureTextListener
    /* renamed from: a */
    public float getMinTextSize() {
        return Math.min(this.textSizeHighlight, this.textSizeNormal);
    }

    public void setPlayingTime(long playingTime) {
        if (!this.screenOfOrOn && playingTime != this.playingTime && !this.f6792ax) {
            if (playingTime < 10) {
                playingTime = 10;
            }
            this.playingTime = playingTime;
            if (this.formattedLyric != null && !this.handler.hasMessages(5)) {
                this.handler.sendEmptyMessage(5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void setTextSize(float f) {
        boolean z = true;
        float m3449c = applyDimension(2, f);
        boolean z2 = false;
        if (m3449c != this.textSizeNormal) {
            this.textSizeNormal = m3449c;
            z2 = true;
        }
        if (m3449c != this.textSizeHighlight) {
            this.textSizeHighlight = m3449c;
        } else {
            z = z2;
        }
        if (z) {
            m3423k();
        }
    }

    public void postTextSize(float f) {
        this.handler.removeMessages(1);
        this.handler.sendMessage(this.handler.obtainMessage(1, (int) f, 0));
    }

    public void setTextSizeNormal(float f) {
        m3482a(2, f);
    }

    /* renamed from: a */
    public void m3482a(int i, float f) {
        setTextSizeRawNormal(applyDimension(i, f));
    }

    public void setTextSizeRawNormal(float f) {
        if (f != this.textSizeNormal) {
            this.textSizeNormal = f;
            m3423k();
        }
    }

    public void setTextSizeHighlight(float f) {
        m3459b(2, f);
    }

    /* renamed from: b */
    public void m3459b(int i, float f) {
        setTextSizeRawHighlight(applyDimension(i, f));
    }

    public void setTextSizeRawHighlight(float f) {
        if (f != this.textSizeHighlight) {
            this.textSizeHighlight = f;
            m3423k();
        }
    }

    /* renamed from: c */
    private float applyDimension(int unit, float value) {
        return TypedValue.applyDimension(unit, value, this.context.getResources().getDisplayMetrics());
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (i != i3 || i2 != i4) {
            m3423k();
        }
    }

    /* renamed from: a */
    public void setShadowLayer(float radius, float dx, float dy, int shadowColor) {
        this.normalTextPaint.setShadowLayer(radius, dx, dy, shadowColor);
        this.highlightTextPaint.setShadowLayer(radius, dx, dy, shadowColor);
        m3438d(true);
    }

    /* renamed from: q */
    private void m3417q() {
        m3416r();
        this.normalTextPaint.setFakeBoldText(false);
        this.highlightTextPaint.setFakeBoldText(false);
        this.f6738E = this.f6739F >> 1;
        this.width = getWidth() - 20;
        int height = getHeight();
        this.f6751R.left = 10;
        this.f6751R.right = this.f6751R.left + this.width;
        this.f6736C = height >> 1;
        this.f6737D = (height / this.f6739F) + 2;
        this.f6735B = this.f6736C + this.f6739F;
        this.currentPlayTimeRect.left = this.f6751R.left;
        this.currentPlayTimeRect.bottom = this.f6736C - ((int) applyDimension(1, 1.0f));
        this.currentPlayTimeRect.top = this.currentPlayTimeRect.bottom - this.f6739F;
        this.currentTextWidth = measureLrcTextWidth("00:00");
        this.currentPlayTimeRect.right = this.currentPlayTimeRect.left + 20 + this.currentTextWidth;
    }

    /* renamed from: r */
    private void m3416r() {
        this.normalTextPaint.setTypeface(this.typefaceNormal == null ? this.typefaceHighlight : this.typefaceNormal);
        this.highlightTextPaint.setTypeface(this.typefaceHighlight != null ? this.typefaceHighlight : this.typefaceNormal);
        this.normalTextPaint.setColor(this.textColor);
        this.highlightTextPaint.setColor(this.colorHighlight);
        this.normalTextPaint.setTextSize(this.textSizeNormal);
        this.highlightTextPaint.setTextSize(this.textSizeHighlight);
        this.currentTextPaint = this.textSizeHighlight >= this.textSizeNormal ? this.highlightTextPaint : this.normalTextPaint;
        Paint.FontMetrics fontMetrics = this.normalTextPaint.getFontMetrics();
        Paint.FontMetrics fontMetrics2 = this.highlightTextPaint.getFontMetrics();
        int i = (int) (fontMetrics.bottom - fontMetrics.top);
        int i2 = (int) (fontMetrics2.bottom - fontMetrics2.top);
        this.f6739F = this.textSizeHighlight >= this.textSizeNormal ? i2 : i;
        this.f6739F += this.f6739F >> 2;
        if (this.f6739F < 2) {
            this.f6739F = 2;
        }
        this.f6740G = ((this.f6739F - i) >> 1) + ((int) fontMetrics.bottom);
        this.f6741H = ((this.f6739F - i2) >> 1) + ((int) fontMetrics2.bottom);
        setTextAlign();
    }

    /* renamed from: s */
    private void m3415s() {
        m3416r();
        this.normalTextPaint.setFakeBoldText(true);
        this.highlightTextPaint.setFakeBoldText(true);
        this.width = getWidth() - 12;
        this.f6769aa = this.width - (this.width >> 2);
        int height = getHeight();
        int i = height - this.f6739F;
        this.f6756W.set(6, i, this.width + 6, height);
        if (this.mtvPositionDown) {
            this.f6755V.set(6, i - this.f6739F, this.width + 6, i);
        } else {
            this.f6755V.set(6, 0, this.width + 6, this.f6739F);
        }
        this.f6751R.set(6, 0, this.width + 6, height);
        this.f6777ai = -1;
        this.f6776ah = -1;
        this.f6784ap = -1;
        this.f6785aq = -1;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        changeTextSize();
        if (this.formattedLyric == null) {
            m3458b(canvas);
        } else {
            int save = canvas.save();
            if (this.lyricDisplayEnum == LyricDisplayEnum.MTV) {
                m3441d(canvas);
            } else {
                m3448c(canvas);
                if (this.f6792ax && this.lyricDisplayEnum == LyricDisplayEnum.Normal) {
                    m3480a(canvas);
                }
            }
            canvas.restoreToCount(save);
        }
        this.f6750Q = false;
    }

    /* renamed from: a */
    private void m3480a(Canvas canvas) {
        canvas.drawLine(this.f6751R.left, this.f6736C, this.f6751R.right, this.f6736C, this.highlightTextPaint);
        this.highlightTextPaint.setColor(Color.argb(128, 0, 0, 0));
        canvas.drawRect(this.currentPlayTimeRect, this.highlightTextPaint);
        this.highlightTextPaint.setColor(this.colorHighlight);
        String curPlayTimeStr = getCurPlayTimeStr();
        this.highlightTextPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(curPlayTimeStr, this.currentPlayTimeRect.centerX(), this.currentPlayTimeRect.bottom - this.f6741H, this.highlightTextPaint);
        setTextAlign();
    }

    private String getCurPlayTimeStr() {
        int playTimeSecond = (int) (this.playingTime / 1000);
        int playTimeMinute = playTimeSecond / 60;
        return String.format("%02d:%02d", Integer.valueOf(playTimeMinute), Integer.valueOf(playTimeSecond - (playTimeMinute * 60)));
    }

    /* renamed from: b */
    private void m3458b(Canvas canvas) {
        int height = 0;
        Paint.FontMetrics fontMetrics = this.normalTextPaint.getFontMetrics();
        this.f6751R.bottom = this.f6735B - this.f6738E;
        this.f6751R.top = this.f6751R.bottom - this.f6739F;
        if (this.lyricDisplayEnum == LyricDisplayEnum.Single) {
            m3457b(canvas, this.normalLyricText, this.normalTextPaint, this.textColor, measureLrcTextWidth(this.normalLyricText));
        } else if (this.lyricDisplayEnum == LyricDisplayEnum.Normal) {
            if (m3414t()) {
                this.highlightTextPaint.setUnderlineText(true);
                this.highlightTextPaint.setTextAlign(Paint.Align.LEFT);
                m3477a(canvas, this.lyricStatusText, this.highlightTextPaint, this.colorHighlight, measureLrcTextWidth(this.lyricStatusText));
                this.highlightTextPaint.setTextAlign(this.align);
                this.highlightTextPaint.setUnderlineText(false);
                this.f6751R.bottom += this.f6738E;
                this.f6751R.top -= this.f6738E;
                return;
            }
            m3457b(canvas, this.lyricStatusText, this.normalTextPaint, this.textColor, measureLrcTextWidth(this.lyricStatusText));
        } else {
            this.f6751R.set(6, 0, this.width + 6, getHeight());
            int m3450c = m3450c(measureLrcTextWidth(this.lyricStatusText));
            this.normalTextPaint.setColor(-1);
            int i = (int) ((this.f6751R.bottom - ((height - ((int) (fontMetrics.bottom - fontMetrics.top))) >> 1)) - this.normalTextPaint.getFontMetrics().descent);
            if (this.gradientUGuest == null) {
                int i2 = this.f6739F >> 3;
                this.gradientUGuest = new LinearGradient(0.0f, (i - this.f6739F) + i2, 0.0f, i - i2, this.colorGradientUGuest, this.textColor, Shader.TileMode.CLAMP);
            }
            m3476a(canvas, this.lyricStatusText, false, m3450c, i + this.f6740G, false, false);
        }
    }

    /* renamed from: t */
    private boolean m3414t() {
        return (this.state == 1 || this.state == 2 || this.state == 4 || this.state == 0 || this.state == 8) ? false : true;
    }

    /* renamed from: a */
    private void m3477a(Canvas canvas, String str, TextPaint textPaint, int i, int i2) {
        int m3450c = m3450c(i2);
        textPaint.setColor(i);
        canvas.drawText(str, m3450c, this.f6751R.bottom - this.f6741H, textPaint);
    }

    /* renamed from: b */
    private void m3457b(Canvas canvas, String str, TextPaint textPaint, int color, int i2) {
        int i3 = textPaint == this.highlightTextPaint ? this.f6741H : this.f6740G;
        int m3460b = m3460b(i2);
        textPaint.setColor(color);
        canvas.drawText(str, m3460b, this.f6751R.bottom - i3, textPaint);
    }

    /* renamed from: a */
    private void m3478a(Canvas canvas, String str, int i) {
        int m3460b = m3460b(i);
        int m3450c = m3450c(i);
        canvas.save();
        canvas.clipRect(m3450c, this.f6751R.top, this.f6746M + m3450c, this.f6751R.bottom);
        this.highlightTextPaint.setColor(this.colorHighlight);
        canvas.drawText(str, m3460b, this.f6751R.bottom - this.f6741H, this.highlightTextPaint);
        canvas.restore();
        canvas.save();
        canvas.clipRect(m3450c + this.f6746M, this.f6751R.top, this.f6751R.right, this.f6751R.bottom);
        this.highlightTextPaint.setColor(this.textColor);
        canvas.drawText(str, m3460b, this.f6751R.bottom - this.f6741H, this.highlightTextPaint);
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
        switch (C19953.f6822a[this.align.ordinal()]) {
            case 1:
                return i2 + (this.width >> 1);
            case 2:
                return this.f6751R.right;
            default:
                return i2;
        }
    }

    /* renamed from: c */
    private int m3450c(int i) {
        int i2 = this.f6751R.left;
        switch (C19953.f6822a[this.align.ordinal()]) {
            case 1:
                return i2 + ((this.width - i) >> 1);
            case 2:
                return this.f6751R.right - i;
            default:
                return i2;
        }
    }

    /* renamed from: c */
    private void m3448c(Canvas canvas) {
        int index;
        int i2;
        TextPaint textPaint;
        int i3;
        int i4 = this.lrcLineIndex;
        int i5 = this.f6744K;
        int lrcLineSize = this.formattedLyric.getLrcLineSize();
        int lrcLineIndex = this.formattedLyric.getCurrentIndex();
        if (lrcLineIndex < 0) {
            index = -1;
        } else {
            index = this.formattedLyric.getCurrentIndex(lrcLineIndex).getIndex();
        }
        if (this.fadeEdgeLength <= 0 || this.lyricDisplayEnum != LyricDisplayEnum.Normal) {
            i2 = 0;
        } else {
            i2 = canvas.saveLayer(this.f6751R.left, 0.0f, this.f6751R.right, getHeight(), null, Canvas.ALL_SAVE_FLAG);
        }
        int i6 = 0;
        while (i6 < this.f6737D && i4 < lrcLineSize) {
            if (i4 >= 0) {
                Sentence sentence = this.formattedLyric.getCurrentIndex(i4);
                int sentenceIndex = sentence.getIndex();
                String mo3635g = sentence.getCurrentLrcText();
                if (mo3635g.length() != 0) {
                    this.f6751R.bottom = i5;
                    this.f6751R.top = i5 - this.f6739F;
                    int color = this.textColor;
                    int mo3640a = sentence.getLrcTextWidth();
                    if (i4 == lrcLineIndex) {
                        if (this.kalaok) {
                            m3478a(canvas, mo3635g, mo3640a);
                        } else {
                            int nextColor = this.currentColor;
                            if (this.lyricDisplayEnum == LyricDisplayEnum.Normal && this.f6745L != 0) {
                                nextColor = this.colorHighlight;
                            }
                            m3457b(canvas, mo3635g, this.highlightTextPaint, nextColor, mo3640a);
                        }
                    } else if (this.lyricDisplayEnum == LyricDisplayEnum.Single) {
                        if (i4 == lrcLineIndex - 1) {
                            m3457b(canvas, mo3635g, this.normalTextPaint, this.prevTextColor, mo3640a);
                        }
                    } else {
                        TextPaint textPaint2 = this.normalTextPaint;
                        if (sentenceIndex == index - 1) {
                            color = this.f6745L == 0 ? this.prevTextColor : this.textColor;
                        }
                        if (sentenceIndex == index) {
                            if (this.kalaok) {
                                i3 = i4 < lrcLineIndex ? this.colorHighlight : this.textColor;
                            } else {
                                i3 = this.f6745L == 0 ? this.currentColor : this.colorHighlight;
                            }
                            textPaint = this.highlightTextPaint;
                            color = i3;
                        } else {
                            textPaint = this.normalTextPaint;
                        }
                        m3457b(canvas, mo3635g, textPaint, color, mo3640a);
                    }
                }
            }
            i6++;
            i5 = this.f6739F + i5;
            i4++;
        }
        if (this.fadeEdgeLength > 0 && this.lyricDisplayEnum == LyricDisplayEnum.Normal) {
            Paint paint = new Paint(1);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, this.fadeEdgeLength, 0, (int) 0xff000000, Shader.TileMode.CLAMP));
            canvas.drawRect(this.f6751R.left, 0.0f, this.f6751R.right, this.fadeEdgeLength, paint);
            int height = getHeight();
            paint.setShader(new LinearGradient(0.0f, height - this.fadeEdgeLength, 0.0f, height, (int) 0xff000000, 0, Shader.TileMode.CLAMP));
            canvas.drawRect(this.f6751R.left, height - this.fadeEdgeLength, this.f6751R.right, height, paint);
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
        if (this.f6757Z || this.lrcLineIndex >= this.f6743J) {
            z = true;
        } else {
            z4 = true;
            z = false;
        }
        int mo3630a = this.formattedLyric.getLrcLineSize();
        if (this.lrcLineIndex < mo3630a) {
            Sentence mo3629a = this.formattedLyric.getCurrentIndex(this.lrcLineIndex);
            this.f6751R.set(this.f6755V);
            int mo3640a = mo3629a.getLrcTextWidth();
            int i = mo3640a - this.width;
            if (this.f6784ap < 0) {
                if (this.f6757Z && i > 0) {
                    int i2 = this.f6746M - this.f6769aa;
                    if (i2 > 0) {
                        if (i2 <= i) {
                            i = i2;
                        }
                        this.f6751R.left -= i;
                    }
                } else if (!z && mo3640a > this.width) {
                    this.f6751R.left -= i;
                }
            }
            m3475a(canvas, z4, mo3629a.getCurrentLrcText(), true, z);
            this.f6776ah = this.lrcLineIndex;
        }
        boolean z5 = !this.f6757Z;
        if (!this.f6757Z || this.lrcLineIndex <= this.f6743J) {
            z2 = z5;
            z3 = true;
        } else {
            z2 = true;
            z3 = false;
        }
        if (this.f6743J < mo3630a) {
            Sentence mo3629a2 = this.formattedLyric.getCurrentIndex(this.f6743J);
            this.f6751R.set(this.f6756W);
            int mo3640a2 = mo3629a2.getLrcTextWidth() - this.width;
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
                } else if (this.lrcLineIndex > this.f6743J) {
                    this.f6751R.left -= mo3640a2;
                }
            }
            m3475a(canvas, z2, mo3629a2.getCurrentLrcText(), false, z3);
            this.f6777ai = this.f6743J;
        }
        this.f6778aj = this.f6757Z;
    }

    /* renamed from: a */
    private void m3466a(String text, int x, int y, Canvas canvas, TextPaint textPaint) {
        Paint.Style style = textPaint.getStyle();
        textPaint.setStyle(Paint.Style.STROKE);
        canvas.drawText(text, x, y, textPaint);
        textPaint.setStyle(style);
    }

    /* renamed from: a */
    private void m3463a(boolean z, String str, boolean z2) {
        Canvas canvas;
        Canvas canvas2;
        Canvas canvas3;
        Canvas canvas4;
        Canvas canvas5 = null;
        if (z2 && (this.f6776ah != this.lrcLineIndex || (this.f6757Z && !this.f6778aj))) {
            imageRecycle(true);
            int mo3467a = measureLrcTextWidth(str) + 12;
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
            imageRecycle(false);
            int mo3467a2 = measureLrcTextWidth(str) + 12;
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
    private void m3476a(Canvas canvas, String text, boolean playing, int x, int i2, boolean selected, boolean z3) {
        LinearGradient linearGradient;
        TextPaint textPaint = playing ? this.highlightTextPaint : this.normalTextPaint;
        int y = i2 - (playing ? this.f6741H : this.f6740G);
        if (this.mtvStroke) {
            textPaint.setColor(selected ? this.colorStrokeSelected : this.colorStrokeNormal);
            m3466a(text, x, y, canvas, textPaint);
        }
        textPaint.setColor(selected ? this.colorHighlight : this.textColor);
        if (this.mtvGradient) {
            if (this.formattedLyric != null || this.gradientUGuest == null) {
                linearGradient = selected ? z3 ? this.f6787as : this.gradientUHost : z3 ? this.f6786ar : this.f6788at;
            } else {
                linearGradient = this.gradientUGuest;
            }
            textPaint.setShader(linearGradient);
        }
        canvas.drawText(text, x, y, textPaint);
        if (this.mtvGradient) {
            textPaint.setShader(null);
        }
    }

    /* renamed from: u */
    private void changeTextSize() {
        float textSize = this.highlightTextPaint.getTextSize();
        float textSize2 = this.normalTextPaint.getTextSize();
        if (textSize != this.textSizeHighlight || textSize2 != this.textSizeNormal) {
            this.highlightTextPaint.setTextSize(this.textSizeHighlight);
            this.normalTextPaint.setTextSize(this.textSizeNormal);
        }
    }

    /* renamed from: a */
    private void m3475a(Canvas canvas, boolean z, String str, boolean z2, boolean z3) {
        String str2;
        boolean z4;
        int i = z2 ? this.f6784ap : this.f6785aq;
        if (i >= 0) {
            z4 = false;
            str2 = this.countDownTexts[i];
        } else {
            str2 = str;
            z4 = z;
        }
        if (!z4) {
            z3 = z4;
        }
        if (this.mtvStroke) {
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
            if (this.mtvStroke) {
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
        return this.textColor;
    }

    public void setColorNormal(int i) {
        this.textColor = i;
        m3444c(true);
    }

    public int getColorHighlight() {
        return this.colorHighlight;
    }

    public void setColorHighlight(int i) {
        if (i == -1) {
            i = this.defaultColorHighlight;
        }
        this.colorHighlight = i;
        this.f6746M = 0;
        m3444c(true);
    }

    public int getColorGradientUGuest() {
        return this.colorGradientUGuest;
    }

    public void setColorGradientUGuest(int colorGradientUGuest) {
        this.colorGradientUGuest = colorGradientUGuest;
        m3444c(true);
    }

    public int getColorGradientUHost() {
        return this.colorGradientUHost;
    }

    public void setColorGradientUHost(int colorGradientUHost) {
        this.colorGradientUHost = colorGradientUHost;
        m3444c(true);
    }

    public int getDefaultColorHighlight() {
        return this.defaultColorHighlight;
    }

    public void setDefaultColorHighlight(int defaultColorHighlight) {
        this.defaultColorHighlight = defaultColorHighlight;
    }

    public void setDefaultFontSizeNormal(float f) {
        this.defaultFontSizeNormal = applyDimension(2, f);
    }

    public void setDefaultFontSizeHighlight(float f) {
        this.defaultFontSizeHighlight = applyDimension(2, f);
    }

    public float getDefaultFontSizeNormal() {
        return this.defaultFontSizeNormal;
    }

    public float getDefaultFontSizeHighlight() {
        return this.defaultFontSizeHighlight;
    }

    public float getTextSizeNormal() {
        return this.textSizeNormal;
    }

    public float getTextSizeHighlight() {
        return this.textSizeHighlight;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009e  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (!isEnabled() || this.lyricDisplayEnum != LyricDisplayEnum.Normal || motionEvent == null) {
            return super.onTouchEvent(motionEvent);
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (motionEvent.getPointerCount() == 1) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.upX = x;
                    this.upY = y;
                    z = false;
                    break;
                case 1:
                    if (this.formattedLyric == null && m3414t() && this.touchListener != null) {
                        int distanceX = x - this.upX;
                        int distanceY = y - this.upY;
                        if (this.f6751R.contains(x, y) && (distanceX * distanceX) + (distanceY * distanceY) < this.f6818y) {
                            this.touchListener.mo3409a(this.state);
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
                        int abs = Math.abs(x - this.upX) << 2;
                        int abs2 = Math.abs(y - this.upY);
                        if (abs2 > abs && abs2 > dp4) {
                            m3442d(y);
                            requestDisallowInterceptTouchEvent();
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
    private void requestDisallowInterceptTouchEvent() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    /* renamed from: d */
    private void m3442d(int upY) {
        int i2;
        int i3 = 1;
        if (this.formattedLyric != null) {
            this.f6792ax = true;
            this.upY = upY;
            this.currentLrcIndex = this.formattedLyric.getCurrentIndex();
            this.f6760aB = this.f6747N;
            if (!this.slowScroll) {
                int i4 = (int) this.playingTime;
                if (this.currentLrcIndex >= 0) {
                    Sentence mo3629a = this.formattedLyric.getCurrentIndex(this.currentLrcIndex);
                    i2 = (int) (this.playingTime - mo3629a.getNextTime());
                    i3 = mo3629a.getDuration();
                } else {
                    int mo3637e = (int) this.formattedLyric.getCurrentIndex(0).getNextTime();
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
        if (this.formattedLyric == null) {
            this.f6792ax = false;
            return;
        }
        int i3 = i - this.upY;
        int i4 = this.f6735B - i3;
        int i5 = this.currentLrcIndex;
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
        int mo3630a = this.formattedLyric.getLrcLineSize();
        if (mo3630a > 0 && i2 >= -1 && i2 < mo3630a) {
            int i14 = i4 - i10;
            if (i2 == -1) {
                int mo3637e2 = (int) this.formattedLyric.getCurrentIndex(0).getNextTime();
                if (mo3637e2 < 1) {
                    mo3637e = 0;
                    mo3636f = 1;
                } else {
                    mo3636f = mo3637e2;
                    mo3637e = 0;
                }
            } else {
                Sentence mo3629a = this.formattedLyric.getCurrentIndex(i2);
                mo3636f = mo3629a.getDuration();
                mo3637e = mo3629a.getNextTime();
            }
            this.playingTime = mo3637e + ((mo3636f * i14) / this.f6739F);
            if (this.playingTime < 10) {
                this.playingTime = 10L;
            } else if (this.playingTime > this.lyric.getLrcLastTime()) {
                this.playingTime = this.lyric.getLrcLastTime();
            }
            m3444c(false);
        }
    }

    /* renamed from: b */
    public boolean m3453b(String lyricPath) {
        if (StringUtils.isEmpty(lyricPath) || this.lyric == null) {
            return false;
        }
        LyricInfo lyricInfo = this.lyric.getLyricInfo();
        return StringUtils.equals(lyricInfo.getLyricPath(), lyricPath)
                && new File(lyricPath).lastModified() == lyricInfo.getLyricLastModified();
    }

    /* renamed from: f */
    private void m3433f(int i) {
        this.f6792ax = false;
        invalidate();
        if (this.touchListener != null) {
            this.touchListener.mo3408a(this.playingTime);
        }
    }

    public void setTouchListener(TouchListener touchListener) {
        this.touchListener = touchListener;
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
            return this.f6824b.getFormatterLyric(LyricView.this.lyricDisplayEnum == LyricDisplayEnum.MTV ? 2 : 1
                    , LyricView.this.width, LyricView.this);
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
                if (formattedLyric == null || formattedLyric.getLrcLineSize() <= 0) {
                    LyricView.this.handler.sendMessageDelayed(LyricView.this.handler.obtainMessage(3, 7, 0), 0L);
                    return;
                }
                LyricView.this.formattedLyric = formattedLyric;
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
        getContext().registerReceiver(this.screenOnOffBroadcastReceiver, intentFilter);
        this.registerScreenOnOffBroadcastReceiver = true;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.registerScreenOnOffBroadcastReceiver) {
            getContext().unregisterReceiver(this.screenOnOffBroadcastReceiver);
        }
    }

    /* renamed from: i */
    public void screenOn() {
        this.screenOfOrOn = false;
    }
}
