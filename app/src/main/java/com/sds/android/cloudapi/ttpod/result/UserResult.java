package com.sds.android.cloudapi.ttpod.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.sdk.lib.request.DataResult;

/* loaded from: classes.dex */
public class UserResult extends DataResult<User> implements Parcelable {
    public static final Parcelable.Creator<UserResult> CREATOR = new Parcelable.Creator<UserResult>() { // from class: com.sds.android.cloudapi.ttpod.result.UserResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserResult createFromParcel(Parcel parcel) {
            return new UserResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserResult[] newArray(int i) {
            return new UserResult[i];
        }
    };

    public UserResult() {
    }

    private UserResult(Parcel parcel) {
        setCode(parcel.readInt());
        setMessage(parcel.readString());
        setData(new User());
        getData().setAccessToken(parcel.readString());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getCode());
        parcel.writeString(getMessage());
        parcel.writeString(getData().getAccessToken());
    }
}
