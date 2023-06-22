package com.sds.android.ttpod.component.p087d.p088a;

import android.content.Context;
import android.content.DialogInterface;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.modules.skin.p130c.DateTimeUtils;
import com.sds.android.ttpod.framework.p106a.p107a.LocalStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.text.DecimalFormat;

/* renamed from: com.sds.android.ttpod.component.d.a.f */
/* loaded from: classes.dex */
public class MediaInfoDialog extends ScrollableDialog {

    /* renamed from: a */
    private View f3946a;

    public MediaInfoDialog(Context context, final MediaItem mediaItem, final DialogInterface.OnDismissListener onDismissListener) {
        super(context);
        m6838a(context, mediaItem);
        setTitle(R.string.media_info);
        m7261a(R.string.edit, new BaseDialog.InterfaceC1064a<MediaInfoDialog>() { // from class: com.sds.android.ttpod.component.d.a.f.1
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MediaInfoDialog mediaInfoDialog) {
                LocalStatistic.m5136aR();
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", SAction.ACTION_RIGHT_MENU_MUSIC_INFO_EDIT.getValue(), SPage.PAGE_DIALOG_MORE.getValue(), SPage.PAGE_DIALOG_MORE_MUSIC_INFO_EDIT.getValue());
                sUserEvent.append("song_id", mediaItem.getLocalDataSource());
                sUserEvent.setPageParameter(true);
                sUserEvent.post();
                PopupsUtils.m6712b(MediaInfoDialog.this.getContext(), mediaItem, onDismissListener);
            }
        }, R.string.cancel, (BaseDialog.InterfaceC1064a) null);
        setOnDismissListener(onDismissListener);
    }

    /* renamed from: a */
    private void m6838a(Context context, MediaItem mediaItem) {
        m6837a(this.f3946a, R.id.title, R.string.media_info_label_title, mediaItem.getTitle());
        m6837a(this.f3946a, R.id.artist, R.string.media_info_label_artist, TTTextUtils.validateString(context, mediaItem.getArtist()));
        m6837a(this.f3946a, R.id.album, R.string.media_info_label_album, TTTextUtils.validateString(context, mediaItem.getAlbum()));
        m6837a(this.f3946a, R.id.genre, R.string.media_info_label_genre, TTTextUtils.validateString(context, mediaItem.getGenre()));
        m6837a(this.f3946a, R.id.time, R.string.media_info_label_time, DateTimeUtils.m3748a(mediaItem.getDuration().intValue()));
        m6837a(this.f3946a, R.id.size, R.string.media_info_label_size, Formatter.formatFileSize(context, FileUtils.m8405g(mediaItem.getLocalDataSource())));
        m6837a(this.f3946a, R.id.format, R.string.media_info_label_format, FileUtils.m8399m(mediaItem.getLocalDataSource()));
        m6837a(this.f3946a, R.id.year, R.string.media_info_label_year, String.valueOf(mediaItem.getYear()));
        m6837a(this.f3946a, R.id.bitrate, R.string.media_info_label_bitrate, context.getString(R.string.media_info_content_bitrate, mediaItem.getBitRate()));
        m6837a(this.f3946a, R.id.track, R.string.media_info_label_track, String.valueOf(mediaItem.getTrack()));
        m6837a(this.f3946a, R.id.samplerate, R.string.media_info_label_samplerate, context.getString(R.string.media_info_content_samplerate, new DecimalFormat("####.0").format(mediaItem.getSampleRate().intValue() / 1000.0f)));
        m6837a(this.f3946a, R.id.channel, R.string.media_info_label_channel, String.valueOf(mediaItem.getChannels()));
        ((TextView) this.f3946a.findViewById(R.id.path_content)).setText(mediaItem.getLocalDataSource());
        ((TextView) this.f3946a.findViewById(R.id.comment_content)).setText(mediaItem.getComment());
    }

    @Override // com.sds.android.ttpod.component.p087d.p088a.ScrollableDialog
    /* renamed from: b */
    protected View mo6793b(Context context) {
        this.f3946a = View.inflate(context, R.layout.dialog_media_info, null);
        return this.f3946a;
    }

    /* renamed from: a */
    private void m6837a(View view, int i, int i2, CharSequence charSequence) {
        View findViewById = view.findViewById(i);
        ((TextView) findViewById.findViewById(R.id.label)).setText(i2);
        ((TextView) findViewById.findViewById(R.id.content)).setText(charSequence);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: b */
    public MediaInfoDialog mo2037a() {
        return this;
    }
}
