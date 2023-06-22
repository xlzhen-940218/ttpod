package com.sds.android.ttpod.widget.mediamenu;

import android.content.Context;
import android.util.AttributeSet;
import com.sds.android.ttpod.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class OnlineMVMenuLayout extends MediaMenuLayout {
    public OnlineMVMenuLayout(Context context) {
        super(context);
    }

    public OnlineMVMenuLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.sds.android.ttpod.widget.mediamenu.MediaMenuLayout
    /* renamed from: a */
    public List<MenuItem> mo1226a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MenuItem(R.id.media_menu_download, R.string.icon_media_menu_download, R.string.media_item_menu_download));
        return arrayList;
    }
}
