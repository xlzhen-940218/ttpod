package com.sds.android.ttpod.widget.expandablelist;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.common.widget.CustomClickIconTextView;
import com.sds.android.ttpod.common.widget.IconTextView;
import java.util.BitSet;

/* loaded from: classes.dex */
public abstract class AbstractExpandableListAdapter extends WrapperListAdapterImpl {

    /* renamed from: a */
    private View f8310a;

    /* renamed from: c */
    private int f8311c;

    /* renamed from: d */
    private int f8312d;

    /* renamed from: e */
    private BitSet f8313e;

    /* renamed from: f */
    private final SparseIntArray f8314f;

    /* renamed from: g */
    private ViewGroup f8315g;

    /* renamed from: h */
    private InterfaceC2279a f8316h;

    /* renamed from: com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2279a {
        void onCollapse(View view, int i);

        void onExpand(View view, int i);
    }

    /* renamed from: a */
    public abstract View mo1258a(View view);

    /* renamed from: b */
    public abstract View mo1257b(View view);

    public AbstractExpandableListAdapter(ListAdapter listAdapter) {
        super(listAdapter);
        this.f8310a = null;
        this.f8311c = -1;
        this.f8312d = 200;
        this.f8313e = new BitSet();
        this.f8314f = new SparseIntArray(10);
    }

    /* renamed from: a */
    public void m1283a(InterfaceC2279a interfaceC2279a) {
        this.f8316h = interfaceC2279a;
    }

    /* renamed from: a */
    public int m1292a() {
        return this.f8311c;
    }

    /* renamed from: a */
    private void m1291a(int i, View view, int i2) {
        if (this.f8316h != null) {
            if (i == 0) {
                this.f8316h.onExpand(view, i2);
            } else if (i == 1) {
                this.f8316h.onCollapse(view, i2);
            }
        }
    }

    @Override // com.sds.android.ttpod.widget.expandablelist.WrapperListAdapterImpl, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        this.f8315g = viewGroup;
        View view2 = this.f8340b.getView(i, view, viewGroup);
        m1287a(view2, i);
        return view2;
    }

    /* renamed from: b */
    public int m1279b() {
        return this.f8312d;
    }

    /* renamed from: c */
    public boolean m1274c() {
        return this.f8311c != -1;
    }

    /* renamed from: a */
    private void m1287a(View view, int i) {
        View mo1258a = mo1258a(view);
        View mo1257b = mo1257b(view);
        if (mo1258a != null && mo1257b != null) {
            mo1257b.measure(view.getWidth(), view.getHeight());
            m1275b(mo1258a, mo1257b, i);
            mo1257b.requestLayout();
        }
    }

    /* renamed from: a */
    private void m1286a(final View view, View view2, int i) {
        Animation animation = view2.getAnimation();
        if (animation != null && animation.hasStarted() && !animation.hasEnded()) {
            animation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation2) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation2) {
                    view.performClick();
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation2) {
                }
            });
            return;
        }
        view2.setAnimation(null);
        int i2 = view2.getVisibility() == View.VISIBLE ? 1 : 0;
        if (i2 == 0) {
            this.f8313e.set(i, true);
        } else {
            this.f8313e.set(i, false);
        }
        if (i2 == 0) {
            if (this.f8311c != -1 && this.f8311c != i) {
                if (this.f8310a != null) {
                    m1276b(this.f8310a, 1);
                    m1291a(1, this.f8310a, this.f8311c);
                }
                this.f8313e.set(this.f8311c, false);
            }
            this.f8310a = view2;
            this.f8311c = i;
        } else if (this.f8311c == i) {
            this.f8311c = -1;
        }
        m1276b(view2, i2);
        m1291a(i2, view2, i);
    }

    /* renamed from: b */
    private void m1276b(final View view, final int i) {
        ExpandCollapseAnimation expandCollapseAnimation = new ExpandCollapseAnimation(view, i);
        expandCollapseAnimation.setDuration(m1279b());
        expandCollapseAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (i == 0 && (AbstractExpandableListAdapter.this.f8315g instanceof ListView)) {
                    ListView listView = (ListView) AbstractExpandableListAdapter.this.f8315g;
                    int bottom = view.getBottom();
                    Rect rect = new Rect();
                    boolean globalVisibleRect = view.getGlobalVisibleRect(rect);
                    Rect rect2 = new Rect();
                    listView.getGlobalVisibleRect(rect2);
                    if (!globalVisibleRect) {
                        listView.smoothScrollBy(bottom, AbstractExpandableListAdapter.this.m1279b());
                    } else if (rect2.bottom == rect.bottom) {
                        listView.smoothScrollBy(bottom, AbstractExpandableListAdapter.this.m1279b());
                    }
                }
            }
        });
        view.startAnimation(expandCollapseAnimation);
    }

    /* renamed from: c */
    private void m1273c(View view, int i) {
        int i2 = view.getVisibility() == View.VISIBLE ? 1 : 0;
        if (i2 == 0) {
            this.f8313e.set(i, true);
        } else {
            this.f8313e.set(i, false);
        }
        if (i2 == 0) {
            if (this.f8311c != -1 && this.f8311c != i) {
                if (this.f8310a != null) {
                    m1271e(this.f8310a, 1);
                    m1291a(1, this.f8310a, this.f8311c);
                }
                this.f8313e.set(this.f8311c, false);
            }
            this.f8310a = view;
            this.f8311c = i;
        } else if (this.f8311c == i) {
            this.f8311c = -1;
        }
        m1271e(view, i2);
        m1291a(i2, view, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1285a(View view, View view2, int i, boolean z) {
        if (z) {
            m1286a(view, view2, i);
        } else {
            m1273c(view2, i);
        }
    }

    /* renamed from: b */
    private void m1275b(View view, final View view2, final int i) {
        if (view2 == this.f8310a && i != this.f8311c) {
            this.f8310a = null;
        }
        if (i == this.f8311c) {
            this.f8310a = view2;
        }
        if (this.f8314f.get(i, -1) == -1) {
            this.f8314f.put(i, view2.getMeasuredHeight());
            m1272d(view2, i);
        } else {
            m1272d(view2, i);
        }
        if (!(view instanceof IconTextView) || !((CustomClickIconTextView) view).m7219a()) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view3) {
                    AbstractExpandableListAdapter.this.m1285a(view3, view2, i, false);
                }
            });
        }
    }

    /* renamed from: d */
    private void m1272d(View view, int i) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (this.f8313e.get(i)) {
            view.setVisibility(View.VISIBLE);
            layoutParams.bottomMargin = 0;
            return;
        }
        view.setVisibility(View.GONE);
        layoutParams.bottomMargin = 0 - this.f8314f.get(i);
    }

    /* renamed from: e */
    private void m1271e(View view, int i) {
        int measuredHeight = view.getMeasuredHeight();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (i == 0) {
            view.setVisibility(View.VISIBLE);
            layoutParams.bottomMargin = 0;
        } else {
            view.setVisibility(View.GONE);
            layoutParams.bottomMargin = -measuredHeight;
        }
        if (i == 0 && (this.f8315g instanceof ListView)) {
            ListView listView = (ListView) this.f8315g;
            Rect rect = new Rect();
            ((View) view.getParent()).getGlobalVisibleRect(rect);
            Rect rect2 = new Rect();
            listView.getGlobalVisibleRect(rect2);
            int measuredHeight2 = ((View) view.getParent()).getMeasuredHeight();
            if (!((rect.top + measuredHeight) + measuredHeight2 < rect2.bottom) && SDKVersionUtils.sdkThan8()) {
                listView.smoothScrollBy(((rect.top + measuredHeight) + measuredHeight2) - rect2.bottom, 0);
            }
        }
    }

    /* renamed from: a */
    public boolean m1280a(boolean z) {
        if (m1274c()) {
            if (this.f8310a != null) {
                if (z) {
                    m1276b(this.f8310a, 1);
                } else {
                    m1271e(this.f8310a, 1);
                }
            }
            this.f8313e.set(this.f8311c, false);
            m1291a(1, this.f8310a, this.f8311c);
            this.f8311c = -1;
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public Parcelable m1288a(Parcelable parcelable) {
        SavedState savedState = new SavedState(parcelable);
        savedState.f8326b = this.f8311c;
        savedState.f8325a = this.f8313e;
        return savedState;
    }

    /* renamed from: a */
    public void m1284a(SavedState savedState) {
        if (savedState == null) {
            return;
        }
        this.f8311c = savedState.f8326b;
        this.f8313e = savedState.f8325a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static BitSet m1278b(Parcel parcel) {
        BitSet bitSet = new BitSet();
        if (parcel != null) {
            int readInt = parcel.readInt();
            for (int i = 0; i < readInt; i++) {
                bitSet.set(parcel.readInt());
            }
        }
        return bitSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m1277b(Parcel parcel, BitSet bitSet) {
        if (parcel != null && bitSet != null) {
            parcel.writeInt(bitSet.cardinality());
            int i = -1;
            while (true) {
                i = bitSet.nextSetBit(i + 1);
                if (i != -1) {
                    parcel.writeInt(i);
                } else {
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        /* renamed from: a */
        private BitSet f8325a;

        /* renamed from: b */
        private int f8326b;

        SavedState(Parcelable parcelable) {
            super(parcelable);
            this.f8325a = null;
            this.f8326b = -1;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f8325a = null;
            this.f8326b = -1;
            this.f8326b = parcel.readInt();
            this.f8325a = AbstractExpandableListAdapter.m1278b(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f8326b);
            AbstractExpandableListAdapter.m1277b(parcel, this.f8325a);
        }
    }
}
