package com.sds.android.ttpod.utils;

import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;

/* renamed from: com.sds.android.ttpod.a.m */
/* loaded from: classes.dex */
public class ListViewUtils {

    /* renamed from: a */
    private static boolean f2501a = false;

    /* renamed from: a */
    public static int m8266a(int i, int i2, int i3) {
        if (i < 0 || i2 < 0 || i3 < 0) {
            throw new IllegalArgumentException("headCount,srcPosition,total must be >= 0");
        }
        int i4 = i2 - i;
        if (i4 < 0 || i4 >= i3) {
            return -1;
        }
        return i4;
    }

    /* renamed from: b */
    public static boolean m8262b(int i, int i2, int i3) {
        return i3 >= i2 && i3 - i <= i2;
    }

    /* renamed from: a */
    public static void m8263a(boolean z, BaseAdapter baseAdapter) {
        f2501a = z;
        if (!f2501a) {
            baseAdapter.notifyDataSetChanged();
        }
    }

    /* compiled from: ListViewUtils.java */
    /* renamed from: com.sds.android.ttpod.a.m$a */
    /* loaded from: classes.dex */
    public static class C0631a implements AbsListView.OnScrollListener {
        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            Object adapter = absListView.getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            if (adapter != null) {
                ListViewUtils.m8263a(i != 0, (BaseAdapter) adapter);
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }
    }

    /* renamed from: a */
    public static boolean m8265a(ImageView imageView, int i) {
        if (f2501a) {
            if (i > 0) {
                imageView.setImageResource(i);
            }
            imageView.setTag(" ");
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m8264a(final ActionExpandableListView actionExpandableListView) {
        return actionExpandableListView != null && actionExpandableListView.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.a.m.1
            @Override // java.lang.Runnable
            public void run() {
                actionExpandableListView.mo1259f();
            }
        }, 200L);
    }
}
