package com.sds.android.ttpod.component.appwidget;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.environment.PreferencesID;
import com.sds.android.ttpod.framework.support.p134a.PlayMode;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;

import java.io.File;

/* loaded from: classes.dex */
public class AppWidget91Layout4x4 extends AppWidget91Base {
    private static final String PATH_BG = "pandawidget_ttpod_bg.png";
    private static final String PATH_BG_BUTTONS = "pandawidget_ttpod_bgbuttons.png";
    private static final String PATH_BG_DEFAULT = "pandawidget_ttpod_bgdefault.png";
    private static final String PATH_BG_TIME = "pandawidget_ttpod_bgtime.png";
    private static final String PATH_FOLDER = "/widget/ttpod/";
    private static final String PATH_NEXT_NORMAL = "pandawidget_ttpod_nextnormal.png";
    private static final String PATH_NEXT_PRESSED = "pandawidget_ttpod_nextpressed.png";
    private static final String PATH_PAUSE_NORMAL = "pandawidget_ttpod_pausenormal.png";
    private static final String PATH_PAUSE_PRESSED = "pandawidget_ttpod_pausepressed.png";
    private static final String PATH_PLAY_NORMAL = "pandawidget_ttpod_playnormal.png";
    private static final String PATH_PLAY_PRESSED = "pandawidget_ttpod_playpressed.png";
    private static final String PATH_PREV_NORMAL = "pandawidget_ttpod_prevnormal.png";
    private static final String PATH_PREV_PRESSED = "pandawidget_ttpod_prevpressed.png";
    private static final int POSITION_THEME_START = 7;
    private static final String TAG = "AppWidget91Layout4x4";
    private static final String THEME91_DEFAULT = "0";
    private boolean mDefaultTheme91;
    private ImageButton mImageButtonPlay;
    private ImageButton mImageButtonPlayMode;
    private ImageButton mImageButtonPlayNext;
    private ImageButton mImageButtonPlayPrev;
    private ImageView mImageViewAlbum;
    private ImageView mImageViewMiddleDefault;
    private RelativeLayout mLayoutBackground;
    private LinearLayout mLayoutButtons;
    private TextView mTextViewArtist;
    private TextView mTextViewTime;
    private TextView mTextViewTitle;
    private String mTheme91Path;
    private static int[] mPlayModeIcons = {R.drawable.img_appwidget91_playmode_repeat_all, R.drawable.img_appwidget91_playmode_repeat_current, R.drawable.img_appwidget91_playmode_normal, R.drawable.img_appwidget91_playmode_shuffle};
    private static final String PATH_MODE_REPEAT_ALL = "pandawidget_ttpod_moderepeatall.png";
    private static final String PATH_MODE_REPEAT_CURRENT = "pandawidget_ttpod_moderepeatcurrent.png";
    private static final String PATH_MODE_NORMAL = "pandawidget_ttpod_modenormal.png";
    private static final String PATH_MODE_SHUFFLE = "pandawidget_ttpod_modeshuffle.png";
    private static String[] mStrPlayModeIcons = {PATH_MODE_REPEAT_ALL, PATH_MODE_REPEAT_CURRENT, PATH_MODE_NORMAL, PATH_MODE_SHUFFLE};

    public AppWidget91Layout4x4(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected ImageView getAlbumImageView() {
        return this.mImageViewAlbum;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mImageViewMiddleDefault = (ImageView) findViewById(R.id.image_bg_default);
        this.mImageViewAlbum = (ImageView) findViewById(R.id.image_album_cover);
        this.mTextViewArtist = (TextView) findViewById(R.id.text_artist);
        this.mTextViewTitle = (TextView) findViewById(R.id.text_title);
        this.mTextViewTime = (TextView) findViewById(R.id.text_time);
        this.mLayoutButtons = (LinearLayout) findViewById(R.id.bg_buttons);
        this.mLayoutBackground = (RelativeLayout) findViewById(R.id.widget_bg);
        this.mImageButtonPlayMode = (ImageButton) findViewById(R.id.button_playmode);
        this.mImageButtonPlayPrev = (ImageButton) findViewById(R.id.button_play_prev);
        this.mImageButtonPlay = (ImageButton) findViewById(R.id.button_play_pause);
        this.mImageButtonPlayNext = (ImageButton) findViewById(R.id.button_play_next);
        this.mImageViewMiddleDefault.setOnLongClickListener(this);
        this.mTextViewArtist.setOnLongClickListener(this);
        this.mTextViewTitle.setOnLongClickListener(this);
        this.mTextViewTime.setOnLongClickListener(this);
        this.mLayoutButtons.setOnLongClickListener(this);
        this.mLayoutBackground.setOnLongClickListener(this);
        this.mImageButtonPlayMode.setOnLongClickListener(this);
        this.mImageButtonPlayPrev.setOnLongClickListener(this);
        this.mImageButtonPlay.setOnLongClickListener(this);
        this.mImageButtonPlayNext.setOnLongClickListener(this);
        this.mImageViewAlbum.setOnLongClickListener(this);
        this.mImageButtonPlay.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.appwidget.AppWidget91Layout4x4.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppWidget91Layout4x4.this.mClickedPlay = true;
                AppWidget91Layout4x4.this.startService("play_pause_command");
            }
        });
        this.mImageButtonPlayPrev.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.appwidget.AppWidget91Layout4x4.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppWidget91Layout4x4.this.startService("previous_command");
            }
        });
        this.mImageButtonPlayNext.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.appwidget.AppWidget91Layout4x4.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppWidget91Layout4x4.this.startService("next_command");
            }
        });
        this.mImageButtonPlayMode.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.appwidget.AppWidget91Layout4x4.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppWidget91Layout4x4.this.startService("switch_play_mode_command");
            }
        });
        this.mImageViewAlbum.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.appwidget.AppWidget91Layout4x4.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppWidget91Layout4x4.this.startActivity(Action.START_ENTRY);
            }
        });
        LogUtils.debug(TAG, "onFinishInflate");
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void onMetaChanged(String str, String str2, String str3) {
        this.mTextViewTitle.setText(str3);
        this.mTextViewArtist.setText(TTTextUtils.validateString(getContext(), str));
        MediaItem m2454v = this.mSupport.m2454v();
        if (m2454v != null && !m2454v.isNull()) {
            this.mSongDuration = m2454v.getDuration().intValue();
        }
        this.mTextViewTime.setText("00:00/" + getFormatTime(this.mSongDuration));
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void setPlayModeBackground(PlayMode playMode) {
        Drawable drawableFromPath;
        if (playMode != null) {
            try {
                if (!StringUtils.isEmpty(this.mTheme91Path) && FileUtils.m8414b(this.mTheme91Path + mStrPlayModeIcons[playMode.ordinal()]) && (drawableFromPath = getDrawableFromPath(this.mTheme91Path + mStrPlayModeIcons[playMode.ordinal()])) != null) {
                    this.mImageButtonPlayMode.setImageDrawable(drawableFromPath);
                } else {
                    this.mImageButtonPlayMode.setImageResource(mPlayModeIcons[playMode.ordinal()]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void setMiniLyricButton(boolean z) {
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void setPlayStateBackground(PlayStatus playStatus) {
        String str;
        String str2;
        StateListDrawable newSelector;
        if (!StringUtils.isEmpty(this.mTheme91Path)) {
            if (PlayStatus.STATUS_PLAYING == playStatus) {
                str = this.mTheme91Path + PATH_PAUSE_NORMAL;
                str2 = this.mTheme91Path + PATH_PAUSE_PRESSED;
            } else {
                str = this.mTheme91Path + PATH_PLAY_NORMAL;
                str2 = this.mTheme91Path + PATH_PLAY_PRESSED;
            }
            if (FileUtils.m8414b(str) && FileUtils.m8414b(str2) && (newSelector = newSelector(getContext(), str, str2)) != null) {
                this.mImageButtonPlay.setImageDrawable(newSelector);
                return;
            }
        }
        this.mImageButtonPlay.setImageResource(PlayStatus.STATUS_PLAYING == playStatus ? R.drawable.xml_appwidget91_pause : R.drawable.xml_appwidget91_play);
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void registerPreferenceListener() {
        Preferences.m3023a(getContext(), PreferencesID.PLAY_MODE, this.mOnPreferencesChangeListener);
        Preferences.m3023a(getContext(), PreferencesID.CURRENT_ARTIST_BITMAP_PATH, this.mOnPreferencesChangeListener);
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void unRegisterPreferenceListener() {
        Preferences.m2940b(getContext(), PreferencesID.PLAY_MODE, this.mOnPreferencesChangeListener);
        Preferences.m2940b(getContext(), PreferencesID.CURRENT_ARTIST_BITMAP_PATH, this.mOnPreferencesChangeListener);
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void applyTheme(Intent intent) {
        String stringExtra = intent.getStringExtra("widgetSkinPath");
        if (!StringUtils.isEmpty(stringExtra)) {
            String str = stringExtra + PATH_FOLDER;
            if (!str.equals(this.mTheme91Path)) {
                this.mTheme91Path = str;
                if ("0".equals(stringExtra.substring(stringExtra.indexOf("Themes") + 7)) && !this.mDefaultTheme91) {
                    this.mDefaultTheme91 = true;
                    setDefaultTheme();
                    setPlayModeBackground(Preferences.m2862l());
                    setPlayStateBackground(this.mSupport.m2463m());
                    return;
                }
                if (new File(this.mTheme91Path).exists()) {
                    this.mDefaultTheme91 = false;
                    set91THEME();
                } else if (!this.mDefaultTheme91) {
                    this.mDefaultTheme91 = true;
                    setDefaultTheme();
                }
                setPlayModeBackground(Preferences.m2862l());
                setPlayStateBackground(this.mSupport.m2463m());
            }
        }
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void updatePlayTime() {
        this.mTextViewTime.setText(getFormatTime(this.mSupport.m2465k().intValue()) + "/" + getFormatTime(this.mSongDuration));
    }

    private void set91THEME() {
        try {
            String str = this.mTheme91Path + PATH_PREV_NORMAL;
            String str2 = this.mTheme91Path + PATH_PREV_PRESSED;
            if (FileUtils.m8414b(str) && FileUtils.m8414b(str2)) {
                this.mImageButtonPlayPrev.setImageDrawable(newSelector(getContext(), str, str2));
            } else {
                this.mImageButtonPlayPrev.setImageResource(R.drawable.xml_appwidget91_play_prev);
            }
            String str3 = this.mTheme91Path + PATH_NEXT_NORMAL;
            String str4 = this.mTheme91Path + PATH_NEXT_PRESSED;
            if (FileUtils.m8414b(str3) && FileUtils.m8414b(str4)) {
                this.mImageButtonPlayNext.setImageDrawable(newSelector(getContext(), str3, str4));
            } else {
                this.mImageButtonPlayNext.setImageResource(R.drawable.img_appwidget_play_next);
            }
            if (FileUtils.m8414b(this.mTheme91Path + PATH_BG_BUTTONS)) {
                this.mLayoutButtons.setBackground(getDrawableFromPath(this.mTheme91Path + PATH_BG_BUTTONS));
            } else {
                this.mLayoutButtons.setBackgroundResource(R.drawable.img_background_appwidget91_buttons);
            }
            if (FileUtils.m8414b(this.mTheme91Path + PATH_BG_TIME)) {
                this.mTextViewTime.setBackground(getDrawableFromPath(this.mTheme91Path + PATH_BG_TIME));
            } else {
                this.mTextViewTime.setBackgroundResource(R.drawable.img_background_appwidget91_time);
            }
            if (FileUtils.m8414b(this.mTheme91Path + PATH_BG)) {
                this.mLayoutBackground.setBackground(getDrawableFromPath(this.mTheme91Path + PATH_BG));
            } else {
                this.mLayoutBackground.setBackgroundResource(R.drawable.img_background_appwidget91);
            }
            if (FileUtils.m8414b(this.mTheme91Path + PATH_BG_DEFAULT)) {
                this.mImageViewMiddleDefault.setImageDrawable(getDrawableFromPath(this.mTheme91Path + PATH_BG_DEFAULT));
            } else {
                this.mImageViewMiddleDefault.setImageResource(R.drawable.img_background_appwidget91_default);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDefaultTheme() {
        this.mLayoutBackground.setBackgroundResource(R.drawable.img_background_appwidget91);
        this.mImageViewMiddleDefault.setImageResource(R.drawable.img_background_appwidget91_default);
        this.mImageButtonPlayPrev.setImageResource(R.drawable.xml_appwidget91_play_prev);
        this.mImageButtonPlayNext.setImageResource(R.drawable.xml_appwidget91_play_next);
        this.mLayoutButtons.setBackgroundResource(R.drawable.img_background_appwidget91_buttons);
        this.mTextViewTime.setBackgroundResource(R.drawable.img_background_appwidget91_time);
    }
}
