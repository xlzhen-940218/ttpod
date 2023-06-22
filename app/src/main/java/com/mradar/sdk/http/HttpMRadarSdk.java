package com.mradar.sdk.http;

import android.content.Context;
import com.mradar.sdk.record.MRadarSdk;
import com.mradar.sdk.record.MRadarSdkUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class HttpMRadarSdk {
    private String appKey;
    private MRadarSdkWorkThread workThread = new MRadarSdkWorkThread();
    private Map<String, String> initParams = null;

    public HttpMRadarSdk(String appKey) {
        this.appKey = "";
        this.appKey = appKey;
    }

    public void init(Map<String, String> initParams) {
        if (initParams == null) {
            this.initParams = new HashMap();
        } else {
            this.initParams = initParams;
        }
        String comQuality = this.initParams.get(MRadarSdk.COMPRESS_QUALITY);
        if (comQuality == null || Integer.parseInt(comQuality) > 10) {
            this.initParams.put(MRadarSdk.COMPRESS_QUALITY, MRadarSdk.SPEEX_WIFI);
        }
    }

    public void start(Context context) {
        //Logger.m2e(MRadarSdk.START, MRadarSdk.START);
        init(MRadarSdkUtils.GetOptions(context));
        MRadarSdkCmd cmd = new MRadarSdkCmd(MRadarSdk.START);
        cmd.setParam("appkey", this.appKey);
        if (this.initParams != null) {
            for (String key : this.initParams.keySet()) {
                cmd.setParam(key, this.initParams.get(key));
            }
        } else {
            cmd.setParam(MRadarSdk.COMPRESS_QUALITY, MRadarSdk.SPEEX_WIFI);
        }
        if (this.workThread != null) {
            this.workThread = new MRadarSdkWorkThread();
        }
        this.workThread.processCmd(cmd);
        this.workThread.start();
    }

    public int resume(byte[] data) {
        MRadarSdkCmd cmd = new MRadarSdkCmd(MRadarSdk.RESUME);
        cmd.setParam(MRadarSdk.SAMPLE, data);
        this.workThread.processCmd(cmd);
        return this.workThread.isHasResult() ? 1 : 0;
    }

    public void stop() {
        MRadarSdkCmd cmd = new MRadarSdkCmd(MRadarSdk.STOP);
        this.workThread.processCmd(cmd);
        try {
            this.workThread.join();
        } catch (Exception e) {
            if (!this.workThread.isHasResult()) {
                this.workThread.setResult(MRadarSdkStatusCode.WORK_THREAD_JOIN_ERROR);
            }
        }
    }

    public void cancel() {
        if (this.workThread != null) {
            this.workThread.setRunning(false);
        }
    }

    public String getResult() {
        return this.workThread != null ? this.workThread.getResult() : "";
    }

    public void setStopTimeout(int timeout) {
        this.workThread.setStopTimeOut(timeout);
    }

    public void setResumeTimeOut(int timeout) {
        this.workThread.setResumeTimeOut(timeout);
    }

    public void release() {
        if (this.workThread != null) {
            this.workThread.setRunning(false);
            this.workThread.release();
            this.workThread = null;
        }
        MRadarSdk.IsAdvance = false;
    }
}
