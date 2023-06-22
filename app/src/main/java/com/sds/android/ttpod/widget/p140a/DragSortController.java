package com.sds.android.ttpod.widget.p140a;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.sds.android.ttpod.widget.DraggableListView;

/* renamed from: com.sds.android.ttpod.widget.a.a */
/* loaded from: classes.dex */
public class DragSortController extends SimpleFloatViewController implements GestureDetector.OnGestureListener, View.OnTouchListener {

    /* renamed from: a */
    private int f8077a;

    /* renamed from: b */
    private boolean f8078b;

    /* renamed from: c */
    private GestureDetector f8079c;

    /* renamed from: d */
    private int f8080d;

    /* renamed from: e */
    private int[] f8081e;

    /* renamed from: f */
    private int f8082f;

    /* renamed from: g */
    private int f8083g;

    /* renamed from: h */
    private boolean f8084h;

    /* renamed from: i */
    private int f8085i;

    /* renamed from: j */
    private DraggableListView f8086j;

    public DragSortController(DraggableListView draggableListView, int i, int i2) {
        super(draggableListView);
        this.f8077a = 0;
        this.f8078b = true;
        this.f8080d = -1;
        this.f8081e = new int[2];
        this.f8084h = false;
        this.f8086j = draggableListView;
        this.f8079c = new GestureDetector(draggableListView.getContext(), this);
        this.f8085i = i;
        m1424b(i2);
    }

    /* renamed from: a */
    public void m1430a(int i) {
        this.f8085i = i;
    }

    /* renamed from: b */
    public void m1424b(int i) {
        this.f8077a = i;
    }

    /* renamed from: a */
    public void m1425a(boolean z) {
        this.f8078b = z;
    }

    /* renamed from: a */
    public boolean m1429a(int i, int i2, int i3) {
        int i4 = 0;
        if (this.f8078b) {
            i4 = 12;
        }
        this.f8084h = this.f8086j.m1831a(i - this.f8086j.getHeaderViewsCount(), i4, i2, i3);
        return this.f8084h;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.f8086j.m1807d() && !this.f8086j.m1811c()) {
            this.f8079c.onTouchEvent(motionEvent);
        }
        return false;
    }

    /* renamed from: a */
    public int m1428a(MotionEvent motionEvent) {
        return m1423b(motionEvent);
    }

    /* renamed from: b */
    private int m1423b(MotionEvent motionEvent) {
        return m1427a(motionEvent, this.f8085i);
    }

    /* renamed from: a */
    private int m1427a(MotionEvent motionEvent, int i) {
        int pointToPosition = this.f8086j.pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        int headerViewsCount = this.f8086j.getHeaderViewsCount();
        int footerViewsCount = this.f8086j.getFooterViewsCount();
        int count = this.f8086j.getCount();
        if (pointToPosition != -1 && pointToPosition >= headerViewsCount && pointToPosition < count - footerViewsCount) {
            View childAt = this.f8086j.getChildAt(pointToPosition - this.f8086j.getFirstVisiblePosition());
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            View findViewById = i == 0 ? childAt : childAt.findViewById(i);
            if (findViewById != null) {
                findViewById.getLocationOnScreen(this.f8081e);
                if (rawX > this.f8081e[0] && rawY > this.f8081e[1] && rawX < this.f8081e[0] + findViewById.getWidth()) {
                    if (rawY < findViewById.getHeight() + this.f8081e[1]) {
                        this.f8082f = childAt.getLeft();
                        this.f8083g = childAt.getTop();
                        return pointToPosition;
                    }
                }
            }
        }
        return -1;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        this.f8080d = m1428a(motionEvent);
        if (this.f8080d != -1 && this.f8077a == 0) {
            m1429a(this.f8080d, ((int) motionEvent.getX()) - this.f8082f, ((int) motionEvent.getY()) - this.f8083g);
            return true;
        }
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }
}
