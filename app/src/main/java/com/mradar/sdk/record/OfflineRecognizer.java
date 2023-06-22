package com.mradar.sdk.record;

import android.content.Context;
import com.mradar.sdk.http.HttpMRadarSdk;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class OfflineRecognizer extends Thread {
    public static final String TAG = "OfflineRecognizer";
    private long mByteCount;
    private boolean mCancel;
    private Context mContext;
    private String mErrorMsg = "";
    private String mKey;
    private MRadarSdkListener mListener;
    private long mMaxByte;
    private File mRecogFile;
    private boolean mStop;
    private HttpMRadarSdk nmclient;

    /* JADX INFO: Access modifiers changed from: protected */
    public OfflineRecognizer(Context context, String key, long duration, File recogFile, MRadarSdkListener listener) {
        this.mContext = context;
        this.mKey = key;
        this.mMaxByte = 16 * duration;
        this.mRecogFile = recogFile;
        this.mListener = listener;
        this.nmclient = new HttpMRadarSdk(key);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reqStop() {
        this.mStop = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reqCancel() {
        this.mCancel = true;
        this.mListener = null;
    }

    protected HttpMRadarSdk getNmc() {
        return this.nmclient;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (!MRadarSdkUtils.isNetworkEnable(this.mContext)) {
            if (this.mListener != null) {
                this.mListener.onError(MRadarSdkError.NETWORK_UNAVAILABLE, "NETWORK_UNAVAILABLE");
                return;
            }
            return;
        }
        try {
            FileInputStream fis = new FileInputStream(this.mRecogFile);
            this.nmclient.start(this.mContext);
            try {
                try {
                    byte[] buffer = new byte[1600];
                    while (!this.mStop && !this.mCancel) {
                        int len = fis.read(buffer);
                        if (len == -1) {
                            reqStop();
                        } else if (len > 0) {
                            byte[] data = new byte[len];
                            System.arraycopy(buffer, 0, data, 0, len);
                            int status = this.nmclient.resume(data);
                            if (status != 0) {
                                break;
                            }
                            this.mByteCount += len;
                            if (this.mMaxByte != 0 && this.mByteCount >= this.mMaxByte) {
                                reqStop();
                            }
                        } else {
                            continue;
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Throwable th) {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            }
            if (!this.mCancel) {
                this.nmclient.stop();
                String result = this.nmclient.getResult();
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
                    default:
                        if (this.mListener != null) {
                            this.mListener.onError(MRadarSdkError.NO_RESULT, this.mErrorMsg);
                            break;
                        }
                        break;
                }
            }
            this.nmclient.release();
        } catch (FileNotFoundException e5) {
            e5.printStackTrace();
            if (this.mListener != null) {
                this.mListener.onError(MRadarSdkError.FILE_NOT_FOUND, "DATA_UNAVAILABLE");
            }
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
