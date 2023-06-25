package com.sds.android.ttpod.component.p087d.p088a;

import android.app.Dialog;
import android.content.Context;
import android.media.AudioManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.component.d.a.m */
/* loaded from: classes.dex */
public class PlayerMenuDialog extends Dialog {

    /* renamed from: a */
    private SeekBar f3979a;

    /* renamed from: b */
    private InterfaceC1165a f3980b;

    /* renamed from: c */
    private InterfaceC1166b f3981c;

    /* renamed from: d */
    private AudioManager f3982d;

    /* renamed from: e */
    private IconTextView f3983e;

    /* renamed from: f */
    private ArrayList<ActionItem> f3984f;

    /* renamed from: g */
    private View.OnClickListener f3985g;

    /* compiled from: PlayerMenuDialog.java */
    /* renamed from: com.sds.android.ttpod.component.d.a.m$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1165a {
        void onAdjustOptionSelected();

        void onDownloadSelected();

        void onLyricOptionSelected();

        void onMoreOptionSelected();

        void onPictureOptionSelected();

        void onSetRingtoneSelected();

        void onShareOptionSelected();
    }

    /* compiled from: PlayerMenuDialog.java */
    /* renamed from: com.sds.android.ttpod.component.d.a.m$b */
    /* loaded from: classes.dex */
    public interface InterfaceC1166b {
        void onVolumeChanged(int i, int i2);
    }

    /* renamed from: a */
    public void m6803a(InterfaceC1165a interfaceC1165a) {
        this.f3980b = interfaceC1165a;
    }

    /* renamed from: a */
    public void m6802a(InterfaceC1166b interfaceC1166b) {
        this.f3981c = interfaceC1166b;
    }

    public PlayerMenuDialog(Context context) {
        super(context, R.style.Dialog_Transparent);
        this.f3985g = new View.OnClickListener() { // from class: com.sds.android.ttpod.component.d.a.m.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlayerMenuDialog.this.dismiss();
                switch (((ActionItem) view.getTag()).m7005e()) {
                    case 0:
                        PlayerMenuDialog.this.f3980b.onPictureOptionSelected();
                        //LocalStatistic.m5085u();
                        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_MENU_SEARCH_PIC.getValue(), SPage.PAGE_PLAYER_MENU.getValue(), SPage.PAGE_NONE.getValue()).post();
                        return;
                    case 1:
                        PlayerMenuDialog.this.f3980b.onLyricOptionSelected();
                        //LocalStatistic.m5084v();
                        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_MENU_SEARCH_LYRIC.getValue(), SPage.PAGE_PLAYER_MENU.getValue(), SPage.PAGE_NONE.getValue()).post();
                        return;
                    case 2:
                        PlayerMenuDialog.this.f3980b.onAdjustOptionSelected();
                        //LocalStatistic.m5083w();
                        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_MENU_CONFIG_LYRIC.getValue(), SPage.PAGE_PLAYER_MENU.getValue(), SPage.PAGE_PLAYER_CONFIG_LYRIC.getValue()).post();
                        return;
                    case 3:
                        PlayerMenuDialog.this.f3980b.onSetRingtoneSelected();
                        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_MENU_SET_RING.getValue(), SPage.PAGE_PLAYER_MENU.getValue(), SPage.PAGE_DIALOG_RING.getValue()).post();
                        return;
                    case 4:
                        PlayerMenuDialog.this.f3980b.onMoreOptionSelected();
                        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_MENU_MORE.getValue(), SPage.PAGE_PLAYER_MENU.getValue(), SPage.PAGE_PLAYER_MENU_MORE.getValue()).post();
                        return;
                    case 5:
                        PlayerMenuDialog.this.f3980b.onDownloadSelected();
                        //LocalStatistic.m5080z();
                        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PLAYER_MENU_DOWNLOAD.getValue(), SPage.PAGE_PLAYER_MENU.getValue(), SPage.PAGE_DIALOG_DOWNLOAD.getValue()).post();
                        return;
                    case 6:
                        PlayerMenuDialog.this.f3980b.onShareOptionSelected();
                        //LocalStatistic.m5082x();
                        return;
                    default:
                        return;
                }
            }
        };
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.popups_lyric_pics_panel);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.BOTTOM;
        attributes.width = DisplayUtils.m7225c();
        window.setAttributes(attributes);
        window.setWindowAnimations(R.style.Dialog_Window_Anim);
        MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        this.f3984f = new ArrayList<>();
        boolean z = (m3225N.isNull() || FileUtils.m8419a(m3225N.getLocalDataSource()) || !m3225N.isOnline()) ? false : true;
        this.f3984f.add(new ActionItem(0, 0, (int) R.string.menu_search_artist_pic, (int) R.string.icon_search_pic));
        this.f3984f.add(new ActionItem(1, 0, (int) R.string.menu_search_lyric, (int) R.string.icon_search_lyric));
        this.f3984f.add(new ActionItem(2, 0, (int) R.string.adjust_lyric, (int) R.string.icon_edit_lyric));
        if (z) {
            this.f3984f.add(new ActionItem(5, 0, (int) R.string.setting_download, (int) R.string.icon_download));
            if (m3225N.containMV()) {
                this.f3984f.add(new ActionItem(4, 0, (int) R.string.more, (int) R.string.icon_more_horizontal));
            } else {
                this.f3984f.add(new ActionItem(6, 0, (int) R.string.share, (int) R.string.icon_share_with_dot));
            }
        } else if (FileUtils.m8419a(m3225N.getLocalDataSource())) {
            this.f3984f.add(new ActionItem(3, 0, (int) R.string.ringtone, (int) R.string.icon_set_ringtone));
            this.f3984f.add(new ActionItem(4, 0, (int) R.string.more, (int) R.string.icon_more_horizontal));
        }
        m6804a(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m6805a(int i) {
        this.f3983e.setText(i == 0 ? R.string.icon_volume_silence : R.string.icon_volume_voice);
    }

    /* renamed from: a */
    private void m6804a(Context context) {
        this.f3982d = (AudioManager) getContext().getSystemService("audio");
        this.f3979a = (SeekBar) findViewById(R.id.lrc_pics_volume);
        this.f3979a.setMax(this.f3982d.getStreamMaxVolume(3));
        this.f3983e = (IconTextView) findViewById(R.id.itv_volume);
        this.f3979a.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.sds.android.ttpod.component.d.a.m.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (PlayerMenuDialog.this.f3981c != null) {
                    PlayerMenuDialog.this.f3981c.onVolumeChanged(i, seekBar.getMax());
                }
                PlayerMenuDialog.this.m6805a(i);
                if (z) {
                    PlayerMenuDialog.this.f3982d.setStreamVolume(3, i, 0);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                //LocalStatistic.m5081y();
            }
        });
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_function);
        Iterator<ActionItem> it = this.f3984f.iterator();
        while (it.hasNext()) {
            ActionItem next = it.next();
            View inflate = LayoutInflater.from(context).inflate(R.layout.popups_lyric_pics_panel_item, (ViewGroup) null);
            ((IconTextView) inflate.findViewById(R.id.itv_icon)).setText(next.m7001i());
            ((TextView) inflate.findViewById(R.id.tv_text)).setText(next.m7006d());
            inflate.setTag(next);
            inflate.setOnClickListener(this.f3985g);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.weight = 1.0f;
            linearLayout.addView(inflate, layoutParams);
        }
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        this.f3979a.post(new Runnable() { // from class: com.sds.android.ttpod.component.d.a.m.3
            @Override // java.lang.Runnable
            public void run() {
                int streamVolume = PlayerMenuDialog.this.f3982d.getStreamVolume(3);
                PlayerMenuDialog.this.f3979a.setProgress(streamVolume);
                PlayerMenuDialog.this.m6805a(streamVolume);
            }
        });
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
            case 82:
                dismiss();
                break;
            case 24:
            case 25:
                this.f3979a.setProgress(this.f3982d.getStreamVolume(3));
                break;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
