package com.sds.android.ttpod.widget.dragupdatelist;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper;

/* loaded from: classes.dex */
public class DragUpdateScrollView extends ScrollView implements ThemeManager.InterfaceC2019b {

    /* renamed from: a */
    private DragUpdateHelper f8274a;

    /* renamed from: b */
    private int[] f8275b;

    /* renamed from: c */
    private DragUpdateHelper.InterfaceC2272b f8276c;

    public DragUpdateScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DragUpdateScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8274a = null;
        this.f8275b = new int[2];
        this.f8276c = new DragUpdateHelper.InterfaceC2272b() { // from class: com.sds.android.ttpod.widget.dragupdatelist.DragUpdateScrollView.1
            @Override // com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper.InterfaceC2272b
            /* renamed from: a */
            public boolean mo1303a() {
                int[] iArr = new int[2];
                DragUpdateScrollView.this.getChildAt(0).getLocationOnScreen(iArr);
                return iArr[1] + DragUpdateScrollView.this.f8274a.m1317c() > DragUpdateScrollView.this.f8275b[1];
            }

            @Override // com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper.InterfaceC2272b
            /* renamed from: b */
            public void mo1301b() {
                int[] iArr = new int[2];
                DragUpdateScrollView.this.getChildAt(0).getLocationOnScreen(iArr);
                if (DragUpdateScrollView.this.getScrollY() != 0) {
                    DragUpdateScrollView.this.scrollTo(0, (DragUpdateScrollView.this.f8275b[1] - iArr[1]) / 2);
                }
            }

            @Override // com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper.InterfaceC2272b
            /* renamed from: a */
            public void mo1302a(View view) {
                if (DragUpdateScrollView.this.getChildCount() > 0) {
                    View childAt = DragUpdateScrollView.this.getChildAt(0);
                    if (childAt instanceof ViewGroup) {
                        ((ViewGroup) childAt).addView(view, 0, new ViewGroup.LayoutParams(-1, -2));
                    }
                }
            }

            @Override // com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper.InterfaceC2272b
            /* renamed from: c */
            public void mo1300c() {
                if (DragUpdateScrollView.this.getScrollY() != 0) {
                    DragUpdateScrollView.this.scrollTo(0, 0);
                }
            }
        };
        this.f8274a = new DragUpdateHelper();
        this.f8274a.m1323a(context, this.f8276c);
        setWillNotDraw(true);
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        getLocationOnScreen(this.f8275b);
    }

    public void setOnStartRefreshListener(DragUpdateHelper.InterfaceC2273c interfaceC2273c) {
        this.f8274a.m1320a(interfaceC2273c);
    }

    public void setLoadingTitleColor(int i) {
        this.f8274a.m1324a(i);
    }

    public void setLoadingTitleColor(ColorStateList colorStateList) {
        this.f8274a.m1322a(colorStateList);
    }

    @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        setLoadingTitleColor(ThemeManager.m3254c(ThemeElement.COMMON_CONTENT_TEXT));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f8274a.m1319b();
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.f8274a.m1321a(motionEvent);
        return super.onTouchEvent(motionEvent);
    }
}
