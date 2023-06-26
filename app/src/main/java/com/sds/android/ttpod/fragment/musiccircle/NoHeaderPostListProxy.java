package com.sds.android.ttpod.fragment.musiccircle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;

/* renamed from: com.sds.android.ttpod.fragment.musiccircle.a */
/* loaded from: classes.dex */
public class NoHeaderPostListProxy {

    /* renamed from: a */
    private DragUpdateListView f5455a;

    /* renamed from: b */
    private StateView f5456b;

    /* renamed from: c */
    private View f5457c;

    /* renamed from: d */
    private View.OnClickListener f5458d;

    /* renamed from: e */
    private DragUpdateHelper.InterfaceC2273c f5459e;

    public NoHeaderPostListProxy(View.OnClickListener onClickListener, DragUpdateHelper.InterfaceC2273c interfaceC2273c) {
        this.f5458d = onClickListener;
        this.f5459e = interfaceC2273c;
    }

    /* renamed from: a */
    public View m5426a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_no_header_post_list_layout, viewGroup, false);
        this.f5456b = (StateView) inflate.findViewById(R.id.discovery_loadingview);
        this.f5455a = (DragUpdateListView) this.f5456b.findViewById(R.id.musiccircle_dragupdate_listview);
        this.f5455a.setOnStartRefreshListener(this.f5459e);
        this.f5457c = this.f5456b.findViewById(R.id.loadingview_failed);
        this.f5457c.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.musiccircle.a.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NoHeaderPostListProxy.this.f5456b.setState(StateView.EnumC2248b.LOADING);
                if (EnvironmentUtils.DeviceConfig.isConnected()) {
                    if (NoHeaderPostListProxy.this.f5458d != null) {
                        NoHeaderPostListProxy.this.f5458d.onClick(view);
                        return;
                    }
                    return;
                }
                NoHeaderPostListProxy.this.f5456b.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.fragment.musiccircle.a.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        NoHeaderPostListProxy.this.m5424a(StateView.EnumC2248b.FAILED);
                        PopupsUtils.m6760a((int) R.string.network_error);
                    }
                }, 200L);
            }
        });
        return inflate;
    }

    /* renamed from: a */
    public ListView m5427a() {
        return this.f5455a;
    }

    /* renamed from: a */
    public void m5424a(StateView.EnumC2248b enumC2248b) {
        if (this.f5456b != null) {
            this.f5456b.setState(enumC2248b);
        }
    }

    /* renamed from: b */
    public StateView m5423b() {
        return this.f5456b;
    }

    /* renamed from: c */
    public void m5421c() {
        if (this.f5455a != null) {
            this.f5455a.m1336b();
        }
    }
}
