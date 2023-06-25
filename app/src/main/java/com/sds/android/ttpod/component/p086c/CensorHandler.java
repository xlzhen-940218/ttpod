package com.sds.android.ttpod.component.p086c;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;

import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.web.WebActivity;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.p088a.NoCopyrightDialog;
import com.sds.android.ttpod.fragment.WebFragment;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.c.a */
/* loaded from: classes.dex */
public class CensorHandler {

    /* renamed from: a */
    private Context f3854a;

    /* renamed from: b */
    private MediaItem f3855b;

    public CensorHandler(Context context, MediaItem mediaItem) {
        if (context == null || mediaItem == null) {
            throw new IllegalArgumentException("Context or MediaItem can NOT be null!!");
        }
        this.f3854a = context;
        this.f3855b = mediaItem;
    }

    /* renamed from: a */
    public void m6949a() {
        List<ActionItem> m6946a = m6946a(this.f3855b);
        switch (this.f3855b.getCensorLevel()) {
            case 1:
                m6942d();
                return;
            case 2:
            default:
                m6945a(m6946a);
                return;
            case 3:
                m6943c();
                return;
            case 4:
                m6944b();
                return;
        }
    }

    /* renamed from: b */
    private void m6944b() {
    }

    /* renamed from: c */
    private void m6943c() {
        ArrayList<OnlineMediaItem.OutListItem> outList = this.f3855b.getOutList();
        if (!ListUtils.m4718a(outList)) {
            m6948a(outList.get(0));
        }
    }

    /* renamed from: a */
    private void m6945a(List<ActionItem> list) {
        final NoCopyrightDialog noCopyrightDialog = new NoCopyrightDialog(this.f3854a, list, this.f3855b);
        noCopyrightDialog.m6844a(new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.component.c.a.1
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(ActionItem actionItem, int i) {
                CensorHandler.this.m6948a((OnlineMediaItem.OutListItem) actionItem.m7004f());
                noCopyrightDialog.dismiss();
            }
        });
        noCopyrightDialog.show();
    }

    /* renamed from: d */
    private void m6942d() {
        new NoCopyrightDialog(this.f3854a, new ArrayList(), this.f3855b).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m6948a(OnlineMediaItem.OutListItem outListItem) {
        Intent intent = new Intent(this.f3854a, WebActivity.class);
        intent.setData(Uri.parse(outListItem.getUrl()));
        intent.putExtra(WebFragment.EXTRA_TITLE, outListItem.getName());
        intent.putExtra(WebFragment.EXTRA_ENABLE_SLIDING_CLOSABLE, false);
        this.f3854a.startActivity(intent);
        //SearchStatistic.m4941b(this.f3855b);
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_OUT_LIST.getValue(), String.valueOf(SPage.PAGE_DIALOG_COPYRIGHT.getValue()), outListItem.getName()).post();
    }

    /* renamed from: a */
    private List<ActionItem> m6946a(MediaItem mediaItem) {
        ArrayList<OnlineMediaItem.OutListItem> outList = mediaItem.getOutList();
        if (ListUtils.m4718a(outList)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= outList.size()) {
                return arrayList;
            }
            OnlineMediaItem.OutListItem outListItem = outList.get(i2);
            if (!StringUtils.isEmpty(outListItem.getUrl())) {
                ActionItem actionItem = new ActionItem(i2, (int) R.drawable.img_setting_right_arrow, outListItem.getName());
                actionItem.m7011a(ActionItem.EnumC1134a.RIGHT_ICON);
                actionItem.m7009a(outListItem);
                arrayList.add(actionItem);
            }
            i = i2 + 1;
        }
    }
}
