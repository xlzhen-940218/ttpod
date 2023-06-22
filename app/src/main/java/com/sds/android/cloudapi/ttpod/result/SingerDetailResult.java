package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;
import com.sds.android.cloudapi.ttpod.result.SingerBriefResult;
import java.io.Serializable;

/* loaded from: classes.dex */
public class SingerDetailResult extends SingerBaseResult implements Serializable {
    @SerializedName(value = "data")
    private SingerDetailData mData;

    /* loaded from: classes.dex */
    public static class SingerDetailData extends SingerBriefResult.SingerBriefData {
        @SerializedName(value = "awards")
        private String mAwards;
        @SerializedName(value = "earlyExperience")
        private String mEarlyExperience;
        @SerializedName(value = "entertainmentExperience")
        private String mEntertainmentExperience;
        @SerializedName(value = "evaluation")
        private String mEvaluation;
        @SerializedName(value = "personalLife")
        private String mPersonalLife;
        @SerializedName(value = "piece")
        private String mPiece;
        @SerializedName(value = "socialActivity")
        private String mSocialActivity;

        public String getEarlyExperience() {
            return this.mEarlyExperience;
        }

        public String getEntertainmentExperience() {
            return this.mEntertainmentExperience;
        }

        public String getPiece() {
            return this.mPiece;
        }

        public String getAwards() {
            return this.mAwards;
        }

        public String getPersonalLife() {
            return this.mPersonalLife;
        }

        public String getEvaluation() {
            return this.mEvaluation;
        }

        public String getSocialActivity() {
            return this.mSocialActivity;
        }
    }

    public SingerDetailData getData() {
        if (this.mData == null) {
            this.mData = new SingerDetailData();
        }
        return this.mData;
    }
}
