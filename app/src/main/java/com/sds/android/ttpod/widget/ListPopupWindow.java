package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.widget.d */
/* loaded from: classes.dex */
public class ListPopupWindow extends PopupWindow {

    /* renamed from: b */
    private static ArrayList<ListPopupWindow> f8267b = new ArrayList<>();

    /* renamed from: a */
    private ListView f8268a;

    @Override // android.widget.PopupWindow
    public void showAtLocation(View view, int i, int i2, int i3) {
        if (view != null) {
            f8267b.add(this);
            super.showAtLocation(view, i, i2, i3);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2) {
        f8267b.add(this);
        super.showAsDropDown(view, i, i2);
    }

    @Override // android.widget.PopupWindow
    public void dismiss() {
        f8267b.remove(this);
        super.dismiss();
    }

    public ListPopupWindow(Context context, int i, int i2) {
        super(View.inflate(context, i, null), -2, -2, true);
        setWindowLayoutMode(0, -2);
        setAnimationStyle(android.R.style.Animation_Dialog);
        setBackgroundDrawable(new ColorDrawable(0));
        View contentView = getContentView();
        if (contentView != null) {
            this.f8268a = (ListView) contentView.findViewById(i2);
            contentView.setFocusableInTouchMode(true);
            contentView.setOnKeyListener(new View.OnKeyListener() { // from class: com.sds.android.ttpod.widget.d.1
                @Override // android.view.View.OnKeyListener
                public boolean onKey(View view, int i3, KeyEvent keyEvent) {
                    if (i3 == 4 || i3 == 82) {
                        if (keyEvent.getAction() == 1) {
                            ListPopupWindow.this.dismiss();
                            return true;
                        }
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    /* renamed from: a */
    public void m1338a(ListAdapter listAdapter) {
        this.f8268a.setAdapter(listAdapter);
    }

    /* renamed from: a */
    public void m1339a(AdapterView.OnItemClickListener onItemClickListener) {
        this.f8268a.setOnItemClickListener(onItemClickListener);
    }
}
