package com.sds.android.ttpod.widget.p140a;

import android.graphics.Bitmap;
import android.graphics.Point;
import androidx.core.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

/* renamed from: com.sds.android.ttpod.widget.a.d */
/* loaded from: classes.dex */
public class SimpleFloatViewController implements FloatViewController {

    /* renamed from: a */
    private Bitmap f8088a;

    /* renamed from: b */
    private ImageView f8089b;

    /* renamed from: c */
    private int f8090c = ViewCompat.MEASURED_STATE_MASK;

    /* renamed from: d */
    private ListView f8091d;

    public SimpleFloatViewController(ListView listView) {
        this.f8091d = listView;
    }

    /* renamed from: d */
    public void m1419d(int i) {
        this.f8090c = i;
    }

    @Override // com.sds.android.ttpod.widget.p140a.FloatViewController
    /* renamed from: c */
    public View mo1420c(int i) {
        View childAt = this.f8091d.getChildAt((this.f8091d.getHeaderViewsCount() + i) - this.f8091d.getFirstVisiblePosition());
        if (childAt == null) {
            return null;
        }
        childAt.setPressed(false);
        childAt.setDrawingCacheEnabled(true);
        Bitmap drawingCache = childAt.getDrawingCache();
        if (drawingCache != null) {
            this.f8088a = Bitmap.createBitmap(drawingCache);
        }
        childAt.setDrawingCacheEnabled(false);
        if (this.f8089b == null) {
            this.f8089b = new ImageView(this.f8091d.getContext());
        }
        this.f8089b.setBackgroundColor(this.f8090c);
        this.f8089b.setPadding(0, 0, 0, 0);
        if (this.f8088a != null) {
            this.f8089b.setImageBitmap(this.f8088a);
        }
        this.f8089b.setLayoutParams(new ViewGroup.LayoutParams(childAt.getWidth(), childAt.getHeight()));
        return this.f8089b;
    }

    @Override // com.sds.android.ttpod.widget.p140a.FloatViewController
    /* renamed from: a */
    public void mo1421a(View view, Point point, Point point2) {
    }

    @Override // com.sds.android.ttpod.widget.p140a.FloatViewController
    /* renamed from: a */
    public void mo1422a(View view) {
        ((ImageView) view).setImageDrawable(null);
        if (this.f8088a != null) {
            this.f8088a.recycle();
            this.f8088a = null;
        }
    }
}
