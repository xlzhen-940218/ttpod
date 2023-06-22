package com.sds.android.sdk.core.statistic;

import java.util.HashMap;

/* loaded from: classes.dex */
public class SPageProperties {
    private HashMap<String, Object> mProperties = new HashMap<>();

    public SPageProperties append(String str, Object obj) {
        this.mProperties.put(str, obj);
        return this;
    }

    public HashMap<String, Object> getProPerties() {
        return this.mProperties;
    }
}
