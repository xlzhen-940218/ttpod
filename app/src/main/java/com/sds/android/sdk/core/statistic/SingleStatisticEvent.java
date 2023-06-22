package com.sds.android.sdk.core.statistic;

import android.os.Parcel;
import com.sds.android.sdk.lib.util.DateUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SingleStatisticEvent extends StatisticEvent {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final String KEY_OPT_MESSAGE = "optmessage";
    private static final String KEY_OPT_MESSAGE2 = "optmessage2";
    private static final String KEY_OPT_VALUE = "optvalue";
    private static final String KEY_OPT_VALUE2 = "optvalue2";
    private static final String KEY_TIME = "time";
    private static final String KEY_VALUE = "value";
    private static final String TAG = "SingleStatisticEvent";

    static {
        $assertionsDisabled = !SingleStatisticEvent.class.desiredAssertionStatus();
    }

    private SingleStatisticEvent(String str, String str2, String str3, int i, int i2, int i3) {
        this(0, StatisticHelper.DELAY_SEND);
        HashMap<String, Object> dateMap = getDateMap();
        dateMap.put("module", str);
        dateMap.put("type", str2);
        dateMap.put("origin", str3);
        dateMap.put("v", Long.valueOf(Integer.valueOf(i2).longValue()));
        dateMap.put("value", Long.valueOf(Integer.valueOf(i).longValue()));
        dateMap.put(KEY_TIME, DateUtils.m8432a(i3));
    }

    public static SingleStatisticEvent instance(String str, String str2, String str3, int i, int i2, int i3) {
        if ($assertionsDisabled || !(str == null || str2 == null || str3 == null || i <= 0)) {
            return new SingleStatisticEvent(str, str2, str3, i, i2, i3);
        }
        throw new AssertionError();
    }

    public void setOptValue(long j) {
        getDateMap().put(KEY_OPT_VALUE, Long.valueOf(j));
    }

    public void setOptValue2(long j) {
        getDateMap().put(KEY_OPT_VALUE2, Long.valueOf(j));
    }

    public void setOptMessage(String str) {
        getDateMap().put(KEY_OPT_MESSAGE, str);
    }

    public void setOptMessage2(String str) {
        getDateMap().put(KEY_OPT_MESSAGE2, str);
    }

    public SingleStatisticEvent(int i, int i2) {
        super(i, i2);
    }

    public SingleStatisticEvent(Parcel parcel) {
        super(parcel);
    }

    public SingleStatisticEvent(JSONObject jSONObject) {
        super(jSONObject);
    }

    @Override // com.sds.android.sdk.core.statistic.StatisticEvent
    public boolean equalData(StatisticEvent statisticEvent) {
        boolean equals;
        if (this != statisticEvent && super.equalData(statisticEvent) && getClass() == statisticEvent.getClass()) {
            if (isOldVersion()) {
                return equalDataVersionCompat(statisticEvent);
            }
            HashMap<String, Object> dateMap = getDateMap();
            if (dateMap.size() == statisticEvent.getDateMap().size()) {
                boolean z = false;
                for (Map.Entry<String, Object> entry : dateMap.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        equals = StringUtils.m8344a((String) value, (String) statisticEvent.getDateMap().get(key));
                    } else {
                        equals = ((value instanceof Integer) || (value instanceof Long)) ? value.equals(statisticEvent.getDateMap().get(key)) : z;
                    }
                    if (!equals) {
                        return false;
                    }
                    z = equals;
                }
                return z;
            }
            return false;
        }
        return false;
    }

    private boolean equalDataVersionCompat(StatisticEvent statisticEvent) {
        HashMap<String, Object> dateMap = getDateMap();
        if (dateMap.get(KEY_TIME).equals(statisticEvent.getDateMap().get(KEY_TIME)) && dateMap.get(KEY_OPT_VALUE).equals(statisticEvent.getDateMap().get(KEY_OPT_VALUE)) && dateMap.get(KEY_OPT_VALUE2).equals(statisticEvent.getDateMap().get(KEY_OPT_VALUE2)) && StringUtils.m8344a((String) dateMap.get(KEY_OPT_MESSAGE), (String) statisticEvent.getDateMap().get(KEY_OPT_MESSAGE))) {
            return StringUtils.m8344a((String) dateMap.get(KEY_OPT_MESSAGE2), (String) statisticEvent.getDateMap().get(KEY_OPT_MESSAGE2));
        }
        return false;
    }

    @Override // com.sds.android.sdk.core.statistic.StatisticEvent
    public boolean combine(StatisticEvent statisticEvent) {
        if (isOldVersion() || $assertionsDisabled || !equalData(statisticEvent)) {
            super.combine(statisticEvent);
            if (isOldVersion()) {
                combineVersionCompat(statisticEvent);
                return true;
            }
            return true;
        }
        throw new AssertionError("data not equal when combine");
    }

    private void combineVersionCompat(StatisticEvent statisticEvent) {
        getDateMap().put("value", Long.valueOf(((Long) getDateMap().get("value")).longValue() + ((Long) statisticEvent.getDateMap().get("value")).longValue()));
    }

    public static SingleStatisticEvent instance(int i, int i2) {
        if ($assertionsDisabled || StatisticHelper.isKVByKeyCode(i)) {
            return new SingleStatisticEvent(i, i2);
        }
        throw new AssertionError("keyCode is not compatible for SingleStatisticEvent");
    }

    @Override // com.sds.android.sdk.core.statistic.StatisticEvent
    public boolean isCompleted() {
        return true;
    }

    public void put(String str, String str2) {
        put(str, (Object) str2);
    }

    public void put(String str, long j) {
        put(str, Long.valueOf(j));
    }

    private void put(String str, Object obj) {
        getDateMap().put(str, obj);
    }
}
