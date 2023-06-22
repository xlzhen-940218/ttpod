package com.mradar.sdk.http;

import android.text.TextUtils;
import android.util.Log;
import com.mradar.sdk.http.StaticParam;
import com.mradar.sdk.record.MRadarSdk;
import com.voicedragon.musicclient.nativemethod.SpeexWrapper;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class MRadarSdkWorkThread extends Thread {
    private static final int CURRENT_LEN = 51200;
    private static final int SEND_ONCE_LEN = 16384;
    private static final String TAG = "MRadarSdkWorkThread";
    private long start_time;
    private long starttime;
    private BlockingQueue<MRadarSdkCmd> cmdQueue = new LinkedBlockingQueue();
    private SpeexWrapper speex = null;
    private final StaticParam.CompressType compressType = StaticParam.CompressType.SPEEX;
    private String sessionId = "";
    private String res = "";
    private boolean isRunning = false;
    private boolean isHasResult = false;
    private int num = 0;
    private int maxnum = 2;
    private int stopTimeOut = 15000;
    private int resumeTimeOut = 5000;
    private long totaltime = this.stopTimeOut + 12000;

    public void processCmd(MRadarSdkCmd cmd) {
        if (MRadarSdk.START.equals(cmd.getCmdType()) && this.compressType == StaticParam.CompressType.SPEEX) {
            this.speex = new SpeexWrapper();
            this.speex.encodeInit(0);
            this.speex.setQuality(Integer.parseInt((String) cmd.getValue(MRadarSdk.COMPRESS_QUALITY)), 10);
        }
        if (MRadarSdk.STOP.equals(cmd.getCmdType())) {
            this.start_time = System.currentTimeMillis();
        }
        try {
            this.cmdQueue.put(cmd);
        } catch (Exception e) {
            this.res = MRadarSdkStatusCode.WORK_THREAD_BLOCKINGQUEUE_PUT_ERROR;
            this.isRunning = false;
            this.isHasResult = true;
        }
    }

    public String getResult() {
        return this.res;
    }

    public void setResult(String res) {
        this.res = res;
    }

    public void setRunning(boolean isRun) {
        this.isRunning = isRun;
    }

    public int getStopTimeOut() {
        return this.stopTimeOut;
    }

    public void setStopTimeOut(int stopTimeOut) {
        this.stopTimeOut = stopTimeOut;
    }

    public int getResumeTimeOut() {
        return this.resumeTimeOut;
    }

    public void setResumeTimeOut(int resumeTimeOut) {
        this.resumeTimeOut = resumeTimeOut;
    }

    public boolean isHasResult() {
        return this.isHasResult;
    }

    public void release() {
        this.cmdQueue.clear();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        this.isRunning = true;
        MRadarSdkCmd task = null;
        MRadarSdkCmd cTask = null;
        MRadarSdkCmd waitTask = null;
        this.starttime = System.currentTimeMillis();
        byte[] currentBuffer = new byte[CURRENT_LEN];
        int currentBufLen = 0;
        while (true) {
            if (!this.isRunning) {
                break;
            } else if (this.start_time != 0 && System.currentTimeMillis() - this.start_time >= this.stopTimeOut) {
                this.isRunning = false;
                this.res = MRadarSdkStatusCode.HTTP_CONNECT_TIMEOUT;
                this.isHasResult = true;
                break;
            } else {
                if (cTask == null) {
                    try {
                        task = this.cmdQueue.poll(1L, TimeUnit.SECONDS);
                    } catch (Exception e) {
                        this.res = MRadarSdkStatusCode.WORK_THREAD_BLOCKINGQUEUE_POLL_ERROR;
                        this.isRunning = false;
                        this.isHasResult = true;
                    }
                }
                if (task != null) {
                    try {
                        task.setParam(MRadarSdk.COMPRESS_TYPE, String.valueOf(this.compressType.ordinal()));
                        if (MRadarSdk.START.equals(task.getCmdType())) {
                            cTask = task;
                        } else if (MRadarSdk.RESUME.equals(task.getCmdType())) {
                            byte[] tBuffer = (byte[]) task.getValue(MRadarSdk.SAMPLE);
                            System.arraycopy(tBuffer, 0, currentBuffer, currentBufLen, tBuffer.length);
                            currentBufLen += tBuffer.length;
                            if (currentBufLen >= 16384) {
                                MRadarSdkCmd tCmd = new MRadarSdkCmd(MRadarSdk.RESUME);
                                tCmd.setParam(MRadarSdk.SID, this.sessionId);
                                tCmd.setParam(MRadarSdk.COMPRESS_TYPE, String.valueOf(this.compressType.ordinal()));
                                byte[] data = new byte[currentBufLen];
                                System.arraycopy(currentBuffer, 0, data, 0, currentBufLen);
                                if (this.compressType == StaticParam.CompressType.SPEEX) {
                                    byte[] tSpeexData = this.speex.encode(data, false);
                                    data = tSpeexData;
                                }
                                tCmd.setParam(MRadarSdk.SAMPLE, data);
                                cTask = tCmd;
                                currentBufLen = 0;
                            }
                        } else if (MRadarSdk.STOP.equals(task.getCmdType())) {
                            if (currentBufLen > 0) {
                                MRadarSdkCmd tCmd2 = new MRadarSdkCmd(MRadarSdk.RESUME);
                                tCmd2.setParam(MRadarSdk.SID, this.sessionId);
                                tCmd2.setParam(MRadarSdk.COMPRESS_TYPE, String.valueOf(this.compressType.ordinal()));
                                byte[] data2 = new byte[currentBufLen];
                                System.arraycopy(currentBuffer, 0, data2, 0, currentBufLen);
                                if (this.compressType == StaticParam.CompressType.SPEEX && this.speex != null) {
                                    byte[] tSpeexData2 = this.speex.encode(data2, false);
                                    data2 = tSpeexData2;
                                }
                                tCmd2.setParam(MRadarSdk.SAMPLE, data2);
                                cTask = tCmd2;
                                waitTask = task;
                                waitTask.setParam(MRadarSdk.SID, this.sessionId);
                                currentBufLen = 0;
                            } else {
                                cTask = task;
                                cTask.setParam(MRadarSdk.SID, this.sessionId);
                            }
                        }
                        task = null;
                    } catch (Exception e2) {
                        this.res = MRadarSdkStatusCode.WORK_THREAD_MERGE_TASK_ERROR;
                        e2.printStackTrace();
                        this.isRunning = false;
                        this.isHasResult = true;
                    }
                }
                if (cTask != null) {
                    String cmd = cTask.getCmdType();
                    int time = this.resumeTimeOut;
                    int readTime = this.resumeTimeOut;
                    if (cmd.equals(MRadarSdk.STOP)) {
                        time = this.stopTimeOut;
                        readTime = this.stopTimeOut;
                    } else if (cmd.equals(MRadarSdk.RESUME)) {
                        try {
                            readTime = Integer.parseInt(new StringBuilder(String.valueOf(this.totaltime - (System.currentTimeMillis() - this.starttime))).toString());
                        } catch (Exception e3) {
                            readTime = this.resumeTimeOut;
                        }
                    }
                    try {
                        String httpRes = HttpWrapper.doPost(cTask.getParams(), time, readTime);
                        cTask = waitTask;
                        waitTask = null;
                        System.out.println(httpRes);
                        if (!this.isRunning) {
                            break;
                        }
                        try {
                            JSONArray array = new JSONArray(httpRes);
                            if (!TextUtils.isEmpty(array.optJSONObject(0).optString(MRadarSdk.ERROR))) {
                                this.isRunning = false;
                                this.res = httpRes;
                                this.isHasResult = true;
                                break;
                            }
                            if (MRadarSdk.START.equals(cmd)) {
                                this.sessionId = array.optJSONObject(0).optString(MRadarSdk.SID);
                            }
                            if (MRadarSdk.RESUME.equals(cmd) && TextUtils.isEmpty(array.optJSONObject(0).optString(MRadarSdk.RESUME))) {
                                this.isRunning = false;
                                this.res = httpRes;
                                this.isHasResult = true;
                                break;
                            } else if (MRadarSdk.STOP.equals(cmd)) {
                                this.isRunning = false;
                                this.isHasResult = true;
                                this.res = httpRes;
                            }
                        } catch (Exception e4) {
                            e4.printStackTrace();
                            this.res = MRadarSdkStatusCode.PARSE_JSON_ERROR;
                            this.isRunning = false;
                            this.isHasResult = true;
                        }
                    } catch (MRadarSdkHttpApiException e5) {
                        this.res = e5.errorMsg;
                        if (cmd.equals(MRadarSdk.STOP)) {
                            if (this.res.equals(MRadarSdkStatusCode.HTTP_CONNECT_TIMEOUT)) {
                                this.isRunning = false;
                                this.isHasResult = true;
                            } else if (this.res.equals(MRadarSdkStatusCode.HTTP_READ_FROM_DORESO_ERROR)) {
                                this.isRunning = false;
                                this.isHasResult = true;
                            }
                        } else if (cmd.equals(MRadarSdk.START)) {
                            if (!this.isRunning) {
                                this.isHasResult = true;
                                break;
                            } else if (this.num >= this.maxnum) {
                                this.isRunning = false;
                                this.isHasResult = true;
                            } else {
                                this.num++;
                            }
                        } else if (cmd.equals(MRadarSdk.RESUME) && this.res.equals(MRadarSdkStatusCode.HTTP_CONNECT_TIMEOUT)) {
                            this.isRunning = false;
                            this.isHasResult = true;
                        }
                        Log.e(TAG, "STOP res:" + this.res + "cmd:" + cmd);
                        e5.printStackTrace();
                    }
                }
            }
        }
        if (this.speex != null) {
            this.speex.release();
        }
    }
}
