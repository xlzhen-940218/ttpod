package com.sds.android.sdk.core.statistic;

import android.os.Parcel;
import java.util.HashMap;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SessionStatisticEvent extends StatisticEvent {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final String KEY_STAMP = "stamp";
    private static final String TAG = "SessionStatisticEvent";
    private int mCompleteID;
    private long mStamp;

    static {
        $assertionsDisabled = !SessionStatisticEvent.class.desiredAssertionStatus();
    }

    private SessionStatisticEvent(String str, String str2, String str3, int i, long j) {
        this(0, StatisticHelper.DELAY_SEND, j);
        HashMap<String, Object> dateMap = getDateMap();
        dateMap.put("module", str);
        dateMap.put("type", str2);
        dateMap.put("origin", str3);
        dateMap.put("v", Integer.valueOf(i));
    }

    private SessionStatisticEvent(int i, int i2, long j) {
        super(i, i2);
        this.mStamp = j;
    }

    public SessionStatisticEvent(Parcel parcel) {
        super(parcel);
    }

    public SessionStatisticEvent(JSONObject jSONObject) {
        super(jSONObject);
    }

    @Override // com.sds.android.sdk.core.statistic.StatisticEvent
    public boolean equalData(StatisticEvent statisticEvent) {
        if (this != statisticEvent && super.equalData(statisticEvent) && getClass() == statisticEvent.getClass()) {
            SessionStatisticEvent sessionStatisticEvent = (SessionStatisticEvent) statisticEvent;
            if (this.mStamp == sessionStatisticEvent.getStamp()) {
                return this.mCompleteID == 0 || this.mCompleteID == sessionStatisticEvent.mCompleteID;
            }
            return false;
        }
        return false;
    }

    protected long getStamp() {
        return this.mStamp;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.sdk.core.statistic.StatisticEvent
    public void fromJsonObject(JSONObject jSONObject) {
        super.fromJsonObject(jSONObject);
        this.mStamp = ((Long) getDateMap().get(KEY_STAMP)).longValue();
        getDateMap().remove(KEY_STAMP);
        complete();
    }

    @Override // com.sds.android.sdk.core.statistic.StatisticEvent
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        jsonObject.put(KEY_STAMP, Long.valueOf(this.mStamp));
        return jsonObject;
    }

    @Override // com.sds.android.sdk.core.statistic.StatisticEvent, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mCompleteID);
        parcel.writeLong(this.mStamp);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.sdk.core.statistic.StatisticEvent
    public void fromParcel(Parcel parcel) {
        super.fromParcel(parcel);
        this.mCompleteID = parcel.readInt();
        this.mStamp = parcel.readLong();
    }

    @Override // com.sds.android.sdk.core.statistic.StatisticEvent
    public boolean isCompleted() {
        return this.mCompleteID != 0;
    }

    @Override // com.sds.android.sdk.core.statistic.StatisticEvent
    public boolean combine(StatisticEvent statisticEvent) {
        if (isOldVersion() && !$assertionsDisabled && equalData(statisticEvent)) {
            throw new AssertionError("data not equal when combine");
        }
        super.combine(statisticEvent);
        SessionStatisticEvent sessionStatisticEvent = (SessionStatisticEvent) statisticEvent;
        if (this.mCompleteID == 0 || this.mCompleteID == sessionStatisticEvent.mCompleteID) {
            this.mCompleteID = sessionStatisticEvent.mCompleteID;
            getDateMap().putAll(sessionStatisticEvent.getDateMap());
            return true;
        }
        return true;
    }

    public static SessionStatisticEvent instance(String str, String str2, String str3, int i, long j) {
        if ($assertionsDisabled || !(str == null || str2 == null || str3 == null)) {
            return new SessionStatisticEvent(str, str2, str3, i, j);
        }
        throw new AssertionError();
    }

    public static SessionStatisticEvent instance(int i, int i2, long j) {
        if ($assertionsDisabled || StatisticHelper.isKVByKeyCode(i)) {
            return new SessionStatisticEvent(i, i2, j);
        }
        throw new AssertionError("keyCode is not compatible for SessionStatisticEvent");
    }

    public void complete() {
        if (!isCompleted()) {
            this.mCompleteID = UUID.randomUUID().toString().hashCode();
        }
    }

    public void put(String str, String str2) {
        put(str, (Object) str2);
    }

    public void put(String str, int i) {
        put(str, Integer.valueOf(i));
    }

    public void put(String str, long j) {
        put(str, Long.valueOf(j));
    }

    private void put(String str, Object obj) {
        getDateMap().put(str, obj);
    }
}
