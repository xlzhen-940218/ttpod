package com.sds.android.ttpod.framework.modules.theme;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.sdk.core.download.TaskInfo;
import com.sds.android.ttpod.framework.TTPodConfig;

import java.io.File;
import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.framework.modules.theme.a */
/* loaded from: classes.dex */
public class BackgroundItem implements Serializable {

    /* renamed from: a */
    protected String imagePath;

    /* renamed from: b */
    private String f6928b;

    /* renamed from: c */
    private String imageName;

    /* renamed from: d */
    private ResourceTypeEnum resourceTypeEnum;

    /* renamed from: e */
    private transient Drawable f6931e;

    /* renamed from: f */
    private Bitmap f6932f;

    /* renamed from: g */
    private Bitmap f6933g;

    /* renamed from: h */
    private OnlineSkinItem onlineSkinItem;

    /* renamed from: i */
    private TaskInfo taskInfo;

    /* renamed from: j */
    private long dateCreated;

    /* compiled from: BackgroundItem.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.theme.a$a */
    /* loaded from: classes.dex */
    public enum ResourceTypeEnum {
        ORIGINAL,
        ADD_BY_USER,
        ADD_VIEW,
        FOLLOW_SKIN,
        ONLINE_BACKGROUND
    }

    /* renamed from: a */
    public ResourceTypeEnum getResourceTypeEnum() {
        return this.resourceTypeEnum;
    }

    /* renamed from: a */
    public void setResourceTypeEnum(ResourceTypeEnum resourceTypeEnum) {
        this.resourceTypeEnum = resourceTypeEnum;
    }

    /* renamed from: b */
    public String getImageName() {
        return this.imageName;
    }

    /* renamed from: c */
    public OnlineSkinItem getOnlineSkinItem() {
        return this.onlineSkinItem;
    }

    /* renamed from: d */
    public TaskInfo getTaskInfo() {
        return this.taskInfo;
    }

    /* renamed from: a */
    public void setTaskInfo(TaskInfo taskInfo) {
        this.taskInfo = taskInfo;
    }

    public BackgroundItem(String str, ResourceTypeEnum resourceTypeEnum) {
        this.imageName = str;
        this.resourceTypeEnum = resourceTypeEnum;
    }

    public BackgroundItem(OnlineSkinItem onlineSkinItem) {
        this.imageName = onlineSkinItem.getName() + ".jpg";
        this.resourceTypeEnum = ResourceTypeEnum.ONLINE_BACKGROUND;
        this.imagePath = TTPodConfig.getBkgs() + File.separator + this.imageName;
        this.onlineSkinItem = onlineSkinItem;
        this.dateCreated = onlineSkinItem.getDateCreated();
    }

    public BackgroundItem(String str) {
        this.f6928b = str;
        if (str != null) {
            this.imageName = str.substring(str.lastIndexOf(File.separator) + 1);
            if (str.startsWith("assets://")) {
                this.resourceTypeEnum = ResourceTypeEnum.ORIGINAL;
            } else if (str.startsWith("file://")) {
                this.resourceTypeEnum = ResourceTypeEnum.ADD_BY_USER;
            } else if (str.startsWith("follow_skin")) {
                this.resourceTypeEnum = ResourceTypeEnum.FOLLOW_SKIN;
            }
        }
    }

    /* renamed from: a */
    public void m3335a(Drawable drawable) {
        this.f6931e = drawable;
    }

    /* renamed from: e */
    public Drawable m3328e() {
        return this.f6931e;
    }

    /* renamed from: f */
    public Bitmap m3327f() {
        return this.f6932f;
    }

    /* renamed from: a */
    public void m3336a(Bitmap bitmap) {
        this.f6933g = bitmap;
    }

    /* renamed from: g */
    public Bitmap m3326g() {
        return this.f6933g;
    }

    @NonNull
    public String toString() {
        StringBuilder stringBuffer = new StringBuilder();
        if (this.resourceTypeEnum == ResourceTypeEnum.ORIGINAL) {
            stringBuffer.append("assets://");
            stringBuffer.append(TTPodConfig.m5292p());
        } else if (this.resourceTypeEnum == ResourceTypeEnum.ADD_BY_USER) {
            stringBuffer.append("file://");
            stringBuffer.append(TTPodConfig.getBkgsPath());
        } else if (this.resourceTypeEnum == ResourceTypeEnum.FOLLOW_SKIN) {
            stringBuffer.append("follow_skin");
            return stringBuffer.toString();
        }
        stringBuffer.append(File.separator);
        stringBuffer.append(this.imageName);
        return stringBuffer.toString();
    }

    /* renamed from: h */
    public String getImagePath() {
        return this.imagePath;
    }

    /* renamed from: a */
    public void setImagePath(String str) {
        this.imageName = str + ".jpg";
        this.imagePath = TTPodConfig.getBkgs() + File.separator + this.imageName;
    }

    /* renamed from: i */
    public long getDateCreated() {
        return this.dateCreated;
    }

    public int hashCode() {
        if (this.imageName != null) {
            return this.imageName.hashCode();
        }
        return 0;
    }

    public boolean equals(Object obj) {
        return (obj instanceof BackgroundItem) && hashCode() == obj.hashCode();
    }
}
