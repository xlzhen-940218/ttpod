package com.sds.android.ttpod.component.soundsearch;

import com.sds.android.ttpod.framework.modules.p122d.History;
import com.sds.android.ttpod.framework.modules.p122d.OnLoadListener;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.soundsearch.c */
/* loaded from: classes.dex */
public final class SoundSearchHistory extends History<SoundSearchInfo> {
    public SoundSearchHistory(OnLoadListener<List<SoundSearchInfo>> onLoadListener) {
        super("sound_search", 50, onLoadListener);
    }
}
