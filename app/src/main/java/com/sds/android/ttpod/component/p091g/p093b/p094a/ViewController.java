package com.sds.android.ttpod.component.p091g.p093b.p094a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.p130c.SkinEventHandler;
import com.sds.android.ttpod.framework.modules.skin.p132d.Lyric;
import com.sds.android.ttpod.framework.modules.skin.view.AnimTransView;
import com.sds.android.ttpod.framework.modules.skin.view.Animation;
import com.sds.android.ttpod.framework.modules.skin.view.AutoScrollableTextView;
import com.sds.android.ttpod.framework.modules.skin.view.Icon;
import com.sds.android.ttpod.framework.modules.skin.view.LineVisualization;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;
import com.sds.android.ttpod.framework.modules.skin.view.MultiScreenLayout;
import com.sds.android.ttpod.framework.modules.skin.view.TTImageSwitcher;
import com.sds.android.ttpod.framework.modules.skin.view.TTPodButton;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.framework.support.p134a.PlayMode;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/* renamed from: com.sds.android.ttpod.component.g.b.a.b */
/* loaded from: classes.dex */
public class ViewController {

    /* renamed from: A */
    protected TextView f4189A;

    /* renamed from: B */
    protected TextView f4190B;

    /* renamed from: C */
    protected TextView f4191C;

    /* renamed from: D */
    protected TextView f4192D;

    /* renamed from: E */
    protected TextView f4193E;

    /* renamed from: F */
    protected TextView f4194F;

    /* renamed from: G */
    protected TextView f4195G;

    /* renamed from: H */
    protected SeekBar f4196H;

    /* renamed from: I */
    protected SeekBar f4197I;

    /* renamed from: J */
    protected LyricView f4198J;

    /* renamed from: K */
    protected LineVisualization f4199K;

    /* renamed from: L */
    protected AnimTransView f4200L;

    /* renamed from: M */
    protected TTImageSwitcher f4201M;

    /* renamed from: N */
    protected Icon f4202N;

    /* renamed from: O */
    protected Icon f4203O;

    /* renamed from: P */
    protected Icon f4204P;

    /* renamed from: Q */
    protected Icon f4205Q;

    /* renamed from: R */
    protected Icon f4206R;

    /* renamed from: S */
    protected Icon f4207S;

    /* renamed from: T */
    protected Animation f4208T;

    /* renamed from: U */
    protected PlayStatus f4209U;

    /* renamed from: V */
    protected PlayMode f4210V;

    /* renamed from: W */
    private Lyric f4211W;

    /* renamed from: ab */
    private String f4217ab;

    /* renamed from: c */
    private Bitmap f4220c;

    /* renamed from: d */
    protected String f4221d;

    /* renamed from: f */
    protected Context f4223f;

    /* renamed from: g */
    protected SkinEventHandler f4224g;

    /* renamed from: h */
    protected TTPodButton f4225h;

    /* renamed from: i */
    protected TTPodButton f4226i;

    /* renamed from: j */
    protected TTPodButton f4227j;

    /* renamed from: k */
    protected TTPodButton f4228k;

    /* renamed from: l */
    protected TTPodButton f4229l;

    /* renamed from: m */
    protected TTPodButton f4230m;

    /* renamed from: n */
    protected TTPodButton f4231n;

    /* renamed from: o */
    protected TTPodButton f4232o;

    /* renamed from: p */
    protected TTPodButton f4233p;

    /* renamed from: q */
    protected TTPodButton f4234q;

    /* renamed from: r */
    protected TTPodButton f4235r;

    /* renamed from: s */
    protected TTPodButton f4236s;

    /* renamed from: t */
    protected TTPodButton f4237t;

    /* renamed from: u */
    protected TTPodButton f4238u;

    /* renamed from: v */
    protected TTPodButton f4239v;

    /* renamed from: w */
    protected TTPodButton f4240w;

    /* renamed from: x */
    protected TTPodButton f4241x;

    /* renamed from: y */
    protected TTPodButton f4242y;

    /* renamed from: z */
    protected TextView f4243z;

    /* renamed from: a */
    private final StringBuilder f4215a = new StringBuilder();

    /* renamed from: b */
    private final Rect f4219b = new Rect();

    /* renamed from: X */
    private ArrayList<AutoScrollableTextView> f4212X = new ArrayList<>();

    /* renamed from: Y */
    private boolean f4213Y = false;

    /* renamed from: Z */
    private boolean f4214Z = false;

    /* renamed from: aa */
    private final View$OnClickListenerC1222a f4216aa = new View$OnClickListenerC1222a();

    /* renamed from: ac */
    private boolean f4218ac = false;

    /* renamed from: e */
    protected HashMap<String, View> f4222e = new HashMap<>();

    public ViewController(Context context, String str) {
        this.f4221d = str;
        this.f4223f = context;
    }

    /* renamed from: E */
    public String m6548E() {
        return this.f4221d;
    }

    /* renamed from: a */
    public View mo6445a(String str) {
        return this.f4222e.get(str);
    }

    /* renamed from: c */
    public void m6529c(View view) {
        if (view != null) {
            if (view.getTag() == null) {
                m6524e(view);
            }
            this.f4222e.put(view.getTag().toString(), view);
        }
    }

    /* renamed from: F */
    public LyricView m6547F() {
        return this.f4198J;
    }

    /* renamed from: G */
    public Collection<View> m6546G() {
        return this.f4222e.values();
    }

    /* renamed from: H */
    public void m6545H() {
        this.f4212X.clear();
        for (View view : m6546G()) {
            mo6437b(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo6511a(Object obj, TTPodButton tTPodButton) {
        if ("PlayButton".equals(obj)) {
            this.f4225h = tTPodButton;
            this.f4225h.setContentDescription("play_page_play");
        } else if ("PauseButton".equals(obj)) {
            this.f4228k = tTPodButton;
            this.f4228k.setContentDescription("play_page_pause");
            this.f4228k.setVisibility(View.INVISIBLE);
        } else if ("PrevSongButton".equals(obj)) {
            this.f4227j = tTPodButton;
            this.f4227j.setContentDescription("play_page_play_pre");
        } else if ("NextSongButton".equals(obj)) {
            this.f4226i = tTPodButton;
            this.f4226i.setContentDescription("play_page_play_next");
        } else if ("MenuButton".equals(obj)) {
            this.f4229l = tTPodButton;
        } else if ("ListButton".equals(obj)) {
            this.f4230m = tTPodButton;
            this.f4230m.setContentDescription("play_page_back");
        } else if ("PlayerButton".equals(obj)) {
            this.f4234q = tTPodButton;
        } else if ("ShareButton".equals(obj)) {
            this.f4235r = tTPodButton;
            this.f4235r.setContentDescription("play_page_share");
        } else if ("AddToButton".equals(obj)) {
            this.f4236s = tTPodButton;
        } else if ("RemoveButton".equals(obj)) {
            this.f4238u = tTPodButton;
        } else if ("RingtoneButton".equals(obj)) {
            this.f4237t = tTPodButton;
        } else if ("SendButton".equals(obj)) {
            this.f4239v = tTPodButton;
        } else if ("ChangeSkinButton".equals(obj)) {
            this.f4240w = tTPodButton;
        } else if ("InfoButton".equals(obj)) {
            this.f4241x = tTPodButton;
        } else if ("EqButton".equals(obj)) {
            this.f4242y = tTPodButton;
        } else if ("MoreButton".equals(obj)) {
            this.f4231n = tTPodButton;
        } else if ("PlaylistButton".equals(obj)) {
            this.f4232o = tTPodButton;
        } else if ("ClearProcess".equals(obj)) {
            this.f4233p = tTPodButton;
        }
        m6540a((View) tTPodButton, true);
    }

    /* renamed from: a */
    protected void m6536a(Object obj, TextView textView) {
        if ("Title".equals(obj)) {
            this.f4243z = textView;
        } else if ("Duration".equals(obj)) {
            this.f4191C = textView;
        } else if ("Lapse".equals(obj)) {
            this.f4192D = textView;
        } else if ("LapseDuration".equals(obj)) {
            this.f4193E = textView;
        } else if ("Album".equals(obj)) {
            this.f4189A = textView;
        } else if ("Artist".equals(obj)) {
            this.f4190B = textView;
        } else if ("BitRate".equals(obj)) {
            this.f4194F = textView;
        } else if ("SampleRate".equals(obj)) {
            this.f4195G = textView;
        } else if (textView instanceof AutoScrollableTextView) {
            AutoScrollableTextView autoScrollableTextView = (AutoScrollableTextView) textView;
            if (autoScrollableTextView.m3491a("Title") || autoScrollableTextView.m3491a("Artist") || autoScrollableTextView.m3491a("Album")) {
                this.f4212X.add(autoScrollableTextView);
            }
        }
    }

    /* renamed from: a */
    protected void m6535a(Object obj, Icon icon) {
        if ("RepeatIcon".equals(obj)) {
            this.f4202N = icon;
            this.f4202N.setContentDescription("play_page_play_mode");
        } else if ("SleepIcon".equals(obj)) {
            this.f4203O = icon;
        } else if ("VolumeIcon".equals(obj)) {
            this.f4204P = icon;
        } else if ("EQ".equals(obj)) {
            this.f4205Q = icon;
        } else if ("Info".equals(obj)) {
            this.f4206R = icon;
        } else if ("FavouriteIcon".equals(obj) || "FavoriteIcon".equals(obj)) {
            this.f4207S = icon;
            this.f4207S.setContentDescription("play_page_favorite");
        } else {
            return;
        }
        m6540a((View) icon, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void mo6437b(View view) {
        Object tag = view.getTag();
        if (view instanceof TTPodButton) {
            mo6511a(tag, (TTPodButton) view);
        } else if (view instanceof TextView) {
            m6536a(tag, (TextView) view);
        } else if (view instanceof SeekBar) {
            if ("Guage".equals(tag)) {
                this.f4196H = (SeekBar) view;
                this.f4196H.setContentDescription("play_page_progress_bar");
            } else if ("Volume".equals(tag)) {
                this.f4197I = (SeekBar) view;
            }
        } else if (view instanceof Icon) {
            m6535a(tag, (Icon) view);
        } else if ("AlbumCover".equals(tag)) {
            if (view instanceof TTImageSwitcher) {
                this.f4201M = (TTImageSwitcher) view;
                m6540a((View) this.f4201M, true);
            } else if (view instanceof AnimTransView) {
                this.f4200L = (AnimTransView) view;
                m6540a((View) this.f4200L, true);
            }
        } else if (view instanceof LyricView) {
            if ("LyricShow".equals(tag)) {
                this.f4198J = (LyricView) view;
            }
        } else if (view instanceof Animation) {
            if ("NetSearching".equals(tag)) {
                this.f4208T = (Animation) view;
            }
        } else if (view instanceof LineVisualization) {
            if ("Visualization".equals(tag)) {
                this.f4199K = (LineVisualization) view;
            }
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                mo6437b(viewGroup.getChildAt(childCount));
            }
        }
    }

    /* renamed from: b_ */
    public void mo6403b_() {
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.sds.android.ttpod.component.g.b.a.b.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar == ViewController.this.f4196H) {
                    if (ViewController.this.f4224g != null) {
                        ViewController.this.f4224g.mo3717a(4, null);
                    }
                    ViewController.this.f4214Z = false;
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (seekBar == ViewController.this.f4196H) {
                    ViewController.this.f4214Z = true;
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (ViewController.this.f4224g != null) {
                    if (seekBar == ViewController.this.f4197I) {
                        ViewController.this.f4224g.mo3717a(10, Integer.valueOf(i));
                    } else if (seekBar == ViewController.this.f4196H && ViewController.this.f4214Z) {
                        ViewController.this.f4224g.mo3717a(14, Integer.valueOf(i));
                    }
                }
            }
        };
        if (this.f4196H != null) {
            this.f4196H.setOnSeekBarChangeListener(onSeekBarChangeListener);
        }
        if (this.f4197I != null) {
            this.f4197I.setOnSeekBarChangeListener(onSeekBarChangeListener);
            if (this.f4204P != null) {
                this.f4197I.setVisibility(View.INVISIBLE);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m6540a(View view, boolean z) {
        if (z) {
            view.setOnClickListener(this.f4216aa);
            if (view instanceof TTPodButton) {
                ((TTPodButton) view).setRepeatListener(this.f4216aa);
                return;
            }
            return;
        }
        view.setOnClickListener(null);
        view.setClickable(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo6456a(View view) {
        int i = 0;
        if (this.f4224g != null) {
            if (view == this.f4225h) {
                i = 18;
            } else if (view == this.f4228k) {
                i = 19;
            } else if (view == this.f4226i) {
                i = 20;
            } else if (view == this.f4227j) {
                i = 21;
            } else if (view == this.f4242y || view == this.f4205Q) {
                i = 22;
            } else if (view == this.f4241x || view == this.f4206R) {
                i = 23;
            } else if (view == this.f4236s) {
                i = 24;
            } else if (view == this.f4239v) {
                i = 26;
            } else if (view == this.f4238u) {
                i = 27;
            } else if (view == this.f4240w) {
                i = 30;
            } else if (view == this.f4237t) {
                i = 28;
            } else if (view == this.f4204P) {
                if (this.f4197I != null) {
                    if (this.f4197I.getVisibility() == View.VISIBLE) {
                        this.f4197I.setVisibility(View.INVISIBLE);
                        this.f4204P.setState(0);
                        i = -1;
                    } else {
                        this.f4197I.setVisibility(View.VISIBLE);
                        this.f4204P.setState(1);
                        i = -1;
                    }
                }
                i = -1;
            } else if (view == this.f4203O) {
                i = 5;
            } else if (view == this.f4202N) {
                this.f4213Y = true;
                i = 6;
            } else if (view != this.f4229l) {
                if (view == this.f4230m) {
                    i = 1;
                } else if (view == this.f4234q) {
                    i = 2;
                } else if (view == this.f4235r) {
                    i = 25;
                } else if (view == this.f4207S) {
                    i = 11;
                } else if (view == this.f4232o) {
                    i = 3;
                } else if (view == this.f4233p) {
                    i = 33;
                } else {
                    if (view == this.f4231n || view == this.f4198J || (view instanceof MultiScreenLayout) || view == this.f4199K || view == this.f4201M || (view instanceof TTImageSwitcher) || view == this.f4200L || (view instanceof AnimTransView)) {
                        i = 29;
                    }
                    i = -1;
                }
            }
            if (-1 != i) {
                this.f4224g.mo3717a(i, null);
            }
        }
    }

    /* renamed from: c */
    protected void m6528c(View view, int i) {
        if (view == this.f4226i) {
            if (i == -1) {
                this.f4224g.mo3717a(4, null);
            } else {
                this.f4224g.mo3717a(15, Long.valueOf(Math.min(15000L, m6541a(this.f4226i.getRepeatInterval(), i))));
            }
        } else if (view == this.f4227j) {
            if (i == -1) {
                this.f4224g.mo3717a(4, null);
            } else {
                this.f4224g.mo3717a(15, Long.valueOf(-Math.min(15000L, m6541a(this.f4227j.getRepeatInterval(), i))));
            }
        }
    }

    /* renamed from: e */
    private Object m6524e(View view) {
        String view2 = view.toString();
        String substring = view2.substring(view2.lastIndexOf(".") + 1);
        view.setTag(substring);
        return substring;
    }

    /* renamed from: a */
    private long m6541a(long j, int i) {
        return (long) (500 + ((((float) j) / 5000.0f) * 15000.0f * i));
    }

    /* renamed from: a */
    public void mo6459a(long j, float f) {
        if (m6526d(this.f4196H) && !this.f4214Z) {
            this.f4196H.setProgress((int) j);
            this.f4196H.setSecondaryProgress((int) (this.f4196H.getMax() * f));
        }
        if (m6526d(this.f4192D) || m6526d(this.f4193E)) {
            String formatElapsedTime = DateUtils.formatElapsedTime(this.f4215a, TimeUnit.SECONDS.convert(j, TimeUnit.MILLISECONDS));
            if (m6526d(this.f4192D) && !TextUtils.equals(formatElapsedTime, this.f4192D.getText())) {
                this.f4192D.setText(formatElapsedTime);
            }
            if (this.f4217ab != null && m6526d(this.f4193E)) {
                String str = formatElapsedTime + " - " + this.f4217ab;
                if (!TextUtils.equals(str, this.f4193E.getText())) {
                    this.f4193E.setText(str);
                }
            }
        }
        if (m6526d(this.f4198J)) {
            this.f4198J.setPlayingTime(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public boolean m6526d(View view) {
        return view != null && view.getVisibility() == View.VISIBLE && view.getWidth() > 0 && view.getHeight() > 0 && view.getGlobalVisibleRect(this.f4219b);
    }

    /* renamed from: a */
    public void mo6446a(PlayStatus playStatus) {
        if (this.f4209U != playStatus) {
            this.f4209U = playStatus;
            m6531b(playStatus);
        }
    }

    /* renamed from: b */
    private void m6531b(PlayStatus playStatus) {
        if (this.f4225h != null) {
            this.f4225h.setVisibility(PlayStatus.STATUS_PLAYING == playStatus ? View.INVISIBLE : View.VISIBLE);
        }
        if (this.f4228k != null) {
            this.f4228k.setVisibility(PlayStatus.STATUS_PLAYING != playStatus ? View.INVISIBLE : View.VISIBLE);
        }
    }

    /* renamed from: a_ */
    public void mo6442a_(int i) {
        if (this.f4198J != null) {
            this.f4198J.m3431g();
        }
    }

    /* renamed from: a */
    public void mo6449a(PlayMode playMode) {
        this.f4210V = playMode;
        if (this.f4202N != null) {
            this.f4202N.setState(playMode.ordinal());
        }
        if (this.f4213Y) {
            this.f4213Y = false;
        }
    }

    /* renamed from: a */
    public void mo6448a(MediaItem mediaItem) {
        m6537a(mediaItem, false);
    }

    /* renamed from: a */
    public void mo6443a(boolean z) {
        if (this.f4207S != null) {
            this.f4207S.setState(z ? 1 : 0);
        }
    }

    /* renamed from: I */
    public PlayMode m6544I() {
        return this.f4210V;
    }

    /* renamed from: a */
    private void m6537a(MediaItem mediaItem, boolean z) {
        m6527c(mediaItem);
        m6532b(mediaItem);
        m6534b(mediaItem.getDuration().intValue());
        mo6443a(mediaItem.getFav());
        if (z && this.f4198J != null) {
            String m3193b = Cache.m3218a().m3193b(mediaItem);
            boolean m3453b = this.f4198J.m3453b(m3193b);
            LogUtils.m8386a("ViewController", "looklyricloading updateView %s want setState equalLyricFile=%b cachePath=%s", getClass().getSimpleName(), Boolean.valueOf(m3453b), m3193b);
            if (!m3453b) {
                this.f4198J.setState(1);
            }
        }
    }

    /* renamed from: b */
    private void m6532b(MediaItem mediaItem) {
        if (this.f4195G != null) {
            this.f4195G.setText(String.format("%.1f", Float.valueOf(mediaItem.getSampleRate().intValue() / 1000.0f)));
        }
        if (this.f4194F != null) {
            this.f4194F.setText(String.valueOf(mediaItem.getBitRate()));
        }
    }

    /* renamed from: c */
    private void m6527c(MediaItem mediaItem) {
        CharSequence validateString = TTTextUtils.validateString(this.f4223f, mediaItem.getTitle());
        CharSequence validateString2 = TTTextUtils.validateString(this.f4223f, mediaItem.getArtist());
        CharSequence validateString3 = TTTextUtils.validateString(this.f4223f, mediaItem.getAlbum());
        if (this.f4243z != null) {
            this.f4243z.setText(validateString);
        }
        if (this.f4190B != null) {
            this.f4190B.setText(validateString2);
        }
        if (this.f4189A != null) {
            this.f4189A.setText(validateString3);
        }
        Iterator<AutoScrollableTextView> it = this.f4212X.iterator();
        while (it.hasNext()) {
            it.next().m3490a("Title", validateString, "Artist", validateString2, "Album", validateString3);
        }
    }

    /* renamed from: b */
    private void m6534b(int i) {
        if (this.f4196H != null) {
            this.f4196H.setMax(i);
        }
        this.f4217ab = DateUtils.formatElapsedTime(this.f4215a, TimeUnit.SECONDS.convert(i, TimeUnit.MILLISECONDS));
        if (this.f4191C != null) {
            this.f4191C.setText(this.f4217ab);
        }
    }

    /* renamed from: J */
    public boolean m6543J() {
        return this.f4218ac;
    }

    /* renamed from: a */
    public void mo6447a(MediaItem mediaItem, Bitmap bitmap, Lyric lyric) {
        m6537a(mediaItem, true);
        if (this.f4198J != null) {
            this.f4198J.setFadeColor(Preferences.m3042T());
            this.f4198J.setKalaOK(Preferences.m3046R());
            this.f4198J.setMtvPostionDown(true);
            if (this.f4198J.getDisplayMode() != LyricView.EnumC1996a.MTV || !this.f4198J.m3443d()) {
                this.f4198J.setColorHighlight(Preferences.m3050P());
            }
            m6523h(Preferences.m3048Q());
        }
        if (this.f4220c != bitmap) {
            mo6457a(bitmap);
        }
        if (this.f4211W != lyric) {
            mo6451a(lyric);
        }
        this.f4218ac = true;
    }

    /* renamed from: d */
    public void mo6428d(int i) {
        if (this.f4198J != null) {
            if (this.f4198J.getDisplayMode() != LyricView.EnumC1996a.MTV || !this.f4198J.m3443d()) {
                this.f4198J.setColorHighlight(i);
            }
        }
    }

    /* renamed from: h */
    public void m6523h(int i) {
        if (this.f4198J != null) {
            this.f4198J.m3459b(0, this.f4198J.getDefaultFontSizeHighlight() + i);
            this.f4198J.m3482a(0, this.f4198J.getDefaultFontSizeNormal() + i);
        }
    }

    /* renamed from: d */
    public void m6525d(boolean z) {
        if (this.f4203O != null) {
            this.f4203O.setState(z ? 1 : 0);
        }
    }

    /* renamed from: a */
    public void mo6451a(Lyric lyric) {
        m6533b(lyric);
    }

    /* renamed from: m */
    public void mo6415m() {
        if (this.f4198J != null) {
            this.f4198J.setState(2);
        }
    }

    /* renamed from: h */
    public void mo6423h() {
        m6533b((Lyric) null);
        if (this.f4198J != null) {
            this.f4198J.setState(4);
        }
    }

    /* renamed from: j */
    public void mo6421j() {
        if (this.f4198J != null) {
            this.f4198J.setState(5);
        }
    }

    /* renamed from: k */
    public void mo6419k() {
        if (this.f4198J != null) {
            this.f4198J.setState(6);
        }
    }

    /* renamed from: l */
    public void mo6417l() {
        if (this.f4198J != null) {
            this.f4198J.setState(1);
        }
    }

    /* renamed from: i */
    public void mo6422i() {
        if (this.f4198J != null) {
            this.f4198J.setState(8);
        }
    }

    /* renamed from: b */
    private void m6533b(Lyric lyric) {
        this.f4211W = lyric;
        if (this.f4198J != null) {
            this.f4198J.setLyric(lyric);
            this.f4198J.setPlayingTime(SupportFactory.m2397a(BaseApplication.getApplication()).m2465k().intValue());
        }
    }

    /* renamed from: a */
    public void mo6457a(Bitmap bitmap) {
        if (this.f4201M != null || this.f4200L != null) {
            Object[] objArr = new Object[2];
            objArr[0] = Boolean.valueOf(bitmap != null);
            objArr[1] = getClass().getSimpleName();
            LogUtils.m8386a("ViewController", "loadArtistPicture artistPic bitmap!=null_%b page=%s", objArr);
        }
        m6530c(bitmap);
    }

    /* renamed from: b */
    public void mo6438b(Bitmap bitmap) {
        if (this.f4201M != null) {
            this.f4201M.setImageBitmap(bitmap);
        }
        if (this.f4200L != null) {
            this.f4200L.setImageBitmapDelay(bitmap);
        }
    }

    /* renamed from: p */
    public void mo6411p() {
    }

    /* renamed from: n */
    public void mo6413n() {
    }

    /* renamed from: o */
    public void mo6412o() {
    }

    /* renamed from: c */
    private void m6530c(Bitmap bitmap) {
        this.f4220c = bitmap;
        if (this.f4201M != null) {
            this.f4201M.setImageBitmap(bitmap);
        }
        if (this.f4200L != null) {
            this.f4200L.setImageBitmapDelay(bitmap);
        }
    }

    /* renamed from: b */
    public void mo6439b(int i, int i2) {
        if (this.f4197I != null) {
            this.f4197I.setMax(i2);
            this.f4197I.setProgress(i);
        }
    }

    /* renamed from: r */
    public void mo6404r() {
    }

    /* renamed from: q */
    public void mo6410q() {
    }

    /* renamed from: b */
    public void mo6441b() {
        this.f4225h = null;
        this.f4228k = null;
        this.f4227j = null;
        this.f4226i = null;
        this.f4243z = null;
        this.f4191C = null;
        this.f4192D = null;
        this.f4193E = null;
        this.f4196H = null;
        this.f4202N = null;
        this.f4203O = null;
        this.f4205Q = null;
        this.f4198J = null;
        this.f4200L = null;
        this.f4201M = null;
        this.f4224g = null;
    }

    /* renamed from: a */
    public void mo6452a(SkinEventHandler skinEventHandler) {
        this.f4224g = skinEventHandler;
    }

    /* renamed from: K */
    public LineVisualization m6542K() {
        return this.f4199K;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ViewController.java */
    /* renamed from: com.sds.android.ttpod.component.g.b.a.b$a */
    /* loaded from: classes.dex */
    public final class View$OnClickListenerC1222a implements View.OnClickListener, TTPodButton.InterfaceC2007a {
        private View$OnClickListenerC1222a() {
        }

        @Override // com.sds.android.ttpod.framework.modules.skin.view.TTPodButton.InterfaceC2007a
        /* renamed from: a */
        public void mo3357a(View view, long j, int i) {
            ViewController.this.m6528c(view, i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewController.this.mo6456a(view);
        }
    }
}
