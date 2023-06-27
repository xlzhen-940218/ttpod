package com.sds.android.ttpod.framework.modules.theme;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.sdk.core.download.TaskInfo;
import com.sds.android.ttpod.framework.TTPodConfig;

import java.io.File;
import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.framework.modules.theme.a */
/* loaded from: classes.dex */
public class BackgroundItem implements Serializable {

    /* renamed from: a */
    protected String f6927a;

    /* renamed from: b */
    private String f6928b;

    /* renamed from: c */
    private String f6929c;

    /* renamed from: d */
    private EnumC2011a f6930d;

    /* renamed from: e */
    private transient Drawable f6931e;

    /* renamed from: f */
    private Bitmap f6932f;

    /* renamed from: g */
    private Bitmap f6933g;

    /* renamed from: h */
    private OnlineSkinItem f6934h;

    /* renamed from: i */
    private TaskInfo f6935i;

    /* renamed from: j */
    private long f6936j;

    /* compiled from: BackgroundItem.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.theme.a$a */
    /* loaded from: classes.dex */
    public enum EnumC2011a {
        ORIGINAL,
        ADD_BY_USER,
        ADD_VIEW,
        FOLLOW_SKIN,
        ONLINE_BACKGROUND
    }

    /* renamed from: a */
    public EnumC2011a m3337a() {
        return this.f6930d;
    }

    /* renamed from: a */
    public void m3333a(EnumC2011a enumC2011a) {
        this.f6930d = enumC2011a;
    }

    /* renamed from: b */
    public String m3331b() {
        return this.f6929c;
    }

    /* renamed from: c */
    public OnlineSkinItem m3330c() {
        return this.f6934h;
    }

    /* renamed from: d */
    public TaskInfo m3329d() {
        return this.f6935i;
    }

    /* renamed from: a */
    public void m3334a(TaskInfo taskInfo) {
        this.f6935i = taskInfo;
    }

    public BackgroundItem(String str, EnumC2011a enumC2011a) {
        this.f6929c = str;
        this.f6930d = enumC2011a;
    }

    public BackgroundItem(OnlineSkinItem onlineSkinItem) {
        this.f6929c = onlineSkinItem.getName() + ".jpg";
        this.f6930d = EnumC2011a.ONLINE_BACKGROUND;
        this.f6927a = TTPodConfig.m5295m() + File.separator + this.f6929c;
        this.f6934h = onlineSkinItem;
        this.f6936j = onlineSkinItem.getDateCreated();
    }

    public BackgroundItem(String str) {
        this.f6928b = str;
        if (str != null) {
            this.f6929c = str.substring(str.lastIndexOf(File.separator) + 1);
            if (str.startsWith("assets://")) {
                this.f6930d = EnumC2011a.ORIGINAL;
            } else if (str.startsWith("file://")) {
                this.f6930d = EnumC2011a.ADD_BY_USER;
            } else if (str.startsWith("follow_skin")) {
                this.f6930d = EnumC2011a.FOLLOW_SKIN;
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

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.f6930d == EnumC2011a.ORIGINAL) {
            stringBuffer.append("assets://");
            stringBuffer.append(TTPodConfig.m5292p());
        } else if (this.f6930d == EnumC2011a.ADD_BY_USER) {
            stringBuffer.append("file://");
            stringBuffer.append(TTPodConfig.getBkgsPath());
        } else if (this.f6930d == EnumC2011a.FOLLOW_SKIN) {
            stringBuffer.append("follow_skin");
            return stringBuffer.toString();
        }
        stringBuffer.append(File.separator);
        stringBuffer.append(this.f6929c);
        return stringBuffer.toString();
    }

    /* renamed from: h */
    public String m3325h() {
        return this.f6927a;
    }

    /* renamed from: a */
    public void m3332a(String str) {
        this.f6929c = str + ".jpg";
        this.f6927a = TTPodConfig.m5295m() + File.separator + this.f6929c;
    }

    /* renamed from: i */
    public long m3324i() {
        return this.f6936j;
    }

    public int hashCode() {
        if (this.f6929c != null) {
            return this.f6929c.hashCode();
        }
        return 0;
    }

    public boolean equals(Object obj) {
        return (obj instanceof BackgroundItem) && hashCode() == obj.hashCode();
    }
}
