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
import com.sds.android.ttpod.framework.modules.skin.lyric.Lyric;
import com.sds.android.ttpod.framework.modules.skin.view.AnimTransView;
import com.sds.android.ttpod.framework.modules.skin.view.AnimationImageView;
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
    protected TextView albumView;

    /* renamed from: B */
    protected TextView artistView;

    /* renamed from: C */
    protected TextView durationView;

    /* renamed from: D */
    protected TextView lapseView;

    /* renamed from: E */
    protected TextView lapseDuration;

    /* renamed from: F */
    protected TextView bitrateView;

    /* renamed from: G */
    protected TextView sampleRateView;

    /* renamed from: H */
    protected SeekBar audioProgressSeekbar;

    /* renamed from: I */
    protected SeekBar volumeSeekView;

    /* renamed from: J */
    protected LyricView lyricShowView;

    /* renamed from: K */
    protected LineVisualization visualizationView;

    /* renamed from: L */
    protected AnimTransView albumCoverAnimTransView;

    /* renamed from: M */
    protected TTImageSwitcher albumCoverSwitcher;

    /* renamed from: N */
    protected Icon repeatIcon;

    /* renamed from: O */
    protected Icon sleepIcon;

    /* renamed from: P */
    protected Icon volumeIcon;

    /* renamed from: Q */
    protected Icon eqIcon;

    /* renamed from: R */
    protected Icon infoIcon;

    /* renamed from: S */
    protected Icon favoriteIcon;

    /* renamed from: T */
    protected AnimationImageView netSearchingView;

    /* renamed from: U */
    protected PlayStatus f4209U;

    /* renamed from: V */
    protected PlayMode playMode;

    /* renamed from: W */
    private Lyric f4211W;

    /* renamed from: ab */
    private String f4217ab;

    /* renamed from: c */
    private Bitmap f4220c;

    /* renamed from: d */
    protected String controllerName;

    /* renamed from: f */
    protected Context context;

    /* renamed from: g */
    protected SkinEventHandler skinEventHandler;

    /* renamed from: h */
    protected TTPodButton playButton;

    /* renamed from: i */
    protected TTPodButton nextSongButton;

    /* renamed from: j */
    protected TTPodButton prevSongButton;

    /* renamed from: k */
    protected TTPodButton pauseButton;

    /* renamed from: l */
    protected TTPodButton menuButton;

    /* renamed from: m */
    protected TTPodButton listButton;

    /* renamed from: n */
    protected TTPodButton moreButton;

    /* renamed from: o */
    protected TTPodButton playlistButton;

    /* renamed from: p */
    protected TTPodButton clearProcessButton;

    /* renamed from: q */
    protected TTPodButton playerButton;

    /* renamed from: r */
    protected TTPodButton shareButton;

    /* renamed from: s */
    protected TTPodButton addToButton;

    /* renamed from: t */
    protected TTPodButton ringtoneButton;

    /* renamed from: u */
    protected TTPodButton removeButton;

    /* renamed from: v */
    protected TTPodButton sendButton;

    /* renamed from: w */
    protected TTPodButton changeSkinButton;

    /* renamed from: x */
    protected TTPodButton infoButton;

    /* renamed from: y */
    protected TTPodButton eqButton;

    /* renamed from: z */
    protected TextView titleView;

    /* renamed from: a */
    private final StringBuilder f4215a = new StringBuilder();

    /* renamed from: b */
    private final Rect f4219b = new Rect();

    /* renamed from: X */
    private ArrayList<AutoScrollableTextView> autoScrollableTextViews = new ArrayList<>();

    /* renamed from: Y */
    private boolean f4213Y = false;

    /* renamed from: Z */
    private boolean audioProgressSeekbarTouch = false;

    /* renamed from: aa */
    private final View$OnClickListenerC1222a f4216aa = new View$OnClickListenerC1222a();

    /* renamed from: ac */
    private boolean f4218ac = false;

    /* renamed from: e */
    protected HashMap<String, View> keyViewMaps = new HashMap<>();

    public ViewController(Context context, String str) {
        this.controllerName = str;
        this.context = context;
    }

    /* renamed from: E */
    public String getControllerName() {
        return this.controllerName;
    }

    /* renamed from: a */
    public View mo6445a(String str) {
        return this.keyViewMaps.get(str);
    }

    /* renamed from: c */
    public void m6529c(View view) {
        if (view != null) {
            if (view.getTag() == null) {
                m6524e(view);
            }
            this.keyViewMaps.put(view.getTag().toString(), view);
        }
    }

    /* renamed from: F */
    public LyricView getLyricShowView() {
        return this.lyricShowView;
    }

    /* renamed from: G */
    public Collection<View> getViews() {
        return this.keyViewMaps.values();
    }

    /* renamed from: H */
    public void m6545H() {
        this.autoScrollableTextViews.clear();
        for (View view : getViews()) {
            initSetView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void initTTPodButtonView(Object obj, TTPodButton tTPodButton) {
        if ("PlayButton".equals(obj)) {
            this.playButton = tTPodButton;
            this.playButton.setContentDescription("play_page_play");
        } else if ("PauseButton".equals(obj)) {
            this.pauseButton = tTPodButton;
            this.pauseButton.setContentDescription("play_page_pause");
            this.pauseButton.setVisibility(View.INVISIBLE);
        } else if ("PrevSongButton".equals(obj)) {
            this.prevSongButton = tTPodButton;
            this.prevSongButton.setContentDescription("play_page_play_pre");
        } else if ("NextSongButton".equals(obj)) {
            this.nextSongButton = tTPodButton;
            this.nextSongButton.setContentDescription("play_page_play_next");
        } else if ("MenuButton".equals(obj)) {
            this.menuButton = tTPodButton;
        } else if ("ListButton".equals(obj)) {
            this.listButton = tTPodButton;
            this.listButton.setContentDescription("play_page_back");
        } else if ("PlayerButton".equals(obj)) {
            this.playerButton = tTPodButton;
        } else if ("ShareButton".equals(obj)) {
            this.shareButton = tTPodButton;
            this.shareButton.setContentDescription("play_page_share");
        } else if ("AddToButton".equals(obj)) {
            this.addToButton = tTPodButton;
        } else if ("RemoveButton".equals(obj)) {
            this.removeButton = tTPodButton;
        } else if ("RingtoneButton".equals(obj)) {
            this.ringtoneButton = tTPodButton;
        } else if ("SendButton".equals(obj)) {
            this.sendButton = tTPodButton;
        } else if ("ChangeSkinButton".equals(obj)) {
            this.changeSkinButton = tTPodButton;
        } else if ("InfoButton".equals(obj)) {
            this.infoButton = tTPodButton;
        } else if ("EqButton".equals(obj)) {
            this.eqButton = tTPodButton;
        } else if ("MoreButton".equals(obj)) {
            this.moreButton = tTPodButton;
        } else if ("PlaylistButton".equals(obj)) {
            this.playlistButton = tTPodButton;
        } else if ("ClearProcess".equals(obj)) {
            this.clearProcessButton = tTPodButton;
        }
        setRepeatListener((View) tTPodButton, true);
    }

    /* renamed from: a */
    protected void m6536a(Object obj, TextView textView) {
        if ("Title".equals(obj)) {
            this.titleView = textView;
        } else if ("Duration".equals(obj)) {
            this.durationView = textView;
        } else if ("Lapse".equals(obj)) {
            this.lapseView = textView;
        } else if ("LapseDuration".equals(obj)) {
            this.lapseDuration = textView;
        } else if ("Album".equals(obj)) {
            this.albumView = textView;
        } else if ("Artist".equals(obj)) {
            this.artistView = textView;
        } else if ("BitRate".equals(obj)) {
            this.bitrateView = textView;
        } else if ("SampleRate".equals(obj)) {
            this.sampleRateView = textView;
        } else if (textView instanceof AutoScrollableTextView) {
            AutoScrollableTextView autoScrollableTextView = (AutoScrollableTextView) textView;
            if (autoScrollableTextView.m3491a("Title")
                    || autoScrollableTextView.m3491a("Artist")
                    || autoScrollableTextView.m3491a("Album")) {
                this.autoScrollableTextViews.add(autoScrollableTextView);
            }
        }
    }

    /* renamed from: a */
    protected void m6535a(Object obj, Icon icon) {
        if ("RepeatIcon".equals(obj)) {
            this.repeatIcon = icon;
            this.repeatIcon.setContentDescription("play_page_play_mode");
        } else if ("SleepIcon".equals(obj)) {
            this.sleepIcon = icon;
        } else if ("VolumeIcon".equals(obj)) {
            this.volumeIcon = icon;
        } else if ("EQ".equals(obj)) {
            this.eqIcon = icon;
        } else if ("Info".equals(obj)) {
            this.infoIcon = icon;
        } else if ("FavouriteIcon".equals(obj) || "FavoriteIcon".equals(obj)) {
            this.favoriteIcon = icon;
            this.favoriteIcon.setContentDescription("play_page_favorite");
        } else {
            return;
        }
        setRepeatListener((View) icon, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void initSetView(View view) {
        Object tag = view.getTag();
        if (view instanceof TTPodButton) {
            initTTPodButtonView(tag, (TTPodButton) view);
        } else if (view instanceof TextView) {
            m6536a(tag, (TextView) view);
        } else if (view instanceof SeekBar) {
            if ("Guage".equals(tag)) {
                this.audioProgressSeekbar = (SeekBar) view;
                this.audioProgressSeekbar.setContentDescription("play_page_progress_bar");
            } else if ("Volume".equals(tag)) {
                this.volumeSeekView = (SeekBar) view;
            }
        } else if (view instanceof Icon) {
            m6535a(tag, (Icon) view);
        } else if ("AlbumCover".equals(tag)) {
            if (view instanceof TTImageSwitcher) {
                this.albumCoverSwitcher = (TTImageSwitcher) view;
                setRepeatListener((View) this.albumCoverSwitcher, true);
            } else if (view instanceof AnimTransView) {
                this.albumCoverAnimTransView = (AnimTransView) view;
                setRepeatListener((View) this.albumCoverAnimTransView, true);
            }
        } else if (view instanceof LyricView) {
            if ("LyricShow".equals(tag)) {
                this.lyricShowView = (LyricView) view;
            }
        } else if (view instanceof AnimationImageView) {
            if ("NetSearching".equals(tag)) {
                this.netSearchingView = (AnimationImageView) view;
            }
        } else if (view instanceof LineVisualization) {
            if ("Visualization".equals(tag)) {
                this.visualizationView = (LineVisualization) view;
            }
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                initSetView(viewGroup.getChildAt(childCount));
            }
        }
    }

    /* renamed from: b_ */
    public void mo6403b_() {
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.sds.android.ttpod.component.g.b.a.b.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar == ViewController.this.audioProgressSeekbar) {
                    if (ViewController.this.skinEventHandler != null) {
                        ViewController.this.skinEventHandler.mo3717a(4, null);
                    }
                    ViewController.this.audioProgressSeekbarTouch = false;
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (seekBar == ViewController.this.audioProgressSeekbar) {
                    ViewController.this.audioProgressSeekbarTouch = true;
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (ViewController.this.skinEventHandler != null) {
                    if (seekBar == ViewController.this.volumeSeekView) {
                        ViewController.this.skinEventHandler.mo3717a(10, Integer.valueOf(i));
                    } else if (seekBar == ViewController.this.audioProgressSeekbar && ViewController.this.audioProgressSeekbarTouch) {
                        ViewController.this.skinEventHandler.mo3717a(14, Integer.valueOf(i));
                    }
                }
            }
        };
        if (this.audioProgressSeekbar != null) {
            this.audioProgressSeekbar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        }
        if (this.volumeSeekView != null) {
            this.volumeSeekView.setOnSeekBarChangeListener(onSeekBarChangeListener);
            if (this.volumeIcon != null) {
                this.volumeSeekView.setVisibility(View.INVISIBLE);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void setRepeatListener(View view, boolean z) {
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
        if (this.skinEventHandler != null) {
            if (view == this.playButton) {
                i = 18;
            } else if (view == this.pauseButton) {
                i = 19;
            } else if (view == this.nextSongButton) {
                i = 20;
            } else if (view == this.prevSongButton) {
                i = 21;
            } else if (view == this.eqButton || view == this.eqIcon) {
                i = 22;
            } else if (view == this.infoButton || view == this.infoIcon) {
                i = 23;
            } else if (view == this.addToButton) {
                i = 24;
            } else if (view == this.sendButton) {
                i = 26;
            } else if (view == this.removeButton) {
                i = 27;
            } else if (view == this.changeSkinButton) {
                i = 30;
            } else if (view == this.ringtoneButton) {
                i = 28;
            } else if (view == this.volumeIcon) {
                if (this.volumeSeekView != null) {
                    if (this.volumeSeekView.getVisibility() == View.VISIBLE) {
                        this.volumeSeekView.setVisibility(View.INVISIBLE);
                        this.volumeIcon.setState(0);
                        i = -1;
                    } else {
                        this.volumeSeekView.setVisibility(View.VISIBLE);
                        this.volumeIcon.setState(1);
                        i = -1;
                    }
                }
                i = -1;
            } else if (view == this.sleepIcon) {
                i = 5;
            } else if (view == this.repeatIcon) {
                this.f4213Y = true;
                i = 6;
            } else if (view != this.menuButton) {
                if (view == this.listButton) {
                    i = 1;
                } else if (view == this.playerButton) {
                    i = 2;
                } else if (view == this.shareButton) {
                    i = 25;
                } else if (view == this.favoriteIcon) {
                    i = 11;
                } else if (view == this.playlistButton) {
                    i = 3;
                } else if (view == this.clearProcessButton) {
                    i = 33;
                } else {
                    if (view == this.moreButton || view == this.lyricShowView || (view instanceof MultiScreenLayout) || view == this.visualizationView || view == this.albumCoverSwitcher || (view instanceof TTImageSwitcher) || view == this.albumCoverAnimTransView || (view instanceof AnimTransView)) {
                        i = 29;
                    }
                    i = -1;
                }
            }
            if (-1 != i) {
                this.skinEventHandler.mo3717a(i, null);
            }
        }
    }

    /* renamed from: c */
    protected void m6528c(View view, int i) {
        if (view == this.nextSongButton) {
            if (i == -1) {
                this.skinEventHandler.mo3717a(4, null);
            } else {
                this.skinEventHandler.mo3717a(15, Long.valueOf(Math.min(15000L, m6541a(this.nextSongButton.getRepeatInterval(), i))));
            }
        } else if (view == this.prevSongButton) {
            if (i == -1) {
                this.skinEventHandler.mo3717a(4, null);
            } else {
                this.skinEventHandler.mo3717a(15, Long.valueOf(-Math.min(15000L, m6541a(this.prevSongButton.getRepeatInterval(), i))));
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
    public void mo6459a(long playingTime, float progress) {
        if (viewVisible(this.audioProgressSeekbar) && !this.audioProgressSeekbarTouch) {
            this.audioProgressSeekbar.setProgress((int) playingTime);
            this.audioProgressSeekbar.setSecondaryProgress((int) (this.audioProgressSeekbar.getMax() * progress));
        }
        if (viewVisible(this.lapseView) || viewVisible(this.lapseDuration)) {
            String formatElapsedTime = DateUtils.formatElapsedTime(this.f4215a, TimeUnit.SECONDS.convert(playingTime, TimeUnit.MILLISECONDS));
            if (viewVisible(this.lapseView) && !TextUtils.equals(formatElapsedTime, this.lapseView.getText())) {
                this.lapseView.setText(formatElapsedTime);
            }
            if (this.f4217ab != null && viewVisible(this.lapseDuration)) {
                String str = formatElapsedTime + " - " + this.f4217ab;
                if (!TextUtils.equals(str, this.lapseDuration.getText())) {
                    this.lapseDuration.setText(str);
                }
            }
        }
        if (viewVisible(this.lyricShowView)) {
            this.lyricShowView.setPlayingTime(playingTime);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public boolean viewVisible(View view) {
        return view != null && view.getVisibility() == View.VISIBLE
                && view.getWidth() > 0 && view.getHeight() > 0
                /*&& view.getGlobalVisibleRect(this.f4219b)*/;
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
        if (this.playButton != null) {
            this.playButton.setVisibility(PlayStatus.STATUS_PLAYING == playStatus ? View.INVISIBLE : View.VISIBLE);
        }
        if (this.pauseButton != null) {
            this.pauseButton.setVisibility(PlayStatus.STATUS_PLAYING != playStatus ? View.INVISIBLE : View.VISIBLE);
        }
    }

    /* renamed from: a_ */
    public void mo6442a_(int i) {
        if (this.lyricShowView != null) {
            this.lyricShowView.m3431g();
        }
    }

    /* renamed from: a */
    public void onPlayModeChange(PlayMode playMode) {
        this.playMode = playMode;
        if (this.repeatIcon != null) {
            this.repeatIcon.setState(playMode.ordinal());
        }
        if (this.f4213Y) {
            this.f4213Y = false;
        }
    }

    /* renamed from: a */
    public void onMetaChange(MediaItem mediaItem) {
        m6537a(mediaItem, false);
    }

    /* renamed from: a */
    public void mo6443a(boolean z) {
        if (this.favoriteIcon != null) {
            this.favoriteIcon.setState(z ? 1 : 0);
        }
    }

    /* renamed from: I */
    public PlayMode getPlayMode() {
        return this.playMode;
    }

    /* renamed from: a */
    private void m6537a(MediaItem mediaItem, boolean z) {
        m6527c(mediaItem);
        m6532b(mediaItem);
        m6534b(mediaItem.getDuration().intValue());
        mo6443a(mediaItem.getFav());
        if (z && this.lyricShowView != null) {
            String m3193b = Cache.getInstance().m3193b(mediaItem);
            boolean m3453b = this.lyricShowView.m3453b(m3193b);
            LogUtils.debug("ViewController", "looklyricloading updateView %s want setState equalLyricFile=%b cachePath=%s", getClass().getSimpleName(), Boolean.valueOf(m3453b), m3193b);
            if (!m3453b) {
                this.lyricShowView.setState(1);
            }
        }
    }

    /* renamed from: b */
    private void m6532b(MediaItem mediaItem) {
        if (this.sampleRateView != null) {
            this.sampleRateView.setText(String.format("%.1f", Float.valueOf(mediaItem.getSampleRate().intValue() / 1000.0f)));
        }
        if (this.bitrateView != null) {
            this.bitrateView.setText(String.valueOf(mediaItem.getBitRate()));
        }
    }

    /* renamed from: c */
    private void m6527c(MediaItem mediaItem) {
        CharSequence validateString = TTTextUtils.validateString(this.context, mediaItem.getTitle());
        CharSequence validateString2 = TTTextUtils.validateString(this.context, mediaItem.getArtist());
        CharSequence validateString3 = TTTextUtils.validateString(this.context, mediaItem.getAlbum());
        if (this.titleView != null) {
            this.titleView.setText(validateString);
        }
        if (this.artistView != null) {
            this.artistView.setText(validateString2);
        }
        if (this.albumView != null) {
            this.albumView.setText(validateString3);
        }
        Iterator<AutoScrollableTextView> it = this.autoScrollableTextViews.iterator();
        while (it.hasNext()) {
            it.next().m3490a("Title", validateString, "Artist", validateString2, "Album", validateString3);
        }
    }

    /* renamed from: b */
    private void m6534b(int i) {
        if (this.audioProgressSeekbar != null) {
            this.audioProgressSeekbar.setMax(i);
        }
        this.f4217ab = DateUtils.formatElapsedTime(this.f4215a, TimeUnit.SECONDS.convert(i, TimeUnit.MILLISECONDS));
        if (this.durationView != null) {
            this.durationView.setText(this.f4217ab);
        }
    }

    /* renamed from: J */
    public boolean m6543J() {
        return this.f4218ac;
    }

    /* renamed from: a */
    public void mo6447a(MediaItem mediaItem, Bitmap bitmap, Lyric lyric) {
        m6537a(mediaItem, true);
        if (this.lyricShowView != null) {
            this.lyricShowView.setFadeColor(Preferences.m3042T());
            this.lyricShowView.setKalaOK(Preferences.kalaOkEnabled());
            this.lyricShowView.setMtvPositionDown(true);
            if (this.lyricShowView.getDisplayMode() != LyricView.LyricDisplayEnum.MTV || !this.lyricShowView.getColorBySkin()) {
                this.lyricShowView.setColorHighlight(Preferences.getLyricHighlightColor());
            }
            m6523h(Preferences.getLyricFontSize());
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
        if (this.lyricShowView != null) {
            if (this.lyricShowView.getDisplayMode() != LyricView.LyricDisplayEnum.MTV || !this.lyricShowView.getColorBySkin()) {
                this.lyricShowView.setColorHighlight(i);
            }
        }
    }

    /* renamed from: h */
    public void m6523h(int i) {
        if (this.lyricShowView != null) {
            this.lyricShowView.m3459b(0, this.lyricShowView.getDefaultFontSizeHighlight() + i);
            this.lyricShowView.m3482a(0, this.lyricShowView.getDefaultFontSizeNormal() + i);
        }
    }

    /* renamed from: d */
    public void m6525d(boolean z) {
        if (this.sleepIcon != null) {
            this.sleepIcon.setState(z ? 1 : 0);
        }
    }

    /* renamed from: a */
    public void mo6451a(Lyric lyric) {
        m6533b(lyric);
    }

    /* renamed from: m */
    public void mo6415m() {
        if (this.lyricShowView != null) {
            this.lyricShowView.setState(2);
        }
    }

    /* renamed from: h */
    public void mo6423h() {
        m6533b((Lyric) null);
        if (this.lyricShowView != null) {
            this.lyricShowView.setState(4);
        }
    }

    /* renamed from: j */
    public void mo6421j() {
        if (this.lyricShowView != null) {
            this.lyricShowView.setState(5);
        }
    }

    /* renamed from: k */
    public void mo6419k() {
        if (this.lyricShowView != null) {
            this.lyricShowView.setState(6);
        }
    }

    /* renamed from: l */
    public void mo6417l() {
        if (this.lyricShowView != null) {
            this.lyricShowView.setState(1);
        }
    }

    /* renamed from: i */
    public void mo6422i() {
        if (this.lyricShowView != null) {
            this.lyricShowView.setState(8);
        }
    }

    /* renamed from: b */
    private void m6533b(Lyric lyric) {
        this.f4211W = lyric;
        if (this.lyricShowView != null) {
            this.lyricShowView.setLyric(lyric);
            this.lyricShowView.setPlayingTime(SupportFactory.getInstance(BaseApplication.getApplication()).m2465k().intValue());
        }
    }

    /* renamed from: a */
    public void mo6457a(Bitmap bitmap) {
        if (this.albumCoverSwitcher != null || this.albumCoverAnimTransView != null) {
            Object[] objArr = new Object[2];
            objArr[0] = Boolean.valueOf(bitmap != null);
            objArr[1] = getClass().getSimpleName();
            LogUtils.debug("ViewController", "loadArtistPicture artistPic bitmap!=null_%b page=%s", objArr);
        }
        m6530c(bitmap);
    }

    /* renamed from: b */
    public void mo6438b(Bitmap bitmap) {
        if (this.albumCoverSwitcher != null) {
            this.albumCoverSwitcher.setImageBitmap(bitmap);
        }
        if (this.albumCoverAnimTransView != null) {
            this.albumCoverAnimTransView.setImageBitmapDelay(bitmap);
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
        if (this.albumCoverSwitcher != null) {
            this.albumCoverSwitcher.setImageBitmap(bitmap);
        }
        if (this.albumCoverAnimTransView != null) {
            this.albumCoverAnimTransView.setImageBitmapDelay(bitmap);
        }
    }

    /* renamed from: b */
    public void mo6439b(int i, int i2) {
        if (this.volumeSeekView != null) {
            this.volumeSeekView.setMax(i2);
            this.volumeSeekView.setProgress(i);
        }
    }

    /* renamed from: r */
    public void onPanelShow() {
    }

    /* renamed from: q */
    public void onPanelDisappear() {
    }

    /* renamed from: b */
    public void mo6441b() {
        this.playButton = null;
        this.pauseButton = null;
        this.prevSongButton = null;
        this.nextSongButton = null;
        this.titleView = null;
        this.durationView = null;
        this.lapseView = null;
        this.lapseDuration = null;
        this.audioProgressSeekbar = null;
        this.repeatIcon = null;
        this.sleepIcon = null;
        this.eqIcon = null;
        this.lyricShowView = null;
        this.albumCoverAnimTransView = null;
        this.albumCoverSwitcher = null;
        this.skinEventHandler = null;
    }

    /* renamed from: a */
    public void mo6452a(SkinEventHandler skinEventHandler) {
        this.skinEventHandler = skinEventHandler;
    }

    /* renamed from: K */
    public LineVisualization m6542K() {
        return this.visualizationView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ViewController.java */
    /* renamed from: com.sds.android.ttpod.component.g.b.a.b$a */
    /* loaded from: classes.dex */
    public final class View$OnClickListenerC1222a implements View.OnClickListener, TTPodButton.RepeatListener {
        private View$OnClickListenerC1222a() {
        }

        @Override // com.sds.android.ttpod.framework.modules.skin.view.TTPodButton.InterfaceC2007a
        /* renamed from: a */
        public void onClick(View view, long differenceTime, int clickCount) {
            ViewController.this.m6528c(view, clickCount);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewController.this.mo6456a(view);
        }
    }
}
