package com.sds.android.ttpod.component.p085b;

import android.graphics.Color;
import android.view.View;


import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.utils.FavoriteUtils;
import com.sds.android.ttpod.widget.mediamenu.MediaMenuLayout;

/* renamed from: com.sds.android.ttpod.component.b.f */
/* loaded from: classes.dex */
public class MediaItemMenuHolder {

    /* renamed from: a */
    private InterfaceC1136a f3835a;

    /* renamed from: b */
    private MediaMenuLayout f3836b;

    /* compiled from: MediaItemMenuHolder.java */
    /* renamed from: com.sds.android.ttpod.component.b.f$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1136a {
        /* renamed from: a */
        void mo6973a(MediaItem mediaItem, boolean z);
    }

    public MediaItemMenuHolder(View view) {
        this.f3836b = null;
        if (view instanceof MediaMenuLayout) {
            this.f3836b = (MediaMenuLayout) view;
        }
    }

    /* renamed from: a */
    public MediaMenuLayout m6981a() {
        return this.f3836b;
    }

    /* renamed from: a */
    public void m6980a(View.OnClickListener onClickListener) {
        if (this.f3836b != null && this.f3836b.getVisibility() == View.VISIBLE) {
            this.f3836b.setMenuItemClickListener(onClickListener);
        }
    }

    /* renamed from: a */
    public void m6977a(MediaItem mediaItem) {
        boolean z = true;
        boolean hasOutList = mediaItem.hasOutList();
        boolean z2 = !StringUtils.isEmpty(mediaItem.getLocalDataSource());
        this.f3836b.m1230a(R.id.media_menu_favor, !hasOutList);
        if (this.f3836b != null && this.f3836b.getVisibility() == View.VISIBLE) {
            this.f3836b.m1230a(R.id.media_menu_share, !hasOutList);
            MediaMenuLayout mediaMenuLayout = this.f3836b;
            if (!mediaItem.containMV() || z2) {
                z = false;
            }
            mediaMenuLayout.m1230a(R.id.media_menu_mv, z);
        }
        m6975a(mediaItem.getFav());
    }

    /* renamed from: b */
    public void m6974b(MediaItem mediaItem) {
        boolean z = true;
        boolean z2 = !StringUtils.isEmpty(mediaItem.getLocalDataSource());
        if (this.f3836b != null && this.f3836b.getVisibility() == View.VISIBLE) {
            this.f3836b.m1230a(R.id.media_menu_delete, z2);
            this.f3836b.m1230a(R.id.media_menu_add, z2);
            this.f3836b.m1230a(R.id.media_menu_ring, z2);
            this.f3836b.m1230a(R.id.media_menu_more, z2);
            this.f3836b.m1230a(R.id.media_menu_download, !z2);
            this.f3836b.m1230a(R.id.media_menu_share, !z2);
            MediaMenuLayout mediaMenuLayout = this.f3836b;
            if (!mediaItem.containMV() || z2) {
                z = false;
            }
            mediaMenuLayout.m1230a(R.id.media_menu_mv, z);
        }
        m6975a(mediaItem.getFav());
    }

    /* renamed from: a */
    private void m6975a(boolean z) {
        IconTextView iconTextView;
        if (this.f3836b != null && this.f3836b.getVisibility() == View.VISIBLE && (iconTextView = (IconTextView) this.f3836b.m1232a(R.id.media_menu_favor_icon)) != null) {
            iconTextView.setChecked(z);
            iconTextView.setTextColor(z ? Color.parseColor("#FF617C") : Color.parseColor("#FFFFFF"));
        }
    }

    /* renamed from: a */
    public void m6976a(MediaItem mediaItem, int i) {
        if (!mediaItem.isThirdParty()) {
            boolean fav = mediaItem.getFav();
            if (mediaItem.isOnline() && Preferences.m2954aq() == null) {
                m6975a(false);
                EntryUtils.m8297a(true);
                //m6978a(SPage.PAGE_CIRCLE_LOGIN, mediaItem, false);
                return;
            }
            mediaItem.setFav(!fav);
            if (!fav) {
                FavoriteUtils.m8283a(mediaItem, true);
            } else {
                FavoriteUtils.m8282b(mediaItem, false);
            }
            //OnlineMediaStatistic.m5053a(i + 1);
            if (this.f3835a != null) {
                this.f3835a.mo6973a(mediaItem, !fav);
            }
            m6975a(mediaItem.getFav());
            //m6978a(SPage.PAGE_NONE, mediaItem, fav ? false : true);
            return;
        }
        m6975a(false);
        PopupsUtils.m6721a("第三方无法歌曲不能收藏！");
    }

    /* renamed from: a */


    /* renamed from: a */
    public void m6979a(InterfaceC1136a interfaceC1136a) {
        this.f3835a = interfaceC1136a;
    }
}
