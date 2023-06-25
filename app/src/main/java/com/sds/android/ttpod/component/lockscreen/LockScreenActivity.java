package com.sds.android.ttpod.component.lockscreen;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.BitmapUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.p132d.Lyric;
import com.sds.android.ttpod.framework.modules.skin.p132d.LyricParser;
import com.sds.android.ttpod.framework.modules.skin.view.AnimTransView;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.ViewUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.framework.support.search.SearchStatus;
import com.sds.android.ttpod.framework.support.search.task.ResultData;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class LockScreenActivity extends SlidingClosableActivity implements View.OnClickListener {
    private static final int LYRIC_FADE_LENGTH_IN_DP = 22;
    private static final int LYRIC_NORMAL_TEXT_SIZE_CUT = 3;
    private static final int REFRESH_DELAY = 100;
    private static final int REFRESH_LYRIC_MSG = 0;
    private static final int SDK_VERSION_CODE_L = 20;
    private static final String TAG = "LockScreenActivity";
    private boolean mIgnoreResume;
    private ImageView mImgViewNext;
    private ImageView mImgViewPause;
    private ImageView mImgViewPlay;
    private ImageView mImgViewPre;
    private ImageView mImgViewSlidingUnlock;
    private LyricView mLyricView;
    private MediaItem mPlayingMediaItem;
    private PowerManager mPowerManager;
    private C1282a mTickReceiver;
    private boolean mIsReceiverRegistered = false;
    private View mLockScreenLayout = null;
    private AnimTransView mSongImageView = null;
    private TextView mTimeTextView = null;
    private TextView mAMPMTextView = null;
    private TextView mDateTextView = null;
    private TextView mTvSongInfo = null;
    private SimpleDateFormat mDateTextDateFormat = new SimpleDateFormat("MM月dd日");
    private SimpleDateFormat mTimeTextDateFormat = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat mWeekDayDateFormat = new SimpleDateFormat("EEEE");
    private Handler mRefreshHandler = new Handler() { // from class: com.sds.android.ttpod.component.lockscreen.LockScreenActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    removeMessages(0);
                    if (LockScreenActivity.this.mLyricView != null && LockScreenActivity.this.mLyricView.getLyric() != null) {
                        if (LockScreenActivity.this.mPowerManager == null || LockScreenActivity.this.mPowerManager.isScreenOn()) {
                            LockScreenActivity.this.mLyricView.setPlayingTime(SupportFactory.m2397a(BaseApplication.getApplication()).m2465k().intValue());
                            sendEmptyMessageDelayed(0, 100L);
                            break;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
            }
            super.handleMessage(message);
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_SEARCH_PICTURE_STATE, ReflectUtils.m8375a(cls, "loadPictureAfterSearchFinished", SearchStatus.class, List.class, String.class, Bitmap.class));
        map.put(CommandID.PLAY_MEDIA_CHANGED, ReflectUtils.m8375a(cls, "updatePlayMeta", new Class[0]));
        map.put(CommandID.UPDATE_PLAY_STATUS, ReflectUtils.m8375a(cls, "updatePlayStatus", PlayStatus.class));
        map.put(CommandID.UPDATE_SEARCH_LYRIC_STATE, ReflectUtils.m8375a(cls, "updateSearchLyricState", SearchStatus.class, List.class, String.class, Lyric.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPowerManager = (PowerManager) getSystemService("power");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT < 20) {
            window.addFlags(4194304);
        }
        window.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
        if (Preferences.m3070F()) {
            window.setFlags(1024, 1024);
        } else {
            window.clearFlags(1024);
        }
        try {
            bindView();
            initView();
            setContentView(this.mLockScreenLayout);
            this.mTickReceiver = new C1282a(this);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            ImageCacheUtils.m4743b().m8804b();
            Cache.getInstance().m3196b();
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        updateTime();
        if (!this.mIsReceiverRegistered) {
            this.mIsReceiverRegistered = true;
            registerReceiver(this.mTickReceiver, buildTimeTickFilter());
        }
        showCachedLyricAndPic();
        ((AnimationDrawable) this.mImgViewSlidingUnlock.getBackground()).start();
        if (!this.mIgnoreResume && this.mPowerManager.isScreenOn()) {
            LogUtils.info(TAG, "onResume looklockscreen statistic");
            //LocalStatistic.m5156a();
        }
        this.mIgnoreResume = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        ((AnimationDrawable) this.mImgViewSlidingUnlock.getBackground()).stop();
        if (this.mIsReceiverRegistered) {
            this.mIsReceiverRegistered = false;
            unregisterReceiver(this.mTickReceiver);
        }
        if (this.mRefreshHandler != null) {
            this.mRefreshHandler.removeMessages(0);
        }
        if (!this.mPowerManager.isScreenOn()) {
            setBackgroundState(false);
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public void onDetachedFromWindow() {
        if (this.mIsReceiverRegistered) {
            this.mIsReceiverRegistered = false;
            unregisterReceiver(this.mTickReceiver);
        }
        super.onDetachedFromWindow();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.mPowerManager = null;
        super.onDestroy();
        this.mRefreshHandler.removeCallbacksAndMessages(null);
        ViewUtils.m4639a(this.mLockScreenLayout);
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity
    protected boolean needFinishAnimation() {
        return false;
    }

    public void updatePlayMeta() {
        this.mPlayingMediaItem = Cache.getInstance().getCurrentPlayMediaItem();
        setSongInfoTextView();
        setLyric(null);
        this.mSongImageView.setImageBitmapDelay(null);
    }

    public void loadPictureAfterSearchFinished(SearchStatus searchStatus, List<ResultData> list, String str, Bitmap bitmap) {
        if (StringUtils.m8344a(this.mPlayingMediaItem.getID(), str)) {
            if (SearchStatus.SEARCH_LOCAL_FINISHED == searchStatus || SearchStatus.SEARCH_DOWNLOAD_FINISHED == searchStatus) {
                this.mSongImageView.setImageBitmapDelay(bitmap);
            }
        }
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        switch (playStatus) {
            case STATUS_PLAYING:
                this.mImgViewPause.setVisibility(View.VISIBLE);
                this.mImgViewPlay.setVisibility(View.INVISIBLE);
                return;
            default:
                this.mImgViewPlay.setVisibility(View.VISIBLE);
                this.mImgViewPause.setVisibility(View.INVISIBLE);
                return;
        }
    }

    public void updateSearchLyricState(SearchStatus searchStatus, List<ResultData> list, String str, Lyric lyric) {
        if (StringUtils.m8344a(this.mPlayingMediaItem.getID(), str)) {
            switch (searchStatus) {
                case SEARCH_LOCAL_FINISHED:
                case SEARCH_DOWNLOAD_FINISHED:
                    setLyric(lyric);
                    return;
                default:
                    return;
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        acquireFastClickSupport();
        switch (view.getId()) {
            case R.id.imageview_pre /* 2131230870 */:
                CommandCenter.getInstance().m4606a(new Command(CommandID.PREVIOUS, new Object[0]));
                return;
            case R.id.imageview_play /* 2131230871 */:
                if (PlayStatus.STATUS_PAUSED == SupportFactory.m2397a(BaseApplication.getApplication()).m2463m()) {
                    CommandCenter.getInstance().m4606a(new Command(CommandID.RESUME, new Object[0]));
                    return;
                } else {
                    CommandCenter.getInstance().m4606a(new Command(CommandID.START, new Object[0]));
                    return;
                }
            case R.id.imageview_pause /* 2131230872 */:
                CommandCenter.getInstance().m4606a(new Command(CommandID.PAUSE, new Object[0]));
                return;
            case R.id.imageview_next /* 2131230873 */:
                CommandCenter.getInstance().m4606a(new Command(CommandID.NEXT, new Object[0]));
                return;
            default:
                return;
        }
    }

    private void bindView() {
        this.mLockScreenLayout = getLayoutInflater().inflate(R.layout.activity_lockscreen, (ViewGroup) null);
        this.mTimeTextView = (TextView) this.mLockScreenLayout.findViewById(R.id.timeText);
        this.mAMPMTextView = (TextView) this.mLockScreenLayout.findViewById(R.id.timeFormat);
        this.mDateTextView = (TextView) this.mLockScreenLayout.findViewById(R.id.dateText);
        this.mTvSongInfo = (TextView) this.mLockScreenLayout.findViewById(R.id.tv_songname);
        this.mSongImageView = (AnimTransView) this.mLockScreenLayout.findViewById(R.id.songImage);
        this.mImgViewPre = (ImageView) this.mLockScreenLayout.findViewById(R.id.imageview_pre);
        this.mImgViewPlay = (ImageView) this.mLockScreenLayout.findViewById(R.id.imageview_play);
        this.mImgViewPause = (ImageView) this.mLockScreenLayout.findViewById(R.id.imageview_pause);
        this.mImgViewNext = (ImageView) this.mLockScreenLayout.findViewById(R.id.imageview_next);
        this.mImgViewSlidingUnlock = (ImageView) this.mLockScreenLayout.findViewById(R.id.imageview_sliding_to_unlock);
        this.mLyricView = (LyricView) this.mLockScreenLayout.findViewById(R.id.lyric_view);
    }

    private void initView() {
        hideActionBar();
        this.mLockScreenLayout.setKeepScreenOn(Preferences.m3080A());
        try {
            Drawable drawable = WallpaperManager.getInstance(this).getDrawable();
            if (SDKVersionUtils.m8367g()) {
                this.mLockScreenLayout.setBackground(drawable);
            } else {
                this.mLockScreenLayout.setBackgroundDrawable(drawable);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        Typeface m7226b = DisplayUtils.m7226b();
        if (m7226b != null) {
            this.mTimeTextView.setTypeface(m7226b);
            this.mAMPMTextView.setTypeface(m7226b);
        }
        if (DateFormat.is24HourFormat(BaseApplication.getApplication())) {
            this.mAMPMTextView.setVisibility(View.GONE);
            this.mTimeTextDateFormat = new SimpleDateFormat("HH:mm");
        } else {
            this.mAMPMTextView.setText(getAMPMText());
            this.mTimeTextDateFormat = new SimpleDateFormat("hh:mm");
        }
        this.mPlayingMediaItem = Cache.getInstance().getCurrentPlayMediaItem();
        setSongInfoTextView();
        this.mImgViewPre.setOnClickListener(this);
        this.mImgViewPause.setOnClickListener(this);
        this.mImgViewPlay.setOnClickListener(this);
        this.mImgViewNext.setOnClickListener(this);
        updatePlayStatus(SupportFactory.m2397a(BaseApplication.getApplication()).m2463m());
        initLyricView();
    }

    private void initLyricView() {
        this.mLyricView.setSlowScroll(false);
        this.mLyricView.setDisplayMode(LyricView.EnumC1996a.Normal);
        this.mLyricView.setDefaultColorHighlight(-1);
        this.mLyricView.setColorHighlight(-1);
        this.mLyricView.setFadeEdgeLength(DisplayUtils.m7229a(22));
        int m3048Q = Preferences.m3048Q();
        this.mLyricView.m3459b(0, this.mLyricView.getDefaultFontSizeHighlight() + m3048Q);
        this.mLyricView.m3482a(0, (m3048Q + this.mLyricView.getDefaultFontSizeNormal()) - 3.0f);
    }

    private void showCachedLyricAndPic() {
        if (this.mLyricView.getLyric() == null) {
            String m3159i = Cache.getInstance().m3159i();
            setLyric(m3159i == null ? null : LyricParser.m3647b(m3159i));
        } else {
            this.mRefreshHandler.sendEmptyMessage(0);
        }
        if (this.mSongImageView.getDrawable() == null) {
            String m3164g = Cache.getInstance().m3164g();
            this.mSongImageView.setImageBitmap(m3164g != null ? BitmapUtils.m8435b(m3164g, DisplayUtils.m7225c(), DisplayUtils.m7224d()) : null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTime() {
        Date date = new Date();
        this.mTimeTextView.setText(this.mTimeTextDateFormat.format(date));
        this.mDateTextView.setText(this.mDateTextDateFormat.format(date) + "  " + this.mWeekDayDateFormat.format(date));
        this.mAMPMTextView.setText(getAMPMText());
    }

    private String getAMPMText() {
        return new GregorianCalendar().get(9) == 0 ? "AM" : "PM";
    }

    private void setSongInfoTextView() {
        if (this.mPlayingMediaItem != null) {
            StringBuffer stringBuffer = new StringBuffer(TextUtils.isEmpty(this.mPlayingMediaItem.getTitle()) ? getString(R.string.unknown) : this.mPlayingMediaItem.getTitle());
            stringBuffer.append(" - ");
            stringBuffer.append(TTTextUtils.validateString(this, this.mPlayingMediaItem.getArtist()));
            this.mTvSongInfo.setText(stringBuffer);
        }
    }

    private void setLyric(Lyric lyric) {
        this.mLyricView.setLyric(lyric);
        if (lyric == null) {
            this.mRefreshHandler.removeMessages(0);
            this.mLyricView.setState(8);
            return;
        }
        this.mRefreshHandler.sendEmptyMessage(0);
    }

    /* renamed from: com.sds.android.ttpod.component.lockscreen.LockScreenActivity$a */
    /* loaded from: classes.dex */
    private static class C1282a extends BroadcastReceiver {

        /* renamed from: a */
        private final WeakReference<LockScreenActivity> f4624a;

        public C1282a(LockScreenActivity lockScreenActivity) {
            this.f4624a = new WeakReference<>(lockScreenActivity);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.TIME_TICK".equals(intent.getAction()) && this.f4624a.get() != null) {
                this.f4624a.get().updateTime();
            }
        }
    }

    private IntentFilter buildTimeTickFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        return intentFilter;
    }
}
