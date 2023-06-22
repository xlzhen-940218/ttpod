package com.mradar.sdk.record;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioRecord;

import androidx.core.app.ActivityCompat;

import com.sds.android.ttpod.TTPodApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class Record extends Thread {
    private static final int SAMPLE_RATE = 8000;
    public static final String TAG = "Record";
    private long mByteCount;
    private boolean mCancel;
    private MRadarSdkListener mListener;
    private long mMaxByte;
    private File mSaveFile;
    private boolean mStop;

    /* JADX INFO: Access modifiers changed from: protected */
    public Record(File saveFile, long duration, MRadarSdkListener listener) {
        this.mMaxByte = 16 * duration;
        this.mSaveFile = saveFile;
        this.mListener = listener;
        //AppMRadar.getInstance().getStream().reset();
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

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        int bufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE, 16, 2);

        if (ActivityCompat.checkSelfPermission(TTPodApplication.getApplication(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        AudioRecord audioRecord = new AudioRecord(6, SAMPLE_RATE, 16, 2, bufferSize);
        if (audioRecord.getState() != 1) {
            if (this.mListener != null) {
                this.mListener.onError(MRadarSdkError.RECORD_INIT_FAIL, "RECORD_INIT_FAIL");
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
            if (this.mListener != null) {
                this.mListener.onError(MRadarSdkError.WRITE_FILE_ERROR, "CREATE_FILE_ERROR");
                return;
            }
        }
        try {
            try {
                byte[] buffer = new byte[bufferSize];
                audioRecord.startRecording();
                while (!this.mStop && !this.mCancel) {
                    int len = audioRecord.read(buffer, 0, buffer.length);
                    if (len > 0) {
                        byte[] data = new byte[len];
                        System.arraycopy(buffer, 0, data, 0, len);
                        double volume = MRadarSdkUtils.computeDb(buffer, len);
                        if (this.mListener != null) {
                            this.mListener.onVolumeChanged(volume);
                        }
                        if (fos != null) {
                            try {
                                fos.write(data);
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                if (this.mListener != null) {
                                    this.mListener.onError(MRadarSdkError.FILE_NOT_FOUND, "WRITE_FILE_ERROR");
                                }
                                if (audioRecord != null && audioRecord.getState() == 1) {
                                    audioRecord.stop();
                                    audioRecord.release();
                                }
                                if (fos != null) {
                                    try {
                                        fos.close();
                                        return;
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                        return;
                                    }
                                }
                                return;
                            }
                        }
                    }
                    this.mByteCount += len;
                    if (this.mMaxByte != 0 && this.mByteCount >= this.mMaxByte) {
                        reqStop();
                    }
                }
                if (this.mListener != null) {
                    this.mListener.onRecordEnd();
                }
                if (audioRecord != null && audioRecord.getState() == 1) {
                    audioRecord.stop();
                    audioRecord.release();
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                if (audioRecord != null && audioRecord.getState() == 1) {
                    audioRecord.stop();
                    audioRecord.release();
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e6) {
            e6.printStackTrace();
            if (audioRecord != null && audioRecord.getState() == 1) {
                audioRecord.stop();
                audioRecord.release();
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
        }
    }
}
