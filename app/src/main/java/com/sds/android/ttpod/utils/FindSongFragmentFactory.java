package com.sds.android.ttpod.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import android.util.SparseArray;
import com.sds.android.cloudapi.ttpod.result.StyleDataListResult;
import com.sds.android.ttpod.fragment.main.findsong.FindSongBannerFragment;
import com.sds.android.ttpod.fragment.main.findsong.FindSongGridViewFragment;
import com.sds.android.ttpod.fragment.main.findsong.FindSongListViewFragment;
import com.sds.android.ttpod.fragment.main.findsong.FindSongPostFragment;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.a.k */
/* loaded from: classes.dex */
public class FindSongFragmentFactory {

    /* renamed from: a */
    private static final SparseArray<String> f2500a = new SparseArray<>();

    static {
        f2500a.put(0, FindSongPostFragment.class.getName());
        f2500a.put(1, FindSongBannerFragment.class.getName());
        f2500a.put(2, FindSongListViewFragment.class.getName());
        f2500a.put(3, FindSongGridViewFragment.class.getName());
        f2500a.put(4, FindSongGridViewFragment.class.getName());
        f2500a.put(5, FindSongGridViewFragment.class.getName());
        f2500a.put(6, FindSongGridViewFragment.class.getName());
    }

    /* renamed from: a */
    public static Fragment m8270a(Context context, StyleDataListResult styleDataListResult) {
        int style = styleDataListResult.getStyle();
        ArrayList dataList = styleDataListResult.getDataList();
        if (dataList == null || dataList.isEmpty() || style < 0 || style >= f2500a.size()) {
            return null;
        }
        Bundle bundle = new Bundle();
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(styleDataListResult);
        bundle.putParcelableArrayList("result", arrayList);
        return Fragment.instantiate(context, f2500a.get(style), bundle);
    }
}
