package com.sds.android.cloudapi.ttpod.result;




import com.google.gson.annotations.SerializedName;
import com.sds.android.cloudapi.ttpod.data.ForwardAction;
import com.sds.android.sdk.lib.request.ExtraDataListResultMock;

/* loaded from: classes.dex */
public class StyleDataListResult<D> extends ExtraDataListResultMock<D> {
    @SerializedName(value = "_id")
    private long mId;
    @SerializedName(value = "order")
    private int mOrder;
    @SerializedName(value = "style")
    private int mStyle = Integer.MIN_VALUE;
    @SerializedName(value = "name")
    private String mName = "";
    @SerializedName(value = "action")
    private ForwardAction mForwardAction = new ForwardAction();

    public long getId() {
        return this.mId;
    }

    public int getOrder() {
        return this.mOrder;
    }

    public int getStyle() {
        return this.mStyle;
    }

    public String getName() {
        return this.mName;
    }

    public ForwardAction getForwardAction() {
        return this.mForwardAction;
    }
}
