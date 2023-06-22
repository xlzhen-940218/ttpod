package com.sds.android.ttpod.widget.dragupdatelist;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public class ModifySizeNotifyLayout extends ViewGroup {

    /* renamed from: a */
    private int f8278a;

    /* renamed from: b */
    private int f8279b;

    /* renamed from: c */
    private InterfaceC2270a f8280c;

    /* renamed from: d */
    private Handler f8281d;

    /* renamed from: com.sds.android.ttpod.widget.dragupdatelist.ModifySizeNotifyLayout$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2270a {
        /* renamed from: b */
        void mo1318b(int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1331a(int i, int i2) {
        int i3 = ((int) ((this.f8278a - i) * 0.5f)) + i;
        if (i3 <= i + 4) {
            m1328b(i);
            this.f8279b = i2;
            m1329b();
            return;
        }
        m1328b(i3);
        this.f8281d.sendEmptyMessageDelayed(this.f8279b, 17L);
    }

    public ModifySizeNotifyLayout(Context context) {
        this(context, null);
    }

    public ModifySizeNotifyLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ModifySizeNotifyLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8281d = new Handler() { // from class: com.sds.android.ttpod.widget.dragupdatelist.ModifySizeNotifyLayout.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 3:
                        ModifySizeNotifyLayout.this.m1331a(ModifySizeNotifyLayout.this.getChildAt(0).getMeasuredHeight() + ModifySizeNotifyLayout.this.getPaddingBottom() + ModifySizeNotifyLayout.this.getPaddingTop(), 5);
                        return;
                    case 4:
                        ModifySizeNotifyLayout.this.m1331a(ModifySizeNotifyLayout.this.getPaddingBottom(), 0);
                        return;
                    default:
                        return;
                }
            }
        };
        if (getPaddingBottom() != 0) {
            setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), 0);
        }
        this.f8278a = getPaddingBottom();
        this.f8279b = 0;
    }

    public void setOnShowStateChangedListener(InterfaceC2270a interfaceC2270a) {
        this.f8280c = interfaceC2270a;
    }

    public int getShowState() {
        return this.f8279b;
    }

    /* renamed from: a */
    public boolean m1332a(int i) {
        if (this.f8279b == 3 || this.f8279b == 4 || this.f8279b == 5) {
            return true;
        }
        if (i < getPaddingBottom()) {
            i = getPaddingBottom();
        }
        if (i == getPaddingBottom()) {
            if (this.f8279b == 0) {
                return false;
            }
            m1328b(i);
            this.f8279b = 0;
            m1329b();
        } else if (i > getPaddingBottom()) {
            m1328b(i);
            if (i > getChildAt(0).getMeasuredHeight() + getPaddingTop() + getPaddingBottom()) {
                if (this.f8279b != 2) {
                    this.f8279b = 2;
                    m1329b();
                }
            } else if (this.f8279b != 1) {
                this.f8279b = 1;
                m1329b();
            }
        }
        return true;
    }

    /* renamed from: b */
    private void m1328b(int i) {
        if (this.f8278a != i) {
            this.f8278a = i;
            requestLayout();
        }
    }

    /* renamed from: b */
    private void m1329b() {
        if (this.f8280c != null) {
            this.f8280c.mo1318b(this.f8279b);
        }
    }

    /* renamed from: a */
    public void m1333a() {
        if (this.f8279b == 2) {
            this.f8279b = 3;
            this.f8281d.sendEmptyMessage(this.f8279b);
            m1329b();
        } else if (this.f8279b == 1 || this.f8279b == 5) {
            this.f8279b = 4;
            this.f8281d.sendEmptyMessage(this.f8279b);
            m1329b();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        m1327b(i, i2);
        setMeasuredDimension(View.MeasureSpec.getSize(i), this.f8278a);
    }

    /* renamed from: b */
    private void m1327b(int i, int i2) {
        View childAt = getChildAt(0);
        if (childAt.getLayoutParams() == null) {
            childAt.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        }
        measureChild(childAt, i, i2);
        if (this.f8279b == 5) {
            this.f8278a = childAt.getMeasuredHeight() + getPaddingBottom() + getPaddingTop();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        int left = getLeft();
        int paddingBottom = (i4 - i2) - getPaddingBottom();
        childAt.layout(left, paddingBottom - childAt.getMeasuredHeight(), childAt.getMeasuredWidth() + left, paddingBottom);
    }
}
