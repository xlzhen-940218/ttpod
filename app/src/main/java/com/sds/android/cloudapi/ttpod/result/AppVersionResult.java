package com.sds.android.cloudapi.ttpod.result;


import com.google.gson.annotations.SerializedName;
import com.sds.android.sdk.lib.request.Extra;
import com.sds.android.sdk.lib.request.ExtraDataListResult;
import com.sds.android.sdk.lib.util.StringUtils;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AppVersionResult extends ExtraDataListResult<AppVersionResult.UpdateData> {

    /* loaded from: classes.dex */
    public static class UpdateData {
        @SerializedName(value = "update_url")
        private String mUrl = "";
        @SerializedName(value = "update_info")
        private String mDescription = "";
    }

    public UpdateData getUpdateData() {
        ArrayList<UpdateData> dataList = getDataList();
        if (dataList.size() == 1) {
            return dataList.get(0);
        }
        return null;
    }

    public boolean isUpdateMandatory() {
        return (getExtra() == null || StringUtils.m8346a(getExtra().m8557a()) || Integer.parseInt(getExtra().m8557a()) == 0) ? false : true;
    }

    public String getLatestVersion() {
        Object m8555c;
        Extra extra = getExtra();
        return (extra == null || (m8555c = extra.m8555c()) == null || !(m8555c instanceof String)) ? "" : (String) m8555c;
    }

    public String getUpdateUrl() {
        ArrayList<UpdateData> dataList = getDataList();
        return dataList.size() == 1 ? dataList.get(0).mUrl : "";
    }

    public String getUpdateDescription() {
        ArrayList<UpdateData> dataList = getDataList();
        return dataList.size() == 1 ? dataList.get(0).mDescription : "";
    }
}
