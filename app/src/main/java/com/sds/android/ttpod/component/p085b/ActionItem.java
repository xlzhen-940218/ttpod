package com.sds.android.ttpod.component.p085b;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.sds.android.cloudapi.ttpod.data.ISongListItem;
import com.sds.android.ttpod.framework.base.BaseApplication;

/* renamed from: com.sds.android.ttpod.component.b.a */
/* loaded from: classes.dex */
public class ActionItem implements ISongListItem {

    /* renamed from: a */
    private int f3811a;

    /* renamed from: b */
    private int f3812b;

    /* renamed from: c */
    private CharSequence f3813c;

    /* renamed from: d */
    private CharSequence name;

    /* renamed from: e */
    private Drawable f3815e;

    /* renamed from: f */
    private Object f3816f;

    /* renamed from: g */
    private int f3817g;

    /* renamed from: h */
    private int f3818h;

    /* renamed from: i */
    private EnumC1134a f3819i;

    /* renamed from: j */
    private Resources f3820j;

    /* compiled from: ActionItem.java */
    /* renamed from: com.sds.android.ttpod.component.b.a$a */
    /* loaded from: classes.dex */
    public enum EnumC1134a {
        NO_ICON,
        LEFT_ICON,
        RIGHT_ICON,
        TITLE_ICON
    }

    /* compiled from: ActionItem.java */
    /* renamed from: com.sds.android.ttpod.component.b.a$b */
    /* loaded from: classes.dex */
    public interface InterfaceC1135b {
        /* renamed from: a */
        void mo5409a(ActionItem actionItem, int i);
    }

    public ActionItem(int i, int i2, int i3) {
        this.f3819i = EnumC1134a.LEFT_ICON;
        this.f3820j = BaseApplication.getApplication().getResources();
        this.f3811a = i;
        if (i2 != 0) {
            this.f3812b = i2;
            this.f3815e = this.f3820j.getDrawable(i2);
        }
        if (i3 != 0) {
            this.name = this.f3820j.getText(i3, "");
        }
    }

    public ActionItem(int i, int i2, int i3, int i4) {
        this(i, i2, i3);
        this.f3818h = i4;
    }

    public ActionItem(int i, Drawable drawable, int i2) {
        this.f3819i = EnumC1134a.LEFT_ICON;
        this.f3820j = BaseApplication.getApplication().getResources();
        this.f3811a = i;
        this.f3815e = drawable;
        if (i2 != 0) {
            this.name = m7002h().getText(i2);
        }
    }

    public ActionItem(int i, int i2, CharSequence charSequence) {
        this.f3819i = EnumC1134a.LEFT_ICON;
        this.f3820j = BaseApplication.getApplication().getResources();
        this.f3811a = i;
        this.name = charSequence;
        if (i2 != 0) {
            this.f3815e = m7002h().getDrawable(i2);
        }
    }

    public ActionItem(int i, int i2, CharSequence charSequence, CharSequence charSequence2) {
        this(i, i2, charSequence);
        this.f3813c = charSequence2;
    }

    /* renamed from: d */
    public CharSequence m7006d() {
        return this.name;
    }

    /* renamed from: e */
    public int m7005e() {
        return this.f3811a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public <T extends ActionItem> T m7009a(Object obj) {
        this.f3816f = obj;
        return (T) this;
    }

    /* renamed from: f */
    public Object m7004f() {
        return this.f3816f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    public <T extends ActionItem> T m7007b(Drawable drawable) {
        this.f3815e = drawable;
        return (T) this;
    }

    @Override // com.sds.android.cloudapi.ttpod.data.ISongListItem
    public CharSequence getTitleName() {
        return this.name;
    }

    @Override // com.sds.android.cloudapi.ttpod.data.ISongListItem
    public CharSequence getSubtitleName() {
        return this.f3813c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public <T extends ActionItem> T m7012a(int i) {
        this.f3818h = i;
        return (T)  this;
    }

    @Override // com.sds.android.cloudapi.ttpod.data.ISongListItem
    public int getIconResourceId() {
        return this.f3812b;
    }

    @Override // com.sds.android.cloudapi.ttpod.data.ISongListItem
    public Drawable getIcon() {
        return this.f3815e;
    }

    @Override // com.sds.android.cloudapi.ttpod.data.ISongListItem
    public int getSubItemCount() {
        return this.f3817g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    public <T extends ActionItem> T m7008b(int i) {
        if (i != 0) {
            this.f3813c = m7002h().getText(i);
        }
        return (T) this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public <T extends ActionItem> T m7010a(CharSequence charSequence) {
        this.f3813c = charSequence;
        return (T) this;
    }

    /* renamed from: g */
    public CharSequence m7003g() {
        return this.f3813c;
    }

    /* renamed from: h */
    public Resources m7002h() {
        return this.f3820j;
    }

    /* renamed from: i */
    public int m7001i() {
        return this.f3818h;
    }

    /* renamed from: j */
    public EnumC1134a m7000j() {
        return this.f3819i;
    }

    /* renamed from: a */
    public void m7011a(EnumC1134a enumC1134a) {
        this.f3819i = enumC1134a;
    }

    public String toString() {
        return this.name.toString();
    }
}
