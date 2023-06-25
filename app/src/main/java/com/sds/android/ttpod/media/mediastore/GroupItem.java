package com.sds.android.ttpod.media.mediastore;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import com.sds.android.cloudapi.ttpod.data.ISongListItem;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import java.io.Serializable;

/* loaded from: classes.dex */
public class GroupItem implements Parcelable, ISongListItem, Serializable {
    public static final Parcelable.Creator<GroupItem> CREATOR = new Parcelable.Creator<GroupItem>() { // from class: com.sds.android.ttpod.media.mediastore.GroupItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GroupItem createFromParcel(Parcel parcel) {
            return new GroupItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GroupItem[] newArray(int i) {
            return new GroupItem[i];
        }
    };
    private Integer mCount;
    private String mGroupID;
    private String mName;
    private char mNameIndexKey;

    private GroupItem(Parcel parcel) {
        this.mName = parcel.readString();
        this.mGroupID = parcel.readString();
        this.mCount = Integer.valueOf(parcel.readInt());
    }

    public GroupItem(String str, String str2, Integer num) {
        this.mName = str;
        this.mGroupID = str2;
        this.mCount = num;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
        this.mNameIndexKey = (char) 0;
    }

    public char getNameIndexKey() {
        char charAt;
        if (this.mNameIndexKey == 0) {
            this.mNameIndexKey = '#';
            if (this.mName != null && !StringUtils.m8344a(this.mName, "<unknown>")) {
                String buildKey = PinyinUtils.buildKey(this.mGroupID.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX) ? FileUtils.getFilename(this.mName) : this.mName);
                if (!StringUtils.isEmpty(buildKey) && (charAt = buildKey.charAt(0)) >= 'A' && charAt <= 'Z') {
                    this.mNameIndexKey = charAt;
                }
            }
        }
        return this.mNameIndexKey;
    }

    public String getGroupID() {
        return this.mGroupID;
    }

    public Integer getCount() {
        return this.mCount;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GroupItem groupItem = (GroupItem) obj;
        if (this.mGroupID != null) {
            if (this.mGroupID.equals(groupItem.mGroupID)) {
                return true;
            }
        } else if (groupItem.mGroupID == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.mGroupID != null) {
            return this.mGroupID.hashCode();
        }
        return 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mGroupID);
        parcel.writeInt(this.mCount.intValue());
    }

    @Override // com.sds.android.cloudapi.ttpod.data.ISongListItem
    public CharSequence getTitleName() {
        return this.mName;
    }

    @Override // com.sds.android.cloudapi.ttpod.data.ISongListItem
    public CharSequence getSubtitleName() {
        return null;
    }

    @Override // com.sds.android.cloudapi.ttpod.data.ISongListItem
    public int getIconResourceId() {
        return 0;
    }

    @Override // com.sds.android.cloudapi.ttpod.data.ISongListItem
    public Drawable getIcon() {
        return null;
    }

    @Override // com.sds.android.cloudapi.ttpod.data.ISongListItem
    public int getSubItemCount() {
        return this.mCount.intValue();
    }
}
