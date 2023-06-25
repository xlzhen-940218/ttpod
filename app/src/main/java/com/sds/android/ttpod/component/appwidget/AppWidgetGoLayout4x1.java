package com.sds.android.ttpod.component.appwidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.BitmapUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.environment.PreferencesID;
import com.sds.android.ttpod.framework.support.Support;
import com.sds.android.ttpod.framework.support.SupportCallback;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;

/* loaded from: classes.dex */
public class AppWidgetGoLayout4x1 extends GoWidgetFrame implements View.OnLongClickListener {
    private static final int ARTIST_BITMAP_SIZE_IN_DP = 200;
    private static final int MILLS_PER_SECOND = 1000;
    private static final float ROUND_DIFFERENCE = 0.5f;
    private static final String TAG = "AppWidgetGoLayout4x1";
    private boolean mClickedPlay;
    private Handler mHandler;
    private ImageButton mImageButtonMiniLyric;
    private ImageButton mImageButtonPlayNext;
    private ImageButton mImageButtonPlayPause;
    private ImageButton mImageButtonPlayPrev;
    private ImageView mImageViewAlbumCover;
    private Preferences.InterfaceC2031a mOnPreferencesChangeListener;
    private ProgressBar mProgressBar;
    private C1099a mRefreshWidgetMonitor;
    private Runnable mRunnable;
    private int mSongDuration;
    private Support mSupport;
    private SupportCallback mSupportCallback;
    private TextView mTextViewTitle;
    private boolean mUpdatedHandlerRun;

    public AppWidgetGoLayout4x1(Context context) {
        this(context, null);
    }

    public AppWidgetGoLayout4x1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSupport = null;
        this.mOnPreferencesChangeListener = new Preferences.InterfaceC2031a() { // from class: com.sds.android.ttpod.component.appwidget.AppWidgetGoLayout4x1.1
            @Override // com.sds.android.ttpod.framework.storage.environment.Preferences.InterfaceC2031a
            /* renamed from: a */
            public void mo2553a(PreferencesID preferencesID) {
                if (PreferencesID.IS_SHOW_DESKTOP_LYRIC_ENABLED == preferencesID) {
                    if (AppWidgetGoLayout4x1.this.mSupport != null) {
                        AppWidgetGoLayout4x1.this.setMiniLyricButton(Preferences.m2838r());
                    }
                } else if (PreferencesID.CURRENT_ARTIST_BITMAP_PATH == preferencesID && AppWidgetGoLayout4x1.this.mSupport != null) {
                    AppWidgetGoLayout4x1.this.onAlbumCoverChanged(Preferences.m3014a(AppWidgetGoLayout4x1.this.mSupport.m2454v()));
                }
            }
        };
        this.mSupportCallback = new SupportCallback() { // from class: com.sds.android.ttpod.component.appwidget.AppWidgetGoLayout4x1.2
            @Override // com.sds.android.ttpod.framework.support.SupportCallback
            /* renamed from: a */
            public void mo2448a() {
                super.mo2448a();
                MediaItem m2454v = AppWidgetGoLayout4x1.this.mSupport.m2454v();
                if (m2454v != null && !m2454v.isNull()) {
                    AppWidgetGoLayout4x1.this.onMetaChanged(m2454v.getArtist(), m2454v.getTitle());
                    AppWidgetGoLayout4x1.this.onAlbumCoverChanged(Preferences.m3014a(AppWidgetGoLayout4x1.this.mSupport.m2454v()));
                }
                AppWidgetGoLayout4x1.this.setPlayStateBackground(AppWidgetGoLayout4x1.this.mSupport.m2463m());
            }

            @Override // com.sds.android.ttpod.framework.support.SupportCallback
            /* renamed from: a */
            public void mo2446a(MediaItem mediaItem) {
                super.mo2446a(mediaItem);
                LogUtils.debug(AppWidgetGoLayout4x1.TAG, "onPlayMediaChanged");
                if (mediaItem != null && !mediaItem.isNull()) {
                    AppWidgetGoLayout4x1.this.onMetaChanged(mediaItem.getArtist(), mediaItem.getTitle());
                    AppWidgetGoLayout4x1.this.onAlbumCoverChanged(Preferences.m3014a(AppWidgetGoLayout4x1.this.mSupport.m2454v()));
                }
            }

            @Override // com.sds.android.ttpod.framework.support.SupportCallback
            /* renamed from: a */
            public void mo2445a(PlayStatus playStatus) {
                super.mo2445a(playStatus);
                if (!AppWidgetGoLayout4x1.this.mUpdatedHandlerRun) {
                    AppWidgetGoLayout4x1.this.mHandler.postDelayed(AppWidgetGoLayout4x1.this.mRunnable, 1000L);
                }
                AppWidgetGoLayout4x1.this.setPlayStateBackground(AppWidgetGoLayout4x1.this.mSupport.m2463m());
            }
        };
        this.mHandler = new Handler();
        this.mRunnable = new Runnable() { // from class: com.sds.android.ttpod.component.appwidget.AppWidgetGoLayout4x1.8
            @Override // java.lang.Runnable
            public void run() {
                MediaItem m2454v;
                if (AppWidgetGoLayout4x1.this.mSupport == null || !AppWidgetGoLayout4x1.this.mSupport.m2452x()) {
                    AppWidgetGoLayout4x1.this.mUpdatedHandlerRun = false;
                    return;
                }
                try {
                    if (AppWidgetGoLayout4x1.this.mSongDuration == 0 && (m2454v = AppWidgetGoLayout4x1.this.mSupport.m2454v()) != null && !m2454v.isNull()) {
                        AppWidgetGoLayout4x1.this.mSongDuration = m2454v.getDuration().intValue();
                    }
                    AppWidgetGoLayout4x1.this.updateTime(AppWidgetGoLayout4x1.this.mSupport.m2465k().intValue(), AppWidgetGoLayout4x1.this.mSongDuration, AppWidgetGoLayout4x1.this.mSupport.m2464l());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AppWidgetGoLayout4x1.this.mUpdatedHandlerRun = true;
                AppWidgetGoLayout4x1.this.mHandler.postDelayed(this, 1000L);
            }
        };
    }

    @Override // com.sds.android.ttpod.component.appwidget.GoWidgetLife
    public void onStart(Bundle bundle) {
        LogUtils.debug(TAG, "onStart");
        this.mImageViewAlbumCover = (ImageView) findViewById(R.id.image_album_cover);
        this.mTextViewTitle = (TextView) findViewById(R.id.text_title);
        this.mProgressBar = (ProgressBar) findViewById(R.id.seekbar_progress);
        this.mImageButtonPlayPrev = (ImageButton) findViewById(R.id.button_play_prev);
        this.mImageButtonPlayPause = (ImageButton) findViewById(R.id.button_play_pause);
        this.mImageButtonPlayNext = (ImageButton) findViewById(R.id.button_play_next);
        this.mImageButtonMiniLyric = (ImageButton) findViewById(R.id.button_minilyric);
        this.mImageButtonPlayPause.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.appwidget.AppWidgetGoLayout4x1.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppWidgetGoLayout4x1.this.mClickedPlay = true;
                AppWidgetGoLayout4x1.this.startService("play_pause_command");
            }
        });
        this.mImageButtonPlayPrev.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.appwidget.AppWidgetGoLayout4x1.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppWidgetGoLayout4x1.this.startService("previous_command");
            }
        });
        this.mImageButtonPlayNext.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.appwidget.AppWidgetGoLayout4x1.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppWidgetGoLayout4x1.this.startService("next_command");
            }
        });
        this.mImageButtonMiniLyric.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.appwidget.AppWidgetGoLayout4x1.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppWidgetGoLayout4x1.this.startService("switch_desktop_lyric_hide_show_command");
            }
        });
        this.mImageViewAlbumCover.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.appwidget.AppWidgetGoLayout4x1.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppWidgetGoLayout4x1.this.startActivity(Action.START_ENTRY);
            }
        });
        this.mImageButtonPlayPrev.setOnLongClickListener(this);
        this.mImageButtonPlayPause.setOnLongClickListener(this);
        this.mImageButtonPlayNext.setOnLongClickListener(this);
        this.mImageButtonMiniLyric.setOnLongClickListener(this);
        this.mImageViewAlbumCover.setOnLongClickListener(this);
        initSupport();
        loadMonitor();
        initAppWidget(this.mSupport);
        registerPreferenceListener();
        this.mHandler.postDelayed(this.mRunnable, 1000L);
    }

    private void loadMonitor() {
        this.mRefreshWidgetMonitor = new C1099a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.EXIT);
        intentFilter.addAction(Action.LAUNCHER);
        intentFilter.addAction(Action.PLAYLIST_IS_EMPTY);
        getContext().registerReceiver(this.mRefreshWidgetMonitor, intentFilter);
    }

    private void unLoadMonitor() {
        if (this.mRefreshWidgetMonitor != null) {
            getContext().unregisterReceiver(this.mRefreshWidgetMonitor);
            this.mRefreshWidgetMonitor = null;
        }
    }

    @Override // com.sds.android.ttpod.component.appwidget.GoWidgetLife
    public void onPause(int i) {
        LogUtils.debug(TAG, "onPause");
    }

    @Override // com.sds.android.ttpod.component.appwidget.GoWidgetLife
    public void onResume(int i) {
        LogUtils.debug(TAG, "onResume");
    }

    @Override // com.sds.android.ttpod.component.appwidget.GoWidgetLife
    public void onDelete(int i) {
        LogUtils.debug(TAG, "onDelete");
        destroy();
    }

    @Override // com.sds.android.ttpod.component.appwidget.GoWidgetLife
    public void onRemove(int i) {
        LogUtils.debug(TAG, "onRemove");
        destroy();
    }

    @Override // com.sds.android.ttpod.component.appwidget.GoWidgetLife
    public boolean onApplyTheme(Bundle bundle) {
        LogUtils.debug(TAG, "onApplyTheme");
        return false;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        LogUtils.debug(TAG, "onFinishInflate");
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        return performLongClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.component.appwidget.AppWidgetGoLayout4x1$a */
    /* loaded from: classes.dex */
    public class C1099a extends BroadcastReceiver {
        private C1099a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                String action = intent.getAction();
                if (Action.LAUNCHER.equals(action)) {
                    if (AppWidgetGoLayout4x1.this.mSupport != null && !AppWidgetGoLayout4x1.this.mSupport.m2452x()) {
                        AppWidgetGoLayout4x1.this.mSupport.m2482b(AppWidgetGoLayout4x1.this.mSupportCallback);
                        AppWidgetGoLayout4x1.this.mSupport.mo2497a(AppWidgetGoLayout4x1.this.mSupportCallback);
                    }
                    AppWidgetGoLayout4x1.this.registerPreferenceListener();
                } else if (Action.EXIT.equals(action)) {
                    AppWidgetGoLayout4x1.this.setPlayStateBackground(PlayStatus.STATUS_PAUSED);
                    AppWidgetGoLayout4x1.this.unRegisterPreferenceListener();
                    if (AppWidgetGoLayout4x1.this.mSupport != null && AppWidgetGoLayout4x1.this.mSupport.m2452x()) {
                        AppWidgetGoLayout4x1.this.mSupport.m2482b(AppWidgetGoLayout4x1.this.mSupportCallback);
                        AppWidgetGoLayout4x1.this.mSupport.m2473d();
                    }
                } else if (Action.PLAYLIST_IS_EMPTY.equals(action) && AppWidgetGoLayout4x1.this.mClickedPlay) {
                    AppWidgetGoLayout4x1.this.mClickedPlay = false;
                    AppWidgetGoLayout4x1.this.startActivity(Action.NOTIFICATION_START_MEDIA_SCAN);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerPreferenceListener() {
        Preferences.m3023a(getContext(), PreferencesID.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.mOnPreferencesChangeListener);
        Preferences.m3023a(getContext(), PreferencesID.CURRENT_ARTIST_BITMAP_PATH, this.mOnPreferencesChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unRegisterPreferenceListener() {
        Preferences.m2940b(getContext(), PreferencesID.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.mOnPreferencesChangeListener);
        Preferences.m2940b(getContext(), PreferencesID.CURRENT_ARTIST_BITMAP_PATH, this.mOnPreferencesChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMetaChanged(String str, String str2) {
        MediaItem m2454v;
        if (TTTextUtils.isValidateMediaString(str)) {
            str2 = ((Object) TTTextUtils.validateString(BaseApplication.getApplication(), str)) + " - " + ((Object) TTTextUtils.validateString(BaseApplication.getApplication(), str2));
        }
        this.mTextViewTitle.setText(str2);
        if (this.mSupport != null && (m2454v = this.mSupport.m2454v()) != null && !m2454v.isNull()) {
            this.mSongDuration = m2454v.getDuration().intValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAlbumCoverChanged(String str) {
        try {
            this.mImageViewAlbumCover.setImageBitmap(BitmapUtils.m8445a(str, dp2px(200, getContext().getResources().getDisplayMetrics().density)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayStateBackground(PlayStatus playStatus) {
        this.mImageButtonPlayPause.setImageResource(PlayStatus.STATUS_PLAYING == playStatus ? R.drawable.img_appwidget_pause : R.drawable.img_appwidget_play);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMiniLyricButton(boolean z) {
        this.mImageButtonMiniLyric.setImageResource(z ? R.drawable.img_appwidget_minilyric_on : R.drawable.img_appwidget_minilyric_off);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startActivity(String str) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        getContext().startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startService(String str) {
        getContext().startService(new Intent(getContext(), SupportService.class).putExtra("command", str));
    }

    private int dp2px(int i, float f) {
        return (int) ((i * f) + ROUND_DIFFERENCE);
    }

    private void destroy() {
        try {
            unRegisterPreferenceListener();
            unLoadMonitor();
            unInitSupport();
            this.mHandler.removeCallbacks(this.mRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTime(int i, int i2, float f) {
        int i3 = (int) (i2 * f);
        if (i3 < 0) {
            i3 = 0;
        }
        this.mProgressBar.setProgress(i);
        this.mProgressBar.setMax(i2);
        this.mProgressBar.setSecondaryProgress(i3);
    }

    private void initSupport() {
        this.mSupport = SupportFactory.m2397a(getContext());
        this.mSupport.mo2497a(this.mSupportCallback);
    }

    private void unInitSupport() {
        if (this.mSupport != null) {
            this.mSupport.m2482b(this.mSupportCallback);
        }
    }

    private void initAppWidget(Support support) {
        if (support != null) {
            setPlayStateBackground(this.mSupport.m2463m());
            onAlbumCoverChanged(Preferences.m3014a(this.mSupport.m2454v()));
        }
    }
}
