package com.mradar.sdk.record;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioRecord;

import androidx.core.app.ActivityCompat;

import com.mradar.sdk.http.HttpMRadarSdk;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class OnlineRecognizer extends Thread {
    private static final int SAMPLE_RATE = 8000;
    public static final String TAG = "OnlineRecognizer";
    private AudioRecord audioRecord;
    private long mByteCount;
    private boolean mCancel;
    private Context mContext;
    private String mKey;
    private MRadarSdkListener mListener;
    private long mMaxByte;
    private RecordByteQueue mQueue;
    private File mSaveFile;
    private boolean mStop;
    private HttpMRadarSdk nmclient;
    private boolean mAdvanceRecord = false;
    private boolean isStart = false;
    private String mErrorMsg = "";

    /* JADX INFO: Access modifiers changed from: protected */
    public OnlineRecognizer(Context context, String key, long duration, File saveFile, MRadarSdkListener listener) {
        this.nmclient = null;
        this.mContext = context;
        this.mKey = key;
        this.mMaxByte = 16 * duration;
        this.mSaveFile = saveFile;
        this.mListener = listener;
        //Logger.m2e(TAG, TAG);
        this.mQueue = new RecordByteQueue();
        this.nmclient = new HttpMRadarSdk(key);
    }

    public RecordByteQueue getmQueue() {
        return this.mQueue;
    }

    public void setmQueue(RecordByteQueue mQueue) {
        this.mQueue = mQueue;
    }

    public void setStopTimeout(int timeout) {
        this.nmclient.setStopTimeout(timeout);
    }

    public void setResumeTimeOut(int timeout) {
        this.nmclient.setResumeTimeOut(timeout);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reqStop() {
        this.mStop = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reqCancel() {
        this.nmclient.cancel();
        this.mCancel = true;
        this.mAdvanceRecord = false;
        this.mListener = null;
    }

    public boolean ismAdvanceRecord() {
        return this.mAdvanceRecord;
    }

    public void setmAdvanceRecord(boolean mAdvanceRecord) {
        this.mAdvanceRecord = mAdvanceRecord;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        int status;
        if (!MRadarSdkUtils.isNetworkEnable(this.mContext)) {
            if (this.mListener != null && !MRadarSdk.IsAdvance) {
                this.mListener.onError(MRadarSdkError.NETWORK_UNAVAILABLE, "NETWORK_UNAVAILABLE");
                return;
            }
            return;
        }
        int bufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE, 16, 2);
        if (bufferSize <= 1024) {
            bufferSize = 1024;
        }
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        this.audioRecord = new AudioRecord(6, SAMPLE_RATE, 16, 2, bufferSize);
        if (this.audioRecord.getState() != 1) {
            if (this.mListener != null && !this.mAdvanceRecord) {
                this.mListener.onError(MRadarSdkError.RECORD_INIT_FAIL, "RECORD_INIT_FAIL");
            }
            this.mAdvanceRecord = false;
            if (this.audioRecord != null) {
                this.audioRecord.stop();
                this.audioRecord.release();
                this.audioRecord = null;
                return;
            }
            return;
        }
        FileOutputStream fos = null;
        try {
            if (this.mSaveFile != null) {
                File dir = this.mSaveFile.getParentFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                fos = new FileOutputStream(this.mSaveFile);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            try {
                byte[] buffer = new byte[1024];
                this.audioRecord.startRecording();
                while (!this.mStop && !this.mCancel) {
                    int len = this.audioRecord.read(buffer, 0, buffer.length);
                    if (len > 0) {
                        byte[] data = new byte[len];
                        System.arraycopy(buffer, 0, data, 0, len);
                        if (this.mAdvanceRecord) {
                            this.mQueue.addRecord(data);
                        } else {
                            if (!this.isStart && !this.mCancel) {
                                this.nmclient.start(this.mContext);
                                this.isStart = true;
                            }
                            int size = this.mQueue.getSize();
                            if (size > 0) {
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                for (int i = 0; i < size; i++) {
                                    baos.write(this.mQueue.getHeadRecord());
                                }
                                byte[] by = baos.toByteArray();
                                status = this.nmclient.resume(by);
                                baos.reset();
                            } else {
                                if (fos != null) {
                                    try {
                                        fos.write(data);
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                status = this.nmclient.resume(data);
                            }
                            double volume = MRadarSdkUtils.computeDb(buffer, len);
                            if (this.mListener != null) {
                                this.mListener.onVolumeChanged(volume);
                            }
                            if (fos != null) {
                                try {
                                    fos.write(data);
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                            if (status != 0) {
                                break;
                            }
                            this.mByteCount += len;
                            if (this.mMaxByte != 0 && this.mByteCount >= this.mMaxByte) {
                                reqStop();
                            }
                        }
                    }
                }
                this.mQueue.clear();
                if (this.mListener != null) {
                    this.mListener.onRecordEnd();
                }
                if (this.audioRecord != null && this.audioRecord.getState() == 1) {
                    this.audioRecord.stop();
                    this.audioRecord.release();
                    this.audioRecord = null;
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            } catch (Exception e5) {
                e5.printStackTrace();
                if (this.mAdvanceRecord) {
                    this.mAdvanceRecord = false;
                }
                if (this.audioRecord != null && this.audioRecord.getState() == 1) {
                    this.audioRecord.stop();
                    this.audioRecord.release();
                    this.audioRecord = null;
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
            }
            if (!this.mCancel) {
                this.nmclient.stop();
                String result = this.nmclient.getResult();
                if (!this.mCancel) {
                    //Logger.m2e(TAG, "result:" + result);
                    int code = checkResult(result);
                    switch (code) {
                        case 0:
                            if (this.mListener != null) {
                                this.mListener.onFinish(MRadarSdkJSON.GetJSONString(result), result);
                                break;
                            }
                            break;
                        case 10001:
                            if (this.mListener != null) {
                                this.mListener.onError(MRadarSdkError.INVALID_KEY, this.mErrorMsg);
                                break;
                            }
                            break;
                        case 10002:
                            if (this.mListener != null) {
                                this.mListener.onError(MRadarSdkError.NO_RESULT, this.mErrorMsg);
                                break;
                            }
                            break;
                        case 10003:
                            if (this.mListener != null) {
                                this.mListener.onError(MRadarSdkError.SEVER_NOT_CONNECT, this.mErrorMsg);
                                break;
                            }
                            break;
                        case 10004:
                        case 10005:
                        case 10006:
                            if (this.mListener != null) {
                                this.mListener.onError(MRadarSdkError.SERVICE_UNAVAILABLE, this.mErrorMsg);
                                break;
                            }
                            break;
                        case 10007:
                            if (this.mListener != null) {
                                this.mListener.onError(MRadarSdkError.DATA_TOO_LARGE, this.mErrorMsg);
                                break;
                            }
                            break;
                        case 10008:
                            if (this.mListener != null) {
                                this.mListener.onError(MRadarSdkError.INVALID_CMD, this.mErrorMsg);
                                break;
                            }
                            break;
                        case 20001:
                            if (this.mListener != null) {
                                this.mListener.onError(MRadarSdkError.CONNECT_ERROR, this.mErrorMsg);
                                break;
                            }
                            break;
                        case 30001:
                            if (this.mListener != null) {
                                this.mListener.onError(MRadarSdkError.NO_RESULT, this.mErrorMsg);
                                break;
                            }
                            break;
                        default:
                            if (this.mListener != null) {
                                this.mListener.onError(MRadarSdkError.NO_RESULT, this.mErrorMsg);
                                break;
                            }
                            break;
                    }
                }
            } else {
                this.nmclient.cancel();
            }
            this.nmclient.release();
        } catch (Throwable th) {
            if (this.audioRecord != null && this.audioRecord.getState() == 1) {
                this.audioRecord.stop();
                this.audioRecord.release();
                this.audioRecord = null;
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
            throw th;
        }
    }

    private int checkResult(String result) {
        try {
            JSONArray array = new JSONArray(result);
            try {
                JSONObject obj = array.getJSONObject(0);
                try {
                    int error = obj.getInt("errorcode");
                    this.mErrorMsg = "";
                    try {
                        this.mErrorMsg = obj.getString(MRadarSdk.ERROR);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return error;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    return 0;
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
                return -1;
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
            return -1;
        }
    }
}
