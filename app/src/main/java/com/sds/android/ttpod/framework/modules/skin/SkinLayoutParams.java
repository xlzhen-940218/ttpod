package com.sds.android.ttpod.framework.modules.skin;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.n */
/* loaded from: classes.dex */
public class SkinLayoutParams extends ViewGroup.MarginLayoutParams {

    /* renamed from: a */
    private int f6666a;

    /* renamed from: b */
    private int zOrder;

    /* renamed from: c */
    private int f6668c;

    /* renamed from: d */
    private int f6669d;

    /* renamed from: e */
    private int f6670e;

    /* renamed from: f */
    private int f6671f;

    /* renamed from: g */
    private int left;

    /* renamed from: h */
    private int top;

    /* renamed from: i */
    private int paddingLeft;

    /* renamed from: j */
    private int paddingRight;

    /* renamed from: k */
    private int paddingTop;

    /* renamed from: l */
    private int paddingBottom;

    /* renamed from: m */
    private int f6678m;

    /* renamed from: a */
    public static SkinLayoutParams m3559a(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof SkinLayoutParams) {
            return (SkinLayoutParams) layoutParams;
        }
        return null;
    }

    /* renamed from: a */
    public static void m3557a(ViewGroup viewGroup, int i, int i2) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            SkinLayoutParams m3559a = m3559a(childAt);
            if (m3559a != null) {
                m3559a.mo3558a(childAt, i, i2);
                if (childAt instanceof ViewGroup) {
                    m3557a((ViewGroup) childAt, m3559a.m3551d() - m3559a.m3556b(), m3559a.m3549e() - m3559a.m3553c());
                }
            }
        }
    }

    /* renamed from: b */
    public static boolean m3554b(View view) {
        SkinLayoutParams m3559a;
        if (view.getVisibility() == View.GONE || (m3559a = m3559a(view)) == null) {
            return false;
        }
        view.layout(m3559a.m3556b(), m3559a.m3553c(), m3559a.m3551d(), m3559a.m3549e());
        return true;
    }

    public SkinLayoutParams(KXmlParser kXmlParser, int i) {
        super(0, 0);
        this.f6666a = 0;
        this.f6678m = -1;
        Rect position = ValueParser.xmlToRect(kXmlParser.getAttributeValue(null, "Position"), (Rect) null);
        if (position != null) {
            this.left = position.left;
            this.top = position.top;
            this.width = position.right;
            this.height = position.bottom;
        }
        String positionAnchor = kXmlParser.getAttributeValue(null, "PositionAnchor");
        if (positionAnchor != null) {
            String trim = positionAnchor.trim();
            if (trim.equals("Center") || trim.contains("Center|") || trim.contains("Center ")) {
                this.f6666a = 1;
            } else {
                if (trim.contains("Right")) {
                    this.f6666a |= 8;
                } else if (trim.contains("CenterHorizontal")) {
                    this.f6666a |= 2;
                }
                if (trim.contains("Bottom")) {
                    this.f6666a |= 16;
                } else if (trim.contains("CenterVertical")) {
                    this.f6666a |= 4;
                }
            }
        }
        Rect rect = ValueParser.xmlToRect(kXmlParser.getAttributeValue(null, "Padding"), (Rect) null);
        if (i > 0) {
            if (rect != null) {
                this.paddingLeft = rect.left;
                this.paddingTop = rect.top;
                this.paddingRight = rect.right;
                this.paddingBottom = rect.bottom;
            }
            rect = ValueParser.xmlToRect(kXmlParser.getAttributeValue(null, "Margin"), (Rect) null);
        }
        if (rect != null) {
            this.leftMargin = rect.left;
            this.topMargin = rect.top;
            this.rightMargin = rect.right;
            this.bottomMargin = rect.bottom;
        }
        this.zOrder = ValueParser.parseInt(kXmlParser.getAttributeValue(null, "ZOrder"), 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3558a(View view, int i, int i2) {
        int i3;
        int i4;
        m3561a(ValueParser.getSize(this.left, i));
        m3555b(ValueParser.getSize(this.top, i2));
        m3552c(m3556b() + ValueParser.getSize(this.width, i));
        m3550d(m3553c() + ValueParser.getSize(this.height, i2));
        int m3562a = m3562a();
        int m3551d = m3551d() - m3556b();
        int m3549e = m3549e() - m3553c();
        if ((m3562a & 1) == 1) {
            i3 = -(m3551d >> 1);
            i4 = -(m3549e >> 1);
        } else {
            if ((m3562a & 8) == 8) {
                i3 = -m3551d;
            } else {
                i3 = (m3562a & 2) == 2 ? -(m3551d >> 1) : 0;
            }
            if ((m3562a & 16) == 16) {
                i4 = -m3549e;
            } else {
                i4 = (m3562a & 4) == 4 ? -(m3549e >> 1) : 0;
            }
        }
        view.setTag(R.id.tag_layout_offset, new int[]{i3, i4});
        m3560a(i3, i4);
        m3561a(m3556b() + ValueParser.getSize(this.leftMargin, i));
        m3555b(m3553c() + ValueParser.getSize(this.topMargin, i2));
        m3552c(m3551d() - ValueParser.getSize(this.rightMargin, i));
        m3550d(m3549e() - ValueParser.getSize(this.bottomMargin, i2));
        view.setPadding(ValueParser.getSize(this.paddingLeft, m3551d), ValueParser.getSize(this.paddingTop, m3549e), ValueParser.getSize(this.paddingRight, m3551d), ValueParser.getSize(this.paddingBottom, m3549e));
        if (this.f6678m >= 0) {
            m3560a(this.f6678m * i, 0);
        }
    }

    /* renamed from: a */
    public int m3562a() {
        return this.f6666a;
    }

    /* renamed from: a */
    public void m3561a(int i) {
        this.f6668c = i;
    }

    /* renamed from: b */
    public void m3555b(int i) {
        this.f6669d = i;
    }

    /* renamed from: c */
    public void m3552c(int i) {
        this.f6670e = i;
    }

    /* renamed from: d */
    public void m3550d(int i) {
        this.f6671f = i;
    }

    /* renamed from: b */
    public int m3556b() {
        return this.f6668c;
    }

    /* renamed from: c */
    public int m3553c() {
        return this.f6669d;
    }

    /* renamed from: d */
    public int m3551d() {
        return this.f6670e;
    }

    /* renamed from: e */
    public int m3549e() {
        return this.f6671f;
    }

    /* renamed from: f */
    public int m3547f() {
        return this.zOrder;
    }

    /* renamed from: g */
    public int m3546g() {
        return this.f6678m;
    }

    /* renamed from: e */
    public void m3548e(int i) {
        this.f6678m = i;
    }

    /* renamed from: a */
    public void m3560a(int i, int i2) {
        this.f6668c += i;
        this.f6670e += i;
        this.f6669d += i2;
        this.f6671f += i2;
    }
}
