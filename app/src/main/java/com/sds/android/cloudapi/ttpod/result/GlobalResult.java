package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;
import com.sds.android.sdk.lib.request.BaseResult;

/* loaded from: classes.dex */
public class GlobalResult extends BaseResult {
    @SerializedName(value = "extra")
    private Extra mExtra;

    public boolean isIPSupported() {
        return 2 != getCode();
    }

    public boolean isAllowToDownloadSong() {
        if (this.mExtra != null) {
            return "1".equals(this.mExtra.getForce());
        }
        return true;
    }

    public boolean isSearchRestricted() {
        if (this.mExtra != null) {
            return this.mExtra.isSearchRestricted();
        }
        return false;
    }

    public boolean is360GuideEnabled() {
        if (this.mExtra != null) {
            return this.mExtra.is360GuideEnabled();
        }
        return false;
    }

    public boolean is360UnoinEnabled() {
        if (this.mExtra != null) {
            return this.mExtra.is360UnoinEnabled();
        }
        return false;
    }

    public String getVersion() {
        return this.mExtra != null ? this.mExtra.getVersion() : "";
    }

    /* loaded from: classes.dex */
    private class Extra {
        @SerializedName(value = "force")
        private String mForce;
        @SerializedName(value = "360_guide")
        private boolean mIs360GuideEnabled;
        @SerializedName(value = "360_union")
        private boolean mIs360UnionEnabled;
        @SerializedName(value = "is_search_restricted")
        private boolean mIsSearchRestricted;
        @SerializedName(value = "version")
        private String mVersion;

        private Extra() {
        }

        public String getVersion() {
            return this.mVersion;
        }

        public String getForce() {
            return this.mForce;
        }

        public boolean isSearchRestricted() {
            return this.mIsSearchRestricted;
        }

        public boolean is360GuideEnabled() {
            return this.mIs360GuideEnabled;
        }

        public boolean is360UnoinEnabled() {
            return this.mIs360UnionEnabled;
        }
    }
}
