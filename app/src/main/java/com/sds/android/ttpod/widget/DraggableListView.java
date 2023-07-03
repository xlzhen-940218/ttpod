package com.sds.android.ttpod.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.widget.p140a.DragSortController;
import com.sds.android.ttpod.widget.p140a.DragSortItemView;
import com.sds.android.ttpod.widget.p140a.FloatViewController;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class DraggableListView extends ListView {

    /* renamed from: A */
    private int f7562A;

    /* renamed from: B */
    private int f7563B;

    /* renamed from: C */
    private float f7564C;

    /* renamed from: D */
    private float f7565D;

    /* renamed from: E */
    private float f7566E;

    /* renamed from: F */
    private float f7567F;

    /* renamed from: G */
    private float f7568G;

    /* renamed from: H */
    private InterfaceC2178c f7569H;

    /* renamed from: I */
    private int f7570I;

    /* renamed from: J */
    private int f7571J;

    /* renamed from: K */
    private int f7572K;

    /* renamed from: L */
    private int f7573L;

    /* renamed from: M */
    private boolean f7574M;

    /* renamed from: N */
    private boolean f7575N;

    /* renamed from: O */
    private FloatViewController f7576O;

    /* renamed from: P */
    private MotionEvent f7577P;

    /* renamed from: Q */
    private int f7578Q;

    /* renamed from: R */
    private float f7579R;

    /* renamed from: S */
    private float f7580S;

    /* renamed from: T */
    private C2175a f7581T;

    /* renamed from: U */
    private boolean f7582U;

    /* renamed from: V */
    private boolean f7583V;

    /* renamed from: W */
    private C2182g f7584W;

    /* renamed from: Z */
    private C2180e f7585Z;

    /* renamed from: a */
    private View f7586a;

    /* renamed from: aa */
    private boolean f7587aa;

    /* renamed from: b */
    private Point f7588b;

    /* renamed from: c */
    private Point f7589c;

    /* renamed from: d */
    private int f7590d;

    /* renamed from: e */
    private boolean f7591e;

    /* renamed from: f */
    private DataSetObserver f7592f;

    /* renamed from: g */
    private float f7593g;

    /* renamed from: h */
    private int f7594h;

    /* renamed from: i */
    private int f7595i;

    /* renamed from: j */
    private int f7596j;

    /* renamed from: k */
    private boolean f7597k;

    /* renamed from: l */
    private int f7598l;

    /* renamed from: m */
    private int f7599m;

    /* renamed from: n */
    private int f7600n;

    /* renamed from: o */
    private DragListener dragListener;

    /* renamed from: p */
    private DropListener f7602p;

    /* renamed from: q */
    private boolean f7603q;

    /* renamed from: r */
    private int f7604r;

    /* renamed from: s */
    private int f7605s;

    /* renamed from: t */
    private int f7606t;

    /* renamed from: u */
    private int f7607u;

    /* renamed from: v */
    private int f7608v;

    /* renamed from: w */
    private View[] f7609w;

    /* renamed from: x */
    private RunnableC2179d f7610x;

    /* renamed from: y */
    private float f7611y;

    /* renamed from: z */
    private float f7612z;

    /* renamed from: com.sds.android.ttpod.widget.DraggableListView$b */
    /* loaded from: classes.dex */
    public interface DragListener {
        /* renamed from: a */
        void mo1780a(int i, int i2);
    }

    /* renamed from: com.sds.android.ttpod.widget.DraggableListView$c */
    /* loaded from: classes.dex */
    public interface InterfaceC2178c {
        /* renamed from: a */
        float mo1779a(float f, long j);
    }

    /* renamed from: com.sds.android.ttpod.widget.DraggableListView$f */
    /* loaded from: classes.dex */
    public interface DropListener {
        /* renamed from: a */
        void mo1773a(int i, int i2);
    }

    public DraggableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7588b = new Point();
        this.f7589c = new Point();
        this.f7591e = false;
        this.f7593g = 0.8f;
        this.f7597k = true;
        this.f7603q = true;
        this.f7604r = 0;
        this.f7605s = 2;
        this.f7608v = 0;
        this.f7609w = new View[1];
        this.f7611y = 0.33333334f;
        this.f7612z = 0.33333334f;
        this.f7568G = 1.0f;
        this.f7569H = new InterfaceC2178c() { // from class: com.sds.android.ttpod.widget.DraggableListView.1
            @Override // com.sds.android.ttpod.widget.DraggableListView.InterfaceC2178c
            /* renamed from: a */
            public float mo1779a(float f, long j) {
                return DraggableListView.this.f7568G * f;
            }
        };
        this.f7573L = 0;
        this.f7574M = false;
        this.f7575N = false;
        this.f7576O = null;
        this.f7578Q = 0;
        this.f7579R = 0.7f;
        this.f7580S = 0.0f;
        this.f7582U = false;
        this.f7583V = false;
        this.f7584W = new C2182g(3);
        this.f7587aa = false;
        setDragScrollStart(0.5f);
        DragSortController dragSortController = new DragSortController(this, 16908292, 0);
        dragSortController.m1425a(true);
        dragSortController.m1419d(1325400064);
        this.f7576O = dragSortController;
        setOnTouchListener(dragSortController);
        this.f7610x = new RunnableC2179d();
        this.f7585Z = new C2180e(0.5f, 150);
        this.f7577P = MotionEvent.obtain(0L, 0L, 3, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        this.f7592f = new DataSetObserver() { // from class: com.sds.android.ttpod.widget.DraggableListView.2
            /* renamed from: a */
            private void m1782a() {
                if (DraggableListView.this.f7604r == 3) {
                    DraggableListView.this.m1835a();
                }
            }

            @Override // android.database.DataSetObserver
            public void onChanged() {
                m1782a();
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                m1782a();
            }
        };
    }

    @Override // android.widget.AdapterView
    public void setAdapter(ListAdapter listAdapter) {
        if (this.f7581T != null && this.f7581T.m1781a() != null) {
            this.f7581T.m1781a().unregisterDataSetObserver(this.f7592f);
        }
        if (listAdapter != null) {
            this.f7581T = new C2175a(listAdapter);
            this.f7581T.m1781a().registerDataSetObserver(this.f7592f);
            if (listAdapter instanceof DropListener) {
                setDropListener((DropListener) listAdapter);
            }
            if (listAdapter instanceof DragListener) {
                setDragListener((DragListener) listAdapter);
            }
        } else {
            this.f7581T = null;
        }
        super.setAdapter((ListAdapter) this.f7581T);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.widget.DraggableListView$a */
    /* loaded from: classes.dex */
    public class C2175a extends BaseAdapter {

        /* renamed from: b */
        private ListAdapter f7616b;

        public C2175a(ListAdapter listAdapter) {
            this.f7616b = listAdapter;
            this.f7616b.registerDataSetObserver(new DataSetObserver() { // from class: com.sds.android.ttpod.widget.DraggableListView.a.1
                @Override // android.database.DataSetObserver
                public void onChanged() {
                    C2175a.this.notifyDataSetChanged();
                }

                @Override // android.database.DataSetObserver
                public void onInvalidated() {
                    C2175a.this.notifyDataSetInvalidated();
                }
            });
        }

        /* renamed from: a */
        public ListAdapter m1781a() {
            return this.f7616b;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return this.f7616b.getItemId(i);
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return this.f7616b.getItem(i);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f7616b.getCount();
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return this.f7616b.areAllItemsEnabled();
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i) {
            return this.f7616b.isEnabled(i);
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i) {
            return this.f7616b.getItemViewType(i);
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return this.f7616b.getViewTypeCount();
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return this.f7616b.hasStableIds();
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean isEmpty() {
            return this.f7616b.isEmpty();
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            DragSortItemView dragSortItemView;
            if (view != null) {
                dragSortItemView = (DragSortItemView) view;
                View childAt = dragSortItemView.getChildAt(0);
                View view2 = this.f7616b.getView(i, childAt, DraggableListView.this);
                if (view2 != childAt) {
                    if (childAt != null) {
                        dragSortItemView.removeViewAt(0);
                    }
                    dragSortItemView.addView(view2);
                }
            } else {
                View view3 = this.f7616b.getView(i, null, DraggableListView.this);
                dragSortItemView = new DragSortItemView(DraggableListView.this.getContext());
                dragSortItemView.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
                dragSortItemView.addView(view3);
            }
            DraggableListView.this.m1827a(DraggableListView.this.getHeaderViewsCount() + i, dragSortItemView, true);
            return dragSortItemView;
        }
    }

    /* renamed from: a */
    private void m1830a(int i, Canvas canvas) {
        ViewGroup viewGroup;
        int bottom;
        int i2;
        Drawable divider = getDivider();
        int dividerHeight = getDividerHeight();
        if (divider != null && dividerHeight != 0 && (viewGroup = (ViewGroup) getChildAt(i - getFirstVisiblePosition())) != null) {
            int paddingLeft = getPaddingLeft();
            int width = getWidth() - getPaddingRight();
            int height = viewGroup.getChildAt(0).getHeight();
            if (i > this.f7598l) {
                i2 = height + viewGroup.getTop();
                bottom = i2 + dividerHeight;
            } else {
                bottom = viewGroup.getBottom() - height;
                i2 = bottom - dividerHeight;
            }
            canvas.save();
            canvas.clipRect(paddingLeft, i2, width, bottom);
            divider.setBounds(paddingLeft, i2, width, bottom);
            divider.draw(canvas);
            canvas.restore();
        }
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        float f;
        super.dispatchDraw(canvas);
        if (this.f7604r != 0) {
            if (this.f7595i != this.f7598l) {
                m1830a(this.f7595i, canvas);
            }
            if (this.f7596j != this.f7595i && this.f7596j != this.f7598l) {
                m1830a(this.f7596j, canvas);
            }
        }
        if (this.f7586a != null) {
            int width = this.f7586a.getWidth();
            int height = this.f7586a.getHeight();
            int i = this.f7588b.x;
            int width2 = getWidth();
            if (i < 0) {
                i = -i;
            }
            if (i < width2) {
                float f2 = (width2 - i) / width2;
                f = f2 * f2;
            } else {
                f = 0.0f;
            }
            int i2 = (int) (f * 255.0f * this.f7593g);
            canvas.save();
            canvas.translate(this.f7588b.x, this.f7588b.y);
            canvas.clipRect(0, 0, width, height);
            canvas.saveLayerAlpha(0.0f, 0.0f, width, height, i2, Canvas.ALL_SAVE_FLAG);
            this.f7586a.draw(canvas);
            canvas.restore();
            canvas.restore();
        }
    }

    /* renamed from: a */
    private int m1833a(int i) {
        View childAt = getChildAt(i - getFirstVisiblePosition());
        return childAt != null ? childAt.getHeight() : m1810c(i, m1818b(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.widget.DraggableListView$g */
    /* loaded from: classes.dex */
    public class C2182g {

        /* renamed from: b */
        private SparseIntArray f7635b;

        /* renamed from: c */
        private ArrayList<Integer> f7636c;

        /* renamed from: d */
        private int f7637d;

        public C2182g(int i) {
            this.f7635b = new SparseIntArray(i);
            this.f7636c = new ArrayList<>(i);
            this.f7637d = i;
        }

        /* renamed from: a */
        public void m1770a(int i, int i2) {
            int i3 = this.f7635b.get(i, -1);
            if (i3 != i2) {
                if (i3 == -1) {
                    if (this.f7635b.size() == this.f7637d) {
                        this.f7635b.delete(this.f7636c.remove(0).intValue());
                    }
                } else {
                    this.f7636c.remove(Integer.valueOf(i));
                }
                this.f7635b.put(i, i2);
                this.f7636c.add(Integer.valueOf(i));
            }
        }

        /* renamed from: a */
        public int m1771a(int i) {
            return this.f7635b.get(i, -1);
        }

        /* renamed from: a */
        public void m1772a() {
            this.f7635b.clear();
            this.f7636c.clear();
        }
    }

    /* renamed from: a */
    private int m1832a(int i, int i2) {
        int headerViewsCount = getHeaderViewsCount();
        int footerViewsCount = getFooterViewsCount();
        if (i <= headerViewsCount || i >= getCount() - footerViewsCount) {
            return i2;
        }
        int dividerHeight = getDividerHeight();
        LogUtils.verbose("testitemheight", "item: " + this.f7605s);
        int i3 = this.f7606t - this.f7605s;
        int m1818b = m1818b(i);
        int m1833a = m1833a(i);
        if (this.f7596j <= this.f7598l) {
            if (i == this.f7596j && this.f7595i != this.f7596j) {
                if (i == this.f7598l) {
                    i2 = (i2 + m1833a) - this.f7606t;
                } else {
                    i2 = ((m1833a - m1818b) + i2) - i3;
                }
            } else if (i > this.f7596j && i <= this.f7598l) {
                i2 -= i3;
            }
        } else if (i > this.f7598l && i <= this.f7595i) {
            i2 += i3;
        } else if (i == this.f7596j && this.f7595i != this.f7596j) {
            i2 += m1833a - m1818b;
        }
        if (i <= this.f7598l) {
            return (((this.f7606t - dividerHeight) - m1818b(i - 1)) / 2) + i2;
        }
        return (((m1818b - dividerHeight) - this.f7606t) / 2) + i2;
    }

    /* renamed from: e */
    private boolean m1804e() {
        int i;
        int i2;
        int i3;
        int firstVisiblePosition = getFirstVisiblePosition();
        int i4 = this.f7595i;
        View childAt = getChildAt(i4 - firstVisiblePosition);
        if (childAt == null) {
            i4 = firstVisiblePosition + (getChildCount() / 2);
            childAt = getChildAt(i4 - firstVisiblePosition);
        }
        int top = childAt.getTop();
        int height = childAt.getHeight();
        int m1832a = m1832a(i4, top);
        int dividerHeight = getDividerHeight();
        if (this.f7590d >= m1832a) {
            int count = getCount();
            int i5 = height;
            int i6 = top;
            i = m1832a;
            i2 = i4;
            i3 = m1832a;
            while (true) {
                if (i2 < count) {
                    if (i2 == count - 1) {
                        i = i6 + dividerHeight + i5;
                        break;
                    }
                    i6 += dividerHeight + i5;
                    i5 = m1833a(i2 + 1);
                    i = m1832a(i2 + 1, i6);
                    if (this.f7590d < i) {
                        break;
                    }
                    i2++;
                    i3 = i;
                } else {
                    break;
                }
            }
        } else {
            int i7 = top;
            i = m1832a;
            i2 = i4;
            i3 = m1832a;
            while (true) {
                if (i2 < 0) {
                    break;
                }
                i2--;
                int m1833a = m1833a(i2);
                if (i2 == 0) {
                    i = (i7 - dividerHeight) - m1833a;
                    break;
                }
                i7 -= m1833a + dividerHeight;
                i = m1832a(i2, i7);
                if (this.f7590d >= i) {
                    break;
                }
                i3 = i;
            }
        }
        int headerViewsCount = getHeaderViewsCount();
        int footerViewsCount = getFooterViewsCount();
        int i8 = this.f7595i;
        int i9 = this.f7596j;
        float f = this.f7580S;
        if (this.f7597k) {
            int abs = Math.abs(i - i3);
            if (this.f7590d >= i) {
                int i10 = i3;
                i3 = i;
                i = i10;
            }
            int i11 = (int) (abs * this.f7579R * 0.5f);
            float f2 = i11;
            int i12 = i3 + i11;
            int i13 = i - i11;
            if (this.f7590d < i12) {
                this.f7595i = i2 - 1;
                this.f7596j = i2;
                this.f7580S = ((i12 - this.f7590d) * 0.5f) / f2;
            } else if (this.f7590d < i13) {
                this.f7595i = i2;
                this.f7596j = i2;
            } else {
                this.f7595i = i2;
                this.f7596j = i2 + 1;
                this.f7580S = (1.0f + ((i - this.f7590d) / f2)) * 0.5f;
            }
        } else {
            this.f7595i = i2;
            this.f7596j = i2;
        }
        if (this.f7595i < headerViewsCount) {
            this.f7595i = headerViewsCount;
            this.f7596j = headerViewsCount;
            i2 = headerViewsCount;
        } else if (this.f7596j >= getCount() - footerViewsCount) {
            i2 = (getCount() - footerViewsCount) - 1;
            this.f7595i = i2;
            this.f7596j = i2;
        }
        boolean z = (this.f7595i == i8 && this.f7596j == i9 && this.f7580S == f) ? false : true;
        if (i2 != this.f7594h) {
            if (this.dragListener != null) {
                this.dragListener.mo1780a(this.f7594h - headerViewsCount, i2 - headerViewsCount);
            }
            this.f7594h = i2;
            return true;
        }
        return z;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.widget.DraggableListView$h */
    /* loaded from: classes.dex */
    public class RunnableC2183h implements Runnable {

        /* renamed from: a */
        private float f7638a;

        /* renamed from: b */
        protected long f7639b;

        /* renamed from: d */
        private float f7641d;

        /* renamed from: e */
        private float f7642e;

        /* renamed from: f */
        private float f7643f;

        /* renamed from: g */
        private float f7644g;

        /* renamed from: h */
        private float f7645h;

        /* renamed from: i */
        private boolean f7646i;

        public RunnableC2183h(float f, int i) {
            this.f7641d = f;
            this.f7638a = i;
            this.f7642e = 1.0f / ((this.f7641d * 2.0f) * (1.0f - this.f7641d));
            this.f7643f = this.f7641d / ((this.f7641d - 1.0f) * 2.0f);
            this.f7644g = 1.0f / (1.0f - this.f7641d);
            this.f7645h = this.f7642e;
        }

        /* renamed from: a */
        public float m1768a(float f) {
            if (f < this.f7641d) {
                return this.f7642e * f * f;
            }
            if (f < 1.0f - this.f7641d) {
                return this.f7643f + (this.f7644g * f);
            }
            return 1.0f - ((this.f7645h * (f - 1.0f)) * (f - 1.0f));
        }

        /* renamed from: c */
        public void m1765c() {
            this.f7639b = SystemClock.uptimeMillis();
            this.f7646i = false;
            mo1769a();
            DraggableListView.this.post(this);
        }

        /* renamed from: d */
        public void m1764d() {
            this.f7646i = true;
        }

        /* renamed from: a */
        public void mo1769a() {
        }

        /* renamed from: a */
        public void mo1767a(float f, float f2) {
        }

        /* renamed from: b */
        public void mo1766b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.f7646i) {
                float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.f7639b)) / this.f7638a;
                if (uptimeMillis >= 1.0f) {
                    mo1767a(1.0f, 1.0f);
                    mo1766b();
                    return;
                }
                mo1767a(uptimeMillis, m1768a(uptimeMillis));
                DraggableListView.this.post(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.widget.DraggableListView$e */
    /* loaded from: classes.dex */
    public class C2180e extends RunnableC2183h {

        /* renamed from: d */
        private int f7630d;

        /* renamed from: e */
        private int f7631e;

        /* renamed from: f */
        private float f7632f;

        /* renamed from: g */
        private float f7633g;

        public C2180e(float f, int i) {
            super(f, i);
        }

        @Override // com.sds.android.ttpod.widget.DraggableListView.RunnableC2183h
        /* renamed from: a */
        public void mo1769a() {
            this.f7630d = DraggableListView.this.f7594h;
            this.f7631e = DraggableListView.this.f7598l;
            DraggableListView.this.f7604r = 1;
            this.f7632f = DraggableListView.this.f7588b.y - m1774e();
            this.f7633g = DraggableListView.this.f7588b.x - DraggableListView.this.getPaddingLeft();
        }

        /* renamed from: e */
        private int m1774e() {
            int firstVisiblePosition = DraggableListView.this.getFirstVisiblePosition();
            int dividerHeight = (DraggableListView.this.f7605s + DraggableListView.this.getDividerHeight()) / 2;
            View childAt = DraggableListView.this.getChildAt(this.f7630d - firstVisiblePosition);
            if (childAt != null) {
                if (this.f7630d == this.f7631e) {
                    return childAt.getTop();
                }
                if (this.f7630d >= this.f7631e) {
                    return (childAt.getBottom() + dividerHeight) - DraggableListView.this.f7606t;
                }
                return childAt.getTop() - dividerHeight;
            }
            m1764d();
            return -1;
        }

        @Override // com.sds.android.ttpod.widget.DraggableListView.RunnableC2183h
        /* renamed from: a */
        public void mo1767a(float f, float f2) {
            int m1774e = m1774e();
            float paddingLeft = DraggableListView.this.f7588b.x - DraggableListView.this.getPaddingLeft();
            float f3 = 1.0f - f2;
            if (f3 < Math.abs((DraggableListView.this.f7588b.y - m1774e) / this.f7632f) || f3 < Math.abs(paddingLeft / this.f7633g)) {
                DraggableListView.this.f7588b.y = m1774e + ((int) (this.f7632f * f3));
                DraggableListView.this.f7588b.x = DraggableListView.this.getPaddingLeft() + ((int) (this.f7633g * f3));
                DraggableListView.this.m1820a(true);
            }
        }

        @Override // com.sds.android.ttpod.widget.DraggableListView.RunnableC2183h
        /* renamed from: b */
        public void mo1766b() {
            DraggableListView.this.m1800g();
        }
    }

    /* renamed from: a */
    public void m1835a() {
        if (this.f7604r == 3) {
            this.f7610x.m1776a(true);
            m1786n();
            m1802f();
            m1792k();
            if (this.f7575N) {
                this.f7604r = 2;
            } else {
                this.f7604r = 0;
            }
        }
    }

    /* renamed from: f */
    private void m1802f() {
        this.f7598l = -1;
        this.f7595i = -1;
        this.f7596j = -1;
        this.f7594h = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public void m1800g() {
        this.f7604r = 1;
        if (this.f7602p != null && this.f7594h >= 0 && this.f7594h < getCount()) {
            int headerViewsCount = getHeaderViewsCount();
            this.f7602p.mo1773a(this.f7598l - headerViewsCount, this.f7594h - headerViewsCount);
        }
        m1786n();
        m1798h();
        m1802f();
        m1792k();
        if (this.f7575N) {
            this.f7604r = 2;
        } else {
            this.f7604r = 0;
        }
    }

    /* renamed from: h */
    private void m1798h() {
        int firstVisiblePosition = getFirstVisiblePosition();
        if (this.f7598l < firstVisiblePosition) {
            View childAt = getChildAt(0);
            setSelectionFromTop(firstVisiblePosition - 1, (childAt != null ? childAt.getTop() : 0) - getPaddingTop());
        }
    }

    /* renamed from: b */
    public boolean m1819b() {
        if (this.f7586a != null) {
            this.f7610x.m1776a(true);
            if (this.f7585Z != null) {
                this.f7585Z.m1765c();
                return true;
            }
            m1800g();
            return true;
        }
        return false;
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.f7583V) {
            this.f7583V = false;
            return false;
        }
        try {
            if (!this.f7603q) {
                return super.onTouchEvent(motionEvent);
            }
            boolean z2 = this.f7574M;
            this.f7574M = false;
            if (!z2) {
                m1815b(motionEvent);
            }
            if (this.f7604r == 3) {
                m1826a(motionEvent);
                return true;
            }
            if (this.f7604r == 0) {
                try {
                    if (super.onTouchEvent(motionEvent)) {
                        z = true;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
            switch (motionEvent.getAction() & 255) {
                case 1:
                case 3:
                    m1796i();
                    return z;
                case 2:
                default:
                    if (z) {
                        this.f7578Q = 1;
                        return z;
                    }
                    return z;
            }
        } catch (Exception e2) {
            return false;
        }
    }

    /* renamed from: i */
    private void m1796i() {
        this.f7578Q = 0;
        this.f7575N = false;
        if (this.f7604r == 2) {
            this.f7604r = 0;
        }
        this.f7587aa = false;
        this.f7584W.m1772a();
    }

    /* renamed from: b */
    private void m1815b(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            this.f7572K = this.f7571J;
        }
        this.f7570I = (int) motionEvent.getX();
        this.f7571J = (int) motionEvent.getY();
        if (action == 0) {
            this.f7572K = this.f7571J;
        }
    }

    /* renamed from: c */
    public boolean m1811c() {
        return this.f7587aa;
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        if (!this.f7603q) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        m1815b(motionEvent);
        this.f7574M = true;
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            if (this.f7604r != 0) {
                this.f7583V = true;
                return true;
            }
            this.f7575N = true;
        }
        if (this.f7586a == null) {
            if (super.onInterceptTouchEvent(motionEvent)) {
                this.f7587aa = true;
                z = true;
            } else {
                z = false;
            }
            switch (action) {
                case 1:
                case 3:
                    m1796i();
                    break;
                case 2:
                default:
                    if (z) {
                        this.f7578Q = 1;
                        break;
                    } else {
                        this.f7578Q = 2;
                        break;
                    }
            }
        } else {
            z = true;
        }
        if (action == 1 || action == 3) {
            this.f7575N = false;
        }
        return z;
    }

    public void setDragScrollStart(float f) {
        m1834a(f, f);
    }

    /* renamed from: a */
    public void m1834a(float f, float f2) {
        if (f2 > 0.2f) {
            this.f7612z = 0.2f;
        } else {
            this.f7612z = f2;
        }
        if (f > 0.2f) {
            this.f7611y = 0.2f;
        } else {
            this.f7611y = f;
        }
        if (getHeight() != 0) {
            m1794j();
        }
    }

    /* renamed from: b */
    private void m1817b(int i, int i2) {
        this.f7588b.x = i - this.f7599m;
        this.f7588b.y = i2 - this.f7600n;
        m1820a(true);
        int min = Math.min(i2, this.f7590d + this.f7607u);
        int max = Math.max(i2, this.f7590d - this.f7607u);
        int m1775b = this.f7610x.m1775b();
        if (min > this.f7572K && min > this.f7563B && m1775b != 1) {
            if (m1775b != -1) {
                this.f7610x.m1776a(true);
            }
            this.f7610x.m1777a(1);
        } else if (max < this.f7572K && max < this.f7562A && m1775b != 0) {
            if (m1775b != -1) {
                this.f7610x.m1776a(true);
            }
            this.f7610x.m1777a(0);
        } else if (max >= this.f7562A && min <= this.f7563B && this.f7610x.m1778a()) {
            this.f7610x.m1776a(true);
        }
    }

    /* renamed from: j */
    private void m1794j() {

        int paddingTop = getPaddingTop();
        float height2 = (getHeight() - paddingTop) - getPaddingBottom();
        this.f7565D = paddingTop + (this.f7611y * height2);
        this.f7564C = (height2 * (1.0f - this.f7612z)) + paddingTop;
        this.f7562A = (int) this.f7565D;
        this.f7563B = (int) this.f7564C;
        this.f7566E = this.f7565D - paddingTop;
        this.f7567F = (paddingTop + height2) - this.f7564C;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        m1794j();
    }

    /* renamed from: k */
    private void m1792k() {
        int firstVisiblePosition = getFirstVisiblePosition();
        int lastVisiblePosition = getLastVisiblePosition();
        int min = Math.min(lastVisiblePosition - firstVisiblePosition, ((getCount() - 1) - getFooterViewsCount()) - firstVisiblePosition);
        for (int max = Math.max(0, getHeaderViewsCount() - firstVisiblePosition); max <= min; max++) {
            View childAt = getChildAt(max);
            if (childAt != null) {
                m1827a(firstVisiblePosition + max, childAt, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    @SuppressLint("WrongConstant")
    public void m1827a(int i, View view, boolean z) {
        int m1809c;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (i != this.f7598l && i != this.f7595i && i != this.f7596j) {
            m1809c = -2;
        } else {
            m1809c = m1809c(i, view, z);
        }
        if (m1809c != layoutParams.height) {
            layoutParams.height = m1809c;
            view.setLayoutParams(layoutParams);
        }
        if (i == this.f7595i || i == this.f7596j) {
            if (i < this.f7598l) {
                ((DragSortItemView) view).setGravity(80);
            } else if (i > this.f7598l) {
                ((DragSortItemView) view).setGravity(48);
            }
        }
        int visibility = view.getVisibility();
        int i2 = 0;
        if (i == this.f7598l && this.f7586a != null) {
            i2 = 4;
        }
        if (i2 != visibility) {
            view.setVisibility(i2);
        }
    }

    /* renamed from: b */
    private int m1818b(int i) {
        View view;
        if (i == this.f7598l) {
            return 0;
        }
        View childAt = getChildAt(i - getFirstVisiblePosition());
        if (childAt != null) {
            return m1816b(i, childAt, false);
        }
        int m1771a = this.f7584W.m1771a(i);
        if (m1771a == -1) {
            ListAdapter adapter = getAdapter();
            int itemViewType = adapter.getItemViewType(i);
            int viewTypeCount = adapter.getViewTypeCount();
            if (viewTypeCount != this.f7609w.length) {
                this.f7609w = new View[viewTypeCount];
            }
            if (itemViewType >= 0) {
                if (this.f7609w[itemViewType] == null) {
                    view = adapter.getView(i, null, this);
                    this.f7609w[itemViewType] = view;
                } else {
                    view = adapter.getView(i, this.f7609w[itemViewType], this);
                }
            } else {
                view = adapter.getView(i, null, this);
            }
            int m1816b = m1816b(i, view, true);
            this.f7584W.m1770a(i, m1816b);
            return m1816b;
        }
        return m1771a;
    }

    /* renamed from: b */
    private int m1816b(int i, View view, boolean z) {
        if (i == this.f7598l) {
            return 0;
        }
        if (i >= getHeaderViewsCount() && i < getCount() - getFooterViewsCount()) {
            view = ((ViewGroup) view).getChildAt(0);
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null && layoutParams.height > 0) {
            return layoutParams.height;
        }
        int height = view.getHeight();
        if (height == 0 || z) {
            m1825a(view);
            return view.getMeasuredHeight();
        }
        return height;
    }

    /* renamed from: c */
    private int m1809c(int i, View view, boolean z) {
        return m1810c(i, m1816b(i, view, z));
    }

    /* renamed from: c */
    private int m1810c(int i, int i2) {
        boolean z = this.f7597k && this.f7595i != this.f7596j;
        int i3 = this.f7606t - this.f7605s;
        int i4 = (int) (this.f7580S * i3);
        if (i == this.f7598l) {
            if (this.f7598l == this.f7595i) {
                if (z) {
                    return i4 + this.f7605s;
                }
                return this.f7606t;
            } else if (this.f7598l == this.f7596j) {
                return this.f7606t - i4;
            } else {
                return this.f7605s;
            }
        } else if (i == this.f7595i) {
            if (z) {
                return i2 + i4;
            }
            return i2 + i3;
        } else if (i == this.f7596j) {
            return (i2 + i3) - i4;
        } else {
            return i2;
        }
    }

    @Override // android.widget.AbsListView, android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (!this.f7582U) {
            super.requestLayout();
        }
    }

    /* renamed from: a */
    private int m1829a(int i, View view, int i2, int i3) {
        int i4;
        int i5;
        int m1818b = m1818b(i);
        int height = view.getHeight();
        int m1810c = m1810c(i, m1818b);
        if (i != this.f7598l) {
            i5 = height - m1818b;
            i4 = m1810c - m1818b;
        } else {
            i4 = m1810c;
            i5 = height;
        }
        int i6 = this.f7606t;
        if (this.f7598l != this.f7595i && this.f7598l != this.f7596j) {
            i6 -= this.f7605s;
        }
        if (i <= i2) {
            if (i > this.f7595i) {
                return (i6 - i4) + 0;
            }
        } else if (i == i3) {
            if (i <= this.f7595i) {
                return (i5 - i6) + 0;
            }
            if (i == this.f7596j) {
                return (height - m1810c) + 0;
            }
            return 0 + i5;
        } else if (i <= this.f7595i) {
            return 0 - i6;
        } else {
            if (i == this.f7596j) {
                return 0 - i4;
            }
        }
        return 0;
    }

    /* renamed from: a */
    private void m1825a(View view) {
        int makeMeasureSpec;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new AbsListView.LayoutParams(-1, -2);
            view.setLayoutParams(layoutParams);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(this.f7608v, getListPaddingLeft() + getListPaddingRight(), layoutParams.width);
        if (layoutParams.height > 0) {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, MeasureSpec.EXACTLY);
        } else {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        view.measure(childMeasureSpec, makeMeasureSpec);
    }

    /* renamed from: l */
    private void m1790l() {
        if (this.f7586a != null) {
            m1825a(this.f7586a);
            this.f7606t = this.f7586a.getMeasuredHeight();
            this.f7607u = this.f7606t / 2;
        }
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f7586a != null) {
            if (this.f7586a.isLayoutRequested()) {
                m1790l();
            }
            this.f7591e = true;
        }
        this.f7608v = i;
    }

    @Override // android.widget.ListView, android.widget.AbsListView
    protected void layoutChildren() {
        super.layoutChildren();
        if (this.f7586a != null) {
            if (this.f7586a.isLayoutRequested() && !this.f7591e) {
                m1790l();
            }
            this.f7586a.layout(0, 0, this.f7586a.getMeasuredWidth(), this.f7586a.getMeasuredHeight());
            this.f7591e = false;
        }
    }

    /* renamed from: a */
    protected boolean m1826a(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & 255) {
            case 1:
                if (this.f7604r == 3) {
                    m1819b();
                }
                m1796i();
                return true;
            case 2:
                m1817b((int) motionEvent.getX(), (int) motionEvent.getY());
                return true;
            case 3:
                if (this.f7604r == 3) {
                    m1835a();
                }
                m1796i();
                return true;
            default:
                return true;
        }
    }

    /* renamed from: a */
    public boolean m1831a(int i, int i2, int i3, int i4) {
        View mo1420c;
        if (!this.f7575N || this.f7576O == null || (mo1420c = this.f7576O.mo1420c(i)) == null) {
            return false;
        }
        return m1828a(i, mo1420c, i2, i3, i4);
    }

    /* renamed from: a */
    public boolean m1828a(int i, View view, int i2, int i3, int i4) {
        if (this.f7604r == 0 && this.f7575N && this.f7586a == null && view != null && this.f7603q) {
            if (getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
            int headerViewsCount = getHeaderViewsCount() + i;
            this.f7595i = headerViewsCount;
            this.f7596j = headerViewsCount;
            this.f7598l = headerViewsCount;
            this.f7594h = headerViewsCount;
            this.f7604r = 3;
            this.f7573L = 0;
            this.f7573L |= i2;
            this.f7586a = view;
            m1790l();
            this.f7599m = i3;
            this.f7600n = i4;
            this.f7588b.x = this.f7570I - this.f7599m;
            this.f7588b.y = this.f7571J - this.f7600n;
            View childAt = getChildAt(this.f7598l - getFirstVisiblePosition());
            if (childAt != null) {
                childAt.setVisibility(View.INVISIBLE);
            }
            switch (this.f7578Q) {
                case 1:
                    super.onTouchEvent(this.f7577P);
                    break;
                case 2:
                    super.onInterceptTouchEvent(this.f7577P);
                    break;
            }
            requestLayout();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1820a(boolean z) {
        int firstVisiblePosition = getFirstVisiblePosition() + (getChildCount() / 2);
        View childAt = getChildAt(getChildCount() / 2);
        if (childAt != null) {
            m1806d(firstVisiblePosition, childAt, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m1806d(int i, View view, boolean z) {
        this.f7582U = true;
        m1788m();
        int i2 = this.f7595i;
        int i3 = this.f7596j;
        boolean m1804e = m1804e();
        if (m1804e) {
            m1792k();
            setSelectionFromTop(i, (m1829a(i, view, i2, i3) + view.getTop()) - getPaddingTop());
            layoutChildren();
        }
        if (m1804e || z) {
            invalidate();
        }
        this.f7582U = false;
    }

    /* renamed from: m */
    private void m1788m() {
        if (this.f7576O != null) {
            this.f7589c.set(this.f7570I, this.f7571J);
            this.f7576O.mo1421a(this.f7586a, this.f7588b, this.f7589c);
        }
        int i = this.f7588b.x;
        int i2 = this.f7588b.y;
        int paddingLeft = getPaddingLeft();
        if ((this.f7573L & 1) == 0 && i > paddingLeft) {
            this.f7588b.x = paddingLeft;
        } else if ((this.f7573L & 2) == 0 && i < paddingLeft) {
            this.f7588b.x = paddingLeft;
        }
        int headerViewsCount = getHeaderViewsCount();
        int footerViewsCount = getFooterViewsCount();
        int firstVisiblePosition = getFirstVisiblePosition();
        int lastVisiblePosition = getLastVisiblePosition();
        int paddingTop = getPaddingTop();
        if (firstVisiblePosition < headerViewsCount) {
            paddingTop = getChildAt((headerViewsCount - firstVisiblePosition) - 1).getBottom();
        }
        if ((this.f7573L & 8) == 0 && firstVisiblePosition <= this.f7598l) {
            paddingTop = Math.max(getChildAt(this.f7598l - firstVisiblePosition).getTop(), paddingTop);
        }
        int height = getHeight() - getPaddingBottom();
        if (lastVisiblePosition >= (getCount() - footerViewsCount) - 1) {
            height = getChildAt(((getCount() - footerViewsCount) - 1) - firstVisiblePosition).getBottom();
        }
        if ((this.f7573L & 4) == 0 && lastVisiblePosition >= this.f7598l) {
            height = Math.min(getChildAt(this.f7598l - firstVisiblePosition).getBottom(), height);
        }
        if (i2 < paddingTop) {
            this.f7588b.y = paddingTop;
        } else if (this.f7606t + i2 > height) {
            this.f7588b.y = height - this.f7606t;
        }
        this.f7590d = this.f7588b.y + this.f7607u;
    }

    /* renamed from: n */
    private void m1786n() {
        if (this.f7586a != null) {
            this.f7586a.setVisibility(View.GONE);
            if (this.f7576O != null) {
                this.f7576O.mo1422a(this.f7586a);
            }
            this.f7586a = null;
            invalidate();
        }
    }

    public void setDragListener(DragListener dragListener) {
        this.dragListener = dragListener;
    }

    /* renamed from: d */
    public boolean m1807d() {
        return this.f7603q;
    }

    public void setDropListener(DropListener dropListener) {
        this.f7602p = dropListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.widget.DraggableListView$d */
    /* loaded from: classes.dex */
    public class RunnableC2179d implements Runnable {

        /* renamed from: b */
        private boolean f7620b;

        /* renamed from: c */
        private long f7621c;

        /* renamed from: d */
        private long f7622d;

        /* renamed from: e */
        private int f7623e;

        /* renamed from: f */
        private float f7624f;

        /* renamed from: g */
        private long f7625g;

        /* renamed from: h */
        private int f7626h;

        /* renamed from: i */
        private float f7627i;

        /* renamed from: j */
        private boolean f7628j = false;

        /* renamed from: a */
        public boolean m1778a() {
            return this.f7628j;
        }

        /* renamed from: b */
        public int m1775b() {
            if (this.f7628j) {
                return this.f7626h;
            }
            return -1;
        }

        public RunnableC2179d() {
        }

        /* renamed from: a */
        public void m1777a(int i) {
            if (!this.f7628j) {
                this.f7620b = false;
                this.f7628j = true;
                this.f7625g = SystemClock.uptimeMillis();
                this.f7621c = this.f7625g;
                this.f7626h = i;
                DraggableListView.this.post(this);
            }
        }

        /* renamed from: a */
        public void m1776a(boolean z) {
            if (z) {
                DraggableListView.this.removeCallbacks(this);
                this.f7628j = false;
                return;
            }
            this.f7620b = true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f7620b) {
                this.f7628j = false;
                return;
            }
            int firstVisiblePosition = DraggableListView.this.getFirstVisiblePosition();
            int lastVisiblePosition = DraggableListView.this.getLastVisiblePosition();
            int count = DraggableListView.this.getCount();
            int paddingTop = DraggableListView.this.getPaddingTop();
            int height = (DraggableListView.this.getHeight() - paddingTop) - DraggableListView.this.getPaddingBottom();
            int min = Math.min(DraggableListView.this.f7571J, DraggableListView.this.f7590d + DraggableListView.this.f7607u);
            int max = Math.max(DraggableListView.this.f7571J, DraggableListView.this.f7590d - DraggableListView.this.f7607u);
            if (this.f7626h == 0) {
                View childAt = DraggableListView.this.getChildAt(0);
                if (childAt == null) {
                    this.f7628j = false;
                    return;
                } else if (firstVisiblePosition != 0 || childAt.getTop() != paddingTop) {
                    this.f7627i = DraggableListView.this.f7569H.mo1779a((DraggableListView.this.f7565D - max) / DraggableListView.this.f7566E, this.f7621c);
                } else {
                    this.f7628j = false;
                    return;
                }
            } else {
                View childAt2 = DraggableListView.this.getChildAt(lastVisiblePosition - firstVisiblePosition);
                if (childAt2 == null) {
                    this.f7628j = false;
                    return;
                } else if (lastVisiblePosition != count - 1 || childAt2.getBottom() > height + paddingTop) {
                    this.f7627i = -DraggableListView.this.f7569H.mo1779a((min - DraggableListView.this.f7564C) / DraggableListView.this.f7567F, this.f7621c);
                } else {
                    this.f7628j = false;
                    return;
                }
            }
            this.f7622d = SystemClock.uptimeMillis();
            this.f7624f = (float) (this.f7622d - this.f7621c);
            this.f7623e = Math.round(this.f7627i * this.f7624f);
            if (this.f7623e >= 0) {
                this.f7623e = Math.min(height, this.f7623e);
                lastVisiblePosition = firstVisiblePosition;
            } else {
                this.f7623e = Math.max(-height, this.f7623e);
            }
            View childAt3 = DraggableListView.this.getChildAt(lastVisiblePosition - firstVisiblePosition);
            int top = childAt3.getTop() + this.f7623e;
            if (lastVisiblePosition == 0 && top > paddingTop) {
                top = paddingTop;
            }
            DraggableListView.this.f7582U = true;
            DraggableListView.this.setSelectionFromTop(lastVisiblePosition, top - paddingTop);
            DraggableListView.this.layoutChildren();
            DraggableListView.this.invalidate();
            DraggableListView.this.f7582U = false;
            DraggableListView.this.m1806d(lastVisiblePosition, childAt3, false);
            this.f7621c = this.f7622d;
            DraggableListView.this.post(this);
        }
    }

    public void setDragStartViewId(int i) {
        if (this.f7576O instanceof DragSortController) {
            ((DragSortController) this.f7576O).m1430a(i);
        }
    }
}
