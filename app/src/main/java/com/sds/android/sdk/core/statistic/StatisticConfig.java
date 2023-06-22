package com.sds.android.sdk.core.statistic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class StatisticConfig {
    private HashMap<Integer, Integer> mControlCodeMap = new HashMap<>();

    public void fromJsonObject(JSONObject jSONObject) {
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            this.mControlCodeMap.put(Integer.valueOf(next), Integer.valueOf(jSONObject.optInt(next)));
        }
    }

    public void toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<Integer, Integer> entry : this.mControlCodeMap.entrySet()) {
            jSONObject.put(entry.getKey().toString(), entry.getValue());
        }
    }

    public HashMap<Integer, Integer> getControlCodeMap() {
        return this.mControlCodeMap;
    }
}
