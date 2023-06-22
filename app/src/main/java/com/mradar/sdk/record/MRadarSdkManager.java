package com.mradar.sdk.record;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.util.concurrent.Future;

/* loaded from: classes.dex */
public class MRadarSdkManager {
    private static final int STATE_IDLE = 0;
    private static final int STATE_RECOGNIZE_OFFLINE = 3;
    private static final int STATE_RECOGNIZE_ONLINE = 1;
    private static final int STATE_RECORD_ONLY = 2;
    private static final String TAG = "MRadarSdkManager";
    private Context mContext;
    private long mDuration;
    private Future<?> mFuture;
    private String mKey;
    private MRadarSdkListener mListener;
    private OfflineRecognizer mOfflineRecognizer;
    private OnlineRecognizer mOnlineRecognizer;
    private Record mRecord;
    private int mState;

    public MRadarSdkManager(Context context, String key) {
        this.mContext = context;
        this.mKey = key;
    }

    public MRadarSdkManager(Context context, String key, String serverName) {
        this.mContext = context;
        this.mKey = key;
        if (!TextUtils.isEmpty(serverName)) {
            MRadarSdk.SERVERS_NAME = serverName;
        }
    }

    public void setListener(MRadarSdkListener listener) {
        this.mListener = listener;
    }

    public void setDuration(long timeInMillisecond) {
        this.mDuration = timeInMillisecond;
    }

    public void start(File saveFile) {
        if (this.mState == 0 || this.mState == 1) {
            this.mState = 1;
            if (this.mOnlineRecognizer != null) {
                this.mOnlineRecognizer.reqCancel();
                this.mOnlineRecognizer = null;
                this.mFuture.cancel(false);
            }
            this.mOnlineRecognizer = new OnlineRecognizer(this.mContext, this.mKey, this.mDuration, saveFile, this.mListener);
            this.mFuture = MRadarSdkThreadPoll.getInstance().getThreadPool().submit(this.mOnlineRecognizer);
        }
    }

    public void flush() {
        this.mState = 0;
    }

    public void setStopTimeout(int timeout) {
        if (this.mOnlineRecognizer != null) {
            this.mOnlineRecognizer.setStopTimeout(timeout);
        }
    }

    public void setResumeTimeOut(int timeout) {
        if (this.mOnlineRecognizer != null) {
            this.mOnlineRecognizer.setResumeTimeOut(timeout);
        }
    }

    public void setAdvance(boolean advance) {
        if (this.mOnlineRecognizer != null) {
            this.mOnlineRecognizer.setmAdvanceRecord(advance);
            if (advance) {
                MRadarSdk.IsAdvance = true;
            }
        }
    }

    public boolean isAdvance() {
        if (this.mOnlineRecognizer == null) {
            return false;
        }
        return this.mOnlineRecognizer.ismAdvanceRecord();
    }

    public void stop() {
        if (this.mState == 1) {
            if (this.mOnlineRecognizer != null) {
                this.mOnlineRecognizer.reqStop();
            }
        } else if (this.mState == 2) {
            if (this.mRecord != null) {
                this.mRecord.reqStop();
            }
        } else if (this.mState == 3 && this.mOfflineRecognizer != null) {
            this.mOfflineRecognizer.reqStop();
        }
        this.mState = 0;
    }

    public void cancel() {
        if (this.mState == 1) {
            if (this.mOnlineRecognizer != null) {
                this.mOnlineRecognizer.reqCancel();
            }
        } else if (this.mState == 2) {
            if (this.mRecord != null) {
                this.mRecord.reqCancel();
            }
        } else if (this.mState == 3 && this.mOfflineRecognizer != null) {
            this.mOfflineRecognizer.reqCancel();
        }
        this.mState = 0;
    }

    public void startRecord(File saveFile) {
        if (this.mState == 0) {
            this.mState = 2;
            this.mRecord = new Record(saveFile, this.mDuration, this.mListener);
            this.mRecord.start();
            return;
        }
        Log.e(TAG, "ilegal state " + this.mState);
    }

    public void startRecognize(File recogFile) {
        if (this.mState == 0) {
            this.mState = 3;
            this.mOfflineRecognizer = new OfflineRecognizer(this.mContext, this.mKey, this.mDuration, recogFile, this.mListener);
            this.mOfflineRecognizer.start();
            return;
        }
        Log.e(TAG, "ilegal state " + this.mState);
    }
}
