package com.sds.android.sdk.core.statistic;

import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes.dex */
public interface IStrategy {
    LinkedList<SEvent> getLastCacheEventList();

    void onAddEvent(SEvent sEvent);

    void onCreate();

    void onDestroy();

    void setGeneralParameters(Map map);

    void setURL(String str);
}
