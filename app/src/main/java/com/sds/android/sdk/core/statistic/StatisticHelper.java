package com.sds.android.sdk.core.statistic;

import android.os.Parcelable;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class StatisticHelper {
    public static final int DELAY_SEND = 65537;
    private static final String KEY_EVENT_TYPE = "eventType";
    private static final int MASK_CACHE_CODE = -65536;
    private static final int MASK_KV_KEY_CODE = 65535;
    private static final int MASK_SAMPLE_CODE = 65535;
    private static final int SHIFT_CACHE_CODE = 16;

    public static List<StatisticEvent> loadEventList(String str) {
        Parcelable kVStatisticEvent = null;
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(FileUtils.m8403i(str));
            int length = jSONArray.length();
            String simpleName = SingleStatisticEvent.class.getSimpleName();
            String simpleName2 = SessionStatisticEvent.class.getSimpleName();
            String simpleName3 = KVStatisticEvent.class.getSimpleName();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString(KEY_EVENT_TYPE);
                jSONObject.remove(KEY_EVENT_TYPE);
                if (simpleName.equals(string)) {
                    kVStatisticEvent = new SingleStatisticEvent(jSONObject);
                } else if (simpleName2.equals(string)) {
                    kVStatisticEvent = new SessionStatisticEvent(jSONObject);
                    ((SessionStatisticEvent) kVStatisticEvent).complete();
                } else if (simpleName3.equals(string)) {
                    kVStatisticEvent = new KVStatisticEvent(jSONObject);
                }
                arrayList.add(kVStatisticEvent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static JSONArray transJSONArray(List<StatisticEvent> list) {
        JSONArray jSONArray = new JSONArray();
        try {
            for (StatisticEvent statisticEvent : list) {
                if (statisticEvent.isCompleted()) {
                    JSONObject jsonObject = statisticEvent.toJsonObject();
                    jsonObject.put(KEY_EVENT_TYPE, statisticEvent.getClass().getSimpleName());
                    jSONArray.put(jsonObject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONArray;
    }

    public static boolean isSampleByCode(int i) {
        String str = (String) EnvironmentUtils.C0603b.m8488e().get("uid");
        if (i == 0) {
            return false;
        }
        if (1 == i) {
            return true;
        }
        return 1 == str.hashCode() % i;
    }

    protected static int getSampleCode(int i) {
        return 65535 & i;
    }

    protected static int getCacheCode(int i) {
        return ((-65536) & i) >> 16;
    }

    /* loaded from: classes.dex */
    public enum SendMode {
        IMMEDIATE,
        DELAY,
        DROP;

        public static SendMode getSendMode(StatisticEvent statisticEvent) {
            int sampleCode = StatisticHelper.getSampleCode(statisticEvent.getControlCode());
            SendMode sendMode = DROP;
            if (StatisticHelper.isSampleByCode(sampleCode)) {
                int cacheCode = StatisticHelper.getCacheCode(statisticEvent.getControlCode());
                if (cacheCode == 0 && statisticEvent.isCompleted()) {
                    return IMMEDIATE;
                }
                if (1 == cacheCode || (cacheCode == 0 && !statisticEvent.isCompleted())) {
                    return DELAY;
                }
                return sendMode;
            }
            return sendMode;
        }
    }

    public static boolean isKVByKeyCode(int i) {
        return i == 0 || (65535 & i) != 0;
    }

    public static boolean equalData(StatisticEvent statisticEvent, StatisticEvent statisticEvent2) {
        return statisticEvent == statisticEvent2 || statisticEvent.equalData(statisticEvent2);
    }
}
