package com.sds.android.ttpod.framework.modules.skin;

import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.modules.skin.p129b.SSkinInfo;
import java.io.File;
import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.m */
/* loaded from: classes.dex */
public class SkinItem implements Serializable {

    /* renamed from: a */
    protected int f6658a;

    /* renamed from: b */
    protected String title;

    /* renamed from: c */
    protected String path;

    /* renamed from: d */
    protected String f6661d;

    /* renamed from: e */
    protected String version;

    /* renamed from: f */
    protected String brand;

    /* renamed from: g */
    protected long f6664g;

    /* renamed from: h */
    protected OnlineSkinItem onlineSkinItem;

    public SkinItem(OnlineSkinItem onlineSkinItem) {
        this.f6658a = 4;
        this.title = onlineSkinItem.getName();
        this.path = TTPodConfig.getSkinPath() + File.separator + onlineSkinItem.getName() + ".tsk";
        this.brand = onlineSkinItem.getAuthor();
        this.version = onlineSkinItem.getVersionNumber();
        this.onlineSkinItem = onlineSkinItem;
        this.f6664g = onlineSkinItem.getDateCreated();
    }

    public SkinItem(SkinItem skinItem) {
        this.f6658a = 0;
        this.title = skinItem.m3565g();
        this.path = TTPodConfig.getSkinPath() + File.separator + skinItem.m3565g() + ".tsk";
        this.brand = skinItem.m3569c();
        this.version = skinItem.m3568d();
        this.f6664g = skinItem.m3567e();
        this.f6661d = skinItem.m3564h();
    }

    public SkinItem(String str, int i) {
        m3570b(i);
        this.path = str;
        this.f6658a = i;
    }

    /* renamed from: a */
    public void m3573a(SSkinInfo sSkinInfo) {
        if (sSkinInfo != null) {
            this.brand = sSkinInfo.m3781a();
            this.version = sSkinInfo.m3780b();
            this.title = sSkinInfo.m3778d();
            this.f6664g = sSkinInfo.m3779c();
        }
    }

    /* renamed from: a */
    public int m3575a() {
        return this.f6658a;
    }

    /* renamed from: a */
    public void m3574a(int i) {
        m3570b(i);
        this.f6658a = i;
    }

    /* renamed from: b */
    public String getPath() {
        return this.path;
    }

    /* renamed from: c */
    public String m3569c() {
        return this.brand;
    }

    /* renamed from: d */
    public String m3568d() {
        return this.version;
    }

    /* renamed from: e */
    public long m3567e() {
        return this.f6664g;
    }

    /* renamed from: f */
    public OnlineSkinItem m3566f() {
        return this.onlineSkinItem;
    }

    /* renamed from: g */
    public String m3565g() {
        if (this.title == null) {
            this.title = m3564h();
        }
        return this.title;
    }

    /* renamed from: h */
    public String m3564h() {
        if (this.f6661d == null) {
            if (2 == this.f6658a) {
                int i = -1;
                if (this.path != null) {
                    i = this.path.lastIndexOf(46);
                }
                this.f6661d = i < 0 ? this.path : this.path.substring(i + 1);
            } else {
                this.f6661d = FileUtils.m8401k(this.path);
            }
        }
        return this.f6661d;
    }

    /* renamed from: b */
    private void m3570b(int i) {
        if (i < 0 || i > 4) {
            throw new IllegalArgumentException("the skin type is invalid");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" name: " + this.title);
        sb.append(" fileName: " + this.f6661d);
        sb.append(" path: " + this.path);
        sb.append(" type: " + this.f6658a);
        return sb.toString();
    }

    /* renamed from: a */
    public boolean m3572a(SkinItem skinItem) {
        if (this.title == null || this.version == null) {
            return false;
        }
        boolean z = this.title.equals(skinItem.title) && this.version.equals(skinItem.version) && this.f6658a == skinItem.f6658a;
        if (z) {
            OnlineSkinItem m3566f = skinItem.m3566f();
            if (m3566f != null) {
                if (this.onlineSkinItem != null) {
                    return m3566f.getPictureUrl().equals(this.onlineSkinItem.getPictureUrl());
                }
                return false;
            }
            return z;
        }
        return false;
    }

    /* renamed from: i */
    public String m3563i() {
        return (m3565g() + m3569c()).toLowerCase();
    }

    public int hashCode() {
        if (this.f6661d != null) {
            return this.f6661d.hashCode();
        }
        return 0;
    }
}
