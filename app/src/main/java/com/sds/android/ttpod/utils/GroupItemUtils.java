package com.sds.android.ttpod.utils;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.sds.android.ttpod.a.l */
/* loaded from: classes.dex */
public class GroupItemUtils {
    /* renamed from: a */
    public static String m8269a() {
        boolean z;
        String string = BaseApplication.getApplication().getString(R.string.playlist);
        List<GroupItem> m3155k = Cache.getInstance().m3155k();
        int i = 1;
        while (true) {
            int i2 = i + 1;
            String str = string + i;
            Iterator<GroupItem> it = m3155k.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                } else if (it.next().getName().equals(str)) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                return str;
            }
            i = i2;
        }
    }

    /* renamed from: a */
    public static boolean m8268a(String str) {
        for (GroupItem groupItem : Cache.getInstance().m3155k()) {
            if (groupItem.getName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public static boolean m8267b(String str) {
        return (!str.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX) || MediaStorage.GROUP_ID_RECENTLY_PLAY.equals(str) || MediaStorage.GROUP_ID_RECENTLY_ADD.equals(str)) ? false : true;
    }
}
