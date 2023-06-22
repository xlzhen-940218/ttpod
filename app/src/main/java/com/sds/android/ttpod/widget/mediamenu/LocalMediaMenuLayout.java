package com.sds.android.ttpod.widget.mediamenu;

import android.content.Context;
import android.util.AttributeSet;
import com.sds.android.ttpod.R;
import java.util.List;

/* loaded from: classes.dex */
public class LocalMediaMenuLayout extends OnlineMediaMenuLayout {
    public LocalMediaMenuLayout(Context context) {
        super(context);
    }

    public LocalMediaMenuLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.sds.android.ttpod.widget.mediamenu.OnlineMediaMenuLayout, com.sds.android.ttpod.widget.mediamenu.MediaMenuLayout
    /* renamed from: a */
    public List<MenuItem> mo1226a() {
        List<MenuItem> mo1226a = super.mo1226a();
        mo1226a.add(new MenuItem(R.id.media_menu_delete, R.string.icon_media_menu_delete, R.string.delete));
        mo1226a.add(new MenuItem(R.id.media_menu_add, R.string.icon_media_menu_add, R.string.add));
        mo1226a.add(new MenuItem(R.id.media_menu_ring, R.string.icon_media_menu_ring, R.string.ringtone));
        mo1226a.add(new MenuItem(R.id.media_menu_more, R.string.icon_media_menu_more, R.string.media_item_menu_more));
        return mo1226a;
    }
}
