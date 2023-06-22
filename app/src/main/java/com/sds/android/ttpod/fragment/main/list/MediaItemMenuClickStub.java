package com.sds.android.ttpod.fragment.main.list;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p086c.DownloadMenuHandler;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.EditTextDialog;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.fragment.main.findsong.MvManager;
import com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack;
import com.sds.android.ttpod.framework.p106a.p107a.LocalStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.OnlineMediaStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.p106a.p107a.SUserUtils;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;

/* renamed from: com.sds.android.ttpod.fragment.main.list.d */
/* loaded from: classes.dex */
public abstract class MediaItemMenuClickStub implements View.OnClickListener {

    /* renamed from: a */
    private Activity f5421a;

    /* renamed from: b */
    private ActionExpandableListView f5422b;

    /* renamed from: c */
    private MediaItem f5423c;

    /* renamed from: d */
    private int f5424d;

    /* renamed from: a */
    protected abstract void mo5444a();

    /* renamed from: a */
    protected abstract void mo5441a(MediaItem mediaItem);

    /* renamed from: b */
    protected abstract void mo5440b(MediaItem mediaItem);

    public MediaItemMenuClickStub(Activity activity, ActionExpandableListView actionExpandableListView, MediaItem mediaItem, int i) {
        this.f5421a = activity;
        this.f5422b = actionExpandableListView;
        this.f5423c = mediaItem;
        this.f5424d = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ListViewUtils.m8264a(this.f5422b);
        SUserUtils.m4958a(this.f5424d);
        switch (view.getId()) {
            case R.id.media_menu_add /* 2131230745 */:
                PopupsUtils.m6729a(this.f5421a, Cache.m3218a().m3155k(), this.f5423c, (ActionItem.InterfaceC1135b) null, (BaseDialog.InterfaceC1064a<EditTextDialog>) null);
                LocalStatistic.m5144aJ();
                m5442a(SAction.ACTION_RIGHT_MENU_ADD_TO, SPage.PAGE_DIALOG_ADD_SONG);
                return;
            case R.id.media_menu_delete /* 2131230746 */:
                mo5440b(this.f5423c);
                m5442a(SAction.ACTION_RIGHT_MENU_DELETE, SPage.PAGE_DIALOG_DELETE);
                return;
            case R.id.media_menu_download /* 2131230747 */:
                new DownloadMenuHandler(this.f5421a).m6927a(this.f5423c, OnlineMediaStatistic.m5043b());
                return;
            case R.id.media_menu_favor /* 2131230748 */:
                mo5444a();
                return;
            case R.id.media_menu_favor_icon /* 2131230749 */:
            default:
                return;
            case R.id.media_menu_more /* 2131230750 */:
                mo5441a(this.f5423c);
                m5442a(SAction.ACTION_RIGHT_MENU_MORE, SPage.PAGE_DIALOG_MORE);
                return;
            case R.id.media_menu_mv /* 2131230751 */:
                final MediaItem mediaItem = this.f5423c;
                MvManager.m5557b(this.f5421a, new MvPopupDialogCallBack() { // from class: com.sds.android.ttpod.fragment.main.list.d.1
                    @Override // com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack
                    /* renamed from: a */
                    public void mo1219a() {
                        VideoPlayManager.m5816a(MediaItemMenuClickStub.this.f5421a, mediaItem);
                    }

                    @Override // com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack
                    /* renamed from: b */
                    public void mo1218b() {
                        MvManager.m5559a(mediaItem);
                    }
                }, 0);
                m5442a(SAction.ACTION_RIGHT_MENU_MV, SPage.PAGE_NONE);
                return;
            case R.id.media_menu_ring /* 2131230752 */:
                PopupsUtils.m6740a((Context) this.f5421a, this.f5423c);
                m5442a(SAction.ACTION_RIGHT_MENU_RING, SPage.PAGE_DIALOG_RING);
                return;
            case R.id.media_menu_share /* 2131230753 */:
                PopupsUtils.m6756a(this.f5421a, this.f5423c);
                m5442a(SAction.ACTION_RIGHT_MENU_SHARE, SPage.PAGE_DIALOG_SHARE);
                return;
        }
    }

    /* renamed from: a */
    private void m5442a(SAction sAction, SPage sPage) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", sAction.getValue(), SPage.PAGE_NONE.getValue(), sPage.getValue());
        if (this.f5423c.getSongID().longValue() == 0) {
            sUserEvent.append("song_id", this.f5423c.getLocalDataSource());
        } else {
            sUserEvent.append("song_id", this.f5423c.getSongID());
        }
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }
}
