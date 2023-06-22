package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;

import android.util.AttributeSet;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.Billboards;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeFramework;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
public class HotwordView extends ViewGroup {

    /* renamed from: a */
    private static final int f7704a = DisplayUtils.m7229a(4);

    /* renamed from: b */
    private int f7705b;

    /* renamed from: c */
    private int f7706c;

    /* renamed from: d */
    private int f7707d;

    /* renamed from: e */
    private boolean f7708e;

    /* renamed from: f */
    private InterfaceC2194a f7709f;

    /* renamed from: g */
    private View.OnClickListener f7710g;

    /* renamed from: com.sds.android.ttpod.widget.HotwordView$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2194a {
        /* renamed from: a */
        void mo1726a(String str);
    }

    public HotwordView(Context context) {
        super(context);
        this.f7708e = false;
        this.f7710g = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.HotwordView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HotwordView.this.f7709f != null) {
                    HotwordView.this.f7709f.mo1726a(((TextView) view).getText().toString());
                }
            }
        };
        m1730a(context);
    }

    public HotwordView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7708e = false;
        this.f7710g = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.HotwordView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HotwordView.this.f7709f != null) {
                    HotwordView.this.f7709f.mo1726a(((TextView) view).getText().toString());
                }
            }
        };
        m1730a(context);
    }

    public HotwordView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7708e = false;
        this.f7710g = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.HotwordView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HotwordView.this.f7709f != null) {
                    HotwordView.this.f7709f.mo1726a(((TextView) view).getText().toString());
                }
            }
        };
        m1730a(context);
    }

    /* renamed from: a */
    private void m1730a(Context context) {
        this.f7705b = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8.0f, getResources().getDisplayMetrics());
    }

    public void setMargin(int i) {
        this.f7705b = i;
        requestLayout();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        if (childCount != 0) {
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            int i8 = i3 - i;
            int measuredHeight = getChildAt(0).getMeasuredHeight();
            int i9 = 0;
            while (i6 < childCount) {
                View childAt = getChildAt(i6);
                int measuredWidth = childAt.getMeasuredWidth();
                if (i5 > 0) {
                    i5 += this.f7705b;
                }
                if (i5 == 0 || i5 + measuredWidth <= i8) {
                    childAt.layout(i5, i9, i5 + measuredWidth, i9 + measuredHeight);
                    i5 += measuredWidth;
                    i6++;
                } else {
                    i7++;
                    i9 += this.f7705b + measuredHeight;
                    i5 = 0;
                }
                if (this.f7708e && i7 >= this.f7707d) {
                    break;
                }
            }
            while (i6 < childCount) {
                getChildAt(i6).layout(i3, i4, i3, i4);
                i6++;
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        int size = View.MeasureSpec.getSize(i);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
        if (childCount > 0) {
            this.f7706c = 1;
            int i3 = 0;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                childAt.measure(makeMeasureSpec, makeMeasureSpec);
                int measuredWidth = i3 + childAt.getMeasuredWidth();
                if (measuredWidth < size) {
                    i3 = measuredWidth + this.f7705b;
                } else {
                    this.f7706c++;
                    i3 = childAt.getMeasuredWidth();
                }
            }
            int measuredHeight = getChildAt(0).getMeasuredHeight();
            if (this.f7708e) {
                this.f7706c = this.f7707d;
            }
            setMeasuredDimension(size, (measuredHeight * this.f7706c) + (this.f7705b * (this.f7706c - 1)));
            return;
        }
        super.onMeasure(i, i2);
    }

    public void setRows(int i) {
        this.f7707d = i;
        this.f7708e = true;
    }

    /* renamed from: a */
    private void m1731a(int i) {
        int childCount = getChildCount();
        if (childCount > i) {
            for (int i2 = childCount - 1; i2 >= i; i2--) {
                removeViewAt(i2);
            }
        } else if (childCount < i) {
            while (childCount < i) {
                addView(m1728b(childCount));
                childCount++;
            }
        }
    }

    /* renamed from: b */
    private View m1728b(int i) {
        View inflate = View.inflate(getContext(), R.layout.fragment_search_result_hotword, null);
        inflate.setOnClickListener(this.f7710g);
        setTheme(inflate);
        return inflate;
    }

    public void setContent(List<Billboards> list) {
        int i;
        int size = list != null ? list.size() : 0;
        if (size > 10) {
            Collections.shuffle(list);
            list = list.subList(0, 10);
            i = list.size();
        } else {
            i = size;
        }
        m1731a(i);
        if (i > 0) {
            Collections.sort(list, new Comparator<Billboards>() { // from class: com.sds.android.ttpod.widget.HotwordView.1
                @Override // java.util.Comparator
                /* renamed from: a */
                public int compare(Billboards billboards, Billboards billboards2) {
                    return billboards.getWord().length() - billboards2.getWord().length();
                }
            });
            for (int i2 = 0; i2 < i; i2++) {
                ((TextView) getChildAt(i2)).setText(list.get(i2).getWord());
            }
        }
    }

    public void setStringList(List<String> list) {
        int i;
        int size = list != null ? list.size() : 0;
        if (size > 10) {
            list = list.subList(0, 10);
            i = list.size();
        } else {
            i = size;
        }
        if (i > 0) {
            m1731a(i);
            for (int i2 = 0; i2 < i; i2++) {
                ((TextView) getChildAt(i2)).setText(list.get(i2));
            }
        }
    }

    public void setListener(InterfaceC2194a interfaceC2194a) {
        this.f7709f = interfaceC2194a;
    }

    private void setTheme(View view) {
        ThemeManager.m3269a(view, ThemeElement.SONG_LIST_ITEM_TEXT);
        Drawable backgroundDrawable = getBackgroundDrawable();
        if (backgroundDrawable != null) {
            view.setBackgroundDrawable(backgroundDrawable);
        } else {
            ThemeManager.m3269a(view, ThemeElement.TILE_MASK);
        }
    }

    public Drawable getBackgroundDrawable() {
        ShapeDrawable shapeDrawable;
        ShapeDrawable shapeDrawable2;
        ThemeFramework.AbstractC2016e m3258b = ThemeManager.m3258b(ThemeElement.TILE_MASK);
        if (m3258b instanceof ThemeFramework.C2014c) {
            ColorDrawable colorDrawable = (ColorDrawable) m3258b.m3289c();
            ColorDrawable colorDrawable2 = (ColorDrawable) m3258b.m3288d();
            float[] fArr = {f7704a, f7704a, f7704a, f7704a, f7704a, f7704a, f7704a, f7704a};
            if (colorDrawable != null) {
                ShapeDrawable shapeDrawable3 = new ShapeDrawable(new RoundRectShape(fArr, null, null));
                shapeDrawable3.getPaint().setColor(ThemeManager.m3272a(colorDrawable));
                shapeDrawable = shapeDrawable3;
            } else {
                shapeDrawable = null;
            }
            if (colorDrawable2 != null) {
                ShapeDrawable shapeDrawable4 = new ShapeDrawable(new RoundRectShape(fArr, null, null));
                shapeDrawable4.getPaint().setColor(ThemeManager.m3272a(colorDrawable2));
                shapeDrawable2 = shapeDrawable4;
            } else {
                shapeDrawable2 = null;
            }
            if (shapeDrawable == null || shapeDrawable2 == null) {
                return shapeDrawable == null ? shapeDrawable2 : shapeDrawable;
            }
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, shapeDrawable2);
            stateListDrawable.addState(new int[]{-16842910}, shapeDrawable2);
            stateListDrawable.addState(StateSet.WILD_CARD, shapeDrawable);
            shapeDrawable2.setPadding(null);
            shapeDrawable.setPadding(null);
            return stateListDrawable;
        }
        return null;
    }

    /* renamed from: a */
    public void m1732a() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            setTheme(getChildAt(childCount));
        }
    }
}
