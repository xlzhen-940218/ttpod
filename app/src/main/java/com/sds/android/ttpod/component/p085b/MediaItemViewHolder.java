package com.sds.android.ttpod.component.p085b;

import android.annotation.SuppressLint;
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
    private View convertView;

    /* renamed from: b */
    private View viewPlayState;

    /* renamed from: c */
    private IconTextView thirdPartyView;

    /* renamed from: d */
    private CustomClickIconTextView menuView;

    /* renamed from: e */
    private IconTextView checkView;

    /* renamed from: f */
    private ImageView flagOnlineView;

    /* renamed from: g */
    private IconTextView flagMvView;

    /* renamed from: h */
    private IconTextView flagQualityView;

    /* renamed from: i */
    private IconTextView downloadStateView;

    /* renamed from: j */
    private IconTextView dragHandle;

    /* renamed from: k */
    private TextView numberTv;

    /* renamed from: l */
    private TextView titleView;

    /* renamed from: m */
    private TextView mediaItemSingerTv;

    /* renamed from: n */
    private ImageView mediaItemFavIv;

    /* renamed from: o */
    private TextView mediaItemFavCountTv;

    /* renamed from: p */
    private View expandable;

    /* renamed from: q */
    private boolean f3853q = false;

    public MediaItemViewHolder(View convertView) {
        this.convertView = convertView;
        this.viewPlayState = convertView.findViewById(R.id.view_play_state);
        this.menuView = (CustomClickIconTextView) convertView.findViewById(R.id.menu_view);
        this.checkView = (IconTextView) convertView.findViewById(R.id.check_view);
        this.flagOnlineView = (ImageView) convertView.findViewById(R.id.flag_online_view);
        this.flagMvView = (IconTextView) convertView.findViewById(R.id.flag_mv_view);
        this.flagQualityView = (IconTextView) convertView.findViewById(R.id.flag_quality_view);
        this.downloadStateView = (IconTextView) convertView.findViewById(R.id.downloadstate_view);
        this.dragHandle = (IconTextView) convertView.findViewById(R.id.drag_handle);
        this.numberTv = (TextView) convertView.findViewById(R.id.tv_number);
        this.titleView = (TextView) convertView.findViewById(R.id.title_view);
        this.mediaItemSingerTv = (TextView) convertView.findViewById(R.id.tv_media_item_singer);
        this.mediaItemFavIv = (ImageView) convertView.findViewById(R.id.iv_media_item_fav);
        this.mediaItemFavCountTv = (TextView) convertView.findViewById(R.id.tv_media_item_fav_count);
        this.thirdPartyView = (IconTextView) convertView.findViewById(R.id.third_party_view);
        if (this.checkView != null) {
            this.checkView.setCheckable(true);
        }
        this.expandable = convertView.findViewById(R.id.expandable);
        this.expandable.setTag(new MediaItemMenuHolder(this.expandable));
    }

    /* renamed from: b */
    public View getConvertView() {
        return this.convertView;
    }

    /* renamed from: c */
    public View getViewPlayState() {
        return this.viewPlayState;
    }

    /* renamed from: d */
    public CustomClickIconTextView getMenuView() {
        return this.menuView;
    }

    /* renamed from: e */
    public ImageView getFlagOnlineView() {
        return this.flagOnlineView;
    }

    /* renamed from: f */
    public IconTextView getFlagMvView() {
        return this.flagMvView;
    }

    /* renamed from: g */
    public IconTextView getFlagQualityView() {
        return this.flagQualityView;
    }

    /* renamed from: h */
    public View getExpandable() {
        return this.expandable;
    }

    /* renamed from: b */
    public void updateExpandable(MediaItem mediaItem) {
        ((MediaItemMenuHolder) this.expandable.getTag()).m6974b(mediaItem);
    }

    /* renamed from: a */
    public void updateFlagQuality(MediaItem mediaItem) {
        if (mediaItem != null) {
            AudioQuality quality = mediaItem.quality();
            if (quality.ordinal() >= AudioQuality.HIGH.ordinal()) {
                this.flagQualityView.setVisibility(View.VISIBLE);
                this.flagQualityView.setText(quality == AudioQuality.LOSSLESS ? R.string.icon_text_wusun : R.string.icon_text_gaozhi);
                this.flagQualityView.setTextColor(quality == AudioQuality.LOSSLESS ? -2185667 : -8665764);
                return;
            }
            this.flagQualityView.setVisibility(View.GONE);
        }
    }

    /* renamed from: c */
    public void m6962c(MediaItem mediaItem) {
        ArrayList<OnlineMediaItem.OutListItem> outList = mediaItem.getOutList();
        OnlineMediaItem.OutListItem outListItem = !ListUtils.m4718a(outList) ? outList.get(0) : null;
        if (mediaItem.hasOutList() && (outListItem == null || StringUtils.isEmpty(outListItem.getUrl()))) {
            ThemeManager.m3269a(this.titleView, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        } else {
            ThemeManager.m3269a(this.titleView, ThemeElement.SONG_LIST_ITEM_TEXT);
        }
    }

    /* renamed from: i */
    public IconTextView m6956i() {
        return this.downloadStateView;
    }

    /* renamed from: j */
    public TextView m6955j() {
        return this.titleView;
    }

    /* renamed from: k */
    public TextView m6954k() {
        return this.mediaItemSingerTv;
    }

    /* renamed from: a */
    public void m6969a(CharSequence charSequence, int i, boolean z) {
        if (charSequence != null) {
            if (charSequence.length() > 40) {
                charSequence = charSequence.subSequence(0, 40);
            }
            try {
                this.mediaItemSingerTv.setText(charSequence);
                this.mediaItemFavCountTv.setText(String.valueOf(i));
                this.mediaItemFavCountTv.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
                this.mediaItemFavIv.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    @SuppressLint("WrongConstant")
    public void m6970a(ActionExpandableListView actionExpandableListView, MediaItem mediaItem, int i, boolean z) {
        int i2 = 0;
        this.numberTv.setText(String.valueOf(i + 1));
        this.titleView.setText(mediaItem.getTitle());
        this.flagOnlineView.setVisibility(View.GONE);
        this.flagMvView.setVisibility(mediaItem.containMV() ? View.VISIBLE : View.GONE);
        if (this.flagMvView != null) {
            this.flagMvView.setVisibility(mediaItem.containMV() ? View.VISIBLE : View.GONE);
        }
        updateFlagQuality(mediaItem);
        if (mediaItem.hasOutList()) {
            ArrayList<OnlineMediaItem.OutListItem> outList = mediaItem.getOutList();
            OnlineMediaItem.OutListItem outListItem = !ListUtils.m4718a(outList) ? outList.get(0) : null;
            IconTextView iconTextView = this.thirdPartyView;
            if (outListItem == null || StringUtils.isEmpty(outListItem.getUrl())) {
                i2 = 8;
            }
            iconTextView.setVisibility(i2);
        } else {
            this.thirdPartyView.setVisibility(View.GONE);
            if (!z) {
                mediaItem.setFav(MediaItemUtils.m4715a(mediaItem));
            }
        }
        m3250a(ThemeUtils.m8163b());
        if (actionExpandableListView != null) {
            this.menuView.setText(actionExpandableListView.mo1260e() == i ? R.string.icon_arrow_top : R.string.icon_arrow_down);
        }
    }

    /* renamed from: a */
    public void m6971a(MediaItem mediaItem, PlayStatus playStatus, boolean z) {
        m6967a(z, playStatus);
        if (mediaItem.hasOutList()) {
            this.mediaItemSingerTv.setText(mediaItem.getArtist());
        } else {
            m6969a((CharSequence) mediaItem.getArtist(), mediaItem.getUseCount().intValue(), true);
        }
        this.titleView.setSelected(z);
        this.mediaItemSingerTv.setSelected(z);
    }

    /* renamed from: l */
    public IconTextView m6953l() {
        return this.checkView;
    }

    /* renamed from: m */
    public IconTextView m6952m() {
        return this.dragHandle;
    }

    /* renamed from: a */
    public void m6968a(Integer num) {
        if (num != null && num.intValue() != 0) {
            Drawable drawable = BaseApplication.getApplication().getResources().getDrawable(R.drawable.song_erro);
            int textSize = (int) this.titleView.getTextSize();
            drawable.setBounds(0, 0, textSize, textSize);
            this.titleView.setCompoundDrawables(drawable, null, null, null);
            return;
        }
        this.titleView.setCompoundDrawables(null, null, null, null);
    }

    /* renamed from: a */
    public void m6967a(boolean z, PlayStatus playStatus) {
        this.titleView.setSelected(z);
        this.viewPlayState.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
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
        if (this.checkView != null) {
            ThemeUtils.m8176a(this.checkView, R.string.icon_unchecked, R.string.icon_checked, "", "", ThemeElement.SONG_LIST_ITEM_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_TEXT);
        }
        ThemeUtils.m8175a(this.menuView, (int) R.string.icon_arrow_down, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeManager.m3269a(this.convertView, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        ThemeManager.m3269a(this.numberTv, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeManager.m3269a(this.titleView, ThemeElement.SONG_LIST_ITEM_TEXT);
        ThemeManager.m3269a(this.mediaItemSingerTv, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeManager.m3269a(this.mediaItemFavCountTv, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeManager.m3269a(this.viewPlayState, ThemeElement.SONG_LIST_ITEM_INDICATOR);
    }

    /* renamed from: o */
    private void m6950o() {
        if (this.checkView != null) {
            ThemeUtils.m8176a(this.checkView, R.string.icon_unchecked, R.string.icon_checked, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT, ThemeElement.PLAYER_MUSIC_LIST_TEXT, ThemeElement.SONG_LIST_ITEM_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_TEXT);
        }
        ThemeUtils.m8174a(this.menuView, (int) R.string.icon_arrow_down, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeUtils.m8178a(this.convertView, ThemeElement.PLAYER_MUSIC_LIST_BACKGROUND, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        ThemeUtils.m8178a(this.numberTv, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeUtils.m8178a(this.titleView, ThemeElement.PLAYER_MUSIC_LIST_TEXT, ThemeElement.SONG_LIST_ITEM_TEXT);
        ThemeUtils.m8178a(this.mediaItemSingerTv, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeUtils.m8178a(this.mediaItemFavCountTv, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeUtils.m8178a(this.viewPlayState, ThemeElement.PLAYER_MUSIC_LIST_INDICATOR, ThemeElement.SONG_LIST_ITEM_INDICATOR);
    }
}
