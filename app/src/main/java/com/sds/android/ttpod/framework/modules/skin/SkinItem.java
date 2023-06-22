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
    protected String f6659b;

    /* renamed from: c */
    protected String f6660c;

    /* renamed from: d */
    protected String f6661d;

    /* renamed from: e */
    protected String f6662e;

    /* renamed from: f */
    protected String f6663f;

    /* renamed from: g */
    protected long f6664g;

    /* renamed from: h */
    protected OnlineSkinItem f6665h;

    public SkinItem(OnlineSkinItem onlineSkinItem) {
        this.f6658a = 4;
        this.f6659b = onlineSkinItem.getName();
        this.f6660c = TTPodConfig.m5294n() + File.separator + onlineSkinItem.getName() + ".tsk";
        this.f6663f = onlineSkinItem.getAuthor();
        this.f6662e = onlineSkinItem.getVersionNumber();
        this.f6665h = onlineSkinItem;
        this.f6664g = onlineSkinItem.getDateCreated();
    }

    public SkinItem(SkinItem skinItem) {
        this.f6658a = 0;
        this.f6659b = skinItem.m3565g();
        this.f6660c = TTPodConfig.m5294n() + File.separator + skinItem.m3565g() + ".tsk";
        this.f6663f = skinItem.m3569c();
        this.f6662e = skinItem.m3568d();
        this.f6664g = skinItem.m3567e();
        this.f6661d = skinItem.m3564h();
    }

    public SkinItem(String str, int i) {
        m3570b(i);
        this.f6660c = str;
        this.f6658a = i;
    }

    /* renamed from: a */
    public void m3573a(SSkinInfo sSkinInfo) {
        if (sSkinInfo != null) {
            this.f6663f = sSkinInfo.m3781a();
            this.f6662e = sSkinInfo.m3780b();
            this.f6659b = sSkinInfo.m3778d();
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
    public String m3571b() {
        return this.f6660c;
    }

    /* renamed from: c */
    public String m3569c() {
        return this.f6663f;
    }

    /* renamed from: d */
    public String m3568d() {
        return this.f6662e;
    }

    /* renamed from: e */
    public long m3567e() {
        return this.f6664g;
    }

    /* renamed from: f */
    public OnlineSkinItem m3566f() {
        return this.f6665h;
    }

    /* renamed from: g */
    public String m3565g() {
        if (this.f6659b == null) {
            this.f6659b = m3564h();
        }
        return this.f6659b;
    }

    /* renamed from: h */
    public String m3564h() {
        if (this.f6661d == null) {
            if (2 == this.f6658a) {
                int i = -1;
                if (this.f6660c != null) {
                    i = this.f6660c.lastIndexOf(46);
                }
                this.f6661d = i < 0 ? this.f6660c : this.f6660c.substring(i + 1);
            } else {
                this.f6661d = FileUtils.m8401k(this.f6660c);
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
        sb.append(" name: " + this.f6659b);
        sb.append(" fileName: " + this.f6661d);
        sb.append(" path: " + this.f6660c);
        sb.append(" type: " + this.f6658a);
        return sb.toString();
    }

    /* renamed from: a */
    public boolean m3572a(SkinItem skinItem) {
        if (this.f6659b == null || this.f6662e == null) {
            return false;
        }
        boolean z = this.f6659b.equals(skinItem.f6659b) && this.f6662e.equals(skinItem.f6662e) && this.f6658a == skinItem.f6658a;
        if (z) {
            OnlineSkinItem m3566f = skinItem.m3566f();
            if (m3566f != null) {
                if (this.f6665h != null) {
                    return m3566f.getPictureUrl().equals(this.f6665h.getPictureUrl());
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
