package com.sds.android.sdk.lib.request;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* renamed from: com.sds.android.sdk.lib.request.b */
/* loaded from: classes.dex */
public class DataListResult<D> extends BaseResult {
    @SerializedName(value = "data")
    private ArrayList<D> mDataList = new ArrayList<>();

    public ArrayList<D> getDataList() {
        return this.mDataList;
    }

    public void setDataList(ArrayList<D> mDataList) {
        this.mDataList = mDataList;
    }

    public boolean isDataListEmpty() {
        return this.mDataList.size() == 0;
    }
}
