package com.sds.android.ttpod.framework.modules.skin.p129b;

import android.text.TextUtils;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.h */
/* loaded from: classes.dex */
public class SBitmap extends SBase {

    /* renamed from: c */
    protected String f6435c;

    /* renamed from: d */
    private String f6436d;

    /* renamed from: e */
    private String f6437e;

    /* renamed from: f */
    private String f6438f;

    /* renamed from: g */
    private String f6439g;

    /* renamed from: h */
    private String f6440h;

    /* renamed from: i */
    private boolean f6441i;

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SBase
    /* renamed from: a */
    public /* bridge */ /* synthetic */ String mo3794a() {
        return super.mo3794a();
    }

    SBitmap() {
        this.f6441i = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SBitmap(KXmlParser kXmlParser) {
        super(kXmlParser);
        this.f6441i = false;
        this.f6435c = kXmlParser.getAttributeValue(null, "File");
        this.f6436d = kXmlParser.getAttributeValue(null, "Empty");
        this.f6437e = kXmlParser.getAttributeValue(null, "Pressed");
        this.f6438f = kXmlParser.getAttributeValue(null, "Enabled");
        this.f6439g = kXmlParser.getAttributeValue(null, "Checked");
        this.f6440h = kXmlParser.getAttributeValue(null, "Focused");
        m3827b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SBitmap(String str) {
        this.f6441i = false;
        this.f6435c = str;
    }

    /* renamed from: b */
    public void m3827b() {
        this.f6441i = (TextUtils.isEmpty(this.f6436d) && TextUtils.isEmpty(this.f6437e) && TextUtils.isEmpty(this.f6438f) && TextUtils.isEmpty(this.f6439g) && TextUtils.isEmpty(this.f6440h)) ? false : true;
    }

    /* renamed from: c */
    public String m3825c() {
        return this.f6435c;
    }

    /* renamed from: a */
    public void m3828a(String str) {
        this.f6436d = str;
    }

    /* renamed from: d */
    public String m3823d() {
        return this.f6436d;
    }

    /* renamed from: b */
    public void m3826b(String str) {
        this.f6437e = str;
    }

    /* renamed from: e */
    public String m3821e() {
        return this.f6437e;
    }

    /* renamed from: c */
    public void m3824c(String str) {
        this.f6438f = str;
    }

    /* renamed from: f */
    public String m3819f() {
        return this.f6438f;
    }

    /* renamed from: d */
    public void m3822d(String str) {
        this.f6440h = str;
    }

    /* renamed from: g */
    public String m3818g() {
        return this.f6440h;
    }

    /* renamed from: e */
    public void m3820e(String str) {
        this.f6439g = str;
    }

    /* renamed from: h */
    public String m3817h() {
        return this.f6439g;
    }

    /* renamed from: i */
    public boolean m3816i() {
        return this.f6441i;
    }
}
