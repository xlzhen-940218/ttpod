package com.sds.android.ttpod.fragment.skinmanager.base;

import com.sds.android.ttpod.framework.modules.skin.SkinItem;

/* renamed from: com.sds.android.ttpod.fragment.skinmanager.base.b */
/* loaded from: classes.dex */
public interface SkinOperateListener {
    void onCurrentSkinChanged(String str);

    void onSkinDeleted(SkinItem skinItem);

    void onSkinDownloadError(SkinItem skinItem);

    void onSkinDownloaded(SkinItem skinItem);

    void onSkinDownloading(SkinItem skinItem);

    void onSkinInfoLoaded();

    void onSkinItemStateChange(String str, int i);
}
