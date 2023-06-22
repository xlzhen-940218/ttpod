package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.framework.modules.skin.SkinLayoutParams;

/* loaded from: classes.dex */
public class SkinAbsoluteLayout extends ViewGroup {

    /* renamed from: a */
    private MyOnLayoutChangeListener f6864a;

    /* renamed from: b */
    private int f6865b;

    /* renamed from: c */
    private int f6866c;

    /* renamed from: d */
    private int f6867d;

    /* renamed from: e */
    private int f6868e;

    /* renamed from: f */
    private boolean f6869f;

    /* renamed from: g */
    private boolean f6870g;

    /* renamed from: h */
    private ViewGroup.OnHierarchyChangeListener f6871h;

    /* renamed from: i */
    private ViewGroup.OnHierarchyChangeListener f6872i;

    public SkinAbsoluteLayout(Context context) {
        super(context);
        this.f6869f = true;
        this.f6870g = false;
        this.f6871h = new ViewGroup.OnHierarchyChangeListener() { // from class: com.sds.android.ttpod.framework.modules.skin.view.SkinAbsoluteLayout.1
            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewAdded(View view, View view2) {
                SkinAbsoluteLayout.this.f6869f = true;
                if (SkinAbsoluteLayout.this.f6872i != null) {
                    SkinAbsoluteLayout.this.f6872i.onChildViewAdded(view, view2);
                }
            }

            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewRemoved(View view, View view2) {
                SkinAbsoluteLayout.this.f6869f = true;
                if (SkinAbsoluteLayout.this.f6872i != null) {
                    SkinAbsoluteLayout.this.f6872i.onChildViewRemoved(view, view2);
                }
            }
        };
        super.setOnHierarchyChangeListener(this.f6871h);
    }

    public SkinAbsoluteLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6869f = true;
        this.f6870g = false;
        this.f6871h = new ViewGroup.OnHierarchyChangeListener() { // from class: com.sds.android.ttpod.framework.modules.skin.view.SkinAbsoluteLayout.1
            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewAdded(View view, View view2) {
                SkinAbsoluteLayout.this.f6869f = true;
                if (SkinAbsoluteLayout.this.f6872i != null) {
                    SkinAbsoluteLayout.this.f6872i.onChildViewAdded(view, view2);
                }
            }

            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewRemoved(View view, View view2) {
                SkinAbsoluteLayout.this.f6869f = true;
                if (SkinAbsoluteLayout.this.f6872i != null) {
                    SkinAbsoluteLayout.this.f6872i.onChildViewRemoved(view, view2);
                }
            }
        };
        super.setOnHierarchyChangeListener(this.f6871h);
    }

    public SkinAbsoluteLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6869f = true;
        this.f6870g = false;
        this.f6871h = new ViewGroup.OnHierarchyChangeListener() { // from class: com.sds.android.ttpod.framework.modules.skin.view.SkinAbsoluteLayout.1
            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewAdded(View view, View view2) {
                SkinAbsoluteLayout.this.f6869f = true;
                if (SkinAbsoluteLayout.this.f6872i != null) {
                    SkinAbsoluteLayout.this.f6872i.onChildViewAdded(view, view2);
                }
            }

            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewRemoved(View view, View view2) {
                SkinAbsoluteLayout.this.f6869f = true;
                if (SkinAbsoluteLayout.this.f6872i != null) {
                    SkinAbsoluteLayout.this.f6872i.onChildViewRemoved(view, view2);
                }
            }
        };
        super.setOnHierarchyChangeListener(this.f6871h);
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.f6872i = onHierarchyChangeListener;
    }

    public void setOnLayoutChangeListener(MyOnLayoutChangeListener myOnLayoutChangeListener) {
        this.f6864a = myOnLayoutChangeListener;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f6870g = this.f6869f;
        if (this.f6869f) {
            SkinLayoutParams.m3557a((ViewGroup) this, getMeasuredWidth(), getMeasuredHeight());
            this.f6869f = false;
        }
        measureChildren(i, i2);
    }

    @Override // android.view.ViewGroup
    protected void measureChild(View view, int i, int i2) {
        int childMeasureSpec;
        int childMeasureSpec2;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof SkinLayoutParams) {
            SkinLayoutParams skinLayoutParams = (SkinLayoutParams) layoutParams;
            childMeasureSpec = View.MeasureSpec.makeMeasureSpec(skinLayoutParams.m3551d() - skinLayoutParams.m3556b(), 1073741824);
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(skinLayoutParams.m3549e() - skinLayoutParams.m3553c(), 1073741824);
        } else {
            childMeasureSpec = getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight(), layoutParams.width);
            childMeasureSpec2 = getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom(), layoutParams.height);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3 || i2 != i4) {
            this.f6869f = false;
        }
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f6870g |= z;
        if (this.f6870g) {
            this.f6870g = false;
            mo3363a(z, i, i2, i3, i4);
        } else {
            m3362b(z, i, i2, i3, i4);
        }
        if (z) {
            if (this.f6864a != null) {
                this.f6864a.m3348a(this, i, i2, i3, i4, this.f6865b, this.f6866c, this.f6867d, this.f6868e);
            }
            this.f6865b = i;
            this.f6866c = i2;
            this.f6867d = i3;
            this.f6868e = i4;
        }
    }

    /* renamed from: b */
    protected void m3362b(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt instanceof ViewGroup) {
                m3366a(childAt);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3363a(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            m3366a(getChildAt(i5));
        }
    }

    /* renamed from: a */
    protected void m3366a(View view) {
        if (!SkinLayoutParams.m3554b(view) && view.getVisibility() != View.GONE) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            view.layout(paddingLeft, paddingTop, view.getMeasuredWidth() + paddingLeft, view.getMeasuredHeight() + paddingTop);
        }
    }
}
