package com.sds.android.ttpod.framework.modules.skin;

import androidx.annotation.NonNull;

import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.modules.skin.serialskin.SSkinInfo;
import java.io.File;
import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.m */
/* loaded from: classes.dex */
public class SkinItem implements Serializable {

    /* renamed from: a */
    protected int type;

    /* renamed from: b */
    protected String title;

    /* renamed from: c */
    protected String path;

    /* renamed from: d */
    protected String fileName;

    /* renamed from: e */
    protected String version;

    /* renamed from: f */
    protected String brand;

    /* renamed from: g */
    protected long dateCreated;

    /* renamed from: h */
    protected OnlineSkinItem onlineSkinItem;

    public SkinItem(OnlineSkinItem onlineSkinItem) {
        this.type = 4;
        this.title = onlineSkinItem.getName();
        this.path = TTPodConfig.getSkinPath() + File.separator + onlineSkinItem.getName() + ".tsk";
        this.brand = onlineSkinItem.getAuthor();
        this.version = onlineSkinItem.getVersionNumber();
        this.onlineSkinItem = onlineSkinItem;
        this.dateCreated = onlineSkinItem.getDateCreated();
    }

    public SkinItem(SkinItem skinItem) {
        this.type = 0;
        this.title = skinItem.getTitle();
        this.path = TTPodConfig.getSkinPath() + File.separator + skinItem.getTitle() + ".tsk";
        this.brand = skinItem.getBrand();
        this.version = skinItem.getVersion();
        this.dateCreated = skinItem.getDateCreated();
        this.fileName = skinItem.getFileName();
    }

    public SkinItem(String str, int i) {
        checkTypeTrue(i);
        this.path = str;
        this.type = i;
    }

    /* renamed from: a */
    public void setSkinInfo(SSkinInfo sSkinInfo) {
        if (sSkinInfo != null) {
            this.brand = sSkinInfo.getAuthor();
            this.version = sSkinInfo.getVersion();
            this.title = sSkinInfo.getName();
            this.dateCreated = sSkinInfo.getDateCreated();
        }
    }

    /* renamed from: a */
    public int getType() {
        return this.type;
    }

    /* renamed from: a */
    public void setType(int type) {
        checkTypeTrue(type);
        this.type = type;
    }

    /* renamed from: b */
    public String getPath() {
        return this.path;
    }

    /* renamed from: c */
    public String getBrand() {
        return this.brand;
    }

    /* renamed from: d */
    public String getVersion() {
        return this.version;
    }

    /* renamed from: e */
    public long getDateCreated() {
        return this.dateCreated;
    }

    /* renamed from: f */
    public OnlineSkinItem getOnlineSkinItem() {
        return this.onlineSkinItem;
    }

    /* renamed from: g */
    public String getTitle() {
        if (this.title == null) {
            this.title = getFileName();
        }
        return this.title;
    }

    /* renamed from: h */
    public String getFileName() {
        if (this.fileName == null) {
            if (2 == this.type) {
                int i = -1;
                if (this.path != null) {
                    i = this.path.lastIndexOf(46);
                }
                this.fileName = i < 0 ? this.path : this.path.substring(i + 1);
            } else {
                this.fileName = FileUtils.m8401k(this.path);
            }
        }
        return this.fileName;
    }

    /* renamed from: b */
    private void checkTypeTrue(int i) {
        if (i < 0 || i > 4) {
            throw new IllegalArgumentException("the skin type is invalid");
        }
    }

    @NonNull
    public String toString() {
        return " name: " + this.title +
                " fileName: " + this.fileName +
                " path: " + this.path +
                " type: " + this.type;
    }

    /* renamed from: a */
    public boolean equal(SkinItem skinItem) {
        if (this.title == null || this.version == null) {
            return false;
        }
        boolean checkTitleAndVersionAndType = this.title.equals(skinItem.title) && this.version.equals(skinItem.version) && this.type == skinItem.type;
        if (checkTitleAndVersionAndType) {
            OnlineSkinItem onlineSkinItem = skinItem.getOnlineSkinItem();
            if (onlineSkinItem != null) {
                if (this.onlineSkinItem != null) {
                    return onlineSkinItem.getPictureUrl().equals(this.onlineSkinItem.getPictureUrl());
                }
                return false;
            }
            return true;
        }
        return false;
    }

    /* renamed from: i */
    public String getSkinKey() {
        return (getTitle() + getBrand()).toLowerCase();
    }

    public int hashCode() {
        if (this.fileName != null) {
            return this.fileName.hashCode();
        }
        return 0;
    }
}
