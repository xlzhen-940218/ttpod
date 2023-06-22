package com.sds.android.ttpod.framework.support.minilyric;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.p132d.Lyric;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;
import com.sds.android.ttpod.framework.p106a.NotificationUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportService;

/* renamed from: com.sds.android.ttpod.framework.support.minilyric.a */
/* loaded from: classes.dex */
public class MiniLyric implements View.OnTouchListener {

    /* renamed from: a */
    private long f7199a;

    /* renamed from: b */
    private long f7200b;

    /* renamed from: d */
    private C2080b f7202d;

    /* renamed from: e */
    private C2080b f7203e;

    /* renamed from: f */
    private boolean f7204f;

    /* renamed from: h */
    private View f7206h;

    /* renamed from: i */
    private View f7207i;

    /* renamed from: j */
    private View f7208j;

    /* renamed from: k */
    private View f7209k;

    /* renamed from: l */
    private ImageView f7210l;

    /* renamed from: m */
    private View f7211m;

    /* renamed from: n */
    private View f7212n;

    /* renamed from: o */
    private View f7213o;

    /* renamed from: p */
    private View f7214p;

    /* renamed from: z */
    private Context f7224z;

    /* renamed from: c */
    private C2080b f7201c = new C2080b(0, Preferences.m2976ac());

    /* renamed from: g */
    private boolean f7205g = false;

    /* renamed from: q */
    private C2079a f7215q = null;

    /* renamed from: r */
    private TextView f7216r = null;

    /* renamed from: s */
    private View f7217s = null;

    /* renamed from: t */
    private LyricView f7218t = null;

    /* renamed from: u */
    private LinearLayout f7219u = null;

    /* renamed from: v */
    private FrameLayout f7220v = null;

    /* renamed from: w */
    private FloatWindow f7221w = null;

    /* renamed from: x */
    private Handler f7222x = new Handler(Looper.getMainLooper());

    /* renamed from: y */
    private boolean f7223y = true;

    /* renamed from: A */
    private Runnable f7197A = new Runnable() { // from class: com.sds.android.ttpod.framework.support.minilyric.a.1
        @Override // java.lang.Runnable
        public void run() {
            MiniLyric.this.m2305b(false, true);
        }
    };

    /* renamed from: B */
    private Runnable f7198B = new Runnable() { // from class: com.sds.android.ttpod.framework.support.minilyric.a.2
        @Override // java.lang.Runnable
        public void run() {
            MiniLyric.this.m2306b(false);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MiniLyric.java */
    /* renamed from: com.sds.android.ttpod.framework.support.minilyric.a$a */
    /* loaded from: classes.dex */
    public class C2079a {

        /* renamed from: b */
        private Drawable f7230b;

        /* renamed from: c */
        private Drawable f7231c;

        /* renamed from: d */
        private SparseArray<ImageView> f7232d;

        /* renamed from: e */
        private SparseArray<ImageView> f7233e;

        private C2079a() {
            this.f7230b = MiniLyric.this.f7224z.getResources().getDrawable(R.drawable.img_button_minilyric_choice_color);
            this.f7231c = MiniLyric.this.f7224z.getResources().getDrawable(R.color.transparent);
            this.f7232d = new SparseArray<>();
            this.f7233e = new SparseArray<>();
        }

        /* renamed from: a */
        void m2275a(Integer num, ImageView imageView, ImageView imageView2) {
            imageView.setImageDrawable(this.f7231c);
            this.f7232d.put(num.intValue(), imageView);
            this.f7233e.put(num.intValue(), imageView2);
        }

        /* renamed from: a */
        int m2277a(ImageView imageView) {
            return this.f7233e.keyAt(this.f7233e.indexOfValue(imageView));
        }

        /* renamed from: a */
        void m2278a() {
            m2276a(Integer.valueOf(Preferences.m2978ab()));
        }

        /* renamed from: a */
        void m2276a(Integer num) {
            this.f7232d.get(Preferences.m2978ab()).setImageDrawable(this.f7231c);
            Preferences.m2885f(num.intValue());
            this.f7232d.get(num.intValue()).setImageDrawable(this.f7230b);
        }
    }

    public MiniLyric(Context context) {
        this.f7224z = null;
        if (context == null) {
            throw new NullPointerException();
        }
        this.f7224z = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2323a() {
        m2291g();
        m2289h();
        m2288i();
        m2287j();
        m2283n();
        m2286k();
        m2299d();
    }

    /* renamed from: g */
    private void m2291g() {
        this.f7219u = (LinearLayout) ((LayoutInflater) this.f7224z.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.minilyricview_main, (ViewGroup) null, false);
        this.f7220v = (FrameLayout) this.f7219u.findViewById(R.id.frame_inner);
        this.f7206h = this.f7219u.findViewById(R.id.iv_setting);
        this.f7207i = this.f7219u.findViewById(R.id.button_previous_minilyric);
        this.f7208j = this.f7219u.findViewById(R.id.button_next_minilyric);
        this.f7210l = (ImageView) this.f7219u.findViewById(R.id.button_playpause_minilyric);
        this.f7209k = this.f7219u.findViewById(R.id.iv_ttpod);
        this.f7218t = new LyricView(this.f7224z);
        this.f7220v.addView(this.f7218t, 0);
        if (this.f7221w == null) {
            this.f7221w = new FloatWindow(this.f7219u, -1, -2, false);
        }
    }

    /* renamed from: h */
    private void m2289h() {
        this.f7219u.setOnTouchListener(this);
        this.f7206h.setOnTouchListener(this);
        this.f7206h.setVisibility(View.INVISIBLE);
        this.f7207i.setOnTouchListener(this);
        this.f7207i.setVisibility(View.INVISIBLE);
        this.f7208j.setOnTouchListener(this);
        this.f7208j.setVisibility(View.INVISIBLE);
        this.f7210l.setOnTouchListener(this);
        this.f7210l.setVisibility(View.INVISIBLE);
        this.f7209k.setOnTouchListener(this);
        this.f7209k.setVisibility(View.INVISIBLE);
        m2309b(Preferences.m2978ab());
        Typeface create = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL);
        this.f7218t.setTypefaceHighlight(create);
        this.f7218t.setTypefaceNormal(create);
        this.f7218t.setKalaOK(true);
        this.f7218t.setTextSize(Preferences.m3030Z());
        m2322a(4);
        this.f7218t.setEnabled(true);
        this.f7218t.setClickable(true);
        this.f7218t.setOnTouchListener(this);
        this.f7202d = new C2080b(0, 0);
        this.f7203e = new C2080b(0, 0);
        this.f7201c.m2273a(0, Preferences.m2976ac());
    }

    /* renamed from: i */
    private void m2288i() {
        this.f7217s = ((LayoutInflater) BaseApplication.getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.minilyricsettingview_main, (ViewGroup) null, false);
        this.f7211m = this.f7217s.findViewById(R.id.iv_font_zoomout);
        this.f7212n = this.f7217s.findViewById(R.id.iv_font_zoomin);
        this.f7213o = this.f7217s.findViewById(R.id.iv_lock);
        this.f7214p = this.f7217s.findViewById(R.id.iv_close);
        this.f7216r = (TextView) this.f7217s.findViewById(R.id.tv_ok);
    }

    /* renamed from: j */
    private void m2287j() {
        this.f7217s.setOnTouchListener(this);
        this.f7217s.setVisibility(View.GONE);
        this.f7211m.setOnTouchListener(this);
        this.f7212n.setOnTouchListener(this);
        this.f7213o.setOnTouchListener(this);
        this.f7214p.setOnTouchListener(this);
        this.f7216r.setOnTouchListener(this);
    }

    /* renamed from: k */
    private void m2286k() {
        this.f7215q = new C2079a();
        m2321a(20, R.id.iv_color_blue_flag, R.id.iv_color_blue);
        m2321a(21, R.id.iv_color_yellow_flag, R.id.iv_color_yellow);
        m2321a(22, R.id.iv_color_pink_flag, R.id.iv_color_pink);
        m2321a(23, R.id.iv_color_gray_flag, R.id.iv_color_gray);
        m2321a(24, R.id.iv_color_green_flag, R.id.iv_color_green);
        this.f7215q.m2278a();
    }

    /* renamed from: a */
    private void m2321a(int i, int i2, int i3) {
        ImageView imageView = (ImageView) this.f7217s.findViewById(i3);
        imageView.setOnTouchListener(this);
        this.f7215q.m2275a(Integer.valueOf(i), (ImageView) this.f7217s.findViewById(i2), imageView);
    }

    /* renamed from: b */
    private void m2309b(int i) {
        int i2;
        int i3;
        int i4 = -328966;
        int i5 = -11097872;
        switch (i) {
            case 20:
                i2 = -4147698;
                i3 = -15905871;
                i5 = -4709;
                i4 = -16723723;
                break;
            case 21:
                i2 = -2854117;
                i3 = -4521727;
                i5 = -154;
                i4 = -83455;
                break;
            case 22:
                i2 = -5402049;
                i3 = -5231963;
                i5 = -199217;
                i4 = -15620;
                break;
            case 23:
                i2 = -14125851;
                i3 = -5395027;
                i5 = -9903106;
                i4 = -921103;
                break;
            case 24:
                i2 = -6383092;
                i3 = -14971380;
                i5 = -398;
                i4 = -9437385;
                break;
            default:
                i2 = -11097872;
                i3 = -328966;
                break;
        }
        this.f7218t.setColorHighlight(i3);
        this.f7218t.setColorNormal(i2);
        this.f7218t.setColorGradientUGuest(i5);
        this.f7218t.setColorGradientUHost(i4);
    }

    /* renamed from: a */
    public void m2319a(long j) {
        if (this.f7218t != null) {
            this.f7218t.setPlayingTime(j);
        }
    }

    /* renamed from: b */
    public boolean m2310b() {
        return !Preferences.m2980aa();
    }

    /* renamed from: a */
    public void m2317a(Lyric lyric) {
        boolean z;
        this.f7218t.setLyric(lyric);
        if (m2310b()) {
            z = false;
        } else {
            z = lyric == null;
        }
        this.f7223y = z;
        if (this.f7223y && this.f7217s.getVisibility() == View.VISIBLE) {
            m2300c(false);
        }
        if (m2310b() && lyric == null) {
            this.f7218t.setState(8);
        }
        this.f7219u.setVisibility(this.f7223y ? View.INVISIBLE : View.VISIBLE);
    }

    /* renamed from: c */
    public boolean m2304c() {
        return this.f7218t.m3429h();
    }

    /* renamed from: d */
    public void m2299d() {
        this.f7218t.setState(1);
    }

    /* renamed from: c */
    private void m2303c(int i) {
        DisplayMetrics displayMetrics = this.f7224z.getResources().getDisplayMetrics();
        int m7229a = DisplayUtils.m7229a((int) ((Math.max(displayMetrics.scaledDensity / displayMetrics.density, 1.0f) * 84.0f) - ((28 - i) * 3)));
        this.f7218t.getLayoutParams().height = m7229a;
        this.f7206h.getLayoutParams().height = m7229a;
        this.f7207i.getLayoutParams().height = m7229a;
        this.f7208j.getLayoutParams().height = m7229a;
        this.f7209k.getLayoutParams().height = m7229a;
        this.f7210l.getLayoutParams().height = m7229a;
        this.f7220v.getLayoutParams().height = m7229a;
        this.f7220v.requestLayout();
    }

    /* renamed from: a */
    public void m2322a(int i) {
        int m3030Z = Preferences.m3030Z();
        LogUtils.m8388a("minilyric", "setDisplayMode fontSize=" + m3030Z);
        switch (i) {
            case 3:
                this.f7218t.setLayoutParams(new FrameLayout.LayoutParams(-2, DisplayUtils.m7229a(40 - (28 - m3030Z))));
                this.f7218t.setDisplayMode(LyricView.EnumC1996a.Single);
                return;
            case 4:
                this.f7218t.setLayoutParams(new FrameLayout.LayoutParams(-2, 0));
                this.f7218t.setDisplayMode(LyricView.EnumC1996a.MTV);
                m2303c(m3030Z);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2320a(int i, int i2, int i3, int i4) {
        this.f7221w.m2363a(i, i2, i3, i4);
    }

    /* renamed from: e */
    public void m2296e() {
        LogUtils.m8388a("minilyric", "show");
        if (this.f7218t != null) {
            this.f7218t.m3427i();
        }
        this.f7219u.setVisibility(View.VISIBLE);
        this.f7221w.m2364a(83, this.f7201c.m2274a(), this.f7201c.m2270b());
        this.f7221w.m2357b();
        m2311a(Preferences.m2980aa(), false);
    }

    /* renamed from: a */
    public void m2312a(boolean z) {
        LogUtils.m8388a("minilyric", "hide");
        this.f7221w.m2353c();
        this.f7219u.setVisibility(View.INVISIBLE);
        m2300c(true);
        NotificationUtils.m4696a(12101710);
    }

    /* renamed from: f */
    public void m2293f() {
        LogUtils.m8388a("minilyric", "destroy");
        this.f7201c = null;
        this.f7202d = null;
        this.f7203e = null;
        this.f7218t = null;
        if (this.f7221w.m2366a()) {
            this.f7221w.m2353c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m2305b(boolean z, boolean z2) {
        if (!z || this.f7206h.getVisibility() != View.VISIBLE) {
            if (z2 && !z) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setDuration(300L);
                this.f7206h.startAnimation(alphaAnimation);
            }
            this.f7206h.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m2306b(boolean z) {
        if (!z) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(300L);
            this.f7207i.startAnimation(alphaAnimation);
            this.f7208j.startAnimation(alphaAnimation);
            this.f7209k.startAnimation(alphaAnimation);
            this.f7210l.startAnimation(alphaAnimation);
        }
        this.f7207i.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
        this.f7208j.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
        this.f7209k.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
        this.f7210l.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.f7202d != null && this.f7203e != null && this.f7201c != null && this.f7219u != null) {
            int rawY = (int) motionEvent.getRawY();
            switch (motionEvent.getAction()) {
                case 0:
                    this.f7202d.m2273a((int) motionEvent.getRawX(), rawY);
                    this.f7203e.m2272a(this.f7201c);
                    this.f7205g = true;
                    this.f7199a = System.currentTimeMillis();
                    break;
                case 1:
                    if (this.f7217s != null) {
                        this.f7200b = System.currentTimeMillis();
                        if (this.f7205g && this.f7200b - this.f7199a < 250) {
                            m2318a(view);
                        }
                        int i = this.f7201c.f7236c;
                        if (this.f7217s.getVisibility() != View.VISIBLE) {
                            this.f7222x.postDelayed(this.f7197A, 1500L);
                        } else if (this.f7204f) {
                            i += this.f7217s.getHeight();
                        }
                        Preferences.m2881g(i);
                        break;
                    }
                    break;
                case 2:
                    this.f7222x.removeCallbacks(this.f7197A);
                    m2305b(true, true);
                    int i2 = this.f7202d.f7236c - rawY;
                    this.f7201c.f7236c = this.f7203e.f7236c + i2;
                    int m7224d = DisplayUtils.m7224d() - this.f7219u.getHeight();
                    if (this.f7201c.f7236c < 0) {
                        this.f7201c.f7236c = 0;
                    } else if (this.f7201c.f7236c > m7224d) {
                        this.f7201c.f7236c = m7224d;
                    }
                    if (Math.abs(i2) > 10) {
                        this.f7205g = false;
                    }
                    m2320a(this.f7201c.m2274a(), this.f7201c.m2270b(), -1, -2);
                    break;
            }
        }
        return false;
    }

    /* renamed from: a */
    private void m2318a(View view) {
        if (view.equals(this.f7218t)) {
            this.f7222x.removeCallbacks(this.f7197A);
            this.f7222x.removeCallbacks(this.f7198B);
            m2305b(true, true);
            m2306b(true);
            this.f7222x.postDelayed(this.f7197A, 1500L);
            this.f7222x.postDelayed(this.f7198B, 1500L);
        } else if (view.equals(this.f7206h)) {
            this.f7222x.removeCallbacks(this.f7197A);
            m2284m();
        } else if (view.equals(this.f7214p)) {
            m2282o();
        } else if (view.equals(this.f7213o)) {
            m2285l();
        } else if (view.equals(this.f7209k)) {
            this.f7224z.sendBroadcast(new Intent(Action.STOP_LOCK_SCREEN));
            this.f7224z.startActivity(new Intent(Action.START_ENTRY).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (view.equals(this.f7211m)) {
            m2280q();
        } else if (view.equals(this.f7212n)) {
            m2279r();
        } else if (view.equals(this.f7216r)) {
            m2300c(false);
        } else if (view.equals(this.f7207i) || view.equals(this.f7208j) || view.equals(this.f7210l)) {
            m2308b(view);
        } else if (view instanceof ImageView) {
            m2302c(view);
        }
    }

    /* renamed from: b */
    private void m2308b(View view) {
        this.f7222x.removeCallbacks(this.f7198B);
        this.f7222x.postDelayed(this.f7198B, 1500L);
        if (view.equals(this.f7207i)) {
            BaseApplication.getApplication().startService(new Intent(BaseApplication.getApplication(), SupportService.class).putExtra("command", "previous_command"));
        } else if (view.equals(this.f7208j)) {
            BaseApplication.getApplication().startService(new Intent(BaseApplication.getApplication(), SupportService.class).putExtra("command", "next_command"));
        } else {
            BaseApplication.getApplication().startService(new Intent(BaseApplication.getApplication(), SupportService.class).putExtra("command", "pause_command"));
            m2312a(true);
        }
    }

    /* renamed from: l */
    private void m2285l() {
        MiniLyricManager.m2344a().m2337b(true);
    }

    /* renamed from: c */
    private void m2302c(View view) {
        int m2277a = this.f7215q.m2277a((ImageView) view);
        this.f7215q.m2276a(Integer.valueOf(m2277a));
        if (this.f7218t != null) {
            m2309b(m2277a);
        }
    }

    /* renamed from: m */
    private void m2284m() {
        if (this.f7217s.getVisibility() == View.VISIBLE) {
            m2300c(false);
        } else {
            m2281p();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n */
    public void m2283n() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300L);
        this.f7219u.setLayoutAnimation(new LayoutAnimationController(alphaAnimation));
    }

    /* renamed from: o */
    private void m2282o() {
        Toast.makeText(BaseApplication.getApplication(), R.string.mini_lyric_closed, Toast.LENGTH_SHORT).show();
        MiniLyricManager.m2344a().m2340a(true);
        Preferences.m2879g(false);
    }

    /* renamed from: p */
    private void m2281p() {
        if (this.f7217s.getVisibility() != View.VISIBLE) {
            this.f7217s.setVisibility(View.VISIBLE);
            int m7224d = DisplayUtils.m7224d() - this.f7218t.getHeight();
            this.f7219u.setVisibility(View.INVISIBLE);
            this.f7204f = this.f7201c.f7236c > (m7224d >> 1);
            this.f7219u.removeView(this.f7217s);
            this.f7219u.addView(this.f7217s, this.f7204f ? 1 : 0);
            this.f7222x.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.framework.support.minilyric.a.3
                @Override // java.lang.Runnable
                public void run() {
                    MiniLyric.this.f7219u.setVisibility(View.VISIBLE);
                    if (MiniLyric.this.f7204f) {
                        C2080b.m2268b(MiniLyric.this.f7201c, MiniLyric.this.f7217s.getHeight());
                        MiniLyric.this.m2320a(MiniLyric.this.f7201c.f7235b, MiniLyric.this.f7201c.f7236c, -1, -2);
                    }
                }
            }, 100L);
        }
    }

    /* renamed from: c */
    private void m2300c(boolean z) {
        LogUtils.m8388a("minilyric", "hideSettingPanel immediate = " + z);
        if (View.GONE != this.f7217s.getVisibility()) {
            if (z) {
                m2305b(false, false);
                if (this.f7217s.getVisibility() == View.VISIBLE && this.f7204f) {
                    C2080b.m2266c(this.f7201c, this.f7217s.getHeight());
                }
                this.f7217s.setVisibility(View.GONE);
            } else {
                this.f7222x.removeCallbacks(this.f7197A);
                this.f7222x.postDelayed(this.f7197A, 1500L);
            }
            if (this.f7221w.m2366a()) {
                this.f7219u.setVisibility(View.INVISIBLE);
            }
            if (!z) {
                this.f7222x.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.framework.support.minilyric.a.4
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!MiniLyric.this.f7223y) {
                            MiniLyric.this.f7219u.setVisibility(View.VISIBLE);
                        }
                        if (MiniLyric.this.f7204f) {
                            C2080b.m2266c(MiniLyric.this.f7201c, MiniLyric.this.f7217s.getVisibility() == View.VISIBLE ? MiniLyric.this.f7217s.getHeight() : 0);
                            MiniLyric.this.m2320a(MiniLyric.this.f7201c.m2274a(), MiniLyric.this.f7201c.m2270b(), -1, -2);
                        }
                        MiniLyric.this.f7217s.setVisibility(View.GONE);
                        MiniLyric.this.m2283n();
                    }
                }, 100L);
            }
        }
    }

    /* renamed from: q */
    private void m2280q() {
        int m3030Z = Preferences.m3030Z();
        if (m3030Z > 14) {
            int i = m3030Z - 1;
            LogUtils.m8388a("minilyric", "fontZoomOut fontSize=" + i);
            Preferences.m2891e(i);
            m2303c(i);
            this.f7218t.setTextSize(i);
        }
    }

    /* renamed from: r */
    private void m2279r() {
        int m3030Z = Preferences.m3030Z();
        if (m3030Z < 28) {
            int i = m3030Z + 1;
            LogUtils.m8388a("minilyric", "fontZoomIn fontSize=" + i);
            Preferences.m2891e(i);
            m2303c(i);
            if (this.f7218t != null) {
                this.f7218t.setTextSize(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MiniLyric.java */
    /* renamed from: com.sds.android.ttpod.framework.support.minilyric.a$b */
    /* loaded from: classes.dex */
    public static class C2080b {

        /* renamed from: b */
        private int f7235b;

        /* renamed from: c */
        private int f7236c;

        /* renamed from: b */
        static /* synthetic */ int m2268b(C2080b c2080b, int i) {
            int i2 = c2080b.f7236c - i;
            c2080b.f7236c = i2;
            return i2;
        }

        /* renamed from: c */
        static /* synthetic */ int m2266c(C2080b c2080b, int i) {
            int i2 = c2080b.f7236c + i;
            c2080b.f7236c = i2;
            return i2;
        }

        C2080b(int i, int i2) {
            this.f7235b = i;
            this.f7236c = i2;
        }

        /* renamed from: a */
        void m2273a(int i, int i2) {
            this.f7235b = i;
            this.f7236c = i2;
        }

        /* renamed from: a */
        void m2272a(C2080b c2080b) {
            this.f7235b = c2080b.f7235b;
            this.f7236c = c2080b.f7236c;
        }

        /* renamed from: a */
        int m2274a() {
            return this.f7235b;
        }

        /* renamed from: b */
        int m2270b() {
            return this.f7236c;
        }
    }

    /* renamed from: a */
    public void m2311a(boolean z, boolean z2) {
        LogUtils.m8388a("minilyric", "lockStateNotification " + z);
        m2297d(z);
        if (this.f7221w.m2366a()) {
            m2300c(false);
            m2320a(this.f7201c.m2274a(), this.f7201c.m2270b(), -1, -2);
        } else {
            m2300c(true);
        }
        if (!z2 || !m2304c()) {
            return;
        }
        Toast.makeText(BaseApplication.getApplication(), z ? R.string.mini_lyric_locked_long : R.string.mini_lyric_unlocked, Toast.LENGTH_SHORT).show();
    }

    /* renamed from: d */
    private void m2297d(boolean z) {
        String string;
        String string2;
        int i;
        Intent intent = new Intent(Action.MINI_LYRIC_LOCK_STATUS_CHANGED);
        if (z) {
            string = BaseApplication.getApplication().getString(R.string.mini_lyric_click_unlock);
            string2 = BaseApplication.getApplication().getString(R.string.mini_lyric_locked);
            i = R.drawable.img_button_minilyric_lock;
            intent.putExtra("is_locked", false);
        } else {
            string = BaseApplication.getApplication().getString(R.string.mini_lyric_click_lock);
            string2 = BaseApplication.getApplication().getString(R.string.mini_lyric_unlocked);
            int i2 = SDKVersionUtils.m8371c() ? R.drawable.img_button_minilyric_unlock_ics : R.drawable.img_button_minilyric_unlocked;
            intent.putExtra("is_locked", true);
            i = i2;
        }
        if (z) {
            Notification m4693a = NotificationUtils.m4693a(BaseApplication.getApplication(), i, string, string2, null, PendingIntent.getBroadcast(BaseApplication.getApplication(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
            m4693a.flags = 2;
            NotificationUtils.m4695a(12101710, m4693a);
        } else {
            NotificationUtils.m4696a(12101710);
        }
        Preferences.m2810z(z);
        m2294e(z);
    }

    /* renamed from: e */
    private void m2294e(boolean z) {
        if (z) {
            this.f7221w.m2354b(false);
        } else {
            this.f7221w.m2354b(true);
        }
    }
}
