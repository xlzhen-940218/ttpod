package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import java.util.List;

/* renamed from: com.sds.android.ttpod.widget.a */
/* loaded from: classes.dex */
public class AutoCompleteView extends PopupWindow {

    /* renamed from: a */
    private static final int f8063a = DisplayUtils.m7229a(240);

    /* renamed from: b */
    private ViewGroup f8064b;

    /* renamed from: c */
    private Context f8065c;

    /* renamed from: d */
    private InterfaceC2253a f8066d;

    /* renamed from: e */
    private final ViewGroup f8067e;

    /* renamed from: f */
    private int f8068f;

    /* renamed from: g */
    private int f8069g;

    /* renamed from: h */
    private int f8070h;

    /* renamed from: i */
    private Drawable f8071i;

    /* renamed from: j */
    private int f8072j;

    /* renamed from: k */
    private Handler f8073k;

    /* renamed from: l */
    private View.OnClickListener f8074l;

    /* compiled from: AutoCompleteView.java */
    /* renamed from: com.sds.android.ttpod.widget.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2253a {
        /* renamed from: a */
        void mo1426a(String str);
    }

    /* renamed from: a */
    public void m1440a(int i, int i2) {
        this.f8068f = i;
        this.f8069g = i2;
    }

    /* renamed from: a */
    public void m1439a(Drawable drawable) {
        if (drawable != null) {
            this.f8072j = drawable.getIntrinsicHeight();
        } else {
            this.f8072j = 0;
        }
        this.f8071i = drawable;
    }

    /* renamed from: a */
    public void m1441a(int i) {
        this.f8072j = i;
    }

    /* renamed from: b */
    public void m1432b(int i) {
        this.f8070h = i;
    }

    public AutoCompleteView(Context context) {
        super(context);
        this.f8068f = 4;
        this.f8069g = this.f8068f << 1;
        this.f8070h = 10;
        this.f8073k = new Handler() { // from class: com.sds.android.ttpod.widget.a.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    AutoCompleteView.this.m1431c();
                }
            }
        };
        this.f8074l = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.a.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String obj = ((TextView) view).getText().toString();
                if (AutoCompleteView.this.f8066d != null) {
                    AutoCompleteView.this.f8066d.mo1426a(obj);
                }
            }
        };
        this.f8065c = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_auto_complete, (ViewGroup) null);
        setContentView(inflate);
        this.f8064b = (ViewGroup) inflate.findViewById(R.id.containerView);
        this.f8067e = (ViewGroup) inflate.findViewById(R.id.scroll_container);
        setBackgroundDrawable(new ColorDrawable(0));
        setAnimationStyle(R.style.auto_complete_popup);
        setTouchable(true);
        setOutsideTouchable(true);
    }

    /* renamed from: a */
    public void m1435a(String str) {
        boolean z = this.f8072j > 0 && this.f8071i != null;
        if (z) {
            View view = new View(this.f8065c);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, this.f8072j);
            view.setBackgroundDrawable(this.f8071i);
            this.f8064b.addView(view, layoutParams);
        }
        TextView textView = new TextView(this.f8065c);
        textView.setText(str);
        textView.setBackgroundResource(R.drawable.xml_listselector_item_default);
        textView.setTextColor(Color.parseColor("#666666"));
        textView.setTextSize(15.0f);
        textView.setPadding(10, this.f8070h, 0, this.f8070h);
        textView.setGravity(16);
        textView.setClickable(true);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        textView.setOnClickListener(this.f8074l);
        if (this.f8064b.getChildCount() > (z ? 6 : 0) + 6 && this.f8067e.getTag() == null) {
            ViewGroup.LayoutParams layoutParams3 = this.f8067e.getLayoutParams();
            layoutParams3.height = f8063a;
            if (z) {
                layoutParams3.height += this.f8072j * 6;
            }
            this.f8067e.setTag(Integer.valueOf(layoutParams3.height));
            this.f8067e.setLayoutParams(layoutParams3);
        }
        if (this.f8064b.getTag() != null) {
            ViewGroup.LayoutParams layoutParams4 = this.f8067e.getLayoutParams();
            layoutParams4.height = -2;
            this.f8067e.setLayoutParams(layoutParams4);
            this.f8067e.setTag(null);
        }
        this.f8064b.addView(textView, layoutParams2);
    }

    /* renamed from: a */
    public void m1442a() {
        if (this.f8064b.getChildCount() > 0) {
            this.f8064b.removeAllViews();
        }
    }

    /* renamed from: a */
    public void m1434a(List<String> list, boolean z) {
        if (!z) {
            m1442a();
        }
        int size = list != null ? list.size() : 0;
        for (int i = 0; i < size; i++) {
            m1435a(list.get(i));
        }
    }

    /* renamed from: a */
    public void m1437a(InterfaceC2253a interfaceC2253a) {
        this.f8066d = interfaceC2253a;
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view) {
        setWidth(view.getWidth() - this.f8068f);
        setHeight(-2);
        try {
            super.showAsDropDown(view, this.f8068f, -this.f8069g);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.widget.PopupWindow
    public void dismiss() {
        this.f8073k.sendEmptyMessageDelayed(1, 200L);
    }

    /* renamed from: b */
    public void m1433b() {
        this.f8073k.removeMessages(1);
    }

    /* renamed from: c */
    public void m1431c() {
        if (isShowing()) {
            try {
                super.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void m1438a(View view) {
        if (this.f8064b.getChildCount() == 0) {
            m1431c();
        } else if (!isShowing()) {
            showAsDropDown(view);
        }
    }
}
