package com.sds.android.ttpod.framework.modules.p125g;

import com.sds.android.cloudapi.ttpod.data.SplashItem;
import com.sds.android.cloudapi.ttpod.result.SplashDataResult;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* renamed from: com.sds.android.ttpod.framework.modules.g.c */
/* loaded from: classes.dex */
final class SplashInfoParser {

    /* renamed from: a */
    private SplashDataResult f6285a;

    private SplashInfoParser(SplashDataResult splashDataResult) {
        this.f6285a = splashDataResult;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static SplashInfoParser m4014a(SplashDataResult splashDataResult) {
        if (splashDataResult == null) {
            return null;
        }
        return new SplashInfoParser(splashDataResult);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int m4015a() {
        if (this.f6285a != null) {
            return this.f6285a.getVersion();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public SplashItem m4013b() {
        List<SplashItem> m4012c = m4012c();
        if (m4012c != null) {
            int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            for (SplashItem splashItem : m4012c) {
                if (splashItem.isContain(seconds)) {
                    return splashItem;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public List<SplashItem> m4012c() {
        if (this.f6285a == null || !this.f6285a.isSuccess() || this.f6285a.isDataListEmpty()) {
            return null;
        }
        int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        ArrayList<SplashItem> dataList = this.f6285a.getDataList();
        ArrayList arrayList = new ArrayList();
        for (SplashItem splashItem : dataList) {
            if (splashItem.isValid(seconds)) {
                arrayList.add(splashItem);
            }
        }
        return arrayList;
    }
}
