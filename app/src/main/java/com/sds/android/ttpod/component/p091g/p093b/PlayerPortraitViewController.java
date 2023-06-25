package com.sds.android.ttpod.component.p091g.p093b;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import androidx.core.view.ViewCompat;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p091g.p092a.EventHandler;
import com.sds.android.ttpod.component.p091g.p093b.p094a.BasePlayerViewController;
import com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController;
import com.sds.android.ttpod.component.p091g.p093b.p095b.LyricColorPanel;
import com.sds.android.ttpod.component.p091g.p093b.p095b.LyricFontPanel;
import com.sds.android.ttpod.component.p091g.p093b.p095b.LyricToolMenu;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.SkinLayoutParams;
import com.sds.android.ttpod.framework.modules.skin.p129b.SComponent;
import com.sds.android.ttpod.framework.modules.skin.p129b.SEvent;
import com.sds.android.ttpod.framework.modules.skin.p129b.SPanel;
import com.sds.android.ttpod.framework.modules.skin.p129b.SPlayerView;
import com.sds.android.ttpod.framework.modules.skin.p130c.SkinEventHandler;
import com.sds.android.ttpod.framework.modules.skin.p132d.Lyric;
import com.sds.android.ttpod.framework.modules.skin.view.Icon;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;
import com.sds.android.ttpod.framework.modules.skin.view.MultiScreenLayout;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.p134a.PlayMode;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.component.g.b.e */
/* loaded from: classes.dex */
public class PlayerPortraitViewController extends BasePlayerViewController implements LyricColorPanel.InterfaceC1226b, LyricFontPanel.InterfaceC1229a, LyricToolMenu.InterfaceC1231a, MultiScreenLayout.InterfaceC2002b {

    /* renamed from: Y */
    private ArrayList<Icon> f4291Y;

    /* renamed from: Z */
    private int f4292Z;

    /* renamed from: a */
    private ArrayList<ViewEventController> f4293a;

    /* renamed from: aa */
    private ViewEventController f4294aa;

    /* renamed from: ab */
    private MultiScreenLayout f4295ab;

    /* renamed from: ac */
    private boolean f4296ac;

    /* renamed from: ad */
    private LyricToolMenu f4297ad;

    /* renamed from: ae */
    private LyricColorPanel f4298ae;

    /* renamed from: af */
    private LyricFontPanel f4299af;

    /* renamed from: b */
    private SparseIntArray f4300b;

    public PlayerPortraitViewController(Context context, SkinCache skinCache) {
        super(context, "Player");
        this.f4293a = new ArrayList<>(4);
        this.f4300b = new SparseIntArray(4);
        this.f4291Y = new ArrayList<>(4);
        this.f4292Z = 0;
        this.f4296ac = true;
        m6458a(context, skinCache);
        m6440b(Preferences.m3040U());
        this.f4297ad = new LyricToolMenu(context, m6418k(60), -1);
        this.f4297ad.m6481a(this);
        this.f4297ad.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.sds.android.ttpod.component.g.b.e.1
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                if (PlayerPortraitViewController.this.m6464M() != null) {
                    PlayerPortraitViewController.this.m6465L();
                }
            }
        });
        int m6418k = this.f4223f.getResources().getDisplayMetrics().widthPixels - m6418k(60);
        this.f4298ae = new LyricColorPanel(context, m6418k, -2);
        this.f4298ae.m6508a(this);
        this.f4299af = new LyricFontPanel(context, m6418k, m6418k(72));
        this.f4299af.m6494a(this);
    }

    /* renamed from: j */
    private void m6420j(int i) {
        PopupsUtils.m6721a(this.f4223f.getString(i > 0 ? R.string.lyric_delay : R.string.lyric_forward) + Math.abs(i / 1000.0f) + this.f4223f.getString(R.string.second));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L */
    public void m6465L() {
        int m3437e;
        LyricView m6464M = m6464M();
        if (m6464M != null && (m3437e = m6464M.m3437e()) != 0) {
            mo6442a_(m3437e);
            m6464M.m3465a(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M */
    public LyricView m6464M() {
        LyricView F = this.f4294aa != null ? this.f4294aa.m6547F() : null;
        return F != null ? F : this.f4198J;
    }

    /* renamed from: k */
    private int m6418k(int i) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, i, this.f4223f.getResources().getDisplayMetrics());
    }

    /* renamed from: a */
    private void m6458a(Context context, SkinCache skinCache) {
        ViewEventController mainPanelViewController;
        if (skinCache == null || skinCache.getSerializableSkin() == null) {
            throw new IllegalArgumentException("illegal SkinCache");
        }
        skinCache.m3581g();
        SPlayerView m3852a = skinCache.getSerializableSkin().m3852a(0);
        long currentTimeMillis = System.currentTimeMillis();
        LogUtils.debug("PlayerPortraitViewController", "create player views.");
        if (m3852a != null) {
            MultiScreenLayout multiScreenLayout = (MultiScreenLayout) m3852a.m3811c(context, skinCache);
            multiScreenLayout.setDrawingCacheBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            m6552c(m3852a.m3830b());
            SPanel[] c = m3852a.m3829c();
            if (c != null) {
                ViewEventController[] viewEventControllerArr = new ViewEventController[4];
                for (SPanel sPanel : c) {
                    String mo3794a = sPanel.mo3794a();
                    if (ThemeElement.PANEL_COMMON.equals(mo3794a)) {
                        mainPanelViewController = this;
                    } else if ("Main".equals(mo3794a)) {
                        MainPanelViewController mainPanelViewController2 = new MainPanelViewController(context, mo3794a);
                        viewEventControllerArr[2] = mainPanelViewController2;
                        mainPanelViewController = mainPanelViewController2;
                    } else if ("Visual".equals(mo3794a)) {
                        VisualizerPanelViewController visualizerPanelViewController = new VisualizerPanelViewController(context, mo3794a);
                        viewEventControllerArr[0] = visualizerPanelViewController;
                        mainPanelViewController = visualizerPanelViewController;
                    } else if ("Lyric".equals(mo3794a)) {
                        LyricPanelViewController lyricPanelViewController = new LyricPanelViewController(context, mo3794a);
                        viewEventControllerArr[3] = lyricPanelViewController;
                        mainPanelViewController = lyricPanelViewController;
                    } else if (!"Playing".equals(mo3794a)) {
                        mainPanelViewController = null;
                    } else {
                        PlayingPanelViewController playingPanelViewController = new PlayingPanelViewController(context, mo3794a);
                        viewEventControllerArr[1] = playingPanelViewController;
                        mainPanelViewController = playingPanelViewController;
                    }
                    if (mainPanelViewController != null) {
                        SComponent[] m3790b = sPanel.m3790b();
                        if (m3790b != null) {
                            for (SComponent sComponent : m3790b) {
                                View m3811c = sComponent.m3811c(context, skinCache);
                                if (m3811c != null) {
                                    mainPanelViewController.m6529c(m3811c);
                                }
                            }
                        }
                        SEvent[] m3789c = sPanel.m3789c();
                        if (m3789c != null) {
                            EventHandler eventHandler = new EventHandler(Looper.myLooper());
                            for (SEvent sEvent : m3789c) {
                                mainPanelViewController.m6517a(sEvent.m3803b(), eventHandler.m6562a(sEvent));
                            }
                        }
                    }
                }
                for (ViewEventController viewEventController : viewEventControllerArr) {
                    if (viewEventController != null) {
                        m6454a(viewEventController);
                    }
                }
                m6450a(multiScreenLayout, false);
                LogUtils.debug("PlayerPortraitViewController", "player views created. cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
            }
        }
        skinCache.handleClose();
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6452a(SkinEventHandler skinEventHandler) {
        super.mo6452a(skinEventHandler);
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6452a(skinEventHandler);
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a_ */
    public void mo6442a_(int i) {
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6442a_(i);
        }
        super.mo6442a_(i);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController, com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: b */
    public void mo6437b(View view) {
        Object tag;
        super.mo6437b(view);
        if ((view instanceof Icon) && (tag = view.getTag()) != null) {
            if ("MainIcon".equals(tag)) {
                view.setTag(R.id.tag_event_on_click, "Main");
            } else if ("LyricIcon".equals(tag)) {
                view.setTag(R.id.tag_event_on_click, "Lyric");
            } else if ("VisualIcon".equals(tag)) {
                view.setTag(R.id.tag_event_on_click, "Visual");
            } else if ("PlayingIcon".equals(tag)) {
                view.setTag(R.id.tag_event_on_click, "Playing");
            } else if (!"NavigationIcon".equals(tag)) {
                return;
            }
            view.setTag("NavigationIcon");
            m6540a(view, true);
            this.f4291Y.add((Icon) view);
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.BasePlayerViewController, com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: b */
    public void mo6441b() {
        super.mo6441b();
        if (this.f4295ab != null) {
            Preferences.m2897d(m6425g());
            this.f4295ab.setScreenChangeListener(null);
            this.f4295ab.removeAllViews();
            this.f4295ab.setMaxScreen(1);
        }
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6441b();
        }
        this.f4293a.clear();
        this.f4300b.clear();
        this.f4291Y.clear();
        this.f4297ad.dismiss();
        this.f4298ae.dismiss();
        this.f4299af.dismiss();
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.BasePlayerViewController
    /* renamed from: c */
    public void mo6432c() {
        PopupsUtils.m6723a(this.f4297ad);
        PopupsUtils.m6723a(this.f4298ae);
        PopupsUtils.m6723a(this.f4299af);
    }

    /* renamed from: a */
    public void m6454a(ViewEventController viewEventController) {
        m6460a(this.f4293a.size(), viewEventController);
    }

    /* renamed from: a */
    public void m6460a(int i, ViewEventController viewEventController) {
        this.f4293a.add(i, viewEventController);
        String E = viewEventController == null ? null : viewEventController.m6548E();
        if (E != null) {
            this.f4300b.put(E.hashCode(), i);
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public View mo6445a(String str) {
        if (!TextUtils.isEmpty(str)) {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf > 0) {
                String substring = str.substring(0, lastIndexOf);
                String substring2 = str.substring(lastIndexOf + 1);
                if (ThemeElement.PANEL_COMMON.equals(substring)) {
                    return super.mo6445a(substring2);
                }
                Iterator<ViewEventController> it = this.f4293a.iterator();
                while (it.hasNext()) {
                    ViewEventController next = it.next();
                    if (substring.equals(next.m6548E())) {
                        return next.mo6445a(substring2);
                    }
                }
            } else {
                return super.mo6445a(str);
            }
        }
        return null;
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController
    /* renamed from: b */
    public void mo6434b(String str) {
        if (!TextUtils.isEmpty(str) && this.f4293a != null) {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf > 0) {
                String substring = str.substring(0, lastIndexOf);
                String substring2 = str.substring(lastIndexOf + 1);
                if (ThemeElement.PANEL_COMMON.equals(substring)) {
                    super.mo6434b(substring2);
                    return;
                }
                Iterator<ViewEventController> it = this.f4293a.iterator();
                while (it.hasNext()) {
                    ViewEventController next = it.next();
                    if (substring.equals(next.m6548E())) {
                        next.mo6434b(substring2);
                        return;
                    }
                }
                return;
            }
            super.mo6434b(str);
        }
    }

    /* renamed from: d */
    public void m6429d() {
    }

    /* renamed from: g */
    public int m6425g() {
        return this.f4292Z;
    }

    /* renamed from: b */
    public void m6440b(int i) {
        if (this.f4295ab != null && i >= 0 && i < this.f4295ab.getChildCount()) {
            m6416l(i);
            this.f4295ab.m3406a(i);
        }
    }

    /* renamed from: l */
    private void m6416l(int i) {
        this.f4292Z = i;
        this.f4294aa = m6431c(i);
        m6462O();
        m6463N();
    }

    /* renamed from: N */
    private void m6463N() {
        String E = this.f4294aa == null ? null : this.f4294aa.m6548E();
        if ("Visual".equals(E)) {
            //LocalStatistic.m5134aT();
        } else if ("Lyric".equals(E)) {
            //LocalStatistic.m5133aU();
        }
    }

    /* renamed from: O */
    private void m6462O() {
        String E = this.f4294aa == null ? null : this.f4294aa.m6548E();
        Iterator<Icon> it = this.f4291Y.iterator();
        while (it.hasNext()) {
            Icon next = it.next();
            boolean equals = TextUtils.equals(E, String.valueOf(next.getTag(R.id.tag_event_on_click)));
            next.setState(equals ? 1 : 0);
            next.setEnabled(!equals);
        }
    }

    /* renamed from: c */
    public ViewEventController m6431c(int i) {
        int size = this.f4293a.size();
        if (this.f4296ac) {
            if (i < 0) {
                i = size - 1;
            } else if (i >= size) {
                i = 0;
            }
        }
        if (i < 0 || i >= size) {
            return null;
        }
        return this.f4293a.get(i);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6459a(long j, float f) {
        if (this.f4293a != null) {
            Iterator<ViewEventController> it = this.f4293a.iterator();
            while (it.hasNext()) {
                it.next().mo6459a(j, f);
            }
        }
        super.mo6459a(j, f);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController, com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6448a(MediaItem mediaItem) {
        super.mo6448a(mediaItem);
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6448a(mediaItem);
        }
        if (this.f4297ad.isShowing()) {
            this.f4297ad.dismiss();
        }
        if (this.f4299af.isShowing()) {
            this.f4299af.dismiss();
        }
        if (this.f4298ae.isShowing()) {
            this.f4298ae.dismiss();
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6447a(MediaItem mediaItem, Bitmap bitmap, Lyric lyric) {
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6447a(mediaItem, bitmap, lyric);
        }
        super.mo6447a(mediaItem, bitmap, lyric);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: d */
    public void mo6428d(int i) {
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6428d(i);
        }
        super.mo6428d(i);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController, com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6446a(PlayStatus playStatus) {
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6446a(playStatus);
        }
        super.mo6446a(playStatus);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6443a(boolean z) {
        super.mo6443a(z);
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6443a(z);
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: h */
    public void mo6423h() {
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6423h();
        }
        super.mo6423h();
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: i */
    public void mo6422i() {
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6422i();
        }
        super.mo6422i();
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: j */
    public void mo6421j() {
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6421j();
        }
        super.mo6421j();
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: k */
    public void mo6419k() {
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6419k();
        }
        super.mo6419k();
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: l */
    public void mo6417l() {
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6417l();
        }
        super.mo6417l();
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: m */
    public void mo6415m() {
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6415m();
        }
        super.mo6415m();
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController, com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6451a(Lyric lyric) {
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6451a(lyric);
        }
        super.mo6451a(lyric);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController, com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6457a(Bitmap bitmap) {
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6457a(bitmap);
        }
        super.mo6457a(bitmap);
        m6430c(bitmap);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: b */
    public void mo6438b(Bitmap bitmap) {
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6438b(bitmap);
        }
        super.mo6438b(bitmap);
        m6430c(bitmap);
    }

    /* renamed from: c */
    private void m6430c(Bitmap bitmap) {
        if (this.f4295ab != null) {
            this.f4295ab.setSecondBackgroundBitmap(bitmap);
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: n */
    public void mo6413n() {
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6413n();
        }
        super.mo6413n();
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: o */
    public void mo6412o() {
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6412o();
        }
        super.mo6412o();
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: p */
    public void mo6411p() {
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6411p();
        }
        super.mo6411p();
    }

    /* renamed from: a */
    private void m6450a(MultiScreenLayout multiScreenLayout, boolean z) {
        if (multiScreenLayout != null) {
            if (multiScreenLayout != this.f4295ab || z) {
                m6444a(m6546G(), multiScreenLayout, -1);
                multiScreenLayout.setMaxScreen(this.f4293a.size());
                Iterator<ViewEventController> it = this.f4293a.iterator();
                int i = 0;
                while (it.hasNext()) {
                    ViewEventController next = it.next();
                    next.m6545H();
                    int i2 = i + 1;
                    m6444a(next.m6546G(), multiScreenLayout, i);
                    next.mo6403b_();
                    if (next instanceof PanelViewController) {
                        ((PanelViewController) next).m6470a(this);
                        if (next instanceof VisualizerPanelViewController) {
                            this.f4185c = next.m6542K();
                        }
                    }
                    i = i2;
                }
                this.f4295ab = multiScreenLayout;
                m6540a((View) this.f4295ab, true);
                m6545H();
                mo6403b_();
                this.f4295ab.setScreenChangeListener(this);
                this.f4295ab.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController, com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6456a(View view) {
        Object tag;
        int i;
        if ("NavigationIcon".equals(view.getTag()) && (tag = view.getTag(R.id.tag_event_on_click)) != null && (i = this.f4300b.get(tag.hashCode(), -1)) >= 0) {
            m6440b(i);
        } else if ((view == this.f4295ab || view == this.f4200L) && this.f4294aa != null) {
            this.f4294aa.mo6456a(view);
        } else {
            super.mo6456a(view);
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.BasePlayerViewController
    /* renamed from: a */
    public View mo6461a() {
        return this.f4295ab;
    }

    /* renamed from: a */
    private void m6444a(Collection<View> collection, MultiScreenLayout multiScreenLayout, int i) {
        if (collection != null) {
            for (View view : collection) {
                SkinLayoutParams m3559a = SkinLayoutParams.m3559a(view);
                m3559a.m3548e(i);
                int m3547f = m3559a.m3547f();
                int childCount = multiScreenLayout.getChildCount() - 1;
                while (childCount >= 0) {
                    multiScreenLayout.getChildAt(childCount);
                    SkinLayoutParams m3559a2 = SkinLayoutParams.m3559a(multiScreenLayout.getChildAt(childCount));
                    if (m3547f > (m3559a2 == null ? 0 : m3559a2.m3547f())) {
                        break;
                    }
                    childCount--;
                }
                if (view.getParent() == null) {
                    multiScreenLayout.addView(view, childCount + 1);
                }
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.view.MultiScreenLayout.InterfaceC2002b
    /* renamed from: a */
    public void mo3376a(int i, int i2) {
        if (i != i2) {
            int size = this.f4293a.size();
            int width = this.f4295ab.getWidth();
            for (int i3 = 0; i3 < size; i3++) {
                this.f4293a.get(i3).m6512i((i3 - i) * width);
            }
            m6416l(i);
            if (i2 >= 0 && i2 < size) {
                this.f4293a.get(i2).mo6410q();
            }
            if (this.f4294aa != null) {
                this.f4294aa.mo6404r();
            }
        }
        if (m6557C()) {
            m6558B();
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController, com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6449a(PlayMode playMode) {
        super.mo6449a(playMode);
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6449a(playMode);
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: b */
    public void mo6439b(int i, int i2) {
        super.mo6439b(i, i2);
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().mo6439b(i, i2);
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.BasePlayerViewController, com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController, com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: q */
    public void mo6410q() {
        super.mo6410q();
        mo6461a().setKeepScreenOn(false);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.BasePlayerViewController, com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController, com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: r */
    public void mo6404r() {
        super.mo6404r();
        mo6461a().setKeepScreenOn(Preferences.m2811z());
        m6463N();
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p095b.LyricFontPanel.InterfaceC1229a
    /* renamed from: e */
    public void mo6427e(int i) {
        Iterator<ViewEventController> it = this.f4293a.iterator();
        while (it.hasNext()) {
            it.next().m6523h(i);
        }
        super.m6523h(i);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p095b.LyricToolMenu.InterfaceC1231a
    /* renamed from: s */
    public void mo6409s() {
        m6414m(-500);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p095b.LyricToolMenu.InterfaceC1231a
    /* renamed from: t */
    public void mo6408t() {
        m6414m(500);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p095b.LyricToolMenu.InterfaceC1231a
    /* renamed from: u */
    public void mo6407u() {
        m6414m(0);
        PopupsUtils.m6760a((int) R.string.lyric_reset);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p095b.LyricToolMenu.InterfaceC1231a
    /* renamed from: a */
    public void mo6455a(View view, int i) {
        this.f4299af.showAtLocation(view, 49, -40, i);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p095b.LyricToolMenu.InterfaceC1231a
    /* renamed from: b */
    public void mo6436b(View view, int i) {
        this.f4298ae.showAtLocation(view, 49, -40, i);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p095b.LyricToolMenu.InterfaceC1231a
    /* renamed from: b */
    public void mo6433b(boolean z) {
        Preferences.m2812y(z);
        if (this.f4294aa instanceof LyricPanelViewController) {
            m6464M().setKalaOK(z);
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p095b.LyricToolMenu.InterfaceC1231a
    /* renamed from: v */
    public void mo6406v() {
        this.f4224g.mo3717a(32, null);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p095b.LyricColorPanel.InterfaceC1226b
    /* renamed from: f */
    public void mo6426f(int i) {
        Preferences.m2942b(i);
        mo6428d(i);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p095b.LyricFontPanel.InterfaceC1229a
    /* renamed from: g */
    public void mo6424g(int i) {
        if (this.f4294aa != null) {
            this.f4294aa.m6523h(i);
        }
    }

    /* renamed from: w */
    public void m6405w() {
        if (this.f4295ab != null) {
            if (this.f4294aa instanceof LyricPanelViewController) {
                this.f4297ad.m6483a(0);
            } else {
                this.f4297ad.m6483a(4);
            }
            this.f4297ad.showAtLocation(this.f4295ab, 21, 0, 0);
        }
    }

    /* renamed from: m */
    private void m6414m(int i) {
        LyricView m6464M = m6464M();
        if (m6464M != null) {
            if (i == 0) {
                m6464M.m3434f();
            } else {
                m6420j(m6464M.m3483a(i));
            }
        }
    }
}
