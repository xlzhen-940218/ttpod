package com.sds.android.ttpod.widget.mediamenu;

import android.app.Activity;
import android.view.View;
import com.sds.android.cloudapi.ttpod.data.MVOnlineData;
import com.sds.android.ttpod.fragment.main.findsong.MvManager;
import com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack;
import com.sds.android.ttpod.fragment.main.findsong.OnlineMVFragment;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;

/* renamed from: com.sds.android.ttpod.widget.mediamenu.b */
/* loaded from: classes.dex */
public class OnOnlineMVDownloadClickListener implements View.OnClickListener {

    /* renamed from: a */
    private Activity f8388a;

    /* renamed from: b */
    private MVOnlineData f8389b;

    /* renamed from: c */
    private ActionExpandableListView f8390c;

    public OnOnlineMVDownloadClickListener(Activity activity, MVOnlineData mVOnlineData, ActionExpandableListView actionExpandableListView) {
        this.f8388a = activity;
        this.f8389b = mVOnlineData;
        this.f8390c = actionExpandableListView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ListViewUtils.m8264a(this.f8390c);
        if (this.f8389b != null && this.f8388a != null) {
            //MVStatistic.m5070d();
            MvManager.m5557b(this.f8388a, new MvPopupDialogCallBack() { // from class: com.sds.android.ttpod.widget.mediamenu.b.1
                @Override // com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack
                /* renamed from: a */
                public void mo1219a() {
                    OnlineMVFragment.downloadMv(OnOnlineMVDownloadClickListener.this.f8388a, OnOnlineMVDownloadClickListener.this.f8389b);
                }

                @Override // com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack
                /* renamed from: b */
                public void mo1218b() {
                }
            }, 1);
        }
    }
}
