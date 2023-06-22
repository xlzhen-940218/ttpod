package com.sds.android.ttpod.fragment.skinmanager.base;

import com.sds.android.ttpod.framework.modules.skin.SkinItem;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.fragment.skinmanager.base.c */
/* loaded from: classes.dex */
public class ThemeListObserver {

    /* renamed from: a */
    private static ThemeListObserver f5558a;

    /* renamed from: b */
    private static ArrayList<SkinOperateListener> f5559b = new ArrayList<>();

    /* renamed from: a */
    public static synchronized ThemeListObserver m5322a() {
        ThemeListObserver themeListObserver;
        synchronized (ThemeListObserver.class) {
            if (f5558a == null) {
                f5558a = new ThemeListObserver();
            }
            themeListObserver = f5558a;
        }
        return themeListObserver;
    }

    /* renamed from: a */
    public void m5320a(SkinItem skinItem) {
        Iterator<SkinOperateListener> it = f5559b.iterator();
        while (it.hasNext()) {
            SkinOperateListener next = it.next();
            if (next != null) {
                next.onSkinDownloaded(skinItem);
            }
        }
    }

    /* renamed from: b */
    public void m5315b(SkinItem skinItem) {
        Iterator<SkinOperateListener> it = f5559b.iterator();
        while (it.hasNext()) {
            SkinOperateListener next = it.next();
            if (next != null) {
                next.onSkinDownloadError(skinItem);
            }
        }
    }

    /* renamed from: c */
    public void m5314c(SkinItem skinItem) {
        Iterator<SkinOperateListener> it = f5559b.iterator();
        while (it.hasNext()) {
            SkinOperateListener next = it.next();
            if (next != null) {
                next.onSkinDownloading(skinItem);
            }
        }
    }

    /* renamed from: d */
    public void m5313d(SkinItem skinItem) {
        Iterator<SkinOperateListener> it = f5559b.iterator();
        while (it.hasNext()) {
            SkinOperateListener next = it.next();
            if (next != null) {
                next.onSkinDeleted(skinItem);
            }
        }
    }

    /* renamed from: a */
    public void m5319a(String str) {
        Iterator<SkinOperateListener> it = f5559b.iterator();
        while (it.hasNext()) {
            SkinOperateListener next = it.next();
            if (next != null) {
                next.onCurrentSkinChanged(str);
            }
        }
    }

    /* renamed from: b */
    public void m5317b() {
        Iterator<SkinOperateListener> it = f5559b.iterator();
        while (it.hasNext()) {
            SkinOperateListener next = it.next();
            if (next != null) {
                next.onSkinInfoLoaded();
            }
        }
    }

    /* renamed from: a */
    public void m5318a(String str, int i) {
        Iterator<SkinOperateListener> it = f5559b.iterator();
        while (it.hasNext()) {
            SkinOperateListener next = it.next();
            if (next != null) {
                next.onSkinItemStateChange(str, i);
            }
        }
    }

    /* renamed from: a */
    public void m5321a(SkinOperateListener skinOperateListener) {
        if (!f5559b.contains(skinOperateListener)) {
            f5559b.add(skinOperateListener);
        }
    }

    /* renamed from: b */
    public void m5316b(SkinOperateListener skinOperateListener) {
        f5559b.remove(skinOperateListener);
    }
}
