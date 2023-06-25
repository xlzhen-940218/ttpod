package com.sds.android.ttpod.component.appwidget;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.environment.PreferencesID;
import com.sds.android.ttpod.framework.support.p134a.PlayMode;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.io.File;

/* loaded from: classes.dex */
public class AppWidget91Layout4x1 extends AppWidget91Base {

    /* renamed from: a */
    private String f3648a;

    /* renamed from: b */
    private ImageView f3649b;

    /* renamed from: c */
    private TextView f3650c;

    /* renamed from: d */
    private ImageButton f3651d;

    /* renamed from: e */
    private ImageButton f3652e;

    /* renamed from: f */
    private ImageButton f3653f;

    /* renamed from: g */
    private ImageButton f3654g;

    /* renamed from: h */
    private ProgressBar f3655h;

    /* renamed from: i */
    private boolean f3656i;

    public AppWidget91Layout4x1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected ImageView getAlbumImageView() {
        return this.f3649b;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.f3649b = (ImageView) findViewById(R.id.image_album_cover);
        this.f3650c = (TextView) findViewById(R.id.text_title);
        this.f3651d = (ImageButton) findViewById(R.id.button_play_prev);
        this.f3652e = (ImageButton) findViewById(R.id.button_play_pause);
        this.f3653f = (ImageButton) findViewById(R.id.button_play_next);
        this.f3654g = (ImageButton) findViewById(R.id.button_minilyric);
        this.f3655h = (ProgressBar) findViewById(R.id.seekbar_progress);
        this.f3651d.setOnLongClickListener(this);
        this.f3652e.setOnLongClickListener(this);
        this.f3653f.setOnLongClickListener(this);
        this.f3654g.setOnLongClickListener(this);
        this.f3650c.setOnLongClickListener(this);
        this.f3649b.setOnLongClickListener(this);
        this.f3655h.setOnLongClickListener(this);
        this.f3649b.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.appwidget.AppWidget91Layout4x1.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppWidget91Layout4x1.this.startActivity(Action.START_ENTRY);
            }
        });
        this.f3654g.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.appwidget.AppWidget91Layout4x1.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppWidget91Layout4x1.this.startService("switch_desktop_lyric_hide_show_command");
            }
        });
        this.f3651d.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.appwidget.AppWidget91Layout4x1.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppWidget91Layout4x1.this.startService("previous_command");
            }
        });
        this.f3653f.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.appwidget.AppWidget91Layout4x1.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppWidget91Layout4x1.this.startService("next_command");
            }
        });
        this.f3652e.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.appwidget.AppWidget91Layout4x1.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppWidget91Layout4x1.this.startService("play_pause_command");
                AppWidget91Layout4x1.this.mClickedPlay = true;
            }
        });
        LogUtils.debug("AppWidget91Layout4x1", "onFinishInflate");
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void onMetaChanged(String str, String str2, String str3) {
        if (TTTextUtils.isValidateMediaString(str)) {
            str3 = ((Object) TTTextUtils.validateString(BaseApplication.getApplication(), str)) + " - " + ((Object) TTTextUtils.validateString(BaseApplication.getApplication(), str3));
        }
        this.f3650c.setText(str3);
        MediaItem m2454v = this.mSupport.m2454v();
        if (m2454v != null && !m2454v.isNull()) {
            this.mSongDuration = m2454v.getDuration().intValue();
        }
        m7145a(0, this.mSongDuration, this.mSupport.m2464l());
    }

    /* renamed from: a */
    private void m7145a(int i, int i2, float f) {
        int i3 = (int) (i2 * f);
        if (i3 < 0) {
            i3 = 0;
        }
        this.f3655h.setProgress(i);
        this.f3655h.setMax(i2);
        this.f3655h.setSecondaryProgress(i3);
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void setPlayStateBackground(PlayStatus playStatus) {
        String str;
        String str2;
        StateListDrawable newSelector;
        if (!StringUtils.isEmpty(this.f3648a)) {
            if (PlayStatus.STATUS_PLAYING == playStatus) {
                str = this.f3648a + "pandawidget_ttpod_pausenormal_4x1.png";
                str2 = this.f3648a + "pandawidget_ttpod_pausepressed_4x1.png";
            } else {
                str = this.f3648a + "pandawidget_ttpod_playnormal_4x1.png";
                str2 = this.f3648a + "pandawidget_ttpod_playpressed_4x1.png";
            }
            if (FileUtils.m8414b(str) && FileUtils.m8414b(str2) && (newSelector = newSelector(getContext(), str, str2)) != null) {
                this.f3652e.setImageDrawable(newSelector);
                return;
            }
        }
        this.f3652e.setImageResource(PlayStatus.STATUS_PLAYING == playStatus ? R.drawable.img_appwidget_pause : R.drawable.img_appwidget_play);
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void setPlayModeBackground(PlayMode playMode) {
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void setMiniLyricButton(boolean z) {
        StateListDrawable newSelector;
        if (!StringUtils.isEmpty(this.f3648a)) {
            String str = z ? this.f3648a + "pandawidget_ttpod_minilyricon_4x1.png" : this.f3648a + "pandawidget_ttpod_minilyricoff_4x1.png";
            if (FileUtils.m8414b(str) && (newSelector = newSelector(getContext(), str, str)) != null) {
                this.f3654g.setImageDrawable(newSelector);
                return;
            }
        }
        this.f3654g.setImageResource(z ? R.drawable.img_appwidget_minilyric_on : R.drawable.img_appwidget_minilyric_off);
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void applyTheme(Intent intent) {
        String stringExtra = intent.getStringExtra("widgetSkinPath");
        if (!StringUtils.isEmpty(stringExtra)) {
            String str = stringExtra + "/widget/ttpod/";
            if (!str.equals(this.f3648a)) {
                this.f3648a = str;
                if (FeedbackItem.STATUS_WAITING.equals(stringExtra.substring(stringExtra.indexOf("Themes") + 7)) && !this.f3656i) {
                    this.f3656i = true;
                    m7144b();
                } else if (new File(this.f3648a).exists()) {
                    this.f3656i = false;
                    m7146a();
                } else if (!this.f3656i) {
                    this.f3656i = true;
                    m7144b();
                }
            }
        }
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void updatePlayTime() {
        m7145a(this.mSupport.m2465k().intValue(), this.mSongDuration, this.mSupport.m2464l());
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void registerPreferenceListener() {
        Preferences.m3023a(getContext(), PreferencesID.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.mOnPreferencesChangeListener);
        Preferences.m3023a(getContext(), PreferencesID.CURRENT_ARTIST_BITMAP_PATH, this.mOnPreferencesChangeListener);
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void unRegisterPreferenceListener() {
        Preferences.m2940b(getContext(), PreferencesID.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.mOnPreferencesChangeListener);
        Preferences.m2940b(getContext(), PreferencesID.CURRENT_ARTIST_BITMAP_PATH, this.mOnPreferencesChangeListener);
    }

    /* renamed from: a */
    private void m7146a() {
        try {
            String str = this.f3648a + "pandawidget_ttpod_prevnormal_4x1.png";
            String str2 = this.f3648a + "pandawidget_ttpod_prevpressed_4x1.png";
            if (FileUtils.m8414b(str) && FileUtils.m8414b(str2)) {
                this.f3651d.setImageDrawable(newSelector(getContext(), str, str2));
            } else {
                this.f3651d.setImageResource(R.drawable.img_appwidget_play_prev);
            }
            String str3 = this.f3648a + "pandawidget_ttpod_nextnormal_4x1.png";
            String str4 = this.f3648a + "pandawidget_ttpod_nextpressed_4x1.png";
            if (FileUtils.m8414b(str3) && FileUtils.m8414b(str4)) {
                this.f3653f.setImageDrawable(newSelector(getContext(), str3, str4));
            } else {
                this.f3653f.setImageResource(R.drawable.img_appwidget_play_next);
            }
            setMiniLyricButton(Preferences.m2838r());
            setPlayStateBackground(this.mSupport.m2463m());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    private void m7144b() {
        setMiniLyricButton(Preferences.m2838r());
        setPlayStateBackground(this.mSupport.m2463m());
        this.f3651d.setImageResource(R.drawable.img_appwidget_play_prev);
        this.f3653f.setImageResource(R.drawable.img_appwidget_play_next);
    }
}
