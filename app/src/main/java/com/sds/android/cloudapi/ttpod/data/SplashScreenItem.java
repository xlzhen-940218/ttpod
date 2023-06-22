package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class SplashScreenItem implements Serializable {
    @SerializedName(value = "date")
    private String mDate = "";
    @SerializedName(value = "large_pic_url")
    private String mLargePicUrl = "";
    @SerializedName(value = "middle_pic_url")
    private String mMiddlePicUrl = "";
    @SerializedName(value = "small_pic_url")
    private String mSmallPicUrl = "";
    @SerializedName(value = "label")
    private String mLabel = "";
    @SerializedName(value = "audio")
    private String mAudioUrl = "";

    public boolean isContain(String str) {
        return new Period(this.mDate).contain(str);
    }

    public boolean isValid(String str) {
        return new Period(this.mDate).valid(str);
    }

    public String getDate() {
        return this.mDate;
    }

    public String getLargePicUrl() {
        return this.mLargePicUrl;
    }

    public String getMiddlePicUrl() {
        return this.mMiddlePicUrl;
    }

    public String getSmallPicUrl() {
        return this.mSmallPicUrl;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public String getAudioUrl() {
        return this.mAudioUrl;
    }

    /* loaded from: classes.dex */
    public static class Period implements Serializable {
        private List<DateRange> mDateRanges = new ArrayList();

        public Period(String str) {
            for (String str2 : str.split(";")) {
                this.mDateRanges.add(new DateRange(str2));
            }
        }

        public boolean contain(String str) {
            for (DateRange dateRange : this.mDateRanges) {
                if (dateRange.contain(str)) {
                    return true;
                }
            }
            return false;
        }

        public boolean valid(String str) {
            for (DateRange dateRange : this.mDateRanges) {
                if (dateRange.valid(str)) {
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public class DateRange implements Serializable {
            private String mBegin;
            private String mEnd;

            public DateRange(String str) {
                this.mBegin = "";
                this.mEnd = "";
                Matcher matcher = Pattern.compile("^\\s*(\\d{8})\\s*(-\\s*(\\d{8})){0,1}\\s*$").matcher(str);
                while (matcher.find()) {
                    this.mBegin = matcher.group(1) != null ? matcher.group(1) : "";
                    this.mEnd = matcher.group(3) != null ? matcher.group(3) : this.mBegin;
                }
            }

            public boolean contain(String str) {
                return str.compareTo(this.mBegin) >= 0 && str.compareTo(this.mEnd) <= 0;
            }

            public boolean valid(String str) {
                return str.compareTo(this.mEnd) <= 0;
            }
        }
    }
}
