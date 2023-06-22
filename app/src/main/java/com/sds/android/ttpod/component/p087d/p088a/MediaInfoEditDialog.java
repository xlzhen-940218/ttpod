package com.sds.android.ttpod.component.p087d.p088a;

import android.content.Context;
import android.content.DialogInterface;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p085b.CheckableActionItem;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectCache;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectUtils;
import com.sds.android.ttpod.framework.p106a.p107a.LocalStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.text.TTTextUtils;

/* renamed from: com.sds.android.ttpod.component.d.a.g */
/* loaded from: classes.dex */
public class MediaInfoEditDialog extends ScrollableDialog {

    /* renamed from: a */
    private View f3950a;

    /* renamed from: b */
    private EditText f3951b;

    /* renamed from: c */
    private EditText f3952c;

    /* renamed from: d */
    private EditText f3953d;

    /* renamed from: e */
    private EditText f3954e;

    /* renamed from: f */
    private EditText f3955f;

    /* renamed from: g */
    private EditText f3956g;

    /* renamed from: h */
    private EditText f3957h;

    public MediaInfoEditDialog(Context context, final MediaItem mediaItem, DialogInterface.OnDismissListener onDismissListener) {
        super(context);
        m6831a(mediaItem);
        setTitle(R.string.media_info);
        m7261a(R.string.save, new BaseDialog.InterfaceC1064a<MediaInfoEditDialog>() { // from class: com.sds.android.ttpod.component.d.a.g.1
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MediaInfoEditDialog mediaInfoEditDialog) {
                LocalStatistic.m5135aS();
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", SAction.ACTION_RIGHT_MENU_MUSIC_INFO_SAVE.getValue(), SPage.PAGE_NONE.getValue(), SPage.PAGE_NONE.getValue());
                sUserEvent.setPageParameter(true);
                sUserEvent.append("song_id", mediaItem.getLocalDataSource()).post();
                MediaInfoEditDialog.this.m6827c(mediaItem);
            }
        }, R.string.cancel, (BaseDialog.InterfaceC1064a) null);
        setOnDismissListener(onDismissListener);
    }

    /* renamed from: a */
    private void m6831a(final MediaItem mediaItem) {
        this.f3951b = m6834a(this.f3950a, R.id.title, R.string.media_info_label_title, R.string.media_info_hint_title, mediaItem.getTitle());
        this.f3952c = m6834a(this.f3950a, R.id.artist, R.string.media_info_label_artist, R.string.media_info_hint_artst, TTTextUtils.validateString(getContext(), mediaItem.getArtist()));
        this.f3953d = m6834a(this.f3950a, R.id.album, R.string.media_info_label_album, R.string.media_info_hint_album, TTTextUtils.validateString(getContext(), mediaItem.getAlbum()));
        this.f3954e = m6834a(this.f3950a, R.id.genre, R.string.media_info_label_genre, R.string.med_info_hint_genre, TTTextUtils.validateString(getContext(), mediaItem.getGenre()));
        this.f3955f = m6834a(this.f3950a, R.id.track, R.string.media_info_label_track, R.string.media_info_hint_track, String.valueOf(mediaItem.getTrack()));
        this.f3956g = m6834a(this.f3950a, R.id.year, R.string.media_info_label_year, R.string.media_info_hint_year, String.valueOf(mediaItem.getYear()));
        this.f3957h = m6834a(this.f3950a, R.id.comment, R.string.media_info_label_comment, R.string.media_info_hint_comment, mediaItem.getComment());
        this.f3955f.setInputType(2);
        this.f3956g.setInputType(2);
        this.f3956g.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        this.f3950a.findViewById(R.id.genre_spinner).setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.d.a.g.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MediaInfoEditDialog.this.m6828b(mediaItem);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m6828b(MediaItem mediaItem) {
        int i = 0;
        CheckableActionItem[] checkableActionItemArr = {new CheckableActionItem(0, R.string.media_info_genre_pop), new CheckableActionItem(1, R.string.media_info_genre_rock), new CheckableActionItem(2, R.string.media_info_genre_metal), new CheckableActionItem(3, R.string.media_info_genre_dance), new CheckableActionItem(4, R.string.media_info_genre_country), new CheckableActionItem(5, R.string.media_info_genre_jazz), new CheckableActionItem(6, R.string.media_info_genre_electronic), new CheckableActionItem(7, R.string.media_info_genre_classical), new CheckableActionItem(8, R.string.media_info_genre_bluce), new CheckableActionItem(9, R.string.media_info_genre_opera), new CheckableActionItem(10, R.string.media_info_genre_voice)};
        SingleChoiceListDialog singleChoiceListDialog = new SingleChoiceListDialog(getContext(), checkableActionItemArr, (BaseDialog.InterfaceC1064a<SingleChoiceListDialog>) null, (BaseDialog.InterfaceC1064a<SingleChoiceListDialog>) null);
        int i2 = -1;
        String obj = this.f3954e.getText().toString();
        if (!TextUtils.isEmpty(obj)) {
            int length = checkableActionItemArr.length;
            while (true) {
                if (i >= length) {
                    break;
                }
                CheckableActionItem checkableActionItem = checkableActionItemArr[i];
                if (!obj.equals(checkableActionItem.m7006d())) {
                    i++;
                } else {
                    i2 = checkableActionItem.m7005e();
                    break;
                }
            }
        }
        singleChoiceListDialog.setTitle(R.string.local_music_genre);
        singleChoiceListDialog.m6778c(i2);
        singleChoiceListDialog.m6844a(new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.component.d.a.g.3
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(ActionItem actionItem, int i3) {
                MediaInfoEditDialog.this.f3954e.setText(actionItem.m7006d());
            }
        });
        singleChoiceListDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m6827c(MediaItem mediaItem) {
        String m4341a = AudioEffectUtils.m4341a(mediaItem);
        mediaItem.setTitle(this.f3951b.getText().toString());
        mediaItem.setArtist(m6826c(this.f3952c.getText().toString()));
        mediaItem.setAlbum(m6826c(this.f3953d.getText().toString()));
        mediaItem.setGenre(m6826c(this.f3954e.getText().toString()));
        mediaItem.setComment(this.f3957h.getText().toString());
        try {
            mediaItem.setTrack(Integer.valueOf(Integer.parseInt(this.f3955f.getText().toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mediaItem.setYear(Integer.valueOf(Integer.parseInt(this.f3956g.getText().toString())));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        FileUtils.m8410c(m4341a, AudioEffectUtils.m4341a(mediaItem));
        m6825d(mediaItem);
        CommandCenter.m4607a().m4606a(new Command(CommandID.UPDATE_MEDIA_ITEM, mediaItem));
    }

    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void m6825d(MediaItem mediaItem) {
        AudioEffectCache audioEffectCache;
        String m8403i = FileUtils.m8403i(AudioEffectUtils.m4341a(mediaItem));
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!StringUtils.m8346a(m8403i)) {
            audioEffectCache = (AudioEffectCache) JSONUtils.fromJson(m8403i, AudioEffectCache.class);
            if (audioEffectCache == null) {
                audioEffectCache.m4405b(mediaItem.getArtist());
                audioEffectCache.m4402c(mediaItem.getTitle());
                CommandCenter.m4607a().m4596b(new Command(CommandID.SAVE_EFFECT, mediaItem, audioEffectCache, false));
                return;
            }
            return;
        }
        audioEffectCache = null;
        if (audioEffectCache == null) {
        }
    }

    @Override // com.sds.android.ttpod.component.p087d.p088a.ScrollableDialog
    /* renamed from: b */
    protected View mo6793b(Context context) {
        this.f3950a = View.inflate(context, R.layout.dialog_media_info_edit, null);
        return this.f3950a;
    }

    /* renamed from: a */
    private EditText m6834a(View view, int i, int i2, int i3, CharSequence charSequence) {
        View findViewById = view.findViewById(i);
        ((TextView) findViewById.findViewById(R.id.label)).setText(i2);
        EditText editText = (EditText) findViewById.findViewById(R.id.content);
        editText.setText(charSequence);
        editText.setHint(i3);
        return editText;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: b */
    public MediaInfoEditDialog mo2037a() {
        return this;
    }

    /* renamed from: c */
    private String m6826c(String str) {
        if (getContext().getString(R.string.media_unknown).equals(str)) {
            return "<unknown>";
        }
        return str;
    }
}
