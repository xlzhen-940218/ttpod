package com.sds.android.sdk.core.statistic;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class StatisticCommitter {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final int COMMIT_TYPE_DAY = 6;
    private static final int COMMIT_TYPE_FIVE_MINUTE = 2;
    private static final int COMMIT_TYPE_HALF_DAY = 5;
    private static final int COMMIT_TYPE_HALF_HOUR = 3;
    private static final int COMMIT_TYPE_HOUR = 4;
    private static final int COMMIT_TYPE_IMMEDIATE = 0;
    private static final int COMMIT_TYPE_MINUTE = 1;
    private static final int MSG_POST = 0;
    private static final String TAG = "StatisticCommit";
    public static final String TEST_URL_MAIN = "http://test.log.ttpod.com/test_log";
    private static final long[] TIME_INTERVAL_MILLIS;
    public static final String URL_MAIN = "http://collect.log.ttpod.com/ttpod_client/index.html";
    private CallBack mCallBack;
    private boolean mCommitRunning;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private long mLastMaxDelayTimeMillis;
    private long mLastMinDelayTimeMillis;
    private String mUrl = URL_MAIN;

    /* loaded from: classes.dex */
    public interface CallBack {
        void onError(Iterable<StatisticEvent> iterable, String str);

        void onSuccess(Iterable<StatisticEvent> iterable, String str);
    }

    static {
        $assertionsDisabled = !StatisticCommitter.class.desiredAssertionStatus();
        TIME_INTERVAL_MILLIS = new long[]{0, 60000, 300000, 1800000, 3600000, 43200000, 86400000};
    }

    public StatisticCommitter(CallBack callBack) {
        if (!$assertionsDisabled && callBack != null) {
            throw new AssertionError("callback can not null!");
        }
        this.mCallBack = callBack;
        this.mHandlerThread = new HandlerThread("postHandlerThread");
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.sds.android.sdk.core.statistic.StatisticCommitter.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        postStatistic((List) message.obj);
                        return;
                    default:
                        return;
                }
            }

            private void postStatistic(Iterable<StatisticEvent> iterable) {
                String uuid = UUID.randomUUID().toString();
                try {
                    JSONArray jSONArray = new JSONArray();
                    for (StatisticEvent statisticEvent : iterable) {
                        JSONObject jSONObject = new JSONObject();
                        for (Map.Entry<String, Object> entry : statisticEvent.getDateMap().entrySet()) {
                            jSONObject.put(entry.getKey(), entry.getValue());
                        }
                        jSONArray.put(jSONObject);
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("data", jSONArray);
                    jSONObject2.put(SService.STATISTIC_PARAM, EnvironmentUtils.C0603b.m8487f());
                    jSONObject2.put("uuid", uuid);
                    jSONObject2.put("time", System.currentTimeMillis());
                    String jSONObject3 = jSONObject2.toString();
                    LogUtils.m8388a(StatisticCommitter.TAG, "send statistic url: " + StatisticCommitter.this.mUrl);
                    LogUtils.m8388a(StatisticCommitter.TAG, "send statistic data: " + jSONObject3);
                    HttpResponse request = HttpClientProxy.instance().request(StatisticCommitter.this.mUrl, jSONObject3);
                    HttpClientProxy.instance().closePost();
                    if (200 == request.getStatusLine().getStatusCode()) {
                        StatisticCommitter.this.mCallBack.onSuccess(iterable, uuid);
                    } else {
                        StatisticCommitter.this.mCallBack.onError(iterable, uuid);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    StatisticCommitter.this.mCallBack.onError(iterable, uuid + "_" + e.toString());
                    HttpClientProxy.instance().closePost();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isCommitting() {
        return this.mCommitRunning;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isMinDelayTimeArrived() {
        boolean z = true;
        long currentTimeMillis = System.currentTimeMillis();
        if (EnvironmentUtils.C0602a.m8503h()) {
            if (currentTimeMillis - this.mLastMinDelayTimeMillis < TIME_INTERVAL_MILLIS[0]) {
                z = false;
            }
        } else if (currentTimeMillis - this.mLastMinDelayTimeMillis < TIME_INTERVAL_MILLIS[1]) {
            z = false;
        }
        if (z) {
            this.mLastMinDelayTimeMillis = currentTimeMillis;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isMaxDelayTimeArrived() {
        boolean z = true;
        long currentTimeMillis = System.currentTimeMillis();
        if (EnvironmentUtils.C0602a.m8503h()) {
            if (currentTimeMillis - this.mLastMaxDelayTimeMillis < TIME_INTERVAL_MILLIS[0]) {
                z = false;
            }
        } else if (currentTimeMillis - this.mLastMaxDelayTimeMillis < TIME_INTERVAL_MILLIS[2]) {
            z = false;
        }
        if (z) {
            this.mLastMaxDelayTimeMillis = currentTimeMillis;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setCommitRunning(boolean z) {
        LogUtils.m8388a(TAG, "commitRunning = " + z);
        this.mCommitRunning = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setUrl(String str) {
        this.mUrl = str;
    }

    protected String getUrl() {
        return StringUtils.m8346a(this.mUrl) ? URL_MAIN : this.mUrl;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void postStatistic(List<StatisticEvent> list) {
        Message obtainMessage = this.mHandler.obtainMessage(0);
        obtainMessage.obj = list;
        this.mHandler.sendMessage(obtainMessage);
    }
}
