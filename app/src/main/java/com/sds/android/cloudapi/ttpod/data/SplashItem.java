package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class SplashItem implements Serializable {
    @SerializedName(value = "end_time")
    private int mEndTime;
    @SerializedName(value = "files")
    private SplashFile mSplashFile;
    @SerializedName(value = "start_time")
    private int mStartTime;

    public boolean isContain(int i) {
        return this.mStartTime <= i && i <= this.mEndTime;
    }

    public boolean isValid(int i) {
        return i <= this.mEndTime;
    }

    public String getSuitFile(int i) {
        if (this.mSplashFile != null) {
            return this.mSplashFile.getSuitFile(i);
        }
        return null;
    }

    /* loaded from: classes.dex */
    private class SplashFile implements Serializable {
        @SerializedName(value = "hdpi")
        private String mHdpiFile;
        @SerializedName(value = "mdpi")
        private String mMdpiFile;
        @SerializedName(value = "xhdpi")
        private String mXhdpiFile;
        @SerializedName(value = "xxhdpi")
        private String mXxhdpiFile;

        private SplashFile() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getSuitFile(int i) {
            String str = this.mMdpiFile;
            switch (i) {
                case 240:
                    return this.mHdpiFile;
                case 320:
                    return this.mXhdpiFile;
                case 480:
                    return this.mXxhdpiFile;
                default:
                    return str;
            }
        }
    }
}
