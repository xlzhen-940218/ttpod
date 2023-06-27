package com.sds.android.ttpod.component.appwidget;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.VoiceOfChina;
import com.sds.android.cloudapi.ttpod.api.VoiceOfChinaAPI;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.cloudapi.ttpod.result.VoiceOfChinaListResult;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.web.WebActivity;
import com.sds.android.ttpod.fragment.WebFragment;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.environment.PreferencesID;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.framework.support.p134a.PlayMode;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class AppWidget91VoiceLayout4x4 extends AppWidget91Base implements View.OnClickListener {

    /* renamed from: a */
    private static int[] f3667a = {R.drawable.img_appwidget91_voice_playmode_repeat_all, R.drawable.img_appwidget91_voice_playmode_repeat_current, R.drawable.img_appwidget91_voice_playmode_normal, R.drawable.img_appwidget91_voice_playmode_shuffle};

    /* renamed from: b */
    private ImageView f3668b;

    /* renamed from: c */
    private TextView f3669c;

    /* renamed from: d */
    private TextView f3670d;

    /* renamed from: e */
    private TextView f3671e;

    /* renamed from: f */
    private TextView f3672f;

    /* renamed from: g */
    private TextView f3673g;

    /* renamed from: h */
    private ProgressBar f3674h;

    /* renamed from: i */
    private TextView f3675i;

    /* renamed from: j */
    private TextView f3676j;

    /* renamed from: k */
    private TextView[] f3677k;

    /* renamed from: l */
    private LinearLayout f3678l;

    /* renamed from: m */
    private ImageButton f3679m;

    /* renamed from: n */
    private ImageButton f3680n;

    /* renamed from: o */
    private ImageButton f3681o;

    /* renamed from: p */
    private ImageButton f3682p;

    /* renamed from: q */
    private ImageButton f3683q;

    /* renamed from: r */
    private Button f3684r;

    /* renamed from: s */
    private ImageButton f3685s;

    /* renamed from: t */
    private ImageView f3686t;

    /* renamed from: u */
    private View f3687u;

    /* renamed from: v */
    private RelativeLayout f3688v;

    /* renamed from: w */
    private List<MediaItem> f3689w;

    /* renamed from: x */
    private Timer f3690x;

    public AppWidget91VoiceLayout4x4(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3677k = new TextView[3];
        this.f3689w = new ArrayList();
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected ImageView getAlbumImageView() {
        return this.f3668b;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        BaseModule.setContext(getContext());
        this.f3686t = (ImageView) findViewById(R.id.image_bg_default);
        this.f3668b = (ImageView) findViewById(R.id.image_album_cover);
        this.f3669c = (TextView) findViewById(R.id.text_artist);
        this.f3671e = (TextView) findViewById(R.id.text_album);
        this.f3670d = (TextView) findViewById(R.id.text_title);
        this.f3672f = (TextView) findViewById(R.id.text_time_current);
        this.f3673g = (TextView) findViewById(R.id.text_time_duration);
        this.f3674h = (ProgressBar) findViewById(R.id.seekbar_progress);
        this.f3678l = (LinearLayout) findViewById(R.id.bg_buttons);
        this.f3688v = (RelativeLayout) findViewById(R.id.widget_bg);
        this.f3679m = (ImageButton) findViewById(R.id.button_playmode);
        this.f3680n = (ImageButton) findViewById(R.id.button_play_prev);
        this.f3681o = (ImageButton) findViewById(R.id.button_play_pause);
        this.f3682p = (ImageButton) findViewById(R.id.button_play_next);
        this.f3683q = (ImageButton) findViewById(R.id.button_minilyric);
        this.f3675i = (TextView) findViewById(R.id.text_voice_title);
        this.f3677k[0] = (TextView) findViewById(R.id.text_song1);
        this.f3677k[1] = (TextView) findViewById(R.id.text_song2);
        this.f3677k[2] = (TextView) findViewById(R.id.text_song3);
        this.f3684r = (Button) findViewById(R.id.button_more);
        this.f3685s = (ImageButton) findViewById(R.id.button_refresh);
        this.f3676j = (TextView) findViewById(R.id.textview_load_failed);
        this.f3687u = findViewById(R.id.layout_list_item);
        this.f3686t.setOnLongClickListener(this);
        this.f3669c.setOnLongClickListener(this);
        this.f3670d.setOnLongClickListener(this);
        this.f3672f.setOnLongClickListener(this);
        this.f3673g.setOnLongClickListener(this);
        this.f3675i.setOnLongClickListener(this);
        this.f3677k[0].setOnLongClickListener(this);
        this.f3677k[1].setOnLongClickListener(this);
        this.f3677k[2].setOnLongClickListener(this);
        this.f3678l.setOnLongClickListener(this);
        this.f3688v.setOnLongClickListener(this);
        this.f3679m.setOnLongClickListener(this);
        this.f3680n.setOnLongClickListener(this);
        this.f3681o.setOnLongClickListener(this);
        this.f3682p.setOnLongClickListener(this);
        this.f3683q.setOnLongClickListener(this);
        this.f3668b.setOnLongClickListener(this);
        this.f3681o.setOnClickListener(this);
        this.f3680n.setOnClickListener(this);
        this.f3682p.setOnClickListener(this);
        this.f3679m.setOnClickListener(this);
        this.f3668b.setOnClickListener(this);
        this.f3683q.setOnClickListener(this);
        this.f3684r.setOnClickListener(this);
        this.f3685s.setOnClickListener(this);
        this.f3677k[0].setOnClickListener(this);
        this.f3677k[1].setOnClickListener(this);
        this.f3677k[2].setOnClickListener(this);
        if (m7143a()) {
            m7135b();
        } else {
            setVoiceOfChinaListVisible(!this.f3689w.isEmpty());
        }
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    public void onDestory(int i) {
        super.onDestory(i);
        this.f3690x.cancel();
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    public void onLoad(int i) {
        super.onLoad(i);
        this.f3690x = new Timer();
        try {
            this.f3690x.schedule(new TimerTask() { // from class: com.sds.android.ttpod.component.appwidget.AppWidget91VoiceLayout4x4.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (AppWidget91VoiceLayout4x4.this.m7143a()) {
                        AppWidget91VoiceLayout4x4.this.m7135b();
                    }
                }
            }, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-07-25 23:00:00"), 604800000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m7143a() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService("connectivity");
        return (connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m7135b() {
        VoiceOfChinaAPI.m8937a(1213, 1, 1).m8544a(new RequestCallback<VoiceOfChinaListResult>() { // from class: com.sds.android.ttpod.component.appwidget.AppWidget91VoiceLayout4x4.2
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(VoiceOfChinaListResult voiceOfChinaListResult) {
                VoiceOfChina voiceOfChina;
                ArrayList<VoiceOfChina> dataList = voiceOfChinaListResult.getDataList();
                if (dataList != null && dataList.size() > 0 && (voiceOfChina = dataList.get(0)) != null) {
                    AppWidget91VoiceLayout4x4.this.f3675i.setText(voiceOfChina.getTitle());
                    AppWidget91VoiceLayout4x4.this.m7134b(voiceOfChina.getId());
                    AppWidget91VoiceLayout4x4.this.setVoiceOfChinaListVisible(true);
                }
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(VoiceOfChinaListResult voiceOfChinaListResult) {
                AppWidget91VoiceLayout4x4.this.setVoiceOfChinaListVisible(!AppWidget91VoiceLayout4x4.this.f3689w.isEmpty());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVoiceOfChinaListVisible(boolean z) {
        if (z) {
            this.f3687u.setVisibility(View.VISIBLE);
            this.f3676j.setVisibility(View.INVISIBLE);
            return;
        }
        this.f3687u.setVisibility(View.INVISIBLE);
        this.f3676j.setVisibility(View.VISIBLE);
    }

    /* renamed from: a */
    private void m7142a(int i) {
        MediaItem mediaItem;
        if (i >= 0 && i < this.f3689w.size() && (mediaItem = this.f3689w.get(i)) != null) {
            try {
                Context context = getContext();
                MediaStorage.clearGroup(context, MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
                MediaStorage.insertMediaItems(context, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, this.f3689w);
                context.startService(new Intent(context, SupportService.class).putExtra("command", "sync_command").putExtra("group", MediaStorage.GROUP_ID_ONLINE_TEMPORARY).putExtra("media_source", mediaItem.getID()));
                Bundle bundle = new Bundle();
                bundle.putParcelable("mediaItem", mediaItem);
                context.startService(new Intent(context, SupportService.class).putExtra("command", "play_command").putExtra("group", MediaStorage.GROUP_ID_ONLINE_TEMPORARY).putExtra("media_source", mediaItem.getID()).putExtras(bundle));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m7134b(int i) {
        VoiceOfChinaAPI.m8936b(i, 1, 100).m8544a(new RequestCallback<OnlineMediaItemsResult>() { // from class: com.sds.android.ttpod.component.appwidget.AppWidget91VoiceLayout4x4.3
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(OnlineMediaItemsResult onlineMediaItemsResult) {
                ArrayList<OnlineMediaItem> dataList = onlineMediaItemsResult.getDataList();
                if (dataList != null) {
                    AppWidget91VoiceLayout4x4.this.f3689w.clear();
                    Iterator<OnlineMediaItem> it = dataList.iterator();
                    while (it.hasNext()) {
                        AppWidget91VoiceLayout4x4.this.f3689w.add(MediaItemUtils.m4716a(it.next()));
                    }
                    AppWidget91VoiceLayout4x4.this.m7140a(0, dataList);
                    AppWidget91VoiceLayout4x4.this.m7140a(1, dataList);
                    AppWidget91VoiceLayout4x4.this.m7140a(2, dataList);
                }
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(OnlineMediaItemsResult onlineMediaItemsResult) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m7140a(int i, ArrayList<OnlineMediaItem> arrayList) {
        if (i < 0 || i >= arrayList.size() || arrayList.get(i) == null) {
            this.f3677k[i].setText("");
            return;
        }
        OnlineMediaItem onlineMediaItem = arrayList.get(i);
        this.f3677k[i].setText((i + 1) + "." + onlineMediaItem.getTitle() + "--" + onlineMediaItem.getArtist());
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void onMetaChanged(String str, String str2, String str3) {
        this.f3670d.setText(str3);
        this.f3669c.setText(TTTextUtils.validateString(getContext(), str));
        this.f3671e.setText(TTTextUtils.validateString(getContext(), str2));
        MediaItem m2454v = this.mSupport.m2454v();
        if (m2454v != null && !m2454v.isNull()) {
            this.mSongDuration = m2454v.getDuration().intValue();
        }
        m7141a(0, this.mSongDuration, this.mSupport.m2464l());
        this.f3672f.setText(getFormatTime(0));
        this.f3673g.setText(getFormatTime(this.mSongDuration));
    }

    /* renamed from: a */
    private void m7141a(int i, int i2, float f) {
        int i3 = (int) (i2 * f);
        if (i3 < 0) {
            i3 = 0;
        }
        this.f3674h.setProgress(i);
        this.f3674h.setMax(i2);
        this.f3674h.setSecondaryProgress(i3);
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void setPlayModeBackground(PlayMode playMode) {
        if (playMode != null) {
            try {
                this.f3679m.setImageResource(f3667a[playMode.ordinal()]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void setMiniLyricButton(boolean z) {
        this.f3683q.setImageResource(z ? R.drawable.img_appwidget91_voice_minilyric_on : R.drawable.img_appwidget91_voice_minilyric_off);
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void applyTheme(Intent intent) {
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void setPlayStateBackground(PlayStatus playStatus) {
        this.f3681o.setImageResource(PlayStatus.STATUS_PLAYING == playStatus ? R.drawable.xml_appwidget91_voice_pause : R.drawable.xml_appwidget91_voice_play);
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void registerPreferenceListener() {
        Preferences.m3023a(getContext(), PreferencesID.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.mOnPreferencesChangeListener);
        Preferences.m3023a(getContext(), PreferencesID.PLAY_MODE, this.mOnPreferencesChangeListener);
        Preferences.m3023a(getContext(), PreferencesID.CURRENT_ARTIST_BITMAP_PATH, this.mOnPreferencesChangeListener);
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void unRegisterPreferenceListener() {
        Preferences.m2940b(getContext(), PreferencesID.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.mOnPreferencesChangeListener);
        Preferences.m2940b(getContext(), PreferencesID.PLAY_MODE, this.mOnPreferencesChangeListener);
        Preferences.m2940b(getContext(), PreferencesID.CURRENT_ARTIST_BITMAP_PATH, this.mOnPreferencesChangeListener);
    }

    @Override // com.sds.android.ttpod.component.appwidget.AppWidget91Base
    protected void updatePlayTime() {
        this.f3672f.setText(getFormatTime(this.mSupport.m2465k().intValue()));
        this.f3673g.setText(getFormatTime(this.mSongDuration));
        m7141a(this.mSupport.m2465k().intValue(), this.mSongDuration, this.mSupport.m2464l());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_album_cover /* 2131231078 */:
                startActivity(Action.START_ENTRY);
                return;
            case R.id.button_play_prev /* 2131231085 */:
                startService("previous_command");
                return;
            case R.id.button_play_pause /* 2131231086 */:
                this.mClickedPlay = true;
                startService("play_pause_command");
                return;
            case R.id.button_play_next /* 2131231087 */:
                startService("next_command");
                return;
            case R.id.button_minilyric /* 2131231088 */:
                startService("switch_desktop_lyric_hide_show_command");
                return;
            case R.id.button_playmode /* 2131231095 */:
                startService("switch_play_mode_command");
                return;
            case R.id.button_refresh /* 2131231101 */:
                m7135b();
                return;
            case R.id.button_more /* 2131231102 */:
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.setData(Uri.parse("http://m.voice.dongting.com/?from=ttpod&v=%s"));
                intent.putExtra(WebFragment.EXTRA_TITLE, getContext().getString(R.string.voice_of_china));
                intent.putExtra(WebFragment.EXTRA_HINT_BANNER_SHOW, false);
                intent.addFlags(268435456);
                getContext().startActivity(intent);
                return;
            case R.id.text_song1 /* 2131231104 */:
                m7142a(0);
                return;
            case R.id.text_song2 /* 2131231105 */:
                m7142a(1);
                return;
            case R.id.text_song3 /* 2131231106 */:
                m7142a(2);
                return;
            default:
                return;
        }
    }
}
