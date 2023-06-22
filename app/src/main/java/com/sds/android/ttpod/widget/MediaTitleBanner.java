package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.widget.MediaController;

/* renamed from: com.sds.android.ttpod.widget.e */
/* loaded from: classes.dex */
public class MediaTitleBanner {

    /* renamed from: a */
    private PopupWindow f8299a;

    /* renamed from: b */
    private Context f8300b;

    /* renamed from: c */
    private int f8301c;

    /* renamed from: d */
    private View f8302d;

    /* renamed from: e */
    private View f8303e;

    /* renamed from: f */
    private ImageButton f8304f;

    /* renamed from: g */
    private TextView f8305g;

    /* renamed from: h */
    private MediaController.InterfaceC2201c f8306h;

    /* renamed from: i */
    private MediaController.InterfaceC2202d f8307i;

    /* renamed from: j */
    private Handler f8308j = new Handler() { // from class: com.sds.android.ttpod.widget.e.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    MediaTitleBanner.this.m1299a();
                    return;
                default:
                    return;
            }
        }
    };

    public MediaTitleBanner(Context context) {
        this.f8300b = context;
        this.f8303e = View.inflate(this.f8300b, R.layout.media_title_banner, null);
        this.f8304f = (ImageButton) this.f8303e.findViewById(R.id.title_back);
        this.f8305g = (TextView) this.f8303e.findViewById(R.id.title_content);
        m1293c();
    }

    /* renamed from: a */
    public void m1297a(View.OnClickListener onClickListener) {
        this.f8304f.setOnClickListener(onClickListener);
    }

    /* renamed from: c */
    private void m1293c() {
        this.f8299a = new PopupWindow(this.f8300b);
        this.f8299a.setFocusable(false);
        this.f8299a.setBackgroundDrawable(null);
        this.f8299a.setOutsideTouchable(true);
        this.f8301c = 16973824;
    }

    /* renamed from: a */
    public void m1296a(View view) {
        this.f8302d = view;
        this.f8299a.setContentView(this.f8303e);
        this.f8299a.setWidth(-1);
        this.f8299a.setHeight(-2);
    }

    /* renamed from: a */
    public void m1295a(CharSequence charSequence) {
        this.f8305g.setText(charSequence);
    }

    /* renamed from: a */
    public void m1299a() {
        if (this.f8299a.isShowing()) {
            try {
                this.f8299a.dismiss();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            if (this.f8306h != null) {
                this.f8306h.m1685a();
            }
        }
    }

    /* renamed from: b */
    public void m1294b() {
        m1298a(3000L);
    }

    /* renamed from: a */
    public void m1298a(long j) {
        if (!this.f8299a.isShowing()) {
            int[] iArr = new int[2];
            this.f8302d.getLocationOnScreen(iArr);
            Rect rect = new Rect(iArr[0], iArr[1], iArr[0] + this.f8302d.getWidth(), iArr[1] + this.f8302d.getHeight());
            this.f8299a.setAnimationStyle(this.f8301c);
            this.f8299a.showAtLocation(this.f8302d, 0, rect.left, rect.top);
            if (this.f8307i != null) {
                this.f8307i.m1684a();
            }
            if (j != 0) {
                this.f8308j.removeMessages(1);
                this.f8308j.sendMessageDelayed(this.f8308j.obtainMessage(1), j);
            }
        }
    }
}
