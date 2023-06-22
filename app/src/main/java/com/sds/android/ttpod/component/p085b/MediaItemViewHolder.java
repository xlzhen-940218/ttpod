package com.sds.android.ttpod.component.p085b;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.CustomClickIconTextView;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.component.b.g */
/* loaded from: classes.dex */
public class MediaItemViewHolder extends ThemeManager.AbstractC2018a {

    /* renamed from: a */
    private View f3837a;

    /* renamed from: b */
    private View f3838b;

    /* renamed from: c */
    private IconTextView f3839c;

    /* renamed from: d */
    private CustomClickIconTextView f3840d;

    /* renamed from: e */
    private IconTextView f3841e;

    /* renamed from: f */
    private ImageView f3842f;

    /* renamed from: g */
    private IconTextView f3843g;

    /* renamed from: h */
    private IconTextView f3844h;

    /* renamed from: i */
    private IconTextView f3845i;

    /* renamed from: j */
    private IconTextView f3846j;

    /* renamed from: k */
    private TextView f3847k;

    /* renamed from: l */
    private TextView f3848l;

    /* renamed from: m */
    private TextView f3849m;

    /* renamed from: n */
    private ImageView f3850n;

    /* renamed from: o */
    private TextView f3851o;

    /* renamed from: p */
    private View f3852p;

    /* renamed from: q */
    private boolean f3853q = false;

    public MediaItemViewHolder(View view) {
        this.f3837a = view;
        this.f3838b = view.findViewById(R.id.view_play_state);
        this.f3840d = (CustomClickIconTextView) view.findViewById(R.id.menu_view);
        this.f3841e = (IconTextView) view.findViewById(R.id.check_view);
        this.f3842f = (ImageView) view.findViewById(R.id.flag_online_view);
        this.f3843g = (IconTextView) view.findViewById(R.id.flag_mv_view);
        this.f3844h = (IconTextView) view.findViewById(R.id.flag_quality_view);
        this.f3845i = (IconTextView) view.findViewById(R.id.downloadstate_view);
        this.f3846j = (IconTextView) view.findViewById(R.id.drag_handle);
        this.f3847k = (TextView) view.findViewById(R.id.tv_number);
        this.f3848l = (TextView) view.findViewById(R.id.title_view);
        this.f3849m = (TextView) view.findViewById(R.id.tv_media_item_singer);
        this.f3850n = (ImageView) view.findViewById(R.id.iv_media_item_fav);
        this.f3851o = (TextView) view.findViewById(R.id.tv_media_item_fav_count);
        this.f3839c = (IconTextView) view.findViewById(R.id.third_party_view);
        if (this.f3841e != null) {
            this.f3841e.setCheckable(true);
        }
        this.f3852p = view.findViewById(R.id.expandable);
        this.f3852p.setTag(new MediaItemMenuHolder(this.f3852p));
    }

    /* renamed from: b */
    public View m6966b() {
        return this.f3837a;
    }

    /* renamed from: c */
    public View m6963c() {
        return this.f3838b;
    }

    /* renamed from: d */
    public CustomClickIconTextView m6961d() {
        return this.f3840d;
    }

    /* renamed from: e */
    public ImageView m6960e() {
        return this.f3842f;
    }

    /* renamed from: f */
    public IconTextView m6959f() {
        return this.f3843g;
    }

    /* renamed from: g */
    public IconTextView m6958g() {
        return this.f3844h;
    }

    /* renamed from: h */
    public View m6957h() {
        return this.f3852p;
    }

    /* renamed from: b */
    public void m6965b(MediaItem mediaItem) {
        ((MediaItemMenuHolder) this.f3852p.getTag()).m6974b(mediaItem);
    }

    /* renamed from: a */
    public void mo6972a(MediaItem mediaItem) {
        if (mediaItem != null) {
            AudioQuality quality = mediaItem.quality();
            if (quality.ordinal() >= AudioQuality.HIGH.ordinal()) {
                this.f3844h.setVisibility(View.VISIBLE);
                this.f3844h.setText(quality == AudioQuality.LOSSLESS ? R.string.icon_text_wusun : R.string.icon_text_gaozhi);
                this.f3844h.setTextColor(quality == AudioQuality.LOSSLESS ? -2185667 : -8665764);
                return;
            }
            this.f3844h.setVisibility(View.GONE);
        }
    }

    /* renamed from: c */
    public void m6962c(MediaItem mediaItem) {
        ArrayList<OnlineMediaItem.OutListItem> outList = mediaItem.getOutList();
        OnlineMediaItem.OutListItem outListItem = !ListUtils.m4718a(outList) ? outList.get(0) : null;
        if (mediaItem.hasOutList() && (outListItem == null || StringUtils.m8346a(outListItem.getUrl()))) {
            ThemeManager.m3269a(this.f3848l, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        } else {
            ThemeManager.m3269a(this.f3848l, ThemeElement.SONG_LIST_ITEM_TEXT);
        }
    }

    /* renamed from: i */
    public IconTextView m6956i() {
        return this.f3845i;
    }

    /* renamed from: j */
    public TextView m6955j() {
        return this.f3848l;
    }

    /* renamed from: k */
    public TextView m6954k() {
        return this.f3849m;
    }

    /* renamed from: a */
    public void m6969a(CharSequence charSequence, int i, boolean z) {
        if (charSequence != null) {
            if (charSequence.length() > 40) {
                charSequence = charSequence.subSequence(0, 40);
            }
            try {
                this.f3849m.setText(charSequence);
                this.f3851o.setText(String.valueOf(i));
                this.f3851o.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
                this.f3850n.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void m6970a(ActionExpandableListView actionExpandableListView, MediaItem mediaItem, int i, boolean z) {
        int i2 = 0;
        this.f3847k.setText(String.valueOf(i + 1));
        this.f3848l.setText(mediaItem.getTitle());
        this.f3842f.setVisibility(View.GONE);
        this.f3843g.setVisibility(mediaItem.containMV() ? View.VISIBLE : View.GONE);
        if (this.f3843g != null) {
            this.f3843g.setVisibility(mediaItem.containMV() ? View.VISIBLE : View.GONE);
        }
        mo6972a(mediaItem);
        if (mediaItem.hasOutList()) {
            ArrayList<OnlineMediaItem.OutListItem> outList = mediaItem.getOutList();
            OnlineMediaItem.OutListItem outListItem = !ListUtils.m4718a(outList) ? outList.get(0) : null;
            IconTextView iconTextView = this.f3839c;
            if (outListItem == null || StringUtils.m8346a(outListItem.getUrl())) {
                i2 = 8;
            }
            iconTextView.setVisibility(i2);
        } else {
            this.f3839c.setVisibility(View.GONE);
            if (!z) {
                mediaItem.setFav(MediaItemUtils.m4715a(mediaItem));
            }
        }
        m3250a(ThemeUtils.m8163b());
        if (actionExpandableListView != null) {
            this.f3840d.setText(actionExpandableListView.mo1260e() == i ? R.string.icon_arrow_top : R.string.icon_arrow_down);
        }
    }

    /* renamed from: a */
    public void m6971a(MediaItem mediaItem, PlayStatus playStatus, boolean z) {
        m6967a(z, playStatus);
        if (mediaItem.hasOutList()) {
            this.f3849m.setText(mediaItem.getArtist());
        } else {
            m6969a((CharSequence) mediaItem.getArtist(), mediaItem.getUseCount().intValue(), true);
        }
        this.f3848l.setSelected(z);
        this.f3849m.setSelected(z);
    }

    /* renamed from: l */
    public IconTextView m6953l() {
        return this.f3841e;
    }

    /* renamed from: m */
    public IconTextView m6952m() {
        return this.f3846j;
    }

    /* renamed from: a */
    public void m6968a(Integer num) {
        if (num != null && num.intValue() != 0) {
            Drawable drawable = BaseApplication.getApplication().getResources().getDrawable(R.drawable.song_erro);
            int textSize = (int) this.f3848l.getTextSize();
            drawable.setBounds(0, 0, textSize, textSize);
            this.f3848l.setCompoundDrawables(drawable, null, null, null);
            return;
        }
        this.f3848l.setCompoundDrawables(null, null, null, null);
    }

    /* renamed from: a */
    public void m6967a(boolean z, PlayStatus playStatus) {
        this.f3848l.setSelected(z);
        this.f3838b.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
    }

    /* renamed from: b */
    public void m6964b(boolean z) {
        this.f3853q = z;
    }

    @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.AbstractC2018a
    /* renamed from: a */
    public void mo3251a() {
        if (this.f3853q) {
            m6950o();
        } else {
            m6951n();
        }
    }

    /* renamed from: n */
    private void m6951n() {
        if (this.f3841e != null) {
            ThemeUtils.m8176a(this.f3841e, R.string.icon_unchecked, R.string.icon_checked, "", "", ThemeElement.SONG_LIST_ITEM_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_TEXT);
        }
        ThemeUtils.m8175a(this.f3840d, (int) R.string.icon_arrow_down, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeManager.m3269a(this.f3837a, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        ThemeManager.m3269a(this.f3847k, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeManager.m3269a(this.f3848l, ThemeElement.SONG_LIST_ITEM_TEXT);
        ThemeManager.m3269a(this.f3849m, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeManager.m3269a(this.f3851o, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeManager.m3269a(this.f3838b, ThemeElement.SONG_LIST_ITEM_INDICATOR);
    }

    /* renamed from: o */
    private void m6950o() {
        if (this.f3841e != null) {
            ThemeUtils.m8176a(this.f3841e, R.string.icon_unchecked, R.string.icon_checked, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT, ThemeElement.PLAYER_MUSIC_LIST_TEXT, ThemeElement.SONG_LIST_ITEM_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_TEXT);
        }
        ThemeUtils.m8174a(this.f3840d, (int) R.string.icon_arrow_down, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeUtils.m8178a(this.f3837a, ThemeElement.PLAYER_MUSIC_LIST_BACKGROUND, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        ThemeUtils.m8178a(this.f3847k, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeUtils.m8178a(this.f3848l, ThemeElement.PLAYER_MUSIC_LIST_TEXT, ThemeElement.SONG_LIST_ITEM_TEXT);
        ThemeUtils.m8178a(this.f3849m, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeUtils.m8178a(this.f3851o, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeUtils.m8178a(this.f3838b, ThemeElement.PLAYER_MUSIC_LIST_INDICATOR, ThemeElement.SONG_LIST_ITEM_INDICATOR);
    }
}
