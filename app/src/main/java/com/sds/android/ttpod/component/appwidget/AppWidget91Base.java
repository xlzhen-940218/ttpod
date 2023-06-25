package com.sds.android.ttpod.component.appwidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.util.BitmapUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.environment.PreferencesID;
import com.sds.android.ttpod.framework.support.Support;
import com.sds.android.ttpod.framework.support.SupportCallback;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.framework.support.p134a.PlayMode;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/* loaded from: classes.dex */
public abstract class AppWidget91Base extends RelativeLayout implements View.OnLongClickListener {
    private static final int ARTIST_BITMAP_SIZE_IN_DP = 200;
    private static final int DENSITY_DEFAULT = 240;
    private static final int FLAG_91 = 32;
    private static final String KEY_PACKAGENAME = "packageName";
    private static final int MILLS_PER_SECOND = 1000;
    private static final float ROUND_DIFFERENCE = 0.5f;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final String TAG = "AppWidget91Base";
    private static final int VALUE_OF_EACH_DECIMAL = 10;
    protected boolean mClickedPlay;
    private Handler mHandler;
    protected Preferences.InterfaceC2031a mOnPreferencesChangeListener;
    private C1077a mRefreshWidgetMonitor;
    private Runnable mRunnable;
    protected int mSongDuration;
    protected Support mSupport;
    private SupportCallback mSupportCallback;
    private boolean mUpdatedHandlerRun;

    protected abstract void applyTheme(Intent intent);

    protected abstract ImageView getAlbumImageView();

    protected abstract void onMetaChanged(String str, String str2, String str3);

    protected abstract void registerPreferenceListener();

    protected abstract void setMiniLyricButton(boolean z);

    protected abstract void setPlayModeBackground(PlayMode playMode);

    protected abstract void setPlayStateBackground(PlayStatus playStatus);

    protected abstract void unRegisterPreferenceListener();

    protected abstract void updatePlayTime();

    public AppWidget91Base(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSupport = null;
        this.mOnPreferencesChangeListener = new Preferences.InterfaceC2031a() { // from class: com.sds.android.ttpod.component.appwidget.AppWidget91Base.1
            @Override // com.sds.android.ttpod.framework.storage.environment.Preferences.InterfaceC2031a
            /* renamed from: a */
            public void mo2553a(PreferencesID preferencesID) {
                LogUtils.debug(AppWidget91Base.TAG, "onPreferencesChanged " + preferencesID + " mSupport=" + AppWidget91Base.this.mSupport);
                if (PreferencesID.CURRENT_ARTIST_BITMAP_PATH == preferencesID) {
                    if (AppWidget91Base.this.mSupport != null) {
                        AppWidget91Base.this.onAlbumCoverChanged(Preferences.m3014a(AppWidget91Base.this.mSupport.m2454v()));
                    }
                } else if (PreferencesID.PLAY_MODE == preferencesID) {
                    if (AppWidget91Base.this.mSupport != null) {
                        AppWidget91Base.this.setPlayModeBackground(Preferences.m2862l());
                    }
                } else if (PreferencesID.IS_SHOW_DESKTOP_LYRIC_ENABLED == preferencesID && AppWidget91Base.this.mSupport != null) {
                    AppWidget91Base.this.setMiniLyricButton(Preferences.m2838r());
                }
            }
        };
        this.mSupportCallback = new SupportCallback() { // from class: com.sds.android.ttpod.component.appwidget.AppWidget91Base.2
            @Override // com.sds.android.ttpod.framework.support.SupportCallback
            /* renamed from: a */
            public void mo2448a() {
                super.mo2448a();
                LogUtils.debug(AppWidget91Base.TAG, "onSupportServiceConnected");
                try {
                    MediaItem m2454v = AppWidget91Base.this.mSupport.m2454v();
                    if (m2454v != null && !m2454v.isNull()) {
                        AppWidget91Base.this.onMetaChanged(m2454v.getArtist(), m2454v.getAlbum(), m2454v.getTitle());
                        AppWidget91Base.this.onAlbumCoverChanged(Preferences.m3014a(AppWidget91Base.this.mSupport.m2454v()));
                    }
                    AppWidget91Base.this.setPlayModeBackground(Preferences.m2862l());
                    AppWidget91Base.this.setPlayStateBackground(AppWidget91Base.this.mSupport.m2463m());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.sds.android.ttpod.framework.support.SupportCallback
            /* renamed from: a */
            public void mo2446a(MediaItem mediaItem) {
                super.mo2446a(mediaItem);
                LogUtils.debug(AppWidget91Base.TAG, "onPlayMediaChanged");
                if (mediaItem != null) {
                    try {
                        if (!mediaItem.isNull()) {
                            AppWidget91Base.this.onMetaChanged(mediaItem.getArtist(), mediaItem.getAlbum(), mediaItem.getTitle());
                            AppWidget91Base.this.onAlbumCoverChanged(Preferences.m3014a(AppWidget91Base.this.mSupport.m2454v()));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override // com.sds.android.ttpod.framework.support.SupportCallback
            /* renamed from: a */
            public void mo2445a(PlayStatus playStatus) {
                super.mo2445a(playStatus);
                try {
                    if (!AppWidget91Base.this.mUpdatedHandlerRun) {
                        AppWidget91Base.this.mHandler.postDelayed(AppWidget91Base.this.mRunnable, 1000L);
                    }
                    AppWidget91Base.this.setPlayStateBackground(AppWidget91Base.this.mSupport.m2463m());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        this.mHandler = new Handler();
        this.mRunnable = new Runnable() { // from class: com.sds.android.ttpod.component.appwidget.AppWidget91Base.3
            @Override // java.lang.Runnable
            public void run() {
                if (AppWidget91Base.this.mSupport == null || !AppWidget91Base.this.mSupport.m2452x()) {
                    AppWidget91Base.this.mUpdatedHandlerRun = false;
                    return;
                }
                try {
                    if (AppWidget91Base.this.mSongDuration == 0) {
                        AppWidget91Base.this.mSongDuration = AppWidget91Base.this.mSupport.m2454v().getDuration().intValue();
                    }
                    if (AppWidget91Base.this.mSongDuration > 0 && AppWidget91Base.this.mSupport.m2465k() != null) {
                        AppWidget91Base.this.updatePlayTime();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AppWidget91Base.this.mUpdatedHandlerRun = true;
                AppWidget91Base.this.mHandler.postDelayed(this, 1000L);
            }
        };
        Preferences.m3024a(context);
    }

    public void onLoad(int i) {
        LogUtils.debug(TAG, "onLoad");
        initSupport();
        loadMonitor();
        initAppWidget(this.mSupport);
        registerPreferenceListener();
        this.mHandler.postDelayed(this.mRunnable, 1000L);
        Intent intent = new Intent("com.nd.android.pandahome.ASK_THEME");
        intent.putExtra(KEY_PACKAGENAME, getContext().getPackageName());
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        getContext().sendBroadcast(intent);
    }

    private synchronized void initSupport() {
        LogUtils.debug(TAG, "initSupport");
        this.mSupport = SupportFactory.m2397a(getContext());
        this.mSupport.mo2497a(this.mSupportCallback);
    }

    private synchronized void unInitSupport() {
        LogUtils.debug(TAG, "unInitSupport");
        if (this.mSupport != null) {
            this.mSupport.m2482b(this.mSupportCallback);
        }
    }

    private void loadMonitor() {
        LogUtils.debug(TAG, "loadMonitor");
        this.mRefreshWidgetMonitor = new C1077a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.nd.android.pandahome.THEME_INFO");
        intentFilter.addAction(Action.EXIT);
        intentFilter.addAction(Action.LAUNCHER);
        intentFilter.addAction(Action.PLAYLIST_IS_EMPTY);
        getContext().registerReceiver(this.mRefreshWidgetMonitor, intentFilter);
    }

    private void unLoadMonitor() {
        LogUtils.debug(TAG, "unLoadMonitor");
        if (this.mRefreshWidgetMonitor != null) {
            getContext().unregisterReceiver(this.mRefreshWidgetMonitor);
            this.mRefreshWidgetMonitor = null;
        }
    }

    private void initAppWidget(Support support) {
        LogUtils.debug(TAG, "initAppWidget");
        if (support != null && support.m2452x()) {
            setPlayStateBackground(this.mSupport.m2463m());
            setPlayModeBackground(Preferences.m2862l());
            onAlbumCoverChanged(Preferences.m3014a(this.mSupport.m2454v()));
        }
    }

    public void onDestory(int i) {
        try {
            unRegisterPreferenceListener();
            unLoadMonitor();
            unInitSupport();
            this.mHandler.removeCallbacks(this.mRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startActivity(String str) {
        try {
            Intent intent = new Intent(str);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startService(String str) {
        try {
            getContext().startService(new Intent(getContext(), SupportService.class).putExtra("command", str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onAlbumCoverChanged(String str) {
        try {
            Bitmap m8445a = BitmapUtils.m8445a(str, dp2px(200, getContext().getResources().getDisplayMetrics().density));
            ImageView albumImageView = getAlbumImageView();
            if (albumImageView != null) {
                albumImageView.setImageBitmap(m8445a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.component.appwidget.AppWidget91Base$a */
    /* loaded from: classes.dex */
    public class C1077a extends BroadcastReceiver {
        private C1077a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                String action = intent.getAction();
                LogUtils.debug(AppWidget91Base.TAG, action);
                if ("com.nd.android.pandahome.THEME_INFO".equals(action)) {
                    String stringExtra = intent.getStringExtra(AppWidget91Base.KEY_PACKAGENAME);
                    if (stringExtra == null || stringExtra.equals(AppWidget91Base.this.getContext().getPackageName())) {
                        AppWidget91Base.this.applyTheme(intent);
                        AppWidget91Base.this.setPlayModeBackground(Preferences.m2862l());
                        AppWidget91Base.this.setPlayStateBackground(AppWidget91Base.this.mSupport.m2463m());
                    }
                } else if (Action.LAUNCHER.equals(action)) {
                    if (AppWidget91Base.this.mSupport != null && !AppWidget91Base.this.mSupport.m2452x()) {
                        AppWidget91Base.this.mSupport.m2482b(AppWidget91Base.this.mSupportCallback);
                        AppWidget91Base.this.mSupport.mo2497a(AppWidget91Base.this.mSupportCallback);
                    }
                    AppWidget91Base.this.registerPreferenceListener();
                } else if (Action.EXIT.equals(action)) {
                    AppWidget91Base.this.setPlayStateBackground(PlayStatus.STATUS_PAUSED);
                    LogUtils.debug(AppWidget91Base.TAG, "EXIT");
                    AppWidget91Base.this.unRegisterPreferenceListener();
                    if (AppWidget91Base.this.mSupport != null && AppWidget91Base.this.mSupport.m2452x()) {
                        AppWidget91Base.this.mSupport.m2482b(AppWidget91Base.this.mSupportCallback);
                        AppWidget91Base.this.mSupport.m2473d();
                    }
                } else if (Action.PLAYLIST_IS_EMPTY.equals(action) && AppWidget91Base.this.mClickedPlay) {
                    AppWidget91Base.this.mClickedPlay = false;
                    AppWidget91Base.this.startActivity(Action.NOTIFICATION_START_MEDIA_SCAN);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getFormatTime(int i) {
        int i2 = i / 1000;
        return unitFormat(i2 / 60) + ":" + unitFormat(i2 % 60);
    }

    private String unitFormat(int i) {
        if (i < 0) {
            return "00";
        }
        if (i >= 0 && i < 10) {
            return FeedbackItem.STATUS_WAITING + i;
        }
        return "" + i;
    }

    protected int dp2px(int i, float f) {
        return (int) ((i * f) + ROUND_DIFFERENCE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't wrap try/catch for region: R(17:1|2|3|4|(3:5|6|7)|8|9|10|11|12|13|14|(1:16)(1:27)|(1:18)(1:26)|19|20|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x007b, code lost:
        r1 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x007c, code lost:
        r2 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x007d, code lost:
        r1.printStackTrace();
        r4 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x009c, code lost:
        r1 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0082 A[Catch: Exception -> 0x0097, TryCatch #3 {Exception -> 0x0097, blocks: (B:11:0x0029, B:16:0x0034, B:25:0x008d, B:24:0x0082, B:23:0x007d, B:20:0x0076), top: B:39:0x0076 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008d A[Catch: Exception -> 0x0097, TRY_LEAVE, TryCatch #3 {Exception -> 0x0097, blocks: (B:11:0x0029, B:16:0x0034, B:25:0x008d, B:24:0x0082, B:23:0x007d, B:20:0x0076), top: B:39:0x0076 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static StateListDrawable newSelector(Context context, String str, String str2) {
        Bitmap bitmap;
        Bitmap bitmap2;
        Exception e;
        StateListDrawable stateListDrawable = null;
        try {
            bitmap = BitmapFactory.decodeStream(new FileInputStream(new File(str)));
        } catch (Exception e1) {
            e = e1;
            bitmap = null;
        }
        try {
            bitmap.setDensity(DENSITY_DEFAULT);
            bitmap2 = bitmap;
        } catch (Exception e2) {
            e = e2;
            try {
                e.printStackTrace();
                bitmap2 = bitmap;
                Bitmap bitmap3 = BitmapFactory.decodeStream(new FileInputStream(new File(str2)));
                bitmap3.setDensity(DENSITY_DEFAULT);
                Bitmap bitmap4 = bitmap3;
                StateListDrawable stateListDrawable2 = new StateListDrawable();
                if (bitmap2 == null) {
                }
                stateListDrawable2.addState(new int[]{16842919, 16842910}, bitmap4 == null ? null : new BitmapDrawable(context.getResources(), bitmap4));
                stateListDrawable2.addState(new int[]{16842910, 16842908}, null);
                stateListDrawable2.addState(new int[]{16842910}, null);
                stateListDrawable2.addState(new int[]{16842908}, null);
                stateListDrawable2.addState(new int[]{16842909}, null);
                stateListDrawable2.addState(new int[0], null);
                stateListDrawable = stateListDrawable2;
                return stateListDrawable;
            } catch (Exception e3) {
                e3.printStackTrace();
                return stateListDrawable;
            }
        }
        Bitmap bitmap32 = null;
        try {
            bitmap32 = BitmapFactory.decodeStream(new FileInputStream(new File(str2)));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        bitmap32.setDensity(DENSITY_DEFAULT);
        Bitmap bitmap42 = bitmap32;
        StateListDrawable stateListDrawable22 = new StateListDrawable();
        BitmapDrawable bitmapDrawable = bitmap2 == null ? null : new BitmapDrawable(context.getResources(), bitmap2);
        stateListDrawable22.addState(new int[]{16842919, 16842910}, bitmap42 == null ? null : new BitmapDrawable(context.getResources(), bitmap42));
        stateListDrawable22.addState(new int[]{16842910, 16842908}, null);
        stateListDrawable22.addState(new int[]{16842910}, bitmapDrawable);
        stateListDrawable22.addState(new int[]{16842908}, null);
        stateListDrawable22.addState(new int[]{16842909}, null);
        stateListDrawable22.addState(new int[0], bitmapDrawable);
        stateListDrawable = stateListDrawable22;
        return stateListDrawable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Drawable getDrawableFromPath(String str) {
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(new FileInputStream(new File(str)));
            decodeStream.setDensity(DENSITY_DEFAULT);
            return new BitmapDrawable(getContext().getResources(), decodeStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        try {
            View view2 = (View) getParent();
            if (view2 != null) {
                if (view2.performLongClick()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
