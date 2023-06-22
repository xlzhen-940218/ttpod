package com.sds.android.ttpod.widget.dragupdatelist;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.TTPodApplication;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.utils.TimeUtils;

import java.util.concurrent.TimeUnit;

/* renamed from: com.sds.android.ttpod.widget.dragupdatelist.a */
/* loaded from: classes.dex */
public class DragUpdateHelper implements ModifySizeNotifyLayout.InterfaceC2270a {

    /* renamed from: a */
    private float f8283a;

    /* renamed from: b */
    private boolean f8284b;

    /* renamed from: c */
    private ModifySizeNotifyLayout f8285c;

    /* renamed from: f */
    private InterfaceC2273c f8288f;

    /* renamed from: d */
    private C2271a f8286d = null;

    /* renamed from: e */
    private int f8287e = -1;

    /* renamed from: g */
    private InterfaceC2272b f8289g = null;

    /* compiled from: DragUpdateHelper.java */
    /* renamed from: com.sds.android.ttpod.widget.dragupdatelist.a$b */
    /* loaded from: classes.dex */
    public interface InterfaceC2272b {
        /* renamed from: a */
        void mo1302a(View view);

        /* renamed from: a */
        boolean mo1303a();

        /* renamed from: b */
        void mo1301b();

        /* renamed from: c */
        void mo1300c();
    }

    /* compiled from: DragUpdateHelper.java */
    /* renamed from: com.sds.android.ttpod.widget.dragupdatelist.a$c */
    /* loaded from: classes.dex */
    public interface InterfaceC2273c {
        void onStartRefreshEvent();
    }

    /* renamed from: a */
    public void m1323a(Context context, InterfaceC2272b interfaceC2272b) {
        if (interfaceC2272b == null) {
            throw new IllegalArgumentException("OnDragUpdateListener can not be null");
        }
        this.f8285c = (ModifySizeNotifyLayout) View.inflate(context, R.layout.drag_update_list_header, null);
        this.f8285c.setOnShowStateChangedListener(this);
        this.f8289g = interfaceC2272b;
    }

    /* renamed from: a */
    public void m1320a(InterfaceC2273c interfaceC2273c) {
        this.f8288f = interfaceC2273c;
    }

    /* renamed from: a */
    public void m1324a(int i) {
        if (this.f8286d != null) {
            this.f8286d.m1312a(i);
        }
    }

    /* renamed from: a */
    public void m1322a(ColorStateList colorStateList) {
        if (this.f8286d != null) {
            this.f8286d.m1310a(colorStateList);
        }
    }

    /* renamed from: a */
    public void m1326a() {
        this.f8285c.m1333a();
    }

    /* renamed from: b */
    public void m1319b() {
        if (this.f8286d == null) {
            this.f8286d = new C2271a(this.f8285c.findViewById(R.id.drag_update_layout));
            this.f8289g.mo1302a(this.f8285c);
        }
    }

    /* renamed from: a */
    public void m1321a(MotionEvent motionEvent) {
        boolean z = true;
        switch (motionEvent.getAction()) {
            case 0:
                this.f8284b = false;
                return;
            case 1:
            case 3:
            case 4:
                int showState = this.f8285c.getShowState();
                if (showState != 2 && showState != 1) {
                    z = false;
                }
                if (this.f8284b && z) {
                    this.f8284b = false;
                    this.f8289g.mo1300c();
                    this.f8285c.m1333a();
                    return;
                }
                return;
            case 2:
                float y = motionEvent.getY();
                if (this.f8284b) {
                    if (!this.f8289g.mo1303a()) {
                        this.f8284b = false;
                    }
                } else if (this.f8289g.mo1303a()) {
                    this.f8283a = y;
                    this.f8284b = true;
                }
                if (this.f8284b) {
                    float f = y - this.f8283a;
                    this.f8285c.m1332a(m1325a(f));
                    if (f > 1.0f) {
                        this.f8289g.mo1301b();
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private int m1325a(float f) {
        ModifySizeNotifyLayout modifySizeNotifyLayout = this.f8285c;
        int paddingBottom = modifySizeNotifyLayout.getPaddingBottom() + modifySizeNotifyLayout.getChildAt(0).getMeasuredHeight() + modifySizeNotifyLayout.getPaddingTop();
        if (f > paddingBottom) {
            f = ((f - paddingBottom) * 0.2f) + paddingBottom;
        }
        return (int) f;
    }

    /* renamed from: c */
    public int m1317c() {
        return this.f8285c.getHeight();
    }

    @Override // com.sds.android.ttpod.widget.dragupdatelist.ModifySizeNotifyLayout.InterfaceC2270a
    /* renamed from: b */
    public void mo1318b(int i) {
        switch (i) {
            case 0:
                this.f8286d.m1313a();
                break;
            case 1:
                if (this.f8287e == 0) {
                    this.f8286d.m1313a();
                    break;
                } else {
                    this.f8286d.m1308b();
                    break;
                }
            case 2:
                this.f8286d.m1306c();
                break;
            case 3:
                this.f8286d.m1305d();
                break;
            case 5:
                if (this.f8288f != null) {
                    this.f8288f.onStartRefreshEvent();
                    break;
                }
                break;
        }
        this.f8287e = i;
    }

    /* renamed from: d */
    public TextView m1316d() {
        if (this.f8286d == null) {
            return null;
        }
        return this.f8286d.f8292c;
    }

    /* renamed from: e */
    public TextView m1315e() {
        if (this.f8286d != null) {
            TextView textView = this.f8286d.f8293d;
            if (textView.getVisibility() != View.VISIBLE) {
                textView.setVisibility(View.VISIBLE);
                return textView;
            }
            return textView;
        }
        return null;
    }

    /* renamed from: f */
    public void m1314f() {
        this.f8286d.m1311a(System.currentTimeMillis());
    }

    /* compiled from: DragUpdateHelper.java */
    /* renamed from: com.sds.android.ttpod.widget.dragupdatelist.a$a */
    /* loaded from: classes.dex */
    public static class C2271a {

        /* renamed from: a */
        private long f8290a;

        /* renamed from: b */
        private ImageView f8291b;

        /* renamed from: c */
        private TextView f8292c;

        /* renamed from: d */
        private TextView f8293d;

        /* renamed from: e */
        private Animation f8294e;

        /* renamed from: f */
        private Animation f8295f = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);

        /* renamed from: g */
        private Animation f8296g;

        /* renamed from: h */
        private String f8297h;

        /* renamed from: i */
        private String f8298i;

        public C2271a(View view) {
            this.f8291b = (ImageView) view.findViewById(R.id.online_refresh_icon);
            this.f8292c = (TextView) view.findViewById(R.id.online_refresh_title);
            this.f8293d = (TextView) view.findViewById(R.id.online_refresh_content);
            this.f8294e = AnimationUtils.loadAnimation(view.getContext(), R.anim.rotate);
            this.f8295f.setDuration(500L);
            this.f8295f.setFillEnabled(true);
            this.f8295f.setFillAfter(true);
            this.f8296g = new RotateAnimation(180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
            this.f8296g.setDuration(500L);
            this.f8296g.setFillEnabled(true);
            this.f8296g.setFillAfter(true);
            this.f8290a = 0L;
            BaseApplication c = TTPodApplication.getApplication();
            this.f8297h = c.getString(R.string.release_refresh);
            this.f8298i = c.getString(R.string.refreshing_prompt);
        }

        /* renamed from: a */
        public void m1312a(int i) {
            this.f8292c.setTextColor(i);
            this.f8292c.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        }

        /* renamed from: a */
        public void m1310a(ColorStateList colorStateList) {
            if (colorStateList != null) {
                this.f8292c.setTextColor(colorStateList);
                this.f8292c.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            }
        }

        /* renamed from: a */
        public void m1313a() {
            this.f8291b.clearAnimation();
            this.f8291b.setImageResource(R.drawable.img_drag_refresh);
            m1304e();
        }

        /* renamed from: e */
        private void m1304e() {
            if (this.f8290a == 0) {
                this.f8292c.setText(this.f8297h);
            } else {
                this.f8292c.setText(TimeUtils.m8155a(TTPodApplication.getApplication(), TimeUnit.MILLISECONDS.toSeconds(this.f8290a)).toString() + "刷新");
            }
        }

        /* renamed from: a */
        public void m1311a(long j) {
            this.f8290a = j;
        }

        /* renamed from: b */
        public void m1308b() {
            this.f8291b.clearAnimation();
            this.f8291b.setImageResource(R.drawable.img_drag_refresh);
            this.f8291b.startAnimation(this.f8295f);
            m1304e();
        }

        /* renamed from: c */
        public void m1306c() {
            this.f8291b.clearAnimation();
            this.f8291b.setImageResource(R.drawable.img_drag_refresh);
            this.f8291b.startAnimation(this.f8296g);
            m1304e();
        }

        /* renamed from: d */
        public void m1305d() {
            this.f8291b.clearAnimation();
            this.f8291b.setImageResource(R.drawable.img_drag_refresh);
            this.f8291b.startAnimation(this.f8294e);
            this.f8292c.setText(this.f8298i);
            this.f8290a = System.currentTimeMillis();
        }
    }
}
