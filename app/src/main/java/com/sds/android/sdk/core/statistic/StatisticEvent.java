package com.sds.android.sdk.core.statistic;

import android.os.Parcel;
import android.os.Parcelable;
import com.sds.android.sdk.lib.util.LogUtils;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class StatisticEvent implements Parcelable {
    public static final Parcelable.Creator<StatisticEvent> CREATOR = new Parcelable.Creator<StatisticEvent>() { // from class: com.sds.android.sdk.core.statistic.StatisticEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StatisticEvent createFromParcel(Parcel parcel) {
            Constructor<?>[] declaredConstructors;
            try {
                for (Constructor<?> constructor : Class.forName(parcel.readString()).getDeclaredConstructors()) {
                    Class<?>[] parameterTypes = constructor.getParameterTypes();
                    if (1 == parameterTypes.length && parameterTypes[0].equals(Parcel.class)) {
                        return (StatisticEvent) constructor.newInstance(parcel);
                    }
                }
            } catch (Exception e) {
                LogUtils.m8381c(StatisticEvent.TAG, "createFromParcel e = " + e.toString());
            }
            return null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StatisticEvent[] newArray(int i) {
            return new StatisticEvent[i];
        }
    };
    static final String KEY_CODE = "key";
    static final String KEY_CONTROL_CODE = "control_code";
    static final String KEY_ENUM_VALUE = "enum_result";
    static final String KEY_LONG_VALUE = "long_result";
    static final String KEY_MODULE = "module";
    static final String KEY_ORIGIN = "origin";
    static final String KEY_STR_VALUE = "str_result";
    static final String KEY_TYPE = "type";
    static final String KEY_VERSION = "v";
    private static final String TAG = "StatisticEvent";
    private int mControlCode;
    private HashMap<String, Object> mDataMap;

    public abstract boolean isCompleted();

    /* JADX INFO: Access modifiers changed from: protected */
    public StatisticEvent(int i, int i2) {
        this.mControlCode = 0;
        this.mDataMap = new HashMap<>();
        this.mControlCode = i2;
        this.mDataMap.put("key", Integer.valueOf(i));
    }

    public int getControlCode() {
        return this.mControlCode;
    }

    public void setControlCode(int i) {
        this.mControlCode = i;
    }

    public boolean isOldVersion() {
        return ((Integer) this.mDataMap.get("key")).intValue() == 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StatisticEvent(Parcel parcel) {
        this.mControlCode = 0;
        this.mDataMap = new HashMap<>();
        fromParcel(parcel);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StatisticEvent(JSONObject jSONObject) {
        this.mControlCode = 0;
        this.mDataMap = new HashMap<>();
        fromJsonObject(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fromJsonObject(JSONObject jSONObject) {
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (KEY_CONTROL_CODE.equals(next)) {
                this.mControlCode = jSONObject.optInt(KEY_CONTROL_CODE);
            } else if ("key".equals(next)) {
                this.mDataMap.put(next, Integer.valueOf(jSONObject.optInt("key")));
            } else {
                Object opt = jSONObject.opt(next);
                if (opt instanceof Integer) {
                    opt = Long.valueOf(((Integer) opt).longValue());
                }
                this.mDataMap.put(next, opt);
            }
        }
    }

    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(KEY_CONTROL_CODE, this.mControlCode);
        for (Map.Entry<String, Object> entry : this.mDataMap.entrySet()) {
            jSONObject.put(entry.getKey(), entry.getValue());
        }
        return jSONObject;
    }

    public HashMap<String, Object> getDateMap() {
        return this.mDataMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean combine(StatisticEvent statisticEvent) {
        return true;
    }

    public boolean equalData(StatisticEvent statisticEvent) {
        if (isOldVersion()) {
            return equalDataCompat(statisticEvent);
        }
        return this.mControlCode == statisticEvent.getControlCode() && this.mDataMap.get("key").equals(statisticEvent.getDateMap().get("key"));
    }

    protected boolean equalDataCompat(StatisticEvent statisticEvent) {
        Object obj;
        Object obj2;
        Object obj3;
        HashMap<String, Object> dateMap = statisticEvent.getDateMap();
        Object obj4 = this.mDataMap.get(KEY_MODULE);
        return obj4 != null && obj4.equals(dateMap.get(KEY_MODULE)) && (obj = this.mDataMap.get("type")) != null && obj.equals(dateMap.get("type")) && (obj2 = this.mDataMap.get(KEY_ORIGIN)) != null && obj2.equals(dateMap.get(KEY_ORIGIN)) && (obj3 = this.mDataMap.get(KEY_VERSION)) != null && obj3.equals(dateMap.get(KEY_VERSION));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("key").append(" = ").append(this.mDataMap.get("key")).append(",");
        for (Map.Entry<String, Object> entry : this.mDataMap.entrySet()) {
            sb.append(entry.getKey()).append(" = ").append(entry.getValue()).append(", ");
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fromParcel(Parcel parcel) {
        this.mControlCode = parcel.readInt();
        this.mDataMap = parcel.readHashMap(HashMap.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getClass().getName());
        parcel.writeInt(this.mControlCode);
        parcel.writeMap(this.mDataMap);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
