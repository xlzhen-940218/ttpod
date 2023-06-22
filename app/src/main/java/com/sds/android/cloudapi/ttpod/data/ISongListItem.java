package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
public interface ISongListItem {
    Drawable getIcon();

    int getIconResourceId();

    int getSubItemCount();

    CharSequence getSubtitleName();

    CharSequence getTitleName();
}
