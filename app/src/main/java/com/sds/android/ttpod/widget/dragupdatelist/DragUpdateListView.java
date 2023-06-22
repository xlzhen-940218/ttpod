package com.sds.android.ttpod.widget.dragupdatelist;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper;

/* loaded from: classes.dex */
public class DragUpdateListView extends ListView {

    /* renamed from: a */
    private DragUpdateHelper f8270a;

    /* renamed from: b */
    private boolean f8271b;

    /* renamed from: c */
    private DragUpdateHelper.InterfaceC2272b f8272c;

    public DragUpdateListView(Context context) {
        this(context, null);
    }

    public DragUpdateListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DragUpdateListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8270a = null;
        this.f8271b = true;
        this.f8272c = new DragUpdateHelper.InterfaceC2272b() { // from class: com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView.1
            @Override // com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper.InterfaceC2272b
            /* renamed from: a */
            public boolean mo1303a() {
                return DragUpdateListView.this.getFirstVisiblePosition() == 0;
            }

            @Override // com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper.InterfaceC2272b
            /* renamed from: b */
            public void mo1301b() {
                DragUpdateListView.this.setSelection(0);
            }

            @Override // com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper.InterfaceC2272b
            /* renamed from: a */
            public void mo1302a(View view) {
                DragUpdateListView.this.addHeaderView(view);
            }

            @Override // com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper.InterfaceC2272b
            /* renamed from: c */
            public void mo1300c() {
            }
        };
        this.f8270a = new DragUpdateHelper();
        this.f8270a.m1323a(context, this.f8272c);
        this.f8270a.m1319b();
    }

    public void setEnableDragUpdate(boolean z) {
        this.f8271b = z;
    }

    public TextView getTitleTextView() {
        return this.f8270a.m1316d();
    }

    public TextView getContentTextView() {
        return this.f8270a.m1315e();
    }

    /* renamed from: a */
    public void m1337a() {
        this.f8270a.m1314f();
    }

    public void setOnStartRefreshListener(DragUpdateHelper.InterfaceC2273c interfaceC2273c) {
        this.f8270a.m1320a(interfaceC2273c);
    }

    public void setLoadingTitleColor(ColorStateList colorStateList) {
        this.f8270a.m1322a(colorStateList);
    }

    /* renamed from: b */
    public void m1336b() {
        this.f8270a.m1326a();
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f8271b) {
            this.f8270a.m1321a(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }
}
